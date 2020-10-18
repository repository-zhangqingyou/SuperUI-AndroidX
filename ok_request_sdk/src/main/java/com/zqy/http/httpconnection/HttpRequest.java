package com.zqy.http.httpconnection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 请求类  HttpURLConnectionClient
 * <p>
 * Author: zhangqingyou
 * Date: 2020/10/18
 * Des:
 */
public class HttpRequest {
    private static HttpRequest requestClient;
    private Map<String, Boolean> isRequestMap = new LinkedHashMap<>();//key:请求地址，vaue：是否可请求

    private HttpRequest() {
    }

    public static HttpRequest getInstance() {
        if (requestClient == null) {
            synchronized (HttpRequest.class) {
                if (requestClient == null)
                    requestClient = new HttpRequest();
            }
        }
        return requestClient;
    }


    public void get(String url, Map<String, String> headers, Map<String, String> params, StringDataCallBack stringDataCallBack) {
        request("GET", url, headers, params, stringDataCallBack);
    }

    public void post(String url, Map<String, String> headers, Map<String, String> params, StringDataCallBack stringDataCallBack) {
        request("POST", url, headers, params, stringDataCallBack);
    }


    /**
     * @param dataCallBack RequestMethod
     */

    private void request(final String requestMethod, final String urlAddress, final Map<String, String> headers, final Map<String, String> params, final StringDataCallBack dataCallBack) {
        dataCallBack.onStart(urlAddress, headers, params);

        if (!isRequestMap.containsKey(dataCallBack.getEndUrl())) {
            //默认设置当前链接可请求
            isRequestMap.put(dataCallBack.getEndUrl(), true);
        }
        if (isRequestMap.get(dataCallBack.getEndUrl())) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        isRequestMap.put(dataCallBack.getEndUrl(), false);//请求开始设置当前链接不可请求

                        StringBuffer stringBufferParams = null;
                        if (params != null) {
                            stringBufferParams = new StringBuffer();
                            for (Map.Entry<String, String> entry : params.entrySet()) {
                                stringBufferParams.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                            }
                            stringBufferParams.deleteCharAt(stringBufferParams.length() - 1);
                        }


                        HttpURLConnection conn = null;
                        if (requestMethod.equals("GET")) {
                            //GET采用拼接参数的方式
                            String url = "";
                            if (stringBufferParams != null) {
                                url = urlAddress + "?" + stringBufferParams.toString();
                            } else {
                                url = urlAddress;
                            }
                            conn = getHttpURLConnection(url, requestMethod, headers);
                            //连接
                            conn.connect();
                        } else if (requestMethod.equals("POST")) {
                            conn.setDoInput(true);
                            conn.setDoOutput(true);

                            //会默认去连接
                            OutputStream outputStream = conn.getOutputStream();
                            outputStream.write(stringBufferParams.toString().getBytes());

//                            InputStream inputStream = conn.getInputStream();
//
//                            streamToString(inputStream);
                        }

                        isRequestMap.put(dataCallBack.getEndUrl(), true);//请求完成设置当前链接可请求
                        dataCallBack.onFinish("请求完成");


                        //得到响应码
                        int responseCode = conn.getResponseCode();
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            InputStream is = conn.getInputStream();// 获取返回数据
                            String successString = getInputStreamString(is);
                            dataCallBack.onSuccess(successString);
                        } else {
                            String errorString = getInputStreamString(conn.getErrorStream());
                            dataCallBack.onError("responseCode:" + responseCode + "----- error:" + errorString);
                        }

                        //关闭连接
                        if (requestMethod.equals("POST")) {
                            conn.disconnect();
                        }
                    } catch (Exception e) {
                        dataCallBack.onError(e.getMessage());
                    }


                }
            };

            // 步骤4：创建线程对象，即 实例化线程类；线程类 = Thread类；
// 创建时通过Thread类的构造函数传入线程辅助类对象
// 原因：Runnable接口并没有任何对线程的支持，我们必须创建线程类（Thread类）的实例，从Thread类的一个实例内部运行
            Thread td = new Thread(runnable);

// 步骤5：通过 线程对象 控制线程的状态，如 运行、睡眠、挂起  / 停止
// 当调用start（）方法时，线程对象会自动回调线程辅助类对象的run（），从而实现线程操作
            td.start();
        } else {
            dataCallBack.onFinish(dataCallBack.getEndUrl() + "还未响应，相同接口无法再次请求！");
        }

    }

    /**
     * 设置请求信息
     *
     * @param urlAddress
     * @param requestMethod
     * @param headers
     * @return
     * @throws IOException
     */
    private HttpURLConnection getHttpURLConnection(String urlAddress, String requestMethod, Map<String, String> headers) throws IOException {
        URL url = new URL(urlAddress);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(requestMethod);
        conn.setConnectTimeout(30000);
        conn.setRequestProperty("User-Agent", "");
        conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                conn.setRequestProperty(key, value);
            }
        }
        return conn;
    }

    /**
     * 输入流转字符
     *
     * @param inputStream
     * @return
     */
    private String getInputStreamString(InputStream inputStream) {
        String inputLine = "";
        try {
            int data = inputStream.read();
            while (data != -1) {
                inputLine = inputLine + (char) data;
                data = inputStream.read();
            }
            inputStream.close();
        } catch (Exception ex) {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {

            }
        }
        return inputLine;
    }

    private String streamToString(InputStream inputStream) {
        String resultString = null;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = 0;
        byte data[] = new byte[1024];
        try {
            while ((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
            byte[] allData = byteArrayOutputStream.toByteArray();
            resultString = new String(allData, "utf-8");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return resultString;
    }


}

package com.zqy.baserequest.request;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 * <p>
 * 上传图片
 */

public class UploadImage {

    private static UploadImage request;
    private boolean signSwitch = false;//请求参数加密开关

    private UploadImage() {
    }

    public static UploadImage getInstance() {
        if (request == null) {
            synchronized (UploadImage.class) {
                if (request == null) {
                    request = new UploadImage();
                }
            }
        }
        return request;
    }


    /**
     * 单图上传
     *
     * @param urlFile
     */
    public void uploadImageOne(String urlFile, final UploadCallback uploadCallback) {
//        Request.getInstance().uploadImageOne(urlFile, new StringCallback() {
//            @Override
//            public void onFinish() {
//                super.onFinish();
//                uploadCallback.onFinish();
//            }
//
//            @Override
//            public void onError(Response<String> response) {
//                super.onError(response);
//                uploadCallback.onError();
//                ToastUtil.toast("单图上传连接失败！");
//            }
//
//            @Override
//            public void uploadProgress(Progress progress) {
//                super.uploadProgress(progress);
//                uploadCallback.schedule(progress.fraction);
//            }
//
//
//            @Override
//            public void onSuccess(Response<String> response) {
//                String body = response.body();
//                Log.d("uploadImageOne", body);
//                try {
//                    DescAndCode descAndCode = new Gson().fromJson(body, DescAndCode.class);
//                    uploadCallback.onSuccess(descAndCode);
//                } catch (JsonSyntaxException e) {
//                    ToastUtil.toast("单图上传-数据格式错误！" + e.getMessage());
//                }
//            }
//        });
    }


    public interface UploadCallback<T> {
        void onError();

        void onFinish();

        //上传进度
        void schedule(float progress);

        void onSuccess(T t);
    }
}

package com.zqy.srequest.request;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.zqy.srequest.bean.CodeAndMsg;
import com.zqy.sutils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 * <p>
 * 上传图片
 */

public class UploadImage {
    private String TAG;
    private static UploadImage request;

    private UploadImage() {
        TAG = getClass().getSimpleName();
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
     * 上传图片（多图上传）
     *
     * @param
     */
    private int index = 0;
    private List<String> imgList = new ArrayList<>();

    public void uploadImageOneMulti(final List<String> compressPathList, final UploadCallback<List<String>> uploadCallback) {
        final int size = compressPathList.size();
        uploadImageOne(compressPathList.get(index), new UploadCallback<CodeAndMsg>() {
            @Override
            public void onError() {
                index = 0;//上传失败 初始化
                imgList.clear();
                uploadCallback.onError();
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void schedule(float progress) {

                float v = (index + 1) * progress / size;

                uploadCallback.schedule(v);

            }

            @Override
            public void onSuccess(CodeAndMsg o) {
                Object data = o.getData();
                if (data != null) {
                    imgList.add(data.toString());
                } else {
                    uploadCallback.onFinish();
                    uploadCallback.onError();
                    return;
                }

                if (++index < size) {
                    uploadImageOneMulti(compressPathList, uploadCallback);
                } else {
                    List<String> imgUrlList = new ArrayList<>(imgList);
                    index = 0;//上传完毕 初始化
                    imgList.clear();

                    uploadCallback.onFinish();
                    uploadCallback.onSuccess(imgUrlList);
                }

            }
        });
    }

    /**
     * 单图上传
     *
     * @param urlFile
     */
    public void uploadImageOne(String urlFile, final UploadCallback uploadCallback) {
        Request.getInstance().uploadImageOne(urlFile, new StringCallback() {
            @Override
            public void onFinish() {
                super.onFinish();
                uploadCallback.onFinish();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                uploadCallback.onError();
                Log.d(TAG + "-上传失败：", response.getException().getMessage());
                ToastUtil.toast("单图上传连接失败！" + response.getException().getMessage());
            }

            @Override
            public void uploadProgress(Progress progress) {
                super.uploadProgress(progress);
                uploadCallback.schedule(progress.fraction);
            }


            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                Log.d(TAG + "-上传成功：", body);
                try {
                    CodeAndMsg CodeAndMsg = new Gson().fromJson(body, CodeAndMsg.class);
                    uploadCallback.onSuccess(CodeAndMsg);
                } catch (JsonSyntaxException e) {
                    Log.d(TAG + "-上传失败：", e.getMessage());
                    ToastUtil.toast("单图上传-数据格式错误！" + e.getMessage());
                }
            }
        });
    }


    public interface UploadCallback<T> {
        void onError();

        void onFinish();

        //上传进度
        void schedule(float progress);

        void onSuccess(T t);
    }
}

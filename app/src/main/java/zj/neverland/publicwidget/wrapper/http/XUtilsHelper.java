package zj.neverland.publicwidget.wrapper.http;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.Map;

/**
 * Created by cefoc on 2017/4/27.
 * Class Note: xutils连接http
 */

public class XUtilsHelper implements IBaseHelper {
    private static XUtilsHelper xutils = null;

    public synchronized static XUtilsHelper getInstance() {
        synchronized (XUtilsHelper.class) {
            if (xutils == null) {
                xutils = new XUtilsHelper();
            }
        }
        return xutils;
    }

    public XUtilsHelper() {
    }

    /**
     * 异步Get发送数据
     * @param url 链接地址
     * @param maps 参数
     * @param callback 返回操作
     */
    @Override
    public void sendGet(String url, Map<String, String> maps, final LoadCallback.ILoadCallBack callback) {
        RequestParams params = new Params(url).bodyParameter(maps).build();
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onFailure(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                callback.onCancelled();
            }

            @Override
            public void onFinished() {
                callback.onFinished();
            }
        });

    }

    /**
     * 异步Post发送数据
     * @param url 链接地址
     * @param maps 参数
     * @param callback 返回操作
     */
    @Override
    public void sendPost(String url, Map<String, String> maps, final LoadCallback.ILoadCallBack callback) {
        RequestParams params = new Params(url).bodyParameter(maps).build();
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onFailure(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                callback.onCancelled();
            }

            @Override
            public void onFinished() {
                callback.onFinished();
            }
        });
    }

    /**
     * 上传文件
     * @param url 连接地址
     * @param maps 参数
     * @param file 文件
     * @param callback 操作
     */
    @Override
    public void uploadFile(String url, Map<String, String> maps, Map<String, File> file, final LoadCallback.IFileLoadCallBack callback) {
        RequestParams params = new Params(url)
                .bodyParameter(maps)
                .fileBodyParameter(file)
                .setMultipart(true)
                .build();

        x.http().post(params, new Callback.ProgressCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onFailure(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                callback.onCancelled();
            }

            @Override
            public void onFinished() {
                callback.onFinished();
            }

            @Override
            public void onWaiting() {
                callback.onWaiting();
            }

            @Override
            public void onStarted() {
                callback.onStarted();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                callback.onLoading(total,current,isDownloading);
            }
        });
    }

    /**
     * 下载文件
     * @param url 链接地址
     * @param maps 参数
     * @param toPath 保存路径
     * @param callback 操作
     */
    @Override
    public void downloadFile(String url, Map<String, String> maps, String toPath, final LoadCallback.IFileLoadCallBack callback) {
        RequestParams params = new Params(url)
                .bodyParameter(maps)
                .setSaveFilePath(toPath)
                .setAutoRename(true)
                .build();
        x.http().post(params, new Callback.ProgressCallback<File>() {

            @Override
            public void onSuccess(File result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onFailure(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                callback.onCancelled();
            }

            @Override
            public void onFinished() {
                callback.onFinished();
            }

            @Override
            public void onWaiting() {
                callback.onWaiting();
            }

            @Override
            public void onStarted() {
                callback.onStarted();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                callback.onLoading(total,current,isDownloading);
            }
        });
    }

    public static class Params {
        private String charset;
        private String url;
        private Map<String, String> maps;
        private Map<String, File> fileMaps;
        private String savePath;
        private RequestParams params;

        public Params(String url) {
            RequestParams params = new RequestParams(url);
            this.url = url;
            this.charset = "UTF-8";
            this.params = params;
        }

        public void setHeader() {
//            this.addHeader();
        }

        public Params setMultipart(boolean multipart){
            this.params.setMultipart(multipart);
            return this;
        }
        public Params setAutoRename(boolean autoRename){
            this.params.setAutoRename(autoRename);
            return this;
        }
        public Params setSaveFilePath(String path){
            this.savePath = path;
            this.params.setSaveFilePath(savePath);
            return this;
        }
        public Params charset(String charset) {
            this.charset = charset;
            return this;
        }

        public Params bodyParameter(Map<String, String> maps) {
            this.maps = maps;
            return this;
        }

        public Params fileBodyParameter(Map<String,File> fileMaps){
            this.fileMaps = fileMaps;
            return this;
        }
        public RequestParams build() {
            params.setCharset(charset);

            if (maps != null && !maps.isEmpty()) {
                for (Map.Entry<String, String> entry : maps.entrySet()) {
                    params.addBodyParameter(entry.getKey(), entry.getValue());
                }
            }

            if(fileMaps!=null&&!fileMaps.isEmpty()){
                for (Map.Entry<String, File> entry : fileMaps.entrySet()) {
                    params.addBodyParameter(entry.getKey(), entry.getValue().getAbsoluteFile());
                }
            }
            return params;
        }

    }
}

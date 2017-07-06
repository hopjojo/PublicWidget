package zj.neverland.publicwidget.wrapper.http;

/**
 * Created by cefoc on 2017/4/1.
 */

public class LoadCallback {
    public interface ILoadCallBack<T> {
        void onSuccess(T result);

        void onFailure(T ex);

        void onCancelled();

        void onFinished();
    }
    public interface IFileLoadCallBack<T>{
        void onSuccess(T result);

        void onFailure(T ex);

        void onCancelled();

        void onFinished();

        void onWaiting();

        void onStarted();

        void onLoading(long total, long current, boolean isDownloading);
    }
}

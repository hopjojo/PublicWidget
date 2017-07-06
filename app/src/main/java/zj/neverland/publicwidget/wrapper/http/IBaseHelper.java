package zj.neverland.publicwidget.wrapper.http;

import java.io.File;
import java.util.Map;

/**
 * Created by cefoc on 2017/4/27.
 * Class Note:
 */

public interface IBaseHelper {
    void sendGet(String url, Map<String, String> maps, final LoadCallback.ILoadCallBack callback);
    void sendPost(String url, Map<String, String> maps, final LoadCallback.ILoadCallBack callback);
    void uploadFile(String url, Map<String, String> maps, Map<String, File> file, LoadCallback.IFileLoadCallBack callback);
    void downloadFile(String url, Map<String, String> maps, String toPath, LoadCallback.IFileLoadCallBack callback);
}

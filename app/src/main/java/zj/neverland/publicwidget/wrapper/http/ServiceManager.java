package zj.neverland.publicwidget.wrapper.http;

/**
 * Created by cefoc on 2017/4/27.
 * Class Note:
 */

public class ServiceManager {
    private IBaseHelper baseHelper;
    public ServiceManager(){
        baseHelper = XUtilsHelper.getInstance();
    }
    public IBaseHelper getBaseHelper(){
        return baseHelper;
    }
}

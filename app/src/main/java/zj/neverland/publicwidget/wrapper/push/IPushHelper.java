package zj.neverland.publicwidget.wrapper.push;

/**
 * Created by zj on 2017/6/27.
 * Class Note:
 */

public interface IPushHelper {
    /**
     * 初始化推送
     */
    void initPush();
    /**
     * 停止推送
     */
    void stopPush();
    /**
     * 恢复推送
     */
    void resumePush();
    /**
     * 判断推送是否停止
     */
    boolean isPushStopped();

}

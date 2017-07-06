//package zj.neverland.publicwidget.wrapper.push;
//
//import android.content.Context;
//
//import cn.jpush.android.api.JPushInterface;
//
///**
// * @author ZhaiJian
// * @data: 2017/6/28 15:40
// * @version: V1.0
// * Class Note: <极光用户行为统计相关>
// */
//public class JPushAnalytics {
//    /**
//     * 用于“用户使用时长”，“活跃用户”，“用户打开次数”的统计
//     * @author ZhaiJian
//     */
//    public static void JPushonResume(Context context){
//        JPushInterface.onResume(context);
//    }
//    public static void JPushonPause(Context context){
//        JPushInterface.onPause(context);
//    }
//
//    /**
//     * 用于上报用户的通知栏被打开，或者用于上报用户自定义消息被展示等客户端需要统计的事件。
//     * @param context
//     * @param msgId 推送每一条消息和通知对应的唯一 ID。
//     * @author ZhaiJian
//     */
//    public static void reportNotificationOpened(Context context, String msgId){
//        JPushInterface.reportNotificationOpened(context, msgId);
//    }
//    /**
//     * 开启CrashLog上报
//     * @author ZhaiJian
//     */
//    public static void startCrashLog(Context context){
//        JPushInterface.initCrashHandler(context);
//    }
//    /**
//     * 关闭CrashLog上报
//     * @author ZhaiJian
//     */
//    public static void stopCrashLog(Context context){
//        JPushInterface.stopCrashHandler(context);
//    }
//}

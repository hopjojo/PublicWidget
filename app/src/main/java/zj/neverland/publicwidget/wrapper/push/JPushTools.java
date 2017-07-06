//package zj.neverland.publicwidget.wrapper.push;
//
//import android.app.Notification;
//import android.content.Context;
//
//import cn.cefoc.R;
//import cn.jpush.android.api.BasicPushNotificationBuilder;
//
///**
// * @author ZhaiJian
// * @data: 2017/6/28 14:43
// * @version: V1.0
// * Class Note: <极光推送相关自定义方法>
// */
//public class JPushTools {
//    private Context context;
//    public enum Type{  DEFAULT,SOUND,VIBRATE,LIGHTS
//    }
//    public JPushTools(Context context){
//        this.context = context.getApplicationContext();
//    }
//    /**
//     * 设置通知提示方式 - 基础属性
//     */
//    public void setStyleBasic(Type type) {
//        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(context);
//        builder.statusBarDrawable = R.mipmap.ic_launcher;
//        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为点击后自动消失
//        switch (type){
//            case DEFAULT:
//                builder.notificationDefaults = Notification.DEFAULT_ALL;//设置为铃声加振动
//                break;
//            case SOUND:
//                builder.notificationDefaults = Notification.DEFAULT_SOUND;//设置为铃声
//                break;
//            case VIBRATE:
//                builder.notificationDefaults = Notification.DEFAULT_VIBRATE;//设置为震动
//                break;
//            case LIGHTS:
//                builder.notificationDefaults = Notification.DEFAULT_LIGHTS;//设置为灯光
//                break;
//        }
//        JPushManager.getInstance(context).customPushNotificationView(builder);
//    }
//
//
//    /**
//     * 设置通知栏样式 - 定义通知栏Layout
//     */
//    private void setStyleCustom(Context context) {
////        CustomPushNotificationBuilder builder = new CustomPushNotificationBuilder(context, R.layout.customer_notitfication_layout, R.id.icon, R.id.title, R.id.text);
////        builder.layoutIconDrawable = R.mipmap.ic_launcher;
////        builder.developerArg0 = "developerArg2";
////        JPushInterface.setPushNotificationBuilder(2, builder);
//    }
//}

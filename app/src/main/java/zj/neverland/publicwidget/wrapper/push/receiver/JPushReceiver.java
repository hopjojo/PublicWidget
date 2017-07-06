//package zj.neverland.publicwidget.wrapper.push.receiver;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.Iterator;
//
//import cn.cefoc.wrapper.push.Logger;
//import cn.jpush.android.api.JPushInterface;
//
///**
// * Created by zj on 2017/6/27.
// * Class Note:极光推送广播接收者，处理推送过来的消息
// */
//
//public class JPushReceiver extends BroadcastReceiver {
//    private static final String TAG = "JPush";
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        try {
//            Bundle bundle = intent.getExtras();
//            Logger.d(TAG, "[JPushReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
//            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
//                //SDK 向 JPush Server 注册所得到的注册 ID
//                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
//                Logger.d(TAG, "[JPushReceiver] 接收Registration Id : " + regId);
//            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
//                /**
//                 * 描述：
//                 * 收到了自定义消息 Push 。
//                 * SDK 对自定义消息，只是传递，不会有任何界面上的展示。
//                 * 如果开发者想推送自定义消息 Push，则需要在 AndroidManifest.xml 里配置此 Receiver action，并且在自己写的 BroadcastReceiver 里接收处理。
//                 */
//
//                 /**
//
//                 JPushInterface.EXTRA_TITLE
//                 保存服务器推送下来的消息的标题。
//                 对应 API 消息内容的 title 字段。
//                 Portal 推送消息界上不作展示
//                 Bundle bundle = intent.getExtras();
//                 String title = bundle.getString(JPushInterface.EXTRA_TITLE);
//
//                 JPushInterface.EXTRA_MESSAGE
//                 保存服务器推送下来的消息内容。
//                 对应 API 消息内容的 message 字段。
//                 对应 Portal 推送消息界面上的"自定义消息内容”字段。
//                 Bundle bundle = intent.getExtras();
//                 String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//
//                 JPushInterface.EXTRA_EXTRA
//                 保存服务器推送下来的附加字段。这是个 JSON 字符串。
//                 对应 API 消息内容的 extras 字段。
//                 对应 Portal 推送消息界面上的“可选设置”里的附加字段。
//                 Bundle bundle = intent.getExtras();
//                 String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//
//                 JPushInterface.EXTRA_MSG_ID
//                 SDK 1.6.1 以上版本支持。
//                 唯一标识消息的 ID, 可用于上报统计等。
//                 Bundle bundle = intent.getExtras();
//                 String file = bundle.getString(JPushInterface.EXTRA_MSG_ID);
//                 */
//                Logger.d(TAG, "[JPushReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
////                processCustomMessage(context, bundle);
//
//            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
//                /**
//                 * 描述：
//                 * 收到了通知 Push。
//                 * 如果通知的内容为空，则在通知栏上不会展示通知。
//                 * 但是，这个广播 Intent 还是会有。开发者可以取到通知内容外的其他信息。
//                 */
//
//                 /**
//
//                 JPushInterface.EXTRA_NOTIFICATION_TITLE
//                 保存服务器推送下来的通知的标题。
//                 对应 API 通知内容的 title 字段。
//                 对应 Portal 推送通知界面上的“通知标题”字段。
//                 Bundle bundle = intent.getExtras();
//                 String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
//
//                 JPushInterface.EXTRA_ALERT
//                 保存服务器推送下来的通知内容。
//                 对应 API 通知内容的 alert 字段。
//                 对应 Portal 推送通知界面上的“通知内容”字段。
//                 Bundle bundle = intent.getExtras();
//                 String content = bundle.getString(JPushInterface.EXTRA_ALERT);
//
//                 JPushInterface.EXTRA_EXTRA
//                 SDK 1.2.9 以上版本支持。
//                 保存服务器推送下来的附加字段。这是个 JSON 字符串。
//                 对应 API 通知内容的 extras 字段。
//                 对应 Portal 推送消息界面上的“可选设置”里的附加字段。
//                 Bundle bundle = intent.getExtras();
//                 String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//
//                 JPushInterface.EXTRA_NOTIFICATION_ID
//                 SDK 1.3.5 以上版本支持。
//                 通知栏的Notification ID，可以用于清除Notification
//                 如果服务端内容（alert）字段为空，则notification id 为0
//                 Bundle bundle = intent.getExtras();
//                 int notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
//
//                 JPushInterface.EXTRA_RICHPUSH_HTML_PATH
//                 SDK 1.4.0 以上版本支持。
//                 富媒体通知推送下载的HTML的文件路径,用于展现WebView。
//                 Bundle bundle = intent.getExtras();
//                 String fileHtml = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_PATH);
//
//                 JPushInterface.EXTRA_RICHPUSH_HTML_RES
//                 SDK 1.4.0 以上版本支持。
//                 富媒体通知推送下载的图片资源的文件名,多个文件名用 “，” 分开。 与 “JPushInterface.EXTRA_RICHPUSH_HTML_PATH” 位于同一个路径。
//                 Bundle bundle = intent.getExtras();
//                 String fileStr = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_RES);
//                 String[] fileNames = fileStr.split(",");
//
//                 JPushInterface.EXTRA_MSG_ID
//                 SDK 1.6.1 以上版本支持。
//                 唯一标识通知消息的 ID, 可用于上报统计等。
//                 Bundle bundle = intent.getExtras();
//                 String file = bundle.getString(JPushInterface.EXTRA_MSG_ID);
//
//                 JPushInterface.EXTRA_BIG_TEXT
//                 SDK 3.0.0 以上版本支持，支持 api 16 以上的rom。
//                 大文本通知样式中大文本的内容。
//                 Bundle bundle = intent.getExtras();
//                 String bigText = bundle.getString(JPushInterface.EXTRA_BIG_TEXT);
//
//                 JPushInterface.EXTRA_BIG_PIC_PATH
//                 SDK 3.0.0 以上版本支持，支持 api 16 以上的rom。
//                 可支持本地图片的路径，或者填网络图片地址。
//                 大图片通知样式中大图片的路径/地址。
//                 Bundle bundle = intent.getExtras();
//                 String bigPicPath = bundle.getString(JPushInterface.EXTRA_BIG_PIC_PATH);
//
//                 JPushInterface.EXTRA_INBOX
//                 SDK 3.0.0 以上版本支持，支持 api 16 以上的rom。
//                 获取的是一个 JSONObject，json 的每个 key 对应的 value 会被当作文本条目逐条展示。
//                 收件箱通知样式中收件箱的内容。
//                 Bundle bundle = intent.getExtras();
//                 String inboxJson = bundle.getString(JPushInterface.EXTRA_INBOX);
//
//                 JPushInterface.EXTRA_NOTI_PRIORITY
//                 SDK 3.0.0 以上版本支持, 支持 api 16 以上的rom。
//                 默认为0，范围为 -2～2 ，其他值将会被忽略而采用默认。
//                 通知的优先级。
//                 Bundle bundle = intent.getExtras();
//                 String prio = bundle.getString(JPushInterface.EXTRA_NOTI_PRIORITY);
//
//                 JPushInterface.EXTRA_NOTI_CATEGORY
//                 SDK 3.0.0 以上版本支持, 支持 api 21 以上的rom。
//                 完全依赖 rom 厂商对每个 category 的处理策略，比如通知栏的排序。
//                 通知分类。
//                 Bundle bundle = intent.getExtras();
//                 String prio = bundle.getString(JPushInterface.EXTRA_NOTI_CATEGORY);
//                 */
//                Logger.d(TAG, "[JPushReceiver] 接收到推送下来的通知");
//                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
//                Logger.d(TAG, "[JPushReceiver] 接收到推送下来的通知的ID: " + notifactionId);
//
//            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
//                /**
//                 * 描述
//                 * 用户点击了通知。 一般情况下，用户不需要配置此 receiver action。
//                 * 如果开发者在 AndroidManifest.xml 里未配置此 receiver action，那么，SDK 会默认打开应用程序的主 Activity，相当于用户点击桌面图标的效果。
//                 * 如果开发者在 AndroidManifest.xml 里配置了此 receiver action，那么，当用户点击通知时，SDK 不会做动作。开发者应该在自己写的 BroadcastReceiver 类里处理，比如打开某 Activity 。
//                 */
//
//                /**
//                 JPushInterface.EXTRA_NOTIFICATION_TITLE
//                 保存服务器推送下来的通知的标题。
//                 对应 API 通知内容的 title 字段。
//                 对应 Portal 推送通知界面上的“通知标题”字段。
//                 Bundle bundle = intent.getExtras();
//                 String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
//
//                 JPushInterface.EXTRA_ALERT
//                 保存服务器推送下来的通知内容。
//                 对应 API 通知内容的alert字段。
//                 对应 Portal 推送通知界面上的“通知内容”字段。
//                 Bundle bundle = intent.getExtras();
//                 String content = bundle.getString(JPushInterface.EXTRA_ALERT);
//
//                 JPushInterface.EXTRA_EXTRA
//                 SDK 1.2.9 以上版本支持。
//                 保存服务器推送下来的附加字段。这是个 JSON 字符串。
//                 对应 API 消息内容的 extras 字段。
//                 对应 Portal 推送消息界面上的“可选设置”里的附加字段。
//                 Bundle bundle = intent.getExtras();
//                 String type = bundle.getString(JPushInterface.EXTRA_EXTRA);
//
//                 JPushInterface.EXTRA_NOTIFICATION_ID
//                 SDK 1.3.5 以上版本支持。
//                 通知栏的Notification ID，可以用于清除Notification
//                 Bundle bundle = intent.getExtras();
//                 int notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
//
//                 JPushInterface.EXTRA_MSG_ID
//                 SDK 1.6.1 以上版本支持。
//                 唯一标识调整消息的 ID, 可用于上报统计等。
//                 Bundle bundle = intent.getExtras();
//                 String file = bundle.getString(JPushInterface.EXTRA_MSG_ID);
//                 */
//                Logger.d(TAG, "[JPushReceiver] 用户点击打开了通知");
//                //上报点击通知栏次数
////                JPushAnalytics.reportNotificationOpened(context,bundle.getString(JPushInterface.EXTRA_MSG_ID));
//                //打开自定义的Activity
//
//            } else if (JPushInterface.ACTION_NOTIFICATION_CLICK_ACTION.equals(intent.getAction())) {
//                /**
//                 * 描述
//                 * 用户点击了通知栏中自定义的按钮。(SDK 3.0.0 以上版本支持)
//                 * 使用普通通知的开发者不需要配置此 receiver action。只有开发者使用了 MultiActionsNotificationBuilder 构建携带按钮的通知栏的通知时，可通过该 action 捕获到用户点击通知栏按钮的操作，并自行处理。
//                 */
//                /**
//
//                 JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA
//                 SDK 3.0.0 以上版本支持。
//                 获取通知栏按钮点击事件携带的附加信息。
//                 对应使用 MultiActionsNotificationBuilder.addJPushAction 添加的按钮信息。
//                 private void setAddActionsStyle() {
//                 MultiActionsNotificationBuilder builder = new MultiActionsNotificationBuilder(PushSetActivity.this);
//                 builder.addJPushAction(R.drawable.jpush_ic_richpush_actionbar_back, "first", "my_extra1");
//                 builder.addJPushAction(R.drawable.jpush_ic_richpush_actionbar_back, "second", "my_extra2");
//                 builder.addJPushAction(R.drawable.jpush_ic_richpush_actionbar_back, "third", "my_extra3");
//                 JPushInterface.setPushNotificationBuilder(10, builder);
//
//                 Toast.makeText(PushSetActivity.this, "AddActions Builder - 10", Toast.LENGTH_SHORT).show();
//                 }
//                 获取到对应的附加信息，确定是哪个按钮后自行处理。
//                 else if (JPushInterface.ACTION_NOTIFICATION_CLICK_ACTION.equals(intent.getAction())){
//                 Log.d(TAG, "[MyReceiver] 用户点击了通知栏按钮");
//                 String nActionExtra = intent.getExtras().getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA);
//
//                 开发者根据不同 Action 携带的 extra 字段来分配不同的动作。
//                 if(nActionExtra==null){
//                 Log.d(TAG,"ACTION_NOTIFICATION_CLICK_ACTION nActionExtra is null");
//                 return;
//                 }
//                 if (nActionExtra.equals("my_extra1")) {
//                 Log.d(TAG, "[MyReceiver] 用户点击通知栏按钮一");
//                 } else if (nActionExtra.equals("my_extra2")) {
//                 Log.d(TAG, "[MyReceiver] 用户点击通知栏按钮二");
//                 } else if (nActionExtra.equals("my_extra3")) {
//                 Log.d(TAG, "[MyReceiver] 用户点击通知栏按钮三");
//                 } else {
//                 Log.d(TAG, "[MyReceiver] 用户点击通知栏按钮未定义");
//                 }
//                 }
//                 */
//                Logger.d(TAG, "[JPushReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
//
//            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
//                /**
//                 * 描述
//                 * 表示接收富文本（如网页、多媒体等等）回调的事件
//                 * 在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
//                 */
//                Logger.d(TAG, "[JPushReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
//            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
//                /**
//                 * 描述
//                 * JPush 服务的连接状态发生变化。（注：不是指 Android 系统的网络连接状态。）
//                 */
//                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
//                Logger.w(TAG, "[JPushReceiver]" + intent.getAction() + " connected state change to " + connected);
//            } else {
//                Logger.d(TAG, "[JPushReceiver] Unhandled intent - " + intent.getAction());
//            }
//        }catch (Exception ex){
//            Logger.d(TAG, "[JPushReceiver] Error - " + intent.getAction());
//        }
//
//    }
//    // 打印所有的 intent extra 数据
//    private static String printBundle(Bundle bundle) {
//        StringBuilder sb = new StringBuilder();
//        for (String key : bundle.keySet()) {
//            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
//                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
//            }else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
//                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
//            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
//                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
//                    Logger.i(TAG, "This message has no Extra data");
//                    continue;
//                }
//
//                try {
//                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
//                    Iterator<String> it =  json.keys();
//
//                    while (it.hasNext()) {
//                        String myKey = it.next().toString();
//                        sb.append("\nkey:" + key + ", value: [" +
//                                myKey + " - " +json.optString(myKey) + "]");
//                    }
//                } catch (JSONException e) {
//                    Logger.e(TAG, "Get message extra JSON error!");
//                }
//
//            } else {
//                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
//            }
//        }
//        return sb.toString();
//    }
//}

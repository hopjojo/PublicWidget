//package zj.neverland.publicwidget.wrapper.push;
//
//import android.content.Context;
//
//import java.util.Set;
//
//import cn.jpush.android.api.BasicPushNotificationBuilder;
//import cn.jpush.android.api.DefaultPushNotificationBuilder;
//import cn.jpush.android.api.JPushInterface;
//import cn.jpush.android.api.TagAliasCallback;
//
///**
// * @author ZhaiJian
// * @data: 2017/6/27 09:37
// * @version: V1.0
// * Class Note: <推送相关功能实现类>
// */
//public class JPushManager implements IPushHelper {
//    private Context context;
//    public static JPushManager instance = null;
//
//    /**
//     * 构造方法
//     *
//     * @param context
//     */
//    public JPushManager(Context context) {
//        this.context = context.getApplicationContext();
//    }
//
//    public static synchronized JPushManager getInstance(Context context) {
//        if (instance == null) {
//            instance = new JPushManager(context);
//        }
//        return instance;
//    }
//
//    /**
//     * 初始化推送
//     *
//     * @author ZhaiJian
//     */
//    @Override
//    public void initPush() {
//        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
//        JPushInterface.init(context);         // 初始化 JPush
//        requestPermission();
//        setLatestNotificationNumber(3);
//    }
//
//    /**
//     * 停止推送
//     *
//     * @author ZhaiJian
//     */
//    @Override
//    public void stopPush() {
//        JPushInterface.stopPush(context);
//    }
//
//    /**
//     * 恢复推送
//     *
//     * @author ZhaiJian
//     */
//    @Override
//    public void resumePush() {
//        JPushInterface.resumePush(context);
//    }
//
//    /**
//     * 判断推送是否停止
//     *
//     * @author ZhaiJian
//     */
//    @Override
//    public boolean isPushStopped() {
//        return JPushInterface.isPushStopped(context);
//    }
//
//    /**
//     * 清除所有展现的通知
//     *
//     * @author ZhaiJian
//     */
//    public void clearAllNotifications() {
//        JPushInterface.clearAllNotifications(context);
//    }
//
//    /**
//     * 清除指定某个通知。
//     *
//     * @param Id 通知ID
//     * @author ZhaiJian
//     */
//    public void clearNotificationById(int Id) {
//        JPushInterface.clearNotificationById(context, Id);
//    }
//
//    /**
//     * 设置允许推送的时间。
//     * 默认情况下用户在任何时间都允许推送。即任何时候有推送下来，客户端都会收到，并展示。
//     *
//     * @param weekDays  0表示星期天，1表示星期一，以此类推。 （7天制，Set集合里面的int范围为0到6）
//     *                  set的值为null,则任何时间都可以收到消息和通知，set的size为0，则表示任何时间都收不到消息和通知.
//     * @param startHour 允许推送的开始时间 （24小时制：startHour的范围为0到23）
//     * @param endHour   允许推送的结束时间 （24小时制：endHour的范围为0到23）
//     * @author ZhaiJian
//     */
//    public void setPushTime(Set<Integer> weekDays, int startHour, int endHour) {
//        JPushInterface.setPushTime(context, weekDays, startHour, endHour);
//    }
//
//    /**
//     * 设置静默推送时间
//     * 如果在该时间段内收到消息，则：不会有铃声和震动。
//     *
//     * @param startHour   静音时段的开始时间 - 小时 （24小时制，范围：0~23 ）
//     * @param startMinute 静音时段的开始时间 - 分钟（范围：0~59 ）
//     * @param endHour     静音时段的结束时间 - 小时 （24小时制，范围：0~23 ）
//     * @param endMinute   静音时段的结束时间 - 分钟（范围：0~59 ）
//     * @author ZhaiJian
//     */
//    public void setSilenceTime(int startHour, int startMinute, int endHour, int endMinute) {
//        JPushInterface.setSilenceTime(context, startHour, startMinute, endHour, endMinute);
//    }
//
//    /**
//     * 推送权限申请
//     *
//     * @author ZhaiJian
//     */
//    public void requestPermission() {
//        JPushInterface.requestPermission(context);
//    }
//
//
//    /**
//     * 设置默认通知栏样式
//     * @param builder 用于定制通知栏样式的构建类：
//     *                BasicPushNotificationBuilder
//     *                Basic 用于定制 Android Notification 里的 defaults / flags / icon 等基础样式（行为）
//     *                CustomPushNotificationBuilder
//     *                继承 Basic 进一步让开发者定制 Notification Layout
//     *                MultiActionsNotificationBuilder
//     *                继承 DefaultPushNotificationBuilder 进一步让开发者定制 Notification Layout
//     * @author ZhaiJian
//     */
//    public void customPushNotificationView(DefaultPushNotificationBuilder builder){
//        JPushInterface.setDefaultPushNotificationBuilder(builder);
//    }
//
//    /**
//     * 设置某编号的通知栏样式
//     * @param notificationBuilderId 通知Id
//     * @param builder 用于定制通知栏样式的构建类
//     * @author ZhaiJian
//     */
//    public void customPushNotificationViewWithId(Integer notificationBuilderId, BasicPushNotificationBuilder builder){
//        JPushInterface.setPushNotificationBuilder(notificationBuilderId, builder);
//    }
//
//    /**
//     * 限制保留的通知条数。默认为保留最近 5 条通知。
//     * @param MaxNum 最多显示的条数
//     * @author ZhaiJian
//     */
//    public void setLatestNotificationNumber(int MaxNum){
//        JPushInterface.setLatestNotificationNumber(context,MaxNum);
//    }
//
//    /**
//     * 获取连接状态
//     * @author ZhaiJian
//     */
//    public void getConnectionState(){
//        JPushInterface.getConnectionState(context);
//    }
//
//    /**
//     * 设置别名和标签。
//     * 需要理解的是，这个接口是覆盖逻辑，而不是增量逻辑。即新的调用会覆盖之前的设置。
//     *
//     * @param alias    null 此次调用不设置此值。（注：不是指的字符串"null"）
//     *                 "" （空字符串）表示取消之前的设置。
//     *                 每次调用设置有效的别名，覆盖之前的设置。
//     *                 有效的别名组成：字母（区分大小写）、数字、下划线、汉字、特殊字符(v2.1.6支持)@!#$&*+=.|。
//     *                 限制：alias 命名长度限制为 40 字节。（判断长度需采用UTF-8编码）
//     * @param tags     null 此次调用不设置此值。（注：不是指的字符串"null"）
//     *                 空数组或列表表示取消之前的设置。
//     *                 每次调用至少设置一个 tag，覆盖之前的设置，不是新增。
//     *                 有效的标签组成：字母（区分大小写）、数字、下划线、汉字、特殊字符(v2.1.6支持)@!#$&*+=.|。
//     *                 限制：每个 tag 命名长度限制为 40 字节，最多支持设置 1000 个 tag，但总长度不得超过7K字节。（判断长度需采用UTF-8编码）
//     * @param callback 在 TagAliasCallback 的 gotResult 方法，返回对应的参数 alias, tags。并返回对应的状态码：0为成功，其他返回码请参考错误码定义。
//     * @return void
//     * @throws （方法有异常的话加）
//     * @author ZhaiJian
//     */
//    public void setAliasAndTags(String alias, Set<String> tags, TagAliasCallback callback) {
//        /*
//         * callback 对应值
//         * 0     成功
//         *6001   无效的设置，tag/alias 不应参数都为 null
//         *6002   设置超时    建议重试
//         *6003   alias 字符串不合法    有效的别名、标签组成：字母（区分大小写）、数字、下划线、汉字。
//         *6004   alias超长。最多 40个字节    中文 UTF-8 是 3 个字节
//         *6005   某一个 tag 字符串不合法  有效的别名、标签组成：字母（区分大小写）、数字、下划线、汉字。
//         *6006   某一个 tag 超长。一个 tag 最多 40个字节  中文 UTF-8 是 3 个字节
//         *6007   tags 数量超出限制。最多 100个 这是一台设备的限制。一个应用全局的标签数量无限制。
//         *6008   tag/alias 超出总长度限制。总长度最多 1K 字节
//         *6011   10s内设置tag或alias大于3次 短时间内操作过于频繁
//         **/
//        JPushInterface.setAliasAndTags(context, alias, tags, callback);
//    }
//
//    /**
//     * 设置别名。
//     * 需要理解的是，这个接口是覆盖逻辑，而不是增量逻辑。即新的调用会覆盖之前的设置。
//     *
//     * @param alias    null 此次调用不设置此值。（注：不是指的字符串"null"）
//     *                 "" （空字符串）表示取消之前的设置。
//     *                 每次调用设置有效的别名，覆盖之前的设置。
//     *                 有效的别名组成：字母（区分大小写）、数字、下划线、汉字、特殊字符(v2.1.6支持)@!#$&*+=.|。
//     *                 限制：alias 命名长度限制为 40 字节。（判断长度需采用UTF-8编码）
//     * @param callback 在 TagAliasCallback 的 gotResult 方法，返回对应的参数 alias, tags。并返回对应的状态码：0为成功，其他返回码请参考错误码定义。
//     * @return void
//     * @throws （方法有异常的话加）
//     * @author ZhaiJian
//     */
//    public void setAlias(String alias, TagAliasCallback callback) {
//        JPushInterface.setAlias(context, alias, callback);
//    }
//
//    /**
//     * 设置标签。
//     * 需要理解的是，这个接口是覆盖逻辑，而不是增量逻辑。即新的调用会覆盖之前的设置。
//     *
//     * @param tags     null 此次调用不设置此值。（注：不是指的字符串"null"）
//     *                 空数组或列表表示取消之前的设置。
//     *                 每次调用至少设置一个 tag，覆盖之前的设置，不是新增。
//     *                 有效的标签组成：字母（区分大小写）、数字、下划线、汉字、特殊字符(v2.1.6支持)@!#$&*+=.|。
//     *                 限制：每个 tag 命名长度限制为 40 字节，最多支持设置 1000 个 tag，但总长度不得超过7K字节。（判断长度需采用UTF-8编码）
//     * @param callback 在 TagAliasCallback 的 gotResult 方法，返回对应的参数 alias, tags。并返回对应的状态码：0为成功，其他返回码请参考错误码定义。
//     * @return void
//     * @throws （方法有异常的话加）
//     * @author ZhaiJian
//     */
//    public void setTags(Set<String> tags, TagAliasCallback callback) {
//        JPushInterface.setTags(context, tags, callback);
//    }
//
//    /**
//     * 调用此 API 来取得应用程序对应的 RegistrationID。
//     * 只有当应用程序成功注册到 JPush 的服务器时才返回对应的值，否则返回空字符串。
//     *
//     * 应用程序在第一次成功注册到 JPush 服务器时，JPush 服务器会给客户端返回一个唯一的该设备的标识 - RegistrationID。
//     * JPush SDK 会以广播的形式发送 RegistrationID 到应用程序。
//     * 应用程序可以把此 RegistrationID 保存以自己的应用服务器上，然后就可以根据 RegistrationID 来向设备推送消息或者通知。
//     *
//     * @return void
//     * @throws （方法有异常的话加）
//     * @author ZhaiJian
//     */
//    public void getRegistrationID() {
//        JPushInterface.getRegistrationID(context);
//    }
//}

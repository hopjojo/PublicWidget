package zj.neverland.publicsdk.utils;

/**
 * Created by sjzhand on 2017/4/16.
 */

public class PhoneUtils {

    /**
     * 获取手机品牌
     *
     * @return
     */
    public static String getPhoneBrand() {
        return  StringUtils.isBlank(android.os.Build.BRAND) ?"未知":android.os.Build.BRAND  ;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneModel() {
        return StringUtils.isBlank(android.os.Build.MODEL) ? "未知": android.os.Build.MODEL;
    }

    /**
     * 获取手机Android API等级（22、23 ...）
     *
     * @return
     */
    public static int getBuildLevel() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机Android 版本（4.4、5.0、5.1 ...）
     *
     * @return
     */
    public static String getBuildVersion() {
        return StringUtils.isBlank(android.os.Build.VERSION.RELEASE) ?"未知":android.os.Build.VERSION.RELEASE;
    }

}

package zj.neverland.publicsdk.utils;

import android.widget.ImageView;

import org.xutils.image.ImageOptions;

import cn.cefoc.R;

/**
 * Created by sjzhand on 2017/4/17.
 */


public class ImageOptionsUtils {
    /**
     * 首页顶部广告滚动
     */
    public static final ImageOptions convenientOptions = new ImageOptions.Builder()
            //设置图片的大小
//            .setSize(300, 300)
            // 如果ImageView的大小不是定义为wrap_content, 不要crop.
//            .setCrop(true)
            // 加载中或错误图片的ScaleType
            //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
            .setImageScaleType(ImageView.ScaleType.FIT_XY)
            //设置加载过程中的图片  透明白图
            // .setLoadingDrawableId(R.mipmap.ic_launcher)
            //设置加载失败后的图片
            //  .setFailureDrawableId(R.mipmap.ic_launcher)
            //设置使用缓存
            .setUseMemCache(true)
            //设置支持gif
            .setIgnoreGif(false)
            //设置显示圆形图片
            //.setCircular(true)
            .build();
    /**
     * 首页按钮组的options
     */
    public static final ImageOptions fit_xy_Options = new ImageOptions.Builder()
            .setImageScaleType(ImageView.ScaleType.FIT_XY)
            //设置使用缓存
            .setUseMemCache(true)
            //设置支持gif
            .setIgnoreGif(false)
            .build();

    public static final ImageOptions center_inside_Options = new ImageOptions.Builder()
            .setImageScaleType(ImageView.ScaleType.CENTER_INSIDE)
            //设置使用缓存
            .setUseMemCache(true)
            //设置支持gif
            .setIgnoreGif(false)
            .build();



    public static final ImageOptions fix_center_Options = new ImageOptions.Builder()
            .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
            //设置使用缓存
            .setUseMemCache(true)
            //设置支持gif
            .setIgnoreGif(false)
            .build();

    public static final ImageOptions fix_center_round_Options = new ImageOptions.Builder()
            .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
            .setRadius(4)
            //设置使用缓存
            .setUseMemCache(true)
            //设置支持gif
            .setIgnoreGif(false)
            .build();


    public static final ImageOptions newslistOptions = new ImageOptions.Builder()
            //设置图片的大小
//            .setSize(300, 300)
            // 如果ImageView的大小不是定义为wrap_content, 不要crop.
//            .setCrop(true)
            // 加载中或错误图片的ScaleType
            .setPlaceholderScaleType(ImageView.ScaleType.FIT_XY)
            .setImageScaleType(ImageView.ScaleType.FIT_XY)
            //设置加载过程中的图片  透明白图
             .setLoadingDrawableId(R.mipmap.news)
            //设置加载失败后的图片
              .setFailureDrawableId(R.mipmap.news)
            //设置使用缓存
            .setUseMemCache(true)
            //设置支持gif
            .setIgnoreGif(false)
            //设置显示圆形图片
            //.setCircular(true)
            .build();
    public static final ImageOptions subProOptions = new ImageOptions.Builder()
            //设置图片的大小
//            .setSize(300, 300)
            // 如果ImageView的大小不是定义为wrap_content, 不要crop.
//            .setCrop(true)
            // 加载中或错误图片的ScaleType
            .setPlaceholderScaleType(ImageView.ScaleType.FIT_XY)
            .setImageScaleType(ImageView.ScaleType.FIT_XY)
            //设置加载过程中的图片  透明白图
             .setLoadingDrawableId(R.mipmap.pic_dir)
            //设置加载失败后的图片
              .setFailureDrawableId(R.mipmap.pic_dir)
            //设置使用缓存
            .setUseMemCache(true)
            //设置支持gif
            .setIgnoreGif(false)
            //设置显示圆形图片
            //.setCircular(true)
            .build();

    /**
     * 图片查看器指定图片，ScaleType.MATRIX
     */
    public static final ImageOptions imageDetailOptions = new ImageOptions.Builder()
            //设置图片的大小
//            .setSize(, 300)
            // 如果ImageView的大小不是定义为wrap_content, 不要crop.
//            .setCrop(true)
            // 加载中或错误图片的ScaleType
            .setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
            .setImageScaleType(ImageView.ScaleType.MATRIX)
            //设置加载过程中的图片  透明白图
             .setLoadingDrawableId(R.mipmap.pic_dir)
            //设置加载失败后的图片
              .setFailureDrawableId(R.mipmap.pic_dir)
            .setCrop(false)//
            //设置使用缓存
            .setUseMemCache(true)
            //设置支持gif
            .setIgnoreGif(false)
            //设置显示圆形图片
            //.setCircular(true)
            .build();


}


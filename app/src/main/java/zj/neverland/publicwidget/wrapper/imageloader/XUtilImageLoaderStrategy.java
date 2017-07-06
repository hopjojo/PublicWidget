package zj.neverland.publicwidget.wrapper.imageloader;

import android.content.Context;
import android.widget.ImageView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Created by cefoc on 2017/4/27.
 * Class Note:
 */

public class XUtilImageLoaderStrategy implements BaseImageLoaderStrategy {
    private ImageOptions imageOptions;
    @Override
    public void loadImage(Context ctx, ImageLoader img) {

        int strategy = img.getWifiStrategy();
        if (strategy == ImageLoaderUtil.LOAD_STRATEGY_ONLY_WIFI) {
            int netType = ImageLoaderUtil.getNetWorkType(ctx);
            //if wifi
            if (netType == ImageLoaderUtil.NETWORKTYPE_WIFI) {
                loadNormal(ctx, img);
            } else {
                //if not wifi
                loadNormal(ctx, img);
            }
        } else {
            //如果不是在wifi下才加载图片
            loadNormal(ctx, img);
        }
    }


    private void loadNormal(Context ctx, ImageLoader img){
        imageOptions = setconfig(img);
        x.image().bind(img.getImgView(),img.getUrl(), imageOptions);
    }
    private ImageOptions setconfig(ImageLoader img){
        ImageOptions newslistOptions = new ImageOptions.Builder()
                //设置图片的大小
//            .setSize(300, 300)
                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
//            .setCrop(true)
                // 加载中或错误图片的ScaleType
                .setPlaceholderScaleType(ImageView.ScaleType.FIT_XY)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                //设置加载过程中的图片  透明白图
                .setLoadingDrawableId(img.getPlaceHolder())
                //设置加载失败后的图片
                .setFailureDrawableId(img.getFailurePic())
                //设置使用缓存
                .setUseMemCache(true)
                //设置支持gif
                .setIgnoreGif(false)
                //设置显示圆形图片
                //.setCircular(true)
                .build();
        return newslistOptions;
    }
}

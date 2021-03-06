package zj.neverland.publicwidget.wrapper.imageloader;

import android.widget.ImageView;

import zj.neverland.publicwidget.R;

public class ImageLoader {
    private int type;  // (Big,Medium,small)
    private String url; //url to parse
    private int placeHolder; //placeholder when loading pics
    private int failurePic;//when fail to load pics
    private ImageView imgView; //ImageView instantce
    private int wifiStrategy;//load strategy ,wheather under wifi

    private ImageLoader(Builder builder) {
        this.type = builder.type;
        this.url = builder.url;
        this.placeHolder = builder.placeHolder;
        this.failurePic = builder.failurePic;
        this.imgView = builder.imgView;
        this.wifiStrategy = builder.wifiStrategy;
    }
    public int getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }
    public int getFailurePic(){
        return failurePic;
    }
    public ImageView getImgView() {
        return imgView;
    }

    public int getWifiStrategy() {
        return wifiStrategy;
    }

    public static class Builder {
        private int type;
        private String url;
        private int placeHolder;
        private int failurePic;
        private ImageView imgView;
        private int wifiStrategy;

        public Builder() {
            this.type = ImageLoaderUtil.PIC_SMALL;
            this.url = "";
            this.placeHolder = R.mipmap.news;
            this.failurePic = R.mipmap.empty_photo;
            this.imgView = null;
            this.wifiStrategy = ImageLoaderUtil.LOAD_STRATEGY_NORMAL;
        }

        public Builder type(int type) {
            this.type = type;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder placeHolder(int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }
        public Builder failurePic(int failurePic) {
            this.failurePic = failurePic;
            return this;
        }
        public Builder imgView(ImageView imgView) {
            this.imgView = imgView;
            return this;
        }

        public Builder strategy(int strategy) {
            this.wifiStrategy = strategy;
            return this;
        }

        public ImageLoader build() {
            return new ImageLoader(this);
        }

    }
}

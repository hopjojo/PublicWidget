package zj.neverland.publicwidget.widget.addmorerecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import zj.neverland.publicwidget.wrapper.imageloader.ImageLoader;
import zj.neverland.publicwidget.wrapper.imageloader.ImageLoaderUtil;

/**
 * Time: 2017年4月25日17:46:32
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private ImageLoaderUtil imageLoaderUtil;
    /**
     * 私有构造方法
     *
     * @param itemView
     */
    private ViewHolder(View itemView) {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<>();
        imageLoaderUtil = new ImageLoaderUtil();
    }

    public static ViewHolder create(Context context, int layoutId, ViewGroup parent) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new ViewHolder(itemView);
    }

    public static ViewHolder create(View itemView) {
        return new ViewHolder(itemView);
    }

    /**
     * 通过id获得控件
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public View getSwipeView() {
        ViewGroup itemLayout = ((ViewGroup) mConvertView);
        if (itemLayout.getChildCount() == 2) {
            return itemLayout.getChildAt(1);
        }
        return null;
    }

    public void setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
    }

    public void setImageVisible(int viewId, int visible) {
        ImageView imageView = getView(viewId);
        imageView.setVisibility(visible);
    }

    public void setImage(int viewId, int id) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(id);
    }
    public void setImage(int viewId, String url) {
        ImageView imageView = getView(viewId);
//        x.image().bind(imageView,url, ImageOptionsUtils.newslistOptions);
        ImageLoader.Builder builder = new ImageLoader.Builder();
        ImageLoader img = builder.url(url)
                .imgView(imageView).strategy(ImageLoaderUtil.LOAD_STRATEGY_NORMAL).build();
        imageLoaderUtil.loadImage(mConvertView.getContext(), img);
    }
//    public void setImage(int viewId, String url) {
//        ImageView imageView = getView(viewId);
//        x.image().bind(imageView,url, ImageOptionsUtils.newslistOptions);
//    }

    public void setText(int viewId, int textId) {
        TextView textView = getView(viewId);
        textView.setText(textId);
    }

    public void setTextColor(int viewId, int colorId) {
        TextView textView = getView(viewId);
        textView.setTextColor(colorId);
    }

    public void setOnClickListener(int viewId, View.OnClickListener clickListener) {
        View view = getView(viewId);
        view.setOnClickListener(clickListener);
    }

    public void setBgRes(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
    }

    public void setBgColor(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(colorId);
    }
}

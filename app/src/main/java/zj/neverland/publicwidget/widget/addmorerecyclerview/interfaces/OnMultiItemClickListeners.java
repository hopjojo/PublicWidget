package zj.neverland.publicwidget.widget.addmorerecyclerview.interfaces;


import zj.neverland.publicwidget.widget.addmorerecyclerview.ViewHolder;

/**
 * Time: 2017年4月25日17:55:02
 */
public interface OnMultiItemClickListeners<T> {
    void onItemClick(ViewHolder viewHolder, T data, int position, int viewType);
}

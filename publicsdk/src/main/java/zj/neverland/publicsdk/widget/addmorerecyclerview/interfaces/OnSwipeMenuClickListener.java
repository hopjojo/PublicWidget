package zj.neverland.publicsdk.widget.addmorerecyclerview.interfaces;


import cn.cefoc.widget.addmorerecyclerview.ViewHolder;

/**
 * Time: 2017年4月25日17:55:08
 */
public interface OnSwipeMenuClickListener<T> {
    void onSwipMenuClick(ViewHolder viewHolder, T data, int position);
}

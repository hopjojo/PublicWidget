package zj.neverland.publicsdk.widget.addmorerecyclerview.interfaces;


import cn.cefoc.widget.addmorerecyclerview.ViewHolder;

/**
 * Time: 2017年4月25日17:54:31
 */
public interface OnItemChildClickListener<T> {
    void onItemChildClick(ViewHolder viewHolder, T data, int position);
}

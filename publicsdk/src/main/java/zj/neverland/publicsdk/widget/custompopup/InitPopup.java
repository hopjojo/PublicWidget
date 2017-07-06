package zj.neverland.publicsdk.widget.custompopup;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhaiJian
 * @data: 2017/5/5 14:51
 * @version: V1.0
 * Class Note: <弹出菜单实现工具类>
 */
public class InitPopup {
/* ------------使用例子------------
    InitPopup popup = new InitPopup.Builder(MainActivity.this, InitPopup.Type.Bottom, new InitPopup.OnItemClick() {
        @Override
        public void ItemClick(ActionItem item, int position) {
            switch (position){
                case 0:
                    showToast(item.mTitle.toString());
                    break;
                case 1:
                    showToast(item.mTitle.toString()+"333");
                    break;
            }
        }
    })
            .addItem("sss")
            .addItem("aaa")
            .build();
            popup.ShowBottomPopup(MainActivity.this,v);
*/

    private TitlePopup titlePopup;//跟随菜单
    private BottomPopup bottomPopup;//底部菜单

    //菜单类型
    public enum Type{
        Free,Bottom
    }
    /**
     * 构造方法
     * @param build
     * @author ZhaiJian
     */
    public InitPopup(Builder build) {
        initPopWindow(build);
    }

    private void initPopWindow(final Builder build) {
        // 实例化标题栏弹窗
        switch (build.type){
            case Free:
                titlePopup = new TitlePopup(build.context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                titlePopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {
                    @Override
                    public void onItemClick(ActionItem item, int position) {
                        build.onItemClick.ItemClick(item, position);
                    }
                });
                // 给标题栏弹窗添加子类
                for (int i = 0; i < build.actionItems.size(); i++) {
                    titlePopup.addAction(build.actionItems.get(i));
                }
                break;
            case Bottom:
                bottomPopup = new BottomPopup(build.context, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                bottomPopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {
                    @Override
                    public void onItemClick(ActionItem item, int position) {
                        build.onItemClick.ItemClick(item, position);
                    }
                });
                // 给标题栏弹窗添加子类
                for (int i = 0; i < build.actionItems.size(); i++) {
                    bottomPopup.addAction(build.actionItems.get(i));
                }
                break;
        }

    }

    /**
     * 显示菜单
     * @param v 所在视图
     */
    public void ShowFreePopup(View v){
        titlePopup.show(v);
    }

    public void ShowBottomPopup(Activity activity,View v){
        bottomPopup.show(activity,v);
    }
    /**
     * 构造属性
     * @author ZhaiJian
     */
    public static class Builder {
        private List<ActionItem> actionItems = new ArrayList<>();
        private OnItemClick onItemClick;
        private Context context;
        private Type type;

        public Builder(Context context ,Type type ,OnItemClick onItemClick) {
            this.context = context.getApplicationContext();
            this.onItemClick = onItemClick;
            this.type = type;
        }

        public Builder addItem(int titleId, int drawableId) {
            actionItems.add(new ActionItem(context, titleId, drawableId));
            return this;
        }

        public Builder addItem(CharSequence title, int drawableId) {
            actionItems.add(new ActionItem(context, title, drawableId));
            return this;
        }

        public Builder addItem(CharSequence title) {
            actionItems.add(new ActionItem(context, title));
            return this;
        }

        public InitPopup build() {
            return new InitPopup(this);
        }
    }

    public interface OnItemClick {
        void ItemClick(ActionItem item, int position);
    }
}

package zj.neverland.publicsdk.widget.custompopup;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import cn.cefoc.R;

/**
 * @author ZhaiJian
 * @data: 2017/7/3 14:51
 * @version: V1.0
 * Class Note: <底部弹出菜单>
 */
public class BottomPopup extends PopupWindow {
    Context mContext;
    // 定义列表对象
    private ListView mListView;
    private Button btn_cancel;
    // 弹窗子类项选中时的监听
    private TitlePopup.OnItemOnClickListener mItemOnClickListener;
    // 定义弹窗子类项列表
    private ArrayList<ActionItem> mActionItems = new ArrayList<ActionItem>();
    // 判断是否需要添加或更新列表子类项
    private boolean mIsDirty;

    private View view;
    private WeakReference<Activity> ref;

    public void setRef(Activity activity) {
        this.ref = new WeakReference<>(activity);
    }

    public BottomPopup(Context context, int width, int height) {
        this.mContext = context;
        this.view = LayoutInflater.from(mContext).inflate(R.layout.bottom_popup, null);
        // 设置可以获得焦点
        setFocusable(true);
        // 设置弹窗内可点击
        setTouchable(true);
        // 设置弹窗外可点击
        setOutsideTouchable(true);

        // 设置弹窗的宽度和高度
        setWidth(width);
        setHeight(height);
        setAnimationStyle(R.style.AnimBottom);
//        setBackgroundDrawable(new BitmapDrawable());
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        // 设置弹出窗体的背景
//        this.setBackgroundDrawable(dw);
        // 设置弹窗的布局界面
        setContentView(view);
        initUI();


        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.title_list).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        backgroundAlpha(ref.get(),1f);
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    /**
     * 初始化弹窗列表
     */
    private void initUI() {
        mListView = (ListView) getContentView().findViewById(R.id.title_list);
//        mListView.setAnimation(getAnimationStyle());
//        setAnimationStyle(R.style.AnimBottom);
        btn_cancel = (Button) getContentView().findViewById(R.id.popbtn_cancel);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
                // 点击子类项后，弹窗消失
                dismiss();
                backgroundAlpha(ref.get(),1f);
                if (mItemOnClickListener != null)
                    mItemOnClickListener.onItemClick(mActionItems.get(index), index);
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                backgroundAlpha(ref.get(),1f);
            }
        });

    }

    /**
     * 设置背景
     *
     * @param activity
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

    /**
     * 显示弹窗列表界面
     */
    public void show(Activity activity, View view) {
        // 判断是否需要添加或更新列表子类项
        if (mIsDirty) {
            populateActions();
        }
        setRef(activity);
        backgroundAlpha(ref.get(),0.3f);
        showAtLocation(view.getRootView(), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }


    /**
     * 设置弹窗列表子项
     */
    private void populateActions() {
        mIsDirty = false;

        // 设置列表的适配器
        mListView.setAdapter(new BaseAdapter() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_item_pop, parent, false);
                }
                TextView textView = TitlePopup.ViewHolder.get(convertView, R.id.txt_title);
                textView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                textView.setTextColor(mContext.getResources().getColor(R.color.primary_color));
                textView.setTextSize(18);
                // 设置文本居中
                textView.setGravity(Gravity.CENTER);
                // // 设置文本域的范围
                textView.setPadding(0, 10, 0, 10);
                // 设置文本在一行内显示（不换行）
                textView.setSingleLine(true);

                ActionItem item = mActionItems.get(position);

                // 设置文本文字
                textView.setText(item.mTitle);
                if (item.mDrawable != null) {
                    // 设置文字与图标的间隔
                    textView.setCompoundDrawablePadding(10);
                    // 设置在文字的左边放一个图标
                    textView.setCompoundDrawablesWithIntrinsicBounds(item.mDrawable, null, null, null);
                }
                return convertView;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public Object getItem(int position) {
                return mActionItems.get(position);
            }

            @Override
            public int getCount() {
                return mActionItems.size();
            }
        });
    }

    /**
     * 添加子类项
     */
    public void addAction(ActionItem action) {
        if (action != null) {
            mActionItems.add(action);
            mIsDirty = true;
        }
    }

    /**
     * 清除子类项
     */
    public void cleanAction() {
        if (mActionItems.isEmpty()) {
            mActionItems.clear();
            mIsDirty = true;
        }
    }

    /**
     * 根据位置得到子类项
     */
    public ActionItem getAction(int position) {
        if (position < 0 || position > mActionItems.size())
            return null;
        return mActionItems.get(position);
    }

    /**
     * 设置监听事件
     */
    public void setItemOnClickListener(TitlePopup.OnItemOnClickListener onItemOnClickListener) {
        this.mItemOnClickListener = onItemOnClickListener;
    }

    /**
     * @author yangyu 功能描述：弹窗子类项按钮监听事件
     */
    public static interface OnItemOnClickListener {
        public void onItemClick(ActionItem item, int position);
    }


    public static class ViewHolder {
        @SuppressWarnings("unchecked")
        public static <T extends View> T get(View view, int id) {
            SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
            if (viewHolder == null) {
                viewHolder = new SparseArray<View>();
                view.setTag(viewHolder);
            }
            View childView = viewHolder.get(id);
            if (childView == null) {
                childView = view.findViewById(id);
                viewHolder.put(id, childView);
            }
            return (T) childView;
        }
    }
}

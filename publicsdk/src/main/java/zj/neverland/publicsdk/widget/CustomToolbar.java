package zj.neverland.publicsdk.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import zj.neverland.publicsdk.R;
import zj.neverland.publicsdk.utils.DensityUtils;


/**
 * @author ZhaiJian
 * @data: 2017/6/29 09:04
 * @version: V1.0
 * Class Note: <导航栏封装>
 */
public class CustomToolbar extends Toolbar {

    private TextView tv_left;
    private TextView tv_title;
    private TextView tv_right;

    private int leftBtnVisibility;//左边按钮可见状态
    private int rightBtnVisibility;//右边按钮可见状态
    private String leftBtnText;//左边文字
    private String rightBtnText;//右边文字
    private String titleText;//标题
    private int leftBtnColor;//左边文字颜色
    private int rightBtnColor;//右边文字颜色
    private int titleColor;//标题颜色
    private int leftBtnSize;//左边文字大小
    private int rightBtnSize;//右边文字大小
    private int titleSize;//标题文字大小
    private int rightBtnImg;

    /**
     * 构造方法
     *
     * @param context
     * @author ZhaiJian
     */
    public CustomToolbar(Context context) {
        super(context);
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        initAttributeSet(context, attrs);
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tv_left = (TextView) findViewById(R.id.toolbar_left);
        tv_title = (TextView) findViewById(R.id.toolbar_title);
        tv_right = (TextView) findViewById(R.id.toolbar_right);
//        initView();
    }

    /**
     * 初始化导航栏
     */
    public void initView() {
        tv_left.setVisibility(leftBtnVisibility);
        tv_left.setText(leftBtnText);
        tv_left.setTextColor(leftBtnColor);
        tv_left.setTextSize(leftBtnSize);

        tv_right.setVisibility(rightBtnVisibility);
        tv_right.setText(rightBtnText);
        tv_right.setTextColor(rightBtnColor);
        tv_right.setTextSize(rightBtnSize);

        tv_title.setText(titleText);
        tv_title.setTextSize(titleSize);
        tv_title.setTextColor(titleColor);

        if (rightBtnImg > 0)
            setRightIcon(rightBtnImg);
    }

    /**
     * 导航栏属性配置
     *
     * @param context
     * @param attrs   属性值
     * @author ZhaiJian
     */
    public void initAttributeSet(Context context, @Nullable AttributeSet attrs) {
        Resources resources = getResources();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar);
        leftBtnVisibility = ta.getInt(R.styleable.CustomToolbar_LeftBtnVisibility, 0);//左边按钮可见状态
        rightBtnVisibility = ta.getInt(R.styleable.CustomToolbar_RightBtnVisibility, 0);//右边按钮可见状态
        leftBtnText = ta.getString(R.styleable.CustomToolbar_LeftBtnText);//左边文字
        rightBtnText = ta.getString(R.styleable.CustomToolbar_RightBtnText);//右边文字
        titleText = ta.getString(R.styleable.CustomToolbar_titleText);//标题
        leftBtnColor = ta.getColor(R.styleable.CustomToolbar_LeftBtnColor, resources.getColor(R.color.white));//左边文字颜色
        rightBtnColor = ta.getColor(R.styleable.CustomToolbar_RightBtnColor, resources.getColor(R.color.white));//右边文字颜色
        titleColor = ta.getColor(R.styleable.CustomToolbar_titleColor, resources.getColor(R.color.white));//标题颜色
        leftBtnSize = DensityUtils.sp2px(getContext(), ta.getDimension(R.styleable.CustomToolbar_LeftBtnSize, 18));//左边文字大小
        rightBtnSize = DensityUtils.sp2px(getContext(), ta.getDimension(R.styleable.CustomToolbar_RightBtnSize, 18));//右边文字大小
        titleSize = DensityUtils.sp2px(getContext(), ta.getDimension(R.styleable.CustomToolbar_titleSize, 18));//标题文字大小
        rightBtnImg = ta.getResourceId(R.styleable.CustomToolbar_RightBtnImg, -1);
        ta.recycle();
    }


    /**
     * 设置toolbar的标题
     *
     * @param titleText 标题栏文字
     * @author ZhaiJian
     */
    public void setTitleText(CharSequence titleText) {
        if (tv_title != null) {
            if (TextUtils.isEmpty(titleText)) {
                tv_title.setText("");
                return;
            }
            tv_title.setText(titleText);
        }
    }

    /**
     * 设置标题栏颜色
     *
     * @param color 颜色id
     * @author ZhaiJian
     */
    public void setTitleColor(int color) {
        if (tv_title != null) {
            tv_title.setTextColor(color);
        }
    }
    /**
     * 设置左侧文字可显状态
     *
     * @param visibility 显示状态
     * @author ZhaiJian
     */
    public void setLeftVisibility(int visibility) {
        if (tv_left != null) {
            tv_left.setVisibility(visibility);
        }
    }
    /**
     * 设置左侧文字
     *
     * @param leftText 左侧文字
     * @author ZhaiJian
     */
    public void setLeftText(CharSequence leftText) {
        if (tv_left != null) {
            if (TextUtils.isEmpty(leftText)) {
                tv_left.setText("");
                return;
            }
            tv_left.setText(leftText);
        }
    }

    /**
     * 设置左侧文字颜色
     *
     * @param color 颜色值
     * @author ZhaiJian
     */
    public void setLeftTextColor(int color) {
        if (tv_left != null) {
            tv_left.setTextColor(color);
        }
    }

    /**
     * 设置左侧图标
     *
     * @param resId 图标资源
     * @author ZhaiJian
     */
    public void setLeftIcon(int resId) {
        Drawable leftDrawable = ContextCompat.getDrawable(getContext(), resId);
        leftDrawable.setBounds(0, 0, leftDrawable.getIntrinsicWidth(), leftDrawable.getIntrinsicHeight());
        tv_left.setCompoundDrawables(leftDrawable, null, null, null);
    }


    public void setLeftOnClickListener(OnClickListener onClickListener) {
        tv_left.setOnClickListener(onClickListener);
    }
    /**
     * 设置左侧文字可显状态
     *
     * @param visibility 显示状态
     * @author ZhaiJian
     */
    public void setRightVisibility(int visibility) {
        if (tv_left != null) {
            tv_right.setVisibility(visibility);
        }
    }
    /**
     * 设置右侧文字
     *
     * @param rightText 文字
     * @author ZhaiJian
     */
    public void setRightText(CharSequence rightText) {
        if (tv_right != null) {
            if (TextUtils.isEmpty(rightText)) {
                tv_right.setText("");
                return;
            }
            tv_right.setText(rightText);
        }
    }

    /**
     * 设置右侧文字颜色值
     *
     * @param color 颜色
     * @author ZhaiJian
     */
    public void setRightTextColor(int color) {
        if (tv_right != null) {
            tv_right.setTextColor(color);
        }
    }

    /**
     * 设置右侧图标
     *
     * @param resId 图标资源
     * @author ZhaiJian
     */
    public void setRightIcon(int resId) {
        Drawable rightDrawable = ContextCompat.getDrawable(getContext(), resId);
        rightDrawable.setBounds(0, 0, rightDrawable.getIntrinsicWidth(), rightDrawable.getIntrinsicHeight());
        tv_right.setCompoundDrawables(rightDrawable, null, null, null);
    }

    public void setRightOnClickListener(OnClickListener onClickListener) {
        tv_right.setOnClickListener(onClickListener);
    }

    /**
     * 设置左侧菜单为后退按钮
     * @param activity
     */
    public void setLeftBtnBack(final Activity activity){
        setLeftIcon(R.mipmap.backicon);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }
}

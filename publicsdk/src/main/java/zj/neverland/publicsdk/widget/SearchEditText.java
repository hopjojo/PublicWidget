package zj.neverland.publicsdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import cn.cefoc.R;


/**
 * Created by sjzhand on 2017/2/9.
 */

public class SearchEditText extends EditText implements View.OnFocusChangeListener,TextWatcher {
    int sellerType;
    private Drawable clerImg ;
    private Drawable voiImg ;
    boolean hasFoucs ;
    Context context;
    int themeType ;
    public SearchEditText(Context context) {
        super(context);
        this.context =context;
        init();
    }

    public SearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context =context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SellerEditView);
        sellerType = ta.getInteger(R.styleable.SellerEditView_sellerType, 0); //0主页 1搜索页
        themeType = ta.getInteger(R.styleable.SellerEditView_themeType, 0); //0主页 1搜索页
        init();
    }

    public SearchEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context =context;
        init();
    }
    private void init() {
        if(sellerType==1){
//            Drawable selImg = ResourcesCompat.getDrawable(getResources(), R.mipmap.nav_search_gray, null);
//            selImg.setBounds(0, 0, selImg.getIntrinsicWidth(), selImg.getIntrinsicHeight());
//            setCompoundDrawables(selImg,getCompoundDrawables()[1], getCompoundDrawables()[2], getCompoundDrawables()[3]);
            setClearIconVisible(false);
            // 设置焦点改变的监听   这个很重要
            setOnFocusChangeListener(this);
        }else{

        }
        setThemeType(themeType);
    }
    protected void setClearIconVisible(boolean iscler) {
        if(iscler&&clerImg==null){
            clerImg = ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_delete_gray, null);
            //设置图标的位置以及大小,getIntrinsicWidth()获取显示出来的大小而不是原图片的大小
            clerImg.setBounds(0, 0, clerImg.getIntrinsicWidth(), clerImg.getIntrinsicHeight());
        }
        if(!iscler&&voiImg==null){
            voiImg = ResourcesCompat.getDrawable(getResources(), R.mipmap.nav_search_gray, null);
            //设置图标的位置以及大小,getIntrinsicWidth()获取显示出来的大小而不是原图片的大小
            voiImg.setBounds(0, 0, voiImg.getIntrinsicWidth(), voiImg.getIntrinsicHeight());
        }
        Drawable right = iscler ? clerImg : voiImg;
        setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        &&
                        (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    if(sellerType==0&&iSearchEditCallBack!=null){
                        iSearchEditCallBack.voiceClick();
                    }
                    if(sellerType==1){
                        if(this.getText().toString().length()>0){
                            this.setText("");
                        }else{
                            if(iSearchEditCallBack!=null)
                                iSearchEditCallBack.voiceClick();
                        }
                    }
                }else if(sellerType==0){
                    if(iSearchEditCallBack!=null)
                        iSearchEditCallBack.searchClick();
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if (hasFoucs) {
            setClearIconVisible(s.length() > 0);
        }
    }



    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
    ISearchEditCallBack iSearchEditCallBack;
    public void setSearchEditCallBack(ISearchEditCallBack iSearchEditCallBack) {
        this.iSearchEditCallBack = iSearchEditCallBack;
    }



    public  interface  ISearchEditCallBack{
        public void voiceClick();
        public void searchClick();
    }

    /**
     * 设置主题
     * @param type
     */
    public void setThemeType(int type){
        Drawable selImg = null;
        Drawable voiImg = null;
        if(type==0){
            selImg = ResourcesCompat.getDrawable(getResources(), R.mipmap.nav_search, null);
            selImg.setBounds(0, 0, selImg.getIntrinsicWidth(), selImg.getIntrinsicHeight());
            voiImg = ResourcesCompat.getDrawable(getResources(), R.mipmap.nav_voice, null);
            voiImg.setBounds(0, 0, selImg.getIntrinsicWidth(), selImg.getIntrinsicHeight());
        }else{
            selImg = ResourcesCompat.getDrawable(getResources(), R.mipmap.nav_search_gray, null);
            selImg.setBounds(0, 0, selImg.getIntrinsicWidth(), selImg.getIntrinsicHeight());
            voiImg = ResourcesCompat.getDrawable(getResources(), R.mipmap.nav_voice_gray, null);
            voiImg.setBounds(0, 0, selImg.getIntrinsicWidth(), selImg.getIntrinsicHeight());

        }
        setCompoundDrawables(selImg,getCompoundDrawables()[1], voiImg, getCompoundDrawables()[3]);
    }



}

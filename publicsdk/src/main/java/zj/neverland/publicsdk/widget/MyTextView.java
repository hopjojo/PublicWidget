package zj.neverland.publicsdk.widget;

import android.content.Context;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.NumberFormat;

import cn.cefoc.R;
import cn.cefoc.utils.StringUtils;

/**
 * Created by sjzhand on 2017/5/2.
 */

public class MyTextView extends LinearLayout {
    public MyTextView(Context context) {
        super(context);
        init(context);
    }

    public MyTextView(Context context,int type) {
        super(context);
        init2(context);
        if (type == 1) {
            line.setVisibility(View.GONE);
        }
        if (type == 2) {
            line.setVisibility(View.VISIBLE);
        }
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    TextView tvTitle,tvVal;
    View line;
    LinearLayout llayout;
    private void init(Context context){
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_mytextview,this);
        tvTitle=(TextView)findViewById(R.id.tvTitle);
        tvVal=(TextView)findViewById(R.id.tvVal);
        line = findViewById(R.id.line);
        llayout = (LinearLayout) findViewById(R.id.llayout);
    }

    private void init2(Context context){
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_mytextview2,this);
        tvTitle=(TextView)findViewById(R.id.tvTitle);
        tvVal=(TextView)findViewById(R.id.tvVal);
        line = findViewById(R.id.line);
        llayout = (LinearLayout) findViewById(R.id.llayout);
    }

    public void setTvTitle(String title) {
        if(tvTitle!=null)
            tvTitle.setText(StringUtils.nullStrToEmpty(title));
    }

    public void setLink() {
        if(tvVal!=null){
            tvVal.setLinksClickable(true);
            tvVal.setAutoLinkMask(Linkify.WEB_URLS);
//            tvVal.setText(String.format("<a href=\\\"%s\\\">链接</a>",tvVal.getText().toString()));

        }

    }

    public void setMargin(int margin) {

    }

    public void setTvTitleVisible(int valVisible) {
        if(tvTitle!=null)
            tvTitle.setVisibility(valVisible);
    }
    public void setTvValVisible(int valVisible) {
        if(tvVal!=null)
            tvVal.setVisibility(valVisible);
    }

    public void setTvVal(String val) {
        if(tvVal!=null){
            tvVal.setText(StringUtils.getHtmlText(val) );
        }
    }
    public void setTvValT(){
        if(tvVal!=null){
            tvVal.getPaint().setFakeBoldText(true);
        }
    }

    public void setTvVal(String val,String type) {
        if(tvVal!=null){
            if (type!=null && type.equals("decimal"))
                tvVal.setText(NumberFormat.getCurrencyInstance().format(val));
            else
                tvVal.setText(StringUtils.getHtmlText(val) );
        }
    }





}

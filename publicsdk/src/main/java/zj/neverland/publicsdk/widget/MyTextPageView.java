package zj.neverland.publicsdk.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.cefoc.R;
import cn.cefoc.data.Model.Field;
import cn.cefoc.data.Model.SubTable;
import cn.cefoc.dbsx.AffairsInfoFragment;
import cn.cefoc.dbsx.SelectPageAcitvity;
import cn.cefoc.utils.StringUtils;

/**
 * Created by sjzhand on 2017/5/2.
 */

public class MyTextPageView extends LinearLayout {
    Field field;
    Context context; 
    Fragment fragment;
    Activity activity;
    String tag;
    SubTable subTable;
    public MyTextPageView(Context context,SubTable subTable) {
        super(context);
        this.context =context;
        this.subTable = subTable;
        init(context);
    }

    public MyTextPageView(Context context,String tag, Field field,   Fragment fragment) {
        super(context);
        this.field= field;
        this.context =context;
        this.fragment=fragment;
        this.tag = tag;
        init(context);
    }

    public MyTextPageView(Context context,String tag, Field field,   Activity activity) {
        super(context);
        this.field= field;
        this.context =context;
        this.activity=activity;
        this.tag = tag;
        init(context);
    }

    public MyTextPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context =context;
        init(context);
    }

    public MyTextPageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context =context;
        init(context);
    }
    TextView tvTitle,tvVal;
    private void init(final Context context){
        if (subTable != null) {
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.layout_mytextview_page,this);
            tvTitle=(TextView)findViewById(R.id.tvTitle);
            tvVal=(TextView)findViewById(R.id.tvVal);
            tvTitle.setText(subTable.getSubTableText());
            tvVal.setText("");

        }else{
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.layout_mytextview_page,this);
            tvTitle=(TextView)findViewById(R.id.tvTitle);
            tvVal=(TextView)findViewById(R.id.tvVal);
            setTag(tag);
            if (field != null) {
                tvTitle.setText(field.getFieldText());
                tvVal.setText(field.getFieldValue());

                findViewById(R.id.linearLayout).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //打开新页面
                        Intent intent = new Intent(context, SelectPageAcitvity.class);
                        intent.putExtra("tag", tag);
                        intent.putExtra("field",field);
                        intent.putExtra("val", tvVal.getText().toString());
                        if (fragment!=null)
                            fragment.startActivityForResult(intent, AffairsInfoFragment.SELECT_PAGE);
                        if (activity!=null)
                            activity.startActivityForResult(intent, AffairsInfoFragment.SELECT_PAGE);
                    }
                });
            }
        }

    }

    public boolean checkVal(){
        if (StringUtils.isBlank(tvVal.getText().toString())) {
            Toast.makeText(context, field.getFieldText()+"不能为空！", Toast.LENGTH_SHORT).show();
            return  false;
        }
        return  true;
    }

    public Field getField() {
        field.setFieldValue(tvVal.getText().toString());
        return field;
    }

    public void setTvTitle(String title) {
        if(tvTitle!=null)
            tvTitle.setText(StringUtils.nullStrToEmpty(title));
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

    public void setVal(String val) {
        if(tvVal!=null)
            tvVal.setText(StringUtils.nullStrToEmpty(val));
        if (field != null) {
            field.setFieldValue(StringUtils.nullStrToEmpty(val));
        }
    }
    String idName ,idVal,isUpdate;

    public String getIdName() {
        return idName;
    }

    public String getIdVal() {
        return idVal;
    }


    public String getisUpdate() {
        return isUpdate;
    }

    public void setFieldIdNameAndIdValue(String idName, String idVal,String isUpdate) {
        this.idName = idName;
        this.idVal = idVal;
        this.isUpdate = isUpdate;
    }




}

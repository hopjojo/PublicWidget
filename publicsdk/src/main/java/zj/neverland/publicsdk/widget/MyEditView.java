package zj.neverland.publicsdk.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import cn.cefoc.R;
import cn.cefoc.data.Model.Field;
import cn.cefoc.utils.DateUtil;
import cn.cefoc.utils.StringUtils;
import cn.cefoc.widget.easypicker.PickerCallBack;
import cn.cefoc.widget.easypicker.PickerViewManager;

/**
 * Created by sjzhand on 2017/5/2.
 */

public class MyEditView extends LinearLayout {
    Field field ;
    Context context;
    String myDate = "";
    public MyEditView(Context context, Field field) {
        super(context);
        this.field = field;
        this.context = context;
        init(context);
    }

    public MyEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    TextView tvTitle;
    EditText etVal;
    private void init(final Context context){
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_myeditview_decimal,this);
        tvTitle=(TextView)findViewById(R.id.tvTitle);
        etVal=(EditText)findViewById(R.id.etVal);
        if (field.getHtmlFieldType().toLowerCase().equals("decimal")){
            etVal.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
            etVal.setFilters(new InputFilter[] {new DecimalInputFilter(9, 2)});
            etVal.setMaxLines(1);
            etVal.setSelectAllOnFocus(true);
        }
        if (field.getHtmlFieldType().toLowerCase().equals("text")){
            etVal.setInputType(InputType.TYPE_CLASS_TEXT);
            etVal.setMaxLines(1);
        }
        if (field.getHtmlFieldType().toLowerCase().equals("textarea")){
            etVal.setGravity(Gravity.TOP|Gravity.LEFT);
            etVal.setGravity(Gravity.CENTER_VERTICAL);
            etVal.setMinLines(3);
        }
        if (field.getHtmlFieldType().toLowerCase().equals("date")||field.getHtmlFieldType().toLowerCase().equals("datetime")){
            etVal.setTextColor(ContextCompat.getColor(context,R.color.sub_fontcolor));
            etVal.setEnabled(false);
            initDate();
            String[] s = myDate.split(" ");
            ImageView ivDate = (ImageView) findViewById(R.id.ivDate);
            ivDate.setVisibility(View.VISIBLE);
            if (s.length == 1) {
                ivDate.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        new MyDatePickerDialog(context, 0, new MyDatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear,
//                                                  int startDayOfMonth) {
//                                iYear = startYear;
//                                iMonth = startMonthOfYear;
//                                iDay = startDayOfMonth;
//                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                                try {
//                                    Date date = formatter.parse(iYear + "-" + (iMonth + 1) + "-" + iDay);
//                                    myDate = formatter.format(date);
//                                    etVal.setText(myDate);
//                                } catch (ParseException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }, iYear, iMonth, iDay,true).show();
                        PickerViewManager pickertime = new PickerViewManager(context);
                        pickertime.CustomTimePicker(TimePickerView.Type.YEAR_MONTH_DAY, etVal.getText().toString(), new PickerCallBack.TimePickerCallBack() {
                            @Override
                            public void callback(Date date, View v) {
                                myDate = DateUtil.dateToString(date,"yyyy-MM-dd");
                                etVal.setText(myDate);
                            }
                        }).show();
                    }
                });
            } else {
                ivDate.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new MyDateTimePickerDialog(context,myDateTime, new MyDateTimePickerDialog.MyOnDateSetListener() {
                            @Override
                            public void onDateSet(Date date) {
                                myDateTime = date;
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(myDateTime);
                                iYear =  cal.get(Calendar.YEAR);
                                iMonth =  cal.get(Calendar.MONTH);
                                iDay =  cal.get(Calendar.DAY_OF_MONTH);
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
                                myDate = formatter.format(date);
                                etVal.setText(myDate);
                            }
                        }).show();
                    }
                });
            }
        }
        tvTitle.setText(StringUtils.nullStrToEmpty(field.getFieldText()));
        etVal.setText(StringUtils.nullStrToEmpty(field.getFieldValue()));
    }

    int iYear,iMonth,iDay;
    int iH,iM,iS;
    Date  myDateTime ;
    private void initDate(){
        myDate = field.getFieldValue();
        //初始化时间选择器
        if (!StringUtils.isBlank(myDate)) {
            String[] s = myDate.split(" ");
            if (s.length > 0) {
                String[] d = s[0].split("-");
                if (d.length>2){
                    if(isNumeric(d[0]))
                        iYear = Integer.parseInt(d[0]);
                    if(isNumeric(d[1]))
                        iMonth = Integer.parseInt(d[1])-1;
                    if(isNumeric(d[2]))
                        iDay = Integer.parseInt(d[2]);
                }
            }
            if (s.length > 1) {
                String[] d = s[0].split(":");
                if (d.length>2){
                    if(isNumeric(d[0]))
                        iH = Integer.parseInt(d[0]);
                    if(isNumeric(d[1]))
                        iM = Integer.parseInt(d[1]);
                    if(isNumeric(d[2]))
                        iS = Integer.parseInt(d[2]);
                }
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                     myDateTime = formatter.parse(field.getFieldValue());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            etVal.setText(myDate);
        }
    }

    public boolean checkVal(){
        if (StringUtils.isBlank(etVal.getText().toString())) {
            Toast.makeText(context, field.getFieldText()+"不能为空！", Toast.LENGTH_SHORT).show();
            return  false;
        }
        return  true;
    }

    public Field getField() {
        field.setFieldValue(etVal.getText().toString());
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
    public void setValVisible(int valVisible) {
        if(etVal!=null)
            etVal.setVisibility(valVisible);
    }

    public void setVal(String val) {
        if(etVal!=null){
            etVal.setText(StringUtils.nullStrToEmpty(val));
        }
        if (field != null) {
            field.setFieldValue(StringUtils.nullStrToEmpty(val));
        }
    }


    public   boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

}

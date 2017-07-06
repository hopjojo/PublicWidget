package zj.neverland.publicsdk.widget.easypicker;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import zj.neverland.publicsdk.utils.DateUtil;
import zj.neverland.publicsdk.utils.KeyBoardUtils;
import zj.neverland.publicsdk.widget.easypicker.bean.JsonBean;


/**
 * Created by cefoc on 2017/5/3.
 * Class Note:
 */

public class PickerViewManager {
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private Context context;

    public PickerViewManager(Context context) {
        this.context = context;
    }
//时间选择
    public TimePickerView CustomTimePicker(TimePickerView.Type type, String selectdTime, final PickerCallBack.TimePickerCallBack timePickerCallBack) {
        Calendar selectedDate = Calendar.getInstance();
        if (selectdTime != null && !selectdTime.isEmpty()) {
            String formatType;
            switch (type) {
                case ALL:
                    formatType = "yyyy-MM-dd HH:mm:ss";
                    break;
                case YEAR_MONTH_DAY:
                    formatType = "yyyy-MM-dd";
                    break;
                case HOURS_MINS:
                    formatType = "HH:mm";
                    break;
                case MONTH_DAY_HOUR_MIN:
                    formatType = "MM-dd HH:mm";
                    break;
                case YEAR_MONTH:
                    formatType = "yyyy-MM";
                    break;
                case YEAR_MONTH_DAY_HOUR_MIN:
                    formatType = "yyyy-MM-dd HH:mm";
                    break;
                default:
                    formatType = "yyyy-MM-dd HH:mm:ss";
                    break;
            }
            selectedDate.setTime(DateUtil.stringToDate(selectdTime, formatType));
        }
        Calendar startDate = Calendar.getInstance();
        startDate.set(2000, 0, 1);
//        Calendar endDate = Calendar.getInstance();
//        endDate.set(2021, 12, 30);
        //关闭软键盘
        KeyBoardUtils keyBoard = new KeyBoardUtils((Activity)context);
        keyBoard.closeKeybord();
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                timePickerCallBack.callback(date, v);
            }
        })
                .setType(type)
                .setLabel("年", "月", "日", "时", "分", "秒") //设置空字符串以隐藏单位提示   hide label
                .setTitleText("请选择日期")
                .setDividerColor(Color.DKGRAY)
                .setContentSize(20)
                .setDate(selectedDate)
                .setRangDate(startDate, null)
                .isCenterLabel(false)
                .build();
        return pvTime;
    }

//城市地点选择
    public void CityOptionsPicker(final PickerCallBack.CityOptionsPickerCallBack cityoptions) {
        initJsonData();
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                cityoptions.callback(options1Items.get(options1).getPickerViewText(),options2Items.get(options1).get(options2),options3Items.get(options1).get(options2).get(options3),v);
            }
        })

                .setTitleText("请选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(false)// default is true
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }
    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open("province.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String JsonData = stringBuilder.toString();//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i=0;i<jsonBean.size();i++){//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c=0; c<jsonBean.get(i).getCityList().size(); c++){//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        ||jsonBean.get(i).getCityList().get(c).getArea().size()==0) {
                    City_AreaList.add("");
                }else {

                    for (int d=0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }
    public ArrayList<JsonBean> parseData(String result) {
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            org.json.JSONArray data = new org.json.JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }
}

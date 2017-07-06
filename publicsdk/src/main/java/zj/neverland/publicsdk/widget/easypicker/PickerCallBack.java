package zj.neverland.publicsdk.widget.easypicker;

import android.view.View;

import java.util.Date;

/**
 * Created by cefoc on 2017/5/3.
 * Class Note:
 */

public class PickerCallBack {
    public interface TimePickerCallBack{
        void callback(Date date, View v);
    }
    public interface CityOptionsPickerCallBack{
        void callback(String op1, String op2, String op3, View v);
    }
}

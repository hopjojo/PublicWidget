package zj.neverland.publicsdk.utils;

/**
 * Created by sjzhand on 2017/2/23.
 * 打开或关闭软键盘
 */

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class KeyBoardUtils {
    private Context mContext;
    private Activity activity;
    private EditText editText;
    public KeyBoardUtils(EditText mEditText, Context mContext) {
        this.mContext = mContext;
        this.editText = mEditText;
    }

    public KeyBoardUtils(Activity activity) {
        this.activity =activity;
    }

    /**
     * 打卡软键盘
     */
    public void openKeybord() {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     */
    public void closeEditKeybord() {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public void closeKeybord() {
        ((InputMethodManager) activity.getApplicationContext().getSystemService(INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(activity.getWindow().getDecorView()
                                .getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
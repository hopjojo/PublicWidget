package zj.neverland.publicsdk.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import zj.neverland.publicsdk.R;


/**
 * Created by sjzhand on 2017/2/20.
 */

public class MyProgressDialog{
    private Context context = null;
    private TextView tv_load_dialog;
    public MyProgressDialog(Context context) {
        this.context = context;
    }

    public static MyProgressDialog getInstance(Context context){
        return  new MyProgressDialog(context);
    }

    public  Dialog init(boolean cancelable,String msg){
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.load_dialog, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        tv_load_dialog = (TextView) v.findViewById(R.id.tv_load_dialog);
        setMessage(msg);
        Dialog loadingDialog = new Dialog(context, R.style.MyDialogMes);// 创建自定义样式dialog
        loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        loadingDialog.setCanceledOnTouchOutside(cancelable);
        loadingDialog.setCancelable(cancelable);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT ,
                LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
        return loadingDialog;
    }

    public void setMessage(String msg){
        if(!TextUtils.isEmpty(msg)){
            tv_load_dialog.setText(msg);
            tv_load_dialog.setVisibility(View.VISIBLE);
        }else{
            tv_load_dialog.setText("");
            tv_load_dialog.setVisibility(View.GONE);
        }
    }
//    View view;
//    TextView tv_load_dialog;
//    private Context context = null;
//    private static MyProgressDialog customProgressDialog = null;
//
//
//    public MyProgressDialog(Context context) {
//        super(context);
//        this.context = context;
//    }
//
//    public MyProgressDialog(Context context, int theme) {
//        super(context, theme);
//    }
//    public static MyProgressDialog createDialog(Context context){
//        customProgressDialog = new MyProgressDialog(context,R.style.MyDialogMes);
//        customProgressDialog.setContentView(R.layout.load_dialog);
//        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
//
//        return customProgressDialog;
//    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(view);
////        WindowManager.LayoutParams params = getWindow().getAttributes();
////        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
////        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
////        getWindow().setAttributes(params);
//    }
//
//
//
//    private void init(Context context) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        view = inflater.inflate(R.layout.load_dialog, null);
//        tv_load_dialog = (TextView) view.findViewById(R.id.tv_load_dialog);
//    }
//
//    public void setMessage(String message) {
//        if (message != null && message.length() > 0) {
//            tv_load_dialog.setText(message);
//            tv_load_dialog.setVisibility(View.VISIBLE);
//        } else {
//            tv_load_dialog.setText("");
//            tv_load_dialog.setVisibility(View.GONE);
//        }
//    }
//
//    @Override
//    public void show() {
//        super.show();
//    }

}

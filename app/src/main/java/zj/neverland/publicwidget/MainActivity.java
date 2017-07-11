package zj.neverland.publicwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zj.neverland.publicsdk.update.Updater;
import zj.neverland.publicsdk.update.UpdaterConfig;

public class MainActivity extends AppCompatActivity {

    private String UPLOADE_URL = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * 检测升级
     */
    public void updateVer() {

        String url = UPLOADE_URL+"/CEFOCAPP.apk";
        UpdaterConfig config = new UpdaterConfig.Builder(this)
                .setTitle(getResources().getString(R.string.app_name))
                .setDescription(getString(R.string.system_download_description))
                .setFileUrl(url)
                .setCanMediaScanner(true)
                .build();
        Updater.get().showLog(true).download(config);
    }
}

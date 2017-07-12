package zj.neverland.publicsdk.update;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;


public class ApkInstallReceiver extends BroadcastReceiver {
    private boolean flag=false;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            long downloadApkId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            long localDownloadId = UpdaterUtils.getLocalDownloadId(context);
            if (downloadApkId == localDownloadId) {
                Logger.get().d("download complete. downloadId is " + downloadApkId);
                flag =true;
                installApk(context, downloadApkId);
            }
        }else if(DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(intent.getAction())){
//            long[] ids = intent.getLongArrayExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS);
//            Logger.get().d(String.valueOf(ids[0]));
            //点击通知栏取消下载
//            DownloadManager manager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
//            manager.remove(ids);
        }
    }

    private static void installApk(Context context, long downloadApkId) {
//        Intent install = new Intent(Intent.ACTION_VIEW);
//        DownloadThread dManager = (DownloadThread) context.getSystemService(Context.DOWNLOAD_SERVICE);
        FileDownloadManager fdm = FileDownloadManager.get();
        String path = fdm.getDownloadPath(context, downloadApkId);
        Uri downloadFileUri = Uri.parse(path);//downloadFileUri = dManager.getUriForDownloadedFile(downloadApkId);
        if (downloadFileUri != null) {
            UpdaterUtils.startInstall(context, downloadFileUri);
//            Logger.get().d("file location " + downloadFileUri.toString());
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                File apkFile = new File(path);
//                install.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                Uri contentUri = FileProvider.getUriForFile(context,
//                        BuildConfig.APPLICATION_ID + ".fileProvider", apkFile);
//                install.setDataAndType(contentUri, "application/vnd.android.package-archive");
//            } else {
//                install.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
//                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            }
//            context.startActivity(install);
        } else {
            Logger.get().d("download failed");
        }
    }


}

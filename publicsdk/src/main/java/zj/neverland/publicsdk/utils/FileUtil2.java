package zj.neverland.publicsdk.utils;

import android.graphics.BitmapFactory;
import android.media.ExifInterface;

import com.lzy.imagepicker.bean.ImageItem;
import com.nanchen.compresshelper.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by sjzhand on 2017/5/1.
 */

public class FileUtil2 {
    /**
     *  根据路径删除指定的目录或文件，无论存在与否
     *@param file  要删除的目录或文件
     *@return 删除成功返回 true，否则返回 false。
     */
    public static void deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        }
    }

    public static String getFileName(String path) {
        File file = FileUtil.getFileByPath(path);
        if (file != null) {
           return file.getName();
        }
        return  "";
    }
    public static String getFileMimeType(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        return options.outMimeType;
    }

    public static ImageItem setFile(ImageItem item ){
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(item.path);
            item.width = Integer.parseInt(exif.getAttribute(ExifInterface.TAG_IMAGE_WIDTH));
            item.height = Integer.parseInt(exif.getAttribute(ExifInterface.TAG_IMAGE_LENGTH));
            String tag_datetime = exif.getAttribute(ExifInterface.TAG_DATETIME);
//            String bb =exif.getAttribute(ExifInterface.TAG_GPS_TIMESTAMP);
//            long a = DateUtils2.dataOne2(tag_datetime);
//            String cc = "";
          //  item.addTime = DateUtils2.dataOne2(tag_datetime);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            item.size = getFileSize(new File(item.path));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  item;
    }

    /**
     * 获取指定文件大小
     * @return
     * @throws Exception 　　
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
        }
        return size;
    }

}

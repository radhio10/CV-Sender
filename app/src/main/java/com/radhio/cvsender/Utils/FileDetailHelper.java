package com.radhio.cvsender.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;

import java.io.File;

/**
 * Created by Azmia Hoque Radhio on 12/11/2020.
 */
public class FileDetailHelper {
    public static FileDetail getFileDetailFromUri(final Context context, final Uri uri) {
        FileDetail fileDetail = null;
        if (uri != null) {
            fileDetail = new FileDetail();
            // File Scheme.
            if (ContentResolver.SCHEME_FILE.equals(uri.getScheme())) {
                File file = new File(uri.getPath());
                fileDetail.fileName = file.getName();
                fileDetail.fileSize = file.length();
            }
            // Content Scheme.
            else if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme())) {
                Cursor returnCursor =
                        context.getContentResolver().query(uri, null, null, null, null);
                if (returnCursor != null && returnCursor.moveToFirst()) {
                    int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
                    fileDetail.fileName = returnCursor.getString(nameIndex);
                    fileDetail.fileSize = returnCursor.getLong(sizeIndex);
                    returnCursor.close();
                }
            }
        }
        return fileDetail;
    }


    public static class FileDetail {
        public String fileName;
        public long fileSize;
    }
}

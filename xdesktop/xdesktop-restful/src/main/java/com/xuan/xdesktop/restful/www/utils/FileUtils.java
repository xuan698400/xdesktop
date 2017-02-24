package com.xuan.xdesktop.restful.www.utils;

/**
 * Created by xuan on 17/2/22.
 */
public class FileUtils {
    /**
     * 文件大小单位：1KB。
     */
    public static final long ONE_KB = 1024;
    /**
     * 文件大小单位：1MB。
     */
    public static final long ONE_MB = ONE_KB * ONE_KB;
    /**
     * 文件大小单位：1GB。
     */
    public static final long ONE_GB = ONE_KB * ONE_MB;

    /**
     * 将以 byte 为单位的文件大小转换为一个可读性更好的文件大小，最终结果精确到一位小数。
     *
     * @param size 以 byte 为单位的文件大小
     * @return 更具可读性的文件大小（包括单位：GB、MB、KB、B），例如：102 B、1.5 KB、23.8 MB、34.2 GB
     */
    public static String byteCountToDisplaySize(long size) {
        String displaySize;
        if (size / ONE_GB > 0) {
            displaySize = MathUtils.div(size * 1.0, ONE_GB, 1) + " GB";
        } else if (size / ONE_MB > 0) {
            displaySize = MathUtils.div(size * 1.0, ONE_MB, 1) + " MB";
        } else if (size / ONE_KB > 0) {
            displaySize = MathUtils.div(size * 1.0, ONE_KB, 1) + " KB";
        } else {
            displaySize = String.valueOf(size) + " B";
        }
        return displaySize;
    }

    public static String getExtension(String filename) {
        if (filename == null) {
            return null;
        } else {
            int index = indexOfExtension(filename);
            return index == -1 ? "" : filename.substring(index + 1);
        }
    }

    public static int indexOfExtension(String filename) {
        if (filename == null) {
            return -1;
        } else {
            int extensionPos = filename.lastIndexOf(46);
            int lastSeparator = indexOfLastSeparator(filename);
            return lastSeparator > extensionPos ? -1 : extensionPos;
        }
    }

    public static int indexOfLastSeparator(String filename) {
        if (filename == null) {
            return -1;
        } else {
            int lastUnixPos = filename.lastIndexOf(47);
            int lastWindowsPos = filename.lastIndexOf(92);
            return Math.max(lastUnixPos, lastWindowsPos);
        }
    }
}

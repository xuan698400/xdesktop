package com.xuan.xdesktop.restful.www.entity;

import com.xuan.xdesktop.restful.www.utils.DateUtils;
import com.xuan.xdesktop.restful.www.utils.FileUtils;

import java.io.File;

/**
 * Created by xuan on 17/2/21.
 */
public class FileInfo {
    private String  name;
    private String  fullpath;
    private boolean dir;
    private String  ext;
    private String  modifyTime;
    private long    size;
    private String  displaySize;

    public FileInfo(File file) {
        name = file.getName();
        fullpath = file.getAbsolutePath();
        dir = file.isDirectory();
        ext = FileUtils.getExtension(file.getName());
        modifyTime = DateUtils.format(file.lastModified(), "yyyy/MM/dd HH:mm:ss");
        size = file.length();
        displaySize = FileUtils.byteCountToDisplaySize(size);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullpath() {
        return fullpath;
    }

    public void setFullpath(String fullpath) {
        this.fullpath = fullpath;
    }

    public boolean isDir() {
        return dir;
    }

    public void setDir(boolean dir) {
        this.dir = dir;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
    }
}

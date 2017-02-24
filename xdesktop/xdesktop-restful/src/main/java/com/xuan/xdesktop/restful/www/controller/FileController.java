package com.xuan.xdesktop.restful.www.controller;

import com.xuan.xdesktop.restful.www.constants.CodeMessage;
import com.xuan.xdesktop.restful.www.entity.FileInfo;
import com.xuan.xdesktop.restful.www.entity.Result;
import com.xuan.xdesktop.restful.www.enums.Code;
import com.xuan.xdesktop.restful.www.utils.Validators;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xuan on 17/2/21.
 */
@RestController
@RequestMapping("/api/file")
public class FileController extends BaseController {

    /**
     * 获取指定目录下的文件列表
     *
     * @param path
     * @return
     */
    @RequestMapping("filelist.do")
    public Result filelist(@RequestParam(defaultValue = "/") String path) {
        File file = new File(path);
        if (!file.isDirectory()) {
            return new Result(Code.ERROR, "非文件夹");
        } else {
            List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
            File[] tempList = file.listFiles();
            Arrays.sort(tempList, new CompratorByLastModified());
            for (File f : tempList) {
                FileInfo fileInfo = new FileInfo(f);
                fileInfoList.add(fileInfo);
            }
            return new Result(Code.SUCCESS, Result.createList(fileInfoList));
        }
    }

    /**
     * 创建文件夹
     *
     * @param parentPath
     * @param dirName
     * @return
     */
    @RequestMapping("doMkdir.do")
    public Result doMkdir(String parentPath, String dirName) {
        validateParam(parentPath, dirName);
        File dir = new File(parentPath + File.separator + dirName);
        if (dir.exists()) {
            return new Result(Code.ERROR, CodeMessage.DIR_EXSITS);
        }
        dir.mkdirs();
        return new Result(Code.SUCCESS);
    }

    /**
     * 删除文件夹
     *
     * @param pathsStr
     * @return
     */
    @RequestMapping("doRm.do")
    public Result doRm(String pathsStr) {
        if (Validators.isEmpty(pathsStr)) {
            new Result(Code.ERROR, CodeMessage.DIR_NOT_CHECKED);
        }
        //
        String[] paths = StringUtils.split(pathsStr, ',');
        for (String path : paths) {
            FileUtils.deleteQuietly(new File(path));
        }
        return new Result(Code.SUCCESS);
    }

    /**
     * 重命名文件
     *
     * @param parentPath
     * @param oldname
     * @param newname
     * @return
     */
    @RequestMapping("doRename.do")
    public Result doRename(String parentPath, String oldname, String newname) {
        validateParam(parentPath, oldname, newname);
        //判断重新命名的文件是否冲突存在
        String newFullpath = parentPath + File.separator + newname;
        File newFile = new File(newFullpath);
        if (newFile.exists()) {
            return new Result(Code.ERROR, CodeMessage.FILE_EXSITS);
        }
        //
        new File(parentPath + File.separator + oldname).renameTo(newFile);
        return new Result(Code.SUCCESS);
    }

    /**
     * 复制黏贴
     *
     * @param fromDirPath
     * @param toDirPath
     * @param name
     * @return
     */
    @RequestMapping("doCopy.do")
    public Result doCopy(String fromDirPath, String toDirPath, String name) {
        validateParam(fromDirPath, toDirPath, name);
        //判断重新命名的文件是否冲突存在
        File newFile = new File(toDirPath, name);
        if (newFile.exists()) {
            return new Result(Code.ERROR, CodeMessage.FILE_EXSITS);
        }
        //
        try {
            File fromFile = new File(fromDirPath, name);
            if (fromFile.isDirectory()) {
                FileUtils.copyDirectoryToDirectory(fromFile, new File(toDirPath));
            } else {
                FileUtils.copyFileToDirectory(fromFile, new File(toDirPath));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Code.ERROR, CodeMessage.FILE_IO_EXCEPTION);
        }
        return new Result(Code.SUCCESS);
    }

    /**
     * 剪切黏贴
     *
     * @param fromDirPath
     * @param toDirPath
     * @param name
     * @return
     */
    @RequestMapping("doMove.do")
    public Result doMove(String fromDirPath, String toDirPath, String name) {
        validateParam(fromDirPath, toDirPath, name);
        //判断重新命名的文件是否冲突存在
        File newFile = new File(toDirPath, name);
        if (newFile.exists()) {
            return new Result(Code.ERROR, CodeMessage.FILE_EXSITS);
        }
        //
        try {
            File fromFile = new File(fromDirPath, name);
            if (fromFile.isDirectory()) {
                FileUtils.moveDirectoryToDirectory(fromFile, new File(toDirPath), true);
            } else {
                FileUtils.moveFileToDirectory(fromFile, new File(toDirPath), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Code.ERROR, CodeMessage.FILE_IO_EXCEPTION);
        }
        return new Result(Code.SUCCESS);
    }

    private static class CompratorByLastModified implements Comparator<File> {
        public int compare(File f1, File f2) {
            if (f1.isDirectory() && !f2.isDirectory()) {
                return -1;
            }
            if (!f1.isDirectory() && f2.isDirectory()) {
                return 1;
            }
            if (f1.lastModified() > f2.lastModified()) {
                return -1;
            } else if (f1.lastModified() < f2.lastModified()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}

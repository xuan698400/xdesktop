UT = {}

UT.config = {}
UT.config.url = 'http://localhost:8080';

UT.http = {}
UT.http.post = function(subUrl, params, success, fail){
    $.post(UT.config.url + subUrl, params, function(r){
        console.log(r);
        if(r.code == 1){
            if(success){
                success(r.result);
            }else{
                $.zxxbox.remind2('操作成功');
            }
        }else{
            if(fail){
                success(r.message);
            }else{
                $.zxxbox.remind2(r.message);
            }
        }
    });
}
UT.http.filelist = function(path, success, fail){
    var params = {
        path : path
    };
    UT.http.post('/api/file/filelist.do', params, success, fail);
}
UT.http.doMkdir = function(parentPath, dirName, success, fail){
    var params = {
        parentPath : parentPath,
        dirName : dirName
    };
    UT.http.post('/api/file/doMkdir.do', params, success, fail);
}
UT.http.doRm = function(pathsStr, success, fail){
    var params = {
        pathsStr : pathsStr
    };
    UT.http.post('/api/file/doRm.do', params, success, fail);
}
UT.http.doRename = function(parentPath, oldname, newname, success, fail){
    var params = {
        parentPath : parentPath,
        oldname : oldname,
        newname : newname
    };
    UT.http.post('/api/file/doRename.do', params, success, fail);
}
UT.http.doCopy = function(fromDirPath, toDirPath, name, success, fail){
    var params = {
        fromDirPath : fromDirPath,
        toDirPath : toDirPath,
        name : name
    };
    UT.http.post('/api/file/doCopy.do', params, success, fail);
}
UT.http.doMove = function(fromDirPath, toDirPath, name, success, fail){
    var params = {
        fromDirPath : fromDirPath,
        toDirPath : toDirPath,
        name : name
    };
    UT.http.post('/api/file/doMove.do', params, success, fail);
}

//
UT.file = {};
UT.file.extMap = {};
UT.file.extMap['jpg'] = {text:'JPG图片', icon:'images/ic_file_img.png'};
UT.file.extMap['jpeg'] = {text:'JPG图片', icon:'images/ic_file_img.png'};
UT.file.extMap['png'] = {text:'PNG图片', icon:'images/ic_file_img.png'};
UT.file.extMap['bmp'] = {text:'BMP图片', icon:'images/ic_file_img.png'};
UT.file.extMap['gif'] = {text:'GIF图片', icon:'images/ic_file_img.png'};
UT.file.extMap['svg'] = {text:'SVG图片', icon:'images/ic_file_img.png'};
UT.file.extMap['psd'] = {text:'PSD文件', icon:'images/ic_file_img.png'};

UT.file.extMap['wav'] = {text:'WAV语音', icon:'images/ic_file_voice.png'};
UT.file.extMap['mp3'] = {text:'MP3语音', icon:'images/ic_file_voice.png'};
UT.file.extMap['aac'] = {text:'AAC语音', icon:'images/ic_file_voice.png'};
UT.file.extMap['amr'] = {text:'AMR语音', icon:'images/ic_file_voice.png'};
UT.file.extMap['cd'] = {text:'CD语音', icon:'images/ic_file_voice.png'};

UT.file.extMap['avi'] = {text:'AVI视频', icon:'images/ic_file_video.png'};
UT.file.extMap['rmvb'] = {text:'EMVB视频', icon:'images/ic_file_video.png'};
UT.file.extMap['wmv'] = {text:'WMV视频', icon:'images/ic_file_video.png'};
UT.file.extMap['mp4'] = {text:'MP4视频', icon:'images/ic_file_video.png'};
UT.file.extMap['flv'] = {text:'FLV视频', icon:'images/ic_file_video.png'};
UT.file.extMap['dvd'] = {text:'DVD视频', icon:'images/ic_file_video.png'};
UT.file.extMap['3gp'] = {text:'3GP视频', icon:'images/ic_file_video.png'};

UT.file.extMap['zip'] = {text:'ZIP压缩文件', icon:'images/ic_file_zip.png'};
UT.file.extMap['rar'] = {text:'RAR压缩文件', icon:'images/ic_file_zip.png'};

UT.file.extMap['pdf'] = {text:'PDF文件', icon:'images/ic_file_file.png'};

UT.file.getTextByExt = function(isDir, ext){
    if(isDir){
        return '文件夹';
    }else{
        var lowerCaseExt = ext.toLowerCase();
        var item = UT.file.extMap[lowerCaseExt];
        if(item == undefined){
            return '未知文件';
        }else{
            return item.text;
        }
    }
}

UT.file.getIconByExt = function(isDir, ext){
    if(isDir){
        return 'images/ic_file_dir.png';
    }else{
        var lowerCaseExt = ext.toLowerCase();
        var item = UT.file.extMap[lowerCaseExt];
        if(item == undefined){
            return 'images/ic_file_file.png';
        }else{
            return item.icon;
        }
    }
}

UT.file.getParentFullpath = function(fullpath){
    if(fullpath == undefined || fullpath == '/'){
        return '/';
    }
    var lastIndex = fullpath.lastIndexOf('/');
    var parentFullpath = fullpath.substring(0, lastIndex);
    return parentFullpath == '' ? '/' : parentFullpath;
}
UT.file.checkSelected = function(currentFile, callback){
    if(currentFile == undefined){
        $.zxxbox.remind2('请先选中文件');
    }else{
        callback();
    }
}

//
UT.box = {};
UT.box.confirm = function(tit, callback, defaultVal){
    //tit
    $('#confirm_label').html(tit);
    //callback
    $('.confirm_btn').off();
    $('.confirm_btn').on('click', function(){
        if($('#confirm_form').validationEngine('validate')){
            var val = $('#confirm_input').val();
            callback(val);
            $('#confirm_input').val('');
            $.zxxbox.hide();
        }
    });
    //defaultVal
    if(defaultVal != undefined){
        $('#confirm_input').val(defaultVal);
    }
    $.zxxbox($('#confirm'));
}

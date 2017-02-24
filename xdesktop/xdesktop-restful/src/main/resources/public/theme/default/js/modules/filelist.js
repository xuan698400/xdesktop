$(function(){
	var currentPath = '/';
	var list = [];
	var currentFile = undefined;//选中的文件

	var copyType = -1;//1-复制,2-剪切,-1表示无
	var tempPath = undefined;
	var tempName = undefined;

	/**
	 * 刷新文件列表
	 *
	 * @return
	 */
	var fn_refresh = function(){
		//
		UT.http.filelist(currentPath, function(result){
			list = result.list;
			$('._fullpath').html(currentPath);
			$('._filelist').html('');
			for(i in result.list){
				var item = result.list[i];
				//
				var template = '';
				template += '<tr data-index="'+ i +'">';
				template +=		'<td><img src="'+ UT.file.getIconByExt(item.dir, item.ext) +'"/>'+ item.name +'</td>';
				template +=		'<td>'+ item.modifyTime +'</td>';
				template +=		'<td>'+ UT.file.getTextByExt(item.dir, item.ext) +'</td>';
				template +=		'<td class="tdlast">'+ item.displaySize +'</td>';
				template +=		'<td></td>';
				template += '</tr>';
				var templateObj = $(template);
				//双击打开
				templateObj.on('dblclick', function(){
					var t = result.list[$(this).attr('data-index')];
					if(t.dir){
						currentPath = t.fullpath;
						fn_refresh();
					}
				});
				//单击选中
				templateObj.on('click', function(){
					var c = $(this).attr('class');
					if(c == 'tr_checked'){
						$(this).attr('class', '');
						currentFile = undefined;
					}else{
						$('._filelist').find('tr').attr('class', '');
                        $(this).attr('class', 'tr_checked');
                        currentFile = list[$(this).attr('data-index')];
					}
				});
				$('._filelist').append(templateObj);
			}
		})
	}

	/**
	 * 创建文件夹
	 *
	 * @return
	 */
	var fn_doMkdir = function(dirName){
		UT.http.doMkdir(currentPath, dirName, function(){
			fn_refresh();
		});
	}

	/**
	 * 删除
	 *
	 * @return
	 */
	var fn_doRm = function(){
		$.zxxbox.ask('确定删除选中的文件么?', function(){
			UT.http.doRm(currentFile.fullpath, function(){
				$.zxxbox.hide();
				fn_refresh();
			});
		});
	}

	/**
	 * 重命名
	 *
	 * @return
	 */
	var fn_doRename = function(newname){
		UT.http.doRename(currentPath, currentFile.name, newname, function(){
			fn_refresh();
		});
	}

	/**
	 * 初始化事件
	 *
	 * @return
	 */
	var fn_initEvent = function(){
		//返回按钮
		$('.back').on('click', function(){
			currentPath = UT.file.getParentFullpath(currentPath);
			fn_refresh();
		});
		//菜单事件
		$('.operateul').find('li').hover(function(){
			$(this).find('.operateul_sub').show();
		}, function(){
			$(this).find('.operateul_sub').hide();
		});
		//子菜单点击事件
		$('.operateul_sub').find('a').on('click', function(){
			var tag = $(this).attr('data-tag');
			switch(tag) {
				case "createDirBtn":
					UT.box.confirm('文件夹名称', function(val){
						fn_doMkdir(val);
					});
					break;
				case "removeFileBtn":
					UT.file.checkSelected(currentFile, function(){
						fn_doRm();
					});
					break;
				case "renameFileBtn":
					UT.file.checkSelected(currentFile, function(){
						UT.box.confirm('新名称', function(val){
							fn_doRename(val);
						}, currentFile.name);
					});
					break;
				case "copyFileBtn":
					UT.file.checkSelected(currentFile, function(){
						tempPath = currentPath;
						tempName = currentFile.name;
						copyType = 1;
						$.zxxbox.remind2('已复制到剪贴板');
					});
					break;
				case "moveFileBtn":
					UT.file.checkSelected(currentFile, function(){
						tempPath = currentPath;
						tempName = currentFile.name;
						copyType = 2;
						$.zxxbox.remind2('已剪切到剪贴板');
					});
					break;
				case "pasteFileBtn":
					if(copyType == -1){
						$.zxxbox.remind2('请先复制或剪切');
					}else{
						if(copyType == 1){
							UT.http.doCopy(tempPath, currentPath, tempName, function(){
								fn_refresh();
							});
						}else if(copyType == 2){
							UT.http.doMove(tempPath, currentPath, tempName, function(){
								fn_refresh();
							});
						}else{
							$.zxxbox.remind2('复制剪切模式错误');
						}
						//
						tempPath = undefined;
						tempName = undefined;
						copyType = -1;
					}
					break;
				default:
            }
		});
		//
	}

	/**
	 * 初始化
	 *
	 * @return
	 */
	var fn_init = function(){
		fn_initEvent();
		fn_refresh();
	}
	fn_init();
});
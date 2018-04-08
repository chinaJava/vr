$(function () {
    $("#jqGrid").jqGrid({
        url: '../tgameinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'id', index: 'ID', width: 20, key: true},
			{ label: '图标', name: 'logoUrl', index: 'LOGO_URL', width: 80,formatter:picFormatter }, 			
			{ label: '游戏名', name: 'name', index: 'NAME', width: 80 }, 			
			{ label: '简介', name: 'summary', index: 'SUMMARY', width: 80 }, 			
			//{ label: '大小', name: 'size', index: 'SIZE', width: 40 }, 			
			{ label: '是否收费', name: 'ischarge', index: 'ISCHARGE', width: 40,formatter:function(value){
				return value=='0'?
						'<span class="label label-success">免费</span>' : 
						'<span class="label label-danger">收费</span>';
			} }, 			
			{ label: '游戏类型', name: 'typeName', index: 'TYPEID', width: 30 }, 			
			//{ label: '版本', name: 'version', index: 'VERSION', width: 20 }, 			
			//{ label: '游戏介绍', name: 'introduce', index: 'INTRODUCE', width: 80 }, 			
			{ label: '语言', name: 'language', index: 'LANGUAGE', width: 20 }, 			
			//{ label: '更新内容', name: 'updateContent', index: 'UPDATE_CONTENT', width: 80 }, 			
			{ label: '星级', name: 'starLevel', index: 'STAR_LEVEL', width: 20 }, 			
			//{ label: '游戏标签', name: 'gameLabel', index: 'GAME_LABEL', width: 80 }, 			
			//{ label: '苹果下载地址', name: 'downloadingIosUrl', index: 'DOWNLOADING_IOS_URL', width: 80 }, 			
			{ label: '苹果下载量', name: 'downloadingIosNum', index: 'DOWNLOADING_IOS_NUM', width: 40 }	,		
			//{ label: '安卓下载地址', name: 'downloadingAndroidUrl', index: 'DOWNLOADING_ANDROID_URL', width: 80 }, 			
			{ label: '安卓下载量', name: 'downloadingAndroidNum', index: 'DOWNLOADING_ANDROID_NUM', width: 40 }	,
			{ label: '游戏渠道', name: 'gameChannel', index: 'GAME_CHANNEL', width: 40,formatter: function(value, options, row){
				if(value === '1'){
					return '<span class="label label-success">安卓游戏</span>';
				}else if(value === '2'){
					return '<span class="label label-danger">IOS游戏</span>';
				}else{
					return '<span class="label label-info">双端游戏</span>';
				}
			} }	,
			{ label: '推荐', name: 'isRecommend', index: 'IS_RECOMMEND', width: 20,formatter:function(value){
				return value==0?
							'<span class="label label-info">否</span>' : 
							'<span class="label label-success">是</span>';
			} },
			{ label: '应用', name: 'status', index: 'STATUS', width: 20,formatter:function(value){
				return value==0?
							'<span class="label label-danger">下架</span>' : 
							'<span class="label label-success">上架</span>';
			} }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    getGameType();
    getAllGameLabel();
    
    /*联想输入*/  
   	//gameList($('#autocomplete'),$('.list-group').eq(0))
   	//gameList($('#game-list'),$('.list-group').eq(1))
});

function gameList(obj,list){
	obj.keyup(function(){
		var val = $(this).val();
		list.html('');
		$.ajax({
			type:"get",
			url:"../tgameinfo/queryGameName",
			data: {"gameName":val},
			success:function(data){
				var arr = data.tGameInfoList;
				if(arr){
					list.show();
					for(var i=0;i<arr.length;i++){		
						list.append($('<li data-id='+arr[i].id+' value='+arr[i].id+' class="list-group-item">'+arr[i].name+'</li>'));
					}
					var items = list.find('li');
					items.on('mouseover click',function(){
						obj.val($(this).html()).attr('data-id',$(this).attr('data-id'));
					})
					items.on('click',function(){
						list.hide();
					})
				}else{
					list.hide();
				}
			}
		});
	})
}  

function picFormatter(cellvalue, options, rowdata) {
	if(rowdata.logoUrl!=null){
		return '<a href="'+rowdata.logoUrl+'" target="_blank"><img src="'+rowdata.logoUrl+'" style="width:50px;height:50px;-moz-border-radius: 10px;-webkit-border-radius: 10px;border-radius:10px;" /></a>';
	}else{
		return "";
	}
}

function getGameType(){
	$("#gameType_query option").remove();
	$("#gameType_add option").remove();
	$.get("../tgametype/getAllList", function(result){
		var json = eval(result.parentGameTypeList)  
		$("#gameType_query").append("<option value=''>请选择游戏类型</option>");
		for(var i=0; i<json.length; i++){
			$("#gameType_query").append("<option value='"+json[i].id+"'>"+json[i].name+"</option>");
			$("#gameType_add").append("<option value='"+json[i].id+"'>"+json[i].name+"</option>");
		}
	});
}
//查询所有的游戏标签--新增使用
function getAllGameLabel(){
	$.get("../tgamelabel/getAllList", function(result){
		var json = eval(result.gameLabelList)
		for(var i=0; i<json.length; i++){
			$("#gameLabelDiv").append("<label class='checkbox-inline'><input type='checkbox' name='gameLabel' value='"+json[i].id+"' />"+json[i].name+"</label>");
		}
	});
}
//查询游戏已选的标签--修改使用
function getGameLabel(gameId){
	$("input[name='gameLabel']:checkbox").attr("checked",false);
	$.get("../tgameinfo/getGameLabelList/"+gameId, function(result){
		var json = eval(result.gameLabelTempList)
		for(var i=0; i<json.length; i++){
			$("input[name='gameLabel'][value='"+json[i].labelid+"']").attr("checked",true).prop("checked",true);
		}
	});
}
// 游戏标签判断
function changeLabel(){
	// 游戏标签至少判断一个
	var obj=$("input[name='gameLabel']");
	var s = ''; 
	for(var i=0; i<obj.length; i++){ 
		if(obj[i].checked){
			//如果选中，将value添加到变量s中 
			s+=obj[i].value+','; 
			vm.tGameInfo.gameLable =s;
		}
	} 
	return s;
}
// 游戏开始地址和下载地址只能填一个
function gameAddress(){
	var gameUrl=vm.tGameInfo.gameUrl;
	var downloadingIosUrl=vm.tGameInfo.downloadingIosUrl;
	var downloadingAndroidUrl=vm.tGameInfo.downloadingAndroidUrl;
	if($.trim(gameUrl)!='' && $.trim(downloadingIosUrl)!='' || $.trim(downloadingAndroidUrl)!=''){
		alert("游戏开始地址和下载地址，只能填其中一个");
		return true;
	}
	return false;
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			name: null,
			typeid: '',
			ischarge: '',
			isHotGame: '',
			isBoutique: ''
		},
		showList: true,
		title: null,
		tGameInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			$("#game_logo_url").hide();
			vm.title = "新增";
			vm.tGameInfo = {};
			$("input[name='gameLabel']:checkbox").attr("checked",false);
			//默认免费游戏
			vm.tGameInfo.ischarge = '0';
			//获取文本编辑器
			var ue = UM.getEditor('gameIntroduce');
			var ue_con = UM.getEditor('gameUpdateContent');
			//初始化编辑器里的内容
            ue.ready(function() {
    		    ue.setContent("请输入内容...");
    		});
            ue_con.ready(function() {
            	ue_con.setContent("请输入内容...");
            });
            $("#gameFileUrl_hidden").val("");
            $("#gameFileSize_hidden").val("");
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
           
            $("#gameFileUrl_hidden").val("");
            $("#gameFileSize_hidden").val("");
            vm.getInfo(id)
		},
		shelf:function(event){
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.tGameInfo.id=id;
			vm.tGameInfo.status=0;
			var url ="../tgameinfo/updateGameStatus";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tGameInfo),
			    success: function(r){
			    	if(r.code === 200){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		shelves :function(event){
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.tGameInfo.id=id;
			vm.tGameInfo.status=1;
			var url ="../tgameinfo/updateGameStatus";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tGameInfo),
			    success: function(r){
			    	if(r.code === 200){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		saveOrUpdate: function (event) {
			var url = vm.tGameInfo.id == null ? "../tgameinfo/save" : "../tgameinfo/update";
			
			var reg = /^\d+(\.\d{1,3})*$/;
			if(vm.tGameInfo.versionIos!='' && vm.tGameInfo.versionIos!=null && !reg.test(vm.tGameInfo.versionIos)){
				alert("版本号不合法!");
				return false;
			}
			if(vm.tGameInfo.versionAndroid!='' && vm.tGameInfo.versionAndroid!=null && !reg.test(vm.tGameInfo.versionAndroid)){
				alert("版本号不合法!");
				return false;
			}
			//获取文本编辑器内容
			vm.tGameInfo.introduce = UM.getEditor('gameIntroduce').getContent();
			vm.tGameInfo.updateContent = UM.getEditor('gameUpdateContent').getContent();
			if($('.cropped').find('img').length){
				//logoImgData:游戏图标二进制流数据
				vm.tGameInfo.logoImgData = $('.cropped').find('img')[0].src;
			}
			//imgData:游戏图片的二进制流数据
			var imgData = "";
			$('#gamePicFileDiv .kv-file-content').each(function(){
				imgData += $(this).find('img')[0].src+"$";
			})
			vm.tGameInfo.imgData = imgData;
			if($("#gameFileUrl_hidden").val()!=null && $("#gameFileUrl_hidden").val()!=""){
				vm.tGameInfo.gameUrl = $("#gameFileUrl_hidden").val();
				vm.tGameInfo.sizeAndroid = $("#gameFileSize_hidden").val();
			}
			if($("#gameVideoUrl_hidden").val()!=null && $("#gameVideoUrl_hidden").val()!=""){
				vm.tGameInfo.gameVideoUrl = $("#gameVideoUrl_hidden").val();
			}
			// 保存或更新游戏信息，必须有一个以上游戏标签
			var labelIds=changeLabel();
			if(labelIds=='' ){
				alert("至少选择一个标签");
				return ;
			}
			// 游戏开始地址和下载地址只能填一个
//			var flag=gameAddress();
//			if(flag){
//				return ;
//			}
			//提示填写完整
//			if((vm.tGameInfo.version&&vm.tGameInfo.downloadingAndroidUrl&&vm.tGameInfo.downloadingIosUrl&&vm.tGameInfo.size&&vm.tGameInfo.packageNameAndroid&&vm.tGameInfo.packageNameIos&&vm.tGameInfo.typeid)){
//				alert("请把必填项填写完整");
//				return ;
//			}
			//console.log(vm.tGameInfo.imgData)
			//console.log(vm.tGameInfo.version,vm.tGameInfo.downloadingAndroidUrl,vm.tGameInfo.downloadingIosUrl,vm.tGameInfo.size,vm.tGameInfo.packageNameAndroid,vm.tGameInfo.packageNameIos,vm.tGameInfo.typeid)
			if(!(vm.tGameInfo.name&&vm.tGameInfo.gameChannel&&vm.tGameInfo.packageNameAndroid&&vm.tGameInfo.packageNameIos&&vm.tGameInfo.typeid)){
				alert("请把必填项填写完整");
				return false;
			}
			
			//console.log(vm.tGameInfo);
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tGameInfo),
			    success: function(r){
			    	if(r.code === 200){
						alert('操作成功', function(index){
							$('.fileinput-remove:not([disabled])').click();
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tgameinfo/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 200){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../tgameinfo/info/"+id, function(r){
                vm.tGameInfo = r.tGameInfo;
                console.log(vm.tGameInfo);
                var id=vm.tGameInfo.id;
                //查询游戏已选的标签
                getGameLabel(id);
                var ue = UM.getEditor('gameIntroduce');
                var ue_con = UM.getEditor('gameUpdateContent');
                ue.ready(function() {
        		    ue.setContent(vm.tGameInfo.introduce);
        		});
                ue_con.ready(function() {
                	ue_con.setContent(vm.tGameInfo.updateContent);
                });
                //游戏图标
            	$("#game_logo_url").attr("src",vm.tGameInfo.logoUrl).show();
            	//游戏视频
            	$("#game_video_url").attr("src",vm.tGameInfo.gameVideoUrl);
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{'name': vm.q.name,"typeid":vm.q.typeid,"ischarge":vm.q.ischarge,"isHotGame":vm.q.isHotGame,"isBoutique":vm.q.isBoutique},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
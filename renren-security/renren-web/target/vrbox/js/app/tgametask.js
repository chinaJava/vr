$(function () {
    $("#jqGrid").jqGrid({
        url: '../tgametask/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true,hidden:true },
			{ label: '图标', name: 'logoUrl', index: 'LOGO_URL', width: 80,formatter:picFormatter }, 			
			{ label: '游戏名称', name: 'gameName', index: 'GAMEID', width: 80 }, 			
			{ label: '任务标题', name: 'title', index: 'TITLE', width: 120 }, 			
			{ label: '奖励金币', name: 'earnGold', index: 'EARN_GOLD', width: 40 }, 			
			{ label: '任务类型', name: 'taskType', index: 'TASK_TYPE', width: 40 },		
			{ label: '奖励份数', name: 'restNumber', index: 'REST_NUMBER', width: 40 }, 			
			{ label: '任务难度', name: 'difficult', index: 'DIFFICULT', width: 80 }, 			
			{ label: '开始时间', name: 'beginTime', index: 'BEGIN_TIME', width: 80 }, 			
			{ label: '最后完成时间', name: 'deadline', index: 'DEADLINE', width: 80 }, 			
			{ label: '任务发布人', name: 'creator', index: 'CREATOR', width: 40 }, 			
			//{ label: '任务详情', name: 'detail', index: 'DETAIL', width: 80 }, 			
			{ label: '任务状态', name: 'status', index: 'STATUS', width: 40,formatter: function(value, options, row){
					if(value === '1'){
						return '<span class="label label-success">进行中</span>';
					}else if(value === '2'){
						return '<span class="label label-danger">已结束</span>';
					}else{
						return '<span class="label label-info">未开始</span>';
					}
				} 
			},
			{ label: '查看详情', name: '', index: 'operate', width: 40,
	            formatter: function (cellvalue, options, rowObject) {
	                var detail="<input type='button' value='详情' class='btn btn-info'  onclick='taskDetail("+ rowObject.id +")' />";
	                return detail;
	            }
			}			
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
});
//查看任务详情
function taskDetail(id){
	$("#submit_button").hide();
	vm.showList = false;
    vm.title = "修改";
    vm.getInfo(id)
}

function picFormatter(cellvalue, options, rowdata) {
	if(rowdata.logoUrl!=null){
		return '<a href="'+rowdata.logoUrl+'" target="_blank"><img src="'+rowdata.logoUrl+'" style="width:50px;height:50px;-moz-border-radius: 10px;-webkit-border-radius: 10px;border-radius:10px;" /></a>';
	}else{
		return "";
	}
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			gameName: null,
			title: null,
			taskType: ''
		},
		showList: true,
		title: null,
		tGameTask: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			$("#task_logo_url").hide();
			$("#submit_button").show();
			vm.showList = false;
			vm.title = "新增";
			vm.tGameTask = {};
			var ue = UM.getEditor('detail_task');
			//初始化编辑器里的内容
            ue.ready(function() {
    		    ue.setContent("请输入内容...");
    		});
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            $("#submit_button").show();
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.tGameTask.id == null ? "../tgametask/save" : "../tgametask/update";
			vm.tGameTask.detail = UM.getEditor('detail_task').getContent();
			if($('.cropped').find('img').length){
				//logoImgData:游戏图标二进制流数据
				vm.tGameTask.logoImgData = $('.cropped').find('img')[0].src;
			}
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tGameTask),
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
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tgametask/delete",
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
			$.get("../tgametask/info/"+id, function(r){
                vm.tGameTask = r.tGameTask;
                var ue = UM.getEditor('detail_task');
				//初始化编辑器里的内容
	            ue.ready(function() {
	    		    ue.setContent(vm.tGameTask.detail);
	    		});
	          //游戏图标
            	$("#task_logo_url").attr("src",vm.tGameTask.logoUrl).show();
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'gameName': vm.q.gameName,'title': vm.q.title,'taskType': vm.q.taskType},
				page:page
            }).trigger("reloadGrid");
		}
	}
});
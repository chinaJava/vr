$(function () {
    $("#jqGrid").jqGrid({
        url: '../tappusertask/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true,hidden:true },
			{ label: '游戏任务', name: 'taskName', index: 'TASK_ID', width: 80 }, 			
			{ label: '领取人', name: 'userMobile', index: 'APPUSER_ID', width: 80 }, 			
			{ label: '领取任务时间', name: 'receiveTime', index: 'RECEIVE_TIME', width: 80 }, 			
			{ label: '完成任务时间', name: 'finishTime', index: 'FINISH_TIME', width: 80 }, 			
			{ label: '备注', name: 'remark', index: 'REMARK', width: 80 },			
			{ label: '状态', name: 'status', index: 'STATUS', width: 80 ,formatter: function(value, options, row){
					if(value === '1'){
						return '<span class="label label-info">进行中</span>';
					}else if(value === '2'){
						return '<span class="label label-info">已完成</span>';
					}else if(value === '3'){
						return '<span class="label label-success">已领奖</span>';
					}else {
						return '<span class="label label-danger">已结束</span>';
					}
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			useMobile: null,
			taskName: null,
			status: ''
		},
		showList: true,
		title: null,
		tAppuserTask: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tAppuserTask = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.tAppuserTask.id == null ? "../tappusertask/save" : "../tappusertask/update";
			vm.tAppuserTask.remark = UM.getEditor('remark_task').getContent();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tAppuserTask),
			    success: function(r){
			    	if(r.code === 0){
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
				    url: "../tappusertask/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
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
			$.get("../tappusertask/info/"+id, function(r){
                vm.tAppuserTask = r.tAppuserTask;
                $("#finishTask_pic img").remove();
                if(vm.tAppuserTask.finishTaskPic!=null && vm.tAppuserTask.finishTaskPic!=''){
                	var pic = vm.tAppuserTask.finishTaskPic.split(";");
                	for(var i=0;i<pic.length;i++){
                		$("#finishTask_pic").append("<a href='"+pic[i]+"' target='_blank'><img src='"+pic[i]+"' style='width:150px;height:150px;' /></a>");
                	}
                }
                var ue = UM.getEditor('remark_task');
                ue.ready(function() {
                	ue.setContent(vm.tAppuserTask.remark);
                });
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'useMobile': vm.q.useMobile,'taskName': vm.q.taskName,'status': vm.q.status},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
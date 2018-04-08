$(function () {
    $("#jqGrid").jqGrid({
        url: '../tinvitation/list',
        datatype: "json",
        colModel: [			
			{ label: 'invitationId', name: 'invitationId', index: 'INVITATIONID', width: 50, key: true,hidden:true },
			{ label: '内容', name: 'content', index: 'CONTENT', width: 80 }, 			
			{ label: '创建时间', name: 'sendTime', index: 'SEND_TIME', width: 80 }, 			
			{ label: '发帖者', name: 'nikeName', index: 'NIKENAME', width: 80 },
			{ label: '点赞数', name: 'likesNum', index: 'LIKESNUM', width: 80 },
			{ label: '评论数', name: 'answerNum', index: 'ANSWERNUM', width: 80 },
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
			title: null
		},
		showList: true,
		title: null,
		tVideoInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tVideoInfo = {};
			//获取文本编辑器
			var ue = UM.getEditor('videoRemark');
			//初始化编辑器里的内容
            ue.ready(function() {
    		    ue.setContent("请填写视频说明...");
    		});
            vm.tVideoInfo.status = "1";
            $("#video_play").hide();
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
			var url = vm.tVideoInfo.id == null ? "../tvideoinfo/save" : "../tvideoinfo/update";
			if($("#videoUrl_hidden").val()!=null && $("#videoUrl_hidden").val()!=""){
				vm.tVideoInfo.videoUrl = $("#videoUrl_hidden").val();
				vm.tVideoInfo.duration = $("#videoSize_hidden").val();
			}
			vm.tVideoInfo.remark = UM.getEditor('videoRemark').getContent();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tVideoInfo),
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
			var invitationIds = getSelectedRows();
			if(invitationIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tinvitation/delete",
				    data: JSON.stringify(invitationIds),
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
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'content': vm.q.title},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
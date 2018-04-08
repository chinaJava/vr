$(function () {
    $("#jqGrid").jqGrid({
        url: '../tanswer/list',
        datatype: "json",
        colModel: [			
			{ label: 'answerId', name: 'answerId', index: 'ANSWER_ID', width: 50, key: true,hidden:true },
			{ label: '文章内容', name: 'invitationContent', index: 'INVITATIONCONTENT', width: 80 }, 			
			{ label: '评论内容', name: 'answerContent', index: 'ANSWER_CONTENT', width: 80 }, 			
			{ label: '评论时间', name: 'answerTime', index: 'ANSWER_TIME', width: 80 },
			{ label: '回复者', name: 'nikeName', index: 'NIKENAME', width: 80 },
			{ label: '被回复者', name: 'coverAnswerUserName', index: 'COVERANSWERUSERNAME', width: 80 }
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
			var answerIds = getSelectedRows();
			if(answerIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tanswer/delete",
				    data: JSON.stringify(answerIds),
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
			$.get("../tvideoinfo/info/"+id, function(r){
                vm.tVideoInfo = r.tVideoInfo;
                $("#video_play").attr("src",vm.tVideoInfo.videoUrl).show();
                //获取文本编辑器
    			var ue = UM.getEditor('videoRemark');
    			//初始化编辑器里的内容
                ue.ready(function() {
        		    ue.setContent(vm.tVideoInfo.remark);
        		});
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'title': vm.q.title},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
$(function () {
    $("#jqGrid").jqGrid({
        url: '../tgamecomment/list',
        datatype: "json",
        colModel: [			
			/*{ label: 'id', name: 'id', index: 'ID', width: 50, key: true },*/
			{ label: '游戏名', name: 'gameName', index: 'gameName', width: 80 }, 			
			{ label: '用户名', name: 'userName', index: 'userName', width: 80 }, 			
			{ label: '游戏评论', name: 'comment', index: 'COMMENT', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'CREATETIME', width: 80 }, 			
			{ label: '星级', name: 'starLevel', index: 'STAR_LEVEL', width: 80 }, 			
			{ label: '主副评论', name: 'pid', index: 'PID', width: 80, formatter: function(value){
				return value ==0 ? 
						'<span class="label label-success">主评论</span>' : 
						'<span class="label label-info">副评论</span>';
				} },
			{ label: '推荐', name: 'isRecommend', index: 'IS_RECOMMEND', width: 80, formatter: function(value){
				return value == '0' ? 
						'<span class="label label-info">否</span>' : 
						'<span class="label label-success">是</span>';
				} },
			{ label: '审核', name: 'status', index: 'STATUS', width: 80, formatter: function(value){
				if(value=='0'){
					return '<span class="label label-warning">待审核</span>';
				}else if(value=='1'){
					return '<span class="label label-success">通过</span>';
				}else{
					return '<span class="label label-danger">不通过</span>';
				}} 
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
    /*联想输入*/  
   	gameList($('#autocomplete'),$('.list-group').eq(0));
   	gameList($('#game-list'),$('.list-group').eq(1));
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
					});
					items.on('click',function(){
						list.hide();
					});
				}else{
					list.hide();
				}
			}
		});
	});
}  

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			gameName:null,
			userName:null,
			status:""
		},
		showList: true,
		title: null,
		tGameComment: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tGameComment = {};
			$("#pid").attr("selected",1);
			$("#pid").removeAttr("disabled");
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";    
            vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
				var url = vm.tGameComment.id == null ? "../tgamecomment/save" : "../tgamecomment/update";
				vm.tGameComment.pid = $("#pid").val();
				$.ajax({
					type: "POST",
					url: url,
					data: JSON.stringify(vm.tGameComment),
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
				    url: "../tgamecomment/delete",
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
			$("#pid").empty();
			$.get("../tgamecomment/info/"+id, function(r){
                vm.tGameComment = r.tGameComment;
                var pid = vm.tGameComment.pid;
                if( pid!="" && pid>0){
                	$("#pid").append("<option value='"+pid+"'>"+"副评论"+"</option>");
                 }else {
                	$("#pid").append("<option value='0'>"+"主评论"+"</option>");
                 }
            });
			$("#pid").attr("disabled","disabled");
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{"status":vm.q.status,"userName":vm.q.userName,"gameName":vm.q.gameName},
                page:1
            }).trigger("reloadGrid");
		}
	}
});
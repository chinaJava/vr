$(function () {
    $("#jqGrid").jqGrid({
        url: '../tappuserfavorites/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true,hidden:true },
			{ label: '用户手机号', name: 'userMobile', index: 'APPUSER_ID', width: 80 }, 			
			{ label: '游戏名称', name: 'gameName', index: 'GAME_ID', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'CREATETIME', width: 80 }, 			
			{ label: '类型', name: 'type', index: 'TYPE', width: 80,formatter:function(value){
				if(value=='0'){
					return '<span class="label label-success"></span>';
				}else if(value=='1'){
					return '<span class="label label-success">推荐</span>';
				}else if(value=='2'){
					return '<span class="label label-info">收藏</span>';
				}
					
				
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
});

var vm = new Vue({

	el:'#rrapp',
	data:{
		q:{
			gameName: null,
			userMobile:null
		},
		showList: true,
		title: null,
		tAppuserFavorites: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tAppuserFavorites = {};
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
			var url = vm.tAppuserFavorites.id == null ? "../tappuserfavorites/save" : "../tappuserfavorites/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tAppuserFavorites),
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
				    url: "../tappuserfavorites/delete",
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
			$.get("../tappuserfavorites/info/"+id, function(r){
                vm.tAppuserFavorites = r.tAppuserFavorites;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'gameName': vm.q.gameName,'userMobile': vm.q.userMobile},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
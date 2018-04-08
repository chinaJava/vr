$(function () {
    $("#jqGrid").jqGrid({
        url: '../tappuser/list',
        datatype: "json",
        colModel: [			
			{ label: '用户id', name: 'id', index: 'ID', width: 80, key: true },
			{ label: '手机号', name: 'mobile', index: 'MOBILE', width: 80 }, 			
			{ label: '用户名', name: 'username', index: 'USERNAME', width: 80 },		
			//{ label: '密码', name: 'password', index: 'PASSWORD', width: 80 }, 			
			{ label: '注册时间', name: 'createtime', index: 'CREATETIME', width: 80 }, 			
			{ label: '第三方登录ID', name: 'openid', index: 'OPENID', width: 80 },		
			{ label: '状态', name: 'status', index: 'STATUS', width: 40,formatter: function(value, options, row){
				return value === '0' ? 
						'<span class="label label-danger">禁用</span>' : 
						'<span class="label label-success">正常</span>';
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
			mobile: null,
			username:null
		},
		showList: true,
		title: null,
		tAppuser: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tAppuser = {};
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
			var url = vm.tAppuser.id == null ? "../tappuser/save" : "../tappuser/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tAppuser),
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
				    url: "../tappuser/delete",
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
			$.get("../tappuser/info/"+id, function(r){
                vm.tAppuser = r.tAppuser;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'mobile': vm.q.mobile,'username': vm.q.username},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
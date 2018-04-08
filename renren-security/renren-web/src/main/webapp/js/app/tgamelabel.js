$(function () {
    $("#jqGrid").jqGrid({
        url: '../tgamelabel/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true,hidden:true },
			{ label: '游戏标签名', name: 'name', index: 'NAME', width: 80 }, 			
			{ label: '创建者', name: 'creator', index: 'CREATOR', width: 80 }, 			
			{ label: '创建时间', name: 'createtime', index: 'CREATETIME', width: 80 }, 			
			{ label: '状态', name: 'status', index: 'STATUS', width: 80,formatter: function(value, options, row){
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
			name: null
		},
		showList: true,
		title: null,
		tGameLabel: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tGameLabel = {};
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
			var url = vm.tGameLabel.id == null ? "../tgamelabel/save" : "../tgamelabel/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tGameLabel),
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
				    url: "../tgamelabel/delete",
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
			$.get("../tgamelabel/info/"+id, function(r){
                vm.tGameLabel = r.tGameLabel;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
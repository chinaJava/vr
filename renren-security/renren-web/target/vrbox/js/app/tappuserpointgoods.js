$(function () {
    $("#jqGrid").jqGrid({
        url: '../tappuserpointgoods/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true,hidden:true },
			{ label: '用户手机号', name: 'userMobile', index: 'APPUSER_ID', width: 80 }, 			
			{ label: '兑换商品名称', name: 'goodsName', index: 'GOODS_ID', width: 80 }, 			
			{ label: '兑换状态', name: 'status', index: 'STATUS', width: 40,formatter: function(value, options, row){
					if(value === '1'){
						return '<span class="label label-info">兑换中</span>';
					}else if(value === '2'){
						return '<span class="label label-success">兑换成功</span>';
					}else{
						return '<span class="label label-danger">用户已删除</span>';
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
			goodsName: null,
			status: ''
		},
		showList: true,
		title: null,
		tAppuserPointgoods: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tAppuserPointgoods = {};
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
			var url = vm.tAppuserPointgoods.id == null ? "../tappuserpointgoods/save" : "../tappuserpointgoods/update";
			console.log(vm.tAppuserPointgoods.remark);
			if($("#goods_remark").val()==""){
				alert("商品发送说明不能为空!");
				return ;
			}
			if(vm.tAppuserPointgoods.status!='2'){
				alert("请修改状态为兑换成功");
				return ;
			}
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tAppuserPointgoods),
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
				    url: "../tappuserpointgoods/delete",
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
			$.get("../tappuserpointgoods/info/"+id, function(r){
                vm.tAppuserPointgoods = r.tAppuserPointgoods;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'useMobile': vm.q.useMobile,'goodsName': vm.q.goodsName,'status': vm.q.status},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
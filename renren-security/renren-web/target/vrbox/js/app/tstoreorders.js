$(function () {
    $("#jqGrid").jqGrid({
        url: '../tstoreorders/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true,hidden:true },
			{ label: '客户手机号', name: 'userMobile', index: 'APPUSER_ID', width: 40 }, 			
			{ label: '商品名称', name: 'productName', index: 'PRODUCT_ID', width: 40 }, 			
			{ label: '兑换数量', name: 'productAmount', index: 'PRODUCT_AMOUNT', width: 20 },		
			{ label: '下单时间', name: 'createtime', index: 'CREATETIME', width: 40 }, 			
			{ label: '收货地址', name: 'addressDetail', index: 'ADDRESS_ID', width: 80 }, 			
			{ label: '收货人', name: 'contactPerson', index: 'ADDRESS_ID', width: 40 }, 			
			{ label: '收货电话', name: 'contactPhone', index: 'ADDRESS_ID', width: 40 }, 			
			{ label: '订单状态', name: 'status', index: 'STATUS', width: 30,formatter: function(value, options, row){
					if(value === '1'){
						return '<span class="label label-success">已发货</span>';
					}else if(value === '2'){
						return '<span class="label label-success">确认收货</span>';
					}else{
						return '<span class="label label-danger">未发货</span>';
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
			mobile: null,
			productName: null,
			status: ''
		},
		showList: true,
		title: null,
		tStoreOrders: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tStoreOrders = {};
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
			var url = vm.tStoreOrders.id == null ? "../tstoreorders/save" : "../tstoreorders/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tStoreOrders),
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
				    url: "../tstoreorders/delete",
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
		send: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			confirm('确定要对选中的记录批量发货？', function(){
				$.ajax({
					type: "POST",
				    url: "../tstoreorders/send",
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
			$.get("../tstoreorders/info/"+id, function(r){
                vm.tStoreOrders = r.tStoreOrders;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'mobile': vm.q.mobile,'productName': vm.q.productName,'status': vm.q.status},
				page:page
            }).trigger("reloadGrid");
		}
	}
});
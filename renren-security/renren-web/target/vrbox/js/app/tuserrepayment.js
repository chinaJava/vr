$(function () {
    $("#jqGrid").jqGrid({
        url: '../tuserrepayment/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true,hidden:true },
			{ label: '用户编号', name: 'userId', index: 'USER_ID', width: 80 }, 			
						
			{ label: '交易号', name: 'paymentNo', index: 'PAYMENT_NO', width: 80 }, 			
			{ label: '交易时间', name: 'paymentTime', index: 'PAYMENT_TIME', width: 80 }, 			
			{ label: '会员等级', name: 'mermberLevelId', index: 'MERMBER_LEVEL_ID', width: 80 }, 			
			{ label: '开通时间', name: 'mermberTime', index: 'MERMBER_TIME', width: 80 }, 			
			{ label: '支付金额', name: 'amountMoney', index: 'AMOUNT_MONEY', width: 80}	, 
			{ label: '状态', name: 'status', index: 'STATUS', width: 80 ,formatter:function(value){
				if(value=='Y'){
					return '<span class="label label-success">已支付</span>';
				}else if(value=='D'){
					return '<span class="label label-info">待支付</span>';
				}
			}}, 
			{ label: '支付方式', name: 'paymentMethod', index: 'PAYMENT_METHOD', width: 80,formatter:function(value){
				if(value=='wx'){
					return '<span class="label label-success">微&nbsp;&nbsp;&nbsp;信</span>';
				}else if(value=='zfb'){
					return '<span class="label label-info">支付宝</span>';
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
			userId:null,
			paymentNo:null
		},
		showList: true,
		title: null,
		tUserRepayment: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tUserRepayment = {};
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
			var url = vm.tUserRepayment.id == null ? "../tuserrepayment/save" : "../tuserrepayment/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tUserRepayment),
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
				    url: "../tuserrepayment/delete",
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
			$.get("../tuserrepayment/info/"+id, function(r){
                vm.tUserRepayment = r.tUserRepayment;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'userId':vm.q.userId,'paymentNo':vm.q.paymentNo},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
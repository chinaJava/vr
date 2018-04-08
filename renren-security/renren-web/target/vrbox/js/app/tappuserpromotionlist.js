$(function () {
    $("#jqGrid").jqGrid({
        url: '../tappuserpromotionlist/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 40, key: true,hidden:true },
			{ label: '会员手机号', name: 'userMobile', index: '', width: 80 }, 			
			//{ label: '被推荐的会员', name: 'promotionMobile', index: 'PROMOTION_USERID', width: 80 },
			{ label: '', name: 'appuserId', index: 'APPUSER_ID', width: 80 ,hidden:true},
			{ label: '会员等级', name: 'memberLevel', index: 'memberLevel', width: 80 }	,
			//{ label: '贡献积分', name: 'gainPoints', index: 'GAIN_POINTS', width: 80 }	,	
			{ label: '过期时间', name: 'memberEndTime', index: 'memberEndTime', width: 80 }	,
			{ label: '被推荐会员数', name: 'promotionNum', index: '', width: 80 }	,
			{ label: '剩余积分', name: 'point', index: '', width: 80 }	,
			{ label: '累计积分', name: 'pointTotal', index: '', width: 80 }	,
			{ label: '查看兑换详情', name: '', index: 'operate', width: 40,
	            formatter: function (cellvalue, options, rows) {
	                var detail="<input type='button' value='详情' class='btn btn-info'  onclick='exchangeDetail(\""+rows.appuserId+"\")' />";
	                return detail;
	            }
			},	
			{ label: '查看推广详情', name: '', index: 'operate', width: 40,
	            formatter: function (cellvalue, options, rows) {
	                var detail="<input type='button' value='详情' class='btn btn-info'  onclick='promotionDetail(\""+rows.appuserId+"\")' />";
	                return detail;
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
function exchangeDetail(id){
	console.log(id);  
	layer.open({
		   type:2,
		   shadeClose:true,
		   title:'礼品兑换详情',
		   fixed:false,
		   mammin:true,
		   area:['80%','90%'],
		   content:'tappuserpointgoodsdetail.html?id='+id,
		   end:function(){
			   //location.reload();
		   }
	   })

	}
function promotionDetail(id){
	console.log(id);
   layer.open({
	   type:2,
	   shadeClose:true,
	   title:'个人推广信息',
	   fixed:false,
	   mammin:true,
	   area:['80%','90%'],
	   content:'tappuserpromotiondetail.html?id='+id,
	   end:function(){
		   //location.reload();
	   }
   })

}
var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			userMobile: null,
			promotionMobile: null,
			status: ''
		},
		showList: true,
		title: null,
		tAppuserPromotionList: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tAppuserPromotionList = {};
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
			var url = vm.tAppuserPromotionList.id == null ? "../tappuserpromotionlist/save" : "../tappuserpromotionlist/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tAppuserPromotionList),
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
				    url: "../tappuserpromotionlist/delete",
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
			$.get("../tappuserpromotionlist/info/"+id, function(r){
                vm.tAppuserPromotionList = r.tAppuserPromotionList;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'userMobile': vm.q.userMobile,"promotionMobile":vm.q.promotionMobile,"status":vm.q.status},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
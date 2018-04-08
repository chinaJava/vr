

$(function () {
	
	//获取url参数
		var url = window.location;
		function getToken(url, token){
				url = url+ "";
	            regstr = "/(\\?|\\&)" + token + "=([^\\&]+)/";
	            reg = eval(regstr);//eval可以将 regstr字符串转换为 正则表达式
	            result = url.match(reg);
	            if (result && result[2]) {
	                return result[2];
	            }
		}
		
		var  qid = getToken(url, "id");
		
	
    $("#jqGrid").jqGrid({
        url: "../tappuserpromotionlist/detail/"+qid,
        datatype: "json",
        colModel: [			
			{ label: '', name: 'id', index: 'ID', width: 40, key: true,hidden:true },
			{ label: '会员手机号', name: 'userMobile', index: '', width: 80 }, 			
			{ label: '被推荐的会员', name: 'promotionMobile', index: 'PROMOTION_USERID', width: 80 },
			
			{ label: '贡献积分', name: 'gainPoints', index: 'GAIN_POINTS', width: 80 }	,
			{ label: '状态', name: 'status', index: 'STATUS', width: 40 ,formatter:function(value){
				return value=='1'?
						'<span class="label label-success">有效推广</span>' : 
							'<span class="label label-danger">推门名单</span>';
			} },
			
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
            order: "order",
            
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});
//查看任务详情

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
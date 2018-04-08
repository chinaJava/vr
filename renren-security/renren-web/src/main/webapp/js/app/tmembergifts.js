$(function () {
    $("#jqGrid").jqGrid({
        url: '../tmembergifts/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true,hidden:true },
			{ label: 'logo地址', name: 'logoUrl', index: 'LOGO_URL', width: 40,formatter:picFormatter }, 			
			{ label: '礼包标题', name: 'title', index: 'TITLE', width: 80 }, 			
			{ label: '礼包说明', name: 'remark', index: 'REMARK', width: 80 }, 			
			{ label: '会员等级要求', name: 'memberLevel', index: 'MEMBER_LEVEL_ID', width: 40 }, 			
			{ label: '开始时间', name: 'giftsStartTime', index: 'GIFTS_START_TIME', width: 50 }, 			
			{ label: '结束时间', name: 'giftsEndTime', index: 'GIFTS_END_TIME', width: 50 }, 			
			//{ label: '领取人', name: 'creator', index: 'CREATOR', width: 80 }, 			
			{ label: '剩余数量', name: 'amount', index: 'AMOUNT', width: 40 }, 		
			{ label: '状态', name: 'status', index: 'STATUS', width: 40,formatter: function(value, options, row){
				return value === '0' ? 
						'<span class="label label-danger">已下架</span>' : 
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
    getMemberList();
});

function picFormatter(cellvalue, options, rowdata) {
	if(rowdata.logoUrl!=null){
		return '<a href="'+rowdata.logoUrl+'" target="_blank"><img src="'+rowdata.logoUrl+'" style="width:50px;height:50px;-moz-border-radius: 10px;-webkit-border-radius: 10px;border-radius:10px;" /></a>';
	}else{
		return "";
	}
}

function getMemberList(){
	$("#memberLevel_query option").remove();
	$("#memberLevel_add option").remove();
	$.get("../api/tappuserdetail/memberList", function(result){
		var json = eval(result.memberList);
		$("#memberLevel_query").append("<option value=''>请选择会员等级</option>");
		for(var i=0; i<json.length; i++){
			$("#memberLevel_query").append("<option value='"+json[i].id+"'>"+json[i].name+"</option>");
			$("#memberLevel_add").append("<option value='"+json[i].id+"'>"+json[i].name+"</option>");
		}
	});
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			title: null,
			memberLevel:"",
			status:""
		},
		showList: true,
		title: null,
		tMemberGifts: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tMemberGifts = {};
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
			var url = vm.tMemberGifts.id == null ? "../tmembergifts/save" : "../tmembergifts/update";
			if($('.cropped').find('img').length){
				//logoImgData:游戏图标二进制流数据
				vm.tMemberGifts.logoImgData = $('.cropped').find('img')[0].src;
			}
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tMemberGifts),
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
				    url: "../tmembergifts/delete",
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
			$.get("../tmembergifts/info/"+id, function(r){
                vm.tMemberGifts = r.tMemberGifts;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'title': vm.q.title,'memberLevel': vm.q.memberLevel,'status': vm.q.status},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
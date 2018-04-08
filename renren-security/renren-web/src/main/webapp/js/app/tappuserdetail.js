$(function () {
    $("#jqGrid").jqGrid({
        url: '../tappuserdetail/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 10, key: true ,hidden:true},
			{ label: '头像', name: 'headPic', index: 'HEAD_PIC', width: 40,formatter:picFormatter }, 			
			{ label: '手机号', name: 'mobile',  width: 40 }, 			
			{ label: '用户账号', name: 'username',  width: 30 }, 			
			{ label: '用户编号', name: 'usercode', index: 'USERCODE', width: 30 },		
			{ label: '昵称', name: 'nikename', index: 'NIKENAME', width: 30 }, 			
			{ label: '会员等级', name: 'memberLevel', index: 'MEMBER_LEVEL_ID', width: 30 }, 			
			{ label: '会员开始时间', name: 'memberStartTime', index: 'MEMBER_START_TIME', width: 40 }, 			
			{ label: '会员结束时间', name: 'memberEndTime', index: 'MEMBER_END_TIME', width: 40 }, 			
			{ label: '真实姓名', name: 'realname', index: 'REALNAME', width: 30 }, 			
			{ label: '性别', name: 'sex', index: 'SEX', width: 15, formatter: function(value, options, row){
				return value === 'M' ? 
						'<span class="label label-success">男</span>' : 
						'<span class="label label-danger">女</span>';
				}
			}, 
			{ label: '出生日期', name: 'birthday', index: 'BIRTHDAY', width: 40 }, 			
			{ label: '所属地区', name: 'city', index: 'CITY', width: 30 }, 			
			{ label: '当前积分', name: 'point', index: 'POINT', width: 20 }			
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
	if(rowdata.headPic!=null){
		return '<a href="'+rowdata.headPic+'" target="_blank"><img src="'+rowdata.headPic+'" style="width:80px;height:50px;" /></a>';
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
			mobile: null,
			username:null,
			sex:"",
			memberLevel:""
		},
		showList: true,
		title: null,
		tAppuserDetail: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tAppuserDetail = {};
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
			var url = vm.tAppuserDetail.id == null ? "../tappuserdetail/save" : "../tappuserdetail/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tAppuserDetail),
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
				    url: "../tappuserdetail/delete",
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
			$.get("../tappuserdetail/info/"+id, function(r){
                vm.tAppuserDetail = r.tAppuserDetail;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'mobile': vm.q.mobile,'username': vm.q.username,'sex': vm.q.sex,'memberLevel': vm.q.memberLevel},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
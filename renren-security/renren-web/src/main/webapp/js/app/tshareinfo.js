$(function () {
    $("#jqGrid").jqGrid({
        url: '../tshareinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true,hidden:true },
			{ label: '分享标题', name: 'title', index: 'TITLE', width: 80 }, 			
			{ label: '分享内容', name: 'content', index: 'CONTENT', width: 80 }, 			
			{ label: '分享图片', name: 'pic', index: 'PIC', width: 80,formatter:picFormatter  }, 			
			{ label: '分享跳转地址', name: 'pathUrl', index: 'PATH_URL', width: 80 },			
			{ label: '类型', name: 'type', index: 'TYPE', width: 80,formatter:function(value){
				return value=='1'?
						'<span class="label label-success">详情页分享内容</span>' : 
						'<span class="label label-info">个人中心分享内容</span>';
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
//图片回显
function picFormatter(cellvalue, options, rowdata) {
	if(rowdata.pic!=null){
		var str_html = "";
		var urls = rowdata.pic.split(";");
		for(var i=0;i<urls.length;i++){
			if(urls[i]!=null && urls[i]!=""){
				str_html +='<a href="'+urls[i]+'" target="_blank"><img src="'+urls[i]+'" style="width:80px;height:50px;" /></a>';
			}
		}
		return str_html;
	}else{
		return "";
	}
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		tShareInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tShareInfo = {};
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
			var url = vm.tShareInfo.id == null ? "../tshareinfo/save" : "../tshareinfo/update";
			var imgData = "";
			$('.kv-file-content').each(function(){
				imgData += $(this).find('img')[0].src+"$";
			});
			vm.tShareInfo.imgData = imgData;
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tShareInfo),
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
				    url: "../tshareinfo/delete",
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
			$.get("../tshareinfo/info/"+id, function(r){
                vm.tShareInfo = r.tShareInfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});
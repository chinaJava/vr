$(function () {
    $("#jqGrid").jqGrid({
        url: '../tgamestore/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true,hidden:true },
			{ label: '商品名称', name: 'name', index: 'NAME', width: 80 }, 			
			{ label: '价值金币', name: 'gameGold', index: 'GAME_GOLD', width: 80 }, 			
			{ label: '图片', name: 'productUrl', index: 'PRODUCT_URL', width: 80,formatter:picFormatter }, 			
			{ label: '产品价值', name: 'productValue', index: 'PRODUCT_VALUE', width: 80 }, 			
			{ label: '商品库存', name: 'inventory', index: 'INVENTORY', width: 80 }, 			
			{ label: '产品类型', name: 'productTypeName', index: 'PRODUCT_TYPE', width: 80 },
			{ label: '创建者', name: 'creator', index: 'CREATOR', width: 80 },	
			{ label: '状态', name: 'status', index: 'STATUS', width: 80,formatter: function(value, options, row){
				return value === '1' ? 
						'<span class="label label-success">上架</span>' : 
						'<span class="label label-danger">下架</span>';
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
    getstoreType();
});

//图片回显
function picFormatter(cellvalue, options, rowdata) {
	if(rowdata.productUrl!=null){
		var str_html = "";
		var urls = rowdata.productUrl.split(";");
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

function getstoreType(){
	$("#storeType_add option").remove();
	$("#storeType_query option").remove();
	$.get("../tstoreproducttype/ALLlist", function(result){
		var json = eval(result.StoreProducttypeList);
		$("#storeType_query").append("<option value=''>请选择游戏类型</option>");
		$("#storeType_add").append("<option value=''>请选择游戏类型</option>");
		for(var i=0; i<json.length; i++){
			$("#storeType_query").append("<option value='"+json[i].id+"'>"+json[i].name+"</option>");
			$("#storeType_add").append("<option value='"+json[i].id+"'>"+json[i].name+"</option>");
		}
	});
}
var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			name: null,
			productType: ''
		},
		showList: true,
		title: null,
		tGameStore: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tGameStore = {};
			var ue = UM.getEditor('detail_product');
			//初始化编辑器里的内容
            ue.ready(function() {
    		    ue.setContent("请输入内容...");
    		});
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
			var url = vm.tGameStore.id == null ? "../tgamestore/save" : "../tgamestore/update";
			var imgData = "";
			vm.tGameStore.detail = UM.getEditor('detail_product').getContent();
			$('.kv-file-content').each(function(){
				imgData += $(this).find('img')[0].src+"$";
			});
			vm.tGameStore.imgData = imgData;
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tGameStore),
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
				    url: "../tgamestore/delete",
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
			$.get("../tgamestore/info/"+id, function(r){
				vm.tGameStore = r.tGameStore;
				var ue = UM.getEditor('detail_product');
				//初始化编辑器里的内容
	            ue.ready(function() {
	    		    ue.setContent(vm.tGameStore.detail);
	    		});
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'name': vm.q.name,"productType":vm.q.productType},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
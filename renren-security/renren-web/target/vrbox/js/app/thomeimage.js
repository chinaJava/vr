$(function () {
    $("#jqGrid").jqGrid({
        url: '../thomeimage/list',
        datatype: "json",
        colModel: [			
			/*{ label: 'id', name: 'id', index: 'ID', width: 50, key: true },*/
			{ label: '所属页面', name: 'typeName', width: 30},
			{ label: '页面地址', name: 'path', index: 'PATH', width: 80, formatter: pathFormatter}, 			
			{ label: '图片', name: 'picUrl', index: 'PIC_URL', width: 80 ,formatter:picFormatter }, 
			{ label: '显示顺序', name: 'index', index: 'INDEX', width: 20 }, 
			{ label: '是否显示', name: 'status', index: 'STATUS', width: 20, formatter: function(value){
				return value == '1' ? 
						'<span class="label label-success">是</span>' : 
						'<span class="label label-danger">否</span>';
				} },			
			{ label: '是否banner图', name: 'isBanner', index: 'IS_BANNER', width: 20, formatter: function(value){
				return value == '1' ? 
						'<span class="label label-danger">是</span>' : 
						'<span class="label label-success">否</span>';
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
    
    getGameType();
    
});


// 设置a标签跳转地址:路径前加http可以不让a标签自定义添加前缀
function pathFormatter(cellvalue, options, rowdata){
	if(rowdata.path!=null){
		return '<a href="'+ rowdata.path+'" target="_blank">查看页面</a>';
	}else{
		return "";
	}
}
// 图片回显
function picFormatter(cellvalue, options, rowdata) {
	if(rowdata.picUrl!=null){
		var str_html = "";
		var urls = rowdata.picUrl.split(";")
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

// 填充类型下拉框
function getGameType(){
	$("#typeName").empty();
	$("#gameType_query").empty();
	$.get("../tgametype/getAllList", function(result){
		var json = eval(result.parentGameTypeList)  
		//console.log(json)
		$("#typeName").append("<option value=''>请选择页面类型</option>");
		$("#gameType_query").append("<option value=''>请选择页面类型</option>");
		for(var i=0; i<json.length; i++){
			$("#typeName").append("<option value='"+json[i].id+"'>"+json[i].name+"</option>");
			$("#gameType_query").append("<option value='"+json[i].id+"'>"+json[i].name+"</option>");
		}
		$("#typeName").append("<option value='0'>推荐页面</option>");
		$("#gameType_query").append("<option value='0'>推荐页面</option>");
	});
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			typeId:"",
			isBanner:""
		},
		showList: true,
		title: null,
		tHomeImage: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tHomeImage = {};
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
			var url = vm.tHomeImage.id == null ? "../thomeimage/save" : "../thomeimage/update";
			$("ul").each(function(){
			    var y = $(this).children().last();
			    vm.tHomeImage.gameId=y.val();
			});
			var imgData = "";
			$('.kv-file-content').each(function(){
				imgData += $(this).find('img')[0].src+"$";
			})
			vm.tHomeImage.imgData = imgData;
			
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tHomeImage),
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
				    url: "../thomeimage/delete",
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
			$.get("../thomeimage/info/"+id, function(r){
                vm.tHomeImage = r.tHomeImage;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{"typeId":vm.q.typeId,"isBanner":vm.q.isBanner},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
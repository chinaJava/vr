$(function () {
    $("#jqGrid").jqGrid({
        url: '../tpointgoods/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'ID', width: 50, key: true,hidden:true },
			{ label: '商品图标', name: 'logoUrl', index: 'LOGO_URL', width: 40,formatter:picFormatter }, 			
			{ label: '商品名称', name: 'goodsName', index: 'GOODS_NAME', width: 80 }, 			
			{ label: '商品说明', name: 'remark', index: 'REMARK', width: 80 }, 			
			{ label: '兑换积分', name: 'exchangePoints', index: 'EXCHANGE_POINTS', width: 40 }, 			
			{ label: '总库存', name: 'totalNum', index: 'TOTAL_NUM', width: 40 }, 			
			{ label: '剩余库存', name: 'remainNum', index: 'REMAIN_NUM', width: 40 }, 			
			{ label: '状态', name: 'status', index: 'STATUS', width: 40,formatter: function(value, options, row){
				return value === '0' ? 
						'<span class="label label-danger">已下架</span>' : 
						'<span class="label label-success">正常</span>';
				} 
			}			
			//{ label: '', name: 'createtime', index: 'CREATETIME', width: 80 }			
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

function picFormatter(cellvalue, options, rowdata) {
	if(rowdata.logoUrl!=null){
		return '<a href="'+rowdata.logoUrl+'" target="_blank"><img src="'+rowdata.logoUrl+'" style="width:50px;height:50px;-moz-border-radius: 10px;-webkit-border-radius: 10px;border-radius:10px;" /></a>';
	}else{
		return "";
	}
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		tPointGoods: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tPointGoods = {};
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
			var url = vm.tPointGoods.id == null ? "../tpointgoods/save" : "../tpointgoods/update";
			if($('.cropped').find('img').length){
				//logoImgData:游戏图标二进制流数据
				vm.tPointGoods.logoImgData = $('.cropped').find('img')[0].src;
			}
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tPointGoods),
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
				    url: "../tpointgoods/delete",
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
			$.get("../tpointgoods/info/"+id, function(r){
                vm.tPointGoods = r.tPointGoods;
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
$(function () {
    $("#jqGrid").jqGrid({
        url: '../tgamearticle/list',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'id', index: 'ID', width: 10, key: true},
			{ label: '游戏名称', name: 'gameName', width: 20 }, 		
			{ label: '标题', name: 'title', index: 'TITLE', width: 80 }, 			
			{ label: '副标题', name: 'subheading', index: 'SUBHEADING', width: 50 }, 			
			{ label: '作者', name: 'author', index: 'AUTHOR', width: 20 }, 			
			{ label: '日期', name: 'createtime', index: 'CREATETIME', width: 25 }, 			
			{ label: '浏览量', name: 'pageViews', index: 'PAGE_VIEWS', width: 20 }, 			
			//{ label: '内容', name: 'content', index: 'CONTENT', width: 200 }, 			
			{ label: '文章类型', name: 'typeName', width: 20 } ,			
			/*{ label: '游戏名称', name: 'gameName', width: 20 } ,	*/
			{ label: '查看详情', name: '', index: 'operate', width: 15,
	            formatter: function (cellvalue, options, rowObject) {
	                var detail="<input type='button' value='详情' class='btn btn-info'  onclick='articleDetail("+ rowObject.id +")' />";
	                return detail;
	            }
			}
			//{ label: '图片', name: 'picUrl', index: 'PIC_URL', width: 80 }			
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
    getArticleType();

    /*联想输入*/  
   	gameList($('#autocomplete'),$('.list-group').eq(0))
   	gameList($('#game-list'),$('.list-group').eq(1))
   	
});

function gameList(obj,list){
	obj.keyup(function(){
		var val = $(this).val();
		list.html(val);
		$.ajax({
			type:"GET",
			url:"../tgameinfo/queryGameName",
			data: {'gameName':val},
			success:function(data){
				var arr = data.tGameInfoList;
				if(arr){
					list.show();
					for(var i=0;i<arr.length;i++){		
						list.append($('<li data-id='+arr[i].id+' value='+arr[i].id+' class="list-group-item">'+arr[i].name+'</li>'));
					}
					var items = list.find('li');
					items.on('mouseover click',function(){
						obj.val($(this).html()).attr('data-id',$(this).attr('data-id'));
					});
					items.on('click',function(){
						list.hide();
					});
				}else{
					list.hide();
				}
			}
		});
	})
}  

function getArticleType(){
	$("#articleType_query option").remove();
	$("#articleType_add option").remove();
	$.get("../tarticletype/getArticleTypeList", function(result){
		var json = eval(result.articleTypeList)
		$("#articleType_query").append("<option value=''>请选择文章类型</option>");
		for(var i=0; i<json.length; i++){
			$("#articleType_query").append("<option value='"+json[i].id+"'>"+json[i].name+"</option>");
			$("#articleType_add").append("<option value='"+json[i].id+"'>"+json[i].name+"</option>");
		}
	});
}

function articleDetail(id){
	$("#submit_button").hide();
	vm.showList = false;
    vm.title = "修改";
    vm.getInfo(id)
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			title: null,
			typeid: null,
			gameid: null
		},
		showList: true,
		title: null,
		tGameArticle: {}
		
	},
	methods: {
		query: function () {
			//获取data-id的属性值。。。赋值入参gameid
			vm.q.gameid =$("#autocomplete").attr('data-id');
			console.log("gameid=="+vm.q.gameid);
			vm.reload();
		},
		add: function(){
			$("#submit_button").show();
			vm.showList = false;
			vm.title = "新增";
			vm.tGameArticle = {};
			var ue = UM.getEditor('container');
            ue.ready(function() {
    		    ue.setContent("请输入内容...");
    		});
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			$("#submit_button").show();
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.tGameArticle.id == null ? "../tgamearticle/save" : "../tgamearticle/update";
			
			$("ul").each(function(){
				    var y = $(this).children().last();
				    vm.tGameArticle.gameid=y.val();
			});
			var gameid=$("#game-list").attr('data-id');
			if(gameid==null || gameid==''){
				alert("请输入正确的游戏名");
				return ;
			}
			vm.tGameArticle.gameid = gameid;
			vm.tGameArticle.content = UM.getEditor('container').getContent();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tGameArticle),
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
				    url: "../tgamearticle/delete",
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
			$.get("../tgamearticle/info/"+id, function(r){
                vm.tGameArticle = r.tGameArticle;
                var ue = UM.getEditor('container');
                ue.ready(function() {
        		    ue.setContent(vm.tGameArticle.content);
        		});
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'title': vm.q.title,"typeid":vm.q.typeid,"gameid":vm.q.gameid},
                page:1
            }).trigger("reloadGrid");
		}
	}
});
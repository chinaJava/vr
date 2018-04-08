//获取url参数
	var url = window.location;
	var taskId = getToken(url, "taskId");
	var token = getToken(url, "token");
	var state  = getToken(url,"state");
	function getToken(url, token){
			url = url+ "";
            regstr = "/(\\?|\\&)" + token + "=([^\\&]+)/";
            reg = eval(regstr);//eval可以将 regstr字符串转换为 正则表达式
            result = url.match(reg);
            if (result && result[2]) {
                return result[2];
            }
	}
	
	//获取当前手机类型
	var sUserAgent = navigator.userAgent.toLowerCase();
	var isIphone = sUserAgent.match(/iphone/i) == "iphone";
	var isAndroid = sUserAgent.match(/android/i) == "android";
		//返回app登录界面
	var login = function goLogin(){
		if(isAndroid){
			android.gotoLogin();
		}
		if(isIphone){
			loginWay('sss');
		}
	}
	//返回app界面
	function back(){
		if(isAndroid){
			android.finishActivity();
		}
		if(isIphone){
			backpage();
		}
	}
	//下载中心
	function gotoDownLoad(){
		if(isAndroid){
			android.gotoDownLoad();
		}
		if(isIphone){
			loginWay('sss');
		}
	}
	
	function getCookie() {
		var arr, reg = new RegExp("(^| )" + "taskId" + "=([^;]*)(;|$)");
		if(arr = document.cookie.match(reg)) {
			//		        return (arr[2]);
			//记录active状态
			myID = arr[2];
			if(myID == taskId){
				$(".submit").text("开始游戏");
			}
		} else {
			return null;
		}
	}
	getCookie();
	
$(function(){
	if(isIphone){
		$("#header").remove();
		$("#container").css("padding-top","0")
	}
	
	//声明vue
	var vm = new Vue({
		el:"#container",
		data:{
			taskDetails:null,
			gameUrl:null,
			downloadingIosUrl:""
		},
		created:function(){
			//判断改变游戏状态
			if(state&&isAndroid){
				switch(parseInt(state)){
				case 1:
					$(".submit").text("下载游戏(免费)");
					$(".submit").css("background","#1f86f5");
					break;
				case 2:
					$(".submit").text("开始游戏(免费)");
					$(".submit").css("background","#30c41c");
					break;
				case 3:
					$(".submit").text("继续下载(免费)");
					$(".submit").css("background","#1f86f5");
					break;
				case 4:
					$(".submit").text("暂停下载(免费)");
					$(".submit").css("background","#1f86f5");
					break;
				case 5:
					$(".submit").text("安装游戏(免费)");
					$(".submit").css("background","#FD6602");
					break;
				case 6:
					$(".submit").text("更新游戏(免费)");
					$(".submit").css("background","#25cdbf");
					break;
				default:
					alert("传入的statd错误");
				}
			}else{
				//判断状态是否领取
				var params= JSON.stringify({});
				$.ajax({
					type:"post",
					url:"../api/tgametask/getMyTaskList",
					cache:false,
					async:true,
					headers:{"token":token},
					contentType:"application/json",
					data:params,
					success:function(msg){
						//json数据转换
						var json = JSON.parse(msg);
						//成功
						if(json.code == 200){
							var data = json.page.list;
							console.log(data)
							for(var j=0;j<data.length;j++){
								if(taskId == data[j].id){
										$(".submit").text("下载游戏(免费)");
										$(".submit").css("background","#1f86f5");
								}
							}
						}
					},
				});
			}
			
			//初始化请求数据
			$.ajax({
					type:"get",
					url:"../api/tgametask/detail/"+taskId+"",
					cache:false,
					async:true,
					success:function(msg){
						//json数据转换
						var json = JSON.parse(msg);
//						var json = msg;
						console.log(json)
						//成功
						if(json.code === 200){
							vm.taskDetails = json.tGameTask;
							//查询是否h5小游戏
							$.ajax({
								type:"get",
								url:"../api/tgameinfo/info/"+vm.taskDetails.gameid+"",
								cache:false,
								async:true,
								success:function(msg){
									//json数据转换
									var json = JSON.parse(msg);
//									var json = msg;
									console.log(json)
									//成功
									if(json.code === 200){
										vm.gameUrl = json.tGameInfo.gameUrl;
										vm.downloadingIosUrl = json.tGameInfo.downloadingIosUrl;
									}
								}
							});
							$(".task-main").append($(json.tGameTask.detail));
							$(".game-main").append($(json.tGameTask.gameIntroduce));
						}else{
							//判断错误情况
							switch(json.code){
							case 500:
								alert(json.msg);
								break;
							default:
								alert("发生未知错误");
							}
						}
					},
					error:function(){
						alert("服务器连接失败，请检查网络");
					}
			});
			
		},
		methods:{
			changeTask:function(){
				$(".game-show").removeClass("active");
				$(".task-show").addClass("active");
				$(".game-main").css("display","none");
				$(".task-main").css("display","block");
			},
			changeGame:function(){
				$(".task-show").removeClass("active");
				$(".game-show").addClass("active");
				$(".task-main").css("display","none");
				$(".game-main").css("display","block");
			},
			goHelp:function(){
				if(isAndroid){
					android.gotoFeedBack();
				}
				if(isIphone){
					questionFeedback();
				}
			},
			submit:function(){
				if($(".submit").text()=="开始游戏"&&vm.gameUrl){
					document.cookie = "taskId=" + taskId;
					window.location = vm.gameUrl;
				}
				if(token){
					if ($(".submit").text()=="领取任务") {
						var data = JSON.stringify({"taskId": taskId});
						//领取任务
						$.ajax({
								type:"post",
								url:"../api/tgametask/takeTask",
								cache:false,
								async:true,
								headers:{"token":token},
								contentType:"application/json",
								data:data,
								success:function(msg){
									//json数据转换
									var json = JSON.parse(msg);
									
									//成功
									if(json.code == 200){
										$('#background').css("display","block");
										$("body,html").css("overflow","hidden");
									}else{
										//判断错误情况
											alert(json.msg);
									}
								},
								error:function(){
									alert("服务器连接失败，请检查网络");
								}
						});
					}else if(state&&isAndroid){
							switch(parseInt(state)){
							case 1:
								if(isAndroid){
									android.downLoadGame();
								}
								break;
							case 2:
								if(isAndroid){
									android.starGame();
								}
								break;
							case 3:
								if(isAndroid){
									android.pauseDown();
								}
								break;
							case 4:
								if(isAndroid){
									android.resumeDown();
								}
								break;
							case 5:
								if(isAndroid){
									android.install();
								}
								break;
							case 6:
								if(isAndroid){
									android.update();
								}
								break;
							default:
								alert("传入的statd错误");
							}
						}else if(isIphone){
							if(vm.gameUrl){
								window.location = vm.gameUrl;
							}else{
								//iphone操作   去往苹果商店
								OpenONAppStore(vm.downloadingIosUrl);
							}
							
							
						}
				}else{
					alert("请登录",login)
				}
				
			},
			doAfter:function(){
				state = 1;
				if(vm.gameUrl){
					$('#background').hide();
					$(".submit").text("开始游戏");
					$("body,html").css("overflow","auto");
				}else{
					$('#background').hide();
					$(".submit").text("下载游戏(免费)");
					$(".submit").css("background","#1f86f5");
					$("body,html").css("overflow","auto");
				}
				
			},
			install:function(){
				state = 1;
				if(vm.gameUrl){
					$('#background').hide();
					$(".submit").text("开始游戏");
					$("body,html").css("overflow","auto");
					document.cookie = "taskId=" + taskId;
					window.location = vm.gameUrl;
				}else{
					$('#background').hide();
					$(".submit").text("下载游戏(免费)");
					$(".submit").css("background","#1f86f5");
					$("body,html").css("overflow","auto");
					if(isAndroid){
						android.downLoadGame();
					}
					if(isIphone){
						OpenONAppStore(vm.downloadingIosUrl);
					}
				}
				
			}
			
		}
	})
	
	vm.changeTask();
})
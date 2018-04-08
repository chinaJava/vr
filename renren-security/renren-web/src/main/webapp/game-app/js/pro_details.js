//获取url参数
	var url = window.location;
	var productId = getToken(url, "productId");
	var token = getToken(url, "token");
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
	//选择收货地址
	function	selectAddress(obj){
			$(".address-list li").not($(obj)).removeClass("active");
			$(obj).addClass("active");
	}
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
	
//声明vue
	var vm = new Vue({
		el:"#container",
		data:{
			"tGameStore":null,
			"addressInfo":null
		},
		created:function(){
			if(isIphone){
				$("#header").remove();
				$("#container").css("padding-top","0");
			}
			//初始化请求产品数据
			$.ajax({
					type:"get",
					url:"../api/tgamestore/detail/"+productId+"",
					cache:false,
					async:true,
					success:function(msg){
						//json数据转换
						var json = JSON.parse(msg);
//						var json = msg;
						
						//成功
						if(json.code === 200){
							vm.tGameStore = json.tGameStore;
//							$(".address-list li").eq(0).addClass("active");
						}else{
							//判断错误情况
							switch(json.code){
							case 500:
								alert("获取商品信息失败");
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
			//初始化请求收货地址数据
			$.ajax({
				type:"post",
				url:"../api/tappuseraddress/info",
				headers:{"token":token},
				cache:false,
				async:true,
				success:function(msg){
					//json数据转换
					var json = JSON.parse(msg);
//					var json = msg;
					console.log(json)
					//成功
					if(json.code === 200){
						vm.addressInfo = json.tAppuserAddressList;
//						$(".address-list li:first").addClass("active");
					}else{
						//判断错误情况
//							alert(json.msg);
					}
				},
				error:function(msg){
					alert("服务器连接失败，请检查网络");
				}
			});
		},
		methods:{
			address:function(obj){
				$(".address-list li").not($(event.currentTarget)).removeClass("active");
				$(event.currentTarget).addClass("active");
			},
			submitPro:function(){
				alert(token)
				if(token){
					//判断是否有收货地址信息
					var lis = $(".address-list li");
					if(lis.length<1){
						alert("请先完善收货信息")
						return;
					}
					//点击兑换商品
					if($(".submit").text()=="兑换"){
						//限制按钮
						$(".submit").text("兑换中");
						$(".submit").css("background","#999999");
						var data=JSON.stringify({
							"addressId": $(".address-list .active").attr("data-id"),
							"productAmount": 1,
							"productId": vm.tGameStore.id,
							"productName": vm.tGameStore.name
						});
						$.ajax({
							type:"post",
							url:"/api/tgamestore/createOrder",
							headers:{"token":token},
							cache:false,
							data:data,
							contentType:"application/json",
							async:false,
							success:function(msg){
								//json数据转换
								var json = JSON.parse(msg);
								console.log(json)
								//成功
								if(json.code === 200){
									alert("兑换成功")
									//限制按钮
									$(".submit").text("兑换");
									$(".submit").css("background","#62cb15")
								}else{
									//判断错误情况
										alert(json.msg);
										//限制按钮
										$(".submit").text("兑换");
										$(".submit").css("background","#62cb15")
								}
							},
							error:function(msg){
								alert("服务器连接失败，请检查网络");
								//限制按钮
								$(".submit").text("兑换");
								$(".submit").css("background","#62cb15")
							}
						});
					}
				}else{
					alert("请登录",login)
				}
			}
		}
	})
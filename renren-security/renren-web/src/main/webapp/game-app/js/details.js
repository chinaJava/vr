var page = 0;
var onoff = true;
var timer = null;

//获取当前手机类型
var sUserAgent = navigator.userAgent.toLowerCase();
var isIphone = sUserAgent.match(/iphone/i) == "iphone";
var isAndroid = sUserAgent.match(/android/i) == "android";

//当顶部高度滑动超过一半，引发过渡效果
window.addEventListener("scroll",function(){
	var scrollTop=document.body.scrollTop||document.documentElement.scrollTop;
	if(scrollTop>=100){
          document.querySelector("#head_game_name").className="show1";
          document.querySelector("#head_game_name1").className="show2";
	}else{
          document.querySelector("#head_game_name").className="head_game_name";
          document.querySelector("#head_game_name1").className="head_game_name1";
	}
});
//返回登录界面
var login = function goLogin(){
	if(isAndroid){
		android.gotoLogin();
	}
	if(isIphone){
		loginWay('sss');
	}
}
//获取缓存的cookie
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

//获取url
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


//推荐游戏跳转
var gameTitle = "";
function goApp(gameId,obj){
	if(isAndroid){
		android.reloadGame(gameId);
	}
	if(isIphone){
//		window.location = "http://192.168.1.110/game-app/details.html?id="+gameId+"&token="+getToken(url, "token")+"";
		//$(obj).children("p").text()  游戏名称
		getGameID(gameId,$(obj).children("p").text());
	}
}

//重写alert
window.alert = function(msg,callback) {
		    var div = document.createElement("div");  
		    div.innerHTML = "<style type=\"text/css\">"  
		            + ".nbaMask { position: fixed; z-index: 1000; top: 0; right: 0; left: 0; bottom: 0; background: rgba(0, 0, 0, 0.5); }                                                                                                                                                                       "  
		            + ".nbaMaskTransparent { position: fixed; z-index: 1000; top: 0; right: 0; left: 0; bottom: 0; }                                                                                                                                                                                            "  
		            + ".nbaDialog { position: fixed; z-index: 5000; width: 80%; max-width: 300px; top: 50%; left: 50%; -webkit-transform: translate(-50%, -50%); transform: translate(-50%, -50%); background-color: #fff; text-align: center; border-radius: 8px; overflow: hidden; opacity: 1; color: white; }"  
		            + ".nbaDialog .nbaDialogHd { padding: .2rem .27rem .08rem .27rem; }                                                                                                                                                                                                                         "  
		            + ".nbaDialog .nbaDialogHd .nbaDialogTitle { font-size: 17px; font-weight: 400; }                                                                                                                                                                                                           "  
		            + ".nbaDialog .nbaDialogBd { padding: 1rem; font-size: 15px; line-height: 1.3; word-wrap: break-word; word-break: break-all; color: #000000; }                                                                                                                                          "  
		            + ".nbaDialog .nbaDialogFt { position: relative; line-height: 48px; font-size: 17px; display: -webkit-box; display: -webkit-flex; display: flex; }                                                                                                                                          "  
		            + ".nbaDialog .nbaDialogFt:after { content: \" \"; position: absolute; left: 0; top: 0; right: 0; height: 1px; border-top: 1px solid #e6e6e6; color: #e6e6e6; -webkit-transform-origin: 0 0; transform-origin: 0 0; -webkit-transform: scaleY(0.5); transform: scaleY(0.5); }               "  
		            + ".nbaDialog .nbaDialogBtn { display: block; -webkit-box-flex: 1; -webkit-flex: 1; flex: 1; color: #09BB07; text-decoration: none; -webkit-tap-highlight-color: transparent; position: relative; margin-bottom: 0; }                                                                       "  
		            + ".nbaDialog .nbaDialogBtn:after { content: \" \"; position: absolute; left: 0; top: 0; width: 1px; bottom: 0; border-left: 1px solid #e6e6e6; color: #e6e6e6; -webkit-transform-origin: 0 0; transform-origin: 0 0; -webkit-transform: scaleX(0.5); transform: scaleX(0.5); }             "  
		            + ".nbaDialog a { text-decoration: none; -webkit-tap-highlight-color: transparent; }"  
		            + "</style>"  
		            + "<div id=\"dialogs2\" style=\"display: none\">"  
		            + "<div class=\"nbaMask\"></div>"  
		            + "<div class=\"nbaDialog\">"  
		            + " <div class=\"nbaDialogHd\">"  
		            + "     <strong class=\"nbaDialogTitle\"></strong>"  
		            + " </div>"  
		            + " <div class=\"nbaDialogBd\" id=\"dialog_msg2\">弹窗内容，告知当前状态、信息和解决方法，描述文字尽量控制在三行内</div>"  
		            + " <div class=\"nbaDialogHd\">"  
		            + "     <strong class=\"nbaDialogTitle\"></strong>"  
		            + " </div>"  
		            + " <div class=\"nbaDialogFt\">"  
		            + "     <a href=\"javascript:;\" class=\"nbaDialogBtn nbaDialogBtnPrimary\" id=\"dialog_ok2\">确定</a>"  
		            + " </div></div></div>";  
		    document.body.appendChild(div);  
		  
		    var dialogs2 = document.getElementById("dialogs2");  
		    dialogs2.style.display = 'block';  
		  
		    var dialog_msg2 = document.getElementById("dialog_msg2");  
		    dialog_msg2.innerHTML = msg;  
		  
		    // var dialog_cancel = document.getElementById("dialog_cancel");  
		    // dialog_cancel.onclick = function() {  
		    // dialogs2.style.display = 'none';  
		    // };  
		    var dialog_ok2 = document.getElementById("dialog_ok2");  
		    dialog_ok2.onclick = function() {  
		        dialogs2.style.display = 'none';  
		        if(callback){
		        	callback();
		        }
		        
		    };  	
}



$(function(){
	//隐藏游戏攻略
	$(".pro-strategy").hide;
//	$.cookie('token','f70d0884-e931-4a84-a64f-44ed7a1b41c7');
//	$.cookie('token','18fb32ca-5c1b-49e7-919d-58462f937813');
	$('#game_app').html('<section class="game_head"><div id="head_game_name" class="head_game_name"></div><figure class="head_details"></figure><div class="head_tab"><a class="head_active pro-show">游戏介绍</a><a class="pro-strage">攻略</a></div></section><section id="game_ban" class="gamebg_banner show"><div class="swiper-container"><div class="swiper-wrapper"></div></div></section><article class="game_paper show"><div class="paper_title"><h3>内容摘要</h3></div><p class="paper_show"></p><div class="paper_open"><div><span>展开</span><img src="img/icon_sre.png" /></div></div><div class="paper_notice"><img src="img/nav_icon03.png" /><div><ul></ul></div></div></article><article class="game_related"><div class="related_title"><img src="img/icon1.png" /><h3>游戏相关</h3></div><ul class="related_list"></ul></article><section class="game_label show"><div class="lable_title"><img src="img/icon_label.png" /><h3>游戏标签</h3></div><nav></nav></section><section class="game_comments show"><div class="comments_title"><div><img src="img/comments1.png" /><h3>用户评论</h3></div><a href="#release">评论</a></div><div class="all_comments"><p class="num_coms"><span></span>条评论</p></div></section><section class="more_comments show"><a>点击查看更多评论</a><div id="release" class="release"><div><img src="img/comments2.png" /><input type="text" placeholder="我也评论两句" /></div><span>发布</span></div></section><section class="game_recommended show"><div class="related_title"><img src="img/icon.png" /><h3>游戏推荐</h3></div><section id="game_recommend" class="gamebg_banner show"><div class="swiper-container"><div class="swiper-wrapper"></div></div></section></section><section class="pro-strategy"><ul class="strategy-list"><li><img src="img/news.png"/><h1>游戏资讯</h1><p>当前共收录：6篇</p></li><li><img src="img/strategy.png"/><h1>游戏攻略</h1><p>当前共收录：9篇</p></li><li><img src="img/evaluating.png"/><h1>游戏评测</h1><p>当前共收录：8篇</p></li></ul></section><footer><a class="download">下载（免费）</a></footer>')
	
	var curWwwPath=window.document.location.href;  
	var pathName=window.document.location.pathname;  
	var pos=curWwwPath.indexOf(pathName);  
    var index = pathName.substr(1).indexOf("/");   
    var result = pathName.substr(0,index+1);
    var localhostPath=curWwwPath.substring(0,pos);  
    if(result!="/game-app"){
    	localhostPath += result;
    }
    
    //点击切换游戏介绍和游戏攻略
    $(".pro-show").click(function(){
    	$(".pro-strage").removeClass("head_active");
    	$(".pro-show").addClass("head_active");
    	$("html,body").css("background","#eeeeee");
    	$("footer").removeClass("foot");
//    	$(".pro-strategy").hide();
    	$(".related_list").removeClass("article");
    	$(".related_title").show();
    	$(".show").show();
    })
    $(".pro-strage").click(function(){
    	$(".pro-show").removeClass("head_active");
    	$(".pro-strage").addClass("head_active");
    	$("html,body").css("background","#ffffff");
    	$("footer").addClass("foot");
    	$(".show").hide();
    	$(".related_list").addClass("article");
    	$(".related_title").hide();
//    	$(".pro-strategy").show();
    })
    
	
    //获取url信息
   
    var state  = getToken(url,"state");
    var token  = "";
	
	//判断改变游戏状态
	if(state&&isAndroid){
		switch(parseInt(state)){
		case 1:
			$(".download").text("下载游戏(免费)");
			$(".download").css("background","#1f86f5");
			break;
		case 2:
			$(".download").text("开始游戏(免费)");
			$(".download").css("background","#30c41c");
			break;
		case 3:
			$(".download").text("继续下载(免费)");
			$(".download").css("background","#1f86f5");
			break;
		case 4:
			$(".download").text("暂停下载(免费)");
			$(".download").css("background","#1f86f5");
			break;
		case 5:
			$(".download").text("安装游戏(免费)");
			$(".download").css("background","#FD6602");
			break;
		case 6:
			$(".download").text("更新游戏(免费)");
			$(".download").css("background","#25cdbf");
			break;
		default:
			alert("传入的statd错误");
		}
	}
	
    
	allComments();
	//游戏banner图
	/*$.ajax({
		type:"get",
		url:"/api/tgamepic/queryPicByGameId?gameId="+gameid('id'),
		success:function(data){
			var arr = (new Function("return"+data))();
			var data = arr.gamePictures;
			if(data){
				for(var i=0;i<data.length;i++){
					$('#game_ban .swiper-wrapper').append($('<div class="swiper-slide"><img src='+data[i].picUrl+' /></div>'));			
				}
				var swiper = $('.swiper-container').eq(0);
				banner(swiper,2);
			}	
		}
	});*/
	
	var gameUrl = "";
	var downloadingIosUrl = "";
	//游戏详情
	$.ajax({
		type:"get",
		async:true,
		url:localhostPath+'/api/tgameinfo/info/'+gameid('id'),
		success:function(data){
			var arr = (new Function("return"+data))();
			var data = arr.tGameInfo;
			console.log(data)
			gameUrl = data.gameUrlH5;
			downloadingIosUrl = data.downloadingIosUrl;
			gameTitle = data.name;
			if(gameUrl){
				$(".download").text("开始游戏(免费)");
			}
			var str = arr.tGameInfo.gameLable;
			var s = str.substring(0,str.lastIndexOf(','));
			var label = s.split(',');
			var paper = arr.tGameInfo.introduce;
			//var p = paper.replace(/<.*?>/ig,"");
			var video = arr.tGameInfo.gameVideoUrl;
			var starLevel = Math.floor(data.starLevel);
			console.log(starLevel)
			var nav = $('nav');
			for(var i=0;i<label.length;i++){
				var r = parseInt(Math.random()*255);
			    var g = parseInt(Math.random()*255);
			    var b = parseInt(Math.random()*255);
				nav.append($('<a data-set='+label[i]+'>'+label[i]+'</a>').css({color:'rgb('+r+','+g+','+b+')'}));
			}
			if(isIphone){
				var mobile = Math.floor(data.downloadingIosNum);
				var version = data.versionIos;
				var size = data.sizeIos;
				console.log(version)
			}else{
				var mobile = Math.floor(data.downloadingAndroidNum);
				var version = data.versionAndroid;
				var size = data.sizeAndroid;
			}
			size = size/(1024*1024);
			var details = $('.head_details');
			details.html('<img src='+data.logoUrl+'><div class="head_title"><h3 id="head_game_name1" class="head_game_name1">'+data.name+'</h3><p><span>'+data.language+'丨</span><span>'+mobile+'下载丨</span><span>'+version+'版本</span></p><div class="head_star"><i></i><span>'+starLevel+'分</span><span>'+size.toFixed(2)+'M</span></div><div>');
			$('.head_details i').css('background-position-y',-starLevel*19.5);
			//$('.paper_show').html(p)
			$('.paper_show').html(paper);
			if(video){
				$('#game_ban .swiper-slide:first').append($('<video type="video/mp4" controls="true"></video>'));
			}
			if(arr.tGameInfo.gamePictureUrl!=null){
				var gamePic_url = arr.tGameInfo.gamePictureUrl.split(";");
				for(var i=0;i<gamePic_url.length;i++){
					$('#game_ban .swiper-wrapper').append($('<div class="swiper-slide"><img src='+gamePic_url[i]+' /></div>'));			
				}
			}
			var swiper = $('.swiper-container').eq(0);
			banner(swiper,2);
		}
	});
	//游戏公告
	$.ajax({
		type:"get",
		url:localhostPath+'/api/tgamearticle/queryAnnouncement?gameId='+gameid('id')+'&typeId=4',
		success:function(data){
			var arr = (new Function("return"+data))();
			var data = arr.gameArticleList;
			var notice = $('.paper_notice ul');
			if(data==''){
				notice.append($('<li>暂无游戏公告</li>'));
				clearInterval(timer);
			}else{
				for(var i=0;i<data.length;i++){
					notice.append($('<li>《'+data[i].gameName+'》'+data[i].content+'</li>'));
				}
				var items = notice.find('li');
				if(items.size()<2){
					clearInterval(timer);
				}
			}
		}
	});
	//游戏文章
	$.ajax({
		type:"get",
		url:localhostPath+'/api/tgamearticle/list?gameId='+gameid('id')+'&typeId=1',
		success:function(data){
			var arr = (new Function("return"+data))();
			var data = arr.gameArticleList;
			var items = $('.related_list');
			if(data==''){
				items.append($('<li>暂无相关游戏文章</li>'));
			}else{
				for(var i=0;i<data.length;i++){
					items.append($('<li><a href="article.html?id='+data[i].id+'"><div><span>'+data[i].typeName+'</span>'+data[i].title+'</div><img src="img/icon_more.png" /></a></li>'));
					var title = $('.related_list span').eq(i);
					var html = title.html();
					if(html=='评测'){
						title.addClass('review');
					}else if(html=='攻略'){
						title.addClass('strategy');
					}
				}
			}
		}
	});
	//游戏推荐banner图
	$.ajax({
		type:"get",
//		url:localhostPath+"/api/tgameinfo/list?page=1&limit=10&isRecommend=1",
		url:localhostPath+"/api/tgameinfo/recommendPage",
		success:function(data){
			console.log(data)
//			var arr = (new Function("return"+data))();
			var data = JSON.parse(data).recommendPage;
			if(data){
				for(var i=0;i<data.length;i++){
					$('#game_recommend .swiper-wrapper').append($('<div class="swiper-slide"><a onclick="goApp('+data[i].gameId+',this)"><img src='+data[i].gameLogoUrl+' /><p>'+data[i].gameName+'</p></a></div>'))
				}
				var swiper1 = $('.swiper-container').eq(1);
				banner(swiper1,5);
			}
		}
	});
	//显示更多评论
	$('.more_comments a').on('touchstart',function(ev){
		ev.preventDefault();
		allComments();
	})
	//发布评论
	$('.more_comments span').on('touchstart',function(ev){
		ev.preventDefault();
		var inputBox = $('.more_comments input');
		var val = inputBox.val();
		postComments(val,0,'发布',inputBox);
	})
	//游戏介绍
	$('.paper_open div').on('touchstart',function(ev){
		ev.preventDefault();
		if(onoff) {
			$(this).find('span').html('收回').next().addClass('fz').parent().parent().prev().removeClass('paper_show');
		} else {
			$(this).find('span').html('展开').next().removeClass('fz').parent().parent().prev().addClass('paper_show');
		}
		onoff = !onoff
	})
	//轮播新闻
	timer = setInterval(function() {
		var notice = $('.paper_notice ul');
		var noticeFirst = $('.paper_notice ul li:first')
		notice.animate({
			marginTop: -4.5 + 'rem'
		}, 500, 'linear', function() {
			notice.css({marginTop: 0});
			noticeFirst.remove().clone().appendTo(notice);
		})
	}, 3000)
	//根据url后面参数获取游戏id
	function gameid(name){
	    var str = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	    var s = window.location.search.substr(1).match(str);
	    if(s!=null)return unescape(s[2]); return null;
	}
	//滑动轮播
	function banner(obj,num){
		new Swiper(obj,{
			slidesOffsetBefore: 15,
			slidesPerView: num,
			spaceBetween: 15,
		})
	}
	//每条主评论
	function creatDiv(pic,name,commend,star,comments,id,createtime){
		if(name ==null)name="匿名用户";
		var newDiv = $('<div data-id='+id+' class="comments_warp"></div>');
		var html = '<div class="list_head"><img src='+pic+'/><div class="list_head_con"><p>'+name+'</p><div><i></i></div></div><a data-id='+id+'>回复</a></div><p class="comments_con">'+comments+'</p><div class="comments_from"><span>来自4399.cn</span><span><img src="img/icon_praise.png"/>'+createtime+'</span><div class="deputy_cs"></div></div>';
		newDiv.html(html);
		//回复主评论弹出框
		var reply = newDiv.find('a');
		var viceDiv = newDiv.find('.deputy_cs');
		reply.on('touchstart', function(ev){
			ev.preventDefault();
			var id = $(this).attr('data-id');
			var oidId=$('.more_deputy').parent().attr('data-id');
			$('.more_deputy').remove();
			if(oidId!=id){
				$(this).parent().parent().append(deputyDiv(id));
			}
			
		})	
		var jc = newDiv.find('.list_head_con').find('p');
		if(commend==1){
			jc.append($('<span>精彩评论</span>'));
		}
		var starDiv = newDiv.find('i');
		starDiv.css('background-position-y',star*-19.5);
		deputyComments(id,viceDiv);
		return newDiv;
	}
	//副评论对应某条主评论
	function deputyComments(id,obj){
		$.ajax({
			type:"get",
			url:localhostPath+'/api/tgamecomment/list?page=1&limit=5&gameId='+gameid('id')+'&pId='+id+'',
			success:function(data){
				var arr = (new Function("return"+data))();
				var data = arr.page.list;
				for(var i=0;i<data.length;i++){
					if(data[i].realname ==null)data[i].realname="匿名用户";
					obj.append($('<div class="comments_list"><span>'+data[i].realname+'：</span>'+data[i].comment+'</div>'));	
				}
				var html = obj.html();
				if(!html){
					obj.remove();
				}else{
					if(data.length>4){
						obj.append(replyDiv());
					}
				}
			}
		});		
	}
	//加载更多对应主评论的副评论
	function replyDiv(){
		var newDiv = $('<div class="more_list">点击查看更多回复</div>');
		var page1 = 1;
		newDiv.on('touchstart', function(ev){
			ev.preventDefault();
			page1++;
			var id = $(this).parent().parent().parent().attr('data-id');
			$.ajax({
				type:"get",
				url:localhostPath+'/api/tgamecomment/list?page='+page1+'&limit=5&gameId='+gameid('id')+'&pId='+id,
				beforeSend:function(){
					newDiv.html('评论加载中...');
				},
				success:function(data){
					var arr = (new Function("return"+data))();
					var data = arr.page.list;
					newDiv.html('点击查看更多回复');
					for(var i=0;i<data.length;i++){
						newDiv.before($('<div class="comments_list"><span>'+data[i].realname+'：</span>'+data[i].comment+'</div>'));
					}	
					if(data.length<5){
						newDiv.html('没有更多回复了').unbind();
					}		
				}
			});
		})
		return newDiv;
	}
	//回复主评论
	function deputyDiv(id){
		var newDiv = $('<section class="more_deputy"></section>');
		var html = '<div class="release"><div><img src="img/comments2.png"/><input type="text" placeholder="我也回复两句" /></div><span data-id='+id+'>发布</span></div>';
		newDiv.html(html);
		var reply = newDiv.find('span');
		reply.on('touchstart',function(ev){
			ev.preventDefault();
			var inputBox = $('.more_deputy input');
			var val = inputBox.val();
			var id = $(this).attr('data-id');
			postComments(val,id,'回复',inputBox);
			if(val){
				$(this).parent().parent().remove();
			}
		})
		return newDiv;
	}
	//等待审核显示发布和回复的评论
	function postComments(val,id,text,obj){
		if(isAndroid){
			token = android.getToken();
			android.hideKeyBoard();
		}
		if(isIphone){
			token = getToken(url, "token");
		}
		if(!val){
			alert('请输入内容');
			return;
		}
		if(!token){
			alert("请先登录",login)
			return;
		}
		$.ajax({
			type:"post",
			url:localhostPath+"/api/tgamecomment/save",
			headers:{'token':token},
			contentType: "application/json",
			data: JSON.stringify({"comment": val,"gameid":gameid('id'),"pid": id}),
//			data: {"comment": val,"gameid":gameid('id'),"pid": id},
			success:function(data){
				console.log(data)
//				var arr = (new Function("return"+data))();
				arr=JSON.parse(data)
				if(arr.code==200){
					alert('评论成功，等待审核通过即可查看');
				}else{
//					alert(''+text+'失败');
					alert('评论失败,'+arr.msg)
				}
				$(obj).val('');
			},
		});
	}
	//显示所有主评论
	function allComments(){
		page++;
		var moreComments = $('.more_comments a');
		$.ajax({
			type:"get",
			url:localhostPath+'/api/tgamecomment/list?page='+page+'&limit=5&gameId='+gameid('id')+'&pId=0',
			beforeSend:function(){
				moreComments.html('评论加载中...');
			},
			success:function(data){
				var arr = (new Function("return"+data))();
				var data = arr.page.list;
				moreComments.html('点击查看更多评论');
				var numComs = $('.num_coms span');
				numComs.html(arr.page.totalCount);
				var allComs = $('.all_comments');
				for(var i=0;i<data.length;i++){
					var str = data[i].createtime.split(' ');
					var time = str.slice(0,-1);
					allComs.append(creatDiv(data[i].headPic,data[i].realname,data[i].isRecommend,data[i].starLevel,data[i].comment,data[i].id,time));
				}
				var moreComs = $('.more_comments a');
				if(data.length>4){
					moreComs.css('display','block');
				}else{
					moreComs.html('没有更多评论了').unbind();
				}
			}
		});
	}
	//点击下载游戏等操作
	$(".download").click(function(){
		if(gameUrl){
			if(isIphone){
				setTitle(gameTitle)
			}
			window.location = gameUrl;
		}
		if(state&&isAndroid){
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
			//iphone操作   去往苹果商店
			if(!gameUrl){
//				alert("hello")
				setTitle(gameTitle)
				console.log(downloadingIosUrl)
				OpenONAppStore(downloadingIosUrl);
			}
			
		}
	})
	
})

var GM=GM||{apis:{},ajax:{},temp:{},render:{},utils:{},init:{},initPage:{},vars:{}};GM.vars={article_reply_id:"",post_reply_id:"",reply_type:"",GA_TYPE:"",UA:navigator.userAgent,top_img_src:null,top_download_url:null},GM.apis={articleReply:"apis/minisite/article_reply.json",postReply:"apis/group/post_reply.json@retrieve_type=by_post",topAdImg:"apis/flowingboard/item/mguokr_top_ad.json",hotArticle:"apis/flowingboard/item/m_hot_content.json",maddenArticle:"apis/flowingboard/item/hot_madden_content.json"},GM.ajax={articleReply:function(a,b,c){var d=b||0,e=c||10,f={article_id:a,offset:d,limit:e,desc:"false",retrieve_type:"by_article"};$.ajax({type:"get",url:GM.apis.articleReply,data:f,dataType:"json",cache:!0,conmplete:function(a){GM.vars.reply_total=a.total,GM.render.articleReply(a),GM.vars.reply_total>$("#replyList>article").length?$("#loadMoreReply").find("span").show().prev().removeClass("loadMoreAnimate"):$("#loadMoreReply").hide(),$(".cmt-main img").each(function(){this.removeAttribute("style"),this.style.maxWidth="200px"});var b=$(".comment"),c=b.find("embed"),d=b.find("iframe");c.replaceWith("<p>（此处视频，暂不支持播放）</p>"),d.replaceWith("<p>（此处视频，暂不支持播放）</p>")}})},postReply:function(a,b,c){var d=b||0,e=c||10,f={post_id:a,offset:d,limit:e,desc:"false"};$.ajax({type:"get",url:GM.apis.postReply,data:f,dataType:"json",cache:!0,conmplete:function(a){GM.vars.reply_total=a.total,GM.render.articleReply(a),GM.vars.reply_total>$("#replyList>article").length?$("#loadMoreReply").find("span").show().prev().removeClass("loadMoreAnimate"):$("#loadMoreReply").hide(),$(".cmt-main img").each(function(){this.removeAttribute("style"),this.style.maxWidth="200px"});var b=$(".comment"),c=b.find("embed"),d=b.find("iframe");c.replaceWith("<p>（此处视频，暂不支持播放）</p>"),d.replaceWith("<p>（此处视频，暂不支持播放）</p>")}})},getNotifyNum:function(){var a=descriptor,b=GM.utils.cookie("_32353_access_token"),c=$.param({descriptor:a,access_token:b},!0);$.ajax({url:"/apis/community/rn_num.json",type:"GET",dataType:"json",data:c,cache:!1,success:function(a){a.result.n&&GM.utils.hasNotify()}})},topAdImgs:function(){$.ajax({type:"get",url:GM.apis.topAdImg,data:{},dataType:"json",cache:!0,success:function(a){var b=a.result.length;if(b){var c=Math.floor(Math.random()*b),a=a.result[c];GM.vars.top_img_src=a.image,GM.vars.top_download_url=a.url,GM.init.showNavAdvert()}}})},loadHotArticle:function(){var a=Math.floor(74*Math.random()),b="",c="";-1!==document.cookie.indexOf("_is_search")?(b=GM.apis.maddenArticle,c="mobile_madden_content:",_gaq.push(["_trackEvent","loadhot_from_search",window.location.href,"hotStuff"])):(b=GM.apis.hotArticle,c="mobile_hot_content:",_gaq.push(["_trackEvent","load_hot",window.location.href,"hotStuff"])),"article"==GM.vars.GA_TYPE?c+="article":"post"==GM.vars.GA_TYPE?c+="post":"question"==GM.vars.GA_TYPE&&(c+="question"),$.ajax({type:"get",url:b,data:{offset:a,limit:15},dataType:"json",cache:!0,success:function(a){{var a=a.result;a.length}GM.render.hotArticleList(a,c)}})}},GM.temp={articleReply:'<article class="comment"><footer class="cmt-more"><div class="gclear"><address class="cmt-author gfl"><a href="#">{name}</a></address><time class="cmt-time gfr">{time}</time></div><div class="cmt-order">{num}楼</div></footer><p class="cmt-main">{content}</p></article>',hotArticleList:'<li><a href="{item_url}" data-gaevent="{ga_event}"><h4>{item_desc}</h4><div class="title-detail"><img src="{img_src}"><p>{item_summary}</p></div></a></li>',hotArticleListNotFirst:'<li><a href="{item_url}" data-gaevent="{ga_event}"><h4>{item_desc}</h4></a></li>',viewLargeMask:'<div class="mask" id="mask"><div class="mask-top"><a href="javascript:void 0;" class="mask-close"></a></div><div class="mask-box"><img src="{img_src}"><span class="loader-icon"></span></div></div>'},GM.render={articleReply:function(a){{var b="",c=$("#replyList>article").length;a.total}$.each(a.result,function(a){var d=GM.utils.dateFormat(this.date_created),e=(this.author.avatar.normal,c+a+1),f=this.author.nickname,g=this.html;b+=GM.temp.articleReply.replace("{time}",d).replace("{num}",e).replace("{content}",g).replace("{name}",f)}),$("#replyList").append(b)},hotArticleList:function(a,b){var c="",d=a.length,e=d>5?5:d;if(d)for(var f=Math.max(e,d),g=!1,h=0;f>h&&e;h++){var i=a[h],j=i.title;i.title&&i.url&&(!g&&i.image&&i.summary?(c=GM.temp.hotArticleList+c,c=c.replace(/\{item_url\}/gi,i.url).replace("{img_src}",i.image).replace("{item_desc}",j).replace("{item_summary}",i.summary).replace(/\{ga_event\}/gi,b),g=!0):c+=GM.temp.hotArticleListNotFirst.replace(/\{item_url\}/gi,i.url).replace("{item_desc}",j).replace(/\{ga_event\}/gi,b),--e),$("#hotStuff").html(c)}}},GM.utils={dateFormat:function(a){var b=a.slice(0,16).replace("T"," ");return b},dateGet:function(a){return a.slice(5,10)},getGetData:function(a){var b,c,d=document.URL.split("?");if(d[1]){b=d[1].split("&");for(var e=0,f=b.length;f>e;e++)if(c=b[e].split("="),c[0]===a)return c[1];return""}return""},scrollToTop:function(){$.scrollTo({endY:0,duration:500,callback:function(){}})},scrollShow:function(){var a=0,b=!1,c=$("#nav"),d=c.height(),e=c.next();$(window).scroll(function(){var f=$(this).scrollTop();b=0>f-a?1:0,b&&f?(d=c.height(),c.addClass("nav-fixed"),e.css("margin-top",d+"px")):(c.removeClass("nav-fixed"),e.css("margin-top","0")),a=f})},cookie:function(a,b,c){if("undefined"==typeof b){var d=null;if(document.cookie&&""!=document.cookie)for(var e=document.cookie.split(";"),f=0,g=e.length;g>f;f++){var h=e[f].replace(/^\s+/,"").replace(/\s+$/,"");if(h.substring(0,a.length+1)==a+"="){d=decodeURIComponent(h.substring(a.length+1));break}}return d}c=c||{},null==b&&(b="",c.expires=-1);var i,j="";c.expires&&("number"==typeof c.expires?(i=new Date,i.setTime(i.getTime()+24*c.expires*60*60*1e3)):c.expires.toUTCString&&(i=c.expires),j=";expires="+i.toUTCString());var k=c.path?";path="+c.path:";path=/",l=c.domain?";domain="+c.domain:"",m=c.secure?";"+c.secure:"";document.cookie=[a,"=",encodeURIComponent(b),j,k,l,m].join("")},showBySystem:function(){var a=navigator.userAgent.toLowerCase(),b='<div class="spread" id="spread"><a href="'+GM.vars.top_download_url+'" class="top-ad"></a><a href="javascript:void 0;" class="icon-close"></a></div>';(-1!=a.indexOf("iphone os")||-1!=a.indexOf("android"))&&($(b).insertBefore("#nav"),$("#spread .top-ad").css("background-image","url("+GM.vars.top_img_src+")"),$("#spread .icon-close").click(function(){$("#spread").remove(),GM.utils.cookie("_is_closed_adv","true",{expires:7})}))},toggleShowHideNav:function(a,b){$(a).click(function(){$(b).toggle().siblings().hide(),$(this).toggleClass("on").siblings().removeClass("on")})},toggleCoverHideNav:function(){$("#navBtn").click(function(){$("#navMenu").toggle(),$(this).toggleClass("on")})},hasNotify:function(){var a='<span class="nav-msg-dot"></span>';$("#navUserBtn .icon-userbar").html(a),$("#navNotice .icon-notice").html(a)},logOut:function(a){$(a).click(function(a){a.preventDefault();var b={id:"logOut",leftBtn:"注销",txt:"退出当前帐号",leftCb:function(){url_signout&&(window.location=url_signout)}};GM.utils.easyPop(b)})},easyPop:function(a){var b={id:a.id||"pop",leftBtn:a.leftBtn||"确定",rightBtn:a.rightBtn||"取消",txt:a.txt||"",leftCb:a.leftCb||function(){$("#"+b.id).remove()},rightCb:a.rightCb||function(){$("#"+b.id).remove()}},c='<div id="'+b.id+'" class="mask-light">                        <div class="pop-bd">                           <div class="pop-content">'+b.txt+'</div>                           <div class="pop-footer">                            <a href="javascript:void 0;" class="pop-left-btn">'+b.leftBtn+'</a>                            <a href="javascript:void 0;" class="pop-right-btn">'+b.rightBtn+"</a>                            </div>                        </div>                    </div>",d=$(window).height();$("body").append(c);var e=$("#"+b.id+" .pop-bd"),f=e.height();d-f>0&&e.css("margin-top",Math.floor((d-f)/2)),e.delegate(".pop-left-btn","click",function(a){a.preventDefault(),b.leftCb()}),e.delegate(".pop-right-btn","click",function(a){a.preventDefault(),b.rightCb()})},hideRestWord:function(a,b,c,d,e){for(var f=$(a),g=$(window).height()*e,h=!1,i=0,c=c,j='<a class="extend gclear"  href="javascript:" id="showRestBtn"><b class="extend-icon gfl"></b><span class="extend-txt gfl">'+b+"<span></a>",k=f.children(),l=k.length;1===k.size();)k=k.eq(0).children(),l=k.length;if(c>g)f.hide(),$(j).insertBefore(k.find(".content-other").first()),$("body").delegate("#showRestBtn","click",function(){f.show(),$("#showRestBtn").hide()});else{f.css("position","relative");for(var m=0;l>m;m++){var n=k.eq(m);if(h)break;if("br"==n[0].nodeName.toLowerCase()?($("<span></span>").insertBefore(n),i=n.prev().position().top,n.prev().remove()):i=n.position().top,i+c>g){h=!0,n.attr("id","hideTag");var o=$(a).html(),p=/<[^>]+hideTag[^>]+>/,q=o.search(p),r=o.substring(0,q),s=o.substring(q,o.length),t=r.concat('<div id="hideWrap" style="display:none;">',s,"</div>",j);$(a).html(t)}}$(a).delegate("#showRestBtn","click",function(){$("#hideWrap").fadeIn(),$("#showRestBtn").hide()})}},cutName:function(a,b,c){var a=$(a),b=$(b),c=c,d=$("body").width(),e=a.width()+c,f=b.width();if(e>d){var g=f-(e-d);b.css("width",g+"px")}},cutStrLen:function(a,b){for(var b=b,c=0,d="",e=0;e<a.length;e++){if(singleChar=a.charAt(e).toString(),a.charCodeAt(e)>255?c+=2:c++,c>b){d+="...";break}d+=singleChar}return d},isSearchRef:function(){var a=/\.(baidu|google|sogou|soso|youdao|yahoo|bing|jike|so)(\.[a-z0-9\-]+){1,2}\//gi,b=document.referrer,c=a.test(b);return c},tabChange:function(a,b){var c=b.parents(".content-block").find(".tab li");a>=c.length&&(a=0),c.removeClass("current"),c.eq(a).addClass("current")},clickChange:function(a){{var a=a,b=a.parents("section").find(".titles-list li");a.length}a.click(function(){var c=$(this),d=a.index(c),e=a.parent().find(".current"),f=e.index(),g=d-f;if(g>0)for(var h=0;g>h;h++)b.eq(f++).trigger("swipeLeft");else if(0>g)for(var h=0;-g>h;h++)b.eq(f--).trigger("swipeRight");return!1})},swipeChange:function(a,b,c){var d=1,a=a,e=a.parent(),f=a.length,c=c;a.eq(f-1).clone().insertBefore(a.eq(0)),a.eq(0).clone().appendTo(e),a=e.children();var g=a.length,h=$(window).width(),i=h*g,j=0,k=!1,l=!1,m=!0;if(a.css("width",h),e.css({width:i,"-webkit-transform":"translate3d(-"+h+"px, 0, 0)",transform:"translate(-"+h+"px, 0)"}),e.parent().css("width",h),$(window).resize(function(){h=$(window).width(),i=h*g,a.css("width",h),e.css({width:i,"-webkit-transform":"translate3d(-"+h+"px, 0, 0)",transform:"translate(-"+h+"px, 0)"}),e.parent().css("width",h)}),b){var n={},o=function(b){var b=b||0,c=function(){a.eq(b).trigger("swipeLeft")};n=setInterval(c,5e3)};o(1)}e.delegate("li","swipeLeft",function(a){a.preventDefault(),m&&(k=d>=f?!0:!1,j=h*(d+1),d++,e.css({"-webkit-transition":"0.5s",transition:"0.5s","-webkit-transform":"translate3d(-"+j+"px, 0, 0)",transform:"translate(-"+j+"px, 0)"}),k&&(d=1,m=!1,setTimeout(function(){e.css({"-webkit-transition":"0",transition:"0","-webkit-transform":"translate3d(-"+h+"px, 0, 0)",transform:"translate(-"+h+"px, 0)"}),m=!0},500)),b&&(clearInterval(n),o(d)),c&&c(d-1,e))}),e.delegate("li","swipeRight",function(a){a.preventDefault(),m&&(l=1>=d?!0:!1,j=h*(d-1),d--,e.css({"-webkit-transition":"0.5s",transition:"0.5s","-webkit-transform":"translate3d(-"+j+"px, 0, 0)",transform:"translate(-"+j+"px, 0)"}),l&&(d=g-2,m=!1,setTimeout(function(){e.css({"-webkit-transition":"0",transition:"0","-webkit-transform":"translate3d(-"+h*(g-2)+"px, 0, 0)",transform:"translate(-"+h*(g-2)+"px, 0)"}),m=!0},500)),b&&(clearInterval(n),o(d)),c&&c(d-1,e))})},autoResizeLongEdge:function(a,b,c,d,e){var a=a,f=d||a.width(),g=e||a.height(),h=f/g,i=0,j=navigator.userAgent.toLowerCase();(j.indexOf("iphone")>0||j.indexOf("ipod")>0)&&j.indexOf("safari")>0&&(j.indexOf("os 7_0")<0?b+=60:b=$("#mask").height()-38);var k=c/b;g>b&&k>h?(i=g/b,a.width(Math.floor(f/i)),a.height(b),a.css("margin-top",Math.floor((b-Math.floor(g/i))/2)+"px")):f>c&&h>=k?(i=f/c,a.height(Math.floor(g/i)),a.width(c),a.css("margin-top",Math.floor((b-Math.floor(g/i))/2)+"px")):(g=a.height()||g,a.css("margin-top",Math.floor((b-g)/2)+"px"))},viewLarge:function(a){function b(a,b){var c=new Image;c.onload=function(){c.onload=null,b(c,c.width,c.height)},c.src=a}function c(a,b,c){var a=a;$("#mask img").remove(),$("#mask .mask-box").append('<img src="'+a.src+'">'),a=$("#mask img"),GM.utils.autoResizeLongEdge(a,j,k,b,c),$("#mask .loader-icon").remove(),$(window).resize(function(){j=$(window).height()-38,k=$(window).width(),GM.utils.autoResizeLongEdge(a,j,k,b,c)})}var a=a,d=a.attr("src"),e=a.width(),f=a.height();if(d.toLowerCase().indexOf("guokr.com")>0){var g=d.replace(/thumbnail/i,"image").replace(/_[0-9]+x[0-9]+(.[a-z]+)/i,"$1"),h=GM.temp.viewLargeMask.replace("{img_src}",d),i=$(window).height(),j=i-38,k=$(window).width(),l=$("body"),m=$("#mask .mask-box");d.toLowerCase().indexOf("/gkimage/")>0||d.toLowerCase().indexOf("/image/")>0?(l.append(h).addClass("maskActive"),m.css("height",i),f=a[0].naturalHeight,e=a[0].naturalWidth,GM.utils.autoResizeLongEdge($("#mask img"),j,k,e,f),$("#mask .loader-icon").remove(),$(window).resize(function(){j=$(window).height()-38,k=$(window).width(),GM.utils.autoResizeLongEdge($("#mask img"),j,k,e,f)})):d.toLowerCase().indexOf("/thumbnail/")>0&&(l.append(h).addClass("maskActive"),m.css("height",i),GM.utils.autoResizeLongEdge($("#mask img"),j,k,e,f),b(g,c)),$("#mask .mask-close")&&$("#mask .mask-close").on("click",function(){return $("#mask").detach(),l.removeClass("maskActive"),window.history.back(),!1})}},showSpecial:function(){if("home"==GM.vars.GA_TYPE)var a='<div class="special-line"></div>';else var a="";a+='<div class="special-box"><div class="special" id="special">                        <div class="special-ufo"></div>                        <div class="special-light"></div>                        <div class="special-link">不要点这里！</div>                    </div></div>',$(a).insertBefore($("#to-top"));var b=!0;$("#special").click(function(){b?($(this).animate({top:"15px"},500).find(".special-link").fadeIn(),b=!1):window.open("jokes/default.htm")})}},GM.init={prosConsBar:function(a){ukey?(window.access_token=GM.utils.cookie("_32353_access_token"),$("body").delegate(".mi-btn",a,function(){var a=this.getAttribute("data-id"),b=this.getAttribute("data-operation"),c=$(this).addClass("pros-cons");if(setTimeout(function(){c.removeClass("pros-cons")},1e3),$(this).hasClass("mime-attitude")){var d=$(this);setTimeout(function(){d.removeClass("mime-attitude"),d.find("b").text(+d.find("b").text()-1)},700),$.ajax({type:"DELETE",url:"apis/ask/answer_polling.json@answer_id="+a+"&access_token="+window.access_token,data:{},dataType:"json",cache:!1,error:function(){},success:function(){}})}else{var d=$(this);setTimeout(function(){d.find("b").text(+d.find("b").text()+1),d.addClass("mime-attitude");var a=d.siblings("a");a.hasClass("mime-attitude")&&(a.removeClass("mime-attitude"),a.find("b").text(+a.find("b").text()-1))},700),$.ajax({type:"POST",url:"apis/ask/answer_polling.json",data:{answer_id:a,opinion:b,access_token:window.access_token},dataType:"json",cache:!1,error:function(){},success:function(){}})}})):$("body").delegate(".mi-btn","click",function(){location.href=url_signin})},showNavAdvert:function(){var a=GM.utils.cookie;null===a("_is_closed_adv")&&a("_is_closed_adv","false",{expires:7}),"false"===a("_is_closed_adv")&&GM.utils.showBySystem()},navMethods:function(){$("#nav").length&&(ukey?(GM.utils.toggleShowHideNav("#navBtn","#navMenu"),GM.utils.toggleShowHideNav("#navUserBtn","#navLogin"),GM.utils.logOut("#navSettings"),GM.ajax.getNotifyNum()):GM.utils.toggleCoverHideNav())},loadArticleReply:function(a){GM.vars.article_reply_id=a,GM.vars.reply_type="articleReply",GM.ajax.articleReply(a,0,10)},loadPostReply:function(a){GM.vars.post_reply_id=a,GM.vars.reply_type="postReply",GM.ajax.postReply(a,0,10)},loadMoreReply:function(a){if($(a).find("span").hide().prev().addClass("loadMoreAnimate"),"article"==GM.vars.GA_TYPE){var b=GM.vars.article_reply_id,c=$("#replyList>article").length;GM.ajax.articleReply(b,c,10)}else if("post"==GM.vars.GA_TYPE){var b=GM.vars.post_reply_id,c=$("#replyList>article").length;GM.ajax.postReply(b,c,10)}var d=$("#replyList"),e=d.find("embed"),f=d.find("iframe");e.replaceWith("<p>（此处视频，暂不支持播放）</p>"),f.replaceWith("<p>（此处视频，暂不支持播放）</p>")},getTopAd:function(){GM.vars.top_img_src&&GM.vars.top_download_url||GM.ajax.topAdImgs()},scrollShowNav:function(){GM.utils.scrollShow()},textFold:function(){if("article"==GM.vars.GA_TYPE){var a=$(".article").height()-$("#articleContent").height();GM.utils.hideRestWord("#articleContent","展开全文",a,"mobile_article_all:article",2)}else if("post"==GM.vars.GA_TYPE){var a=$(".post").height()-$("#postContent").height();GM.utils.hideRestWord("#postContent","展开全文",a,"mobile_post_all:post",2)}else if("question"==GM.vars.GA_TYPE){var a=$(".question").height()-$("#askContent").height();GM.utils.hideRestWord("#askContent","展开全文",a,"mobile_post_all:post",.5);var b=$("#answersList");b.find(".answerItem").hide().eq(0).show().siblings().hide(),1===b.find(".answerItem").length?$("#showRestBtn").hide():$("body").delegate("#showRestBtn","click",function(){b.find(".answerItem").show(),$("#showRestBtn").hide()})}},cutNames:function(){GM.utils.cutName("#mBreadcrumb","#mBreadcrumb .currrent",14)},enterRef:function(){GM.utils.isSearchRef()&&GM.utils.cookie("_is_search","1")},loadHot:function(){GM.ajax.loadHotArticle()},swipeChangeFocus:function(a,b,c){GM.utils.swipeChange(a,b,c)},clickChangeTab:function(a){GM.utils.clickChange(a)},viewLargeImg:function(a){$("body").delegate(".ZoomIn",a,function(){return GM.utils.viewLarge($(this).find("img").eq(0)),location.href=location.href+"#"+ +new Date,!1})},initSpecial:function(){var a=100*Math.random();70>=a&&GM.utils.showSpecial()}};var _gaq=_gaq||[];_gaq.push(["_setAccount","UA-19521615-1"]),_gaq.push(["_setDomainName","guokr.com"]),window.ukey?(_gaq.push(["_setCustomVar",1,"Is Registered","Yes",1]),_gaq.push(["_setCustomVar",2,"Is Signed In","Yes",3])):(_gaq.push(function(){var a=_gat._getTrackerByName(),b=a._getVisitorCustomVar(1);"Yes"!==b&&"No"!==b&&_gaq.push(["_setCustomVar",1,"Is Registered","No",1])}),_gaq.push(["_setCustomVar",2,"Is Signed In","No",3])),$("body").delegate("a[data-gaevent]","click",function(){var a=$(this).data("gaevent").split(":");_gaq.push(["_trackEvent",a[0],$(this).attr("href"),a[a.length-1]])}),_gaq.push(["_trackPageview"]);var ga=document.createElement("script");ga.type="text/javascript",ga.async=!0,ga.src=("https:"==document.location.protocol?"../https@ssl/":"../www/")+".google-analytics.com/ga.js";var s=document.getElementsByTagName("script")[0];s.parentNode.insertBefore(ga,s),function(){$(document).ready(function(){$("#to-top").click(function(){GM.utils.scrollToTop()}),GM.init.getTopAd(),GM.init.navMethods(),GM.init.enterRef(),$(".content-main").find("img").each(function(){var a=this.src.toLowerCase();a.indexOf("guokr.com")>0&&(a.indexOf("thumbnail")>0||a.indexOf("gkimage")>0)&&$(this).wrap('<div class="ZoomBox"><div class="content-zoom ZoomIn"></div></div>').closest(".ZoomIn").append('<a href="javascript:">放大</a>')}),GM.init.viewLargeImg("click"),GM.utils.cookie("redirect_mobile")&&"no"===GM.utils.cookie("redirect_mobile")&&GM.utils.cookie("redirect_mobile","yes",{domain:".guokr.com"})});var a=location.href,b=location.protocol+"//",c=location.host;a.indexOf("/#!/minisite/")>0&&(location=b+c+"/articles/"),a.indexOf("/#!/article/")>0&&(location=a.replace(/\/#!/,""))}(),function(){function a(){for(b=0;b<c.length;b++)"viewport"==c[b].name&&(c[b].content="width=device-width, minimum-scale=0.25, maximum-scale=1.6")}var b,c=document.getElementsByTagName("meta");if(navigator.userAgent.match(/iPhone/i)){for(b=0;b<c.length;b++)"viewport"==c[b].name&&(c[b].content="width=device-width, minimum-scale=1.0, maximum-scale=1.0");document.addEventListener("gesturestart",a,!1)}}();
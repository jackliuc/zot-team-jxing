$(function(){GM.vars.GA_TYPE="question",GM.init.prosConsBar("click"),$("#unfoldQuestion").on("click",function(){$(this).hide().prev().show()}),GM.init.textFold(),GM.init.loadHot(),$(".question-head a").click(function(){$(".question-head a").hide(),$(".question-head p").show(),$(".question-content").show()});var a=$("#question-content, .answer-content"),b=a.find("img"),c=a.find("embed"),d=a.find("iframe"),e=$("h1.title").html();b.removeAttr("style"),c.replaceWith("<p>（此处视频，暂不支持播放）</p>"),d.replaceWith("<p>（此处视频，暂不支持播放）</p>");var f={title:e,url:document.URL.replace("../../../m/","../../../www/"),summary:"这个有意思 ⇒_⇒  【"+e+"】"};b.length>0&&(f.pic=$(b[0]).attr("src")),bShare.addEntry(f),GM.init.initSpecial()});
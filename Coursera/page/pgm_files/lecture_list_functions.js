function pushLecture(e){var t=e;History.pushState(null,null,t)}function viewPrevLecture(e){var t=lectureOrder.indexOf(e);if(-1===t)throw"unknown lectureId '"+e+"'";if(0!==t){var n=lectureOrder[t-1];pushLecture(n)}}function viewNextLecture(e){var t=lectureOrder.indexOf(e);if(-1===t)throw"unknown lectureId '"+e+"'";if(t!==lectureOrder.length-1){var n=lectureOrder[t+1];pushLecture(n)}}function showLecture(e){var t=lectureAnchors[e],n=$.trim(t.text());$("title").text(n);var i=$(".course-modal-frame");t.hasClass("lecture-with-slides-link")?i.addClass("course-modal-frame-with-slide"):i.removeClass("course-modal-frame-with-slide"),t.click()}(function(){window.GET=get_js_query_parameters()})();var savedScrollTop=0,enterFullScreen=function(){$(".course-modal-frame").addClass("fullscreen-block"),Modal($(".course-modal-frame")).reposition(),$("html, body").css("overflow","hidden"),savedScrollTop=$(window).scrollTop(),$(window).scrollTop(0)},exitFullScreen=function(){$(".course-modal-frame").removeClass("fullscreen-block"),Modal($(".course-modal-frame")).reposition(),$("html, body").css("overflow","auto"),$(window).scrollTop(savedScrollTop)},lectureOrder=[],lectureAnchors={},listTitle="",inLecture=!1,pushClose=!0,getAction=function(e){var t=document.createElement("a");t.href=e;var n=t.pathname.split("/"),i=n[n.length-1];return i};$(document).ready(function(){$("a[rel=lecture-link]").each(function(){var e=$(this).attr("data-lecture-id");lectureOrder.push(e),lectureAnchors[e]=$(this)}),modal=Modal(".course-modal-frame"),modal.on("open",function(){inLecture=!0}),modal.on("close",function(){inLecture=!1,pushClose&&History.pushState(null,null,"index")}),$(window).bind("statechange",function(){var e=History.getState(),t=getAction(e.url);isNaN(t)?"index"===t&&(pushClose=!1,Modal(".course-modal-frame").close(),pushClose=!0):showLecture(t)}),$(".lecture-link").each(function(){var e=$(this).attr("data-lecture-id");$(this).click(function(t){t.originalEvent&&pushLecture(e)})}),$(".item-resource a[rel=tooltip]").tooltip({animation:!1}),$(".lecture-link, .lecture-with-slides-link").click(function(){$(this).parent().removeClass("unviewed").prepend('<span class="icon-ok"></span>')}),listTitle=$("title").text();var e=getAction(window.location);isNaN(e)||showLecture(e)}),$(document).keydown(function(e){if(inLecture&&"object"==typeof QL_player){var t=QL_player.mediaelement_handle,n=QL_player.mediaelement_media;if(t.options.enableKeyboard)for(var i=0,r=t.options.keyActions.length;r>i;i++)for(var a=t.options.keyActions[i],o=0,s=a.keys.length;s>o;o++)if(e.keyCode==a.keys[o])return e.preventDefault(),a.action(t,n),!1;"object"==typeof QL_player.mediaelement_handle&&QL_player.mediaelement_handle.showControls()}});
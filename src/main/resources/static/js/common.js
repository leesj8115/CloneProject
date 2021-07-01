/**
 * 	common.js
 */

/*
	작업해야하는 일
	1. 상단 메뉴 호버시 아래 세부 메뉴 호출
	2. 각 메뉴에 따른 세부 메뉴 전환
 */

$(function() {
	initHeader();
	$("#navi-menu .left ul li").on("mouseover", showSubMenu);
	$("#navi-menu-all>.wrap").on("mouseleave", hideSubMenu);
});

function initHeader() {
	$("#navi-menu-all>.wrap table").hide(); // 서브메뉴 숨기기
}

// 세부 메뉴 보여주는 함수
function showSubMenu() {
	var target = $(this).index();
	$("#navi-menu .left ul li").removeClass("choice");
	$("#navi-menu .left ul li").eq(target).addClass("choice");
	$("#navi-menu-all>.wrap").css({"height": "480px"});
	$("#navi-menu-all>.wrap table").hide();
	$("#navi-menu-all>.wrap table").eq(target).show();
}

// 세부 메뉴 숨기는 함수
function hideSubMenu() {
	$("#navi-menu .left ul li").removeClass("choice");
	$("#navi-menu-all>.wrap").css({"height": 0});
}
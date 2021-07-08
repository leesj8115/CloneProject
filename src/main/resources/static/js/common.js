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
	$("#navi-menu-all>.wrap table tr td ul li span").on("click", showItem);
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

function pageGoPost(data) {
	// post 방식으로 페이지 이동하기 위한 방법
	// form을 여기서 만들어서 데이터를 같이 보내면서 페이지 전환!
	// 참고 : https://horangi.tistory.com/417
	
	var inputTagData = "";
	
	for (var i = 0; i < data.vals.length; i++) {
		inputTagData += "<input type='hidden' name='" + data.vals[i][0] + "' value='" + data.vals[i][1] + "'/>";
	}
	
	var tempForm = $("<form>", {
		method: "post",
		action: data.url,
		target: data.target,
		html: inputTagData
	}).appendTo("body");
	
	tempForm.submit();	// deprecated 표현이라.. 바꿔줘야함 ㅠ
}

function showItem() {
	var inputData = "";	// input data 묶음
	
	// tag = 남성, 여성, 아동, 프로스펙스, 세일의 index 반환 0~..
	var tag = $("#navi-menu .left ul li.choice").index();
	// largeCategory = HOT SPOT, 신발, 의류, 용품 순...
	var largeCategory = $(this).parents("td").index();
	// smallCategory = 전체, 운동화, 런닝화.. 의 순..
	var smallCategory = $(this).parents("li").index();
	
	pageGoPost({
		url: "/item",
		target: "_self",
		vals: [
			["tag", tag],
			["largeCategory", largeCategory],
			["smallCategory", smallCategory]
		]
	});
}

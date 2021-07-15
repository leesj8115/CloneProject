/*
    item/list.js
*/

/*
    처리하는 일
    1.  소메뉴 클릭하면 해당 목록에 clicked 클래스 부여, 나머지 제거
    2.  아이템에 마우스 호버시 이미지 교체
    3. 정렬순서 클릭시 해당 목록에 맞게 다시 로딩 (ajax)
    4. attribute로 전달받은 값을 통해, 해당하는 목록 아이템 가져오기
    (ajax)
*/

$(function() {
	pageInit();
	
    $("#title div ul li").on("click", changeClicked);
    $("#item-list>.wrap table tr td").on("mouseenter", showHover);
    $("#item-list>.wrap table tr td").on("mouseleave", showNormal);
    
});

function pageInit() {
    $(".img-hover").hide();
	loadItem();
}

function changeClicked() {
    $("#title div ul li").removeClass("clicked");
    $(this).addClass("clicked");

	// 다른 소분류를 클릭할 때마다, 바뀌는 소분류에 따라 목록을 다르게 불러옴
	// loadItem();
}

function showHover() {
    $(this).find(".img-normal").hide();
    $(this).find(".img-hover").show();
}

function showNormal() {
    $(this).find(".img-normal").show();
    $(this).find(".img-hover").hide();
}




function loadItem() {
	// 어떤 값을 불러와야하는지 확인
	var large = $("#title>p").text();
	
	var small = $("#title div ul li.clicked").text();
	
	
	
	// 필요한 값은 문자열로 보내 ㅠㅠㅠㅠ
	
	$.ajax({
		url: "/item/load",
		data: {
			large: large,
			small: small
		},
		method: "POST",
		success: function(result) {
			$("#item-list>.wrap table").html(result);
		}
	});
	
}
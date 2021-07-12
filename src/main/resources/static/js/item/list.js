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
	var _large = $("#title>p").text();
	
	var large;
	if (_large === "신발") {
		large = "SHOES";
	} else if (_large === "의류") {
		large = "CLOTHES";
	} else if (_large === "용품") {
		large = "SUPPLIES";
	}
	
	// smallCategory는 large에 따라 변경되기 때문에, enum 이름 값으로 넘길 수 없음
	// index 값으로 넘기겠습니다.
	var small = $("#title div ul li.clicked").index();
	
	$.ajax({
		url: "/item/load",
		data: {
			largeCategory: large,
			smallCategory: small
		},
		method: "POST",
		success: function(result) {
			alert(result);
			$("#item-list>.wrap table").html(result);
		}
	});
	
}
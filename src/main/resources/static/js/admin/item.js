/*
    admin/item.js
*/

/*
    해야할 일
    1. 대분류 변경에 따른 소분류 변경 (초기화 포함)
    2. 판매가에 아무 것도 입력하지 않았을 때, 판매가 = 정가 처리
*/

$(function() {
    pageInit();
    $("#item-upload form p #large").on("change", changeSmallCategory);
	$("#item-upload form p #price").on("focusout", setSellPrice);
});


function pageInit() {
	$(".opt-group").css("display", "none"); // 소분류 목록 모두 숨기기 (처음에)
}

function changeSmallCategory() {
    var clicked = $(this).val();

    if (clicked === "") {
        // 아무것도 선택하지 않았을 경우
        $(".opt-group").css("display", "none");
        //$("#opt-shoes").hide();
    } else if (clicked === "SHOES") {
        $(".opt-group").css("display", "none");
        $("#opt-SHOES").css("display", "inline");
    } else if (clicked === "CLOTHES") {
        $(".opt-group").css("display", "none");
        $("#opt-CLOTHES").css("display", "inline");
    } else if (clicked === "SUPPLIES") {
        $(".opt-group").css("display", "none");
        $("#opt-SUPPLIES").css("display", "inline");
    }

}

function setSellPrice() {
	var price = $("#price").val();
	
	$("#sell-price").val(price);
}
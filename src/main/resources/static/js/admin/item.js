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
    $("#item-upload form").on("submit", checkSellPrice);    // submit에 대한 핸들러 추가
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
        $("#opt-shoes").css("display", "inline");
    } else if (clicked === "CLOTHES") {
        $(".opt-group").css("display", "none");
        $("#opt-clothes").css("display", "inline");
    } else if (clicked === "SUPPLIES") {
        $(".opt-group").css("display", "none");
        $("#opt-supplies").css("display", "inline");
    }

}

function checkSellPrice() {

    // 고민좀 더 해보자 ㅠㅠㅠㅠㅠㅠㅠㅠ


    // 값 복사해서 넘겨주기
    /*
    
    var pri = Number($("#price").val());

    alert("pri = " + pri);

    $("#salePrice").val(pri);

    alert("sale = " + $("#salePrice").val());

    // 왜 undefined 인지 ㅠ

    return false;
    */
}
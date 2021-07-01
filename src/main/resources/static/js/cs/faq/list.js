/*
    faq/list.js

    해야할 일
    1. DB에서 FAQ 가져와서 뿌려주기
    2. faq-tag 클릭에 따라 다른 분류의 데이터 보내주기
*/

let divisionList = ["회원","배송","주문/결제","교환/반품/취소","쿠폰/포인트","상품"];

$(function() {
    $("#faq-tag li").eq(0).addClass("clicked");
    faqLoading(divisionList[0], 1);   // faqEntity에서 데이터 가져오는 함수!
    $("#faq-tag li").on("click", changeList);
});

function changeList() {
    // 입력받은 faq-tag의 li에 따라 목록 갱신
    // 지금은 분류를 테스트 + n 으로 넣었기 때문에...
    // 다시 정리할 때는 각 분류에 맞는 값으로 변환해줘야함
    var idx = $(this).index();

    $("#faq-tag li").removeClass("clicked");
    $("#faq-tag li").eq(idx).addClass("clicked");

    // 클릭한 분류에 맞는 값을 불러와서 실행
    faqLoading(divisionList[idx], 1);
}

function faqLoading(inputDivision, page) {
    /*
        기존에 했던 ajax로는
        $.ajax({
            url: "/cs/faq/get",
            data: {division: inputDivision}
            type: "get",
            success: function(result) {
                ~~~~
            }
        });
        이러한 형태가 됨
    */

    $.get(
        "/cs/faq/get",
        {division: inputDivision, pageNo: page},
        function(result) {
            $("#faq-data").html(result);
            $("#faq-board table tbody .faq-content").hide();
            $("#faq-board table tbody .faq-subject").on("click", showContext);
            $("#page-list li button").on("click", function() {
                var divNum = $("#faq-tag li.clicked").index();
                var pageNum = $(this).text();
                faqLoading(divisionList[divNum], parseInt(pageNum));
            })
        }
    );
}

function showContext() {
    var idx = $(this).index();
    // index가 tr 전체에서의 index를 가르쳐줌... 주의!
    // faq-subject = 0, 2, 4, 6, ... 의 짝수
    // faq-content = 1, 3, 5, 7, ... 의 홀수
    // 클릭되면 index+1의 내용을 hide, show
    

    if($("#faq-board table tbody tr").eq(idx).hasClass("clicked")) {
        // clicked 클래스를 가지고 있다면 (내용을 보여주는 중)
        // clicked 클래스 제거
        $("#faq-board table tbody tr").eq(idx).removeClass("clicked");
        // 내용은 다시 숨김
        $("#faq-board table tbody tr").eq(idx+1).hide();
    } else {
        // clicked 클래스가 없다면 (내용을 보여주기 전)
        // clicked 클래스 추가
        $("#faq-board table tbody tr").eq(idx).addClass("clicked");
        // 내용은 보여줌
        $("#faq-board table tbody tr").eq(idx+1).show();
    }
    

}
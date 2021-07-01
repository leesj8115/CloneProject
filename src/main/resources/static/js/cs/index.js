/*

    cs의 index.html에 대한 js

    해야할 일

    1. FAQ TOP5 에서 클릭에 따라 내용 toggle

*/

$(function() {
    // 클릭에 따라 내용 보여주기/숨기기 토글
    $("#faq-top>.wrap ol li").on("click", function() {
        $(this).hasClass("clicked") ? $(this).removeClass("clicked") : $(this).addClass("clicked");
        
    });
});


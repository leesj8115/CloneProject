/**
 * 	index.js
 */

/*
	작업해야하는 일
	1. visual 영역 사진 슬라이드
	2. 불릿 반영
	3. tag-item 영역 클릭시 메뉴 변경
	
 */

var timer;
var speed = 3000;	// 전환 속도

var bullet_list;

$(function() {
	initPage();
	$("#tag-item>.wrap .menu li").on("click", changeTagItem); // 3번 작업
});


function initPage() {
	// 처음 페이지 로딩시 실행하는 함수
	initTagItem();
	bulletSet();
}

/*
	1번 작업 (비주얼 영역 이미지 슬라이드)
*/

function bulletSet() {
	// 불릿 리스트 중에 첫번째 꺼만 target 클래스 설정
	bullet_list = $("#visual>.wrap .bullet li");
	bullet_list.first().addClass("target");

	// 영역 슬라이드 시작
	//timer = setTimeout(bull_start, speed);

	timer = setInterval(bull_start, speed);

	// 호버되면 정지
	$("#visual>.wrap .visual-image").hover(stop, function() {
		timer = setInterval(bull_start, speed);
	});
}

function stop() {
	clearInterval(timer);
}

function bull_start() {
	next();	// 이미지 하나 슬라이드
	//setTimeout(bull_start, speed);	// 다시 이벤트 시작
}

function next(){
	var first=$("#visual>.wrap .visual-image li:first-child");
	var last=$("#visual>.wrap .visual-image li:last-child");
	var tar_bul=(first.val()+1) % bullet_list.length;
	$("#visual>.wrap .visual-image").animate(
		{marginLeft: -100+"%"}, //애내메이션 처리할 스타일적용 속성은 카멜표기법으로표기하세요.
		500,                    //스피드
		function(){             //애니메이션 실행후 처리하는 작업
			last.after(first);
			$("#visual>.wrap .visual-image").css({marginLeft: 0});
			bullet_list.removeClass("target"); //모든 블릿의 "bullet"클래스 제거
			bullet_list.eq(tar_bul).addClass("target");//이동된 블릿에 적용
			//1번 실행끝
		}
	);
}

/*
	3번 작업 (태그 아이템 메뉴 변경)
*/
function initTagItem() {
	// 처음 거 선택하고, 나머지 메뉴 숨기기
	$("#tag-item>.wrap .menu li").removeClass("clicked");
	$("#tag-item>.wrap .menu li").eq(0).addClass("clicked");

	$("#tag-item>.wrap .item li").hide();
	$("#tag-item>.wrap .item li").eq(0).show();
}

function changeTagItem() {
	var idx = $(this).index();	// 클릭된 녀석의 index 확인
	//alert(idx);
	$("#tag-item>.wrap .menu li").removeClass("clicked");
	$("#tag-item>.wrap .menu li").eq(idx).addClass("clicked");

	// 클릭된 아이템의 메뉴만 출력하기
	$("#tag-item>.wrap .item li").hide();
	$("#tag-item>.wrap .item li").eq(idx).show();
}



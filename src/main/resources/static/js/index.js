/**
 * 	index.js
 */

/*
	작업해야하는 일
	1. visual 영역 사진 슬라이드
	2. 불릿 반영
	3. tag-item 영역 클릭시 메뉴 변경
	4. 불릿 클릭시 해당 이미지로 이동
 */

var timer;
var speed = 3500;	// 전환 속도

var bullet_list;

$(function() {
	visualLoading();
});


function visualLoading() {
	// ajax를 통해, DB에서 파일 경로 따와서 메인의 visual 영역 출력
    $.get(
        "/visual",
        {},
        function(result) {
			$("#visual>.wrap").html(result);
	
            initPage();
			$("#tag-item>.wrap .menu li").on("click", changeTagItem); // 3번 작업
			$("#visual>.wrap .bullet li").on("click", clickBullet);
        }
    );
}

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
	timer = setInterval(bull_start, speed);

	// 비주얼 영역에 마우스 올릴 경우 이미지 스크롤 정지
	$("#visual>.wrap .visual-image").on("mouseover", function() {
		stop();
	});

	// 비주얼 영역에서 마우스 떠날 경우 타이머 재시작
	$("#visual>.wrap .visual-image").on("mouseleave", function() {
		timer = setInterval(bull_start, speed);
	});
}

function clickBullet() {
	bullet_list = $("#visual>.wrap .bullet li");
	var target = $("#visual>.wrap .bullet li.target").index();
	var clickIdx = $(this).index();

	// target = 현재 보여주고 있는 이미지 index
	// clickIdx = 클릭한 불릿의 index

	if (target === clickIdx) {
		clearInterval(timer); // 정지
		timer = setInterval(bull_start, speed);	// 다시 시작
		// javascript는 순차 언어가 아니기 때문에, 정지 후 실행이 아닐 수 있음
	} else if (target < clickIdx) {
		// 클릭한 이미지가 더 뒤에 있을 경우
		// -> 기존에 넘겨주는 이벤트 그대로 재활용!
		nextMore(target, clickIdx);
	} else {
		// 클릭한 이미지가 더 앞에 있을 경우
		// -> 반대로 넘겨주는 이벤트 실행
		previousMore(target, clickIdx);
	}

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

function nextMore(target, clickIdx) {
	// 여러장 넘기는 함수 (기본 틀 : next() )

	// 여러장 넘기는 함수는 bullet 클릭시 수동으로 이뤄지기 때문에, 타이머도 수동으로 관리
	stop();	// 정지 한 번 해주고

	var gap = clickIdx - target;

	var marginSize = (-100) * gap;

	$("#visual>.wrap .visual-image").animate(
		{marginLeft: marginSize + "%"}, //애내메이션 처리할 스타일적용 속성은 카멜표기법으로표기하세요.
		500,                    //스피드
		function(){             //애니메이션 실행후 처리하는 작업

			// 차이나는 갯수만큼 옆으로 넘겨줌
			$("#visual>.wrap .visual-image").css({marginLeft: 0});

			for (var i = 0; i < gap; i++) {
				var first=$("#visual>.wrap .visual-image li:first-child");
				var last=$("#visual>.wrap .visual-image li:last-child");

				last.after(first);
			}

			bullet_list.removeClass("target"); //모든 블릿의 "bullet"클래스 제거
			bullet_list.eq(clickIdx).addClass("target");//이동된 블릿에 적용
			
			timer = setInterval(bull_start, speed);

		}
	);
}

function previousMore(target, clickIdx) {
	// 작업 예정
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



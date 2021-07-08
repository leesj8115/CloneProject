/*
    faq/list.js

    해야할 일
    1. DB에서 FAQ 가져와서 뿌려주기
    2. faq-tag 클릭에 따라 다른 분류의 데이터 보내주기
*/

let divisionList = ["회원","배송","주문/결제","교환/반품/취소","쿠폰/포인트","상품"];
var divisionIdx = 0;

$(function() {
    $("#faq-tag li").eq(0).addClass("clicked");
    faqLoading(divisionList[divisionIdx], 1);   // faqEntity에서 데이터 가져오는 함수!
    $("#faq-tag li").on("click", changeList);
});

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

			// 각 faq에 대한 삭제, 수정 버튼 이벤트 추가
			$(".btn-edit").on("click", showEdit);
			$(".btn-delete").on("click", faqDelete);
			$(".btn-cancel").on("click", hideEdit);
			$(".btn-update").on("click", faqUpdate);
			
			// 수정을 위해 만든 태그 안보이게 수정
			$(".edit").hide();
        }
    );
}

function hideEdit() {
	// showEdit에서 바꿔치기한 녀석을 다시 원상복구 하기
	var idx = $(this).parents("tr").index();	// 클릭된 녀석의 tr 번호 확인
	
	// 창 줄이지 못하게 editing 클래스 추가
	$("#faq-board table tbody tr").eq(idx - 1).removeClass("editing");
	
	$("#faq-board table tbody tr").eq(idx - 1).find(".question-text").show();
	$("#faq-board table tbody tr").eq(idx - 1).find(".edit").hide();
	
	// 내용 input 변경 (수정,취소 버튼도 보이게)
	$("#faq-board table tbody tr").eq(idx).find(".answer-text").show();
	$("#faq-board table tbody tr").eq(idx).find(".edit").hide();
	
	$("#faq-board table tbody tr").eq(idx).find(".btn-edit").show();
}

function showEdit() {
	var idx = $(this).parents("tr").index();	// 클릭된 녀석의 tr 번호 확인
	
	
	// 창 줄이지 못하게 editing 클래스 추가
	$("#faq-board table tbody tr").eq(idx - 1).addClass("editing");
	
	// 제목 input 변경
	$("#faq-board table tbody tr").eq(idx - 1).find(".question-text").hide();
	$("#faq-board table tbody tr").eq(idx - 1).find(".edit").show();
	
	// 제목 내용을 input으로 load
	var question = $("#faq-board table tbody tr").eq(idx - 1).find(".question-text").text();
	$("#faq-board table tbody tr").eq(idx - 1).find(".edit-question").val(question);
	
	// 내용 input 변경 (수정,취소 버튼도 보이게)
	$("#faq-board table tbody tr").eq(idx).find(".answer-text").hide();
	$("#faq-board table tbody tr").eq(idx).find(".edit").show();
	
	// 답변 내용을 textarea로 load
	var answer = $("#faq-board table tbody tr").eq(idx).find(".answer-text").text();
	$("#faq-board table tbody tr").eq(idx).find(".edit-answer").val(answer);
	
	// 이 때 편집 버튼도 안보여야 안헷갈릴듯
	$("#faq-board table tbody tr").eq(idx).find(".btn-edit").hide();

}

function faqUpdate() {
	
	if(!confirm("수정하겠습니까?")) {
		return;
	}
	
	var idx = $(this).parents("tr").index();	// 클릭된 녀석의 tr 번호 확인
	
	// faq의 no 값과 분류, input의 수정된 제목, 내용 가져오기
	var _no = $(this).parents("td").find("input").val();
	var _division = divisionList[divisionIdx];
	var _question = $("#faq-board table tbody tr").eq(idx-1).find(".edit-question").val();
	var _answer = $("#faq-board table tbody tr").eq(idx).find(".edit-answer").val();

	//alert("번호 : " + _no + "\n질문 : " + _question + "\n내용 : " + _answer);
	
	// 위에서 얻은 내용을 통해서 처리하기!
	
	$.ajax({
		url: "/cs/faq/" + _no,
		data: {
			division: _division,
			question: _question,
			answer: _answer
		},
		type: "PUT",
		success: function(result) {
			alert("수정을 완료했습니다.");
			faqLoading(divisionList[divisionIdx], 1);	
		},
		error: function() {
			alert("수정에 실패했습니다. 서버 나빠요 ㅠㅠ");
		}
	});
	
}

function faqDelete() {
	var _no = $(this).parents("td").find("input").val();
	//alert("no가 " + _no + "인 글 삭제");
	
	if(!confirm("삭제하겠습니까?")) {
		return;
	}
	
	$.ajax({
		url: "/cs/faq/"+ _no,
		type: "DELETE",
		success: function(result) {
			alert("삭제를 완료했습니다.");
			faqLoading(divisionList[divisionIdx], 1);	
		}
	});
}

function changeList() {
    // 입력받은 faq-tag의 li에 따라 목록 갱신
    // 지금은 분류를 테스트 + n 으로 넣었기 때문에...
    // 다시 정리할 때는 각 분류에 맞는 값으로 변환해줘야함
    divisionIdx = $(this).index();

    $("#faq-tag li").removeClass("clicked");
    $("#faq-tag li").eq(divisionIdx).addClass("clicked");

    // 클릭한 분류에 맞는 값을 불러와서 실행
    faqLoading(divisionList[divisionIdx], 1);
}

function showContext() {
    var idx = $(this).index();
    // index가 tr 전체에서의 index를 가르쳐줌... 주의!
    // faq-subject = 0, 2, 4, 6, ... 의 짝수
    // faq-content = 1, 3, 5, 7, ... 의 홀수
    // 클릭되면 index+1의 내용을 hide, show
    
	
	if (!$("#faq-board table tbody tr").eq(idx).hasClass("editing")) {
		// editing 클래스가 없는 경우에만 실행
		// 즉, 편집중이 아닐 경우
		
		
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

}
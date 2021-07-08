/*
    new.js
*/

/*
    여기서 해야하는 일
    1. 비밀번호 = 비밀번호 입력 같은지 확인
    2. 이메일 도메인에 관한 처리 (선택했을 때 값 처리, 미입력시 block처리)
	3. 이메일 중복되는지 확인해야함
	4. 선택항목 전체 선택시 모두 체크/해제
*/

$(function() {
	initJoin();
	$("#email-id").on("focusout", checkEmail);
	$("#email-domain").on("focusout", checkEmail);
    $("#domain-list").on("change", domainSelect);
	$("#domain-list").on("change", checkEmail);
	$("#pw-again").on("focusout", checkPw);
	$("#agree-all").on("click", agreeAllTerms);
});


function initJoin() {
    $("#email-domain").attr("disabled", true);
    disableButton();
}

function domainSelect() {
	// select-option 값 선택에 따른 입력폼 값 전달
	
    var opt = $(this).val();

    if (opt === "") {
        $("#email-domain").attr("disabled", true);
		$("#email-domain").attr("readonly", true);
        $("#email-domain").val("");
        disableButton();
    }
    else if (opt === "etc") {
        $("#email-domain").attr("disabled", false);
        $("#email-domain").attr("readonly", false);
        $("#email-domain").val("");
        ableButton();

    } else {
        $("#email-domain").attr("disabled", false);
		$("#email-domain").attr("readonly", true);
        $("#email-domain").val(opt);
        ableButton();
    }

}

function checkEmail() {
	var front = $("#email-id").val();		// 앞부분
	var back = $("#email-domain").val();	// 뒷부분
	
	if ((front.length < 4) || (front.length > 12)) {
		$("#id-msg").text("아이디를 4자리 이상 12자 이하로 입력해주세요.");
		disableButton();
	} else if(back.length === 0) {
		// 아직 도메인 선택 안함
		$("#id-msg").text("도메인을 입력해주세요.");
		disableButton();
	} else {
		var fullId = front + "@" + back;	// 두 데이터를 조합해서 이메일로 만들어줌
		
		$.ajax({
			url: "/log/check",
			data: {id: fullId},
			type: "post",
			success: function(result) {
				// true = 사용 가능, false = 사용 불가능
				if (result) {
					$("#id-msg").text("");
					ableButton();
				} else {
					disableButton();
					$("#id-msg").text("이미 사용중인 이메일입니다.");
				}
			}
		});
	}
	
}

function checkPw() {
    // 비밀번호 일치하는지 검증 실행

    var pw1 = $("#pw").val();
    var pw2 = $("#pw-again").val();

    if (pw1 === pw2) {
        // 비밀번호 일치시
        $("#pw-msg").text("");
        ableButton();
    }
    else {
        // 비밀번호 불일치시
        $("#pw-msg").text("비밀번호를 확인하여주십시오.");
        disableButton();
    }
}

function disableButton() {
    // submit 버튼 비활성화
    $("#btn-submit").css({"opacity": 0.4, "cursor": "default"});
    $("#btn-submit").attr("disabled", true);
}

function ableButton() {
    // submit 버튼 활성화
    $("#btn-submit").css({"opacity": 1, "cursor": "pointer"});
    $("#btn-submit").attr("disabled", false);
}

function agreeAllTerms() {
	// 클릭할 때 선택상자 전부 체크, 해제
	var checked = $(this).is(":checked");
	
	if (checked) {
		// 클릭돼있으면
		$("input[type='checkbox']").prop("checked", true);
	}
	else {
		$("input[type='checkbox']").prop("checked", false);
	}
}
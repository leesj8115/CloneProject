/**
 * 	board/detail.js
 */


$(function() {
	
	// 댓글 정보 가져오는 함수
	getReplies();
	
	// 댓글 등록 버튼 클릭시
	$("#btn-reply").on("click", replyWrite);
});

function getReplies() {
	var bno = $("#bno").val();	// 게시판의 글 정보 가져옴
	
	// 자료를 가져올 때는 Get
	$.ajax({
		url: "/board/" + bno + "reply",
		type: "get",
		success: function() {
			
		}
	});
}


function replyWrite() {
	var bno = $("#bno").val();
	var reply = $("#reply").val();
	var writer = $("#writer").val();
	
	$.ajax({
		url: "/board/" + bno + "/reply",
		data: {
			reply: reply,
			writer: writer
		},
		type: "post",
		success: function() {
			
		}
	});
	
}
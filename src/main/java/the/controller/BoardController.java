package the.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import the.domain.dto.MemberDetails;
import the.domain.dto.board.BoardWriteDto;
import the.domain.dto.board.ReplyInsertDto;
import the.service.board.BoardService;
import the.service.board.ReplyService;

@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	@Autowired
	private ReplyService replyService;
	
	
	// 게시글 리스트 보기
	@GetMapping("/board")
	public String board(Model model, Authentication authentication) {
		
		// 로그인한 사용자 이름 가져오기
		MemberDetails member = (MemberDetails) authentication.getPrincipal();
		System.out.println(member.getName());
		model.addAttribute("userName", member.getName());
		
		service.getBoardList(model);
		return "/board/list";
	}
		
	@GetMapping("/board/write")
	public String wirte() {
		return "/board/write";
	}
		
	// 글쓰기 처리 실행
	@PostMapping("/board/write")
	public String wirte(BoardWriteDto dto) {
		service.save(dto);
		return "redirect:/board";
	}
		
	@GetMapping("/board/{no}")
	public String detail(@PathVariable("no") long no, Model model) {
		
		service.detail(no, model);
		
		return "/board/detail";
	}
	
	@ResponseBody
	@PostMapping("/board/{bno}/reply")
	public void writeReply(@PathVariable long bno, ReplyInsertDto dto) {

		System.out.println("no = " + bno);
		
		replyService.save(bno, dto);
		
		
	}
	
	@GetMapping("/board/{bno}/reply")
	public ModelAndView writeReply(@PathVariable long bno) {

		System.out.println("no = " + bno);
		
		// 실제 로직 구현은 service에서 처리
		
		return replyService.getReplies(bno);
	}
}

package the.service.board;


import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import the.domain.dto.board.ReplyDto;
import the.domain.dto.board.ReplyInsertDto;
import the.domain.entity.board.BoardEntity;
import the.domain.entity.board.Reply;
import the.domain.entity.board.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyRepository replyRepository;
	
	
	@Transactional
	@Override
	public void save(long bno, ReplyInsertDto dto) {
		// 댓글 등록하기 (지정된 게시물에 대해!)
		
		// board와 연결해주기 위해
		// board의 no값만 넣어서 만들면 됨!
		
		Reply replyEntity = Reply.builder()
				.reply(dto.getReply())
				.writer(dto.getWriter())
				.board(BoardEntity.builder().no(bno).build())
				.build();
		
		replyRepository.save(replyEntity);
		
	}


	@Override
	public ModelAndView getReplies(long bno) {
		ModelAndView mv = new ModelAndView("/board/reply");
		
		
		// 1. 특별한 처리 없이 보내기 (그냥 가져와서 뿌리기!)
	/*
		List<Reply> entity = replyRepository.findAllByBoardNo(bno);
		
		List<ReplyDto> dto = entity.stream()
				.map(ReplyDto::new)
				.collect(Collectors.toList());
	*/
		Sort sort = Sort.by("no").descending();
		
		List<Reply> entity = replyRepository.findAllByBoardNo(bno, sort);
		// 정렬을 사용하기 위해 Sort 객체를 선언해서 사용함.
		// 또는 findAllByBoardNoOrderByNoDesc 와같은 쿼리메소드로도 처리 가능
		
		List<ReplyDto> dto = entity.stream()
				.map(ReplyDto::new)
				.collect(Collectors.toList());
		
		
		
		mv.addObject("replies", dto);
		
		return mv;
	}

}

package the.service.board;


import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
		
		List<Reply> entity = replyRepository.findAllByBoardNo(bno);
		
		List<ReplyDto> dto = entity.stream()
				.map(reply -> {
					ReplyDto d;
					d.setNo(reply.getNo());
					d.setReply(reply.getReply());
					d.setWriter(reply.)
				})
				.collect(Collectors.toList());
		
		
		mv.addObject("replies", null);
		
		return mv;
	}

}
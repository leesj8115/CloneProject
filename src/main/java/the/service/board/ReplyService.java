package the.service.board;

import org.springframework.web.servlet.ModelAndView;

import the.domain.dto.board.ReplyInsertDto;

public interface ReplyService {

	void save(long bno, ReplyInsertDto dto);

	ModelAndView getReplies(long bno);

}

package the.service.board;

import org.springframework.ui.Model;

import the.domain.dto.board.BoardWriteDto;


public interface BoardService {


	void getBoardList(Model model);

	void save(BoardWriteDto dto);

	void detail(long no, Model model);

}

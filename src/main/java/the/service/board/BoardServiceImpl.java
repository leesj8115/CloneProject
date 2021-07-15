package the.service.board;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import the.domain.dto.board.BoardDetailDto;
import the.domain.dto.board.BoardListDto;
import the.domain.dto.board.BoardWriteDto;
import the.domain.entity.board.BoardEntityRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardEntityRepository repository;
	
	@Override
	public void getBoardList(Model model) {
		//조회결과 List<BoardListDto> 로 저장
		List<BoardListDto> result=repository.findAll().stream()
				.map(BoardListDto::new)
				.collect(Collectors.toList());
		//페이지에서 확인할수 있도록 Model에 "list"이름으로 저장
		model.addAttribute("list", result);
		
	}

	@Override
	public void save(BoardWriteDto dto) {
		repository.save(dto.toEntity());
		
	}

	@Override
	public void detail(long no, Model model) {
		
		BoardDetailDto result = repository.findById(no)
				.map(BoardDetailDto::new)
				.orElseThrow();
	
		model.addAttribute("detail", result);
		
	}

}

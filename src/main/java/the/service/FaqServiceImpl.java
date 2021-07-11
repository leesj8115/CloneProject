package the.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;
import the.domain.dto.faq.FaqDto;
import the.domain.dto.faq.FaqResultDto;
import the.domain.entity.FaqEntity;
import the.domain.entity.FaqRepository;


@Slf4j
@Service
public class FaqServiceImpl implements FaqService {

    @Autowired
    private FaqRepository faqRepository;

    @Override
    public void list(String division, Model model) {

        // 받을 때 부터 entity -> dto 형태로 변환해서 받아요!
        List<FaqResultDto> result = 
        faqRepository.findAllByDivisionOrderByNoDesc(division)
        .stream()
        .map(FaqResultDto::new)
        .collect(Collectors.toList());
        
        model.addAttribute("list", result); // attribute로 전달

        
    }

    @Override
    public void list(String division, int pageNo, Model model) {
        // 페이지 처리를 위해 Pageable 이용
        
        int pageSize = 10;


        Sort sort = Sort.by(Direction.DESC, "no");
        // PageRequest.of(가져올 페이지 번호, 페이지 크기, 정렬)
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);

        Page<FaqEntity> result = faqRepository.findAllByDivision(division, pageable);

        int pageTotal = result.getTotalPages(); // 총 페이지 수 획득

        List<FaqResultDto> data = result.getContent()   // List<entity> 객체를 얻어내고
        .stream()
        .map(FaqResultDto::new)                         // dto 로 변환
        .collect(Collectors.toList());

        log.debug("total = " + pageTotal);
        
        model.addAttribute("list", data); // attribute로 전달
        model.addAttribute("pageTotal", pageTotal);
        // 이렇게 개별로 주기보다, 클래스 하나 만들어서 페이지 관련된 데이터 잘 줄 수도 있습니당
    }

    @Override
    public void write(FaqDto dto) {
        
        // faq 작성
        FaqEntity entity = FaqEntity.builder()
                            .division(dto.getDivision())
                            .question(dto.getQuestion())
                            .answer(dto.getAnswer())
                            .build();

        faqRepository.save(entity);
    }

	@Override
	public void delete(long no) {
		// FaqEntity의 id인 no를 통해 삭제!
		faqRepository.deleteById(no);
	}

	@Override
	public void update(long no, FaqDto dto) {
		FaqEntity entity = FaqEntity.builder()
				.no(no)
				.division(dto.getDivision())
				.question(dto.getQuestion())
				.answer(dto.getAnswer())
				.build();
		
		faqRepository.save(entity);
	}
    
}

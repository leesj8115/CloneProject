package the.service;

import org.springframework.ui.Model;

import the.domain.dto.faq.FaqDto;
import the.domain.dto.faq.FaqResultDto;

public interface FaqService {

    void list(String division, Model model);

    void list(String division, int pageNo, Model model);

    void write(FaqDto dto);

	void delete(long no);
	
	void update(long no, FaqDto dto);

    
}

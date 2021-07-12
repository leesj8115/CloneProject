package the.service.cs.faq;

import org.springframework.ui.Model;

import the.domain.dto.faq.FaqDto;

public interface FaqService {

    void list(String division, Model model);

    void list(String division, int pageNo, Model model);

    void write(FaqDto dto);

	void delete(long no);
	
	void update(long no, FaqDto dto);

    
}

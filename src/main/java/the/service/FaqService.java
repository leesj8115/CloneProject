package the.service;

import org.springframework.ui.Model;

import the.domain.dto.FaqDto;

public interface FaqService {

    void list(String division, Model model);

    void list(String division, int pageNo, Model model);

    void write(FaqDto dto);
    
}

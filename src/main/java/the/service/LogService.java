package the.service;

import org.springframework.ui.Model;

import the.domain.dto.MemberInputDto;

public interface LogService {

    void join(MemberInputDto dto, Model model);

	boolean emailCheck(String id);
    
}

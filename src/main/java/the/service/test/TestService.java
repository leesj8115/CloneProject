package the.service.test;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import the.domain.dto.test.TestWriteDto;

public interface TestService {

	void insert(MultipartFile file, TestWriteDto dto);

	String uploadTempFile(MultipartFile file);

	void testList(Model model);

}

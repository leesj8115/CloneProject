package the.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import the.domain.dto.test.TestWriteDto;
import the.service.test.TestService;

@Controller
public class TestController {

	@Autowired
	private TestService testService;
	
	@GetMapping("/test/file")
	public String filetest() {
		return "/test/filetest";
	}
	
	@PostMapping("/test/file/write")
	public String test(MultipartFile file, TestWriteDto dto) {
		
		testService.insert(file, dto);
		
		return "redirect:/test/list";
	}
	
	@ResponseBody
	@PostMapping("/test/file/temp")
	public String temp(MultipartFile file) {
		
		System.out.println(file.getOriginalFilename());
		
		return testService.uploadTempFile(file);
		
	}

	@GetMapping("/test/list")
	public String list(Model model) {
		
		testService.testList(model);
		
		return "/test/list";
	}
}

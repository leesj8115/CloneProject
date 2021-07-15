package the.service.test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import the.domain.dto.test.TestResponseDto;
import the.domain.dto.test.TestWriteDto;
import the.domain.entity.test.TestRepository;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;

	@Override
	public void insert(MultipartFile file, TestWriteDto dto) {
		// 1. file 정보 확인
		
		long fileSize = file.getSize();	// 파일의 용량 정보 확인
		/*
		if (false) {
			// 사이즈 검토 해도 되고, 안해도 됨
		}
		*/
		
		String fileName = file.getOriginalFilename();
		String fileUrl = "/images/test/";
		// url이 아닌 path 경로를 찾아요 .
		
		//String hardFileUrl = "E:/spring/workspace/cloneProj/src/main/resources/static";
		
		//String fileUrl = "/images/test/temp/";
		
		ClassPathResource cpr = new ClassPathResource("static" + fileUrl);
		
		ClassPathResource tempCpr = new ClassPathResource("static" + fileUrl + "temp/");
		
		try {
			File location = cpr.getFile();
			// 파일 업로드 (X) : 왜? 임시폴더에 이미 존재하고 있으니까, 이동 시킵시다
			//file.transferTo(new File(location, fileName));
			
			File tempFile = new File(tempCpr.getFile(), fileName);
			
			if (tempFile.exists()) {
				// 파일이 존재하면, 옮기기만 합시다!!!
				tempFile.renameTo(new File(location, fileName));
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dto.setFileName(fileName);
		dto.setFileSize(fileSize);
		dto.setFileUrl(fileUrl);
		
		testRepository.save(dto.toEntity());
		
		
	}

	@Override
	public String uploadTempFile(MultipartFile file) {
		// 1. 파일 정보
		long fileSize = file.getSize();
		
		if (fileSize > 2 * 1024 * 1024) {
			log.warn("파일 용량 초과!");
			return null;
		}
		
		String fileName = file.getOriginalFilename();
		
		String hardFileUrl = "E:/spring/workspace/cloneProj/src/main/resources/static";
		
		String fileUrl = "/images/test/temp/";
		//ClassPathResource cpr = new ClassPathResource("static" + fileUrl);
		
		try {
			File location = new File(hardFileUrl + fileUrl);
			// File location = cpr.getFile();
			// 해당 폴더에 파일이 존재.. (안 쓴 애들..)
			
			for(File element : location.listFiles()) {
				element.delete();
			}
			
			// 파일 업로드
			file.transferTo(new File(location, fileName));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("경로 = " + fileUrl + fileName);
		
		return fileUrl + fileName;
	}

	@Override
	public void testList(Model model) {

		List<TestResponseDto> result = testRepository.findAll().stream().map(TestResponseDto::new).collect(Collectors.toList());
		
		
		model.addAttribute("list", result);
		
	}
	
	
}

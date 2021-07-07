package the.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import the.domain.dto.FileDto;
import the.service.FileService;

@Slf4j
@Controller
public class AdminController {

    @Autowired
    FileService fileService;

    @GetMapping("/admin")
	public String admin() {
		// 관리자 페이지로 이동

		return "/admin/index";
	}

    @GetMapping("/admin/file")
    public String file() {
        return "/admin/file";
    }
    
    @GetMapping("/admin/item")
    public String item() {
    	return "/admin/item";
    }
    
    // 파일 업로드
    // 참고 : http://yoonbumtae.com/?p=2834
    
    @PostMapping("/admin/file/write")
    public String write(@RequestParam("files") MultipartFile files,String division, Model model) {        

        log.debug("파일 업로드 실행");
        
        // rootPath은 작업 환경 (집 로컬, 외부 로컬, GCP Server)에 맞게 변경 필요
        String rootPath = "D:/workspace/cloneProj/src/main/resources/static/images";	// 집
        // String rootPath = "E:/spring/workspace/cloneProj/src/main/resources/static/images";	// 외부
        // String rootPath = "/home/leesj8115/src/root/WEB-INF/classes/static/images";		// 리눅스 서버 (GCP)
        
        String basePath = rootPath + "/" + division;

        // divison에 해당하는 폴더 없을 경우 폴더 생성
        // 참고 : https://coding-factory.tistory.com/283
        File folder = new File(basePath);

        if (!folder.exists()) {
            folder.mkdir();
            log.debug(basePath + " : 폴더 생성");
        }

        String filePath = basePath + "/" + files.getOriginalFilename();

        File dest = new File(filePath);

        try {
            files.transferTo(dest);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        
        /*
         * 위에서 실제 파일은 전송한 후
         * DB에는 root 아래 / 부터 저장되도록 수정함
         * 지금은 무조건 images 폴더 아래로 가게 되어있으니 해당 에 맞게 filePath 수정
         * */
        
        FileDto dto = new FileDto();
        dto.setFileName(files.getName());
        dto.setFileOriName(files.getOriginalFilename());
        dto.setFilePath("/images" + "/" + division + "/" + files.getOriginalFilename());
        dto.setDivision(division);

        fileService.saveFile(dto, model);

        return "/admin/file";
    }
}

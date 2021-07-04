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

    @GetMapping({"/admin", "/admin/index"})
	public String admin() {
		// 관리자 페이지로 이동

		return "/admin/index";
	}

    @GetMapping("/admin/file")
    public String file() {
        return "/admin/file";
    }

    @PostMapping("/admin/file/write")
    public String write(@RequestParam("files") MultipartFile files,String division, Model model) {        

        log.debug("파일 업로드 실행");

        String rootPath = "D:/workspace/cloneProj/src/main/resources/static";
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

        FileDto dto = new FileDto();
        dto.setFileName(files.getName());
        dto.setFileOriName(files.getOriginalFilename());
        dto.setFilePath(filePath);
        dto.setDivision(division);

        try {
            files.transferTo(dest);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        fileService.saveFile(dto, model);

        return "/admin/file";
    }
}

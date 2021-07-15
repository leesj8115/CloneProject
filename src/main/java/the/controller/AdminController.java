package the.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import the.domain.dto.item.ItemDto;
import the.domain.entity.file.FileEntity;
import the.domain.entity.item.LargeCategory;
import the.domain.entity.item.SmallCategory;
import the.service.file.FileService;
import the.service.item.ItemService;

@Slf4j
@Controller
public class AdminController {

    @Autowired
    private FileService fileService;
    
    @Autowired
    private ItemService itemService;
    
    // 상품 작성 완료시 사용하는 flag
	private boolean writeFlag = false;
    
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
    public String item(Model model) {

        LargeCategory[] large = LargeCategory.values();
        SmallCategory[] small = SmallCategory.values();
        
        model.addAttribute("large", large);
        model.addAttribute("small", small);
        
        if (writeFlag == true) {
            model.addAttribute("msg", "상품 등록을 완료했습니다.");
            writeFlag = false;
        }
        
        return "/admin/item";
    }
    
    @Transactional
    @PostMapping("/admin/item/write")
    public String itemWrite(ItemDto dto, @RequestParam("files[]") List<MultipartFile> files, Model model) {
        log.debug("상품 등록 실행");
        
    	// item을 등록하기 위해 차근차근 처리합시다..
        
    	// 이미지부터 처리
        List<FileEntity> photo = fileService.saveMultiFiles(files, "item");
        
    	// 엔티티 생성
        itemService.insert(dto, photo, model);
        
        writeFlag = true;   // 완료 msg 보내기 위한 변수

        return "redirect:/admin/item";
    }
    
    // 파일 업로드
    // 참고 : http://yoonbumtae.com/?p=2834
    @PostMapping("/admin/file/write")
    public String write(@RequestParam("files") MultipartFile files,String division, Model model) {        

        log.debug("파일 업로드 실행");
        
        fileService.saveFile(files, division, model);

        return "/admin/file";
    }
}

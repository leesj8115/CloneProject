package the.controller;

import java.io.File;
import java.io.IOException;
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
import the.domain.dto.FileDto;
import the.domain.dto.item.ItemDto;
import the.domain.dto.item.ShoesDto;
import the.domain.entity.file.FileEntity;
import the.domain.entity.item.Item;
import the.domain.entity.item.LargeCategory;
import the.service.FileService;
import the.service.item.ItemService;
import the.service.item.ShoesService;

@Slf4j
@Controller
public class AdminController {

    @Autowired
    private FileService fileService;
    
    @Autowired
    private ItemService itemService;
    
    @Autowired
    private ShoesService shoesService;
    
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
        
        fileService.saveFile(files, division, model);

        return "/admin/file";
    }
    
    @Transactional
    @PostMapping("/admin/item/write")
    public String itemWrite(ItemDto dto, String smallCategory, @RequestParam("files[]") List<MultipartFile> files, String division, Model model) {
    	
    	log.debug("상품 등록 실행");
    	
    	// item 작성하는 곳
    	
    	// shoesEntity를 만들려면 ItemEntity를 만들어야하고,
    	// ItemEntity를 만들려면 거기에 들어가는 stockList와 fileList를 연결해야함..
    	
    	// 이미지 파일부터 올립시다..
    	List<FileEntity> photo = fileService.saveMultiFiles(files, "item");
    	
    	// item 엔티티 만듭시다 이제
    	Item item = itemService.insert(dto, photo);
    	
    	// LargeCategory에 따라 나중에 신발, 의류, 용품이 나뉘게 된다
    	LargeCategory large = item.getLargeCategory();
    	
    	if (large == LargeCategory.SHOES) {
    		// 신발
    		shoesService.insert(smallCategory, item);
    	} else if (large == LargeCategory.CLOTHES) {
    		// 의류
    	
    	} else if (large == LargeCategory.SUPPLIES) {
    		// 용품
    	
    	}
    	
    	model.addAttribute("msg", "상품 등록을 완료했습니다.");
    	
    	return "/admin/item";
    }
    
}

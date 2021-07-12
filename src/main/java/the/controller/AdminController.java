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
import the.domain.entity.item.ClothesCategory;
import the.domain.entity.item.Item;
import the.domain.entity.item.LargeCategory;
import the.domain.entity.item.ShoesCategory;
import the.domain.entity.item.SuppliesCategory;
import the.service.file.FileService;
import the.service.item.ClothesService;
import the.service.item.ItemService;
import the.service.item.ShoesService;
import the.service.item.SuppliesService;

@Slf4j
@Controller
public class AdminController {

    @Autowired
    private FileService fileService;
    
    @Autowired
    private ItemService itemService;
    
    @Autowired
    private ShoesService shoesService;

    @Autowired
    private ClothesService clothesService;

    @Autowired
    private SuppliesService suppliesService;
    
    
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

        // 상품 등록 페이지로 이동
        // 이동간에 목록 출력에 필요한 데이터 전달
        // largeCategory, smallCategory 전달

        LargeCategory[] lc = LargeCategory.values();

        model.addAttribute("largeCategory", lc);

        ShoesCategory[] shoesCategories = ShoesCategory.values();
        ClothesCategory[] clothesCategories = ClothesCategory.values();
        SuppliesCategory[] suppliesCategories = SuppliesCategory.values();

        model.addAttribute("shoesCategory", shoesCategories);
        model.addAttribute("clothesCategory", clothesCategories);
        model.addAttribute("suppliesCategory", suppliesCategories);

        
        if (writeFlag == true) {
        	writeFlag = false;
        	model.addAttribute("msg", "상품 등록을 완료했습니다.");
        }
        
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
    public String itemWrite(ItemDto dto, String smallCategory, @RequestParam("files[]") List<MultipartFile> files, Model model) {

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
            log.debug("대분류 : 신발 에 대한 등록 서비스 실행");
            shoesService.insert(smallCategory, item);
        } else if (large == LargeCategory.CLOTHES) {
    		// 의류
            log.debug("대분류 : 의류 에 대한 등록 서비스 실행");
            clothesService.insert(smallCategory, item);

        } else if (large == LargeCategory.SUPPLIES) {
    		// 용품
            log.debug("대분류 : 용품 에 대한 등록 서비스 실행");
            suppliesService.insert(smallCategory, item);
        } else {
            log.debug("이게 보인다면 뭔가.. 뭔가 잘못된 것입니다");
            return null;
        }

        log.debug("등록 완료!!");
        
        writeFlag = true;

        return "redirect:/admin/item";
    }
    
}

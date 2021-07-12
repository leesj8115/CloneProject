package the.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import the.domain.dto.faq.FaqDto;
import the.service.cs.faq.FaqService;

@Slf4j
@Controller
public class CustomerserviceController {

    @Autowired
    FaqService faqService;

    @GetMapping("cs/index")
    public String index() {
        return "/cs/index";
    }
    
    @GetMapping({"/cs/faq/list", "/cs/faq"})
    public String faq() {
        // faq로 이동
        return "/cs/faq/list";
    }
    
    @ResponseBody
    @PutMapping("/cs/faq/{no}")
    public void edit(@PathVariable long no, FaqDto dto) {
        log.debug(no + "번 faq 수정");
        
        faqService.update(no, dto);
    }
    
    @ResponseBody
    @DeleteMapping("/cs/faq/{no}")
    public void deleteFaq(@PathVariable long no) {
    	// faq의 id 값을 받아서 삭제 예정
        log.debug("삭제할 no = " + no);
        
        faqService.delete(no);
        
    }

    @GetMapping("/cs/faq/write")
    public String write() {
        return "/cs/faq/write";
    }

    @PostMapping("/cs/faq/write")
    public String faqWrite(FaqDto dto) {

        faqService.write(dto);

        return "redirect:/cs/faq";
    }

/*
    주소에서 변수로 만들고 싶다면
    @GetMappling("/cs/faq/{지정할변수명}") 으로 하면
    public String getfaq(@PathVariable (type) (변수명)) 으로 사용 가능하다
*/

    // 요기서 FAQ 페이지의 데이터들 전달해줄겁니다!
    //@ResponseBody // 리턴 타입을 그대로 받겠다는 의미
    @GetMapping("/cs/faq/get")
    public String getFaq(String division, int pageNo, Model model) {
        log.debug("division = " + division);
        // 입력받은 분류에 해당하는 값들을 넘겨줍시다!!

        faqService.list(division, pageNo, model);

        return "/cs/faq/listdata";
    }

    @GetMapping("/cs/cbc")
    public String cbc() {
        // case by case
        // 1:1 문의로 이동
        return "/cs/cbc";
    }

    @GetMapping("/cs/bonus")
    public String bonus() {
        // 회원혜택으로 이동
        return "/cs/bonus";
    }

    @GetMapping("/cs/groupbuying")
    public String groupBuying() {
        // 단체주문 으로 이동
        return "/cs/faq";
    }

    @GetMapping("/cs/afterservice")
    public String afterService() {
        // A/S로 이동
        return "/cs/afterservice";
    }

    @GetMapping("/cs/notice")
    public String notice() {
        // 공지사항으로 이동
        return "/cs/notice";
    }
}

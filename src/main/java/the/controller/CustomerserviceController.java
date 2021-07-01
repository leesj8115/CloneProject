package the.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerserviceController {

    @GetMapping("cs/index")
    public String index() {
        return "/cs/index";
    }
    
    @GetMapping({"/cs/faq/list", "/cs/faq"})
    public String faq() {
        // faq로 이동
        return "/cs/faq/list";
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

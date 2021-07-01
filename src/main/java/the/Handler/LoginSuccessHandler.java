package the.Handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    // 로그인 완료시 실행되는 핸들러
    // 
    // 참고 사이트 : https://galid1.tistory.com/698
    


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        // 세션 확보
        //HttpSession session = request.getSession();
        
        // 세션에 대해 어트리뷰트 추가하려면 추가
        //session.setAttribute("good", "안녕");

        // 다시 메인 페이지로 보냄
        response.sendRedirect("/");

    }

}

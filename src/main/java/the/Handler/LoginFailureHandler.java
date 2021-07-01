package the.Handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    // 로그인 실패시 실행되는 핸들러
    // 출처 : https://dkyou.tistory.com/26

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        
        System.out.println("실패 핸들러 실행");

        String msg = "아이디 혹은 비밀번호를 확인해주세요";

        if (exception instanceof UsernameNotFoundException) {

        }
        else {
            msg = exception.getMessage();
        }

        request.setCharacterEncoding("UTF-8");

        setDefaultFailureUrl("/log/error?error=true&exception=" + msg);
        
        super.onAuthenticationFailure(request, response, exception);
    }
    
    
}

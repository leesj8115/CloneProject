package the.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import the.Handler.LoginFailureHandler;

//import the.Handler.LoginSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public LoginFailureHandler loginFailureHandler() {
		return new LoginFailureHandler();
	}

	@Autowired
	LoginFailureHandler failHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 페이지별 권한 설정
		http.authorizeRequests()
			.antMatchers(
				"/", "/log/**", "/display/**",
				"/cs/index", "/cs/notice", "/cs/faq/list", "/cs/faq", "/cs/faq/get").permitAll()
			.antMatchers("/cs/faq/write", "/cs/faq/remove", "/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated();
	
		// 로그인 설정
		http.formLogin()
			.loginPage("/log/login")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/", true)
			.failureHandler(failHandler)	// 실패시 동작하는 핸들러
			.permitAll();			
			//.successHandler(new LoginSuccessHandler());	// 핸들러 정의해도 됨! 지금은 메인으로 보내만 주면 돼서 패스
			
		// 로그아웃 설정
		http.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/");
			
		
		http.csrf().disable();
		
		http.oauth2Login().defaultSuccessUrl("/",true).userInfoEndpoint();
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		//super.configure(web);
		
		web.ignoring()
			.antMatchers("/resources/**", "/css/**", "/js/**", "/images/**");
		
	}
}

package com.coco.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    /*
	 * ==============================================
	 *	리소스 관련 파일에 대해서는 시큐리티 적용을 무시하는 설정을 합니다.
	 * ==============================================
	 */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 메인페이지 : css나 js 같은것들도 여기에 포함시켜준다.
        // 예를들어 이런식으로 인증할것들을 풀어주는겁니다. (주로 리소스)
        web
                .ignoring()
                .antMatchers(
                        "/css/**",
                        "/script/**",
                        "/"
                );
                //.antMatchers("/**"); // 앱을 실행하면 모든 페이지가 인증 요구가 풀려있습니다
    }


    /*
	 * ==============================================
	 *	스프링 시큐리티 설정을 커스터마이징 합니다.
	 * ==============================================
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // 우선순위가 중요하다
        // 먼저 Mapping되기 때문에 가장 포괄적인 부분을 인증을 요구하는 것을 먼저 선언하는게 중요
        http
                .authorizeRequests()
                .antMatchers("/mypage")
                .authenticated();

        // 여기에선 리소스외에 페이지의 인증/비인증/인증권한등을 설정하는게 좋은것 같습니다.
        http
                .authorizeRequests()
                // 어드민 권한으로만 접근할 수 있는 경로.
                .antMatchers("/admin/**")
                //.hasRole("ROLE_ADMIN")
                .access("ROLE_ADMIN");

        // 로그인 관련된 부분
        http
                // 로그인 페이지 : 컨트롤러 매핑을 하지 않으면 기본 제공되는 로그인 페이지가 뜬다.
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/sign-in/auth")
                .failureUrl("/sign-in?error=exception")
                .defaultSuccessUrl("/")
                .permitAll();

        http
                .logout()
                // /logout 을 호출할 경우 로그아웃
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // 로그아웃이 성공했을 경우 이동할 페이지
                .logoutSuccessUrl("/");
    }
}
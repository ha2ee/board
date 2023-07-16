package hello.board.config;

import hello.board.interceptor.LogInterceptor;
import hello.board.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
//                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","/images/**","/js/**","*.ico");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
//                .addPathPatterns("/**")
                .excludePathPatterns("/members/**", "/css/**","/images/**","/js/**","*.ico");
    }
}

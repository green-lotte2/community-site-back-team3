package kr.co.orangenode.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AppInfo appInfo;

    // ====== 배포시엔 해당 어노테이션 사용 ======
    //@Value("${myServerProd.static-resources-path}")
    // ====== 개발시엔 해당 어노테이션 사용 ======
    @Value("${localProd.static-resources-pathProd}")
    private String staticServerPathProd;

    // ====== 배포시엔 해당 어노테이션 사용 ======
    //@Value("${MyServerImg.static-resources-pathImg}")
    // ====== 개발시엔 해당 어노테이션 사용 ======
    @Value("${localImg.static-resources-pathImg}")
    private String staticServerPathImg;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 상품 경로
        registry.addResourceHandler("prodImg/**")
                .addResourceLocations("file:" + staticServerPathProd);
        // 글 경로
        registry.addResourceHandler("uploads/**")
                .addResourceLocations("file:" + staticServerPathImg);
    }
    // CORS 방지를 위한 설정
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
/*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AppInfoInterceptor(appInfo));


        // myPage 배너 Interceptor 추가
        registry.addInterceptor(new MyBannerInterceptor(bannerService))
                .addPathPatterns("/my/**");
    }*/
}
package kr.co.orangenode.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AppInfo appInfo;

    @Value("${myServerProd.static-resources-path}")
    private String staticServerPathProd;

    // ====== 배포시엔 해당 어노테이션 사용 ======
    @Value("${MyServerImg.static-resources-pathImg}")
    private String staticServerPathImg;

    @Value("${front.url}")
    private String frontUrl;

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
                .allowedOrigins(frontUrl, "http://3.34.204.24")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Authorization", "Cache-Control", "Content-Type", "X-Requested-With", "Origin", "Accept")
                .allowCredentials(true);
    }
}
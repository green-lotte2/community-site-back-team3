package kr.co.orangenode.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@EnableCaching
@Configuration
public class CacheConfig {

    @Primary
    @Bean
    public CacheManager cacheManager1(){
        return new ConcurrentMapCacheManager("cate1Cache");
    }

    @Bean
    public CacheManager cacheManager2(){
        return new ConcurrentMapCacheManager("cate2Cache");
    }

    @Bean
    public CacheManager cacheManager3(){
        return new ConcurrentMapCacheManager("cate3Cache");
    }

}

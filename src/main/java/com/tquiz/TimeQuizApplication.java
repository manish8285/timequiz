package com.tquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class TimeQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeQuizApplication.class, args);
	}
	
	
	
	// configuration for Cache
	@Bean
    CacheManagerCustomizer<ConcurrentMapCacheManager> customizer(){
        return new ConcurrentCustomizer();
    }

    class ConcurrentCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager>{

        @Override
        public void customize(ConcurrentMapCacheManager cacheManager) {
            cacheManager.setAllowNullValues(false);
            //cacheManager.setStoreByValue(true);
            System.out.println("customizer invoked!!");
        }
    }

}

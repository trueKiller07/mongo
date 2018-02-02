package com.sameer.demo.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>RedisConfiguration class.</p>
 *
 */
@EnableCaching
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.cache.annotation.CachingConfigurerSupport#errorHandler()
     */
    /** {@inheritDoc} */
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        return new CustomCacheErrorHandler();
    }

}
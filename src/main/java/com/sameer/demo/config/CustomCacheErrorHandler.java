package com.sameer.demo.config;


import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>CustomCacheErrorHandler class.</p>
 */
@Slf4j
public class CustomCacheErrorHandler implements CacheErrorHandler {

    /** {@inheritDoc} */
    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        System.out.println("Cache {} is down to search for key :{} with exception :{}"+
                cache.getName()+ key+ exception.getMessage());
    }

    /** {@inheritDoc} */
    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key,
            Object value) {
        System.out.println("Cache {} is down to put for key :{} with exception :{}"+
                cache.getName()+ key+ exception.getMessage());
    }

    /** {@inheritDoc} */
    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache,
            Object key) {
        System.out.println("Cache {} is down to evict for key :{} with exception :{}"+
                cache.getName()+ key+ exception.getMessage());
    }

    /** {@inheritDoc} */
    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        System.out.println("Cache {} is down to clear with exception :{}"+ cache.getName()+
                exception.getMessage());
    }
}
package com.dtyunxi.content.config;

import com.dtyunxi.huieryun.cache.api.CacheFactory;
import com.dtyunxi.huieryun.cache.api.ICacheService;
import com.dtyunxi.huieryun.cache.vo.CacheRegistryVo;
import com.dtyunxi.huieryun.registry.api.IRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CenterContentCacheConfig {

    @Autowired
    private IRegistryService centerContentRegistryService;

    @Bean
    public ICacheService centerContentCacheService() {
        String profile = centerContentRegistryService.get("huieryun.active.profile");
        CacheRegistryVo cacheRegistryVo = centerContentRegistryService.getObject("huieryun.cacheregistryvo", CacheRegistryVo.class);
        return CacheFactory.createCache("huieryun.newretail.center.content." + profile, cacheRegistryVo);
    }
}

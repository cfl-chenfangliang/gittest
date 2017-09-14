package com.dtyunxi.content.config;

import com.dtyunxi.app.AppEnv;
import com.dtyunxi.huieryun.registry.api.IRegistryService;
import com.dtyunxi.huieryun.registry.api.RegistryServiceFactory;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration("centerContentRootContextConfig")
@PropertySource({ "classpath:config/fuli/content/config.properties" })
@EnableSwagger2
public class CenterContentRootContextConfig {
	@Autowired
	private Environment centerContentEnvironment;

	@Bean
	public IRegistryService centerContentRegistryService() {
		return RegistryServiceFactory.createRegistryService(centerContentEnvironment);
	}

	@Bean
	public AppEnv centerContentAppEnv(IRegistryService centerContentRegistryService) {
		return new AppEnv();
	}
	
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.dtyunxi")).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("内容中心api").description("后台管理用")
				.contact("陈肪良").version("1.0").build();
	}
}
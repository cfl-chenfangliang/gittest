/**
 * @(#)StartMain.java 1.0 2017年8月16日
 *
 * Copyright (c) 2016, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dtyunxi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.dtyunxi.StartMain;
import com.taobao.pandora.boot.PandoraBootstrap;

/**
 * @author chen.fangliang
 *
 */
@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages = { "com.dtyunxi.content", "com.dtyunxi.newretail.center.content" }, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = { "com.dtyunxi.newretail.center.content.config.*" }) })
public class StartMain {
	public static void main(String[] args) {
		PandoraBootstrap.run(args);
		SpringApplication app = new SpringApplication(StartMain.class);
		app.run(args);
		PandoraBootstrap.markStartupAndWait();
	}
}

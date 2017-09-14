/**
 * @(#)IArticleStatApiServiceController.java 1.0 2017年9月11日
 *
 * Copyright (c) 2016, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dtyunxi.content.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dtyunxi.newretail.center.content.api.IArticleStatApiService;
import com.dtyunxi.newretail.center.content.dto.ArticleDto;
import com.dtyunxi.rest.RestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author chen.fangliang
 *
 */
@Api(tags = { "内容中心-内容文章统计服务接口" })
@RestController
@RequestMapping("/IArticleStatApiService")
public class IArticleStatApiServiceController {
	
	@Resource(name = "centerContentArticleStatApiService")
	private IArticleStatApiService articleStatApiService;

	@ApiOperation(value = "文章阅读数加1", notes = "操作失败抛出CenterContentBusinessException异常")
	@ApiImplicitParams({ @ApiImplicitParam(name = "articleDto", value = "文章Dto对象", dataType = "ArticleDto") })
	@RequestMapping(value = "increaseReadCount/addone", method = RequestMethod.POST)
	public RestResponse increaseReadCount(ArticleDto articleDto) {
		articleStatApiService.increaseReadCount(articleDto);
		return new RestResponse();
	}

	@ApiOperation(value = "文章阅读数增加", notes = "操作失败抛出CenterContentBusinessException异常")
	@ApiImplicitParams({ @ApiImplicitParam(name = "articleDto", value = "文章Dto对象", dataType = "ArticleDto") , @ApiImplicitParam(name = "increment", value = "增加数", dataType = "long") })
	@RequestMapping(value = "increaseReadCount", method = RequestMethod.POST)
	public RestResponse increaseReadCount(ArticleDto articleDto , long increment) {
		articleStatApiService.increaseReadCount(articleDto , increment);
		return new RestResponse();
	}


}

/**
 * @(#)ArticleApiServiceController.java 1.0 2017年9月11日
 *
 * Copyright (c) 2016, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dtyunxi.content.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dtyunxi.newretail.center.content.api.IArticleApiService;
import com.dtyunxi.newretail.center.content.constant.ArticleStatus;
import com.dtyunxi.newretail.center.content.dto.ArticleDto;
import com.dtyunxi.newretail.center.content.dto.ArticleFrontendDto;
import com.dtyunxi.rest.RestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author chen.fangliang
 *
 */
@Api(tags = { "内容中心-内容文章服务接口" })
@RestController
@RequestMapping("/IArticleApiService")
public class ArticleApiServiceController {
	
	@Resource(name = "centerContentArticleApiService")
	private IArticleApiService articleApiService;

	@ApiOperation(value = "审核内容文章并更新状态到新状态", notes = "操作失败抛出CenterContentBusinessException异常", response = Long.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "articleDto", value = "内容文章对象", dataType = "ArticleDto"),
			@ApiImplicitParam(name = "newStatus", value = "文章新状态", dataType = "ArticleStatus") })
	@RequestMapping(value = "auditArticle", method = RequestMethod.POST)
	public RestResponse auditArticle(ArticleDto articleDto, ArticleStatus newStatus) {
		articleApiService.auditArticle(articleDto, newStatus);
		return new RestResponse();
	}

	@ApiOperation(value = "发布内容文章", notes = "操作失败抛出CenterContentBusinessException异常", response = Long.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "articleDto", value = "内容文章对象", dataType = "ArticleDto") })
	@RequestMapping(value = "publishArticle", method = RequestMethod.POST)
	public RestResponse publishArticle(ArticleDto articleDto) {
		articleApiService.publishArticle(articleDto);
		return new RestResponse();
	}

	@ApiOperation(value = "新增内容文章", notes = "操作失败抛出CenterContentBusinessException异常", response = Long.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleFrontendDto", value = "内容文章Dto对象", dataType = "ArticleFrontendDto") })
	@RequestMapping(value = "saveArticle", method = RequestMethod.POST)
	public RestResponse saveArticle(ArticleFrontendDto articleFrontendDto) {
		return new RestResponse(articleApiService.saveArticle(articleFrontendDto));
	}

	@ApiOperation(value = "修改内容文章", notes = "操作失败抛出CenterContentBusinessException异常", response = Long.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleFrontendDto", value = "内容文章Dto对象", dataType = "ArticleFrontendDto") })
	@RequestMapping(value = "updateArticle", method = RequestMethod.POST)
	public RestResponse updateArticle(ArticleFrontendDto articleFrontendDto) {
		return new RestResponse(articleApiService.updateArticle(articleFrontendDto));
	}

	@ApiOperation(value = "删除内容文章", notes = "操作失败抛出CenterContentBusinessException异常", response = Long.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleFrontendDto", value = "内容文章Dto对象", dataType = "ArticleFrontendDto") })
	@RequestMapping(value = "deleteArticle", method = RequestMethod.POST)
	public RestResponse deleteArticle(ArticleFrontendDto articleFrontendDto) {
		articleApiService.deleteArticle(articleFrontendDto);
		return new RestResponse();
	}

	@ApiOperation(value = "新增并发布内容文章", notes = "操作失败抛出CenterContentBusinessException异常", response = Long.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleFrontendDto", value = "内容文章Dto对象", dataType = "ArticleFrontendDto") })
	@RequestMapping(value = "saveAndPublishArticle", method = RequestMethod.POST)
	public RestResponse saveAndPublishArticle(ArticleFrontendDto articleFrontendDto) {
		return new RestResponse(articleApiService.saveAndPublishArticle(articleFrontendDto));
	}

}

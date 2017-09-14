/**
 * @(#)IArticleQueryApiServiceController.java 1.0 2017年9月11日
 *
 * Copyright (c) 2016, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dtyunxi.content.query.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dtyunxi.newretail.center.content.api.query.IArticleQueryApiService;
import com.dtyunxi.newretail.center.content.dto.ArticleDto;
import com.dtyunxi.newretail.center.content.dto.ArticleFrontendDto;
import com.dtyunxi.newretail.center.content.dto.ArticleWithNavigationDto;
import com.dtyunxi.newretail.center.content.dto.ArticleWithStatDto;
import com.dtyunxi.newretail.center.content.dto.query.ArticleQueryConditionDto;
import com.dtyunxi.rest.RestResponse;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author chen.fangliang
 *
 */
@Api(tags = { "内容中心-内容文章查询服务接口" })
@RestController
@RequestMapping("/IArticleQueryApiService")
public class IArticleQueryApiServiceController {

	@Resource(name = "centerContentArticleQueryApiService")
	private IArticleQueryApiService articleQueryApiService;

	@ApiOperation(value = "根据文章Id查询文章前端Dto", notes = "", response = ArticleFrontendDto.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "articleId", value = "文章ID", dataType = "Long") })
	@RequestMapping(value = "selectArticleFrontendDtoById", method = RequestMethod.GET)
	public RestResponse selectArticleFrontendDtoById(Long articleId) {
		return new RestResponse(articleQueryApiService.selectArticleFrontendDtoById(articleId));
	}

	@ApiOperation(value = "分页查询文章前端Dto记录", notes = "", response = PageInfo.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "queryVo", value = "文章查询Dto", dataType = "ArticleDto"),
			@ApiImplicitParam(name = "pageNum", value = "页码数", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "int") })
	@RequestMapping(value = "selectPageArticleFrontendDtos", method = RequestMethod.POST)
	public RestResponse selectPageArticleFrontendDtos(ArticleDto queryVo, int pageNum, int pageSize) {
		return new RestResponse(articleQueryApiService.selectPageArticleFrontendDtos(queryVo, pageNum, pageSize));
	}

	@ApiOperation(value = "根据文章Id查询文章前端Dto", notes = "", response = ArticleWithStatDto.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "articleId", value = "文章ID", dataType = "Long") })
	@RequestMapping(value = "selectArticleWithStatDtoById", method = RequestMethod.GET)
	public RestResponse selectArticleWithStatDtoById(Long articleId) {
		return new RestResponse(articleQueryApiService.selectArticleWithStatDtoById(articleId));
	}

	@ApiOperation(value = "根据文章Id查询文章", notes = "", response = ArticleDto.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "articleId", value = "文章ID", dataType = "Long") })
	@RequestMapping(value = "selectArticleById", method = RequestMethod.GET)
	public RestResponse selectArticleById(Long articleId) {
		return new RestResponse(articleQueryApiService.selectArticleById(articleId));
	}

	@ApiOperation(value = "分页查询文章记录", notes = "", response = PageInfo.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "queryDto", value = "文章查询Dto", dataType = "ArticleEo"),
			@ApiImplicitParam(name = "pageNum", value = "页码数", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "int") })
	@RequestMapping(value = "selectPageArticles", method = RequestMethod.POST)
	public RestResponse selectPageArticles(ArticleDto queryDto, int pageNum, int pageSize) {
		return new RestResponse(articleQueryApiService.selectPageArticles(queryDto, pageNum, pageSize));
	}

	@ApiOperation(value = "分页查询文章前端Dto记录", notes = "", response = PageInfo.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "queryDto", value = "文章查询Dto", dataType = "ArticleDto"),
			@ApiImplicitParam(name = "pageNum", value = "页码数", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "int") })
	@RequestMapping(value = "selectPageArticleWithStatDtos", method = RequestMethod.POST)
	public RestResponse selectPageArticleWithStatDtos(ArticleDto queryDto, int pageNum, int pageSize) {
		return new RestResponse(articleQueryApiService.selectPageArticleWithStatDtos(queryDto, pageNum, pageSize));
	}

	@ApiOperation(value = "根据文章Id查询文章导航Dto", notes = "", response = ArticleWithNavigationDto.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "articleId", value = "文章ID", dataType = "Long") })
	@RequestMapping(value = "selectArticleWithNavigationDtoById", method = RequestMethod.GET)
	public RestResponse selectArticleWithNavigationDtoById(Long articleId) {
		return new RestResponse(articleQueryApiService.selectArticleWithNavigationDtoById(articleId));
	}

	@ApiOperation(value = "根据查询条件Dto分页查询文章前端Dto", notes = "", response = PageInfo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleQueryConditionDto", value = "查询条件Dto", dataType = "ArticleQueryConditionDto"),
			@ApiImplicitParam(name = "pageNum", value = "页码数", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "int") })
	@RequestMapping(value = "selectPageArticleWithStatDtosByQueryConditionDto", method = RequestMethod.POST)
	public RestResponse selectPageArticleWithStatDtosByQueryConditionDto(
			ArticleQueryConditionDto articleQueryConditionDto, int pageNum, int pageSize) {
		return new RestResponse(articleQueryApiService
				.selectPageArticleWithStatDtosByQueryConditionDto(articleQueryConditionDto, pageNum, pageSize));
	}

	@ApiOperation(value = "根据查询条件Dto分页查询文章前端Dto记录", notes = "", response = PageInfo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleQueryConditionDto", value = "查询条件Dto", dataType = "ArticleQueryConditionDto"),
			@ApiImplicitParam(name = "pageNum", value = "页码数", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "int") })
	@RequestMapping(value = "selectPageArticleFrontendDtosByQueryCondition", method = RequestMethod.POST)
	public RestResponse selectPageArticleFrontendDtosByQueryCondition(ArticleQueryConditionDto articleQueryConditionDto,
			int pageNum, int pageSize) {
		return new RestResponse(articleQueryApiService
				.selectPageArticleFrontendDtosByQueryCondition(articleQueryConditionDto, pageNum, pageSize));
	}

	@ApiOperation(value = "根据文章Id查询文章前端Dto并使阅读数加1", notes = "", response = ArticleWithStatDto.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "articleId", value = "文章ID", dataType = "Long") })
	@RequestMapping(value = "selectArticleWithStatDtoAndIncreaseReadCountById", method = RequestMethod.GET)
	public RestResponse selectArticleWithStatDtoAndIncreaseReadCountById(Long articleId) {
		return new RestResponse(articleQueryApiService.selectArticleWithStatDtoAndIncreaseReadCountById(articleId));
	}

}

/**
 * @(#)ICommentQueryApiServiceController.java 1.0 2017年9月11日
 *
 * Copyright (c) 2016, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dtyunxi.content.query.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dtyunxi.newretail.center.content.api.query.ICommentQueryApiService;
import com.dtyunxi.newretail.center.content.dto.CommentDto;
import com.dtyunxi.newretail.center.content.dto.query.CommentQueryConditionDto;
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
@Api(tags = { "内容中心-内容评论查询服务接口" })
@RestController
@RequestMapping("/ICommentQueryApiService")
public class ICommentQueryApiServiceController {

	@Resource(name = "centerContentCommentQueryApiService")
	private ICommentQueryApiService commentQueryApiService;

	@ApiOperation(value = "根据评论Id查询评论", notes = "", response = CommentDto.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "commentId", value = "评论ID", dataType = "Long") })
	@RequestMapping(value = "selectCommentById", method = RequestMethod.GET)
	public RestResponse selectCommentById(Long commentId) {
		return new RestResponse(commentQueryApiService.selectCommentById(commentId));
	}

	@ApiOperation(value = "分页查询评论", notes = "", response = PageInfo.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "queryDto", value = "查询Dto对象", dataType = "CommentDto"),
			@ApiImplicitParam(name = "pageNum", value = "页码数", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "int") })
	@RequestMapping(value = "selectPageComments", method = RequestMethod.POST)
	public RestResponse selectPageComments(CommentDto queryDto, int pageNum, int pageSize) {
		return new RestResponse(commentQueryApiService.selectPageComments(queryDto, pageNum, pageSize));
	}

	@ApiOperation(value = "分页查询评论统计Dto", notes = "", response = PageInfo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "commentQueryConditionDto", value = "查询条件Dto对象", dataType = "CommentQueryConditionDto"),
			@ApiImplicitParam(name = "pageNum", value = "页码数", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "int") })
	@RequestMapping(value = "selectPageCommentWithStatDtos", method = RequestMethod.POST)
	public RestResponse selectPageCommentWithStatDtos(CommentQueryConditionDto commentQueryConditionDto, int pageNum,
			int pageSize) {
		return new RestResponse(
				commentQueryApiService.selectPageCommentWithStatDtos(commentQueryConditionDto, pageNum, pageSize));
	}

}

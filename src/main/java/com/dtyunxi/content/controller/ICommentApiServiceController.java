/**
 * @(#)ICommentApiServiceController.java 1.0 2017年9月11日
 *
 * Copyright (c) 2016, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dtyunxi.content.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dtyunxi.newretail.center.content.api.ICommentApiService;
import com.dtyunxi.newretail.center.content.dto.CommentDto;
import com.dtyunxi.rest.RestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author chen.fangliang
 *
 */
@Api(tags = { "内容中心-内容评论服务接口" })
@RestController
@RequestMapping("/ICommentApiService")
public class ICommentApiServiceController {

	@Resource(name = "centerContentCommentApiService")
	private ICommentApiService commentApiService;

	@ApiOperation(value = "删除内容评论", notes = "操作失败抛出CenterContentBusinessException异常", response = Long.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "commentDto", value = "内容评论Dto对象", dataType = "CommentDto") })
	@RequestMapping(value = "deleteComment", method = RequestMethod.POST)
	public RestResponse deleteComment(CommentDto commentDto) {
		commentApiService.deleteComment(commentDto);
		return new RestResponse();
	}

	@ApiOperation(value = "新增内容评论", notes = "操作失败抛出CenterContentBusinessException异常", response = Long.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "commentDto", value = "内容评论Dto对象", dataType = "CommentDto") })
	@RequestMapping(value = "saveComment", method = RequestMethod.POST)
	public RestResponse saveComment(CommentDto commentDto) {
		return new RestResponse(commentApiService.saveComment(commentDto));
	}

	@ApiOperation(value = "修改内容评论", notes = "操作失败抛出CenterContentBusinessException异常", response = Long.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "commentDto", value = "内容评论Dto对象", dataType = "CommentDto") })
	@RequestMapping(value = "updateComment", method = RequestMethod.POST)
	public RestResponse updateComment(CommentDto commentDto) {
		return new RestResponse(commentApiService.updateComment(commentDto));
	}

}

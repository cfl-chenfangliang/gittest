/**
 * @(#)ITagQueryApiServiceController.java 1.0 2017年9月11日
 *
 * Copyright (c) 2016, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dtyunxi.content.query.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dtyunxi.newretail.center.content.api.query.ITagQueryApiService;
import com.dtyunxi.newretail.center.content.dto.TagDto;
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
@Api(tags = { "内容中心-内容标签查询服务接口" })
@RestController
@RequestMapping("/ITagQueryApiService")
public class ITagQueryApiServiceController {
	
	@Resource(name = "centerContentTagQueryApiService")
	private ITagQueryApiService tagQueryApiService;

	@ApiOperation(value = "根据ID查询标签", notes = "",response = TagDto.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "tagId", value = "标签ID", dataType = "Long") })
	@RequestMapping(value = "selectTagById", method = RequestMethod.GET)
	public RestResponse selectTagById(Long tagId) {
		return new RestResponse(tagQueryApiService.selectTagById(tagId));
	}

	@ApiOperation(value = "分页查询标签信息", notes = "",response = PageInfo.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "queryDto", value = "查询标签对象", dataType = "TagDto") , @ApiImplicitParam(name = "pageNum", value = "页码数", dataType = "int") , @ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "int") })
	@RequestMapping(value = "selectPageTags", method = RequestMethod.POST)
	public RestResponse selectPageTags(TagDto queryDto , int pageNum , int pageSize) {
		return new RestResponse(tagQueryApiService.selectPageTags(queryDto , pageNum , pageSize));
	}


}

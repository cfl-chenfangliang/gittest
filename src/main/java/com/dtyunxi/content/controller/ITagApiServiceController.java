/**
 * @(#)ITagApiServiceController.java 1.0 2017年9月11日
 *
 * Copyright (c) 2016, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dtyunxi.content.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dtyunxi.newretail.center.content.api.ITagApiService;
import com.dtyunxi.newretail.center.content.dto.TagDto;
import com.dtyunxi.rest.RestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author chen.fangliang
 *
 */
@Api(tags = { "内容中心-内容标签服务接口" })
@RestController
@RequestMapping("/ITagApiService")
public class ITagApiServiceController {

	@Resource(name = "centerContentTagApiService")
	private ITagApiService tagApiService;

	@ApiOperation(value = "删除内容标签", notes = "与文章关联时，标签不能删除", response = Long.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "tagDto", value = "内容标签Dto对象", dataType = "TagDto") })
	@RequestMapping(value = "deleteTag", method = RequestMethod.POST)
	public RestResponse deleteTag(TagDto tagDto) {
		tagApiService.deleteTag(tagDto);
		return new RestResponse();
	}

	@ApiOperation(value = "新增内容标签", notes = "", response = Long.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "tagDto", value = "内容标签Dto对象", dataType = "TagDto") })
	@RequestMapping(value = "saveTag", method = RequestMethod.POST)
	public RestResponse saveTag(TagDto tagDto) {
		return new RestResponse(tagApiService.saveTag(tagDto));
	}

	@ApiOperation(value = "修改内容标签", notes = "", response = Long.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "tagDto", value = "内容标签Dto对象", dataType = "TagDto") })
	@RequestMapping(value = "updateTag", method = RequestMethod.POST)
	public RestResponse updateTag(TagDto tagDto) {
		return new RestResponse(tagApiService.updateTag(tagDto));
	}

}

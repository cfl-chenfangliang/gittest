/**
 * @(#)IMemberActionQueryApiServiceController.java 1.0 2017年9月11日
 *
 * Copyright (c) 2016, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dtyunxi.content.query.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dtyunxi.newretail.center.content.api.query.IMemberActionQueryApiService;
import com.dtyunxi.newretail.center.content.dto.MemberActionDto;
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
@Api(tags = { "内容中心-会员行为查询服务接口" })
@RestController
@RequestMapping("/IMemberActionQueryApiService")
public class IMemberActionQueryApiServiceController {

	@Resource(name = "centerContentMemberActionQueryApiService")
	private IMemberActionQueryApiService memberActionQueryApiService;

	@ApiOperation(value = "按查询Vo以及目标Id数组分页查询会员行为记录", notes = "", response = PageInfo.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "queryDto", value = "查询Dto对象", dataType = "MemberActionDto"),
			@ApiImplicitParam(name = "targetIds", value = "目标Id数组", dataType = "Long[]"),
			@ApiImplicitParam(name = "pageNum", value = "页码数", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "int") })
	@RequestMapping(value = "selectPageMemberActionsByMemberActionAndTargetIds", method = RequestMethod.POST)
	public RestResponse selectPageMemberActionsByMemberActionAndTargetIds(MemberActionDto queryDto, Long[] targetIds,
			int pageNum, int pageSize) {
		return new RestResponse(memberActionQueryApiService.selectPageMemberActionsByMemberActionAndTargetIds(queryDto,
				targetIds, pageNum, pageSize));
	}

	@ApiOperation(value = "分页查询会员行为记录", notes = "", response = PageInfo.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "queryDto", value = "查询Dto对象", dataType = "MemberActionDto"),
			@ApiImplicitParam(name = "pageNum", value = "页码数", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "int") })
	@RequestMapping(value = "selectPageMemberActions", method = RequestMethod.POST)
	public RestResponse selectPageMemberActions(MemberActionDto queryDto, int pageNum, int pageSize) {
		return new RestResponse(memberActionQueryApiService.selectPageMemberActions(queryDto, pageNum, pageSize));
	}

}

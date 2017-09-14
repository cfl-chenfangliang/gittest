/**
 * @(#)IMemberActionApiServiceController.java 1.0 2017年9月11日
 *
 * Copyright (c) 2016, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dtyunxi.content.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dtyunxi.newretail.center.content.api.IMemberActionApiService;
import com.dtyunxi.newretail.center.content.dto.MemberActionDto;
import com.dtyunxi.rest.RestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author chen.fangliang
 *
 */
@Api(tags = { "内容中心-会员行为服务接口" })
@RestController
@RequestMapping("/IMemberActionApiService")
public class IMemberActionApiServiceController {

	@Resource(name = "centerContentMemberActionApiService")
	private IMemberActionApiService memberActionApiService;

	@ApiOperation(value = "新增会员行为", notes = "新增会员行为失败时抛出CenterContentBusinessException异常")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "memberActionDto", value = "会员行为Dto对象", dataType = "MemberActionDto") })
	@RequestMapping(value = "saveMemberAction", method = RequestMethod.POST)
	public RestResponse saveMemberAction(MemberActionDto memberActionDto) {
		return new RestResponse(memberActionApiService.saveMemberAction(memberActionDto));
	}

}

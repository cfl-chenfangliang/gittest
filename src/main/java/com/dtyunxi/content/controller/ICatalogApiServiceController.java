/**
 * @(#)ICatalogApiServiceController.java 1.0 2017年9月11日
 *
 * Copyright (c) 2016, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dtyunxi.content.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dtyunxi.newretail.center.content.api.ICatalogApiService;
import com.dtyunxi.newretail.center.content.dto.CatalogDto;
import com.dtyunxi.rest.RestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author chen.fangliang
 *
 */
@Api(tags = { "内容中心-内容类别服务接口" })
@RestController
@RequestMapping("/ICatalogApiService")
public class ICatalogApiServiceController {
	
	@Resource(name = "centerContentCatalogApiService")
	private ICatalogApiService catalogApiService;

	@ApiOperation(value = "修改内容类别", notes = "操作失败抛出CenterContentBusinessException异常", response = Long.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "catalogDto", value = "内容类别Dto对象", dataType = "CatalogDto") })
	@RequestMapping(value = "updateCatalog", method = RequestMethod.POST)
	public RestResponse updateCatalog(CatalogDto catalogDto) {
		return new RestResponse(catalogApiService.updateCatalog(catalogDto));
	}

	@ApiOperation(value = "新增内容类别", notes = "操作失败抛出CenterContentBusinessException异常", response = Long.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "catalogDto", value = "内容类别Dto对象", dataType = "CatalogDto") })
	@RequestMapping(value = "saveCatalog", method = RequestMethod.POST)
	public RestResponse saveCatalog(CatalogDto catalogDto) {
		return new RestResponse(catalogApiService.saveCatalog(catalogDto));
	}

	@ApiOperation(value = "删除内容类别", notes = "操作失败抛出CenterContentBusinessException异常", response = Long.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "catalogDto", value = "内容类别Dto对象", dataType = "CatalogDto") })
	@RequestMapping(value = "deleteCatalog", method = RequestMethod.POST)
	public RestResponse deleteCatalog(CatalogDto catalogDto) {
		catalogApiService.deleteCatalog(catalogDto);
		return new RestResponse();
	}

}

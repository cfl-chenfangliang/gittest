/**
 * @(#)ICatalogQueryApiServiceController.java 1.0 2017年9月11日
 *
 * Copyright (c) 2016, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dtyunxi.content.query.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dtyunxi.newretail.center.content.api.query.ICatalogQueryApiService;
import com.dtyunxi.newretail.center.content.dto.CatalogDto;
import com.dtyunxi.newretail.center.content.eo.CatalogEo;
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
@Api(tags = { "内容中心-内容类别查询服务接口" })
@RestController
@RequestMapping("/ICatalogQueryApiService")
public class ICatalogQueryApiServiceController {

	@Resource(name = "centerContentCatalogQueryApiService")
	private ICatalogQueryApiService catalogQueryApiService;

	@ApiOperation(value = "分页查询内容类别", notes = "", response = PageInfo.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "queryDto", value = "查询类别Dto对象", dataType = "CatalogDto"),
			@ApiImplicitParam(name = "pageNum", value = "页码数", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "int") })
	@RequestMapping(value = "selectPageCatalogs", method = RequestMethod.POST)
	public RestResponse selectPageCatalogs(CatalogDto queryDto, int pageNum, int pageSize) {
		return new RestResponse(catalogQueryApiService.selectPageCatalogs(queryDto, pageNum, pageSize));
	}

	@ApiOperation(value = "根据ID查询类别", notes = "", response = CatalogEo.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "catalogId", value = "类别ID", dataType = "Long") })
	@RequestMapping(value = "selectCatalogById", method = RequestMethod.GET)
	public RestResponse selectCatalogById(Long catalogId) {
		return new RestResponse(catalogQueryApiService.selectCatalogById(catalogId));
	}

	@ApiOperation(value = "根据类别Dto查询类别集合", notes = "", response = List.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "queryDto", value = "查询Dto", dataType = "CatalogDto") })
	@RequestMapping(value = "selectCatalogs", method = RequestMethod.POST)
	public RestResponse selectCatalogs(CatalogDto queryDto) {
		return new RestResponse(catalogQueryApiService.selectCatalogs(queryDto));
	}

}

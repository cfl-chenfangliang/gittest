/**
 * @(#)MemberQueryTest.java 1.0 2017年8月26日
 *
 * Copyright (c) 2016, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dtyunxi.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dtyunxi.StartMain;
import com.dtyunxi.newretail.center.content.api.query.IArticleQueryApiService;
import com.dtyunxi.newretail.center.content.api.query.ICatalogQueryApiService;
import com.dtyunxi.newretail.center.content.api.query.ICommentQueryApiService;
import com.dtyunxi.newretail.center.content.api.query.IMemberActionQueryApiService;
import com.dtyunxi.newretail.center.content.api.query.ITagQueryApiService;
import com.dtyunxi.newretail.center.content.dto.ArticleDto;
import com.dtyunxi.newretail.center.content.dto.CatalogDto;
import com.dtyunxi.newretail.center.content.dto.CommentDto;
import com.dtyunxi.newretail.center.content.dto.MemberActionDto;
import com.dtyunxi.newretail.center.content.dto.TagDto;
import com.dtyunxi.newretail.center.content.dto.query.ArticleQueryConditionDto;
import com.dtyunxi.newretail.center.content.dto.query.CommentQueryConditionDto;
import com.taobao.hsf.lightapi.ServiceFactory;

/**
 * @author chen.fangliang
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartMain.class)
public class ContentrQueryTest {
	static {
		ServiceFactory.getInstanceWithPath("d:/");
	}

	@Resource(name = "iCatalogQueryApiServiceConsumer")
	private ICatalogQueryApiService iCatalogQueryApiService;

	/**
	 * com.dtyunxi.newretail.center.content.api.ICatalogQueryApiService
	 */
	@Test
	public void iCatalogQueryApiService() throws InterruptedException {
		
		System.out.println(iCatalogQueryApiService.selectCatalogById(2L));
		
		CatalogDto catalogVo = new CatalogDto();
		catalogVo.setName("文章");
		
		System.out.println(iCatalogQueryApiService.selectCatalogs(catalogVo));
		
		System.out.println(iCatalogQueryApiService.selectPageCatalogs(catalogVo, 1, 1));
		
	}

	
	@Resource(name = "iArticleQueryApiServiceConsumer")
	private IArticleQueryApiService iArticleQueryApiService;

	/**
	 * com.dtyunxi.newretail.center.content.api.IArticleQueryApiService
	 */
	@Test
	public void iArticleQueryApiService() throws InterruptedException {
		
		iArticleQueryApiService.selectArticleById(5L);
		
		iArticleQueryApiService.selectArticleFrontendDtoById(5L);
		
		iArticleQueryApiService.selectArticleWithNavigationDtoById(5L);
		
		iArticleQueryApiService.selectArticleWithStatDtoAndIncreaseReadCountById(5L);
		
		iArticleQueryApiService.selectArticleWithStatDtoById(5L);
		
		ArticleDto queryVo = new ArticleDto();
		queryVo.setId(15L);
		iArticleQueryApiService.selectPageArticleFrontendDtos(queryVo, 1, 1);
		
		ArticleQueryConditionDto articleQueryConditionVo = new ArticleQueryConditionDto();
		articleQueryConditionVo.setCatalogId(15L);
		iArticleQueryApiService.selectPageArticleFrontendDtosByQueryCondition(articleQueryConditionVo, 1, 1);
		
		iArticleQueryApiService.selectPageArticles(queryVo, 1, 1);
		
		iArticleQueryApiService.selectPageArticleWithStatDtos(queryVo, 1, 1);
		
		iArticleQueryApiService.selectPageArticleWithStatDtosByQueryConditionDto(articleQueryConditionVo, 1, 1);
		
		
	}
	
	@Resource(name = "iCommentQueryApiServiceConsumer")
	private ICommentQueryApiService iCommentQueryApiService;

	/**
	 * com.dtyunxi.newretail.center.content.api.ICommentQueryApiService
	 */
	@Test
	public void iCommentQueryApiService() throws InterruptedException {
		
		iCommentQueryApiService.selectCommentById(7L);
		
		CommentDto queryVo = new CommentDto();
		queryVo.setArticleId(15L);
		iCommentQueryApiService.selectPageComments(queryVo, 1, 1);
		
		CommentQueryConditionDto commentQueryConditionVo = new CommentQueryConditionDto();
		commentQueryConditionVo.setArticleId(15L);
		iCommentQueryApiService.selectPageCommentWithStatDtos(commentQueryConditionVo, 1, 1);
		
	}
	
	@Resource(name = "iMemberActionQueryApiServiceConsumer")
	private IMemberActionQueryApiService iMemberActionQueryApiService;

	/**
	 * com.dtyunxi.newretail.center.content.api.ICommentQueryApiService
	 */
	@Test
	public void iMemberActionQueryApiService() throws InterruptedException {
		MemberActionDto queryVo = new MemberActionDto();
		queryVo.setId(5L);
		iMemberActionQueryApiService.selectPageMemberActions(queryVo, 1, 1);
		
		Long[] l = {3L,5L};
		iMemberActionQueryApiService.selectPageMemberActionsByMemberActionAndTargetIds(queryVo, l, 1, 1);
		
	}
	
	@Resource(name = "iTagQueryApiServiceConsumer")
	private ITagQueryApiService iTagQueryApiService;

	/**
	 * com.dtyunxi.newretail.center.content.api.ITagQueryApiService
	 */
	@Test
	public void iTagQueryApiService() throws InterruptedException {
		
		TagDto queryVo = new TagDto(); 
		queryVo.setChannelId(1L);
		iTagQueryApiService.selectPageTags(queryVo, 1, 1);
		
		iTagQueryApiService.selectTagById(5L);
		
	}
}

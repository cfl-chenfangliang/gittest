/**
 * @(#)MemberServiceTest.java 1.0 2017年8月26日
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
import com.dtyunxi.newretail.center.content.api.IArticleApiService;
import com.dtyunxi.newretail.center.content.api.IArticleStatApiService;
import com.dtyunxi.newretail.center.content.api.ICatalogApiService;
import com.dtyunxi.newretail.center.content.api.ICommentApiService;
import com.dtyunxi.newretail.center.content.api.IMemberActionApiService;
import com.dtyunxi.newretail.center.content.api.ITagApiService;
import com.dtyunxi.newretail.center.content.dto.ArticleDto;
import com.dtyunxi.newretail.center.content.dto.ArticleFrontendDto;
import com.dtyunxi.newretail.center.content.dto.CatalogDto;
import com.dtyunxi.newretail.center.content.dto.CommentDto;
import com.dtyunxi.newretail.center.content.dto.MemberActionDto;
import com.dtyunxi.newretail.center.content.dto.TagDto;
import com.taobao.hsf.lightapi.ServiceFactory;

/**
 * @author chen.fangliang
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartMain.class)
public class ContentServiceTest {
	static {
		ServiceFactory.getInstanceWithPath("d:/");
	}

	@Resource(name = "iCatalogApiServiceConsumer")
	private ICatalogApiService iCatalogApiService;

	/**
	 * com.dtyunxi.newretail.center.content.api.ICatalogApiService
	 */
	@Test
	public void iCatalogApiService() throws InterruptedException {

		CatalogDto catalogVo = new CatalogDto();
		// catalogVo.setId(15L);
		// iCatalogApiService.deleteCatalog(catalogVo);

		catalogVo.setName("ttttttaaaaa");
		catalogVo.setParentId(3L);
		catalogVo.setCatalogType(3);
		// iCatalogApiService.saveCatalog(catalogVo);

		catalogVo.setId(15L);
		iCatalogApiService.updateCatalog(catalogVo);
	}

	@Resource(name = "iArticleApiServiceConsumer")
	private IArticleApiService iArticleApiService;

	/**
	 * com.dtyunxi.newretail.center.content.api.ICatalogApiService
	 */
	@Test
	public void iArticleApiService() throws InterruptedException {

		// ArticleVo articleVo = new ArticleVo();
		// articleVo.setId(1L);
		// iArticleApiService.auditArticle(articleVo,ArticleStatus.FINAL_AUDITING);

		// ArticleFrontendVo articleFrontendVo = new ArticleFrontendVo();
		// articleFrontendVo.setId(13L);
		// iArticleApiService.deleteArticle(articleFrontendVo);
		
		// ArticleVo articleVo = new ArticleVo();
		// articleVo.setAuthorName("haha");
		// articleVo.setId(15L);
		// iArticleApiService.publishArticle(articleVo);
		
		ArticleFrontendDto articleFrontendVo = new ArticleFrontendDto();
		articleFrontendVo.setAuthorName("sss");
		articleFrontendVo.setTitle("ssszzz");
		articleFrontendVo.setContent("xxxx");
		// iArticleApiService.saveAndPublishArticle(articleFrontendVo);
		
		// iArticleApiService.saveArticle(articleFrontendVo);
		
		articleFrontendVo.setId(15L);
		iArticleApiService.updateArticle(articleFrontendVo);
	}
	
	@Resource(name = "iArticleStatApiServiceConsumer")
	private IArticleStatApiService iArticleStatApiService;

	/**
	 * com.dtyunxi.newretail.center.content.api.IArticleStatApiService
	 */
	@Test
	public void iArticleStatApiService() throws InterruptedException {
		
		ArticleDto articleVo = new ArticleDto();
		articleVo.setId(5L);
		iArticleStatApiService.increaseReadCount(articleVo);
		
		iArticleStatApiService.increaseReadCount(articleVo, 2L);
		
	}
	
	@Resource(name = "iCommentApiServiceConsumer")
	private ICommentApiService iCommentApiService;

	/**
	 * com.dtyunxi.newretail.center.content.api.ICommentApiService
	 */
	@Test
	public void iCommentApiService() throws InterruptedException {
		
		CommentDto commentVo = new CommentDto();
		// commentVo.setId(15L);
		// iCommentApiService.deleteComment(commentVo);
		
		commentVo.setContent("hehe");
		commentVo.setArticleId(15L);
		commentVo.setMemberId(15L);
		// iCommentApiService.saveComment(commentVo);
		
		commentVo.setId(15L);
		iCommentApiService.updateComment(commentVo);
		
	}
	
	@Resource(name = "iMemberActionApiServiceConsumer")
	private IMemberActionApiService iMemberActionApiService;

	/**
	 * com.dtyunxi.newretail.center.content.api.IMemberActionApiService
	 */
	@Test
	public void iMemberActionApiService() throws InterruptedException {
		
		MemberActionDto memberActionVo = new MemberActionDto();
		iMemberActionApiService.saveMemberAction(memberActionVo);
	}
	
	@Resource(name = "iTagApiServiceConsumer")
	private ITagApiService iTagApiService;

	/**
	 * com.dtyunxi.newretail.center.content.api.ITagApiService
	 */
	@Test
	public void iTagApiService() throws InterruptedException {
		
		TagDto tagVo1 = new TagDto();
		tagVo1.setId(6L);
		iTagApiService.deleteTag(tagVo1);
		
		TagDto tagVo2 = new TagDto();
		tagVo2.setTagName("hehe");
		tagVo2.setChannelId(1L);
		iTagApiService.saveTag(tagVo2);
		
		TagDto tagVo3 = new TagDto();
		tagVo3.setId(5L);
		tagVo3.setTagName("sss");
		iTagApiService.updateTag(tagVo3);
		
	}
	

}

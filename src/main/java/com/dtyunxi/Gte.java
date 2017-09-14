/**
 * @(#)StartMain.java 1.0 2017年8月31日
 *
 * Copyright (c) 2016, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dtyunxi;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.dtyunxi.newretail.center.content.api.query.IArticleQueryApiService;
import com.dtyunxi.newretail.center.content.api.query.ICatalogQueryApiService;
import com.dtyunxi.newretail.center.content.api.query.ICommentQueryApiService;
import com.dtyunxi.newretail.center.content.api.query.IMemberActionQueryApiService;
import com.dtyunxi.newretail.center.content.api.query.ITagQueryApiService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 根据接口生成rest控制器，接口上有@ApiImplicitParam注解的才能获取，不然需要手动添加
 * 
 * 运行后复制控制台数据到控制器中，ctrl+shift+o导包
 * 
 * @author cfl
 * 
 */
public class Gte {
	public static void main(String[] args) {
		List list = new ArrayList<>();
		list.add(IArticleQueryApiService.class);
		list.add(ICatalogQueryApiService.class);
		list.add(ICommentQueryApiService.class);
		list.add(IMemberActionQueryApiService.class);
		list.add(ITagQueryApiService.class);
		for (Object object : list) {
			main2((Class)object);
		}
	}

	public static void main2(Class c) {
		System.out.println();
		System.out.println();
		// 接口
		// Class c = IArticleStatApiService.class;
		String string = c.getName();
		String[] ss = string.split("\\.");
		String autoWard = ss[ss.length - 1];
		System.out.println(autoWard);
		System.out.println();
		System.out.println();
		System.out.println();
		Method[] methods = c.getMethods();
		for (Method method : methods) {
			ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
			if (apiOperation != null) {
				System.out.println(method.getName()+","+getApiOperation(apiOperation)+","+"成功");
			} else {
				System.out.println("//*************该方法没有注解！请手动添加*************");
				System.out.println("//*************" + method.getName() + "*************");
				System.out.println();
				continue;
			}
			String an = getApiImplicitParam(method);
			if (an == null || "".equals(an)) {
				System.out.println("//*************该方法没有注解！请手动添加*************");
				System.out.println("//*************" + method.getName() + "*************");
				System.out.println();
				continue;
			}
		}
	}

	/**
	 * 得到接口方法异常
	 * 
	 * @param method
	 * @return 接口方法返回值
	 */
	public static String getException(Method method) {
		Class[] cc = method.getExceptionTypes();
		return getLastStr(cc[0].getName());
	}

	/**
	 * 参数是不是常用类型 用于get方法还是post方法
	 * 
	 * @param method
	 * @return 接口方法返回值
	 */
	public static boolean isUsuallParamType(Method method) {
		Class[] cls = method.getParameterTypes();
		for (Class class1 : cls) {
			if (!"Long".equals(getLastStr(class1.getName()))) {
				if (!"Integer".equals(getLastStr(class1.getName()))) {
					if (!"String".equals(getLastStr(class1.getName()))) {
						if (!"int".equals(getLastStr(class1.getName()))) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * 得到接口方法返回值
	 * 
	 * @param method
	 * @return 接口方法返回值
	 */
	public static String getReturnName(Method method) {
		String returnType = method.getReturnType().getName();
		String returnName = getLastStr(returnType);
		return returnName;
	}

	/**
	 * 得到接口中方法参数的类型和名称
	 * 
	 * @param method
	 * @return
	 */
	public static String getParamAndType(Method method) {
		ApiImplicitParam apm = method.getAnnotation(ApiImplicitParam.class);
		ApiImplicitParams apms = method.getAnnotation(ApiImplicitParams.class);
		StringBuilder finalStr = new StringBuilder("");
		// 单个参数
		if (apm != null) {
			String dataType = apm.dataType();
			String name = apm.name();
			// 如果注解没有声明参数类型，那就通过反射区找
			if(dataType == null || "".equals(dataType)){
				dataType = getParamType(method)[0];
			}
			finalStr.append(dataType + " " + name);
		}
		// 多个参数
		if (apms != null) {
			ApiImplicitParam[] ApiImplicitParams = apms.value();
			for (int i = 0; i < ApiImplicitParams.length; i++) {
				ApiImplicitParam apiImplicitParam = ApiImplicitParams[i];
				String dataType = apiImplicitParam.dataType();
				String name = apiImplicitParam.name();
				if(dataType == null || "".equals(dataType)){
					dataType = getParamType(method)[i];
				}
				finalStr.append(dataType + " " + name);
				if (i < ApiImplicitParams.length - 1) {
					finalStr.append(" , ");
				}
			}
		}
		return finalStr.toString();
	}
	
	/**
	 * 得到接口中方法参数的类型   数组形式
	 * 
	 * @param method
	 * @return
	 */
	public static String[] getParamType(Method method) {
		Class[] ms = method.getParameterTypes();
		if(ms.length != 0){
			String[] strs = new String[10];
			for(int i=0;i<ms.length;i++){
				strs[i] = getLastStr(ms[i].getName());
			}
			return strs;
		}
		return null;
		
	}
	

	/**
	 * 得到接口中方法参数的名称
	 * 
	 * @param method
	 * @return
	 */
	public static String getParamName(Method method) {
		ApiImplicitParam apm = method.getAnnotation(ApiImplicitParam.class);
		ApiImplicitParams apms = method.getAnnotation(ApiImplicitParams.class);
		StringBuilder finalStr = new StringBuilder("");
		if (apm != null) {
			String name = apm.name();
			finalStr.append(name);
		}
		if (apms != null) {
			ApiImplicitParam[] ApiImplicitParams = apms.value();
			for (int i = 0; i < ApiImplicitParams.length; i++) {
				ApiImplicitParam apiImplicitParam = ApiImplicitParams[i];
				String name = apiImplicitParam.name();
				finalStr.append(name);
				if (i < ApiImplicitParams.length - 1) {
					finalStr.append(" , ");
				}
			}
		}
		return finalStr.toString();
	}

	/**
	 * 得到用符号隔开的字符串最后一组字符串 如：.隔开 a.b.c 返回c
	 * 
	 * @return
	 */
	public static String getLastStr(String str) {
		String retStr = str;
		if (str.split("\\.").length > 1) {
			retStr = str.split("\\.")[str.split("\\.").length - 1];
		}
		return retStr;
	}

	/**
	 * 得到接口方法中字符串形式的@ApiOperation
	 * 
	 * @param apiOperation
	 * @return
	 */
	public static String getApiOperation(ApiOperation apiOperation) {
		String value = apiOperation.value();
		return value;
	}

	/**
	 * 得到接口方法中字符串形式的@ApiImplicitParam或者@ApiImplicitParams
	 * 
	 * @param method
	 * @return
	 */
	public static String getApiImplicitParam(Method method) {

		ApiImplicitParam apm = method.getAnnotation(ApiImplicitParam.class);
		ApiImplicitParams apms = method.getAnnotation(ApiImplicitParams.class);
		if (apm != null) {
			String name = apm.name();
			String value = apm.value();
			String dataType = apm.dataType();
			String str = "@ApiImplicitParam(name = \"" + name + "\", value = \"" + value + "\", dataType = \""
					+ dataType + "\")";
			return str;
		}
		if (apms != null) {
			ApiImplicitParam[] ApiImplicitParams = apms.value();
			StringBuilder finalStr = new StringBuilder("@ApiImplicitParams({ ");
			for (int i = 0; i < ApiImplicitParams.length; i++) {
				ApiImplicitParam apiImplicitParam = ApiImplicitParams[i];
				String name = apiImplicitParam.name();
				String value = apiImplicitParam.value();
				String dataType = apiImplicitParam.dataType();
				String str = "@ApiImplicitParam(name = \"" + name + "\", value = \"" + value + "\", dataType = \""
						+ dataType + "\")";
				finalStr.append(str);
				if (i < ApiImplicitParams.length - 1) {
					finalStr.append(" , ");
				}
			}
			finalStr.append(" })");
			return finalStr.toString();
		}
		return null;
	}

}
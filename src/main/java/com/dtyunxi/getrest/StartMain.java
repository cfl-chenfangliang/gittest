/**
 * @(#)StartMain.java 1.0 2017年8月31日
 *
 * Copyright (c) 2016, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dtyunxi.getrest;

import java.lang.reflect.Method;

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
public class StartMain {

	public static void main(String[] args) {
		// 接口
		Class c = ITagQueryApiService.class;
		String string = c.getName();
		String[] ss = string.split("\\.");
		String autoWard = ss[ss.length - 1];
		String autoWardName = ss[ss.length - 1].substring(1, 2).toLowerCase() + ss[ss.length - 1].substring(2);
		System.out.println("@Resource(name = \"\")");// Resource的名字规则
		System.out.println("private " + autoWard + " " + autoWardName + ";");
		System.out.println();
		Method[] methods = c.getMethods();
		for (Method method : methods) {
			ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
			if (apiOperation != null) {
				System.out.println(getApiOperation(apiOperation));
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
			System.out.println(an);
			String reqMethod = "GET";
			if (!isUsuallParamType(method)) {
				reqMethod = "POST";
			}
			System.out.println(
					"@RequestMapping(value = \"" + method.getName() + "\", method = RequestMethod." + reqMethod + ")");
			String params = getParamAndType(method);
			StringBuilder all = new StringBuilder("public RestResponse " + method.getName() + "(" + params + ")");
			all.append(" {\n");
			if ("void".equals(getReturnName(method))) {
				all.append("\t" + autoWardName + "." + method.getName() + "(" + getParamName(method) + ");");
				all.append("\n\treturn new RestResponse();");
			} else {
				all.append("\treturn new RestResponse(" + autoWardName + "." + method.getName() + "("
						+ getParamName(method) + "));");
			}
			all.append("\n}");
			System.out.println(all.toString());
			System.out.println();
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
		String notes = apiOperation.notes();
		String response = getLastStr(apiOperation.response().getName()) + ".class";
		String responseContainer = apiOperation.responseContainer();
		StringBuilder str = new StringBuilder("");
		if ("void.class".equals(response.toLowerCase())) {
			str.append("@ApiOperation(value = \"" + value + "\", notes = \"" + notes + "\"");
		} else {
			str.append("@ApiOperation(value = \"" + value + "\", notes = \"" + notes + "\",response = " + response);
		}
		if (!"".equals(responseContainer) && responseContainer != null) {
			str.append(",responseContainer = \"" + responseContainer + "\")");
		} else {
			str.append(")");
		}
		return str.toString();
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
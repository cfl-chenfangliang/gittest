/**
 * @(#)CopyInterfaces.java 1.0 2017年9月1日
 *
 * Copyright (c) 2016, YUNXI. All rights reserved.
 * YUNXI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dtyunxi.getrest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiOperation;

/**
 * 分组
 * 
 * 多个接口 copy到一个接口
 * 
 * @author chen.fangliang
 *
 */
public class CopyInterfaces {

	public static void main(String[] args) throws ClassNotFoundException {
		// 接口
		List<Class<?>> list = new ArrayList<>();

		// 添加接口到list中
		// list.add(IMemberQueryService.class);

		StringBuilder finalStr = new StringBuilder("");
		for (Class<?> c : list) {
			finalStr.append(startCopy(c));
		}
		// 接口文件
		createIFile("D:/interface.java",finalStr.toString());
		System.out.println(finalStr.toString());
	}

	public static void createIFile(String path,String context) {
		File file = new File(path);
		OutputStreamWriter osw = null;
		if (file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			osw = new OutputStreamWriter(new FileOutputStream(file));
			osw.write(context);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				osw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String startCopy(Class c) {

		StringBuilder finalStr = new StringBuilder("");

		String string = c.getName();
		String[] ss = string.split("\\.");
		String autoWard = ss[ss.length - 1];
		String autoWardName = ss[ss.length - 1].substring(1, 2).toLowerCase() + ss[ss.length - 1].substring(2);
		Method[] methods = c.getMethods();
		for (Method method : methods) {
			ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
			if (apiOperation != null) {
				finalStr.append(StartMain.getApiOperation(apiOperation) + "\n");
			} else {
				finalStr.append("//*************该方法没有注解！请手动添加*************" + "\n");
				finalStr.append("//*************" + method.getName() + "*************" + "\n");
				finalStr.append("\n");
				continue;
			}
			String an = StartMain.getApiImplicitParam(method);
			if (an == null || "".equals(an)) {
				finalStr.append("//*************该方法没有注解！请手动添加*************" + "\n");
				finalStr.append("//*************" + method.getName() + "*************" + "\n");
				finalStr.append("\n");
				continue;
			}
			finalStr.append(an + "\n");
			// public
			String returnName = StartMain.getReturnName(method);
			String methodName = method.getName();
			String paramNames = StartMain.getParamAndType(method);
			String MethodException = StartMain.getException(method);
			StringBuilder methodStr = new StringBuilder("public ");
			methodStr.append(returnName).append(" ").append(methodName).append(" (").append(paramNames)
					.append(") throws ").append(MethodException).append(";");
			finalStr.append(methodStr.toString() + "\n");
			finalStr.append("\n");
		}
		return finalStr.toString();
	}
}

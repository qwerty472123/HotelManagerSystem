package com.seucourse.hotelmanage.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码的加密类：使用MD5算法进行加密
 *
 * MD5 ： 不可逆的-单向 。只能由源字符串生成加密后的字符串
 * 		  基于哈希算法
 * 		  定长 - 32位 ，无论原字符串有多长，最后生成的都是32位的字符
 * 		  由十六进制数表示的字符串 0123456789abcdef
 * @author Administrator
 *
 */
public class MD5Util {

	/**
	 * 生成MD5加密后的字符串的静态方法
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getMD5String(String password) throws NoSuchAlgorithmException {
//		定义十六进制数的字符数组
		char[] hexcharacters = "0123456789abcdef".toCharArray();
//		生成源密码字符串的字节数组
		byte[] passBytes = password.getBytes();

//		借助一个工具 ： 创建出MD5算法对应的消息摘要对象
		MessageDigest mdInstance = MessageDigest.getInstance("MD5");

//		首先需要将原密码的字节数组更新到消息摘要中
		mdInstance.update(passBytes);
//		计算哈希值/生成哈希值
		byte[] afterBytes = mdInstance.digest();

//		通过计算出来的哈希值完成加密后字符串的拼接
//		创建返回值的字符数组
		char[] resultChars = new char[afterBytes.length * 2];
//		定义一个resultChars的下标值
		int k = 0;
//		通过遍历方式 - afterBytes，计算对应的下标数字（十六进制数的字节数组中获取指定的字符）
		for (int i = 0; i < afterBytes.length; i++) {
//			获取当前的字节
			byte temp = afterBytes[i];
//			获取当前字节的高位（4） 0xf - 十六进制表示的数字15
			resultChars[k++] = hexcharacters[temp >>> 4 & 0xf];
//			获取当前字节的低位（4）
			resultChars[k++] = hexcharacters[temp & 0xf];
		}
		return new String(resultChars).toUpperCase();
	}

	public static void main(String[] args) {
		String pass = "123456";
		String pass1 = "123457";

		try {
			String passAfter = MD5Util.getMD5String(pass);
			String pass1After = MD5Util.getMD5String(pass1);
			System.out.println("123456加密后的字符串是：" + passAfter);
			System.out.println("123457加密后的字符串是：" + pass1After);

			System.out.println("123456两次加密后的字符串是：" + MD5Util.getMD5String(passAfter));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}

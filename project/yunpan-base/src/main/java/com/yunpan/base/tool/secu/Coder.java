package com.yunpan.base.tool.secu;

import java.security.MessageDigest;

public class Coder {
	public static final String KEY_SHA1 = "SHA-1";
	public static final String KEY_MD5 = "MD5";

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	/*
	 * public static byte[] decryptBASE64(String key) throws Exception { return
	 * (new BASE64Decoder()).decodeBuffer(key); }
	 *//**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	/*
	 * public static String encryptBASE64(byte[] key) throws Exception { return
	 * (new BASE64Encoder()).encodeBuffer(key); }
	 */

	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws Exception {

		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);

		return md5.digest();

	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data) throws Exception {

		MessageDigest sha = MessageDigest.getInstance(KEY_SHA1);
		sha.update(data);

		return sha.digest();

	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(String entryContent) throws Exception {
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA1);
		sha.update(entryContent.getBytes("utf-8"));
		return sha.digest();
	}
}

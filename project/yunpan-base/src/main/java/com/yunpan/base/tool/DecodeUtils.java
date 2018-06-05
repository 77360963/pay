package com.yunpan.base.tool;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class DecodeUtils {

	private KeyGenerator keygen; // 提供密钥生成器的功能

	private SecretKey deskey; // 保存密钥

	private Cipher cipher; // 完成加密或解密工作

	private byte[] cipherByte;
	
	/**
	 * 注：须要加解密字符串不能包含空格符
	 */
	public DecodeUtils() {
		//Security.addProvider(new com.sun.crypto.provider.SunJCE());
		// 实例化支持DES算法的密钥生成器
		try {
			keygen = KeyGenerator.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 生成密钥
		deskey = keygen.generateKey();
		// 生成Cipher对象并指定其支持的DES算法
		try {
			try {
				cipher = Cipher.getInstance("DES/ECB/NoPadding");
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 加密
	 */
	public String Encrypt(String str) {
		StringBuffer sb = new StringBuffer(str);
		byte [] byteStr = str.getBytes();
		String string = "";
		if(byteStr.length%8 != 0){
			for(int i = 0 ; i < 8-byteStr.length%8; i++){
				sb.append(" ");
			}
			string = new String (sb);
		}else {
			string = str;
		}
		// 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
		try {
			cipher.init(Cipher.ENCRYPT_MODE, deskey);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] src = string.getBytes();
		// 加密后将结果保存到cipherByte
		try {
			try {
				cipherByte = cipher.doFinal(src);
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Base64Util.encode(cipherByte);
	}

	/**
	 * 解密
	 */
	public String Descrypt(String str) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, deskey);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			try {
				cipherByte = cipher.doFinal(Base64Util.decode(str.toCharArray()));
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		char[] cipherChar = new String(cipherByte).toCharArray();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i< cipherChar.length; i++){
			if(' ' != cipherChar[i]){
				sb.append(cipherChar[i]);
			}
		}
		
		return sb.toString();
	}

	public static class Base64Util {
		static private char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
		static private byte[] codes = new byte[256];
		static {
			for (int i = 0; i < 256; i++)
				codes[i] = -1;
			for (int i = 'A'; i <= 'Z'; i++)
				codes[i] = (byte) (i - 'A');
			for (int i = 'a'; i <= 'z'; i++)
				codes[i] = (byte) (26 + i - 'a');
			for (int i = '0'; i <= '9'; i++)
				codes[i] = (byte) (52 + i - '0');
			codes['+'] = 62;
			codes['/'] = 63;
		}

		/**
		 * 将原始数据编码为base64编码
		 */
		static public String encode(byte[] data) {
			char[] out = new char[((data.length + 2) / 3) * 4];
			for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
				boolean quad = false;
				boolean trip = false;
				int val = (0xFF & (int) data[i]);
				val <<= 8;
				if ((i + 1) < data.length) {
					val |= (0xFF & (int) data[i + 1]);
					trip = true;
				}
				val <<= 8;
				if ((i + 2) < data.length) {
					val |= (0xFF & (int) data[i + 2]);
					quad = true;
				}
				out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
				val >>= 6;
				out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
				val >>= 6;
				out[index + 1] = alphabet[val & 0x3F];
				val >>= 6;
				out[index + 0] = alphabet[val & 0x3F];
			}
			return new String(out);
		}

		/**
		 * 将base64编码的数据解码成原始数据
		 */
		static public byte[] decode(char[] data) {
			int len = ((data.length + 3) / 4) * 3;
			if (data.length > 0 && data[data.length - 1] == '=')
				--len;
			if (data.length > 1 && data[data.length - 2] == '=')
				--len;
			byte[] out = new byte[len];
			int shift = 0;
			int accum = 0;
			int index = 0;
			for (int ix = 0; ix < data.length; ix++) {
				int value = codes[data[ix] & 0xFF];
				if (value >= 0) {
					accum <<= 6;
					shift += 6;
					accum |= value;
					if (shift >= 8) {
						shift -= 8;
						out[index++] = (byte) ((accum >> shift) & 0xff);
					}
				}
			}
			if (index != out.length)
				throw new Error("数据长度错误！");
			return out;
		}
	}

	public static void main(String[] args) throws Exception {
		DecodeUtils des = new DecodeUtils();
		String msg = "ads国32a.aa/oi4q别想";
		String encontent = des.Encrypt(msg);
		String decontent = des.Descrypt(encontent);
		System.out.println("原文是:" + msg);
		System.out.println("加密后:" + encontent);
		System.out.println("解密后:" + decontent);
	}

}

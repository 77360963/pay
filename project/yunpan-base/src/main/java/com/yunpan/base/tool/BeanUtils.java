package com.yunpan.base.tool;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtils {
	
	/**
	 * list转换成Map,参数kName必须为唯一
	 * @param vList
	 * @param kName
	 * @return
	 */
	public static <K, V> Map<K, V> listToMap(List<V> vList, String kName) {
		Map<K, V> map = new HashMap<K, V>();
		if (vList == null || kName == null || vList.size() == 0) {
			return map;
		}
		Field kField = getField(vList.get(0).getClass(), kName);
		for (V v : vList) {
			try {
				map.put((K) kField.get(v), v);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	public static <V> Field getField(Class<V> clazz, String kName) {
		if (clazz == null || kName == null) {
			return null;
		}
		for (Field fieldElem : clazz.getDeclaredFields()) {
			fieldElem.setAccessible(true);
			if (fieldElem.getName().equals(kName)) {
				return fieldElem;
				}
		}
		return null;
	}

}

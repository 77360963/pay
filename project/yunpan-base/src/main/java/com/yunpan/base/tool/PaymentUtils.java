package com.yunpan.base.tool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

public class PaymentUtils {
	

    
    /**
     * 过滤请求报文中的空字符串或者空字符串
     * @param contentData
     * @return
     */
    public static Map<String, String> filterBlank(Map<String, String> contentData){   
        Map<String, String> submitFromData = new HashMap<String, String>();
        Set<String> keyset = contentData.keySet();
        for(String key:keyset){
            String value = contentData.get(key);
            if (!StringUtils.isEmpty(value)) {
                // 对value值进行去除前后空处理
                submitFromData.put(key, value.trim());             
            }
        }
        return submitFromData;
    }
    
    /**
     * flag=false时，signature不参于运算
     * 将Map中的数据转换成按照Key的ascii码排序后的key1=value1&key2=value2的形式 不包含签名域signature
     *
     * @param data
     *            待拼接的Map数据
     * @return 拼接好后的字符串
     */
   public static String coverMap2String(Map<String, String> data) {
        Set<String> keySet=new TreeSet<String>(data.keySet());
        StringBuffer sf = new StringBuffer();
        for(String key:keySet){        	
    		if ("sign".equals(key.trim())) {
                continue;
            }        	         
            sf.append(key + "=" + data.get(key)+"&");
        }
        sf.setLength(sf.length()-1);
        return sf.toString();   
  }
   
   /**
    * 功能：前台交易构造HTTP POST自动提交表单<br>
    * @param action 表单提交地址<br>
    * @param hiddens 以MAP形式存储的表单键值<br>
    * @param encoding 上送请求报文域encoding字段的值<br>
    * @return 构造好的HTTP POST交易表单<br>
    */
   public static String createAutoFormHtml(String reqUrl, Map<String, String> hiddens,String encoding) {
       StringBuffer sf = new StringBuffer();
       sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset="+encoding+"\"/></head><body>");
       sf.append("<form id = \"pay_form\" action=\"" + reqUrl
               + "\" method=\"post\">");
       if (null != hiddens && 0 != hiddens.size()) {
           Set<Entry<String, String>> set = hiddens.entrySet();
           Iterator<Entry<String, String>> it = set.iterator();
           while (it.hasNext()) {
               Entry<String, String> ey = it.next();
               String key = ey.getKey();
               String value = ey.getValue();
               sf.append("<input type=\"hidden\" name=\"" + key + "\" id=\""
                       + key + "\" value=\"" + value + "\"/>");
           }
       }
       sf.append("</form>");
       sf.append("</body>");
       sf.append("<script type=\"text/javascript\">");
       sf.append("document.all.pay_form.submit();");
       sf.append("</script>");
       sf.append("</html>");
       return sf.toString();
   }

}

package com.dkunc.cmn.utils;
import java.io.File;
import java.util.HashMap;
import java.util.regex.Matcher;
import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils {
    public static HashMap<String, String> split(String authValue, String param1, String param2) {
        HashMap<String, String> hm = new HashMap<String, String>();
        String[] ary = authValue.split(param1);
        for (String s : ary) {
            String[] arytmp = s.split(param2);
            hm.put(arytmp[0], arytmp[1]);
        }
        return hm;
    }
    public static String nvl(String value) {
        String s = "";
        try {
            if (value != null && !"".equals(value) && value.length() > 0) {
                s = value.trim();
            }
        } catch (Exception ex) {
            // throw ex;
        }
        return s;
    }
    /**
     * 
     * @param msg
     *            : javascript alert으로 띄워줄 메시지내용
     * @return
     */
    public static String getJavascriptAlert(String msg) {
        return getJavascriptSrc("alert('" + msg + "');");
    }
    /**
     * 
     * @param javascriptSrc
     *            : javascript 소스를 넣고싶을 경우, 해당 소스
     * @return
     */
    public static String getJavascriptSrc(String javascriptSrc) {
        StringBuilder sb = new StringBuilder("<script type='text/javascript'>");
        sb.append(javascriptSrc);
        sb.append("</script>");
        return sb.toString();
    }
    public static String getString(Object obj, String defaultStr) {
        return (obj == null ? defaultStr : obj.toString());
    }
    /**
     *  OS의 경로구분자인 "/" or "\"----> URL에서 사용하는 경로구분자 "/" 으로 변환
     * 
     * @param str
     * @return
     */
    public static String transUrlSeperator(String str) {
        return str.replaceAll(Matcher.quoteReplacement(File.separator), "/");
    }
    /**
     * URL에서 사용하는 경로구분자 "/" ----> OS의 경로구분자인 "/" or "\" 으로 변환
     * 
     * @param str
     * @return
     */
    public static String transOsSeperator(String str) {
        return str.replaceAll("/", Matcher.quoteReplacement(File.separator));
    }
    /**
     * FIELD  값에 해당하는 두개의 값을 비교하여 두개의 값이 같으면  null return 아니면 field 사용
     * @param field
     * @param src
     * @param tgt
     * @return
     */
    public static String compareStr(String field, String src, String tgt){
        if(isEmpty(src) == isEmpty(tgt)) 
            return null;
        if(isNotEmpty(src) && isNotEmpty(tgt)){
            return src.equals(tgt) ? null : field;
        }else{
            return tgt;
        }
    }
}

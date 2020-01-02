package com.blog.framework.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

/** 
 * xss非法标签过滤工具类 
 * 过滤html中的xss字符
 *
 * @author liuzw
 */  
public class JsoupUtil {

    private JsoupUtil(){}
  
    /** 
     * 使用自带的basicWithImages 白名单 
     * 允许的便签有a,b,blockquote,br,cite,code,dd,dl,dt,em,i,li,ol,p,pre,q,small,span, 
     * strike,strong,sub,sup,u,ul,img 
     * 以及a标签的href,img标签的src,align,alt,height,width,title属性 
     */  
    private static final Whitelist WHITE_LIST = Whitelist.basicWithImages();
    /** 配置过滤化参数,不对代码进行格式化 */  
    private static final Document.OutputSettings OUTPUT_SETTINGS = new Document.OutputSettings().prettyPrint(false);
    static {  
        WHITE_LIST.addAttributes(":all", "style");
    }  
  
    public static String clean(String content) {  
        if(StringUtils.isNotBlank(content)){  
            content = content.trim();  
        }  
        return Jsoup.clean(content, "", WHITE_LIST, OUTPUT_SETTINGS);
    }  
}
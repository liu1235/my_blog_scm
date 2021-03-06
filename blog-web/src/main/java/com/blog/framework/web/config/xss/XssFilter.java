package com.blog.framework.web.config.xss;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**  
 * 拦截防止xss注入 
 * 通过Jsoup过滤请求参数内的特定字符
 *
 * @author liuzw
 */
@Slf4j
public class XssFilter implements Filter {    
    private static final Logger logger = LogManager.getLogger();  
  
    /** 
     * 是否过滤富文本内容 
     */  
    private boolean isIncludeRichText = false;
      
    private List<String> excludes = new ArrayList<>();
    
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,ServletException {  
        if(logger.isDebugEnabled()){  
            logger.debug("xss filter is open");  
        }  
          
        HttpServletRequest req = (HttpServletRequest) request;  
        if(handleExcludeURL(req)){
            filterChain.doFilter(request, response);  
            return;  
        }  
          
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request,isIncludeRichText);
        filterChain.doFilter(xssRequest, response);  
    }  
      
    private boolean handleExcludeURL(HttpServletRequest request) {
  
        if (excludes == null || excludes.isEmpty()) {  
            return false;  
        }  
  
        String url = request.getServletPath();  
        for (String pattern : excludes) {  
            Pattern p = Pattern.compile("^" + pattern);  
            Matcher m = p.matcher(url);  
            if (m.find()) {  
                return true;  
            }  
        }  
  
        return false;  
    }  
  
    @Override  
    public void init(FilterConfig filterConfig) throws ServletException {
        if(logger.isDebugEnabled()){  
            logger.debug("xss filter init ====================");  
        }  
        String val = filterConfig.getInitParameter("isIncludeRichText");
        if(StringUtils.isNotBlank(val)){
            isIncludeRichText = BooleanUtils.toBoolean(val);
        }  
          
        String temp = filterConfig.getInitParameter("excludes");  
        if (temp != null) {  
            String[] url = temp.split(",");
            Collections.addAll(excludes, url);
        }  
    }  
  
    @Override  
    public void destroy() {
        log.info("destroy...");
    }
    
}    

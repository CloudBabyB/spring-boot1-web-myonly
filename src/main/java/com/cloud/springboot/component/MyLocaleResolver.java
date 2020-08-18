package com.cloud.springboot.component;

import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author myBin
 * @Title:
 * @Package
 * @Description:
 * 设置国际语言，
 * @date 2020/8/1121:00
 */
@Configuration
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String l = httpServletRequest.getParameter("l");
        // 如果没有指定语言，默认使用系统语言
        Locale locale = Locale.getDefault();
        Local local= null;
        if (!StringUtils.isEmpty(l)){
            String[] s = l.split("_");
             locale = new Locale(s[0], s[1]);

        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}

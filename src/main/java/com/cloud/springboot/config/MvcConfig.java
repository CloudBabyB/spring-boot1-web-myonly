package com.cloud.springboot.config;

import com.cloud.springboot.component.LoginHandlerInterceptor;
import com.cloud.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author myBin
 * @Title:
 * @Package
 * @Description: 配置扩展SpringMvc中相关功能，映射
 * @date 2020/8/1118:17
 */
// WebMvcConfigurerAdapter此类已经过时，使用实现WebMvcConfigurer接口代替
//@EnableWebMvc 全面接管springMvc,所有springMvc自动配置都失效，开发不推荐使用
//@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器发送cloud请求，跳转到success页面

        //registry.addViewController("/index").setViewName("index");
    }

    // 所有的WebViewConfigurer组件一起起作用
    @Bean //将组建注册到容器
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer wmc = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                // 设置访问路径
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                // 添加试图映射
                registry.addViewController("/dashboard.html").setViewName("dashboard");
            }

            // 注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/login.html", "/", "/admin/login","/asserts/**","/webjars/**");
            }


        };

        return wmc;
    }

    // 配置此类。如果在自动配置中
    // 如果容器中没有LocaleResolver则默认webMvcAutoConfiguration
    // 如果有则使用自定义LocaleResolver类则创建LocaleResolver，
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}

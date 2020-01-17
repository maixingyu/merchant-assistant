package com.team.merchantassistant.config;

import com.team.merchantassistant.interceptors.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringMVCConfig extends WebMvcConfigurationSupport {
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;
    @Value("${file.imgPath}")
    private String imgPath;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
        configurer.setUseSuffixPatternMatch(false);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:" + imgPath);
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        List<String> url = new ArrayList<>();
        url.add("/client/user/login");
        url.add("/img/**");
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**").excludePathPatterns(url);
        super.addInterceptors(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600);
        super.addCorsMappings(registry);
    }
}

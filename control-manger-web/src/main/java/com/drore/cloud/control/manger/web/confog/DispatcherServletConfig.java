package com.drore.cloud.control.manger.web.confog;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/3/2.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
@Configuration
@ComponentScan(basePackages = {"com.drore.cloud.control.manger.web"}, includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = RestController.class)})
public class DispatcherServletConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("html/img/");
        registry.addResourceHandler("/css/**").addResourceLocations("html/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("html/js/");
        registry.addResourceHandler("/less/**").addResourceLocations("html/less/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(getSwaggerFastJsonHttpMessageConverter());
        super.configureMessageConverters(converters);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                .maxAge(3600);
    }


    /**
     * 请求处理程序映射适配器
     *
     * @return swagger fast json http message converter
     */
    @Bean
    public HttpMessageConverter getSwaggerFastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter httpMessageConverter = new FastJsonHttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        mediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        mediaTypes.add(MediaType.TEXT_HTML);
        httpMessageConverter.setSupportedMediaTypes(mediaTypes);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(httpMessageConverter);
        return httpMessageConverter;
    }


    /**
     * Character encoding filter character encoding filter.
     *
     * @return the character encoding filter
     */
    @Bean
    @ConditionalOnMissingBean(CharacterEncodingFilter.class)
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceRequestEncoding(true);
        filter.setForceResponseEncoding(true);
        return filter;
    }


}

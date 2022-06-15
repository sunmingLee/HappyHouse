package com.happyhouse.config;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.happyhouse.interceptor.ConfirmInterceptor;



@Configuration
@EnableAspectJAutoProxy
@MapperScan(basePackages = {"com.happyhouse.**.mapper"})
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Autowired
	private ConfirmInterceptor confirm;

//	private final List<String> patterns = Arrays.asList("/guestbook/*","/admin/*","/user/list");
	private final List<String> patterns = Arrays.asList("/user/info");

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(confirm).addPathPatterns(patterns);

	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		default 설정.
//		Allow all origins.
//		Allow "simple" methods GET, HEAD and POST.
//		Allow all headers.
//		Set max age to 1800 seconds (30 minutes).
		registry.addMapping("/**")
			.allowedOrigins("*")
//			.allowedOrigins("http://localhost:8080", "http://localhost:8081")
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//			.allowedHeaders("*")
			.maxAge(6000);
	}
	
//	Swagger UI 실행시 404처리
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/index.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui/index.html");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
	
	

//	@Bean
//  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
//      SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//      sqlSessionFactoryBean.setDataSource(dataSource);
//      Resource[] arrResource = new PathMatchingResourcePatternResolver()
//              .getResources("classpath:mapper/**/*Mapper.xml");
//      sqlSessionFactoryBean.setMapperLocations(arrResource);
//      sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
//      return sqlSessionFactoryBean.getObject();
//  }

//	@Bean
//	public ViewResolver internalResourceViewResolver() {
//	    InternalResourceViewResolver bean = new InternalResourceViewResolver();
//	    bean.setPrefix("/WEB-INF/views/");
//	    bean.setSuffix(".jsp");
//	    return bean;
//	}
//	
//	@Bean
//	public BeanNameViewResolver beanNameViewResolver() {
//		BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
//		beanNameViewResolver.setOrder(0);
//		return beanNameViewResolver;
//	}

}
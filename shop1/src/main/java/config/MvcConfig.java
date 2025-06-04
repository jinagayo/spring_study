package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration 
@ComponentScan(basePackages= {"controller","logic","service","dao"})
@EnableWebMvc   //기본 웹처리를 위한 설정.
public class MvcConfig implements WebMvcConfigurer{
	// http://localhost:8080/shop1/item/list
	@Bean //객체화 
	public HandlerMapping handlerMapping() { //url 처리.
		RequestMappingHandlerMapping hm = new RequestMappingHandlerMapping();
		hm.setOrder(0);
		return hm;
	}
	@Bean
	public ViewResolver viewResolver() { //뷰 결정자. 
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/view/");  //  /WEB-INF/view/item/list.jsp
		vr.setSuffix(".jsp");
		return vr;
	}
	//파일 업로드를 위한 설정. enctype="multipart/form-data" 요청 처리함.
	@Bean
	public MultipartResolver multipartResolver()
	{
		CommonsMultipartResolver mr = new CommonsMultipartResolver();
		mr.setMaxInMemorySize(1024 * 1024); // 업로드시 메모리에서 처리가능 크기지정
		mr.setMaxUploadSize(1024 * 10240);	// 업로드 가능한 파일의 크기 지정
		return mr;
	}
	//기본 웹파일 처리를 위한 설정
	// 해당 코드는 Spring MVC 기반의 웹 애플리케이션에서 기본 정적 자원(이미지, CSS, JS 등)을 처리하기 위한 설정
	@Override
	public void configureDefaultServletHandling
	(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();   // 기본 서블릿(DefaultServlet) 을 활성화
		                       //  Spring MVC가 처리하지 않는 요청(CSS, JS, 이미지 등 정적 리소스)은 서블릿 컨테이너(Tomcat 등)의 기본 서블릿이 처리하도록 허용
	}
}
package kr.ac.kopo.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInit implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		initListener(servletContext);
		
		initServlet(servletContext);
		
		initFilter(servletContext);

	}

	private void initFilter(ServletContext servletContext) {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("utf-8", true, true);
		
	 	FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", encodingFilter);
	 	
	 	filter.addMappingForUrlPatterns(null, false, "/*");
	}

	private void initServlet(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext wac = new AnnotationConfigWebApplicationContext();
		wac.register(WebConfig.class);
		wac.refresh();
		
		DispatcherServlet dispatcherServlet = new DispatcherServlet(wac);
		
		ServletRegistration.Dynamic servlet = servletContext.addServlet("action", dispatcherServlet);
		
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
		
	}

	private void initListener(ServletContext servletContext) {
		//XmlWebApplicationContext
		AnnotationConfigWebApplicationContext wac = new AnnotationConfigWebApplicationContext();
		wac.register(RootContext.class);
		
		ContextLoaderListener listener = new ContextLoaderListener();
		
		servletContext.addListener(listener);
	}

}

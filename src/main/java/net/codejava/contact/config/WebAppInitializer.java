package net.codejava.contact.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer{
    public void onStartup(ServletContext servletContext) throws ServletException {
        /*
        * Регистрация класса конфигурации с помощью AnnotationConfigApplicationContext
        * Класс AnnotationConfigApplicationContext является реализацией интерфейса ApplicationContext,
        * которая позволяет регистрировать аннотированные классы конфигурации.
        * */

        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(SpringMvcConfig.class); // установили конфигурационный класс

    /*
    * ServletRegistration.Dynamic dispatcher =  //Интерфейс, через который
    * может быть настроен Servlet зарегистрированный через
    * один из методов addServletServletContext .
     */
        ServletRegistration.Dynamic dispatcher
                = servletContext.addServlet("SpringDispatcher", new DispatcherServlet(appContext));

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");




    }
}

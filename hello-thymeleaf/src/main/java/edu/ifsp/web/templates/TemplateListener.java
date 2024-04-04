package edu.ifsp.web.templates;

import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.JavaxServletWebApplication;

@WebListener
public class TemplateListener implements ServletContextListener {

   public TemplateListener() {
   }


   public void contextDestroyed( ServletContextEvent sce ) {
   }


   public void contextInitialized( ServletContextEvent sce ) {

      ServletContext servletContext = sce.getServletContext();
      JavaxServletWebApplication webApplication = JavaxServletWebApplication.buildApplication( servletContext );
      WebApplicationTemplateResolver resolver = new WebApplicationTemplateResolver( webApplication );

      resolver.setTemplateMode( TemplateMode.HTML );
      resolver.setPrefix( "WEB-INF/templates/" );
      resolver.setSuffix( ".html" );
      resolver.setCacheable( false );

      TemplateEngine templateEngine = new TemplateEngine();
      templateEngine.setTemplateResolver( resolver );
      servletContext.setAttribute( "templateEngine", templateEngine );
      servletContext.setAttribute( "templateWebApplication", webApplication );

      Logger.getGlobal().info( "Template engine OK" );
   }

}

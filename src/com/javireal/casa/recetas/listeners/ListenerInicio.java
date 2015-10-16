package com.javireal.casa.recetas.listeners;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * Application Lifecycle Listener implementation class ListenerInicio
 *
 */
public class ListenerInicio implements ServletContextListener{

	private static final Logger LOG = Logger.getLogger(ListenerInicio.class);
	Properties props = null;
	

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
	public void contextDestroyed(ServletContextEvent arg0)  { 
		LOG.info("Destruyendo contexto aplicacion");
		props = null;
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
	public void contextInitialized(ServletContextEvent arg0)  { 
    	// Fichero configuracion de Log4j
		try {
			props = new Properties();
			props.load(this.getClass().getResourceAsStream("/log4j.properties"));
			PropertyConfigurator.configure(props);
			LOG.info("Log4j cargado con exito");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
}

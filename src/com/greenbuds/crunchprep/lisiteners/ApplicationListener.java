package com.greenbuds.crunchprep.lisiteners;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import com.greenbuds.crunchprep.bo.server.ServerProperties;

/**
 * Application Lifecycle Listener implementation class ApplicationListener
 *
 */

public class ApplicationListener implements ServletContextListener {

	private ServletContext context = null;
	
	private final InputStream inputStream = getClass().getClassLoader()
			.getResourceAsStream("mail.properties");
	private final InputStream inputStreamCustomizationPractiseSession = getClass().getClassLoader()
			.getResourceAsStream("practice-session.properties");
	
	private Properties properties,practiceSession;
	private ServerProperties serverProperties;
	
    /**
     * Default constructor. 
     * @throws IOException 
     */
    public ApplicationListener() throws IOException {
        // TODO Auto-generated constructor stub
    	properties = new Properties();
		properties.load(inputStream);
		practiceSession=new Properties();
		practiceSession.load(inputStreamCustomizationPractiseSession);
		
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
        // TODO Auto-generated method stub
    	context = event.getServletContext();
    	serverProperties=ServerProperties.getInistance();
    	serverProperties.setProjectName(properties.getProperty("projectname"));
    	serverProperties.setIpaddress(properties.getProperty("ipaddress"));
        serverProperties.setDiff_id(Integer.parseInt(practiceSession.getProperty("practice.difficulty")));
    	serverProperties.setQuestion_pool(practiceSession.getProperty("practice.question_pool"));
    	serverProperties.setQuestions_count(Integer.parseInt(practiceSession.getProperty("practice.questions_count")));
    	serverProperties.setQuestions_mode(practiceSession.getProperty("practice.questions_mode"));
        serverProperties.setQuestions_time_limit(Integer.parseInt(practiceSession.getProperty("practice.questions_time_limit")));
        serverProperties.setDifficulty(Double.parseDouble(practiceSession.getProperty("practice.starting_difficulty")));
        serverProperties.setPattern(Integer.parseInt(practiceSession.getProperty("practice.starting_pattern")));
        serverProperties.setRefer_limit(Integer.parseInt(practiceSession.getProperty("refer_limit")));
    	context.setAttribute("server",serverProperties);
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    	
    }
	
}

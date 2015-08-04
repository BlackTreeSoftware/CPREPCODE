package com.greenbuds.crunchprep.util;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.EmailExceptions;
import com.greenbuds.crunchprep.setups.Exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class MailUtil.
 */
public class MailUtil {
	
	/** The host. */
	static String host;
	
	/** The port. */
	static String port;
	
	private  final static InputStream inputStream=MailUtil.class.getResourceAsStream("/mail.properties");
	private static  Properties properties1 =null;
	
	
/**
 * Send mail to one.
 *
 * @param content the content
 * @param receipient the receipient
 * @return true, if successfull
 * @throws CPException the CP exception
 */
public static boolean sendMailToOne(String subject,String content,String receipient) throws EmailExceptions{
	boolean flag=false;
	/*Properties properties1=new Properties();
	File file=new File("mail.properties");
	InputStream inputStream=MailUtil.class.getResourceAsStream("/mail.properties");*/
	try {
		intializePropertiesFile();
		//properties1.load(inputStream);
	}  catch (IOException exp) {
		// TODO Auto-generated catch block
		throw new EmailExceptions(Exceptions.PROPERTIES_READ_FAILED,exp);
	}
	final String username=properties1.getProperty("mail.username");
	final String password=properties1.getProperty("mail.password");
	host=properties1.getProperty("mail.host");
	port=properties1.getProperty("mail.port");
	//Get the Session Object`
	Properties properties=new  Properties();
	properties.put("mail.smtp.host",host);
	properties.put("mail.smtp.port",port);
	properties.put("mail.smtp.auth","true");
	
		Session session=Session.getDefaultInstance(properties,
		   new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(username, password);
			}
		});
		
		//Get the MIME Message Object
		MimeMessage message=new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(username));
			message.setSubject(subject);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receipient));
			//vary
			/*message.setContent("<h3>Thank you for register with CrunchPrep.Click the below link to verify your account.</h3> " +
					"<br />" +
					"<a href='http://www.greenbuds.co.in'>http://www.greenbuds.co.in</a> ","text/html"); */
			message.setContent(content,"text/html");
		//Send a Message 
			Transport.send(message);
			System.out.println("Message Sent Successfully......!");
			flag=true;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			throw new EmailExceptions(Exceptions.ADDRESS_EXCEPTION,e);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new EmailExceptions(Exceptions.MESSAGE_EXCEPTION,e);
		}
	return flag;
}
private static Properties intializePropertiesFile() throws IOException
{
	if(properties1==null)
	{
		properties1=new Properties();
		properties1.load(inputStream);	
	}
	return properties1;
}

/**
 * Send mail to many.
 *
 * @param content the content
 * @param to the to
 * @return true, if successful
 * @throws CPException the CP exception
 */
public static boolean sendMailToMany(String subject,String content,String[] to) throws EmailExceptions{
	boolean flag=false;
	try {
		intializePropertiesFile();
		//properties1.load(inputStream);
	}  catch (IOException exp) {
		// TODO Auto-generated catch block
		throw new EmailExceptions(Exceptions.PROPERTIES_READ_FAILED,exp);
	}
	final String username=properties1.getProperty("mail.username");
	final String password=properties1.getProperty("mail.password");
	host=properties1.getProperty("mail.host");
	port=properties1.getProperty("mail.port");
	//Get the Session Object`
	Properties properties=new  Properties();
	properties.put("mail.smtp.host",host);
	properties.put("mail.smtp.port",port);
	properties.put("mail.smtp.auth","true");
	
		Session session=Session.getDefaultInstance(properties,
		   new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(username, password);
			}
		});
		
		//Get the MIME Message Object
		MimeMessage message=new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(username));
			message.setSubject(subject);
			InternetAddress[] addressTo = new InternetAddress[to.length];
		    for (int i = 0; i < to.length; i++)
		    {
		        addressTo[i] = new InternetAddress(to[i]);
		    }
		    message.setRecipients(RecipientType.TO, addressTo);
			//vary
			/*message.setContent("<h3>Thank you for register with CrunchPrep.Click the below link to verify your account.</h3> " +
					"<br />" +
					"<a href='http://www.greenbuds.co.in'>http://www.greenbuds.co.in</a> ","text/html"); */
			message.setContent(content,"text/html");
		//Send a Message 
			Transport.send(message);
			System.out.println("Message Sent Successfully......!");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			throw new EmailExceptions(Exceptions.ADDRESS_EXCEPTION,e);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new EmailExceptions(Exceptions.MESSAGE_EXCEPTION,e);
		}
	return flag;
}

}

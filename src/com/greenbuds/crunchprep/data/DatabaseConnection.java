/***************************************************************************
 * Copyright (c) 2014 , bnandigama , All rights reserved.   * Product Name : CrunchPrep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.data;
 
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.setups.Exceptions;
 

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseConnection.
 */
public class DatabaseConnection {
	
	/** The Constant LOGGER. */
	private final static Logger LOGGER = Logger.getLogger( DatabaseConnection.class );
	
	/** The Constant DB_LOOKUP. */
	private final static String DB_LOOKUP = "java:/comp/env/jdbc/crunchPrepDB";
	

	/** The context. */
	private static InitialContext context;
	
	private static void initContext()   {
						
				if ( context == null ) {
					try {
						context = new InitialContext();
					} catch ( NamingException e ) {
						LOGGER.error( "Initial context instanciation error.", e );
						 
					}
					if ( context == null ) {
						LOGGER.error( "Initial context is null." );
						 
					}
				}
			}

/**
 * Gets the crunch prep connection.
 *
 * @return the crunch prep connection
 * @throws ConnectionException 
 */
public static Connection getCrunchPrepConnection() throws ConnectionException  {
		
		Connection connection = null;
		DataSource dmsDatasource = null; 

		// Initialization of the context
		initContext();
		
		if ( context != null ) {
			
			try {
				//System.out.println(DatabaseConnection.DB_LOOKUP);
				dmsDatasource = (DataSource)context.lookup(DatabaseConnection.DB_LOOKUP );
				//System.out.println("dmsDatasource"+dmsDatasource);
			} catch ( NamingException e ) {
				throw new ConnectionException(Exceptions.CONNECTION_REFUSED,e);
				//LOGGER.error( "Error while trying to get the DMS datasource", e );
				 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new ConnectionException(Exceptions.CONNECTION_REFUSED,e);
				//LOGGER.error( "Error while trying to get the DMS datasource", e );
				//e.printStackTrace();
			}
			if ( dmsDatasource == null ) {
				throw new ConnectionException(Exceptions.CONNECTION_REFUSED);
				//LOGGER.error( "DMS data source is null." );
				 
			}

			try {
				connection = dmsDatasource.getConnection();
			} catch ( SQLException e ) {
				throw new ConnectionException(Exceptions.CONNECTION_REFUSED,e);
				//LOGGER.error( "Error while trying to get a connection through the CrunchPrep datasource", e );
				 
			}
			if( connection == null ) {
				throw new ConnectionException(Exceptions.CONNECTION_REFUSED);
				//LOGGER.error ( "Connection on DMS is null." );
				 
	        }
		}else{
			throw new ConnectionException(Exceptions.CONNECTION_REFUSED);	
		}

		return connection;
	}


}

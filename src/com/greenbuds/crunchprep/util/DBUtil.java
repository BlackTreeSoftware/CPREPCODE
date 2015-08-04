package com.greenbuds.crunchprep.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.Exceptions;
import com.mysql.jdbc.ResultSetMetaData;

 

public class DBUtil {
	/** The Constant LOGGER. */
	private final static Logger LOGGER = Logger.getLogger( DBUtil.class );

  
	public static void closeConnection(Connection conn){
	    
  	try{
  	if(conn!=null)
      conn.close();
          } catch(SQLException ex){        
          	LOGGER.error(Exceptions.CONNECTION_CLOSING_EXCEPTION);
  }
}
  
	public static void closeConnection(Statement st,Connection conn){
    
    	try {
			st.close();
		} catch (SQLException e1) {
			  LOGGER.error(Exceptions.STATEMENT_CLOSING_EXCEPTION);
		}
    	try{
    	if(conn!=null)
        conn.close();
            } catch(SQLException ex){        
            	LOGGER.error(Exceptions.CONNECTION_CLOSING_EXCEPTION);
    }
}
    public static void closeConnection(ResultSet rs,Statement st,Connection conn){
    	try {
    		if(rs!=null)
			rs.close();
		} catch (SQLException e1) {			 
			 LOGGER.error(Exceptions.RESULTSET_CLOSING_EXCEPTION);
		}
    	try {
    		if(st!=null)
			st.close();
		} catch (SQLException e1) {
			LOGGER.error(Exceptions.STATEMENT_CLOSING_EXCEPTION);
		}
    	try{
    	if(conn!=null)
        conn.close();
            } catch(SQLException ex){        
            	LOGGER.error(Exceptions.CONNECTION_CLOSING_EXCEPTION);
    }
}
    public static JSONArray convertResultSetIntoJSONFormate(String inputQuiree) throws ConnectionException, DBException, JSONException
    {
    	JSONArray  parent=null;
    	JSONObject child=null;
    	Connection connection=DatabaseConnection.getCrunchPrepConnection();
    	try {
    		Statement statement=connection.createStatement();
    		ResultSet rs=statement.executeQuery(inputQuiree);
            java.sql.ResultSetMetaData metaData=rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			while(rs.next())
			{
				if(rs.isFirst()) parent=new JSONArray();
				child=new JSONObject();
				for(int i=1;i<=columnCount;i++){
					String columnName=metaData.getColumnName(i);
					child.put(columnName, rs.getString(columnName));
			}
				parent.put(child);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBException(Exceptions.SQL_EXCEPTION,e);
		}finally{
			DBUtil.closeConnection(connection);
		}
    	return parent;
    	
    }

}

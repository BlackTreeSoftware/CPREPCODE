package com.greenbuds.crunchprep.dao.contentcreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.DataInterpretionBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.Exceptions;
import com.greenbuds.crunchprep.util.DBUtil;

/**
 * The Class DataInterpretionDAO.
 */
public class DataInterpretionDAO implements IDataInterpretionDAO {
	
	public static DataInterpretionDAO dataInterpretionDAO;
	private DataInterpretionDAO(){
		
		
		
	}
	
	public static synchronized DataInterpretionDAO getInstance(){
		if(dataInterpretionDAO==null) dataInterpretionDAO = new DataInterpretionDAO();
		return dataInterpretionDAO;
	
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.contentcreator.IDataInterpretionDAO#addDataInterpretion(com.greenbuds.crunchprep.bo.contentcreator.DataInterpretionBO)
	 */
	public String addDataInterpretion(DataInterpretionBO dataInterpretionBO) throws ConnectionException, DBException{
		
		String result ="Error".toLowerCase();
		
		Connection con=null;
		Statement st = null;
		ResultSet rs = null;
		
		String query;
		
		try {
			con=DatabaseConnection.getCrunchPrepConnection();
			st=con.createStatement();
			
			String check ="select cdg.* from cp_data_interpretation_graph cdg where cdg.graph_title like'"+dataInterpretionBO.getGraph_title()+"'";
			      System.out.println("checkingQuery:"+check);
			rs=st.executeQuery(check);
			if(rs.next()){
			    	  
			   result="exist";
			    	  
			  }else{
			
			
			query="INSERT INTO `crunchprep`.`cp_data_interpretation_graph` (`difficulty`,`graph_title`,`graph`, `access_type`, `graph_status`) VALUES ("+dataInterpretionBO.getDifficulty()+",'"+dataInterpretionBO.getGraph_title()+"','"+dataInterpretionBO.getGraph()+"','"+dataInterpretionBO.getAccess_type()+"','"+dataInterpretionBO.getGraph_status()+"')";
		
			if(st.executeUpdate(query)==1){
				
				result ="success";
				
			}
			      }
		} catch (SQLException e) {
		    throw new DBException(e);
		}finally{
			
			DBUtil.closeConnection(st, con);			
		}
		
		return result;
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.contentcreator.IDataInterpretionDAO#getDataInterpretionList(com.greenbuds.crunchprep.bo.contentcreator.DataInterpretionBO)
	 */
	public List<DataInterpretionBO>getDataInterpretionList(DataInterpretionBO dataInterpretionBO) throws ConnectionException, DBException{
		String result ="Error".toLowerCase();
		
		Connection con=null;
		Statement st = null;
		ResultSet rs = null;
		List<DataInterpretionBO> dataInterpretionList = new ArrayList<DataInterpretionBO>();
		String query;
		 
		
			
			try {
				con=DatabaseConnection.getCrunchPrepConnection();
				st=con.createStatement();
				//query="select cdig.graph_id,cdig.graph_title,cdig.graph,cdig.difficulty,cdig.access_type,cdig.graph_status from cp_data_interpretation_graph cdig where cdig.graph_id like '%'";
				query="select cdig.graph_id,cdig.graph_title,cdig.graph,cdig.difficulty,dl.diff_name,cdig.access_type,cdig.graph_status from cp_data_interpretation_graph cdig,cp_difficulty_level dl where  dl.diff_id=cdig.difficulty and  cdig.graph_id like '%'";
				rs = st.executeQuery(query);
				while(rs.next()){
					
					dataInterpretionBO = new DataInterpretionBO();
					dataInterpretionBO.setGraph_id(rs.getInt("graph_id"));
					dataInterpretionBO.setGraph_title(rs.getString("graph_title"));
					dataInterpretionBO.setGraph(rs.getString("graph"));
					dataInterpretionBO.setDifficulty(rs.getInt("difficulty"));
					dataInterpretionBO.setDifficulty_name(rs.getString("diff_name"));
					dataInterpretionBO.setAccess_type(rs.getString("access_type"));
					dataInterpretionBO.setGraph_status(rs.getString("graph_status"));
					
					 dataInterpretionList.add(dataInterpretionBO);
					
					
				}
			} catch (SQLException e) {
				throw new DBException(e);
			}finally{
				
				DBUtil.closeConnection(st, con);			
			}
			
						
				result ="success";
		
	
		return dataInterpretionList;
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.contentcreator.IDataInterpretionDAO#getDeleteDataInterpretation(java.lang.String[])
	 */
	public String getDeleteDataInterpretation(String[] dataIds) throws ConnectionException, DBException{
		
		String result = "Error".toLowerCase();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query;	
		try {
			con=DatabaseConnection.getCrunchPrepConnection();
			st = con.createStatement();
			for(int i=0;i<dataIds.length;i++){
			query ="DELETE FROM cp_data_interpretation_graph WHERE graph_id="+dataIds[i];
				//query="DELETE FROM cp_data_interpretation_graph WHERE graph_id";
			st.addBatch(query);
			
			
			}
			 int[] i = st.executeBatch();
			 if(i.length!=0) {
		    	  result="success";
		       }
	
		} catch (SQLException e) {
			
		// e.printStackTrace();
		
		  
		}finally{
			
		   DBUtil.closeConnection(st, con);
			
		}

		return result;
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.contentcreator.IDataInterpretionDAO#updateDataInterpretion(com.greenbuds.crunchprep.bo.contentcreator.DataInterpretionBO)
	 */
	public String updateDataInterpretion(DataInterpretionBO dataInterpretionBO) throws SQLException, DBException {
		
		
	 String result="Error".toLowerCase();
	 
	 
	 Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query;	
		
			try {
				con=DatabaseConnection.getCrunchPrepConnection();
				st = con.createStatement();
				
				 query="UPDATE cp_data_interpretation_graph SET `difficulty`="+dataInterpretionBO.getDifficulty()+", `graph_title`='"+dataInterpretionBO.getGraph_title()+"',`graph`='"+dataInterpretionBO.getGraph()+"',`access_type`='"+dataInterpretionBO.getAccess_type()+"',`graph_status`='"+dataInterpretionBO.getGraph_status()+"' WHERE `graph_id`="+dataInterpretionBO.getGraph_id()+"";  
				 
		            if(st.executeUpdate(query)==1){
		            	
		            	result ="success";
		            	
		            }
			
			} catch (ConnectionException e) {
				  throw new DBException(e);
			}finally{
				
				  DBUtil.closeConnection(st, con);
				
			}
		
	
	
	 return result;
		
		
	}
	
	
	
	
	
	}



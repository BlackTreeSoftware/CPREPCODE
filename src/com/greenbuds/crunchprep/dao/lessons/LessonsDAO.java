package com.greenbuds.crunchprep.dao.lessons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.lessons.LessonsHierarchy;
import com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.Section;
import com.greenbuds.crunchprep.util.DBUtil;

/**
 * The Class LessonsDAO.
 */
public class LessonsDAO implements ILessonsDAO {

	private static LessonsDAO lessonsDAO;
	private  ArrayList<LessonsHierarchy> childNodes;
	 private java.util.Map<String, String> childNodesName;
	 private ArrayList<LessonsHierarchy> parentNodes;
	 private  ArrayList<String> childNode;
	 private JSONObject jsonObject;
	 private JSONObject destination;
	 private StringBuffer finalData;
	 private StringBuffer comboBoxData;
	 private  JSONArray comboBox; 
	int index = 1;

	private LessonsDAO() {

	}

	/**
	 * Gets the single instance of LessonsDAO.
	 * 
	 * @return single instance of LessonsDAO
	 */
	public static synchronized LessonsDAO getInstance() {

		if (lessonsDAO == null)
			lessonsDAO = new LessonsDAO();
		return lessonsDAO;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.greenbuds.crunchprep.dao.lessons.ILessonsDAO#addConfidenceLevel(com
	 * .greenbuds.crunchprep.bo.lessons.LessonsmasterBO)
	 */
	public String addConfidenceLevel(LessonsmasterBO lessonsmasterBO)
			throws DBException, SQLException {
		System.out.println("Calling the DAO confidence@@@@@@@@@@@@@@@@@@@");

		String result = "Error".toLowerCase();

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = DatabaseConnection.getCrunchPrepConnection();
			String query = "INSERT INTO cp_lessons_confidence (`user_id`, `sublesson_id`, `lesson_confidence`) VALUES ("+ lessonsmasterBO.getUser_id()
					+ ", "
					+ lessonsmasterBO.getSublesson_id()
					+ ", '"
					+ lessonsmasterBO.getLesson_confidence() + "')";
			System.out.println("query:\t\t" + query);
			st = con.createStatement();
			if (st.executeUpdate(query) == 1) {

				result = "success";

			}

		} catch (ConnectionException e) {
			throw new DBException(e);
		} finally {

			DBUtil.closeConnection(st, con);

		}

		return result;

	}

	public boolean levelChecking(LessonsmasterBO lessonsmasterBO)
			throws SQLException {

		boolean flag = false;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DatabaseConnection.getCrunchPrepConnection();
			String query = "select cplc.* from  cp_lessons_confidence cplc where cplc.user_id="
					+ lessonsmasterBO.getUser_id()
					+ " and cplc.sublesson_id="
					+ lessonsmasterBO.getSublesson_id() + "";

			System.out.println("Checking query:" + query);
			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				lessonsmasterBO.setLesson_confidence_id(rs
						.getInt("lesson_confidence_id"));
				flag = true;

			}

		} catch (ConnectionException e) {
			e.printStackTrace();
		} finally {

			DBUtil.closeConnection(rs, st, con);
		}

		return flag;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.greenbuds.crunchprep.dao.lessons.ILessonsDAO#updateConfidenceLevel
	 * (com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO)
	 */
	public String updateConfidenceLevel(LessonsmasterBO lessonsmasterBO)
			throws DBException, SQLException {

		String result = "Error".toLowerCase();

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = DatabaseConnection.getCrunchPrepConnection();
			String query = "UPDATE cp_lessons_confidence SET `user_id`="
					+ lessonsmasterBO.getUser_id() + ", `sublesson_id`="
					+ lessonsmasterBO.getSublesson_id()
					+ ", `lesson_confidence`='"
					+ lessonsmasterBO.getLesson_confidence()
					+ "' WHERE  `lesson_confidence_id`="
					+ lessonsmasterBO.getLesson_confidence_id() + "";
			System.out.println("Update Quesry\t" + query);
			st = con.createStatement();
			if (st.executeUpdate(query) == 1) {

				result = "success";

			}

		} catch (ConnectionException e) {
			throw new DBException(e);
		} finally {

			DBUtil.closeConnection(st, con);
		}
		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.greenbuds.crunchprep.dao.lessons.ILessonsDAO#getConfidenceLevels(
	 * com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO)
	 */
	public List<LessonsmasterBO> getConfidenceLevels(LessonsmasterBO lessonsmasterBO) throws DBException, SQLException{

		List<LessonsmasterBO> confidenceLevelList = new ArrayList<LessonsmasterBO>();

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = DatabaseConnection.getCrunchPrepConnection();
			String query = "select cplc.lesson_confidence_id,cplc.user_id,cplc.sublesson_id,cplc.lesson_confidence  from cp_lessons_confidence cplc where cplc.lesson_confidence_id ="+lessonsmasterBO.getLesson_confidence_id()+"";
			
			//System.out.println("Confidence Levels List Query:::::::::::::::::::::::::::::::::::::::::"+query);
			st = con.createStatement();
			rs = st.executeQuery(query);
             
			while (rs.next()) {
				LessonsmasterBO bo = new LessonsmasterBO();

				bo.setLesson_confidence_id(rs.getInt("lesson_confidence_id"));
				bo.setUser_id(rs.getInt("user_id"));
				bo.setSublesson_id(rs.getInt("sublesson_id"));
				bo.setLesson_confidence(rs.getString("lesson_confidence"));
				confidenceLevelList.add(bo);

			}

		} catch (ConnectionException e) {
			throw new DBException(e);
		} finally {

			DBUtil.closeConnection(rs, st, con);

		}
		return confidenceLevelList;

	}

	@Override
	public String addBookMarks(LessonsmasterBO lessonsmasterBO)
			throws DBException, SQLException {

		String result = "";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {

			connection = DatabaseConnection.getCrunchPrepConnection();

			String qry = "SELECT c.lesson_bookmark_id  FROM `cp_lessons_bookmarks` c WHERE c.user_id = "
					+ lessonsmasterBO.getUser_id()
					+ " and c.sublesson_id ="
					+ lessonsmasterBO.getSublesson_id();
			statement = connection.createStatement();
			System.out.println("In book mark"+qry);
			resultSet = statement.executeQuery(qry);
			if (resultSet.next()) {

				int bookmarkid = resultSet.getInt(1);
				qry = "UPDATE `cp_lessons_bookmarks` c SET c.lesson_bookmark ='"
						+ lessonsmasterBO.getLesson_bookmark()
						+ "' WHERE  c.lesson_bookmark_id =" + bookmarkid;
				int res = statement.executeUpdate(qry);
				if (res > 0) {
					result = "success";
					System.out.println("Bookmark Updation Done");

				} else {

					result = "error";
					System.out.println("Book Mark Updation Error");
				}

			} else {

				qry = "INSERT INTO `cp_lessons_bookmarks` (`user_id`, `sublesson_id`, `lesson_bookmark`) VALUES (?, ?, ?)";
				preparedStatement = connection.prepareStatement(qry);

				preparedStatement.setInt(1, lessonsmasterBO.getUser_id());
				preparedStatement.setInt(2, lessonsmasterBO.getSublesson_id());
				preparedStatement.setString(3,
						lessonsmasterBO.getLesson_bookmark());

				System.out.println("Qry = " + preparedStatement);

				int res = preparedStatement.executeUpdate();

				if (res > 0) {
					result = "success";
					System.out.println("Bookmark Insretion Done");

				} else {

					result = "error";
					System.out.println("Book Mark Insretion Error");
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(resultSet, statement, connection);

		}

		return result;
	}

	@Override
	public String addNotes(LessonsmasterBO lessonsmasterBO) throws DBException,
			SQLException {
		// TODO Auto-generated method stub

		String result = "";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {

			connection = DatabaseConnection.getCrunchPrepConnection();

			String qry = "SELECT c.lesson_note_id FROM  `cp_lesson_notes` c  WHERE c.user_id = "
					+ lessonsmasterBO.getUser_id()
					+ " and c.sublesson_id ="
					+ lessonsmasterBO.getSublesson_id();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(qry);
			if (resultSet.next()) {

				int noteid = resultSet.getInt(1);
				qry = "UPDATE  `cp_lesson_notes` c  SET c.lesson_note_name ='"
						+ lessonsmasterBO.getLesson_note_name()
						+ "' WHERE  c.lesson_note_id=" + noteid;
				int res = statement.executeUpdate(qry);
				if (res > 0) {
					result = "success";
					System.out.println("Notes Updation Done");

				} else {

					result = "error";
					System.out.println("Notes  Updation Error");
				}

			} else {

				qry = "INSERT INTO `cp_lesson_notes` (`user_id`, `sublesson_id`, `lesson_note_name`) VALUES (?, ?, ?);";

				preparedStatement = connection.prepareStatement(qry);

				preparedStatement.setInt(1, lessonsmasterBO.getUser_id());
				preparedStatement.setInt(2, lessonsmasterBO.getSublesson_id());
				preparedStatement.setString(3,
						lessonsmasterBO.getLesson_note_name());

				System.out.println("Qry = " + preparedStatement);

				int res = preparedStatement.executeUpdate();

				if (res > 0) {
					result = "success";
					System.out.println("Insretion Done");

				} else {

					result = "error";
					System.out.println("Insretion Error");
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(resultSet, statement, connection);

		}

		return result;
	}

	public LessonsmasterBO getLessonDetails(LessonsmasterBO lessonsmasterBO)
			throws DBException, SQLException {
		// List<LessonsmasterBO> lessonsmasterBOs = new
		// ArrayList<LessonsmasterBO>();
		Connection con = null;
		Statement st = null;
		ResultSet resultSet = null,rs=null;
		LessonsmasterBO bo = null;
		try {
			System.out.println("In DAO");
			con = DatabaseConnection.getCrunchPrepConnection();
		    String query ="SELECT m.lesson_id,s.sublesson_id,m.lesson_name AS masterlesson,s.lesson_name,s.lesson_text,sec.section_name," +
		    		"sec.section_id,(" +
		    		"SELECT con.lesson_confidence" +
		    		" FROM cp_lessons_confidence con" +
		    		" WHERE con.user_id='"+lessonsmasterBO.getUser_id()+"' AND con.sublesson_id=s.sublesson_id) AS confidence,(" +
		    		"IFNULL((SELECT n.lesson_note_name " +
		    		" FROM cp_lesson_notes n" +
		    		" WHERE n.user_id='"+lessonsmasterBO.getUser_id()+"' AND n.sublesson_id=s.sublesson_id),'  ')) AS note,(" +
		    		"SELECT bok.lesson_bookmark" +
		    		" FROM cp_lessons_bookmarks bok" +
		    		" WHERE bok.user_id='"+lessonsmasterBO.getUser_id()+"' AND bok.sublesson_id=s.sublesson_id) AS bookmark" +
		    		" FROM cp_lessons s,cp_lessons_masters m,cp_sections sec" +
		    		" WHERE s.lesson_id=m.lesson_id  AND m.section_id=sec.section_id AND s.sublesson_id='"+lessonsmasterBO.getSublesson_id()+"'" +
		    		" GROUP BY s.sublesson_id" +
		    		" ORDER BY s.lesson_id,s.sublesson_id";
		    String select="select * from cp_lesson_session where activity_id='"+lessonsmasterBO.getActivity_id()+"' and sublesson_id ='"+lessonsmasterBO.getSublesson_id()+"'";
		    String session_query="INSERT INTO `cp_lesson_session` (`activity_id`, `sublesson_id`) VALUES ('"+lessonsmasterBO.getActivity_id()+"', '"+lessonsmasterBO.getSublesson_id()+"')";   
		    System.out.println("Lesson Details :::: "+query);
		    System.out.println("Lesson Details :::: "+session_query);
		    st=con.createStatement();
		           resultSet = st.executeQuery(query);	
		           if(resultSet.next()){
		    	   bo = new  LessonsmasterBO();  
		    	   bo.setLesson_id(resultSet.getInt("LESSON_ID"));
		    	   bo.setSublesson_id(resultSet.getInt("SUBLESSON_ID"));
		    	   bo.setLesson_name(resultSet.getString("MASTERLESSON"));
		    	   bo.setSublesson_name(resultSet.getString("LESSON_NAME"));
		    	   bo.setLesson_text(resultSet.getString("LESSON_TEXT"));
		    	   bo.setSection_id(resultSet.getInt("SECTION_ID"));
		    	   bo.setSection_name(resultSet.getString("SECTION_NAME"));
		    	   bo.setLesson_confidence(resultSet.getString("confidence"));
		    	   bo.setLesson_note_name(resultSet.getString("note"));
		    	   bo.setLesson_bookmark(resultSet.getString("bookmark"));
		      }
		       rs=st.executeQuery(select);
		       if(rs.next()){
		    	   
		       }
		       else{
		      int execution_status=st.executeUpdate(session_query); 
		      if(execution_status>0){
		    	 System.out.println("success in session");
		      }
		      }

		} catch (ConnectionException e) {
			throw new DBException(e);
		} finally {
			DBUtil.closeConnection(st, con);
		}
		return bo;
	}

	
	

   public String lessonsTaken(LessonsmasterBO lessonsmasterBO) throws DBException, SQLException{
	  String result="Error".toLowerCase();
	  Connection con = null;
	  Statement st = null;
	  ResultSet rs = null;
	  try {
		con = DatabaseConnection.getCrunchPrepConnection();
		 String query ="INSERT INTO `cp_lessons_taken` (`user_id`, `sublesson_id`, `timespent`, `status`) VALUES ('"+lessonsmasterBO.getUser_id()+"','"+lessonsmasterBO.getSublesson_id()+"', '"+lessonsmasterBO.getTime_spent()+"', 'TAKEN')";
	     System.out.println("query:\t\t"+query);
		 st=con.createStatement();
		 int i=st.executeUpdate(query); 
	     if(i==1){    	      	  
	    	  result ="success";    	  
	      }  
	 } catch (ConnectionException e) {
	    throw new DBException(e);
	 }finally{	
		DBUtil.closeConnection(st, con);	
	 }
	 return result;
}		
   
   
   public String lessonsSession(LessonsmasterBO lessonsmasterBO) throws DBException, SQLException{
		  String result="Error".toLowerCase();
		  Connection con = null;
		  Statement st = null;
		  ResultSet rs = null;
		  try {
			con = DatabaseConnection.getCrunchPrepConnection();
			 String query ="INSERT INTO `cp_user_activity` (`user_id`, `activity_type`) VALUES ('"+lessonsmasterBO.getUser_id()+"', 'LESSONS')";
			 String last_lesson="SELECT ACT.*,SES.*,LES.lesson_name" +
			 		" FROM cp_user_activity ACT,cp_lesson_session SES,cp_lessons LES" +
			 		" WHERE ACT.`status` LIKE 'ACTIVE' AND ACT.activity_type LIKE 'LESSONS' AND" +
			 		"" +
			 		" (SES.activity_id=(CASE IFNULL((" +
			 		" SELECT SES.sublesson_id" +
			 		" FROM cp_lesson_session SES" +
			 		" WHERE SES.activity_id LIKE ACT.activity_id" +
			 		" GROUP BY SES.activity_id" +
			 		" ORDER BY SES.row_id DESC),0) WHEN 0 THEN (ACT.activity_id-1) ELSE ACT.activity_id END))" +
			 		"" +
			 		" AND LES.sublesson_id=SES.sublesson_id AND ACT.USER_ID='"+lessonsmasterBO.getUser_id()+"'" +
			 		" ORDER BY SES.row_id  DESC" +
			 		" LIMIT 1";
			 System.out.println("the query is----------------------------------"+last_lesson);
			 st=con.createStatement();	
			 rs=st.executeQuery(last_lesson);
			 if(rs.next()){ 
				lessonsmasterBO.setLast_lesson(rs.getString("LESSON_NAME")); 
				lessonsmasterBO.setSublesson_id(Integer.parseInt(rs.getString("SUBLESSON_ID")));
			 }
			 int n = st.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
				ResultSet keys = st.getGeneratedKeys();
	            if(keys.next())
	            {
	           	 int key=keys.getInt(1);
	             lessonsmasterBO.setActivity_id(key);
	            }
				if (n > 1) {
					result = "success";
				}
				
		 } catch (ConnectionException e) {
		    throw new DBException(e);
		 }finally{	
			con.close();
		 }
		 return result;
	}		
   
   /**
 * @throws DBException 
 * @throws ConnectionException 
    * @author rrajulapati
 * @throws  
    */
   	@Override
   	public void lessonsHierarchyForCustomization(JSONObject destination) throws JSONException, ConnectionException, DBException  {
   		
   		Connection connection = null;
   		Statement stmt = null;
   		ResultSet rs = null;
   		ResultSetMetaData metaData = null;
   		int userId =  destination.getInt("userid");
   		
   		String quireeForParents = "(SELECT LM.lesson_id MAIN_LESSON_ID,LM.lesson_name MAIN_LESSON_NAME,LM.section_id SECTION_ID," +
   				"  IFNULL(LS.sublesson_id,0) SUB_LESSON_ID,IFNULL(LS.lesson_name,\"\") SUB_LESSON_NAME,"
   				+ " IFNULL(GROUP_CONCAT( LH1.child_id),0) CHILDS,IFNULL((SELECT BKMRS.lesson_bookmark FROM cp_lessons_bookmarks BKMRS "
   				+ " WHERE BKMRS.sublesson_id LIKE LS.sublesson_id AND BKMRS.user_id LIKE "
   				+ userId
   				+ " "
   				+ " GROUP BY BKMRS.lesson_bookmark_id),'UNMARKED') BOOKMARK,IFNULL((SELECT CNF.lesson_confidence FROM cp_lessons_confidence CNF WHERE CNF.sublesson_id " +
   				" LIKE LS.sublesson_id AND CNF.user_id LIKE "
   				+ userId
   				+ " "
   				+ " GROUP BY CNF.lesson_confidence_id),'NO') CONFIDENCE,IFNULL((SELECT NTS.lesson_note_name FROM cp_lesson_notes NTS WHERE NTS.sublesson_id LIKE " +
   				" LS.sublesson_id AND NTS.user_id LIKE "
   				+ userId
   				+ "  GROUP BY NTS.lesson_note_id),'NO')NOTES ,IFNULL((SELECT ROUND((COUNT(RES1.row_id)/COUNT(RES.row_id))*100) result FROM cp_test_results RES LEFT JOIN cp_test_results " +
   				" RES1 ON RES1.row_id=RES.row_id AND RES1.result='CORRECT' WHERE RES.userid LIKE '"+userId+"' AND RES.sublession_id LIKE SUB_LESSON_ID ),'Not Attempt') perncentage "
   				+ "  FROM cp_lessons_masters LM , (cp_lessons LS LEFT JOIN cp_lesson_hierarchy LH1 ON LH1.parent_id=LS.sublesson_id ) " +
   				" WHERE LS.lesson_id=LM.lesson_id AND LS.sublesson_id NOT IN(SELECT HRY.child_id FROM cp_lesson_hierarchy HRY) " +
   				" and LS.sublesson_id IN(SELECT DISTINCTROW sublesson_id  fROM cp_question_lessons LENS)  GROUP BY LS.sublesson_id  order by LM.lesson_id ) ";
   			
   		
        System.out.println("\n\n Parnt Quiree is!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: "+quireeForParents);
   		LessonsHierarchy node = null;

   		try {
   			// finalData.append(" Quiree is : "+quiree);
           connection = DatabaseConnection.getCrunchPrepConnection();
   			
   			childNodes = new ArrayList<LessonsHierarchy>();
   			childNode = new ArrayList<String>();
   			childNodesName = new HashMap<String, String>();
   			parentNodes = new ArrayList<LessonsHierarchy>();
   		//	destination=new JSONObject();
   			
   			

   			getNodesList(quireeForParents,connection,true);
   			
   			
   			destination.put(Section.VERBAL.getSectionName(),getParentForLessionCustomization(Section.VERBAL));
   			destination.put(Section.QUANTITATIVE.getSectionName(),getParentForLessionCustomization(Section.QUANTITATIVE));
   			//System.out.println("\n\n "+destination.toString());
   			

   		
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   			throw new DBException(e);
   		} finally {

   			DBUtil.closeConnection(connection);
   		}

   		
   	}
   
   
/**
 * @author rrajulapati
 */
	@Override
	public void lessonsHierarchyData(JSONObject destination) throws JSONException {
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData metaData = null;
		String userId = (String) destination.get("userid");
		
		String quireeForParents = "(SELECT LM.lesson_id MAIN_LESSON_ID,LM.lesson_name MAIN_LESSON_NAME,LM.section_id SECTION_ID," +
				"  IFNULL(LS.sublesson_id,0) SUB_LESSON_ID,IFNULL(LS.lesson_name,\"\") SUB_LESSON_NAME,"
				+ " IFNULL(GROUP_CONCAT( LH1.child_id),0) CHILDS,IFNULL((SELECT BKMRS.lesson_bookmark FROM cp_lessons_bookmarks BKMRS "
				+ " WHERE BKMRS.sublesson_id LIKE LS.sublesson_id AND BKMRS.user_id LIKE "
				+ userId
				+ " "
				+ " GROUP BY BKMRS.lesson_bookmark_id),'UNMARKED') BOOKMARK,IFNULL((SELECT CNF.lesson_confidence FROM cp_lessons_confidence CNF WHERE CNF.sublesson_id " +
				" LIKE LS.sublesson_id AND CNF.user_id LIKE "
				+ userId
				+ " "
				+ " GROUP BY CNF.lesson_confidence_id),'NO') CONFIDENCE,IFNULL((SELECT NTS.lesson_note_name FROM cp_lesson_notes NTS WHERE NTS.sublesson_id LIKE " +
				" LS.sublesson_id AND NTS.user_id LIKE "
				+ userId
				+ "  GROUP BY NTS.lesson_note_id),'NO')NOTES ,IFNULL((SELECT ROUND((COUNT(RES1.row_id)/COUNT(RES.row_id))*100) result FROM cp_test_results RES LEFT JOIN cp_test_results " +
				" RES1 ON RES1.row_id=RES.row_id AND RES1.result='CORRECT' WHERE RES.userid LIKE '"+userId+"' AND RES.sublession_id LIKE SUB_LESSON_ID ),'Not Attempt') perncentage "
				+ "  FROM cp_lessons_masters LM LEFT JOIN (cp_lessons LS LEFT JOIN cp_lesson_hierarchy LH1 ON LH1.parent_id=LS.sublesson_id ) " +
				" ON LS.lesson_id=LM.lesson_id AND LS.sublesson_id NOT IN(SELECT HRY.child_id FROM cp_lesson_hierarchy HRY)  GROUP BY LS.sublesson_id  order by LM.lesson_id ) ";
			
		String quireeForChilds= "  (SELECT LM.lesson_id  MAIN_LESSON_ID,LM.lesson_name MAIN_LESSON_NAME,LM.section_id SECTION_ID,IFNULL(LS.sublesson_id,0) SUB_LESSON_ID,IFNULL(LS.lesson_name,\"\") SUB_LESSON_NAME,IFNULL(GROUP_CONCAT( LH1.child_id),0)CHILDS,IFNULL((SELECT BKMRS.lesson_bookmark FROM cp_lessons_bookmarks BKMRS "
				+ " WHERE BKMRS.sublesson_id LIKE LS.sublesson_id AND BKMRS.user_id LIKE "
				+ userId
				+ " "
				+ "  GROUP BY BKMRS.lesson_bookmark_id),'UNMARKED') BOOKMARK,IFNULL((SELECT CNF.lesson_confidence FROM cp_lessons_confidence CNF WHERE CNF.sublesson_id LIKE LS.sublesson_id AND CNF.user_id LIKE "
				+ userId
				+ " "
				+ " GROUP BY CNF.lesson_confidence_id),'NO') CONFIDENCE,IFNULL((SELECT NTS.lesson_note_name FROM cp_lesson_notes NTS WHERE NTS.sublesson_id LIKE LS.sublesson_id AND NTS.user_id LIKE "
				+ userId
				+ "  GROUP BY NTS.lesson_note_id),'NO')NOTES ,IFNULL((SELECT ROUND((COUNT(RES1.row_id)/COUNT(RES.row_id))*100) result FROM cp_test_results RES LEFT JOIN cp_test_results " +
				" RES1 ON RES1.row_id=RES.row_id AND RES1.result='CORRECT' WHERE RES.userid LIKE '"+userId+"' AND RES.sublession_id LIKE SUB_LESSON_ID ),'Not Attempt') perncentage "
				+ "  FROM cp_lessons_masters LM LEFT JOIN (cp_lessons LS LEFT JOIN cp_lesson_hierarchy LH1 ON LH1.parent_id=LS.sublesson_id )ON LS.lesson_id=LM.lesson_id WHERE LS.sublesson_id  IN(SELECT HRY.child_id FROM cp_lesson_hierarchy HRY)  GROUP BY LS.sublesson_id)";
	
		System.out.println("\n\n\n\n \t\t\t Parnt Quiree is; "+quireeForParents+" \n Child Quiree is: "+quireeForChilds);
		LessonsHierarchy node = null;

		try {
			// finalData.append(" Quiree is : "+quiree);
             connection = DatabaseConnection.getCrunchPrepConnection();
			
			childNodes = new ArrayList<LessonsHierarchy>();
			childNode = new ArrayList<String>();
			childNodesName = new HashMap<String, String>();
			parentNodes = new ArrayList<LessonsHierarchy>();
		//	destination=new JSONObject();
			
			

			getNodesList(quireeForParents,connection,true);
			getNodesList(quireeForChilds,connection,false);
			
			destination.put(Section.VERBAL.getSectionName(),getParent(Section.VERBAL));
			destination.put(Section.QUANTITATIVE.getSectionName(),getParent(Section.QUANTITATIVE));
			//System.out.println("\n\n "+destination.toString());
			

		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}

		
	}
	/**
	 * @author rrajulapati
	 */
	private  void getNodesList(String quiree,Connection connection,boolean isParentNodesData) throws SQLException
	{
		Statement stmt = null;
		ResultSet rs = null;
		stmt = connection.createStatement();
		rs = stmt.executeQuery(quiree);
		LessonsHierarchy node = null;
		while (rs.next()) {

			String mainLessionId = rs.getString("MAIN_LESSON_ID");
			String mainLessionName = rs.getString("MAIN_LESSON_NAME");
			String sectionId = rs.getString("SECTION_ID");
			String subLessionId = rs.getString("SUB_LESSON_ID");
			String subLessionName = rs.getString("SUB_LESSON_NAME");
			String childs = rs.getString("CHILDS");
			String bookMarks = rs.getString("BOOKMARK");
			String confidence = rs.getString("CONFIDENCE");
			String notes = rs.getString("NOTES");
			String percentage=rs.getString("perncentage");
			node = new LessonsHierarchy();
			node.setMainLessonId(mainLessionId);
			node.setMainLessonName(mainLessionName);
			node.setSectionId(sectionId);
			node.setSubLessonId(subLessionId);
			node.setSubLessonName(subLessionName);
			node.setChilds(childs.split(","));
			node.setBookMark(bookMarks);
			node.setConfidence(confidence);
			node.setNotes(notes);
			node.setPerncentage(percentage);

			
			if (isParentNodesData) {
				parentNodes.add(node);
			} else {
				if (!node.getChilds()[0].equalsIgnoreCase("0")) {
					childNode.add(node.getSubLessonId());
				}
				childNodesName.put(node.getSubLessonId(),
						node.getSubLessonName()+","+node.getBookMark()+","+node.getConfidence()+","+node.getNotes()+","+node.getPerncentage());
				childNodes.add(node);
			}

		}
		
	}
	
	/**
	 * @author rrajulapati
	 */
	private  JSONObject getParentForLessionCustomization(Section section) throws JSONException{
		index=1;
		JSONObject targetData=new JSONObject();
		jsonObject = new JSONObject();
		comboBox=new JSONArray();
		TreeSet<String> parentLessionStart = new TreeSet<String>();
		
//finalData = new StringBuffer("<ul>");
		finalData = new StringBuffer("");
		comboBoxData=new StringBuffer("<option value=-1>Select</option>");
		
		for (LessonsHierarchy parent : parentNodes) {
			
           if(parent.getSectionId().equalsIgnoreCase(""+section.getSectionId())){
			
        	   if (parentLessionStart.add(parent.getMainLessonId())) {
				if (parentLessionStart.size() != 1) {
//finalData.append("</li>");
					finalData.append("</div>");

				}			
				/*
				finalData.append("<li class=\"mainmenu\" id=\"id"+parent.getMainLessonId()+"parent\"><input type=\"checkbox\" value=" +
						""+parent.getMainLessonId()+" class=\"parents\" onclick=\"$('.id"+parent.getMainLessonId()+"').prop('checked',$(this)." +
								"prop('checked'))\"> <a onClick=\"lessionId("+parent.getMainLessonId()+",\'parent\',0)\"> " +
										"" +parent.getMainLessonName() + "</a>");
				*/
				finalData.append("<div class=\"checkbox i-checks mainmenu\" id=\"id"+parent.getMainLessonId()+"parent\"><label>" +
						"<input type=\"checkbox\" value="+parent.getMainLessonId()+" class=\"parents\" onclick=\"$('.id"+parent.getMainLessonId()+"').prop('checked',$(this)."+
						"prop('checked'))\"> <i></i> <a onClick=\"lessionId("+parent.getMainLessonId()+",\'parent\',0)\"> " +
										"</a><strong>" +parent.getMainLessonName() + "</strong>");
															 
				
				comboBoxData.append("<option value="+parent.getMainLessonId()+">" +parent.getMainLessonName() + "</option>");
				comboBox.put(new JSONObject().put("id",parent.getMainLessonId()+"parent").put("text", parent.getMainLessonName()));

			}

			
        	   
		
				comboBoxData.append("<option value="+parent.getSubLessonId()+">" +parent.getSubLessonName() + "</option>");
				comboBox.put(new JSONObject().put("id",parent.getSubLessonId()+"child").put("text", parent.getSubLessonName()));
				System.out.println("The values are  "+parent.getSubLessonName());
				String notes = parent.getNotes().equalsIgnoreCase("NO") ? "NONOTES"
						: "YES";

			/*	finalData.append("<div> <div class=\"" + notes + "  "
						+ parent.getBookMark() + "  "
						+ parent.getConfidence() + "   id"+parent.getSubLessonId()+"child\"> ");
				finalData
						.append(" <section class=\"col-xs-10\">  " +
								" <input type=\"checkbox\" class=\"id"+parent.getMainLessonId()+" childs\" value="+parent.getSubLessonId()+">" +
								" <a onClick=\"lessionId("+parent.getSubLessonId()+",\'child\',"+index+")\">"
								+ parent.getSubLessonName()
								+ "</a>  ");*/
				finalData.append("<div class=\"checkbox i-checks\"" + notes + "  "
						+ parent.getBookMark() + "  "
						+ parent.getConfidence() + "  id= \"id"+parent.getSubLessonId()+"child\"><label>" +
						"<input type=\"checkbox\" value="+parent.getSubLessonId()+" class=\"id"+parent.getMainLessonId()+" childs\"> <i></i>" +
								parent.getSubLessonName()+"<a onClick=\"lessionId("+parent.getSubLessonId()+",\'child\\',"+index+")\"> </a>");
				
				
				jsonObject.put("" + index++, parent.getSubLessonId());
					/*	if(!parent.getNotes().equalsIgnoreCase("NO"))
						{
							finalData.append("<span><i class=\"fa paddy text-success fa-pencil-square-o\"></i></span>");
						}
						if(parent.getBookMark().trim().equalsIgnoreCase("MARKED")){
							finalData.append("<span><i class=\"fa text-success fa-bookmark-o paddy\"></i></span>");
						}*/
						if(!parent.getPerncentage().trim().equalsIgnoreCase("Not Attempt")){
							finalData.append("<span class=\"badge bg-success l-s-p-c\">"+parent.getPerncentage().trim()+"</span>");
						}
						finalData.append("</label></div>");
			

		
		//finalData.append("</li>");// ok Satisfied mail lessions tag
           }
 
		} 
		finalData.append("</ul>"); // ok tag successfully closed for global tag
		
		targetData.put(section.getSectionName()+"keySet", jsonObject);
		targetData.put(section.getSectionName()+"Hierarchy",finalData.toString());
		targetData.put(section.getSectionName()+"ComboBox", comboBox.toString());
		return targetData;
		
	}
	/**
	 * @author rrajulapati
	 */
	private  JSONObject getParent(Section section) throws JSONException{
		index=1;
		JSONObject targetData=new JSONObject();
		jsonObject = new JSONObject();
		comboBox=new JSONArray();
		TreeSet<String> parentLessionStart = new TreeSet<String>();
		
		finalData = new StringBuffer("<ul id=\"mainul\">");
		comboBoxData=new StringBuffer("<option value=-1>Select</option>");
		
		for (LessonsHierarchy parent : parentNodes) {
			
           if(parent.getSectionId().equalsIgnoreCase(""+section.getSectionId())){
			
        	   if (parentLessionStart.add(parent.getMainLessonId())) 
        	   {
				if (parentLessionStart.size() != 1) {
					finalData.append("</li>");

				}			
				
				finalData.append("<li class=\"mainmenu \" id=\"id"+parent.getMainLessonId()+"parent\">" +
						" <a href=\"#\" onClick=\"javascript:accordianCall('id"+parent.getMainLessonId()+"parent');\" class=\"toogle\">" +
					  "<i style=\"color:#11a2a4\" class=\"fa fa-chevron-down\" id=\"downid"+parent.getMainLessonId()+"parent\"></i>" +
					  "<i class=\"fa fa-chevron-right\" style=\"display:none; color:#11a2a4;\" id=\"rightid"+parent.getMainLessonId()+"parent\"></i></a>"+
						"<a onClick=\"lessionId("+parent.getMainLessonId()+",\'parent\',0)\"> " +parent.getMainLessonName() + "</a>");
				comboBoxData.append("<option value="+parent.getMainLessonId()+">" +parent.getMainLessonName() + "</option>");
				comboBox.put(new JSONObject().put("id",parent.getMainLessonId()+"parent").put("text", parent.getMainLessonName()));

			}

			

			if (!parent.getChilds()[0].equalsIgnoreCase("0")) {
				
				finalData.append("<ul> <li id=\"ids"+parent.getSubLessonId()+"parent\">	" +
						" <a href=\"#\" onClick=\"javascript:accordianCall('ids"+parent.getSubLessonId()+"parent');\" class=\"toogle\" id=\"toggle\">" +
							"<i style=\"color:#11a2a4\" class=\"fa fa-chevron-down\" id=\"downids"+parent.getSubLessonId()+"parent\"></i>" +
									"<i class=\"fa fa-chevron-right\" style=\"display:none; color:#11a2a4;\" id=\"rightids"+parent.getSubLessonId()+"parent\"></i></a>"+ 
					" <a onClick=\"lessionId("+parent.getSubLessonId()+",\'parent\',0)\">" + parent.getSubLessonName()
						+ "</a><ul>");
				
				for (String child : parent.getChilds()) {
					if (childNode.contains(child)) {
						finalData.append(" <li  id=\"idc"+child+"parent\">"+
					" <a href=\"#\" onClick=\"javascript:accordianCall('idc"+child+"parent');\" class=\"toogle\" id=\"toggle\">" +
							"<i style=\"color:#11a2a4\" class=\"fa fa-chevron-down\" id=\"downidc"+child+"parent\"></i>" +
									"<i class=\"fa fa-chevron-right\" style=\"display:none; color:#11a2a4;\" id=\"rightidc"+child+"parent\"></i></a>"+ 
								"<a onClick=\"lessionId("+child+",\'parent\',0)\">"
								+ childNodesName.get(child).split(",")[0] + "</a>");
						comboBoxData.append("<option value="+child+">" +childNodesName.get(child).split(",")[0] + "</option>");
						comboBox.put(new JSONObject().put("id",child+"parent").put("text", childNodesName.get(child).split(",")[0]));
					
					}
					// hieraricy.put("<li><a onClick=\"lessionId("++")\">"+child+"</a>");
					
					if (childNode.contains(child)) {

						getChilds(child);

					} else {
						
						LessonsHierarchy kid = null;
						for(LessonsHierarchy childern:childNodes)
						{
							if(childern.getSubLessonId().equalsIgnoreCase(child)){
								kid=childern;
								break;
							}
						}
						
						
						String notes = parent.getNotes().equalsIgnoreCase("NO") ? "NONOTES"
								: "YES";

						finalData.append(" <li class=\"" + notes + "  "
								+ kid.getBookMark() + "  "
								+ kid.getConfidence() + "  id"+child+"child\"> ");
						finalData
								.append("<section class=\"col-xs-10\">   <a onClick=\"lessionId("+child+",\'child\',"+index+")\">"
										+ childNodesName.get(child).split(",")[0]
										+ "</a> ");
						comboBoxData.append("<option value="+child+">" +childNodesName.get(child).split(",")[0] + "</option>");
						comboBox.put(new JSONObject().put("id",child+"child").put("text", childNodesName.get(child).split(",")[0]));
						
						jsonObject.put("" + index++, child);
						if(!kid.getNotes().equalsIgnoreCase("NO"))
						{
							finalData.append("<span><i class=\"fa paddy text-success fa-pencil-square-o\"></i></span>");
						}
						if(kid.getBookMark().trim().equalsIgnoreCase("MARKED")){
							finalData.append("<span><i class=\"fa text-success fa-bookmark-o paddy\"></i></span>");
						}
						if(!kid.getPerncentage().trim().equalsIgnoreCase("Not Attempt")){
							finalData.append("<span class=\"paddy\"> <span class=\"badge bg-success\">"+kid.getPerncentage().trim()+"</span></span>");
						}
						
						finalData.append("</section></li>");

					}
				}

				finalData.append("</ul></li></ul>");
			} else {
				
				comboBoxData.append("<option value="+parent.getSubLessonId()+">" +parent.getSubLessonName() + "</option>");
				comboBox.put(new JSONObject().put("id",parent.getSubLessonId()+"child").put("text", parent.getSubLessonName()));
			//	System.out.println("The values are  "+parent.getSubLessonName());
				String notes = parent.getNotes().equalsIgnoreCase("NO") ? "NONOTES"
						: "YES";

				finalData.append("<ul> <li class=\"" + notes + "  "
						+ parent.getBookMark() + "  "
						+ parent.getConfidence() + "   id"+parent.getSubLessonId()+"child\"> ");
				finalData
						.append(" <section class=\"col-xs-10\"> <a onClick=\"lessionId("+parent.getSubLessonId()+",\'child\',"+index+")\">"
								+ parent.getSubLessonName()
								+ "</a>  ");
				jsonObject.put("" + index++, parent.getSubLessonId());
						if(!parent.getNotes().equalsIgnoreCase("NO"))
						{
							finalData.append("<span><i class=\"fa paddy text-success fa-pencil-square-o\"></i></span>");
						}
						if(parent.getBookMark().trim().equalsIgnoreCase("MARKED")){
							finalData.append("<span><i class=\"fa text-success fa-bookmark-o paddy\"></i></span>");
						}
						if(!parent.getPerncentage().trim().equalsIgnoreCase("Not Attempt")){
							finalData.append("<span class=\"paddy\"> <span class=\"badge bg-success\">"+parent.getPerncentage().trim()+"</span></span>");
						}
						finalData.append("</section></li></ul>");
			}

		
		//finalData.append("</li>");// ok Satisfied mail lessions tag
           }
 
		} 
		finalData.append("</ul>"); // ok tag successfully closed for global tag
		System.out.println("final data : "+finalData);
		
		targetData.put(section.getSectionName()+"keySet", jsonObject);
		targetData.put(section.getSectionName()+"Hierarchy",finalData.toString());
		targetData.put(section.getSectionName()+"ComboBox", comboBox.toString());
		return targetData;
		
	}
	
	
	
	/**
	 * @author rrajulapati
	 */
	private  String getChilds(String findChild) throws JSONException {
		String subChilds = "";

		for (LessonsHierarchy childs : childNodes) {


			if (childs.getSubLessonId().equalsIgnoreCase(findChild)) {
				if (!childs.getChilds()[0].equalsIgnoreCase("0")) {

					finalData.append("<ul id=\"mainul\">");
					for (String chld : childs.getChilds()) {
					
						
						// if(subChilds.isEmpty())
						// subChilds="Sub Child :"+chld;
						if (childNode.contains(chld)) {
							finalData.append("<li id=\"ids"+chld+"parent\">" +
									" <a href=\"#\" onClick=\"javascript:accordianCall('ids"+chld+"parent');\" class=\"toogle\" id=\"toggle\">" +
	"<i style=\"color:#11a2a4\" class=\"fa fa-chevron-down\" id=\"downids"+chld+"parent\"></i>" +
	"<i class=\"fa fa-chevron-right\" style=\"display:none; color:#11a2a4;\" id=\"rightids"+chld+"parent\"></i></a>" +
			"<a onClick=\"lessionId("+chld+",\'parent\',0)\">"
									+ childNodesName.get(chld).split(",")[0] + "</a>");
                                 
							comboBoxData.append("<option value="+chld+">" +childNodesName.get(chld) + "</option>");
							comboBox.put(new JSONObject().put("id",chld+"parent").put("text", childNodesName.get(chld).split(",")[0]));
							
							subChilds = chld;
							getChilds(chld);
						} else {
							String notes = childNodesName.get(chld).split(",")[3].equalsIgnoreCase("NO") ? "NONOTES"
									: "YES";
                
							finalData.append(" <li class=\"" + notes + "  "
									+ childNodesName.get(chld).split(",")[1] + "  "
									+ childNodesName.get(chld).split(",")[2] + "  id"+chld+"child\"> ");
							
							finalData
									.append("<section class=\"col-xs-10\"> <a onClick=\"lessionId("+chld+",\'child\',"+index+")\">"
											+ childNodesName.get(chld).split(",")[0]
											+ "</a>");
							jsonObject.put("" + index++, chld);
							comboBoxData.append("<option value="+chld+">" +childNodesName.get(chld).split(",")[0] + "</option>");
							
							comboBox.put(new JSONObject().put("id",chld+"child").put("text", childNodesName.get(chld).split(",")[0]));
							if(!childNodesName.get(chld).split(",")[3].equalsIgnoreCase("NO"))
							{
								finalData.append("<span><i class=\"fa paddy text-success fa-pencil-square-o\"></i></span>");
							}
							if(childNodesName.get(chld).split(",")[1].trim().equalsIgnoreCase("MARKED")){
								finalData.append("<span><i class=\"fa text-success fa-bookmark-o paddy\"></i></span>");
							}
							if(!childNodesName.get(chld).split(",")[4].trim().equalsIgnoreCase("Not Attempt")){
								finalData.append("<span class=\"paddy\"> <span class=\"badge bg-success\">"+childNodesName.get(chld).split(",")[4].trim()+"</span></span>");
							}
							finalData.append("</section></li>");
							
						}
					}
					finalData.append("</ul>");

				} else {

					return subChilds = "";
				}
			} else {
				continue;
			}
		}
		// hieraricy.put("</li>");

		finalData.append("</li>");
		return subChilds;
		

	}

	
	
	
	
	
	
	
	
	
	



}

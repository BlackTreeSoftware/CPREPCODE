package com.greenbuds.crunchprep.dao.contentcreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.List;

import org.apache.log4j.Logger;
 
import com.greenbuds.crunchprep.action.contentcreator.LessonsMaster;
import com.greenbuds.crunchprep.bo.contentcreator.LessonsBo;
import com.greenbuds.crunchprep.bo.contentcreator.SubLessonBO;
import com.greenbuds.crunchprep.controller.contentcreator.LessonController;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.Exceptions;
import com.greenbuds.crunchprep.util.DBUtil;
import com.greenbuds.crunchprep.dao.common.CommonsDAO;

public class LessonsDAO implements ILessonsDAO{
	/** The Constant LOGGER. */
	private final static Logger LOGGER = Logger.getLogger( LessonsDAO.class );
 
	 private CommonsDAO commonsDAO;
 
	/** The LessonsDAO */
	private static LessonsDAO lessonsDao;
	private LessonsDAO(){
		
	}
	/**
	 * Gets the single instance of LessonsDAO.
	 *
	 * @return single instance of LessonsDAO.
	 */
	public static synchronized LessonsDAO getInstance(){
		if(lessonsDao==null)
			lessonsDao=new LessonsDAO();
		return lessonsDao;
	}
	 
 
	@Override
	public HashMap<Integer, String> getLessonBasedonSections(int sectionid)
			throws CPException {
	 
		Statement st=null;
		ResultSet rs=null;
			Connection connection=null;
			HashMap<Integer, String> sectionBasedLessons=new HashMap<Integer, String>();
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				rs=st.executeQuery("select lesson_id,lesson_name from cp_lessons_masters where section_id like '"+sectionid+"'");
				while(rs.next())
				{ 
					sectionBasedLessons.put(rs.getInt("lesson_id"), rs.getString("lesson_name"));   
				} 
			}
			catch(ConnectionException exp){ 
				throw new ConnectionException(exp); 
			}
			catch(Exception exp)
			{
				throw new CommonExceptions(exp);
			}
			finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CPException(e);
				}
			}
			return sectionBasedLessons;
	}

	@Override
	public List<SubLessonBO> getLessonStructure() throws CPException {
		// TODO Auto-generated method stub
		Statement st=null;
		ResultSet rs=null;
			Connection connection=null;
			List<SubLessonBO> lessonStructure=new ArrayList<SubLessonBO>();
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				rs=st.executeQuery("select id,parent_id,child_id from cp_lesson_hierarchy order by parent_id");
				while(rs.next())
				{ 
					SubLessonBO subLessonBO=new SubLessonBO();
					subLessonBO.setParent_id(rs.getInt("parent_id"));
					subLessonBO.setChild_id(rs.getInt("child_id"));
					subLessonBO.setId(rs.getInt("id"));
					lessonStructure.add(subLessonBO);
				} 
			}
			catch(ConnectionException exp){ 
				throw new ConnectionException(exp); 
			}
			catch(Exception exp)
			{
				throw new CommonExceptions(exp);
			}
			finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CPException(e);
				}
			}
			return lessonStructure;
	}

	@Override
	public List<SubLessonBO> getSubLessons(int lessonid) throws CPException {
		// TODO Auto-generated method stub
		Statement st=null;
		ResultSet rs=null;
			Connection connection=null;
			List<SubLessonBO> sublessons=new ArrayList<SubLessonBO>();
			String lesson_id="";
			if(lessonid==-1)
			{
				lesson_id="%";
			}
			else
			{
				lesson_id=""+lessonid;
			}
				
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				//rs=st.executeQuery("select l.lesson_id,l.lesson_name,l.sublesson_id,s.section_id as sectionid,s.section_name as sectionname,l.skill_id1,l.skill_id2,l.skill_id3,l1.skill_name as skill1,l2.skill_name as skill2,l3.skill_name as skill3,l.lesson_text,l.lesson_video,l.avg_time,l.access_type,l.status from cp_lessons l,cp_lessons_masters m,cp_skill_level l1,cp_skill_level l2,cp_skill_level l3,cp_sections s where m.lesson_id=l.lesson_id and m.section_id=s.section_id and l1.skill_id=l.skill_id1 and l2.skill_id=l.skill_id2 and l3.skill_id=l.skill_id3 and l.lesson_id like '"+lesson_id+"'"); 
				rs=st.executeQuery("select les.lesson_id,les.lesson_name,les.sublesson_id,mas.section_id as sectionid," +
						"s.section_name as sectionname,mas.lesson_name as main,(select sk.skill_name from cp_skill_level sk where sk.skill_id=les.skill_id1) skill1," +
						"(select sk.skill_name from cp_skill_level sk where sk.skill_id=les.skill_id2) skill2," +
						"(select sk.skill_name from cp_skill_level sk where sk.skill_id=les.skill_id3) skill3," +
						"(select les.skill_id1 from cp_skill_level sk where sk.skill_id=les.skill_id1) skill_id1," +
						"(select les.skill_id2 from cp_skill_level sk where sk.skill_id=les.skill_id2) skill_id2," +
						"(select les.skill_id3 from cp_skill_level sk where sk.skill_id=les.skill_id3) skill_id3," +
						"les.lesson_text,les.lesson_video,les.avg_time,les.access_type,les.status from cp_lessons les," +
						"cp_lessons_masters mas,cp_sections s where mas.lesson_id=les.lesson_id and s.section_id=mas.section_id and " +
						"les.lesson_id like '"+lesson_id+"'"); 
				while(rs.next())
				{ 
					String skill1="";String skill2="";String skill3="";
					int skill_id1=0;int skill_id2=0;int skill_id3=0;
					SubLessonBO subLessonBO=new SubLessonBO();
					subLessonBO.setLessonId(rs.getInt("lesson_id"));
					subLessonBO.setSub_lessonname(rs.getString("lesson_name"));
					subLessonBO.setSublesson_id(rs.getInt("sublesson_id"));
					subLessonBO.setSectionId(rs.getInt("sectionid"));
					subLessonBO.setSectionName(rs.getString("sectionname"));  
					subLessonBO.setMain_lesson_name(rs.getString("lesson_name")+"("+rs.getString("main")+")");
					if(rs.getString("skill_id1")==null)
					{
						      skill_id1=0;
					}
					else
					{
						skill_id1=Integer.parseInt(rs.getString("skill_id1"));
					}
					if(rs.getString("skill_id2")==null)
					{
						      skill_id2=0;
					}
					else
					{
						skill_id2=Integer.parseInt(rs.getString("skill_id2"));
					}
					if(rs.getString("skill_id3")==null)
					{
						      skill_id3=0;
					}
					else
					{
						skill_id3=Integer.parseInt(rs.getString("skill_id3"));
					}
					if(rs.getString("skill1")==null)
					{
						skill1="";
					}
					else
					{
						skill1=rs.getString("skill1");
					}
					if(rs.getString("skill2")==null)
					{
						skill2="";
					}
					else
					{
						skill2=rs.getString("skill2");
					}
					if(rs.getString("skill3")==null)
					{
						skill3="";
					}
					else
					{
						skill3=rs.getString("skill3");
					}
					subLessonBO.setSkillname1(skill1);
					subLessonBO.setSkillname2(skill2);
					subLessonBO.setSkillname3(skill3);
					subLessonBO.setSkillid1(skill_id1); 
					subLessonBO.setSkillid2(skill_id2); 
					subLessonBO.setSkillid3(skill_id3);
					String t="",u="",v="";
				    String k=rs.getString("lesson_text").toString(); 
				     if(k.contains("@@"))
						{
							t=k.replace("@@", "#");
						}
						else
						{
							t=k;
						}
				     if(t.contains("**"))
						{
							u=t.replace("**", "&");
						}
						else
						{
							u=t;
						} 
				   if(u.contains("\\"))
				   {
					   //System.out.println("Text contains:"+u);
					   v=t.replace("\\", "\\\\");
				   }
				   else
				   {
					   v=u;
				   }
				   
				    subLessonBO.setLesson_text(v); 
					subLessonBO.setLesson_video(rs.getString("lesson_video"));
					subLessonBO.setAccess_type(rs.getString("access_type"));
					subLessonBO.setStatus(rs.getString("status"));
					sublessons.add(subLessonBO);
				} 
			}
			catch(ConnectionException exp){ 
				throw new ConnectionException(exp); 
			}
			catch(Exception exp)
			{
				throw new CommonExceptions(exp);
			}
			finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CPException(e);
				}
			}
			return sublessons;
	}

	/**
	 * Adding new Lesson
	 * @return "success"/"error"/"exist"
	 * @throws ConnectioException
	 */
	
	@Override
	public String saveNewLesson(LessonsBo bo) throws ConnectionException {
		 Connection connection=null;
		 Statement statement=null;
		String result="error";
		 
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				try {
					statement=connection.createStatement();
					String sql="INSERT INTO `cp_lessons_masters` (`section_id`, `lesson_name`) VALUES ("+bo.getSectionId()+", '"+bo.getLessonName()+"')";
					
					int b=statement.executeUpdate(sql);
					//System.out.println("QUery : "+sql+"\t b value : "+b);
					  if(b==1)
						  result="success";
					   
				} catch (SQLException e) {
					
					 if(e.getMessage().contains("Lesson Name Already Existed"))
					 {
						 
						 result="exist";
					 }					 
					 else
					 {
						 e.printStackTrace();
					 }
					 
				} 
				finally
				{
					DBUtil.closeConnection(statement, connection);
				}
		 
		return result;
	}

	/**
	 * Updating Existed Lesson
	 * @return "true"/"false"
	 * @throws ConnectioException
	 */
	@Override
	public boolean editLesson(LessonsBo bo) throws ConnectionException {
		 Connection connection=null;
		 Statement statement=null;		 
		 boolean result=false;
		 
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				try {
					statement=connection.createStatement();
					String sql="UPDATE `cp_lessons_masters` SET `section_id`="+bo.getSectionId()+" , `lesson_name`='"+bo.getLessonName()+"' WHERE  `lesson_id`="+bo.getLessonId();
					//System.out.println("Edit LEsson Query : "+sql);
					int b=statement.executeUpdate(sql);
					  if(b==1)result=true;
					  else
						  result=false;
				} catch (SQLException e) {
					 new RuntimeException(Exceptions.SQL_EXCEPTION.getExceptionType());
					 
				} 
				finally
				{
					DBUtil.closeConnection(statement, connection);
				}
		 
		return result;
	}
	/**
	 * Deleting number of Lessons
	 * @return  "true"/"false"
	 * @throws ConnectioException
	 */
	@Override
	public boolean deleteLesson(String lessonid) throws ConnectionException {
		 Connection connection=null;
		 Statement statement=null;
		 boolean result=false;
		 
		 String str[]=lessonid.split(",");
		// System.out.println("Str[]"+str);
		 if(lessonid.length()==1)
		 {
			 str[0]=lessonid;
		 }
		 
		 
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				try {
					statement=connection.createStatement();
					for(int i=0;i<str.length;i++)
					{
						String sql="DELETE FROM `cp_lessons_masters` WHERE  `lesson_id`="+Integer.parseInt(str[i])+"";
						//System.out.println("Delete Query : "+sql);
						statement.addBatch(sql);
					}
					
					int b[]=statement.executeBatch();
					//System.out.println("blenth : "+b.length+"\t str lenght : "+str.length);
					  if(b.length==str.length)
					  {
						  result=true;
					  }
					  else
						  result=false;
				} catch (SQLException e) {
					result=false;
					 new RuntimeException(Exceptions.SQL_EXCEPTION.getExceptionType());
					 
				} 
				finally
				{
					DBUtil.closeConnection(statement, connection);
				}
		 
		return result;
	}
	/**
	 * get all the lessons Data
	 * @return  list of lessons
	 * @throws ConnectioException
	 */
	@Override
	public List<LessonsBo> getLessonsData() throws ConnectionException {
		Connection connection=null;
		 Statement statement=null;
		 ResultSet resultSet=null;
		 List<LessonsBo> result=new ArrayList<LessonsBo>();
		 
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				try {
					statement=connection.createStatement();
					String sql="select clm.lesson_id,clm.section_id,clm.lesson_name,cs.section_name from cp_lessons_masters clm,cp_sections cs where cs.section_id=clm.section_id";
				    resultSet=statement.executeQuery(sql);
				    while(resultSet.next())
				    {
				    	LessonsBo bo=new LessonsBo();
				    	bo.setLessonId(resultSet.getInt(1));
				    	bo.setSectionId(resultSet.getInt(2));
				    	bo.setLessonName(resultSet.getString(3));
				    	bo.setSectionName(resultSet.getString(4));
				    	result.add(bo);
				    	
				    }
					   
				} catch (SQLException e) {
					 new RuntimeException(Exceptions.SQL_EXCEPTION.getExceptionType());
					 
				} 
				finally
				{
					DBUtil.closeConnection(resultSet,statement, connection);
				}
		 
		return result;
	}

	@Override
	public boolean saveSubLessons(SubLessonBO subLessonBOs) throws CPException {
		// TODO Auto-generated method stub
		boolean result=false;
		Statement st=null;
		ResultSet rs=null;
		commonsDAO=commonsDAO.getInstance(); 
		    int key=commonsDAO.createKey("cp_lessons", "sublesson_id");
		    List<String> skillList=new ArrayList<String>();
		    if(isSameLessonExists(subLessonBOs))
		    {
		      result=false;
		    }
		    else
		    {
		    	Connection connection=null; 
				try{
					
					connection=DatabaseConnection.getCrunchPrepConnection(); 
					st=connection.createStatement(); 
					int skillsize=0;
					//int skillsize=subLessonBOs.getSkills_required().length;
					if(subLessonBOs.getSkills_required()!=null)
					{
						skillsize=subLessonBOs.getSkills_required().length;
						for(String s:subLessonBOs.getSkills_required())
						 {
							 skillList.add(s);
						 }
					}
					 
					 String query="";
					 if(skillsize==0)
					  {
						  query="INSERT INTO `cp_lessons` (`sublesson_id`, `lesson_id`, `lesson_name`,`lesson_text`,`lesson_video`,`avg_time`,`access_type`,`status`) VALUES ('"+key+"', '"+subLessonBOs.getLessonId()+"', '"+subLessonBOs.getSub_lessonname()+"', '"+subLessonBOs.getLesson_text()+"','"+subLessonBOs.getLesson_video()+"','00:00:00','"+subLessonBOs.getAccess_type()+"','"+subLessonBOs.getStatus()+"')";
					  }
					  if(skillsize==1)
					  {
						  query="INSERT INTO `cp_lessons` (`sublesson_id`, `lesson_id`, `lesson_name`, `skill_id1`, `lesson_text`,`lesson_video`,`avg_time`,`access_type`,`status`) VALUES ('"+key+"', '"+subLessonBOs.getLessonId()+"', '"+subLessonBOs.getSub_lessonname()+"','"+skillList.get(0)+"', '"+subLessonBOs.getLesson_text()+"','"+subLessonBOs.getLesson_video()+"','00:00:00','"+subLessonBOs.getAccess_type()+"','"+subLessonBOs.getStatus()+"')";
					  }
					  if(skillsize==2)
					  {
						  query="INSERT INTO `cp_lessons` (`sublesson_id`, `lesson_id`, `lesson_name`, `skill_id1`, `skill_id2`, `lesson_text`,`lesson_video`,`avg_time`,`access_type`,`status`) VALUES ('"+key+"', '"+subLessonBOs.getLessonId()+"', '"+subLessonBOs.getSub_lessonname()+"','"+skillList.get(0)+"','"+skillList.get(1)+"', '"+subLessonBOs.getLesson_text()+"','"+subLessonBOs.getLesson_video()+"','00:00:00','"+subLessonBOs.getAccess_type()+"','"+subLessonBOs.getStatus()+"')";
					  }
					  if(skillsize==3)
					  {
						  query="INSERT INTO `cp_lessons` (`sublesson_id`, `lesson_id`, `lesson_name`, `skill_id1`, `skill_id2`, `skill_id3`, `lesson_text`,`lesson_video`,`avg_time`,`access_type`,`status`) VALUES ('"+key+"', '1', '"+subLessonBOs.getSub_lessonname()+"','"+skillList.get(0)+"','"+skillList.get(1)+"', '"+skillList.get(2)+"', '"+subLessonBOs.getLesson_text()+"','"+subLessonBOs.getLesson_video()+"','00:00:00','"+subLessonBOs.getAccess_type()+"','"+subLessonBOs.getStatus()+"')";
					  }
					int n=st.executeUpdate(query); 
					if(n>0)
					{
						result=true; 
					}
					connection.close();
					if(subLessonBOs.getChild_id()!=0)
					{
					saveLessonHeirarchy(subLessonBOs.getChild_id(),key);
					}
				}
				catch(ConnectionException exp){ 
					throw new ConnectionException(exp); 
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
					throw new CommonExceptions(exp); 
				}
				finally{
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						throw new CPException(e);
					}
				} 
		    }
			
		return result;
	}
	public boolean saveLessonHeirarchy(int parentid,int childid) throws CPException {
		// TODO Auto-generated method stub
		boolean result=false;
		Statement st=null;
		ResultSet rs=null;  
			Connection connection=null;  
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				st.executeUpdate("INSERT INTO `cp_lesson_hierarchy` ( `parent_id`, `child_id`) VALUES ('"+parentid+"', '"+childid+"')"); 
			}
			catch(ConnectionException exp){ 
				throw new ConnectionException(exp); 
			}
			catch(Exception exp)
			{
				throw new CommonExceptions(exp);
			}
			finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CPException(e);
				}
			} 
		return result;
	}
	public boolean isSameLessonExists(SubLessonBO m) throws CPException
	{
		boolean result=false;
		Statement st=null;
		ResultSet rs=null; 
			Connection connection=null; 
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				rs=st.executeQuery("select * from cp_lessons where lesson_name like '"+m.getSub_lessonname()+"'");  
				while(rs.next())
				{
					result=true;
				}
			}
			catch(ConnectionException exp){ 
				throw new ConnectionException(exp); 
			}
			catch(Exception exp)
			{
				throw new CommonExceptions(exp);
			}
			finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CPException(e);
				}
			} 
		return result;  
	}

	@Override
	public boolean updateSubLessons(SubLessonBO subLessonBOs)
			throws CPException {
		// TODO Auto-generated method stub
		boolean result=false;
		Statement st=null;
		ResultSet rs=null; 
		    List<String> skillList=new ArrayList<String>(); 
		    	Connection connection=null; 
				try{
					
					 connection=DatabaseConnection.getCrunchPrepConnection(); 
					 st=connection.createStatement(); 
					    int skillsize=0; 
						if(subLessonBOs.getSkills_required()!=null)
						{
							skillsize=subLessonBOs.getSkills_required().length;
							for(String s:subLessonBOs.getSkills_required())
							 {
								 skillList.add(s);
							 }
						}
					  String query="";
					  if(skillsize==0)
					  {
						  query="update cp_lessons set lesson_id='"+subLessonBOs.getLessonId()+"',lesson_name='"+subLessonBOs.getSub_lessonname()+"',skill_id1=null,skill_id2=null,skill_id3=null,lesson_text='"+subLessonBOs.getLesson_text()+"',lesson_video='"+subLessonBOs.getLesson_video()+"',access_type='"+subLessonBOs.getAccess_type()+"',status='"+subLessonBOs.getStatus()+"' where sublesson_id='"+subLessonBOs.getSublesson_id()+"'";
					  }
					  if(skillsize==1)
					  {
						  query="update cp_lessons set lesson_id='"+subLessonBOs.getLessonId()+"',lesson_name='"+subLessonBOs.getSub_lessonname()+"',skill_id1='"+skillList.get(0)+"',skill_id2=null,skill_id3=null,lesson_text='"+subLessonBOs.getLesson_text()+"',lesson_video='"+subLessonBOs.getLesson_video()+"',access_type='"+subLessonBOs.getAccess_type()+"',status='"+subLessonBOs.getStatus()+"' where sublesson_id='"+subLessonBOs.getSublesson_id()+"'";
					  }
					  if(skillsize==2)
					  {
						  query="update cp_lessons set lesson_id='"+subLessonBOs.getLessonId()+"',lesson_name='"+subLessonBOs.getSub_lessonname()+"',skill_id1='"+skillList.get(0)+"',skill_id2='"+skillList.get(1)+"',skill_id3=null,lesson_text='"+subLessonBOs.getLesson_text()+"',lesson_video='"+subLessonBOs.getLesson_video()+"',access_type='"+subLessonBOs.getAccess_type()+"',status='"+subLessonBOs.getStatus()+"' where sublesson_id='"+subLessonBOs.getSublesson_id()+"'";
					  }
					  if(skillsize==3)
					  {
						  query="update cp_lessons set lesson_id='"+subLessonBOs.getLessonId()+"',lesson_name='"+subLessonBOs.getSub_lessonname()+"',skill_id1='"+skillList.get(0)+"',skill_id2='"+skillList.get(1)+"',skill_id3='"+skillList.get(2)+"',lesson_text='"+subLessonBOs.getLesson_text()+"',lesson_video='"+subLessonBOs.getLesson_video()+"',access_type='"+subLessonBOs.getAccess_type()+"',status='"+subLessonBOs.getStatus()+"' where sublesson_id='"+subLessonBOs.getSublesson_id()+"'";
					  }
				    //System.out.println(query);
				    int n=st.executeUpdate(query);
					//int n=st.executeUpdate("update cp_lessons set lesson_id='"+subLessonBOs.getLessonId()+"',lesson_name='"+subLessonBOs.getSub_lessonname()+"',skill_id1='"+skillList.get(0)+"',skill_id2='"+skillList.get(1)+"',skill_id3='"+skillList.get(2)+"',lesson_text='"+subLessonBOs.getLesson_text()+"',lesson_video='"+subLessonBOs.getLesson_video()+"',access_type='"+subLessonBOs.getAccess_type()+"',status='"+subLessonBOs.getStatus()+"' where sublesson_id='"+subLessonBOs.getSublesson_id()+"'"); 
					if(n>0)
					{
						result=true; 
					}
					connection.close(); 
					if(subLessonBOs.getParent_id()!=0)
					{
						if(isRelationExists(subLessonBOs.getParent_id(),subLessonBOs.getChild_id()))
						{
							updateLessonHeirarchy(subLessonBOs.getParent_id(),subLessonBOs.getChild_id());
						}
						else
						{
							saveLessonHeirarchy(subLessonBOs.getParent_id(), subLessonBOs.getChild_id());
						}
					}
					else
					{  
						 deleteLessonHeirarchyWithParent(subLessonBOs.getChild_id());
					} 
					 
				}
				catch(ConnectionException exp){ 
					throw new ConnectionException(exp); 
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
					throw new CommonExceptions(exp);
					
				}
				finally{
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						throw new CPException(e);
					}
				} 
		    
			
		return result;
	}
	public boolean updateLessonHeirarchy(int parentid,int childid) throws CPException {
		// TODO Auto-generated method stub
		boolean result=false;
		Statement st=null;
		ResultSet rs=null;  
			Connection connection=null;  
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				st.executeUpdate("update cp_lesson_hierarchy set  parent_id='"+parentid+"' where child_id='"+childid+"'"); 
				result=true;
		    	}
			catch(ConnectionException exp){ 
				throw new ConnectionException(exp); 
			}
			catch(Exception exp)
			{
				throw new CommonExceptions(exp);
			}
			finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CPException(e);
				}
			} 
		return result;
	}

	@Override
	public boolean deleteSubLessons(String[] records) throws CPException {
		// TODO Auto-generated method stub
		boolean result=false,test=false;
		int  k=0;
		Statement st=null;
		ResultSet rs=null;  
			Connection connection=null; 
			int  i=0,id=0; 
			 int  child_id=0; 
			 for(String s:records)
			 {
			   k=Integer.parseInt(s);
			 }
			 for(String s:records)
			 {
				 System.out.println("Records:"+k);
				 for(SubLessonBO l:this.getLessonStructure())
				 {   
					 //System.out.println("parents:"+l.getParent_id()+"\tchilds:"+l.getChild_id());
					 if(l.getParent_id()==Integer.parseInt(s))
					 {
						 child_id=l.getChild_id();
						 test=true;
						 id=l.getId();
						 break; 
					 }
					 else if(l.getChild_id()==k) 
					 { 
						 id=l.getId();
					 }
				 }
			 }
			 
			 if(test)
			 {
			   //  System.out.println("Childs found"); 
				   result=false;
			 }
			 else
			 {
				 try{ 
					    String query=""; 
						connection=DatabaseConnection.getCrunchPrepConnection();
						st=connection.createStatement();
						 
						System.out.println("Herarchy deleted"+"delete from cp_lesson_hierarchy where id like "+id+"");
						if(id==0)
						{
							int j=0;
							  for(String s:records)
							 {
								 j++;
								 query="delete from cp_lessons where sublesson_id like '"+s+"'";
								 st.addBatch(query);
								 if(j==20)
								 {
									 st.executeBatch();
								 } 
							 } 
							  try
							  {
							  st.executeBatch(); 
							  result=true;
							  }
							  catch(Exception e)
							  {
								  result=false;
								  e.printStackTrace();
							  }
						}
						else
						{
							int n=st.executeUpdate("delete from cp_lesson_hierarchy where id like "+id+"");  
							if(n>0)
							{ 
								int j=0;
								  for(String s:records)
								 {
									 j++;
									 query="delete from cp_lessons where sublesson_id like '"+s+"'";
									 st.addBatch(query);
									 if(j==20)
									 {
										 st.executeBatch();
									 } 
								 } 
								  try{
									st.executeBatch();
									result=true;
								  }
								  catch(Exception e)
								  {
									  result=false;
									 // e.printStackTrace();
								  }
								
							}
						} 
							  connection.close();   
					}
					catch(ConnectionException exp){ 
						result=false; 
						exp.printStackTrace();
						throw new ConnectionException(exp);  
					}
					catch(Exception exp)
					{ 
						result=false; 
						exp.printStackTrace();
						throw new CommonExceptions(exp);
					}
					finally{
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							throw new CPException(e);
						}
					} 
			 } 
		return result;
	}
	public boolean deleteLessonHeirarchy(int id) throws CPException
	{
		boolean result=false;
		Statement st=null;
		ResultSet rs=null; 
			Connection connection=null; 
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				System.out.println("Herarchy deleted"+"delete from cp_lesson_hierarchy where id like "+id+"");
				int n=st.executeUpdate("delete from cp_lesson_hierarchy where id like "+id+"");  
				if(n>0)
				{
					result=true; 
				}
			}
			catch(ConnectionException exp){ 
				result=false;
				throw new ConnectionException(exp);  
			}
			catch(Exception exp)
			{
				result=false;
				throw new CommonExceptions(exp); 
			}
			finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					result=false;
					throw new CPException(e);
				}
			} 
		return result;  
	}
	@Override
	public List<SubLessonBO> getMainLessons() throws CPException {
		// TODO Auto-generated method stub
		Statement st=null;
		ResultSet rs=null;
			Connection connection=null;
			List<SubLessonBO> mainLessons=new ArrayList<SubLessonBO>();
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				rs=st.executeQuery("select m.lesson_id,m.lesson_name from cp_lessons_masters m");
				while(rs.next())
				{ 
					SubLessonBO subLessonBO=new SubLessonBO(); 
					subLessonBO.setLessonId(rs.getInt("lesson_id"));
					subLessonBO.setMain_lesson_name(rs.getString("lesson_name"));
					mainLessons.add(subLessonBO);
				} 
			}
			catch(ConnectionException exp){ 
				throw new ConnectionException(exp); 
			}
			catch(Exception exp)
			{
				throw new CommonExceptions(exp);
			}
			finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CPException(e);
				}
			}
			return mainLessons;
	}
	public boolean isRelationExists(int parent_id,int child_id)throws CPException
	{
		boolean result=false; 
		Statement st=null;
		ResultSet rs=null; 
			Connection connection=null; 
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				//rs=st.executeQuery("select * from cp_lesson_hierarchy where  parent_id like '"+parent_id+"' and child_id like '"+child_id+"'");  
				rs=st.executeQuery("select * from cp_lesson_hierarchy where child_id like '"+child_id+"'");
				while(rs.next())
				{
					result=true;
				}
			}
			catch(ConnectionException exp){ 
				throw new ConnectionException(exp); 
			}
			catch(Exception exp)
			{
				throw new CommonExceptions(exp);
			}
			finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CPException(e);
				}
			} 
		return result;   
	}
	public boolean deleteLessonHeirarchyWithParent(int id) throws CPException
	{
		boolean result=false;
		Statement st=null;
		ResultSet rs=null; 
			Connection connection=null; 
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				int n=st.executeUpdate("delete from cp_lesson_hierarchy where child_id like "+id+"");  
				if(n>0)
				{
					result=true;
				}
			}
			catch(ConnectionException exp){ 
				throw new ConnectionException(exp); 
			}
			catch(Exception exp)
			{
				throw new CommonExceptions(exp);
			}
			finally{
				try {
					connection.close();
				} catch (SQLException e) {					 
					throw new CPException(e);
				}
			} 
		return result;  
	}
}

/**
 * 
 */
package com.greenbuds.crunchprep.util;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.TreeSet;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.greenbuds.crunchprep.bo.lessons.LessonsHierarchy;

import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.setups.Section;

/**
 * @author rrajulapati
 * 
 */
public class UtilsUsage {

	/**
	 * @param args
	 * @throws JSONException
	 */
	public static void mm() throws JSONException {
		main("10185".split(","));
	}

	private static ArrayList<LessonsHierarchy> childNodes;
	private static java.util.Map<String, String> childNodesName;
	private static ArrayList<LessonsHierarchy> parentNodes;
	private static ArrayList<String> childNode;
	private static JSONObject jsonObject;
	private static JSONObject destination;
	private static StringBuffer finalData;
	private static StringBuffer comboBoxData;
	private static int index = 1;
	private static JSONArray comboBox; 
	

	public static void main(String[] args) throws JSONException {
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData metaData = null;
		String userId = args[0];
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
		
		
		System.out.println("\n\n Quiree is:\n "+quireeForParents+"\n Quiree for CHilds "+quireeForChilds);

		LessonsHierarchy node = null;

		try {
			// finalData.append(" Quiree is : "+quiree);
			connection = DatabaseConnection.getCrunchPrepConnection();
			
			childNodes = new ArrayList<LessonsHierarchy>();
			childNode = new ArrayList<String>();
			childNodesName = new HashMap<String, String>();
			parentNodes = new ArrayList<LessonsHierarchy>();
			destination=new JSONObject();
			
			

			getNodesList(quireeForParents,connection,true);
			getNodesList(quireeForChilds,connection,false);
			
			destination.put(Section.VERBAL.getSectionName(),getParent(Section.VERBAL));
			destination.put(Section.QUANTITATIVE.getSectionName(),getParent(Section.QUANTITATIVE));
			System.out.println("\n\n "+destination.toString());
			// finalData.append(" Parent Details<ul>");
		/*	finalData.delete(0,finalData.capacity());
			finalData.append("<ul>");
			for (LessonsHierarchy parent : parentNodes) {

				if (parentLessionStart.add(parent.getMainLessonId())) {
					if (parentLessionStart.size() != 1) {
						finalData.append("</ul></li>");

					}
					finalData.append("<li><a onClick=\"lessionId("++")\"> " +parent.getMainLessonName() + "</a><ul>");
					
				}

				jsonObject.put("" + index++, parent.getSubLessonId());

				if (!parent.getChilds()[0].equalsIgnoreCase("0")) {
					finalData.append(" <li> <a onClick=\"lessionId("++")\">" + parent.getSubLessonName()
							+ "</a>");
					finalData.append("<ul>");
					for (String child : parent.getChilds()) {
						if (childNode.contains(child)) {
							finalData.append(" <li><a onClick=\"lessionId("++")\">"
									+ childNodesName.get(child) + "</a>");
						}
						// hieraricy.put("<li><a onClick=\"lessionId("++")\">"+child+"</a>");
						jsonObject.put("" + index++, child);
						if (childNode.contains(child)) {

							getChilds(child);

						} else {
							String notes = parent.getNotes().equalsIgnoreCase("NO") ? "NONOTES"
									: "YES";

							finalData.append(" <li class=\"" + notes + "  "
									+ parent.getBookMark() + "  "
									+ parent.getConfidence() + "  id"+parent.getSubLessonId()+"\"> ");
							finalData
									.append("<section class=\"col-xs-10\">   <a onClick=\"lessionId("++")\">"
											+ childNodesName.get(child)
											+ "</a> ");
							if(!parent.getNotes().equalsIgnoreCase("NO"))
							{
								finalData.append("<span><i class=\"fa paddy text-success fa-pencil-square-o\"></i></span>");
							}
							if(parent.getBookMark().trim().equalsIgnoreCase("MARKED")){
								finalData.append("<span><i class=\"fa text-success fa-bookmark-o paddy\"></i></span>");
							}
							
							finalData.append("</section></li>");

						}
					}

					finalData.append("</ul></li>");
				} else {
					
					String notes = parent.getNotes().equalsIgnoreCase("NO") ? "NONOTES"
							: "YES";

					finalData.append(" <li class=\"" + notes + "  "
							+ parent.getBookMark() + "  "
							+ parent.getConfidence() + "   id"+parent.getSubLessonId()+"\"> ");
					finalData
							.append(" <section class=\"col-xs-10\"> <a onClick=\"lessionId("++")\">"
									+ parent.getSubLessonName()
									+ "</a>  ");
							if(!parent.getNotes().equalsIgnoreCase("NO"))
							{
								finalData.append("<span><i class=\"fa paddy text-success fa-pencil-square-o\"></i></span>");
							}
							if(parent.getBookMark().trim().equalsIgnoreCase("MARKED")){
								finalData.append("<span><i class=\"fa text-success fa-bookmark-o paddy\"></i></span>");
							}
							
							finalData.append("</section></li>");
				}

			}
			finalData.append("</ul></li></ul>");*/

			/*System.out.println(" Tree Travellese " + jsonObject.toString()
					+ "\n\n\t Hierarichy " + finalData.toString());*/

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
private static void getNodesList(String quiree,Connection connection,boolean isParentNodesData) throws SQLException
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
	private static JSONObject getParent(Section section) throws JSONException{
		index=1;
		JSONObject targetData=new JSONObject();
		jsonObject = new JSONObject();
		comboBox=new JSONArray();
		TreeSet<String> parentLessionStart = new TreeSet<String>();
		finalData = new StringBuffer("<ul>");
		comboBoxData=new StringBuffer("<option value=-1>Select</option>");
		
		for (LessonsHierarchy parent : parentNodes) {
			
           if(parent.getSectionId().equalsIgnoreCase(""+section.getSectionId())){
			
        	   if (parentLessionStart.add(parent.getMainLessonId())) {
				if (parentLessionStart.size() != 1) {
					finalData.append("</li>");

				}
				
				
				finalData.append("<li><a onClick=\"lessionId("+parent.getMainLessonId()+",\'parent\',0)\"> " +parent.getMainLessonName() + "</a>");
				comboBoxData.append("<option value="+parent.getMainLessonId()+">" +parent.getMainLessonName() + "</option>");
				comboBox.put(new JSONObject().put("id",parent.getMainLessonId()+"parent").put("text", parent.getMainLessonName()));
			}

			

			if (!parent.getChilds()[0].equalsIgnoreCase("0")) {
				finalData.append("<ul> <li> <a onClick=\"lessionId("+parent.getSubLessonId()+",\'parent\',0)\">" + parent.getSubLessonName()
						+ "</a><ul>");
				
				for (String child : parent.getChilds()) {
					if (childNode.contains(child)) {
						finalData.append(" <li><a onClick=\"lessionId("+child+",\'parent\',0)\">"
								+ childNodesName.get(child).split(",")[0] + "</a>");
						comboBoxData.append("<option value="+child+">" +childNodesName.get(child).split(",")[0] + "</option>");
						comboBox.put(new JSONObject().put("id",child+"parent").put("text", childNodesName.get(child).split(",")[0]));
						
					}
					// hieraricy.put("<li><a onClick=\"lessionId("++")\">"+child+"</a>");
					
					if (childNode.contains(child)) {

						getChilds(child);

					} else {
						String notes = parent.getNotes().equalsIgnoreCase("NO") ? "NONOTES"
								: "YES";

						finalData.append(" <li class=\"" + notes + "  "
								+ parent.getBookMark() + "  "
								+ parent.getConfidence() + "  id"+child+"\"> ");
						finalData
								.append("<section class=\"col-xs-10\">   <a onClick=\"lessionId("+child+",\'child\',"+index+")\">"
										+ childNodesName.get(child).split(",")[0]
										+ "</a> ");
						comboBoxData.append("<option value="+child+">" +childNodesName.get(child).split(",")[0] + "</option>");
						comboBox.put(new JSONObject().put("id",child+"child").put("text", childNodesName.get(child)));
						jsonObject.put("" + index++, child);
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
						
						finalData.append("</section></li>");

					}
				}

				finalData.append("</ul></li></ul>");
			} else {
				comboBoxData.append("<option value="+parent.getSubLessonId()+">" +parent.getSubLessonName() + "</option>");
				comboBox.put(new JSONObject().put("id",parent.getSubLessonId()+"child").put("text", parent.getSubLessonName()));
				
				String notes = parent.getNotes().equalsIgnoreCase("NO") ? "NONOTES"
						: "YES";

				finalData.append("<ul> <li class=\"" + notes + "  "
						+ parent.getBookMark() + "  "
						+ parent.getConfidence() + "   id"+parent.getSubLessonId()+"\"> ");
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
		
		targetData.put(section.getSectionName()+"keySet", jsonObject);
		targetData.put(section.getSectionName()+"Hierarchy",finalData.toString());
		targetData.put(section.getSectionName()+"ComboBox", comboBox.toString());
		return targetData;
		
	}
	private static String getChilds(String findChild) throws JSONException {
		String subChilds = "";

		for (LessonsHierarchy childs : childNodes) {

			if (childs.getSubLessonId().equalsIgnoreCase(findChild)) {
				if (!childs.getChilds()[0].equalsIgnoreCase("0")) {

					finalData.append("<ul>");
					for (String chld : childs.getChilds()) {
						// if(subChilds.isEmpty())
						// subChilds="Sub Child :"+chld;
						if (childNode.contains(chld)) {
							finalData.append("<li><a onClick=\"lessionId("+chld+",\'parent\',0)\">"
									+ childNodesName.get(chld).split(",")[0] + "</a>");
                                 
							comboBoxData.append("<option value="+chld+">" +childNodesName.get(chld) + "</option>");
							comboBox.put(new JSONObject().put("id",chld+"parent").put("text", childNodesName.get(chld)));
							subChilds = chld;
							getChilds(chld);
						} else {
							String notes = childNodesName.get(chld).split(",")[3].equalsIgnoreCase("NO") ? "NONOTES"
									: "YES";
                
							finalData.append(" <li class=\"" + notes + "  "
									+ childNodesName.get(chld).split(",")[1] + "  "
									+ childNodesName.get(chld).split(",")[2] + "  id"+chld+"\"> ");
							
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

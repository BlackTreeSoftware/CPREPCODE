/***************************************************************************
 * Copyright (c) 2014 , rrajulapati , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.action.questions;

import java.beans.DesignMode;
import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.common.DifficultyBO;
import com.greenbuds.crunchprep.bo.common.SkillBO;
import com.greenbuds.crunchprep.bo.common.TestsBO;
import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.controller.common.ICommonsController;
import com.greenbuds.crunchprep.controller.contentcreator.ILessonController;
import com.greenbuds.crunchprep.controller.contentcreator.IQuestionUploadController;
import com.greenbuds.crunchprep.controller.contentcreator.QuestionUploadController;
import com.greenbuds.crunchprep.controller.lessons.ILessonsController;
import com.greenbuds.crunchprep.controller.practicesession.IPracticeSessionController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.Categories;
import com.greenbuds.crunchprep.setups.QuestionType;

import com.greenbuds.crunchprep.util.QuestionTypeUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author rrajulapati
 *
 */

public class QuestionAction extends ActionSupport implements SessionAware,
		ServletRequestAware, ServletResponseAware, RequestAware {

	@Autowired
	IQuestionUploadController questionUploadController;
	@Autowired
	private ICommonsController commonsController;
	@Autowired
	private ILessonsController lessonsController;

	@Autowired
	private IPracticeSessionController practiceSessionController;
	
	private Map<String, Object> sessionMap;
	private Map<Integer, String> lessons;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private Map<String, Object> requestMap;
	private String requestedPage;
	private QuestionsUploadBO uploadBO;
	private QuestionUploadController controller;
	private String jsonString;

	public String getQuestionsCustomizationHieraricy() {
		CPException exception = null;
		JSONObject destination = new JSONObject();
		JSONObject percentageCount=new JSONObject();
		try {

			LoginUserBO bo = (LoginUserBO) sessionMap.get("user");
			destination.put("userid", bo.getUser_id());
			lessonsController.lessonsHierarchyForCustomization(destination);
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("\n\n Final Data is; "+destination.toString());
		requestMap.put("tableData", destination.toString());

			     
		//System.out.println("\n\n Final Data is; "+destination.toString());
		try {
			getPercentageCountforCategories();
		} catch (DBException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		requestMap.put("tableData",destination.toString() );

		
		return SUCCESS;
	}

	public void getQuestionsCount() throws JSONException, IOException {
		// System.out.println("\n In Questions Count ");

		CPException exception = null;
		JSONObject jsonObject = new JSONObject();
	try {
			jsonObject.put("inputQuiree", checkData());
			questionUploadController.requiredTableDataInJSONFormate(jsonObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			exception = e;
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			exception = e;
			e.printStackTrace();
		}
		jsonObject.remove("inputQuiree");

		jsonObject.put("exceptionRaised", (exception != null));
		if (exception != null)
			jsonObject.put("exceptionIs", exception.getMessage());
	        jsonObject.put("exceptionRaised",(exception!=null));
 		if(exception!=null) jsonObject.put("exceptionIs",exception.getMessage());
 		try{
 		System.out.println("\n\n Row Count is :  Json Data is: "+jsonObject.toString()+"\n----Row COunt is; "+jsonObject.getInt("rowCount"));
 		}catch(Exception ex)
 		{
 			System.err.println(" error is; "+ex.getMessage());
 			ex.printStackTrace();
 		}
 		
 		
 		response.getWriter().write("yesData<strong  class=text-success id=questionCountNumber>"+jsonObject.getInt("rowCount")+"</strong>");
 		
 		
	}

	private String checkData() {

		LoginUserBO userBO = (LoginUserBO) sessionMap.get("user");

		int userId = userBO.getUser_id();
		String difficulty = request.getParameter("difficulty");
		String reuseMode = request.getParameter("reuseMode");
		String parents = request.getParameter("parents");
		String childs = request.getParameter("childs");
		String catigories = request.getParameter("catigories");			 
		String types = request.getParameter("types");
		String timelimitforcustomiza = request.getParameter("timeLimitFromCustomization");
		String questionslimit = request.getParameter("questionslimit");		
		System.out.println("Time limit for customization---------------"+timelimitforcustomiza+"questions limit"+questionslimit);
		if( types.contains("8")||types.contains("9")||types.contains("10")||types.contains("13"))
		{
			System.out.println("The values areeeeeeeeeeeeee");
			if(!catigories.isEmpty())
			catigories+=",6";
			else
				catigories+="6";
		}
		String section = request.getParameter("section");

		String answered = request.getParameter("ANSWERED");
		String flag = request.getParameter("FLAG");
		String guessed = request.getParameter("GUESSED");
		
		int time = 0;
				try{
					System.out.println("The time mode is"+request.getParameter("TIMEMODE"));
				time=Integer.parseInt(request.getParameter("TIMEMODE"));
				}catch(NumberFormatException ex)
				{
					ex.printStackTrace();
				}
				
		String comprehenssion = request.getParameter("comprehenssion");
		String quiree = "select qls.*,qmas.*,qs.*,le.* from cp_questions qs left join cp_question_lessons qls on qs.question_id= qls.question_id ,"
				+ " (cp_test_results trs right join  cp_question_masters qmas on qmas.question_id=trs.question_id and "
				+ " trs.userid="
				+ userId
				+ ")left join cp_reading_comprehension_passage rpas  on rpas.passage_id=qmas.passage_id  ,"
				+ " cp_difficulty_level le "
				+ " WHERE qmas.test_id like '1'and qs.question_id=qmas.question_id and le.diff_id=qs.diff_id and qmas.section_id = "
				+ section;
		String tail = "  group by qs.question_id ";
		if(!questionslimit.equalsIgnoreCase("0"))
		{
			tail+= "Limit  "+questionslimit;
		}
		
		
		
		  System.out.println("\n Difficulty : "+difficulty+" \n reuse Mode is: "
		  +reuseMode+"\n Parents Is; "+parents+" childs is; "+childs+
		  " categories : "+catigories+"" + "\n Types is; "+types+"\n GUessed : "+guessed+"\n Flag is: "+flag+"\n Answered : "+answered);
		  System.out.println("\n Section : "+section+"\n ");
		 if(time==0)
		 {
			 
		 }
		 else
		{
		if (time == 1) {
			quiree += " and  MINUTE(trs.user_time) <1 ";
		} else if (time == 2) {
			quiree += " and MINUTE(trs.user_time) >=1 and      MINUTE(trs.user_time) <=2 ";
		} else if (time == 3) {
			quiree += " and  MINUTE(trs.user_time) > 3  ";
		}
		}
		if (!difficulty.isEmpty()) {
			quiree += "  and le.diff_description in (" + difficulty + ")";
		}
		
		
		
		/*if (!catigories.isEmpty()) {
			quiree += " and qmas.category_id in (" + catigories +","+cat+ ")";
		}*/
		String cat="";
		if (!catigories.isEmpty()) {
			//quiree += " and qmas.type_id in  (" + types + ")";
			cat += " and qmas.type_id in(select qtype.question_type_id from cp_question_type qtype where qtype.category_id in("+catigories+") ";			  
		}
		
     if(!types.isEmpty()){
	 cat+="and qtype.question_type_id in("+types+"))";
	  quiree+=cat;
       }
     else if(types.isEmpty() && !catigories.isEmpty())
     {
    	  cat +=")"; 
    	  quiree+=cat;
     }
     
		if (!childs.isEmpty()) {
			quiree += " and qls.sublesson_id in (" + childs + ")";
		}
		if (!comprehenssion.isEmpty()) {
			quiree += " and rpas.passage_type in (" + comprehenssion + ") ";
		}
		if (!answered.isEmpty()) {
			quiree += "  AND trs.result IN (" + answered + ")";
		}

		if (!flag.isEmpty()) {
			quiree += " and trs.flagstatus ='FLAG' ";
		}
		if (!guessed.isEmpty()) {
			quiree += " and trs.guess='GUESS' ";
		}

		quiree += tail;
		 System.out.println("\n In Quiree is; \n\t "+quiree);
		return quiree;

	}

	public void getQuestionCoustomiztionData() throws JSONException,
			IOException {
		CPException exception = null;
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("inputQuiree", getQuestionsCustomiationQuiree());
			questionUploadController.requiredTableDataInJSONFormate(jsonObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			exception = e;
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			exception = e;
			e.printStackTrace();
		}
		jsonObject.remove("inputQuiree");
		jsonObject.put("exceptionRaised", (exception != null));
		if (exception != null)
			jsonObject.put("exceptionIs", exception.getMessage());
		// System.out.println("\n\n Json Data is: "+jsonObject.toString());
		response.getWriter().write(jsonObject.toString());

	}

	private String getQuestionsCustomiationQuiree() {
		String userId = "" + sessionMap.get("user_id");
		String quiree = "select QMST.question_id,QMST.section_id,SEC.section_name,QMST.category_id,CAT.category_name,QMST.type_id,TYP.question_type_name,"
				+ " QST.diff_id,DLEVL.diff_description, "
				+ " IFNULL((SELECT  RES.result FROM cp_test_results RES WHERE RES.userid LIKE '"
				+ userId
				+ "' "
				+ " AND RES.question_id LIKE QMST.question_id  ORDER BY RES.row_id DESC LIMIT 1 ),'UNANSWERED') MODE From cp_questions QST , cp_question_masters QMST, "
				+ " cp_question_type TYP,cp_difficulty_level DLEVL,cp_sections SEC,cp_question_categories CAT "
				+ " WHERE QMST.question_id=QST.question_id  AND SEC.section_id=QMST.section_id AND CAT.category_id=QMST.category_id "
				+ " AND TYP.question_type_id=QMST.type_id AND DLEVL.diff_id=QST.diff_id "
				+ " AND QST.`status` ='ACTIVE' order by QMST.section_id,QMST.category_id,QMST.type_id";
		// System.out.println("\n Quiree is: "+quiree);
		return quiree;
	}

	/**
	 * @author rrajulapati
	 * @throws IOException
	 * @throws JSONException
	 */
	public void jsonQuestionUploading() throws IOException, JSONException {
		CPException exception = null;

		addAditionalData();

		JSONObject object = new JSONObject();
		int questionId = this.getUploadBO().getQuestion_id();
		try {

			if (questionId == 0) {
				questionUploadController.verbalQuestionUploading(this
						.getUploadBO());
			} else {
				questionUploadController.verbalQuestionUpDating(this
						.getUploadBO());
			}
		} catch (DBException e) {
			// TODO Auto-generated catch block
			exception = e;
			e.printStackTrace();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exception = e;
		}
		object.put("questionId", questionId);
		object.put("exceptionRaised", (exception != null));
		if (exception != null)
			object.put("exceptionIs", exception.getMessage());
		object.put("operationStatus", this.getUploadBO().isOperationSuccess());
		response.getWriter().write(object.toString());
	}

	public void deleteSelectedQuestions() throws IOException {
		JSONObject jsonObject = new JSONObject();
		CPException exception = null;

		try {
			jsonObject.put("inputQuiree", deleteQuiree());
			questionUploadController.executeDeleteQuiree(jsonObject);

		} catch (JSONException e) {

		} catch (ConnectionException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exception = e;
		} finally {
			jsonObject.remove("inputQuiree");
			try {
				jsonObject.put("isExceptionRaised", exception != null);
				if (exception != null)
					jsonObject.put("exceptionIs", exception.getMessage());

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		/*
		 * verbalQuestionsDisplaying(); System.out.println(this.jsonString); try
		 * { jsonObject.put("jsonString",this.jsonString); } catch
		 * (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		response.getWriter().write(jsonObject.toString());

	}

	private String deleteQuiree() {
		return "DELETE FROM `crunchprep`.`cp_question_masters` WHERE  `question_id` in ("
				+ request.getParameter("deleteList") + ")";
	}

	private void addAditionalData() {
		this.getUploadBO().setFeeType(
				this.getUploadBO().getFeeType().replace(", on", ""));
		this.getUploadBO().setRefferal(
				this.getUploadBO().getRefferal().replace(", on", ""));
		if (this.getUploadBO()
				.getCategory_name()
				.equalsIgnoreCase(
						Categories.SENTENCE_EQUIVALENCE.getCategorieName())) {
			this.getUploadBO().setCategory_id(
					Categories.SENTENCE_EQUIVALENCE.getCategorieId());
			this.getUploadBO()
					.setSection_id(
							Categories.SENTENCE_EQUIVALENCE.getSection()
									.getSectionId());
			this.getUploadBO().setTypeId(
					QuestionTypeUtil.getQuestionTypeID(
							Categories.SENTENCE_EQUIVALENCE,
							QuestionType.MULTIPLE_ANSWER));
		} else if (this
				.getUploadBO()
				.getCategory_name()
				.equalsIgnoreCase(Categories.TEXT_COMPLETION.getCategorieName())) {
			this.getUploadBO().setCategory_id(
					Categories.TEXT_COMPLETION.getCategorieId());
			this.getUploadBO().setSection_id(
					Categories.TEXT_COMPLETION.getSection().getSectionId());
			this.getUploadBO().setTypeId(
					QuestionTypeUtil.getQuestionTypeID(
							Categories.TEXT_COMPLETION, this.getUploadBO()
									.getQuestion_type()));
		} else if (this
				.getUploadBO()
				.getCategory_name()
				.equalsIgnoreCase(
						Categories.QUNTITATIVE_COMPREHENSION.getCategorieName())) {
			this.getUploadBO().setCategory_id(
					Categories.QUNTITATIVE_COMPREHENSION.getCategorieId());
			this.getUploadBO().setSection_id(
					Categories.QUNTITATIVE_COMPREHENSION.getSection()
							.getSectionId());
			this.getUploadBO().setTypeId(
					QuestionTypeUtil.getQuestionTypeID(
							Categories.QUNTITATIVE_COMPREHENSION,
							QuestionType.SINGLE_ANSWER));
		}
	}

	public String verbalQuestionsDisplaying() {
		JSONObject jsonObject = new JSONObject();
		CPException cpException = null;
		// String operationMessage= request.getParameter("operationName");
		// System.out.println("\n\n Operation MEssage is:  "+operationMessage);

		System.out.println("IN ACTION");
		try {
			jsonObject.put("inputQuiree", getInputQuiree());
			questionUploadController.requiredTableDataInJSONFormate(jsonObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonObject.remove("inputQuiree");

		System.out.println("\n\n  FInal Input data is: "
				+ jsonObject.toString());
		requestMap.put("tableData", jsonObject.toString());
		//this.jsonString = jsonObject.toString();
		/*
		 * try { response.getWriter().write(jsonObject.toString()); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		// requestMap.put("alertMessage",operationMessage);
		return SUCCESS;

	}

	private String getInputQuiree() {
		String quiree = null;
		int categoryId = -1;
		requestMap.put("requestedString", ActionContext.getContext().getName());
		if (requestMap.get("requestedString").toString()
				.equalsIgnoreCase("SentanceEqualanceQuestions")) {
			categoryId = Categories.SENTENCE_EQUIVALENCE.getCategorieId();
			requestMap.put("operation",
					Categories.SENTENCE_EQUIVALENCE.getCategorieName());

		} else if (requestMap.get("requestedString").toString()
				.equalsIgnoreCase("TextCompletionQuestions")) {
			categoryId = Categories.TEXT_COMPLETION.getCategorieId();
			requestMap.put("operation",
					Categories.TEXT_COMPLETION.getCategorieName());
		} else {
			categoryId = Categories.QUNTITATIVE_COMPREHENSION.getCategorieId();
			requestMap.put("operation",
					Categories.QUNTITATIVE_COMPREHENSION.getCategorieName());
		}

		quiree = "SELECT qmaster.question_id,qst.question_no,sec.section_name,cate.category_name,"
				+ " qtype.question_type_name,tmaster.test_name,qmaster.question_title,qmaster.question_directions "
				+ " FROM cp_question_masters qmaster,cp_sections sec,cp_question_categories cate,cp_question_type qtype,"
				+ " cp_testmaster tmaster,cp_questions qst "
				+ " WHERE qmaster.section_id=sec.section_id AND cate.category_id=qmaster.category_id "
				+ " AND qtype.question_type_id=qmaster.type_id  "
				+ " AND tmaster.test_id=qmaster.test_id AND qst.question_id=qmaster.question_id "
				+ " and cate.category_id like " + categoryId + "";

		return quiree;
	}

	private void checkBeanInitalizedOrNot() {
		if (this.getUploadBO() == null) {
			System.out.println("\n\n Bean Not Inialized ");
		} else {
			System.out.println("\n\n Bean Initialized ");
			System.out.println("\n\n Question Type is: "
					+ this.getUploadBO().getQuestion_type());
			System.out.println("\n\n Category Id is: "
					+ this.getUploadBO().getCategory_id());
			System.out.println("\n\n Section Id is: "
					+ this.getUploadBO().getSection_id());
			System.out.println("\n\n Type id is: "
					+ this.getUploadBO().getTypeId());
			System.out.println(" getTest_id() is: "
					+ this.getUploadBO().getTest_id());
			System.out.println(" getQuestion_title() is: "
					+ this.getUploadBO().getQuestion_title());
			System.out.println(" .getQuantityA(): "
					+ this.getUploadBO().getQuantityA());
			System.out.println(" getQuestion_directions() is: "
					+ this.getUploadBO().getQuestion_directions());
			System.out.println(" getSkillid1(): "
					+ this.getUploadBO().getSkillid1());
			System.out.println(" getDifficulty_id()is: "
					+ this.getUploadBO().getDifficulty_id());
			System.out.println(" getSolution_text(): "
					+ this.getUploadBO().getSolution_text());
			System.out.println(" getStatus(): "
					+ this.getUploadBO().getStatus());
			System.out.println(" isFeeType()is: "
					+ this.getUploadBO().getFeeType());

			System.out.println(" isRefferal(): "
					+ this.getUploadBO().getRefferal());
			System.out.println("\n Category Name is: "
					+ this.getUploadBO().getCategory_name());
			if (this.getUploadBO().getChoices() != null) {
				for (String choice : this.getUploadBO().getChoices()) {
					System.out.println("\t CHoices is: " + choice);
				}
			} else {
				System.out.println("\n\n CHoices List not Inialized ");
			}
			System.out.println("\n\n");
			if (this.getUploadBO().getAnswers() != null) {
				for (String choice : this.getUploadBO().getAnswers()) {
					System.out.println("\t Answer is: " + choice);
				}
			} else {
				System.out.println("\n\n Answers List Not Inailized ");
			}
			System.out.println("\n\n");
			if (this.getUploadBO().getTooltips() != null) {
				for (String choice : this.getUploadBO().getTooltips()) {
					System.out.println("\t ToolTip is: " + choice);
				}
			} else {
				System.out.println("\n\n Tool tip List Not Inailized ");
			}

		}
	}

	/**
	 * Question upload required data.
	 * 
	 * @return the string
	 */
	public String questionUploadRequiredData() {

		List<DifficultyBO> difficulitieSList = null;
		List<SkillBO> skillsList = null;
		java.util.ArrayList<TestsBO> testsList = null;
		String difficultyLevels = "<option value=-1>Select</option>";
		String skillLevels = difficultyLevels;
		String testNames = difficultyLevels;
        String sectionid=request.getParameter("sectionId").toString();
		int questionId = 0;
		try {
			

			if ((questionId = Integer.parseInt(request
					.getParameter("questionId"))) > 0) {
				System.out.println("request.getParameter" + questionId);
				JSONObject jsonObject = new JSONObject();
				requestMap.put("requestedString", ActionContext.getContext().getName());
				if (requestMap.get("requestedString").toString()
						.equalsIgnoreCase("quant-comparision")) {
					jsonObject.put("inputQuiree",
							questionslessonsQuery(questionId));
				} else {
					jsonObject.put("inputQuiree",
							questionsDataQuiree(questionId));
				}
				questionUploadController
						.requiredTableDataInJSONFormate(jsonObject);
				jsonObject.remove("inputQuiree");
				System.out.println("\n\n\n\t\t\t\t^^^Fnal Json Data is: \n\n\n "
						+ jsonObject.toString());
				requestMap.put("tableData", jsonObject.toString());
			}
		} catch (NumberFormatException ex) {

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			requestMap.put("questionId", questionId);
		}
		System.out.println("\n\n\n\n\n\n\t\t\t************** Question Id is: "+questionId+"\n\n\n");

		try {
			if ((difficulitieSList = commonsController.getDifficultiesList()) != null) {
				for (DifficultyBO difficulty : difficulitieSList) {
					difficultyLevels = difficultyLevels + "<option value="
							+ difficulty.getDifficulty_id() + ">"
							+ difficulty.getDifficulty_name() + "</option>";

				}

			}

		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if ((skillsList = commonsController.getSkillsList(sectionid)) != null) {
				for (SkillBO skill : skillsList) {
					skillLevels = skillLevels + "<option value="
							+ skill.getSkill_id() + ">" + skill.getSkill_name()
							+ "</option>";

				}

			}

		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if ((testsList = commonsController.getTestsList()) != null) {
				for (TestsBO test : testsList) {
					testNames = testNames + "<option value=" + test.getTestId()
							+ ">" + test.getTestName() + "</option>";

				}
			}
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			this.controller = new QuestionUploadController();
			this.setLessons(this.controller.loadLessons(1, 4));
		} catch (ConnectionException e) {
			// TODO: handle exception
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestMap.put("difficultyLevels", difficultyLevels);
		requestMap.put("skillLevels", skillLevels);
		requestMap.put("testNames", testNames);
		requestMap.put("requestedString", ActionContext.getContext().getName());
		System.out
				.println("requestMap.get\t" + requestMap.get("requestedString"));
		if (requestMap.get("requestedString").toString()
				.equalsIgnoreCase("SentenceEquivalence"))
			requestMap.put("operation",
					Categories.SENTENCE_EQUIVALENCE.getCategorieName());
		else if (requestMap.get("requestedString").toString()
				.equalsIgnoreCase("TextCompletion"))
			requestMap.put("operation",
					Categories.TEXT_COMPLETION.getCategorieName());
		else if (requestMap.get("requestedString").toString()
				.equalsIgnoreCase("quant-comparision"))
			requestMap.put("operation",
					Categories.QUNTITATIVE_COMPREHENSION.getCategorieName());

		return SUCCESS;
	}

	private String questionsDataQuiree(int questionId) {
		return "SELECT * FROM cp_question_masters QMAS , cp_questions QST , cp_question_choices QCHS, cp_question_answers QANS "
				+ " WHERE  QMAS.question_id=QST.question_id AND QST.question_id=QANS.question_id AND QST.question_no=QANS.question_no "
				+ " AND QST.question_id=QCHS.question_id AND QST.question_no=QCHS.question_no AND "
				+ "  QMAS.question_id=" + questionId;

	}

	private String questionslessonsQuery(int questionId) {
		return "SELECT * FROM cp_question_masters QMAS, cp_questions QST,"
				+ " cp_question_choices QCHS, cp_question_answers QANS,cp_question_lessons qlessons,"
				+ "cp_lessons lsns WHERE QMAS.question_id=QST.question_id AND QST.question_id=QANS.question_id AND "
				+ "QST.question_no=QANS.question_no AND QST.question_id=QCHS.question_id AND QST.question_no=QCHS.question_no  "
				+ "AND qlessons.sublesson_id=lsns.sublesson_id AND qlessons.question_id=QST.question_id AND qlessons.question_no="
				+ "QST.question_no  and QMAS.question_id=" + questionId;

	}

	/**
	 * @author kkatikala
	 * @throws IOException
	 * @throws JSONException
	 */
	public void QuantComparisionUploading() throws IOException, JSONException {
		CPException exception = null;

		addAditionalData();

		// System.out.println("SKILSSSS"+this.getUploadBO().getSkills()+"    TYPE ID"+this.getUploadBO().getLessons()+"   "+this.getUploadBO().getFeeType());

		JSONObject object = new JSONObject();
		int questionId = this.getUploadBO().getQuestion_id();
		try {

			if (questionId == 0) {
				questionUploadController.quantComparisionUploading(this
						.getUploadBO());
			} else {
				questionUploadController.quantComparisionUpDating(this
						.getUploadBO());
			}
		} catch (DBException e) {
			// TODO Auto-generated catch block
			exception = e;
			e.printStackTrace();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exception = e;
		}

		object.put("questionId", questionId);
		object.put("exceptionRaised", (exception != null));
		if (exception != null)
			object.put("exceptionIs", exception.getMessage());
		object.put("operationStatus", this.getUploadBO().isOperationSuccess());
		response.getWriter().write(object.toString());
	}

	
		/**
		 * @author kkatikala
		 * @throws DBException
		 * @throws SQLException
		 * @throws JSONException 
		 * @throws IOException 
		 */
		public void getPercentageCountforCategories() throws DBException, SQLException, JSONException, IOException{
			
			LoginUserBO user=(LoginUserBO)sessionMap.get("user");
			System.out.println("USer ID "+user.getUser_id());
					
			
			String query="SELECT DISTINCT " +
					"IFNULL(ROUND((( SELECT COUNT(res.row_id)  FROM cp_test_results res " +
					" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT')*100)/( " +
					" SELECT COUNT(res.row_id) FROM cp_test_results res" +
					" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1)),0) AS math," +
					"IFNULL(ROUND((( SELECT COUNT(res.row_id)  FROM cp_test_results res " +
					" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.result='CORRECT')*100)/( " +
					" SELECT COUNT(res.row_id) FROM cp_test_results res" +
					" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2)),0) AS verbal, " +
					"IFNULL(ROUND((( SELECT COUNT(res.row_id)  FROM cp_test_results res " +
					" WHERE res.userid="+user.getUser_id()+" AND res.category_id=4 AND res.result='CORRECT')*100)/( " +
					" SELECT COUNT(res.row_id) FROM cp_test_results res" +
					" WHERE res.userid="+user.getUser_id()+" AND res.category_id=4)),0) AS quantComparison, IFNULL(ROUND(((SELECT COUNT(res.row_id) FROM cp_test_results res " +
					"WHERE res.userid="+user.getUser_id()+" AND res.category_id=6 AND res.result='CORRECT')*100)/(SELECT COUNT(res.row_id) FROM cp_test_results res " +
					"WHERE res.userid="+user.getUser_id()+" AND res.category_id=6)),0) AS problemSolving, IFNULL(ROUND(((SELECT COUNT(res.row_id) " +
					"FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=6 AND res.type_id=8 AND res.result='CORRECT')*100)/( " +
					"SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=6 AND res.type_id=8)),0) " +
					"AS problemSolvingSingle, IFNULL(ROUND(((SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" " +
					"AND res.category_id=6 AND res.type_id=9 AND res.result='CORRECT')*100)/( SELECT COUNT(res.row_id) FROM cp_test_results res " +
					"WHERE res.userid="+user.getUser_id()+" AND res.category_id=6 AND res.type_id=9)),0) AS problemSolvingDouble, IFNULL(ROUND((( " +
					"SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=6 AND " +
					"res.type_id=10 AND res.result='CORRECT')*100)/(SELECT COUNT(res.row_id) FROM cp_test_results res " +
					"WHERE res.userid="+user.getUser_id()+" AND res.category_id=6 AND res.type_id=10)),0) AS numericeEntrySingle, IFNULL(ROUND((( SELECT COUNT(res.row_id) " +
					"FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=6 AND res.type_id=13 AND res.result='CORRECT')*100)/( " +
					 "SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=6 AND res.type_id=13)),0) AS numericEntryDouble," +
					 "IFNULL(ROUND((( SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=5 AND res.result='CORRECT')*100)/( " +
					 "SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=5)),0) AS dataInterpretation, " +
					 "IFNULL(ROUND((( SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=2 AND res.result='CORRECT')*100)/( " +
					 "SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=2)),0) AS sentenceEquivalence, " +
					 "IFNULL(ROUND((( SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=1 AND " +
					 "res.result='CORRECT')*100)/(SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=1)),0) AS textCompletion, " +
					 "IFNULL(ROUND(((SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=1 AND res.type_id=1 AND res.result='CORRECT')*100)/( " +
					 "SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=1 AND res.type_id=1)),0) AS textCompletionSingle, " +
					 "IFNULL(ROUND((( SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=1 AND res.type_id=2 AND res.result='CORRECT')*100)/( " +
					 "SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=1 AND res.type_id=2)),0) AS textCompletionDouble, " +
					 "IFNULL(ROUND((( SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=1 AND res.type_id=2 AND res.result='CORRECT')*100)/( " +
					 "SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=1 AND res.type_id=3)),0) AS textCompletiontriple, " +
					 "IFNULL(ROUND((( SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=3 AND res.result='CORRECT')*100)/( " +
					 "SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=3)),0) AS readingComprehension, IFNULL(ROUND((( " +
					 "SELECT COUNT(res.row_id) FROM cp_test_results res,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp " +
					 "WHERE res.userid="+user.getUser_id()+" AND res.category_id=3 AND res.question_id=qmasters.question_id AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='SHORT' AND res.result='CORRECT')*100)/( " +
					 "SELECT COUNT(res.row_id) FROM cp_test_results res,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp " +
					 "WHERE res.userid="+user.getUser_id()+" AND res.category_id=3  AND res.question_id=qmasters.question_id " +
					 "AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='SHORT' )),0) AS readingCompShort, IFNULL(ROUND((( " +
					 "SELECT COUNT(res.row_id) FROM cp_test_results res,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp " +
					 "WHERE res.userid="+user.getUser_id()+" AND res.category_id=3 AND res.question_id=qmasters.question_id AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='LONG' AND res.result='CORRECT')*100)/(" +
					 " SELECT COUNT(res.row_id) FROM cp_test_results res,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp WHERE res.userid="+user.getUser_id()+" AND res.category_id=3  " +
					 "AND res.question_id=qmasters.question_id AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='LONG' )),0) AS readingCompLong,  IFNULL(ROUND(((SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=9 AND res.result='CORRECT')*100)/(SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.category_id=9)),0) AS criticalreasoning            FROM cp_test_results test WHERE test.userid="+user.getUser_id()+"  ";
			
			System.out.println("The query is--------------------------"+query);
			CPException exception=null;
			JSONObject jsonObject=new JSONObject();
			try {
				jsonObject=practiceSessionController.getPercentageForCategories(query);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DBException e) {
				// TODO Auto-generated catch block
				exception=e;
				e.printStackTrace();
			}			
			jsonObject.put("exception",(exception!=null));
	 		if(exception!=null) jsonObject.put("exceptionMessage",exception.getMessage());
	 		//System.out.println("\n\n Json Data is: "+jsonObject.toString());
	 		requestMap.put("percentageCount",jsonObject.toString() );
	 		//response.getWriter().write(jsonObject.toString());
		}			
	/* 

	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;

	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	@Override
	public void setRequest(Map<String, Object> requestMap) {
		this.requestMap = requestMap;

	}

	public String getRequestedPage() {
		return requestedPage;
	}

	public void setRequestedPage(String requestedPage) {
		this.requestedPage = requestedPage;
	}

	public Map<Integer, String> getLessons() {
		return lessons;
	}

	public void setLessons(Map<Integer, String> lessons) {
		this.lessons = lessons;
	}

	public QuestionsUploadBO getUploadBO() {
		return uploadBO;
	}

	public void setUploadBO(QuestionsUploadBO uploadBO) {
		this.uploadBO = uploadBO;
	}

}

package com.greenbuds.crunchprep.action.fulllengthtest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSON;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.FullLengthTestBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.FullResultsBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.OverallPerformanceBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.QuestionAnalysisBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.ResumeBo;
import com.greenbuds.crunchprep.bo.fulllengthtest.ScoreBO;

import com.greenbuds.crunchprep.bo.practicesession.ResultsBO;
import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.bo.server.ServerProperties;
import com.greenbuds.crunchprep.controller.contentcreator.IQuestionUploadController;
import com.greenbuds.crunchprep.controller.contentcreator.QuestionUploadController;
import com.greenbuds.crunchprep.controller.fulllengthtest.FulllengthController;
import com.greenbuds.crunchprep.controller.fulllengthtest.IFulllengthController;
import com.greenbuds.crunchprep.controller.practicesession.IPracticeSessionController;
import com.greenbuds.crunchprep.controller.practicesession.PracticeSessionController;
import com.greenbuds.crunchprep.dao.fulllengthTest.FulllengthDao;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.Categories;
import com.greenbuds.crunchprep.setups.QuantComparssion;
import com.greenbuds.crunchprep.setups.QuestionType;
import com.greenbuds.crunchprep.setups.Section;
import com.greenbuds.crunchprep.util.ConvertBeanToJSON;
import com.greenbuds.crunchprep.util.DifficultyLevelScore;
import com.greenbuds.crunchprep.util.DoubtsCLear;
import com.greenbuds.crunchprep.util.QuestionTypeUtil;
import com.opensymphony.xwork2.ActionSupport;

public class FullLengthTestAction extends ActionSupport implements
		SessionAware, ServletRequestAware, ServletResponseAware,RequestAware {

	private Map<String, Object> sessionMap;
	private HttpServletRequest request;
	private List<QuestionsUploadBO> questionsList;
	private String questionIndex;
	private FullLengthTestBO fullLengthTestBO;
	private ResumeBo resumeBo;
	private List<OverallPerformanceBO> performanceSummary;
	private int testNo=0;
	private int testno=0;

	private int  reviewTestNumber;
	private String qaData;
	private String avgTimeData;
	private String qaDataTable;
	private List<QuestionAnalysisBO> quesAnalysisBOs;
	private String remainingTime;
	private List<QuestionsUploadBO> scorePrediction;
	private List<ScoreBO> scaledScoreRanges;
	Double quantScore=0.0;
	Double verbalScore=0.0;
	Double totalQuantScaledScore=0.0;
	Double totalVerbalScaledScore=0.0;
	Double quantPercentile=0.0;
	Double verbalPercentile=0.0;
	
	public String getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(String remainingTime) {
		this.remainingTime = remainingTime;
	}

	public String getQaDataTable() {
		return qaDataTable;
	}

	public void setQaDataTable(String qaDataTable) {
		this.qaDataTable = qaDataTable;
	}

	public String getAvgTimeData() {
		return avgTimeData;
	}

	public void setAvgTimeData(String avgTimeData) {
		this.avgTimeData = avgTimeData;
	}

	public List<QuestionAnalysisBO> getQuesAnalysisBOs() {
		return quesAnalysisBOs;
	}

	public void setQuesAnalysisBOs(List<QuestionAnalysisBO> quesAnalysisBOs) {
		this.quesAnalysisBOs = quesAnalysisBOs;
	}

	public String getQaData() {
		return qaData;
	}

	public void setQaData(String qaData) {
		this.qaData = qaData;
	}


	
	private QuestionsUploadBO questionsUploadBO;

	public int getReviewTestNumber() {
		return reviewTestNumber;
	}

	public void setReviewTestNumber(int reviewTestNumber) {
		this.reviewTestNumber = reviewTestNumber;
	}

	private FullResultsBO fullResultsBO;

	public FullResultsBO getFullResultsBO() {
		return fullResultsBO;
	}

	public List<OverallPerformanceBO> getPerformanceSummary() {
		return performanceSummary;
	}

	public void setPerformanceSummary(List<OverallPerformanceBO> performanceSummary) {
		this.performanceSummary = performanceSummary;
	}

	public void setFullResultsBO(FullResultsBO fullResultsBO) {
		this.fullResultsBO = fullResultsBO;
	}

	public FullLengthTestBO getFullLengthTestBO() {
		return fullLengthTestBO;
	}

	public void setFullLengthTestBO(FullLengthTestBO fullLengthTestBO) {
		this.fullLengthTestBO = fullLengthTestBO;
	}

	public List<QuestionsUploadBO> getQuestionsList() {
		return questionsList;
	}

	public void setQuestionsList(List<QuestionsUploadBO> questionsList) {
		this.questionsList = questionsList;
	}

	public String getQuestionIndex() {
		return questionIndex;
	}

	public void setQuestionIndex(String questionIndex) {
		this.questionIndex = questionIndex;
	}

	private PracticeSessionController practiceSessionController;
	private HttpServletResponse response;
	private FulllengthController fulllengthController;

	@Override
	public String execute() {
		// TODO Auto-generated method stub

		String result = "quant_comparison";

		String res[] = {"quant_comparison", "problem_solving_single",
				"problem_solving_double", "text_completion_single",
				"text_completion_double", "text_completion_triple",
				"sentence_equivalence", "reading_comprehension_single",
				"reading_comprehension_double", "data_interpretation_single",
				"data_interpretation_double", "problem_solving_single_fill",
				"problem_solving_double_fill"};

		Random randomGenerator = new Random();
		int i = randomGenerator.nextInt(12);
		System.out.println("************  IAM FROM ACTION ***************"
				+ res[i] + "  I : " + i);
		result = res[i];

		/*
		 * Data for getting Quant QUestions and Verbal Questions Start
		 */
		fulllengthController = FulllengthController.getInstance();
		LoginUserBO bo = (LoginUserBO) sessionMap.get("user");
		int userId = bo.getUser_id();
		Random r = new Random();
		char c = r.nextBoolean() ? 'Q' : 'V';
		List<QuestionsUploadBO> question_list = null;
		System.out.println("Getting Questions Started  " + c);
		JSONObject object = new JSONObject();
		try {
			object.put("limit", ServerProperties.getInistance().getDifficulty());
			object.put("sectionNumber",1);
			
			if (c == 'Q') {
				// This is for Quant Section( please provide Section and
				// diffictulry range for this method)
				object.put("testNumber", sessionMap.get("test_no"));
				question_list = fulllengthController
						.getQuestionsForFulllegthTest(Section.QUANTITATIVE,
								object, userId);

			} else if (c == 'V') {
				// This is for Verbal Section( please provide Section and
				// diffictulry range for this method)
				System.out.println("Getting Verbal Questions");
				object.put("testNumber", sessionMap.get("test_no"));
				question_list = fulllengthController
						.getQuestionsForFulllegthTest(Section.VERBAL, object,
								userId);

			}
			if (question_list.size() == 0 || question_list == null) {
				System.out.println("Questions Not Available!!!!!!!!!");
			} else {

				JSONArray jsonArray = new JSONArray();
				for (int i1 = 0; i1 < question_list.size(); i1++) {
					try {
						this.getQuestionsList().get(i).setQuestion_index(i + 1);
						object = ConvertBeanToJSON.ConvertObjectToMap(this
								.getQuestionsList().get(i));
						System.out.println("The object is--------------"
								+ object.toString());
					} catch (IllegalAccessException e) {

						e.printStackTrace();
					} catch (IllegalArgumentException e) {

						e.printStackTrace();
					} catch (InvocationTargetException e) {

						e.printStackTrace();
					}
					jsonArray.put(object);
				}

				String questionsList = jsonArray.toString();
				//object.put("questions", questionsList);

				System.out.println("JSON OBJECT String     ::::: "
						+ questionsList);
				System.out.println("JSON ARRAY " + jsonArray);
				System.out.println("JSON OBJECT " + object.toString());
				System.out.println("Total Questionssssssssss:"
						+ question_list.size());

			}// else
		} catch (JSONException e) {
			e.printStackTrace();
		}
		/*
		 * Data for getting Quant QUestions and Verbal Questions End
		 */catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommonExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public String createTestNumber() {
		System.out.println("In test creation--------------START----------------------------------");
		// System.out.println("Start Practice With:"+request.getParameter("sublessonid"));
		LoginUserBO bo = (LoginUserBO) sessionMap.get("user");
		int userid = bo.getUser_id();
		String query = "";
		/*
		 * query= "select qls.*,qmas.*,qs.*,le.*," +
		 * " (select GROUP_CONCAT(skill.skill_name) from cp_skill_level skill where skill.skill_id in(qs.skill_id1,qs.skill_id2,qs.skill_id3)) as skills,(select dlevel.diff_description from cp_difficulty_level dlevel where dlevel.diff_id=qs.diff_id) as difficultyLevel,cat.category_name"
		 * +
		 * "  from cp_questions qs left join cp_question_lessons qls on qs.question_id= qls.question_id ,"
		 * +
		 * " (cp_test_results trs right join  cp_question_masters qmas on qmas.question_id=trs.question_id and "
		 * + " trs.userid='"+userid+
		 * "')left join cp_reading_comprehension_passage rpas  on rpas.passage_id=qmas.passage_id  ,"
		 * + " cp_difficulty_level le,cp_question_categories cat" +
		 * " WHERE qmas.test_id like '1' and  qs.question_id=qmas.question_id  and le.diff_id=qs.diff_id AND cat.category_id=qmas.category_id and qmas.section_id like '1' and qls.sublesson_id like '%' group by qs.question_id limit 20"
		 * ;
		 * 
		 * System.out.println("query is -------------------------------: "+query)
		 * ; sessionMap.put("practiceQuery", query);
		 */
		practiceSessionController = new PracticeSessionController();
		String testname = (String) request.getParameter("testname");
		if (testname == null)
			testname = "Full Length Test";
		try {
			int testno = practiceSessionController.createTestNumber(userid,2,testname);
			sessionMap.put("test_no", testno);
			// this.setAdaptiveModeInPage(getAdaptiveMode());
			// this.setTimeLimitInPage(getTimeLimitFromCustomization());
			getActivityID();

		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "success";
	}

	public void getActivityID() {
		String result = "";
		LoginUserBO bo = (LoginUserBO) sessionMap.get("user");
		QuestionsUploadBO questionsUploadBO = new QuestionsUploadBO();
		questionsUploadBO.setUser_id(bo.getUser_id());
		questionsUploadBO.setTest_no(Integer.parseInt(sessionMap.get("test_no").toString()));
		questionsUploadBO.setTest_name("PRACTICE TEST");
		//System.out.println("UserId\t" + questionsUploadBO.getUser_id());
		practiceSessionController = PracticeSessionController.getInstance();
		try {
			practiceSessionController.testSessions(questionsUploadBO);
			System.out.println("Activity Id\t"
					+ questionsUploadBO.getActivity_id());
			if (questionsUploadBO != null) {
				sessionMap.put("test_activity_id",
						questionsUploadBO.getActivity_id());

			}
		} catch (CPException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void gettingQuestionsforFullTest() throws ConnectionException, SQLException {
		
		System.out.println("Calling from start Full-length test");
		//String testype=request.getParameter("testtype").toString();
		/*if(testype.equalsIgnoreCase("newtest"))
		{*/
		LoginUserBO bo = (LoginUserBO) sessionMap.get("user");
		int userid = bo.getUser_id();
		List<QuestionsUploadBO> question_list = null;
		JSONObject object = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		try {
			//System.out.println("Section : "+request.getParameter("section"));
			object.put("limit", ServerProperties.getInistance().getDifficulty());
 
			object.put("sectionNumber",1);
			int c = 2;
			//int c = (Integer.parseInt(request.getParameter("section").toString())==81)? 1 : 2;
 
			object.put("sectionNumber",request.getParameter("currentSection"));
			int fullLengthRowId = 0;
			try{
			fullLengthRowId =  Integer.parseInt(request.getParameter("section").toString());
			}catch(NumberFormatException ex)
			{
				
			}
			// object.put("fullLengthRowId",fullLengthRowId);
			//byte c = 0;
			byte firstSection = (byte) ((Integer.parseInt(request.getParameter("section").toString())==81)? 1 : 2);
			if(Integer.parseInt(request.getParameter("currentSection").toString())==1)
			c = firstSection;
			else{
				//c = (byte) ((firstSection==1 && (Integer.parseInt(request.getParameter("currentSection").toString())%2==1))? 1 : 2);
				c = (byte) ((firstSection==1)? ((Integer.parseInt(request.getParameter("currentSection").toString())%2==1))? 1 : 2 : ((Integer.parseInt(request.getParameter("currentSection").toString())%2==1))? 2 : 1);
			}
			
		fulllengthController = FulllengthController.getInstance();
			
			//jsonArray.put(new JSONObject().put("generatedPattern",c==1?"Q_V_Q_V_Q":"V_Q_V_Q_V"));
		System.out.println("limit is : "+object.getDouble("limit")+"\t Section Number is : "+object.getInt("sectionNumber"));
		 if(object.getInt("sectionNumber")>2 && object.getInt("sectionNumber")<5 )
		{
		 object.put("userId",bo.getUser_id());
		 object.put("limit", fulllengthController.getDifficultyLevel(object));
		 System.out.println("NEw Limit is : "+object.getInt("limit"));
		}  
		 if(sessionMap!=null)
		 {
			 object.put("fullLengthRowId",sessionMap.get("fullLengthRowId"));
		 }
			if (c == 1) {
				// This is for Quant Section( please provide Section and
				// diffictulry range for this method)
				object.put("testNumber", sessionMap.get("test_no"));
				System.out.println("testNumber ; "+ object.getInt("testNumber"));
				try {
					
					question_list = fulllengthController
							.getQuestionsForFulllegthTest(Section.QUANTITATIVE,
									object, userid);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				// This is for Verbal Section( please provide Section and
				// diffictulry range for this method)
				object.put("testNumber", sessionMap.get("test_no"));
				System.out.println("testNumber ; "
						+ object.getInt("testNumber"));
				try {
					
				 
					
					question_list = fulllengthController
							.getQuestionsForFulllegthTest(Section.VERBAL,
									object, userid);
					
					
					//question_list=resumingTest();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(object.getInt("sectionNumber")==1)
			{
			sessionMap.put("fullLengthRowId",object.get("fullLengthRowId"));
			}
			System.out.println("Haiiii:" + question_list.size());
			if (question_list.size() == 0) {
				System.out.println("Questions Not Available!!!!!!!!!");
			}
			else {
				//jsonArray.put(new JSONObject().put("fullLengthRowId",object.get("fullLengthRowId")));
                //int rowId=object.getInt("fullLengthRowId");
                
				for (int i1 = 0; i1 < question_list.size(); i1++) {
					try {
						question_list.get(i1).setQuestion_index(i1 + 1);
						//System.out.println("Questions List is : "+question_list);
						object = ConvertBeanToJSON
								.ConvertObjectToMap(question_list.get(i1));
						System.out.println("Object\t"+object);
						object.put("generatedPattern",c==1?"Q_V_Q_V_Q":"V_Q_V_Q_V");
						
						//object.put("fullLengthRowId",rowId);
					} catch (IllegalAccessException e) {

						e.printStackTrace();
					} catch (IllegalArgumentException e) {

						e.printStackTrace();
					} catch (InvocationTargetException e) {

						e.printStackTrace();
					}
					
					jsonArray.put(object);
				}
				
				String questionsList = jsonArray.toString();
			//	object.put("questions", questionsList);
				response.setContentType("text/JSON");
				try {
					System.out.println("\n B4 Retun JSonarra "+jsonArray.toString());					
					response.getWriter().write(jsonArray.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			/*	System.out.println("JSON OBJECT String     ::::: "
						+ questionsList);
				System.out.println("JSON ARRAY " + jsonArray);
				System.out.println("JSON OBJECT " + object.toString());
				System.out.println("Total Questionssssssssss:"
						+ question_list.size());*/

			}// else
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		/*}
		else
		{
			try {
			JSONArray jsonArray = resumingTest();
			response.getWriter().write(jsonArray.toString());
			System.out.println("The resume test has beeen calledddd------------"+jsonArray.toString());
			} catch (JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}

	public String getQuestionTypePages() {
		String result = "";
		System.out.println(request.getParameter("type_id"));
		int type = Integer.parseInt(request.getParameter("type_id"));
		int questionid = Integer.parseInt(request.getParameter("question_id"));
		System.out.println(" Type ID"
				+ type
				+ "     ::::  "
				+ QuestionTypeUtil.getQuestionTypeID(
						Categories.QUNTITATIVE_COMPREHENSION,
						QuestionType.SINGLE_ANSWER));
		if (type == 7) {
			result = "quant_comparison";
		} else if (type == 8) {
			result = "problem_solving_single";
		} else if (type == 9) {
			result = "problem_solving_double";
		} else if (type == 10) {
			result = "problem_solving_single_fill";
		} else if (type == 13) {
			result = "problem_solving_double_fill";
		} else if (type == 1) {
			result = "text_completion_single";
		} else if (type == 2) {
			result = "text_completion_double";
		} else if (type == 3) {
			result = "text_completion_triple";
		} else if (type == 6) {
			result = "sentence_equivalence";
		} else if (type == 4) {
			result = "reading_comprehension_single";
		} else if (type == 5) {
			result = "reading_comprehension_double";
		} else if (type == 11) {
			result = "data_interpretation_single";
		} else if (type == 12) {
			result = "data_interpretation_double";
		} else if (type == 15) {
			result = "reading_comprehension_single";
		} else if (type == 16) {
			result = "data_interpretation_single_fill";
		} else if (type == 17) {
			result = "data_interpretation_double_fill";
		}
		else if (type == 14) {
			result = "reading_comprehension_select_in";
		} 
		System.out.println("Result   " + result);
		return result;
	}

	public void markingQuestion() throws JSONException, IOException {

		System.out.println("in marking question");
		String flagValue = request.getParameter("flag");
		System.out.println("FlagValue\t"
				+ flagValue
				+ "Question Id\t"
				+ Integer.parseInt(request.getParameter("questionid")
						.toString()));
		FullLengthTestBO bo = new FullLengthTestBO();
		// sessionMap.put("test_no", 1);
		
		System.out.println("FlagValue\t"+flagValue+"Question Id\t"+Integer.parseInt(request.getParameter("questionid").toString()));
		

		//sessionMap.put("test_no", 1);
		
		LoginUserBO userbo = (LoginUserBO) sessionMap.get("user");
		bo.setUserid(userbo.getUser_id());
		bo.setFlag_status(flagValue);
		bo.setTest_no(Integer.parseInt(sessionMap.get("test_no").toString()));
		bo.setTest_id(2);
		bo.setQuestion_id(Integer.parseInt(request.getParameter("questionid").toString()));

		JSONObject obj = new JSONObject();
		fulllengthController = FulllengthController.getInstance();
		try {
			if (fulllengthController.markingQuestion(bo)) {
				obj.put("message", "Operation Done Successfully");
				
						
			} else {
				obj.put("message", "Failed");
			}
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.getWriter().write(obj.toString());
	}

	public void submitFullLengthTest() throws JSONException,
			ConnectionException, SQLException {
		JSONArray originalquestionsArray = new JSONArray(
				request.getParameter("totalquestions")); 
		JSONObject jsonObject=new JSONObject(); 
	    if(this.questionIndex.equals("20"))
	    {
	    	jsonObject.put("isLastIndex", "YES");
	    }
	    else
	    {
	    	jsonObject.put("isLastIndex", "NO");
	    	nextBackQuestionFunctionality(originalquestionsArray);
	    } 
	    System.out.println("Submitting question no: before:"+jsonObject.toString());  
	    // submitFullLengthTestExistSectionAnswers();
		// exitSectionFunctionality();
		try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public void submitFullLengthTestExistSectionAnswers() {
		try{
		 
		//System.out.println("TestStatus_--------------------------"+request.getParameter("testStatus"));
		 byte  correctAnswersDiffCount=0;
		 double sectionDiffLevel=0.0;
         String teststatus = "INCOMPLETE";
         if(request.getParameter("testStatus")!=null)
         {
        	 teststatus=request.getParameter("testStatus");
        	 
         }
         try {
			JSONArray originalquestionsArray = new JSONArray(
					request.getParameter("totalquestions"));
			 LoginUserBO userbo = (LoginUserBO) sessionMap.get("user");
			 int userid=userbo.getUser_id();
			for (int i = 0; i < originalquestionsArray.length(); i++) {
				
				JSONObject ob = originalquestionsArray.getJSONObject(i);
				// ob.put("user_id",userid);
	            ob.put("remainingTime", "00:00:00");
		 	if(ob.get("UserAnswer") instanceof JSONArray)
			{
			//System.out.println("It is a JSONARRAY");
			JSONArray useranswer=ob.getJSONArray("UserAnswer");
			
			/**
			 * Ram Kumar.
			 * Conditions to check answers for TC Questions()
			 * They Contains _'s  
			 */
			if(Integer.parseInt(ob.get("TypeId").toString())==1){ 
				useranswer=new JSONArray();
				if(useranswer.length()==0){
					useranswer.put(""); 
				}
				else
				{
					String tc_one_answer=useranswer.getString(0); 
		 			String s[]=tc_one_answer.split("_1");  
		 			useranswer.put(s[0]); 
				}
	 			
	 			
	 			
	 		}
			if(Integer.parseInt(ob.get("TypeId").toString())==2){
				
				 List<String> tc_two_answers=new ArrayList<String>(); 
				 String v[]=new String[10];
				for (int answer = 0; answer < useranswer.length(); answer++) { 
					if(useranswer.getString(answer).contains("_1")||useranswer.getString(answer).contains("_2"))
					{
					 if(useranswer.getString(answer).contains("_1"))
					 {
						 v=useranswer.getString(answer).split("_1");
					 }
					 if(useranswer.getString(answer).contains("_2"))
					 {
						 v=useranswer.getString(answer).split("_2");
					 }
					 
					}
					 else
					 {
						 v=new String("").split(",");
					 }
					tc_two_answers.add(v[0]);  
					
				}  
				useranswer=new JSONArray();
				if(tc_two_answers.size()==0){
					useranswer.put(""); 
				}
				else
				{
					for(String s:tc_two_answers){
						useranswer.put(s); 
					} 
				} 
	 		}
			if(Integer.parseInt(ob.get("TypeId").toString())==3){
				List<String> tc_three_answers=new ArrayList<String>(); 
				String v[]=new String[10];
				for (int answer = 0; answer < useranswer.length(); answer++) { 
					 if(useranswer.getString(answer).contains("_1")||useranswer.getString(answer).contains("_2")||useranswer.getString(answer).contains("_3"))
					 {
					 if(useranswer.getString(answer).contains("_1"))
					 {
						 v=useranswer.getString(answer).split("_1");
					 }
					 if(useranswer.getString(answer).contains("_2"))
					 {
						 v=useranswer.getString(answer).split("_2");
					 }
					 if(useranswer.getString(answer).contains("_3"))
					 {
						 v=useranswer.getString(answer).split("_3");
					 }
					 }
					 else
					 {
						 v=new String("").split(",");
					 }
					 tc_three_answers.add(v[0]); 
				} 
				useranswer=new JSONArray(); 
				if(tc_three_answers.size()==0){
					useranswer.put(""); 
				}
				else
				{
					for(String s:tc_three_answers){
						useranswer.put(s); 
					} 
				} 
				/*for(String s:tc_three_answers){
					useranswer.put(s);
				}*/
	 		} 
			int answerCount=0;
			LinkedList<String> userAnswers=new LinkedList<String>();
			System.out.println("user Answer JSON : "+useranswer+""+ob.get("Question_id"));
			
			if(useranswer.length()>0)
			{
				System.out.println("The user answers are----------------------"+useranswer.toString());
				for (int answer = 0; answer < useranswer.length(); answer++) {
					System.out.println("anser is :   "+answer);
					userAnswers.add(useranswer.getString(answer));
				}				
			}
			if (useranswer.length()>0) {
				
				ob.put("IsAnswered","Answered");
				JSONArray array = ob.getJSONArray("Answers");
			//	System.out.println("\nThe questions Answers\t:"+array.toString()+"\nUser Answers\t:"+userAnswers.toString()+"\nUser JsonArray\t:"+useranswer.toString());			
				for (int answer = 0; answer < array.length(); answer++) {
					if(useranswer.length()==1)
					{					
						  ob.put("Answerstatus",useranswer.getString(0).equalsIgnoreCase(array.getString(answer))?"CORRECT":"INCORRECT");
						//  System.out.println("the answerstatus is------------------"+ob.get("Answerstatus"));
				     }
					else{						
						if(Collections.frequency(userAnswers,array.getString(answer))>0)
						{						
							answerCount++;
							//System.out.println("The user answer is-------------------"+answerCount);
						}
					}
									
				}
				if(useranswer.length()>1)
				{
 
					for (int answer = 0; answer < useranswer.length(); answer++) {
						userAnswers.add(useranswer.getString(answer));
					}
 
					ob.put("Answerstatus",array.length()==answerCount?"CORRECT":"INCORRECT");
 
				}
					
				}				
			}
			else
			{
				  System.out.println("It is not a JSONARRAY");
				  ob.put("Answerstatus","SKIPPED");
				  ob.put("IsAnswered","UnAnswered");
			}
		 	int sectionnumber=Integer.parseInt(request.getParameter("sectionNumber"));
		 	 	
		 	if(i==originalquestionsArray.length()-1)
		    {		
		 		
		 		   	ob.put("user_id",userid);
		    	    ob.put("currentsectionnumber",sectionnumber);
				    ob.put("currentsectionstatus",	"COMPLETE");
				    if(sectionnumber==5)
				    ob.put("teststatus",sectionnumber==5?"COMPLETE":"INCOMPLETE");	
				    else
				    ob.put("teststatus",teststatus);
				    ob.put("Test_no", sessionMap.get("test_no"));					   				  
		    }
				 		   	
			    if(sectionnumber==5)
			    ob.put("teststatus",sectionnumber==5?"COMPLETE":"INCOMPLETE");	
			    else
			    ob.put("teststatus",teststatus);
		 	
	 	 //System.out.println("The question no is answer is----------------"+ob.get("Answerstatus")+"teststatus--------------------"+ob.get("teststatus"));		 	 
		 	 
		 	 //static fields  to change in future-------------->
			 ob.put("Test_id", 2);  
			 // ob.put("usertime", "00:00:00");
			 //static fields--------------------------------->
			 ob.put("user_id",userid);
			 if(ob.getString("Answerstatus").equalsIgnoreCase("CORRECT"))
			   {
				   correctAnswersDiffCount+=ob.getInt("Difficulty_id");
			   }
		      sectionDiffLevel=DoubtsCLear.getNextDifficultyLevel(correctAnswersDiffCount);
		      ob.put("sectionDiffLevel",sectionDiffLevel);	 	 
			  }
			  System.out.println("The json array object values are----------------------------------"+originalquestionsArray.toString());
			  try {
				    fulllengthController=FulllengthController.getInstance();
				  	fulllengthController.fullLengthTestQuestionSaving(originalquestionsArray);
				  } catch (ConnectionException e) {
					e.printStackTrace();
				} catch (SQLException e) { 
					e.printStackTrace();
				}				
		} catch (JSONException exp) {
			//System.err.println("\t\t\t Json Expeition is: " + exp.getMessage());
			exp.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public void nextBackQuestionFunctionality(JSONArray fulllengthquestions) {
//System.out.println("Remaining Time:"+getRemainingTime());
		try {
			int questionid = Integer.parseInt(request.getParameter("questionid"));
			LoginUserBO userbo = (LoginUserBO) sessionMap.get("user");
			JSONArray questionsData = new JSONArray();
			JSONArray originalquestionsArray = fulllengthquestions;
			JSONObject ob = new JSONObject();
			String usertime=request.getParameter("usertime").toString();
			for (int i = 0; i < originalquestionsArray.length(); i++) {
				ob = originalquestionsArray.getJSONObject(i); 
				if (ob.getInt("Question_id") == questionid) {
					/*System.out.println("the question--------------"
							+ ob.get("UserAnswer") + "Question id is"
							+ questionid + "Test_Number is "
							+ sessionMap.get("test_no")+"\tindex:"+ob.get("Question_index")+"\t Type_id:"+ob.get("TypeId"));*/
					
					JSONArray useranswer=ob.getJSONArray("UserAnswer");
					if(Integer.parseInt(ob.get("TypeId").toString())==1){  
			 			JSONArray final_answer=new JSONArray();
						if(useranswer.length()==0){
							final_answer.put("");
						}
						else
						{
							String tc_one_answer=useranswer.getString(0); 
				 			String s[]=tc_one_answer.split("_1"); 
							final_answer.put(s[0]);
						} 
			 			
			 			ob.put("UserAnswer", final_answer);
					}
					if(Integer.parseInt(ob.get("TypeId").toString())==2){
						 List<String> tc_two_answers=new ArrayList<String>(); 
						 String v[]=new String[10];
						 JSONArray final_answer=new JSONArray(); 
						for (int answer = 0; answer < useranswer.length(); answer++) { 
							 if(useranswer.getString(answer).contains("_1"))
							 {
								 v=useranswer.getString(answer).split("_1");
							 }
							 if(useranswer.getString(answer).contains("_2"))
							 {
								 v=useranswer.getString(answer).split("_2");
							 }
							tc_two_answers.add(v[0]);  
						} 
						for(String s:tc_two_answers){
							final_answer.put(s);
						}
						ob.put("UserAnswer", final_answer);
					}
					if(Integer.parseInt(ob.get("TypeId").toString())==3){
						 List<String> tc_two_answers=new ArrayList<String>(); 
						 String v[]=new String[10];
						 JSONArray final_answer=new JSONArray(); 
						for (int answer = 0; answer < useranswer.length(); answer++) { 
							 if(useranswer.getString(answer).contains("_1"))
							 {
								 v=useranswer.getString(answer).split("_1");
							 }
							 if(useranswer.getString(answer).contains("_2"))
							 {
								 v=useranswer.getString(answer).split("_2");
							 }
							 if(useranswer.getString(answer).contains("_3"))
							 {
								 v=useranswer.getString(answer).split("_3");
							 }
							tc_two_answers.add(v[0]);  
						} 
						for(String s:tc_two_answers){
							final_answer.put(s);
						}
						ob.put("UserAnswer", final_answer);
					}
					break;
				}
			} 
			ob.put("currentsectionnumber", request.getParameter("sectionNumber")!=null?request.getParameter("sectionNumber"):" ");
			ob.put("currentsectionstatus", "INCOMPLETE");
			ob.put("teststatus", "INCOMPLETE");
			ob.put("Test_no", sessionMap.get("test_no"));
			ob.put("Test_id", 2);
			ob.put("usertime", usertime);
			ob.put("remainingTime", getRemainingTime());
			// ob.put("teststart","true");
			// ob.put("teststart","true");

			if (userbo != null)
				ob.put("user_id", userbo.getUser_id());

			questionsData.put(ob);

			System.out.println("The questions data is"+ questionsData.toString());

			fulllengthController = FulllengthController.getInstance();
			fulllengthController.fullLengthTestQuestionSaving(questionsData);
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	public void quittingTest() throws JSONException, IOException{
	
				System.out.println("Question Id is\t"+Integer.parseInt(request.getParameter("questionid").toString()));
				System.out.println("index Id is\t"+Integer.parseInt(request.getParameter("index").toString()));
				System.out.println("Time Remaining\t"+(request.getParameter("time").toString()));
				System.out.println("Status:"+(request.getParameter("status").toString()));
				
				LoginUserBO userbo = (LoginUserBO) sessionMap.get("user");
				
				FullLengthTestBO bo = new FullLengthTestBO();
				bo.setUserid(userbo.getUser_id());
				bo.setQuestion_id(Integer.parseInt(request.getParameter("questionid").toString()));
				bo.setQuestionIndex(Integer.parseInt(request.getParameter("index").toString()));
				bo.setTimeRemaining(request.getParameter("time").toString());
				bo.setTest_no(Integer.parseInt(sessionMap.get("test_no").toString()));
				bo.setStatus(request.getParameter("status").toString());
				
				
				fulllengthController = FulllengthController.getInstance();
				JSONObject obj = new JSONObject();
				try {
					
					if(fulllengthController.quittingTest(bo)){
						obj.put("message", "Quit");
					}else{
						obj.put("message", "Fail");
					}
				} catch (CPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.getWriter().write(obj.toString());
	}

	 public String  sectionDifficultySum(){
		 
		 
		 
		 return SUCCESS;
	 }


	
	public void generateStartingSection() throws JSONException, IOException{
		System.out.println("\n\n\n Generate Starting Section ");
		JSONObject json = new JSONObject();
		Random r = new Random();
		char c = r.nextBoolean() ? 'Q' : 'V';
		System.out.println("Section : "+c);
		json.put("quantOrVerbal", c);
		int currentSection=1;
		
		try {
			currentSection=getSectionNumber();
		} catch (ConnectionException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("\n\n Exception is; "+e.getMessage());
		}finally{
			//json.put("currentSection",currentSection);
		}
		//System.out.println("\n\n\n\n\n Current Section Number is; "+currentSection);
		response.getWriter().write(json.toString());
	}
	private int getSectionNumber() throws JSONException, ConnectionException, DBException{
	    System.out.println("\n in getSectionNumber is : ");
		IQuestionUploadController questionUploadController=new QuestionUploadController();
		JSONObject jsonObject = new JSONObject();
		LoginUserBO userbo = (LoginUserBO) sessionMap.get("user");
		
		int currentSection=1;
		StringBuffer quiree=new StringBuffer("SELECT TEST.section_number FROM cp_fulllength_test TEST WHERE " +
				"TEST.`status` LIKE 'INCOMPLETE' AND  TEST.userId LIKE "+userbo.getUser_id());
		jsonObject.put("inputQuiree", quiree.toString());
		System.out.println("\n\n B4 Json Formate ");
		try{
		     questionUploadController.requiredTableDataInJSONFormate(jsonObject);
		}catch(Exception exp)
		{
			System.err.println("\n Exception is; "+exp.getMessage());
			exp.printStackTrace();
		}
		System.out.println("\n \n Afetr Json FOrmate ");
		
		if( jsonObject.getBoolean("isDataAvilable"))
 		{
			currentSection=jsonObject.getJSONObject("tableData").getInt("section_number");
 		}
		return currentSection;
		
		
	}
	
	
	
	public String verifyTest() throws JSONException{
		String result="";
		String questions;
		FullLengthTestBO bo1=null;
		LoginUserBO userbo = (LoginUserBO) sessionMap.get("user");
		fulllengthController = FulllengthController.getInstance();
		try {
			bo1=fulllengthController.verifyTest(userbo);
			
			if(bo1!=null){
				System.out.println("Questions pattern is \t"+bo1.getSection_questions());
				sessionMap.put("resumeBean", bo1);
				System.out.println("\nThe test number is---------------------------\t"+bo1.getTest_no());
				sessionMap.put("test_no", bo1.getTest_no());
				sessionMap.put("fullLengthRowId", bo1.getFulllenth_rowId()); 
				//resumingTest();
				result="resume";
			}else{
				result="new_test";
			}
				
			
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="error";
		}
		
		return result;
	}
	
	public void resumingTest() throws JSONException, IOException{
		List<QuestionsUploadBO> questionsList;
		LoginUserBO userbo = (LoginUserBO) sessionMap.get("user");
		FullLengthTestBO bo = (FullLengthTestBO) sessionMap.get("resumeBean");
		bo.setUserid(userbo.getUser_id());
		fulllengthController = FulllengthController.getInstance();
		questionsList=fulllengthController.resumeTest(bo);
		System.out.println("After dao backkkkk-----------------"+questionsList.size());
		JSONArray jsonArray = new JSONArray();
		JSONObject object=new JSONObject();
		for (int i = 0; i < questionsList.size(); i++) {
			try {
				QuestionsUploadBO ob=new QuestionsUploadBO();
				questionsList.get(i).setQuestion_index(i + 1);
				object = ConvertBeanToJSON.ConvertObjectToMap(questionsList.get(i));
				/*System.out.println("The object is--------------"
						+ object.get("IsFlagged"));*/
			} catch (IllegalAccessException e) {


				e.printStackTrace();
			} catch (IllegalArgumentException e) {

				e.printStackTrace();
			} catch (Exception e) {

			e.printStackTrace();
			}
			jsonArray.put(object);
		}

		String questionsLit = jsonArray.toString();
		//	object.put("questions", questionsList);
			response.setContentType("text/JSON");
			try {
				//System.out.println("\n B4 Retun JSonarra "+jsonArray.toString());
				
				response.getWriter().write(jsonArray.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}


	/**
	 * @author rrajulapati
	 * @return
	 */
	
	public String isResumeTest() {
		JSONObject jsonObject = null;
		boolean isDataAvilable=false;
		try {
			jsonObject = checkingReusmeOrNormal();
			 isDataAvilable= jsonObject
					.getBoolean("isDataAvilable");
			request.setAttribute("resume",isDataAvilable ? "resume" : "normal");
			if(isDataAvilable){
			request.setAttribute("tableData",jsonObject.get("tableData"));
			System.out.println("\n Table data is :"+jsonObject.get("tableData").toString());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("\n Checking Resume Testing  "+(isDataAvilable?"resume":"Normal"));
	              }
		return SUCCESS;
	}

	private synchronized JSONObject checkingReusmeOrNormal()
			throws JSONException, ConnectionException, DBException {
		JSONObject jsonObject = new JSONObject();
		IQuestionUploadController questionUploadController = new QuestionUploadController();
		LoginUserBO bo = (LoginUserBO) sessionMap.get("user");
		StringBuffer buffer = new StringBuffer(
				"SELECT * FROM  cp_fulllength_test f WHERE f.userId="
						+ bo.getUser_id()
						+ " AND f.`status`='INCOMPLETE' order by f.test_starting_date desc limit 1");
		jsonObject.put("inputQuiree", buffer.toString());
		questionUploadController.requiredTableDataInJSONFormate(jsonObject);
		return jsonObject;
	}
	public String startResumeTest(){
  
		
		String result="";
		String questions;
		FullLengthTestBO bo1=null;
		LoginUserBO userbo = (LoginUserBO) sessionMap.get("user");
		fulllengthController = FulllengthController.getInstance();
		try {
			bo1=fulllengthController.verifyTest(userbo);
			
			if(bo1!=null){
				System.out.println("Questions pattern is \t"+bo1.getSection_questions());
				sessionMap.put("resumeBean", bo1);
				System.out.println("\nThe test number is---------------------------\t"+bo1.getTest_no()+"Time Remaining\t"+bo1.getTimeRemaining());
				sessionMap.put("test_no", bo1.getTest_no());
				//resumingTest();
				request.setAttribute("ins", "resume"); 
			}
				
			
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="error";
		}		
		return SUCCESS;
	}
	
	

	public String fulllengthTestHistory(){
		
	//	System.out.println("Fulllength Test history action is calling ::::::::::::::::::::::::::::::::::::::::::::::::::::: ");
		
		
		
		return SUCCESS;
		
	}
	

	/*public void resumingTest() throws JSONException, IOException{
		List<QuestionsUploadBO> questionsList;
		LoginUserBO userbo = (LoginUserBO) sessionMap.get("user");
		FullLengthTestBO bo = (FullLengthTestBO) sessionMap.get("resumeBean");
		bo.setUserid(userbo.getUser_id());
		
		fulllengthController = FulllengthController.getInstance();
		questionsList=fulllengthController.resumeTest(bo);
		System.out.println("After dao backkkkk-----------------"+questionsList.size());
		JSONArray jsonArray = new JSONArray();
		JSONObject object=new JSONObject();
		for (int i = 0; i < questionsList.size(); i++) {
			try {
				QuestionsUploadBO ob=new QuestionsUploadBO();
				questionsList.get(i).setQuestion_index(i + 1);
				object = ConvertBeanToJSON.ConvertObjectToMap(questionsList.get(i));
				System.out.println("The object is--------------"
						+ object.toString());
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			} catch (IllegalArgumentException e) {

				e.printStackTrace();
			} catch (Exception e) {

			e.printStackTrace();
			}
			jsonArray.put(object);
		}

		String questionsLit = jsonArray.toString();
		//	object.put("questions", questionsList);
			response.setContentType("text/JSON");
			try {
				//System.out.println("\n B4 Retun JSonarra "+jsonArray.toString());
				
				response.getWriter().write(jsonArray.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}*/


   /***
    * 
    * @author Srikanth ganji
    * Full length Test Result History Json Data Preparing
    * 
    * 
    * 
    * 
    */

	
	public void fulllengthTestHistoryJsonData(){
		
		//System.out.println("FulllengthTest History JsonData is calling:::::::::::::::::::::::::::::::::::::::::::::::::");
		QuestionsUploadBO questionUploadBO = new QuestionsUploadBO();
		fulllengthController  = FulllengthController.getInstance();
		LoginUserBO userBO = (LoginUserBO) sessionMap.get("user");
		  // System.out.println("UserId:::::::::::::::::::::::::::::::::::::::::"+userBO.getUser_id());
		   String fulllengthTestHistoryQuery="SELECT CREAT.*,IFNULL((SELECT MAX(RES.test_date) FROM cp_test_results RES " +
		   		"WHERE RES.test_no LIKE CREAT.test_no),'') TEST_DATE,(select count(*) from cp_test_results cr " +
		   		"where cr.test_no  like CREAT.test_no and cr.section_id like 1)quant_count,(select count(*) " +
		   		"from cp_test_results cr where cr.test_no  like CREAT.test_no and cr.section_id like 1 and cr.result='CORRECT') QUANT_CORRECT_COUNT," +
		   		"IFNULL(FORMAT((((select count(*) from cp_test_results cr where cr.test_no  like CREAT.test_no and cr.section_id like 1 " +
		   		"and cr.result='CORRECT')/(select count(*) from cp_test_results cr where cr.test_no  like CREAT.test_no and " +
		   		"cr.section_id like 1))*100),1),0.0) Quant_correct_pecentage,(select count(*) from cp_test_results cr where cr.test_no " +
		   		" like CREAT.test_no and cr.section_id like 2)verbal_count,(select count(*) from cp_test_results cr where cr.test_no" +
		   		"  like CREAT.test_no and cr.section_id like 1 and cr.result='CORRECT')verbal_correct_count,IFNULL(FORMAT((((select count(*)" +
		   		"from cp_test_results cr where cr.test_no  like CREAT.test_no and cr.section_id like 2 and" +
		   		" cr.result='CORRECT')/(select count(*) from cp_test_results cr where cr.test_no  like CREAT.test_no " +
		   		"and cr.section_id like 2))*100),1),0.0) verbal_correct_pecentage,(select count(*)from cp_test_results cr where cr.test_no like CREAT.test_no and cr.section_id like 1 and cr.result='SKIPPED')Skipped_Quant_Count,(select count(*)from cp_test_results cr where cr.test_no like CREAT.test_no and cr.section_id like 2 and cr.result='SKIPPED')Skipped_Verbal_Count FROM cp_testcreation CREAT" +
		   		" WHERE CREAT.user_id ="+userBO.getUser_id()+" AND CREAT.testmaster_id LIKE 2 ORDER BY TEST_DATE DESC limit 6";
		   //System.out.println("Fulllength History query::::::::::::::::::::::::::::::::::::::::::::"+fulllengthTestHistoryQuery);
		   JSONObject jsonObjectData = new JSONObject();
		   
		   JSONArray jsonArray_Date = new JSONArray();
		   JSONArray jsonArray_Quant = new JSONArray();
		   JSONArray jsonArray_Verbal = new JSONArray();
		   JSONArray jsonArray_Quant_Skipped= new JSONArray();
		   JSONArray jsonArray_Verbal_Skipped= new JSONArray();
		   IQuestionUploadController questionUploadController=new QuestionUploadController();
		   JSONObject object=new JSONObject();
		   
		   try {
			   jsonObjectData.put("inputQuiree",fulllengthTestHistoryQuery);
			  questionUploadController.requiredTableDataInJSONFormate(jsonObjectData);
			  JSONArray tableData=jsonObjectData.getJSONArray("tableData");
				 for(int i=0;i<tableData.length();i++){
					 jsonArray_Date.put(tableData.getJSONObject(i).getString("TEST_DATE"));
					 jsonArray_Quant.put(tableData.getJSONObject(i).getString("Quant_correct_pecentage"));
					 jsonArray_Verbal.put(tableData.getJSONObject(i).getString("verbal_correct_pecentage"));
					 jsonArray_Quant_Skipped.put(tableData.getJSONObject(i).getString("Skipped_Quant_Count"));
					 jsonArray_Verbal_Skipped.put(tableData.getJSONObject(i).getString("Skipped_Verbal_Count"));
					 
					 
					 
				 }
				 object.put("dates", jsonArray_Date);
				 object.put("quant",  jsonArray_Quant);
				 object.put("verbal",  jsonArray_Verbal);
				 object.put("skipped_quant", jsonArray_Quant_Skipped);
				 object.put("skipped_verbal", jsonArray_Verbal_Skipped);
				 object.put("jsonTableData",jsonObjectData);
				 
				 
			//System.out.println("JsonObject Data in Action::::::::::::::::::::::::::::::::"+jsonObjectData);
			
			
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			response.getWriter().write( object.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/***
	 * @author Srikanth ganji
	 * Full Length Test Result Pie Chart Json data preparing
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
	public void fulllengthTestResultPieChartJsonData(){
		
		//System.out.println("FulllengthTest Pie Chart JsonData is calling:::::::::::::::::::::::::::::::::::::::::::::::::");
		QuestionsUploadBO questionUploadBO = new QuestionsUploadBO();
		fulllengthController  = FulllengthController.getInstance();
		LoginUserBO userBO = (LoginUserBO) sessionMap.get("user");
		   
	 //System.out.println("TestNumber::::::::::::::::::::::::"+request.getParameter("reviewTestNumber"));
	 if(request.getParameter("reviewTestNumber")==null)
	 {    
		// testNo=(int) sessionMap.get("testNo");
		 testno= (int) sessionMap.get("test_no");
	 }
	 else
	 {
		 // System.out.println("Pie else block:::::::::::::::::");
		testno = Integer.parseInt(request.getParameter("reviewTestNumber"));
		// sectionId =Integer.parseInt(request.getParameter("sectionid"));
		 //testno=(int) sessionMap.get("test_no");
	 }
	 
	    //System.out.println("Test number in the Pie chart:::::::::::::::::::::::::"+testno);
	   
	   int testNumber=testno;
	    //System.out.println("TestNumber:::::::::::::::::"+testNumber);
		  // System.out.println("UserId:::::::::::::::::::::::::::::::::::::::::"+userBO.getUser_id());
		/*String fulllengthTestResultPieChartDataQuery="select r.test_no,( select count(*) from cp_test_results r WHERE r.userid ="+userBO.getUser_id()+"" +
				" and r.test_no="+testNumber+" and r.result='CORRECT')as TOTAL_CORRECT,( select count(*) " +
				"from cp_test_results r WHERE r.userid="+userBO.getUser_id()+" and r.test_no="+testNumber+"  and" +
				" r.result='INCORRECT')as TOTAL_INCORRECT,( select count(*) from cp_test_results r " +
				"WHERE r.userid="+userBO.getUser_id()+"  and r.test_no="+testNumber+" and r.result='correct' " +
				"and r.section_id =1) as QUANT_CORRECT ,( select count(*) from cp_test_results r WHERE r.userid="+userBO.getUser_id()+" " +
				"and r.test_no="+testNumber+" and r.result='correct' and r.section_id =2)as VERBAL_CORRECT,( select count(*) " +
				"from cp_test_results r WHERE r.userid="+userBO.getUser_id()+" and r.result='INCORRECT' and r.test_no="+testNumber+"" +
				" and r.section_id =2)as Quant_INCORRECT,( select count(*) from cp_test_results r WHERE r.userid="+userBO.getUser_id()+" " +
				"and r.result='INCORRECT' and r.test_no="+testNumber+" and r.section_id =2)as VERBAL_INCORRECT," +
				"IFNULL(FORMAT((((select count(*)from cp_test_results cr where cr.userid="+userBO.getUser_id()+" and " +
				"cr.test_no="+testNumber+" and cr.section_id like 1 and cr.result='CORRECT')/" +
				"(select count(*) from cp_test_results cr where cr.userid="+userBO.getUser_id()+" and cr.test_no="+testNumber+" " +
				"and r.section_id like 1))*100),1),0.00) Quant_correct_pecentage," +
				"IFNULL(FORMAT((((select count(*)from cp_test_results cr where cr.userid="+userBO.getUser_id()+" " +
				"and cr.test_no="+testNumber+" and cr.section_id like 2 and cr.result='CORRECT')/" +
				"(select count(*) from cp_test_results cr where r.userid="+userBO.getUser_id()+" and r.test_no="+testNumber+" " +
				"and cr.section_id like 2))*100),1),0.00) verbal_correct_pecentage," +
				"IFNULL(FORMAT((((select count(*)from cp_test_results cr where r.userid="+userBO.getUser_id()+"" +
				" and r.test_no="+testNumber+" and cr.section_id like 1 and cr.result='INCORRECT')/" +
				"(select count(*) from cp_test_results cr where cr.userid="+userBO.getUser_id()+" and cr.test_no="+testNumber+" " +
				"and r.section_id like 1))*100),1),0.00) QUANT_INCORRECT_PERCENTAGE," +
				"IFNULL(FORMAT((((select count(*)from cp_test_results cr where cr.userid="+userBO.getUser_id()+"" +
				" and cr.test_no="+testNumber+" and cr.section_id like 2 and cr.result='INCORRECT')/" +
				"(select count(*) from cp_test_results cr where cr.userid="+userBO.getUser_id()+" and cr.test_no="+testNumber+" " +
				"and cr.section_id like 2))*100),1),0.00) VERBAL_INCORRECT_PERCENTAGE," +
				"IFNULL(FORMAT((((select count(*)from cp_test_results cr where cr.userid="+userBO.getUser_id()+"" +
				" and cr.test_no="+testNumber+"  and cr.result='CORRECT')/" +
				"(select count(*) from cp_test_results cr where cr.userid="+userBO.getUser_id()+" " +
				"and cr.test_no="+testNumber+"))*100),1),0.00) TOTAL_CORRECT_PERCENTAGE," +
				"IFNULL(FORMAT((((select count(*)from cp_test_results cr where cr.userid="+userBO.getUser_id()+" " +
				"and cr.test_no="+testNumber+" and cr.result='INCORRECT')/" +
				"(select count(*) from cp_test_results cr where cr.userid="+userBO.getUser_id()+" " +
				"and cr.test_no="+testNumber+"))*100),1),0.00) TOTAL_INCORRECT_PERCENTAGE," +
				"IFNULL(FORMAT((((select count(*)from cp_test_results cr where cr.userid="+userBO.getUser_id()+" " +
				"and cr.test_no="+testNumber+" and cr.result='SKIPPED')/" +
				"(select count(*) from cp_test_results cr where cr.userid="+userBO.getUser_id()+"" +
				" and cr.test_no="+testNumber+"))*100),1),0.00) TOTAL_SKIPPED_PERCENTAGE," +
				"IFNULL(FORMAT((((select count(*)from cp_test_results cr where cr.userid="+userBO.getUser_id()+" and" +
				" cr.test_no="+testNumber+" and cr.result='SKIPPED' and cr.section_id=1)/(select count(*) from cp_test_results cr" +
				" where cr.userid="+userBO.getUser_id()+" and cr.test_no="+testNumber+" and cr.section_id=1))*100),1),0.00) " +
				"as TOTAL_QUANT_SKIPPED_PERCENTAGE,IFNULL(FORMAT((((select count(*)from cp_test_results cr" +
				" where cr.userid="+userBO.getUser_id()+" and cr.test_no="+testNumber+" and cr.result='SKIPPED' and cr.section_id=2)/" +
				"(select count(*) from cp_test_results cr where cr.userid="+userBO.getUser_id()+" and cr.test_no="+testNumber+"" +
				" and cr.section_id=2))*100),1),0.00) as TOTAL_VERBAL_SKIPPED_PERCENTAGE from cp_test_results r " +
				" where r.userid="+userBO.getUser_id()+" and r.test_no="+testNumber+" and " +
				"r.test_master_id=2 order by r.test_date desc limit 1";*/
	   
	   String fulllengthTestResultPieChartDataQuery="select r.test_no,( select count(*) from cp_test_results r WHERE r.userid ="+userBO.getUser_id()+"" +
		" and r.test_no="+testNumber+" and r.result='CORRECT')as TOTAL_CORRECT,( select count(*) " +
		"from cp_test_results r WHERE r.userid="+userBO.getUser_id()+" and r.test_no="+testNumber+"  and" +
		" r.result='INCORRECT')as TOTAL_INCORRECT,( select count(*) from cp_test_results r " +
		"WHERE r.userid="+userBO.getUser_id()+"  and r.test_no="+testNumber+" and r.result='correct' " +
		"and r.section_id =1) as QUANT_CORRECT ,( select count(*) from cp_test_results r WHERE r.userid="+userBO.getUser_id()+" " +
		"and r.test_no="+testNumber+" and r.result='correct' and r.section_id =2)as VERBAL_CORRECT,( select count(*) " +
		"from cp_test_results r WHERE r.userid="+userBO.getUser_id()+" and r.result='INCORRECT' and r.test_no="+testNumber+"" +
		" and r.section_id =2)as Quant_INCORRECT,( select count(*) from cp_test_results r WHERE r.userid="+userBO.getUser_id()+" " +
		"and r.result='INCORRECT' and r.test_no="+testNumber+" and r.section_id =2)as VERBAL_INCORRECT," +
		"IFNULL(FORMAT((((select count(*)from cp_test_results cr where cr.userid="+userBO.getUser_id()+" and " +
		"cr.test_no="+testNumber+" and cr.section_id like 1 and cr.result='CORRECT')/" +
		"(select count(*) from cp_test_results cr where cr.userid="+userBO.getUser_id()+" and cr.test_no="+testNumber+" " +
		"))*100),1),0.00) Quant_correct_pecentage," +
		"IFNULL(FORMAT((((select count(*)from cp_test_results cr where cr.userid="+userBO.getUser_id()+" " +
		"and cr.test_no="+testNumber+" and cr.section_id like 2 and cr.result='CORRECT')/" +
		"(select count(*) from cp_test_results cr where r.userid="+userBO.getUser_id()+" and r.test_no="+testNumber+" " +
		"))*100),1),0.00) verbal_correct_pecentage," +
		"IFNULL(FORMAT((((select count(*)from cp_test_results cr where r.userid="+userBO.getUser_id()+"" +
		" and r.test_no="+testNumber+" and cr.section_id like 1 and cr.result='INCORRECT')/" +
		"(select count(*) from cp_test_results cr where cr.userid="+userBO.getUser_id()+" and cr.test_no="+testNumber+" " +
		"))*100),1),0.00) QUANT_INCORRECT_PERCENTAGE," +
		"IFNULL(FORMAT((((select count(*)from cp_test_results cr where cr.userid="+userBO.getUser_id()+"" +
		" and cr.test_no="+testNumber+" and cr.section_id like 2 and cr.result='INCORRECT')/" +
		"(select count(*) from cp_test_results cr where cr.userid="+userBO.getUser_id()+" and cr.test_no="+testNumber+" " +
		"))*100),1),0.00) VERBAL_INCORRECT_PERCENTAGE," +
		"IFNULL(FORMAT((((select count(*)from cp_test_results cr where cr.userid="+userBO.getUser_id()+"" +
		" and cr.test_no="+testNumber+"  and cr.result='CORRECT')/" +
		"(select count(*) from cp_test_results cr where cr.userid="+userBO.getUser_id()+" " +
		"and cr.test_no="+testNumber+"))*100),1),0.00) TOTAL_CORRECT_PERCENTAGE," +
		"IFNULL(FORMAT((((select count(*)from cp_test_results cr where cr.userid="+userBO.getUser_id()+" " +
		"and cr.test_no="+testNumber+" and cr.result='INCORRECT')/" +
		"(select count(*) from cp_test_results cr where cr.userid="+userBO.getUser_id()+" " +
		"and cr.test_no="+testNumber+"))*100),1),0.00) TOTAL_INCORRECT_PERCENTAGE," +
		"IFNULL(FORMAT((((select count(*)from cp_test_results cr where cr.userid="+userBO.getUser_id()+" " +
		"and cr.test_no="+testNumber+" and cr.result='SKIPPED')/" +
		"(select count(*) from cp_test_results cr where cr.userid="+userBO.getUser_id()+"" +
		" and cr.test_no="+testNumber+"))*100),1),0.00) TOTAL_SKIPPED_PERCENTAGE," +
		"IFNULL(FORMAT((((select count(*)from cp_test_results cr where cr.userid="+userBO.getUser_id()+" and" +
		" cr.test_no="+testNumber+" and cr.result='SKIPPED' and cr.section_id=1)/(select count(*) from cp_test_results cr" +
		" where cr.userid="+userBO.getUser_id()+" and cr.test_no="+testNumber+"))*100),1),0.00) " +
		"as TOTAL_QUANT_SKIPPED_PERCENTAGE,IFNULL(FORMAT((((select count(*)from cp_test_results cr" +
		" where cr.userid="+userBO.getUser_id()+" and cr.test_no="+testNumber+" and cr.result='SKIPPED' and cr.section_id=2)/" +
		"(select count(*) from cp_test_results cr where cr.userid="+userBO.getUser_id()+" and cr.test_no="+testNumber+"" +
		"))*100),1),0.00) as TOTAL_VERBAL_SKIPPED_PERCENTAGE from cp_test_results r " +
		" where r.userid="+userBO.getUser_id()+" and r.test_no="+testNumber+" and " +
		"r.test_master_id=2 order by r.test_date desc limit 1";
		
		       //System.out.println("Fulllength Test Result Pie Query:::::::::::::::::::::::::::::"+fulllengthTestResultPieChartDataQuery);
		       JSONObject jsonObjectData = new JSONObject();
		       JSONArray jsonArray_Total_Correct= new JSONArray();
		       JSONArray jsonArray_Total_INCorrect= new JSONArray();
		       JSONArray jsonArray_Quant_Correct= new JSONArray();
		       JSONArray jsonArray_Verbal_Correct= new JSONArray();
		       JSONArray jsonArray_Quant_INCorrect= new JSONArray();
		       JSONArray jsonArray_Verbal_INCorrect= new JSONArray();
		       JSONArray jsonArray_total_skipped= new JSONArray();
		       JSONArray jsonArray_quant_skipped= new JSONArray();
		       JSONArray jsonArray_verbal_skipped= new JSONArray();
		       
		       
		       
		       
		       IQuestionUploadController questionUploadController=new QuestionUploadController();
			   JSONObject object=new JSONObject();
			   try {
				jsonObjectData.put("inputQuiree",fulllengthTestResultPieChartDataQuery);
				  questionUploadController.requiredTableDataInJSONFormate(jsonObjectData);
				  JSONArray tableData=jsonObjectData.getJSONArray("tableData");
				  System.out.println("Fullength test Query:::::::::::::::::::::::"+fulllengthTestResultPieChartDataQuery);
				  System.out.println("Jsosn Table Data::::::::::::::::::::::"+tableData);
				    
				  for(int i=0;i<tableData.length();i++){
					  
					  jsonArray_Total_Correct.put(tableData.getJSONObject(i).getString("TOTAL_CORRECT_PERCENTAGE"));
					  jsonArray_Total_INCorrect.put(tableData.getJSONObject(i).getString("TOTAL_INCORRECT_PERCENTAGE"));
					  jsonArray_Quant_Correct.put(tableData.getJSONObject(i).getString("Quant_correct_pecentage"));
					  jsonArray_Verbal_Correct.put(tableData.getJSONObject(i).getString("verbal_correct_pecentage"));
					  jsonArray_Quant_INCorrect.put(tableData.getJSONObject(i).getString("QUANT_INCORRECT_PERCENTAGE"));
					  jsonArray_Verbal_INCorrect.put(tableData.getJSONObject(i).getString("VERBAL_INCORRECT_PERCENTAGE"));
					  jsonArray_total_skipped.put(tableData.getJSONObject(i).getString("TOTAL_SKIPPED_PERCENTAGE"));
					  jsonArray_quant_skipped.put(tableData.getJSONObject(i).getString("TOTAL_QUANT_SKIPPED_PERCENTAGE"));
					  jsonArray_verbal_skipped.put(tableData.getJSONObject(i).getString("TOTAL_VERBAL_SKIPPED_PERCENTAGE"));
					  
					  
				  }
				  
				  object.put("toatal_correct_percentage",jsonArray_Total_Correct );
				  object.put("toatal_Incorrect_percentage",jsonArray_Total_INCorrect);
				  object.put("quant_correct_percentage", jsonArray_Quant_Correct);
				  object.put("verbal_correct_percentage",jsonArray_Verbal_Correct);
				  object.put("quant_incorrect_percentage",jsonArray_Quant_INCorrect );
				  object.put("verbal_incorrect_percentage",jsonArray_Verbal_INCorrect);
				  object.put("skipped_percentage",jsonArray_total_skipped);
				  object.put("quant_skipped_percentage",jsonArray_quant_skipped);
				  object.put("verbal_skipped_percentage",jsonArray_verbal_skipped);
				  
				  
				  
			} catch (JSONException | ConnectionException | DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				try {
					response.getWriter().write( object.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
	}
	

	/****
	 * @author Srikanth ganji
	 * Full Length Test Results Scatter Booble Chart Json Data is preparing
	 * 
	 * 
	 */
	public void fullLengthTestResultScatterBoobleChadrtJsonData(){
		
		
		System.out.println("FulllengthTest Pie Chart JsonData is calling:::::::::::::::::::::::::::::::::::::::::::::::::");
		QuestionsUploadBO questionUploadBO = new QuestionsUploadBO();
		fulllengthController  = FulllengthController.getInstance();
		LoginUserBO userBO = (LoginUserBO) sessionMap.get("user");
		   
	// System.out.println("TestNumber::::::::::::::::::::::::"+sessionMap.get("test_no"));
	 
		
		 if(request.getParameter("reviewTestNumber")==null)
		 {    
			
			 testno= (int) sessionMap.get("test_no");
		 }
		 else
		 {
			
			testno = Integer.parseInt(request.getParameter("reviewTestNumber"));
			
		 }
		
	
	  //int testNumber=(int) sessionMap.get("test_no");
	    //System.out.println("TestNumber:::::::::::::::::"+testNumber);
		   System.out.println("UserId:::::::::::::::::::::::::::::::::::::::::"+userBO.getUser_id());
		   //DATE_FORMAT(IFNULL(crt.user_time,'00:00:00'),'%h.%i')
		 /*  String fulllengthTestResultScatterBoobleChartDataQuire="select crt.test_no,IFNULL(crt.user_time,'00:00:00') as USER_TIME,crt.userid,crt.test_date," +
		   		"(select count(*)from cp_test_results r where r.test_no="+testno+" and r.userid="+userBO.getUser_id()+" and r.result='CORRECT'" +
		   		" and r.user_time=crt.user_time and r.section_id=1)as Quant_CORRECT_ANSWERS," +
		   		"IFNULL((select r.user_time from cp_test_results r where r.userid!="+userBO.getUser_id()+" and r.result='CORRECT' " +
		   		"and r.section_id=1 and r.user_time=crt.user_time),0)as OTHER_USERS_QUANT_CORRECT_ANSWERS_TIME," +
		   		"(select count(*)from cp_test_results r where r.test_no="+testno+" and r.userid="+userBO.getUser_id()+" " +
		   		"and r.result='INCORRECT' and r.user_time=crt.user_time and r.section_id=1)as Quant_INCORRECT_ANSWERS," +
		   		"IFNULL((select r.user_time from cp_test_results r where r.userid!="+userBO.getUser_id()+" and r.result='INCORRECT' " +
		   		"and r.section_id=1 and r.user_time=crt.user_time),0)as OTHER_USERS_QUANT_INCORRECT_ANSWERS_TIME," +
		   		"(select count(*)from cp_test_results r where r.test_no="+testno+" and r.userid="+userBO.getUser_id()+" and r.result='CORRECT' " +
		   		"and r.user_time=crt.user_time and r.section_id=2)as VERBAL_CORRECT_ANSWERS," +
		   		"IFNULL((select r.user_time from cp_test_results r where r.userid!="+userBO.getUser_id()+" and r.result='CORRECT' " +
		   		"and r.section_id=2 and r.user_time=crt.user_time),0)as OTHER_USERS_VERBAL_CORRECT_ANSWERS_TIME," +
		   		"(select count(*)from cp_test_results r where r.test_no="+testno+" and r.userid="+userBO.getUser_id()+" and r.result='INCORRECT' " +
		   		"and r.user_time=crt.user_time and r.section_id=2)as VERBAL_INCORRECT_ANSWERS," +
		   		"IFNULL((select r.user_time from cp_test_results r where r.userid!="+userBO.getUser_id()+" and r.result='INCORRECT' " +
		   		"and r.section_id=2 and r.user_time=crt.user_time),0)as OTHER_USERS_VERBAL_INCORRECT_ANSWERS_TIME," +
		   		"IFNULL((select count(*) from cp_test_results r where r.userid!="+userBO.getUser_id()+"" +
		   		" and r.result='CORRECT' and r.section_id=1),0)as OTHER_USERS_QUANT_CORRECT_ANSWERS," +
		   		"IFNULL((select count(*)from cp_test_results r where r.userid!="+userBO.getUser_id()+" and r.result='INCORRECT' " +
		   		"and r.section_id=1),0)as OTHER_USERS_QUANT_INCORRECT_ANSWERS,IFNULL((select count(*) " +
		   		"from cp_test_results r where r.userid!="+userBO.getUser_id()+" and r.result='CORRECT' and r.section_id=2),0)as " +
		   		"OTHER_USERS_VERBAL_CORRECT_ANSWERS,IFNULL((select count(*) from cp_test_results r where r.userid!="+userBO.getUser_id()+"" +
		   		" and r.result='INCORRECT'),0)as OTHER_USERS_QUANT_INCORRECT_ANSWERS from cp_test_results crt " +
		   		"where crt.userid="+userBO.getUser_id()+" and crt.test_no="+testno+" and crt.test_master_id=2 group by crt.user_time" +
		   		" order by crt.user_time desc";
		   */
		 
	     
		   
		  /* String fullLengthTestBoobleChart="SELECT tr.question_id,tr.result,TIME_TO_SEC(tr.user_time) as USER_TIME," +
		   		"tr.section_id,sec.section_name,(SELECT COUNT(*) FROM cp_test_results tr1" +
		   		" WHERE tr.question_id=tr1.question_id AND tr1.userid!="+userBO.getUser_id()+" AND tr1.result ) AS OTHER_USER_COUNT," +
		   		"(SELECT COUNT(*) FROM cp_test_results tr1 WHERE tr.question_id=tr1.question_id AND tr1.userid!="+userBO.getUser_id()+" " +
		   		"AND tr1.result LIKE 'CORRECT') AS OTHER_CORRECT,(SELECT COUNT(*) FROM cp_test_results tr1" +
		   		" WHERE tr.question_id=tr1.question_id AND tr1.userid!="+userBO.getUser_id()+" AND tr1.result LIKE 'INCORRECT')" +
		   		" AS OTHER_INCORRECT,(SELECT COUNT(*) FROM cp_test_results tr1 WHERE tr.question_id=tr1.question_id " +
		   		"AND tr1.userid="+userBO.getUser_id()+" AND tr1.result LIKE 'INCORRECT' and tr.user_time=tr1.user_time and tr1.test_no="+testno+") AS MY_INCORRECT," +
		   		"(SELECT COUNT(*) FROM cp_test_results tr1 WHERE tr.question_id=tr1.question_id AND tr1.userid="+userBO.getUser_id()+" " +
		   		"AND tr1.result LIKE 'CORRECT' and tr.user_time=tr1.user_time and tr1.test_no="+testno+") AS MY_CORRECT FROM cp_test_results tr," +
		   		"cp_sections sec WHERE tr.userid="+userBO.getUser_id()+" AND tr.test_no="+testno+" and tr.section_id=sec.section_id";
		   
		   */
		   
		  String fullLengthTestBobbleChart= "SELECT tr.question_id,tr.result,TIME_TO_SEC(IFNULL(tr.user_time,0)) " +
		  		"as USER_TIME,tr.section_id,sec.section_name,(SELECT COUNT(*) FROM cp_test_results tr1 " +
		  		"WHERE tr.question_id=tr1.question_id AND tr1.userid!="+userBO.getUser_id()+" AND tr1.result )" +
		  		" AS OTHER_USER_COUNT,(SELECT COUNT(*) FROM cp_test_results tr1 WHERE tr.question_id=tr1.question_id" +
		  		" AND tr1.userid!="+userBO.getUser_id()+" AND tr1.result LIKE 'CORRECT') AS OTHER_CORRECT,(SELECT COUNT(*) " +
		  		"FROM cp_test_results tr1 WHERE tr.question_id=tr1.question_id AND tr1.userid!="+userBO.getUser_id()+"" +
		  		" AND tr1.result LIKE 'INCORRECT') AS OTHER_INCORRECT,IFNULL(FORMAT(((SELECT COUNT(*) " +
		  		"FROM cp_test_results tr1 WHERE tr.question_id=tr1.question_id AND tr1.userid!="+userBO.getUser_id()+"" +
		  		" AND tr1.result LIKE 'CORRECT')/(SELECT COUNT(*) FROM cp_test_results tr1 WHERE" +
		  		" tr.question_id=tr1.question_id AND tr1.userid!="+userBO.getUser_id()+" AND tr1.result ))*100,1),0.0)as" +
		  		" OTHER_USER_CORRECT_PERCENTAGE,(SELECT COUNT(*) FROM cp_test_results tr1 WHERE " +
		  		"tr.question_id=tr1.question_id AND tr1.userid="+userBO.getUser_id()+" AND tr1.result LIKE 'INCORRECT' " +
		  		"and tr1.test_no="+testno+" and tr1.section_id=1) AS USER_QUANT_INCORRECT,(SELECT COUNT(*)" +
		  		" FROM cp_test_results tr1 WHERE tr.question_id=tr1.question_id AND tr1.userid="+userBO.getUser_id()+" " +
		  		"AND tr1.result LIKE 'CORRECT' and tr1.test_no="+testno+" and tr1.section_id=1) " +
		  		"AS USER_QUANT_CORRECT,(SELECT COUNT(*) FROM cp_test_results tr1 WHERE " +
		  		"tr.question_id=tr1.question_id AND tr1.userid="+userBO.getUser_id()+" AND tr1.result LIKE 'CORRECT'" +
		  		" and tr1.test_no="+testno+" and tr1.section_id=2) AS USER_VERBAL_CORRECT,(SELECT COUNT(*) " +
		  		"FROM cp_test_results tr1 WHERE tr.question_id=tr1.question_id AND tr1.userid="+userBO.getUser_id()+" AND" +
		  		" tr1.result LIKE 'INCORRECT'  and tr1.test_no="+testno+" and tr1.section_id=2) " +
		  		"AS USER_VERBAL_INCORRECT FROM cp_test_results tr,cp_sections sec WHERE tr.userid="+userBO.getUser_id()+" AND" +
		  		" tr.test_no="+testno+" and tr.section_id=sec.section_id and tr.test_master_id=2";
		   
		   
		   System.out.println("Booble Pie chart Query:::::::::::::::::::::::::::::::::::::::::"+fullLengthTestBobbleChart);
		   JSONObject jsonObjectData = new JSONObject();
	       JSONArray jsonArray_Correct= new JSONArray();
	       JSONArray jsonArray_INCorrect= new JSONArray();
	      // JSONArray jsonArray_Verbal_Correct= new JSONArray();
	      // JSONArray jsonArray_Verbal_INCorrect= new JSONArray();
	       JSONArray jsonArray_Time_Data= new JSONArray();
	       
	       IQuestionUploadController questionUploadController=new QuestionUploadController();
		   JSONObject object=new JSONObject();
		   try {
			jsonObjectData.put("inputQuiree",fullLengthTestBobbleChart);
			  questionUploadController.requiredTableDataInJSONFormate(jsonObjectData);
			  JSONArray tableData=jsonObjectData.getJSONArray("tableData");
			  System.out.println("Jsosn Table Data ::::::::::::::::::::::"+tableData);
			
			    JSONArray parent_Quant_Correct=new JSONArray();
			    JSONArray parent_Quant_INCorrect=new JSONArray();
			    JSONArray parent_Verbal_Correct=new JSONArray();
			    JSONArray parent_Verbal_INCorrect=new JSONArray();
			    JSONArray childArray=new JSONArray();
			   System.out.println("\n Length is: "+tableData.length());
			   //int u=0,t=0,r=0,e=0;
			  for(int i=0;i<tableData.length();i++){
				  try{
					  /*JSONArray a1=new JSONArray();
					  a1.put(tableData.getJSONObject(i).get("USER_TIME"));
					  a1.put(tableData.getJSONObject(i).get("OTHER_CORRECT"));
					  parent_Quant_Correct.put(a1);
					  JSONArray a2=new JSONArray();
					  a2.put(tableData.getJSONObject(i).get("USER_TIME"));
					  a2.put(tableData.getJSONObject(i).get("OTHER_INCORRECT"));
					  parent_Quant_INCorrect.put(a2);*/
					 
				 // System.out.println("\n\n in index is  : "+i+"\t\t Quan Incorrect is :  Answers is: "+tableData.getJSONObject(i).getInt("Quant_INCORRECT_ANSWERS"));
					  System.out.println("Table Daa::::::::::::::::::::::::::::::"+tableData.getJSONObject(i).toString().indexOf("USER_QUANT_CORRECT"));
				     /* if(tableData.getJSONObject(i).toString().indexOf("USER_QUANT_CORRECT")>=0){
					  parent_Quant_Correct.put("["+tableData.getJSONObject(i).get("USER_TIME")).put(tableData.getJSONObject(i).get("OTHER_USER_CORRECT_PERCENTAGE")+"]");
				       }if(tableData.getJSONObject(i).toString().indexOf("USER_QUANT_INCORRECT")>=0){
					       parent_Quant_INCorrect.put("["+tableData.getJSONObject(i).get("USER_TIME")).put(tableData.getJSONObject(i).get("OTHER_USER_CORRECT_PERCENTAGE")+"]");
				       }if(tableData.getJSONObject(i).toString().indexOf("USER_VERBAL_CORRECT")>=0){
					       parent_Verbal_Correct.put("["+tableData.getJSONObject(i).get("USER_TIME")).put(tableData.getJSONObject(i).get("OTHER_USER_CORRECT_PERCENTAGE")+"]");
				       }if(tableData.getJSONObject(i).toString().indexOf("USER_VERBAL_INCORRECT")>=0){
					       parent_Verbal_INCorrect.put("["+tableData.getJSONObject(i).get("USER_TIME")).put(tableData.getJSONObject(i).get("OTHER_USER_CORRECT_PERCENTAGE")+"]");
				       }*/
				       if(tableData.getJSONObject(i).get("result").toString().equalsIgnoreCase("CORRECT")&&tableData.getJSONObject(i).get("section_name").toString().equalsIgnoreCase("Verbal"))
				       {
				    	 //   u++;
				    	   parent_Verbal_Correct.put("["+tableData.getJSONObject(i).get("USER_TIME")).put(tableData.getJSONObject(i).get("OTHER_USER_CORRECT_PERCENTAGE")+"]");
				       }
				       if(tableData.getJSONObject(i).get("result").toString().equalsIgnoreCase("INCORRECT")&&tableData.getJSONObject(i).get("section_name").toString().equalsIgnoreCase("Verbal"))
				       {
				    	  // t++;
				    	   parent_Verbal_INCorrect.put("["+tableData.getJSONObject(i).get("USER_TIME")).put(tableData.getJSONObject(i).get("OTHER_USER_CORRECT_PERCENTAGE")+"]");
				       }if(tableData.getJSONObject(i).get("result").toString().equalsIgnoreCase("CORRECT")&&tableData.getJSONObject(i).get("section_name").toString().equalsIgnoreCase("Quantitative"))
				       {
				    	    //r++;
				    	   parent_Quant_Correct.put("["+tableData.getJSONObject(i).get("USER_TIME")).put(tableData.getJSONObject(i).get("OTHER_USER_CORRECT_PERCENTAGE")+"]");
				       } if(tableData.getJSONObject(i).get("result").toString().equalsIgnoreCase("INCORRECT")&&tableData.getJSONObject(i).get("section_name").toString().equalsIgnoreCase("Quantitative"))
				       {
				    	   // e++;
				    	   parent_Quant_INCorrect.put("["+tableData.getJSONObject(i).get("USER_TIME")).put(tableData.getJSONObject(i).get("OTHER_USER_CORRECT_PERCENTAGE")+"]");
				       }
				       
				       	
				       
				       
				       
				      /* parent_Quant_Correct.put("["+tableData.getJSONObject(i).get("USER_TIME")).put(tableData.getJSONObject(i).getInt("OTHER_USER_CORRECT_PERCENTAGE")+"]");
				       parent_Quant_INCorrect.put("["+tableData.getJSONObject(i).get("USER_TIME")).put(tableData.getJSONObject(i).getInt("OTHER_USER_CORRECT_PERCENTAGE")+"]");
				       parent_Verbal_Correct.put("["+tableData.getJSONObject(i).get("USER_TIME")).put(tableData.getJSONObject(i).getInt("OTHER_USER_CORRECT_PERCENTAGE")+"]");
				       parent_Verbal_INCorrect.put("["+tableData.getJSONObject(i).get("USER_TIME")).put(tableData.getJSONObject(i).getInt("OTHER_USER_CORRECT_PERCENTAGE")+"]");*/
				       // parent_Quant_INCorrect.put("["+tableData.getJSONObject(i).get("USER_TIME").toString().substring(3,8).replace(":",".")).put(tableData.getJSONObject(i).getInt("Quant_INCORRECT_ANSWERS")+"]");
				  //parent_Verbal_Correct.put("["+tableData.getJSONObject(i).get("USER_TIME").toString().substring(3,8).replace(":",".")).put(tableData.getJSONObject(i).getInt("VERBAL_CORRECT_ANSWERS")+"]");
				  //parent_Verbal_INCorrect.put("["+tableData.getJSONObject(i).get("USER_TIME").toString().substring(3,8).replace(":",".")).put(tableData.getJSONObject(i).getInt("VERBAL_INCORRECT_ANSWERS")+"]");
				  }catch(JSONException exception)
				  {
					  System.err.println("\n\n Error Reason is: "+exception.getMessage());
					  exception.printStackTrace();
				  }
					
			  }
			  /*System.out.println("Checking Data::::::::::::"+u);
			  System.out.println("Checking Data::::::::::::"+t);
			  System.out.println("Checking Data::::::::::::"+r);
			  System.out.println("Checking Data::::::::::::"+e);*/
			  
			  System.out.println("Parent_Quant_Correct::::::::::::::::::::::@@@@@@@@###:::::"+parent_Quant_Correct.toString().replace("\"", ""));
			  System.out.println("Parent_Quant_INCorrect:::::::::::::::@@@@@@@@@@@#####:::::::::::"+parent_Quant_INCorrect.toString().replace("\"", ""));
			  System.out.println("Parent_Verbal_Correct:::::::::::::@@@@@@@@##########::::::::::::"+parent_Verbal_Correct.toString().replace("\"", ""));
			  System.out.println("Parent_Verbal_INCorrect::::::::::::::@@@@@@@@@#############::::::::::::::::"+parent_Verbal_INCorrect.toString().replace("\"", ""));
			 
			  
			 System.out.println("\n\n\n Quant correct pecentage is are closed::::::::::::::::::::::");
			  
			  //object.put("toatal_correct_percentage",jsonArray_Quant_Correct);
			  //object.put("toatal_Incorrect_percentage",jsonArray_Quant_INCorrect);
			   //object.put("quant_correct_percentage", jsonArray_Quant_Correct);
			  //object.put("verbal_correct_percentage",jsonArray_Verbal_Correct);
			  //object.put("quant_incorrect_percentage",jsonArray_Quant_INCorrect);
			  //object.put("verbal_incorrect_percentage",jsonArray_Verbal_INCorrect);
			  //object.put("time",jsonArray_Time_Data);
			 // object.put("quant_correct_percentage", parent_Quant_Correct.toString().replace("\"", ""));
			  //object.put("quant_incorrect_percentage", parent_Quant_INCorrect.toString().replace("\"", ""));
			  //object.put("verbal_correct_percentage", parent_Verbal_Correct.toString().replace("\"", ""));
			  //object.put("verbal_incorrect_percentage", parent_Verbal_INCorrect.toString().replace("\"", ""));
			 object.put("quant_correct", parent_Quant_Correct);
			 object.put("quant_Incorrect",parent_Quant_INCorrect);
			 object.put("verbal_correct",parent_Verbal_Correct);
			 object.put("verbal_Incorrect",parent_Verbal_INCorrect);
			  
			  
		} catch (JSONException | ConnectionException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
			
			try {
				response.getWriter().write( object.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	

		
		
	}
	
	
	
	
	
	/*
	 * public void exitSectionFunctionality() throws JSONException,
	 * ConnectionException, SQLException {
	 * 
	 * 
	 * }
	 */



	/**
	 * @satya
	 * 
	 * @return
	 */
	public String fullLengthResultPage(){	
		
		
		System.out.println("Full lleng Result page is calling ::::::::::::::::::::::::::::::::::::::::@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		
		LoginUserBO userbo = (LoginUserBO) sessionMap.get("user");
		String result="success";

		/*System.out.println("In result Pge Review Test Nuber::::::::::::::::"+request.getParameter("reviewTestNumber").toString());
		System.out.println("In restult page Sesssion Test Nuber:::::::::::"+sessionMap.get("test_no"));*/
	

	fullResultsBO = new FullResultsBO();
	
		int sectionId=0;
		
		// if(sessionMap.get("test_no")==null)
		if(request.getParameter("reviewTestNumber")==null)
		 {    
			// testNo=(int) sessionMap.get("testNo");
			 testno= (int) sessionMap.get("test_no");
		 }
		 else
		 {
			testno = Integer.parseInt(request.getParameter("reviewTestNumber").toString());
			// sectionId =Integer.parseInt(request.getParameter("sectionid"));
			 //testno=(int) sessionMap.get("test_no");
		 }
		try {			
			//System.out.println("im from Resultpage testNumber:::::::::::::"+request.getParameter("testno"));			
			fulllengthController = FulllengthController.getInstance();
			System.out.println(""+fullResultsBO);
			fullResultsBO.setUserid(userbo.getUser_id());			
			fullResultsBO.setTest_no(testno);			
			this.setFullResultsBO(fulllengthController.resultPage(fullResultsBO));
			//System.out.println("** HTML** RESULT:"+fullResultsBO.getResult_page()); 
			//scorePrediction=fulllengthController.getPredictedScoreDetails(userbo.getUser_id(),125963, 2);
			scorePrediction=fulllengthController.getPredictedScoreDetails(userbo.getUser_id(),testno, 2);
			ScoreBO score = new ScoreBO();
			score = DifficultyLevelScore.propertiesValues(); 
			for(QuestionsUploadBO scoreBo:scorePrediction){ 
				if(scoreBo.getSection_id()==1){
					if(scoreBo.getResult().equals("CORRECT")){ 
						if(scoreBo.getDifficulty_id()==1){
							quantScore+=(scoreBo.getDifficulty_id()*Double.parseDouble(score.getLevel1()));
						}
						if(scoreBo.getDifficulty_id()==2){
							quantScore+=(scoreBo.getDifficulty_id()*Double.parseDouble(score.getLevel2()));
						}
						if(scoreBo.getDifficulty_id()==3){
							quantScore+=(scoreBo.getDifficulty_id()*Double.parseDouble(score.getLevel3()));
						}
						if(scoreBo.getDifficulty_id()==4){
							quantScore+=(scoreBo.getDifficulty_id()*Double.parseDouble(score.getLevel4()));
						}
						if(scoreBo.getDifficulty_id()==5){
							quantScore+=(scoreBo.getDifficulty_id()*Double.parseDouble(score.getLevel5()));
						}
					} 
				}
				if(scoreBo.getSection_id()==2){
					if(scoreBo.getResult().equals("CORRECT")){
						if(scoreBo.getDifficulty_id()==1){
							verbalScore+=(scoreBo.getDifficulty_id()*Double.parseDouble(score.getLevel1()));
							}
							if(scoreBo.getDifficulty_id()==2){
								verbalScore+=(scoreBo.getDifficulty_id()*Double.parseDouble(score.getLevel2()));
							}
							if(scoreBo.getDifficulty_id()==3){
								verbalScore+=(scoreBo.getDifficulty_id()*Double.parseDouble(score.getLevel3()));
							}
							if(scoreBo.getDifficulty_id()==4){
								verbalScore+=(scoreBo.getDifficulty_id()*Double.parseDouble(score.getLevel4()));
							}
							if(scoreBo.getDifficulty_id()==5){
								verbalScore+=(scoreBo.getDifficulty_id()*Double.parseDouble(score.getLevel5()));
							}
					} 
				}  
			} 
			totalVerbalScaledScore=(Double.parseDouble(score.getQuantMinimum()))+Math.round(verbalScore);
			totalQuantScaledScore=(Double.parseDouble(score.getQuantMinimum()))+Math.round(quantScore);
			scaledScoreRanges=fulllengthController.getScaledScoreRanges();  

			HashMap<Integer,Double> scoreMap=new HashMap<Integer,Double>();
			scoreMap.put(1, totalQuantScaledScore);
			scoreMap.put(2, totalVerbalScaledScore);
			
			for (int key : scoreMap.keySet()) { 
				for(ScoreBO scoreBO:scaledScoreRanges){
					 if(scoreMap.get(key)==scoreBO.getScaledScoreRange()){
						 if(key==1){
							 quantPercentile=scoreBO.getQuantScaledScoreRange();
						 }
						 if(key==2){
							 verbalPercentile=scoreBO.getVerbalScaledScoreRange();
						 }
					 }
				}
			} 
			
		System.out.println("Total Quant Score:"+quantScore+"\t Total Verbal Score:"+verbalScore+"\tGeneral GRE Quant:"+totalVerbalScaledScore+"\t General GRE verbal:"+totalQuantScaledScore+"\tQuant Percentile:"+quantPercentile+"\tVerbal Percentile:"+verbalPercentile);
			
		int totalScore =(int) (totalQuantScaledScore+totalVerbalScaledScore);
		
			request.setAttribute("totalQuantScaledScore", totalQuantScaledScore.intValue() );
	  request.setAttribute("totalVerbalScaledScore", totalVerbalScaledScore.intValue() );
	  request.setAttribute("quantPercentile", quantPercentile.intValue() );		 
	  request.setAttribute("verbalPercentile", verbalPercentile.intValue() );
	  request.setAttribute("totalScore", totalScore );
	
	 
	  score.setTotalQuantScaledScore(totalQuantScaledScore.intValue() );
	  score.setTotalVerbalScaledScore(totalVerbalScaledScore.intValue() );
	  score.setQuantPercentile(quantPercentile.intValue() );		
	  score.setVerbalPercentile(verbalPercentile.intValue() );
	  score.setTotalScore(totalScore );
		  score.setQuantRawScore(quantScore);
		  score.setVerbalRawScore(verbalScore);
		  score.setTestno(testno);
		  score.setUserid(userbo.getUser_id());
		  
		int  res =fulllengthController.saveGreScore(score);
		  
		
		
		
		
		request.setAttribute("result", fullResultsBO.getResult_page().substring(0, fullResultsBO.getResult_page().length()-1)); 
		String	v=new net.sf.json.JSONArray().fromObject("["+fullResultsBO.getCategory_select()+"]").toString();
		System.out.println("V ***************  :"+v);
			request.setAttribute("catagory_select", v);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			getStackedGraphForCategories(1,testno);
			getUserPaceGraphValues(1,testno);
			getStackedGraphForCategories(2,testno);
			getUserPaceGraphValues(2,testno);
			
			
		} catch (DBException | SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 
		
		request.setAttribute("test_no", testno);
		
		return result;	
	}
	
	
	
	public void getStackedGraphForCategories(int sectionid,int test_no) throws DBException, SQLException, JSONException{
		
		
		//System.out.println("*********************  getStackedGraphForCategories ****************************************************");
		
		LoginUserBO user = (LoginUserBO) sessionMap.get("user");
		
		String query="SELECT DISTINCT IFNULL(ROUND((( "+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' AND res.test_no="+test_no+" AND res.category_id=4))),0) AS CorrectCount1, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id) "+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' AND res.test_no="+test_no+" AND res.category_id=4))),0) AS WrongCount1, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' AND res.test_no="+test_no+" AND res.category_id=4)*100/("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.test_no="+test_no+" AND res.category_id=4))),0) AS CorrectPercentage1, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' AND res.test_no="+test_no+" AND res.category_id=4)*100/("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.test_no="+test_no+" AND res.category_id=4))),0) AS wrongPercentage1,("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.category_id=4 AND res.test_no="+test_no+") AS TotalCount1, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' AND res.test_no="+test_no+" AND res.category_id=6))),0) AS CorrectCount2, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' AND res.test_no="+test_no+" AND res.category_id=6))),0) AS WrongCount2, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' AND res.test_no="+test_no+" AND res.category_id=6)*100/("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.test_no="+test_no+" AND res.category_id=6))),0) AS correctPercentage2, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' AND res.test_no="+test_no+" AND res.category_id=6)*100/("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.test_no="+test_no+" AND res.category_id=6))),0) AS wrongPercentage2,("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.category_id=6 AND res.test_no="+test_no+") AS TotalCount2, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=8))),0) AS CorrectCount3, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=8))),0) AS WrongCount3, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=8)*100/("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=8))),0) AS correctPercentage3, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=8)*100/("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=8))),0) AS wrongPercentage3,("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.category_id=6 AND res.type_id=8 AND res.test_no="+test_no+") AS TotalCount3, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=9))),0) AS CorrectCount4, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=9))),0) AS WrongCount4, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=9)*100/("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=9))),0) AS correctPercentage4, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=9)*100/("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=9))),0) AS wrongPercentage4,("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.category_id=6 AND res.type_id=9 AND res.test_no="+test_no+") AS TotalCount4, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=10))),0) AS CorrectCount5, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=10))),0) AS WrongCount5, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=10)*100/("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=10))),0) AS correctPercentage5, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=10)*100/("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=10))),0) AS wrongPercentage5,("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.category_id=6 AND res.type_id=10 AND res.test_no="+test_no+") AS TotalCount5, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=13))),0) AS CorrectCount6, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=13))),0) AS WrongCount6, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=13)*100/("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=13))),0) AS correctPercentage6, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=13)*100/("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=13))),0) AS wrongPercentage6,("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.category_id=6 AND res.type_id=13 AND res.test_no="+test_no+") AS TotalCount6, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' AND res.test_no="+test_no+" AND res.category_id=5))),0) AS CorrectCount7, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' AND res.test_no="+test_no+" AND res.category_id=5))),0) AS WrongCount7, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' AND res.test_no="+test_no+" AND res.category_id=5)*100/("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.test_no="+test_no+" AND res.category_id=5))),0) AS correctPercentage7, IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' AND res.test_no="+test_no+" AND res.category_id=6 AND res.type_id=13)*100/("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.test_no="+test_no+" AND res.category_id=5))),0) AS wrongPercentage7,("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.category_id=5 AND res.test_no="+test_no+") AS TotalCount7"+
	" FROM cp_test_results test"+
	" WHERE test.userid="+user.getUser_id()+" AND test.section_id=1 AND test.test_no="+test_no+"";
		
		
	//	System.out.println("Quant Query:::::::::::::::::::::::::::: "+query);
		
		
	/*String verbalQuery="SELECT DISTINCT IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' and res.test_no="+test_no+"  AND res.category_id=2))),0) AS CorrectCount1,"+
	" IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' and res.test_no="+test_no+"  AND res.category_id=2))),0) AS WrongCount1,("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.category_id=2 AND res.test_no="+test_no+") AS TotalCount1,"+
	" IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' and res.test_no="+test_no+"  AND res.category_id=1))),0) AS CorrectCount2,"+
	" IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' and res.test_no="+test_no+"  AND res.category_id=1))),0) AS WrongCount2,"+
	" ("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.category_id=1 AND res.test_no="+test_no+") AS TotalCount2, "+
	" IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' and res.test_no="+test_no+"  AND res.category_id=1 AND res.type_id=1))),0) AS CorrectCount3,"+
	" IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' and res.test_no="+test_no+"  AND res.category_id=1 AND res.type_id=1))),0) AS WrongCount3,"+
	" ("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.category_id=1 AND res.type_id=1 AND res.test_no="+test_no+") AS TotalCount3,"+
	" IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' and res.test_no="+test_no+"  AND res.category_id=1 AND res.type_id=2))),0) AS CorrectCount4,"+
	" IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' and res.test_no="+test_no+"  AND res.category_id=1 AND res.type_id=2))),0) AS WrongCount4,"+
	" ("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.category_id=1 AND res.type_id=2 AND res.test_no="+test_no+") AS TotalCount4,"+
	" IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' and res.test_no="+test_no+"  AND res.category_id=1 AND res.type_id=3))),0) AS CorrectCount5,"+
	" IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' and res.test_no="+test_no+"  AND res.category_id=1 AND res.type_id=3))),0) AS WrongCount5,"+
	" ("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.category_id=1 AND res.type_id=3 AND res.test_no="+test_no+") AS TotalCount5,"+
	" IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='CORRECT' and res.test_no="+test_no+"  AND res.category_id=3))),0) AS CorrectCount6,"+
	" IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.result='INCORRECT' and res.test_no="+test_no+"  AND res.category_id=3))),0) AS WrongCount6,"+
	" ("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res"+
	" WHERE res.userid="+user.getUser_id()+" AND res.section_id=1 AND res.category_id=3  AND res.test_no="+test_no+") AS TotalCount6,"+
	" IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp"+
	" WHERE res.userid="+user.getUser_id()+" AND res.category_id=3 AND res.question_id=qmasters.question_id AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='SHORT' AND res.result='CORRECT' AND res.test_no="+test_no+"))),0) AS CorrectCount7,"+
	" IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp"+
	" WHERE res.userid="+user.getUser_id()+" AND res.category_id=3 AND res.question_id=qmasters.question_id AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='SHORT' AND res.result='INCORRECT' AND res.test_no="+test_no+"))),0) AS WrongCount7,"+
	" ("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp"+
	" WHERE res.userid="+user.getUser_id()+" AND res.category_id=3 AND res.question_id=qmasters.question_id AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='SHORT' AND res.test_no="+test_no+") AS TotalCount7,"+
	" IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp"+
	" WHERE res.userid="+user.getUser_id()+" AND res.category_id=3 AND res.question_id=qmasters.question_id AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='LONG' AND res.result='CORRECT' AND res.test_no="+test_no+"))),0) AS CorrectCount8,"+
	" IFNULL(ROUND((("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp"+
	" WHERE res.userid="+user.getUser_id()+" AND res.category_id=3 AND res.question_id=qmasters.question_id AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='LONG' AND res.result='INCORRECT' AND res.test_no="+test_no+"))),0) AS WrongCount8,"+
	" ("+
	" SELECT COUNT(res.row_id)"+
	" FROM cp_test_results res,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp"+
	" WHERE res.userid="+user.getUser_id()+" AND res.category_id=3 AND res.question_id=qmasters.question_id AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='LONG' AND res.test_no="+test_no+") AS TotalCount8"+
	" from cp_test_results test WHERE test.userid="+user.getUser_id()+" AND test.section_id=1 AND test.test_no="+test_no+"";*/	
		

	   // System.out.println("Verbal Query :::::::::::::::::::::::::::::::::::::::::::: "+verbalQuery);	

		
		String verbalQuery="SELECT DISTINCT IFNULL(ROUND((("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.result='CORRECT' and res.test_no="+test_no+"  AND res.category_id=2))),0) AS CorrectCount1,"+
				" IFNULL(ROUND((("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.result='INCORRECT' and res.test_no="+test_no+"  AND res.category_id=2))),0) AS WrongCount1,("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.category_id=2 AND res.test_no="+test_no+") AS TotalCount1,"+
				" IFNULL(ROUND((("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.result='CORRECT' and res.test_no="+test_no+"  AND res.category_id=1))),0) AS CorrectCount2,"+
				" IFNULL(ROUND((("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.result='INCORRECT' and res.test_no="+test_no+"  AND res.category_id=1))),0) AS WrongCount2,"+
				" ("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.category_id=1 AND res.test_no="+test_no+") AS TotalCount2, "+
				" IFNULL(ROUND((("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.result='CORRECT' and res.test_no="+test_no+"  AND res.category_id=1 AND res.type_id=1))),0) AS CorrectCount3,"+
				" IFNULL(ROUND((("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.result='INCORRECT' and res.test_no="+test_no+"  AND res.category_id=1 AND res.type_id=1))),0) AS WrongCount3,"+
				" ("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.category_id=1 AND res.type_id=1 AND res.test_no="+test_no+") AS TotalCount3,"+
				" IFNULL(ROUND((("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.result='CORRECT' and res.test_no="+test_no+"  AND res.category_id=1 AND res.type_id=2))),0) AS CorrectCount4,"+
				" IFNULL(ROUND((("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.result='INCORRECT' and res.test_no="+test_no+"  AND res.category_id=1 AND res.type_id=2))),0) AS WrongCount4,"+
				" ("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.category_id=1 AND res.type_id=2 AND res.test_no="+test_no+") AS TotalCount4,"+
				" IFNULL(ROUND((("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.result='CORRECT' and res.test_no="+test_no+"  AND res.category_id=1 AND res.type_id=3))),0) AS CorrectCount5,"+
				" IFNULL(ROUND((("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.result='INCORRECT' and res.test_no="+test_no+"  AND res.category_id=1 AND res.type_id=3))),0) AS WrongCount5,"+
				" ("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.category_id=1 AND res.type_id=3 AND res.test_no="+test_no+") AS TotalCount5,"+
				" IFNULL(ROUND((("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.result='CORRECT' and res.test_no="+test_no+"  AND res.category_id=3))),0) AS CorrectCount6,"+
				" IFNULL(ROUND((("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.result='INCORRECT' and res.test_no="+test_no+"  AND res.category_id=3))),0) AS WrongCount6,"+
				" ("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res"+
				" WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.category_id=3  AND res.test_no="+test_no+") AS TotalCount6,"+
				" IFNULL(ROUND((("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp"+
				" WHERE res.userid="+user.getUser_id()+" AND res.category_id=3 AND res.question_id=qmasters.question_id AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='SHORT' AND res.result='CORRECT' AND res.test_no="+test_no+"))),0) AS CorrectCount7,"+
				" IFNULL(ROUND((("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp"+
				" WHERE res.userid="+user.getUser_id()+" AND res.category_id=3 AND res.question_id=qmasters.question_id AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='SHORT' AND res.result='INCORRECT' AND res.test_no="+test_no+"))),0) AS WrongCount7,"+
				" ("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp"+
				" WHERE res.userid="+user.getUser_id()+" AND res.category_id=3 AND res.question_id=qmasters.question_id AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='SHORT' AND res.test_no="+test_no+") AS TotalCount7,"+
				" IFNULL(ROUND((("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp"+
				" WHERE res.userid="+user.getUser_id()+" AND res.category_id=3 AND res.question_id=qmasters.question_id AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='LONG' AND res.result='CORRECT' AND res.test_no="+test_no+"))),0) AS CorrectCount8,"+
				" IFNULL(ROUND((("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp"+
				" WHERE res.userid="+user.getUser_id()+" AND res.category_id=3 AND res.question_id=qmasters.question_id AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='LONG' AND res.result='INCORRECT' AND res.test_no="+test_no+"))),0) AS WrongCount8,"+
				" ("+
				" SELECT COUNT(res.row_id)"+
				" FROM cp_test_results res,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp"+
				" WHERE res.userid="+user.getUser_id()+" AND res.category_id=3 AND res.question_id=qmasters.question_id AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='LONG' AND res.test_no="+test_no+") AS TotalCount8,IFNULL(ROUND(((SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.result='CORRECT' AND res.test_no="+test_no+" AND res.category_id=9))),0) AS CorrectCount9, IFNULL(ROUND((( SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.result='INCORRECT' AND res.test_no="+test_no+" AND res.category_id=9))),0) AS WrongCount9, ( SELECT COUNT(res.row_id) FROM cp_test_results res WHERE res.userid="+user.getUser_id()+" AND res.section_id=2 AND res.category_id=9 AND res.test_no="+test_no+") AS TotalCount9 "+
				" from cp_test_results test WHERE test.userid="+user.getUser_id()+" AND test.section_id=2 AND test.test_no="+test_no+"";
		
		
	 //  System.out.println("Verbal Query : "+verbalQuery);	

	           
		JSONObject jsonObject = new JSONObject();
		String categories = null;
		practiceSessionController=PracticeSessionController.getInstance();
		if(sectionid == 1){
	    jsonObject = practiceSessionController.getPercentageForCategories(query);
	    categories="[\"Quantative Comparison\",\"Problem Solving Single\",\"Problem Solving Multiple\",\"Numeric Entry \",\"Data Interpretation\"]";
	    
	    
	    requestMap.put("datacount", jsonObject.toString());
	    requestMap.put("categories", categories);
	    requestMap.put("sectionid", sectionid);
		}
		else if(sectionid == 2){
			jsonObject = practiceSessionController.getPercentageForCategories(verbalQuery);
			categories="[\"Sentence Equivalence\",\"Text Completion Single Blank\",\"Text Completion Double Blank\",\"Text Completion Triple Blank\",\"Reading Comprehension\",\"Critical Reasoning\"]";
			
			 requestMap.put("datacount1", jsonObject.toString());
			    requestMap.put("categories1", categories);
			    requestMap.put("sectionid1", sectionid);
		}
	  //  System.out.println(jsonObject.get("TotalCount1")+"corect coount"+jsonObject.get("CorrectCount1")+"wrong count"+jsonObject.get("WrongCount1"));
	    
		  
	 //   System.out.println(jsonObject.toString());
	    /*
	    requestMap.put("datacount", jsonObject.toString());
	    requestMap.put("categories", categories);
	    requestMap.put("sectionid", sectionid);*/
		
	  //  System.out.println("*********************  getStackedGraphForCategories   END****************************************************");
		
	}

	public void getUserPaceGraphValues(int sectionid,int test_no) throws DBException, SQLException, JSONException{
		
		LoginUserBO user = (LoginUserBO) sessionMap.get("user");
		
		String queryForUserPace="SELECT DISTINCT qmasters.question_title, TIME_TO_SEC( test.user_time) as user_time , TIME_TO_SEC(ques.avg_time) as avg_time "+
				" FROM cp_test_results test,cp_questions ques,cp_question_masters qmasters"+
				" WHERE test.userid="+user.getUser_id()+" AND test.section_id="+sectionid+" AND test.test_no="+test_no+" AND ques.question_id=test.question_id and qmasters.question_id=ques.question_id";  
		
		
		
		String verbalPaceQuery=" Select DISTINCT "+
		
				
				
				" IFNULL((SELECT DISTINCT CONCAT(SUM(TIME_TO_SEC( test.user_time)),'@',SUM(TIME_TO_SEC(ques.avg_time))) "+
				" FROM cp_test_results test,cp_questions ques,cp_question_masters qmasters"+
				" WHERE test.userid="+user.getUser_id()+" AND test.section_id=2 AND test.test_no="+test_no+" AND ques.question_id=test.question_id and qmasters.question_id=ques.question_id "+
				"  and test.category_id = 2 ),CONCAT(0,'@',0))  as category1,"+ 
				"  IFNULL((SELECT DISTINCT CONCAT(SUM(TIME_TO_SEC( test.user_time)),'@',SUM(TIME_TO_SEC(ques.avg_time))) "+
				" FROM cp_test_results test,cp_questions ques,cp_question_masters qmasters"+
				" WHERE test.userid="+user.getUser_id()+" AND test.section_id=2 AND test.test_no="+test_no+" AND ques.question_id=test.question_id and qmasters.question_id=ques.question_id "+
				" and test.category_id = 1 ),CONCAT(0,'@',0))  as category2,"+ 
				 " IFNULL((SELECT DISTINCT CONCAT(SUM(TIME_TO_SEC(test.user_time)),'@',SUM(TIME_TO_SEC(ques.avg_time))) "+
				" FROM cp_test_results test,cp_questions ques,cp_question_masters qmasters"+
				" WHERE test.userid="+user.getUser_id()+" AND test.section_id=2 AND test.test_no="+test_no+" AND ques.question_id=test.question_id and qmasters.question_id=ques.question_id "+
				" and test.category_id=1 AND test.type_id=1 ),CONCAT(0,'@',0))  as category3,"+ 
				" IFNULL((SELECT DISTINCT CONCAT(SUM(TIME_TO_SEC( test.user_time)),'@',SUM(TIME_TO_SEC(ques.avg_time))) "+
				" FROM cp_test_results test,cp_questions ques,cp_question_masters qmasters"+
				" WHERE test.userid="+user.getUser_id()+" AND test.section_id=2 AND test.test_no="+test_no+" AND ques.question_id=test.question_id and qmasters.question_id=ques.question_id "+
				" and test.category_id=1 AND test.type_id=2 ),CONCAT(0,'@',0))  as category4,"+ 
				" IFNULL((SELECT DISTINCT CONCAT(SUM(TIME_TO_SEC( test.user_time)),'@',SUM(TIME_TO_SEC(ques.avg_time))) "+
				" FROM cp_test_results test,cp_questions ques,cp_question_masters qmasters"+
				" WHERE test.userid="+user.getUser_id()+" AND test.section_id=2 AND test.test_no="+test_no+" AND ques.question_id=test.question_id and qmasters.question_id=ques.question_id "+
				" and test.category_id=1 AND test.type_id=3),CONCAT(0,'@',0))  as category5,"+ 
				 " IFNULL((SELECT DISTINCT CONCAT(SUM(TIME_TO_SEC( test.user_time)),'@',SUM(TIME_TO_SEC(ques.avg_time))) "+
				" FROM cp_test_results test,cp_questions ques,cp_question_masters qmasters"+
				" WHERE test.userid="+user.getUser_id()+" AND test.section_id=2 AND test.test_no="+test_no+" AND ques.question_id=test.question_id and qmasters.question_id=ques.question_id "+
				" and test.category_id=3 ),CONCAT(0,'@',0))  as category6,"+ 
				"   IFNULL((SELECT DISTINCT CONCAT(SUM(TIME_TO_SEC( test.user_time)),'@',SUM(TIME_TO_SEC(ques.avg_time))) "+
				" FROM cp_test_results test,cp_questions ques,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp"+
				" WHERE test.userid="+user.getUser_id()+" AND test.section_id=2 AND test.test_no="+test_no+" AND ques.question_id=test.question_id "+
				" and qmasters.question_id=ques.question_id  AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='SHORT' "+
				 " and test.category_id=3 ),CONCAT(0,'@',0))  as category7,"+ 
				 "  IFNULL((SELECT DISTINCT CONCAT(SUM(TIME_TO_SEC( test.user_time)),'@',SUM(TIME_TO_SEC(ques.avg_time))) "+
				" FROM cp_test_results test,cp_questions ques,cp_question_masters qmasters,cp_reading_comprehension_passage rcomp"+
				" WHERE test.userid="+user.getUser_id()+" AND test.section_id=2 AND test.test_no="+test_no+" AND ques.question_id=test.question_id "+
				" and qmasters.question_id=ques.question_id  AND qmasters.passage_id=rcomp.passage_id AND rcomp.passage_type='LONG' "+
				" and test.category_id=3),CONCAT(0,'@',0))  as category8,  IFNULL((SELECT DISTINCT CONCAT(SUM(TIME_TO_SEC(test.user_time)),'@', SUM(TIME_TO_SEC(ques.avg_time))) FROM cp_test_results test,cp_questions ques,cp_question_masters qmasters WHERE test.userid="+user.getUser_id()+" AND test.section_id=2 AND test.test_no="+test_no+" AND ques.question_id=test.question_id AND qmasters.question_id=ques.question_id AND test.category_id=9), CONCAT(0,'@',0)) AS category9 "+
				" from cp_test_results res ";
		
		//  System.out.println("User Verbal query ::::::::::::::"+verbalPaceQuery);
		
		String quantPaceQuery="Select DISTINCT "+
				" IFNULL((SELECT DISTINCT CONCAT(SUM(TIME_TO_SEC( test.user_time)),'@',SUM(TIME_TO_SEC(ques.avg_time))) "+
				" FROM cp_test_results test,cp_questions ques,cp_question_masters qmasters"+
				" WHERE test.userid="+user.getUser_id()+" AND test.section_id=1 AND test.test_no="+test_no+" AND ques.question_id=test.question_id and qmasters.question_id=ques.question_id "+
				" and test.category_id = 4 ),CONCAT(0,'@',0))  as category1,"+ 
				" IFNULL((SELECT DISTINCT CONCAT(SUM(TIME_TO_SEC( test.user_time)),'@',SUM(TIME_TO_SEC(ques.avg_time))) "+
				" FROM cp_test_results test,cp_questions ques,cp_question_masters qmasters"+
				" WHERE test.userid="+user.getUser_id()+" AND test.section_id=1 AND test.test_no="+test_no+" AND ques.question_id=test.question_id and qmasters.question_id=ques.question_id "+
				" and test.category_id = 6 ),CONCAT(0,'@',0))  as category2,"+ 
				" IFNULL((SELECT DISTINCT CONCAT(SUM(TIME_TO_SEC( test.user_time)),'@',SUM(TIME_TO_SEC(ques.avg_time))) "+
				" FROM cp_test_results test,cp_questions ques,cp_question_masters qmasters"+
				" WHERE test.userid="+user.getUser_id()+" AND test.section_id=1 AND test.test_no="+test_no+" AND ques.question_id=test.question_id and qmasters.question_id=ques.question_id "+
				" and test.category_id=6 AND test.type_id=8 ),CONCAT(0,'@',0))  as category3,"+ 
				" IFNULL((SELECT DISTINCT CONCAT(SUM(TIME_TO_SEC( test.user_time)),'@',SUM(TIME_TO_SEC(ques.avg_time))) "+
				" FROM cp_test_results test,cp_questions ques,cp_question_masters qmasters"+
				" WHERE test.userid="+user.getUser_id()+" AND test.section_id=1 AND test.test_no="+test_no+" AND ques.question_id=test.question_id and qmasters.question_id=ques.question_id "+
				" and test.category_id=6 AND test.type_id=9 ),CONCAT(0,'@',0))  as category4,"+ 
				"  IFNULL((SELECT DISTINCT CONCAT(SUM(TIME_TO_SEC( test.user_time)),'@',SUM(TIME_TO_SEC(ques.avg_time))) "+
				" FROM cp_test_results test,cp_questions ques,cp_question_masters qmasters"+
				" WHERE test.userid="+user.getUser_id()+" AND test.section_id=1 AND test.test_no="+test_no+" AND ques.question_id=test.question_id and qmasters.question_id=ques.question_id "+
				" and test.category_id=6 AND test.type_id=10),CONCAT(0,'@',0))  as category5,"+ 
				"  IFNULL((SELECT DISTINCT CONCAT(SUM(TIME_TO_SEC( test.user_time)),'@',SUM(TIME_TO_SEC(ques.avg_time))) "+
				" FROM cp_test_results test,cp_questions ques,cp_question_masters qmasters"+
				" WHERE test.userid="+user.getUser_id()+" AND test.section_id=1 AND test.test_no="+test_no+" AND ques.question_id=test.question_id and qmasters.question_id=ques.question_id "+
				" and test.category_id=6 AND test.type_id=13 ),CONCAT(0,'@',0))  as category6,"+ 
				"  IFNULL((SELECT DISTINCT CONCAT(SUM(TIME_TO_SEC( test.user_time)),'@',SUM(TIME_TO_SEC(ques.avg_time))) "+
				" FROM cp_test_results test,cp_questions ques,cp_question_masters qmasters"+
				" WHERE test.userid="+user.getUser_id()+" AND test.section_id=1 AND test.test_no="+test_no+" AND ques.question_id=test.question_id and qmasters.question_id=ques.question_id "+
				 " and test.category_id=5 ),CONCAT(0,'@',0))  as category7"+ 
				 " from cp_test_results ";
		
	      //  System.out.println("User quant query:::::::::::::::"+quantPaceQuery);
		
		//String categories=null;
		JSONObject jsonObject = new JSONObject();	
		//practiceSessionController=PracticeSessionController.getInstance();
		
			if(sectionid == 1){
				    jsonObject = practiceSessionController.getUserPaceValues(quantPaceQuery);
				    requestMap.put("userPace", jsonObject.toString());
				 //   categories="[\"Quant Comparison\",\"Problem Solving\",\"Problem Solving Single\",\"Problem Solving Double\",\"Numeric Entry Single\",\"Numeric Entry Double\",\"Data Interpretation\"]";
			}
			else{
				jsonObject = practiceSessionController.getUserPaceValues(verbalPaceQuery);
				 requestMap.put("userPace1", jsonObject.toString());
				//categories="[\"Sentence Equivalence\",\"Text Completion\",\"Text Completion Single\",\"Text Completion Double\",\"Text Completion Triple\",\"Reading Comprehension\",\"Reading Comprehension Short\",\"Reading Comprehension Long\"]";
			}
				    
				  //  System.out.println(jsonObject.toString());
				   /* requestMap.put("userPace", jsonObject.toString());*/
					
		
	}
	
 
	public String updateReason(){		 	
		LoginUserBO userbo = (LoginUserBO) sessionMap.get("user");
		String result="success";
		System.out.println("In result");

	fullResultsBO = new FullResultsBO();
		int testno;
		int sectionId=0;
		 if(request.getParameter("testid")==null)
		 {
			 testno=this.getTestNo();
		 }
		 else
		 {
			 testno = Integer.parseInt(request.getParameter("testid"));
			// sectionId =Integer.parseInt(request.getParameter("sectionid"));
		 }
		try {			
						
			fulllengthController = FulllengthController.getInstance();			
			fullResultsBO.setUserid(userbo.getUser_id());			
			fullResultsBO.setTest_no(testno);
			fullResultsBO.setQuestion_id(Integer.parseInt(request.getParameter("qid").toString()));
			fullResultsBO.setReason(request.getParameter("reasonid").toString());
			this.setFullResultsBO(fulllengthController.updateReason(fullResultsBO));
			System.out.println("** HTML** RESULT:"+fullResultsBO.getResult_page());
			request.setAttribute("result", fullResultsBO.getResult_page().substring(0, fullResultsBO.getResult_page().length()-1));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			} 
		request.setAttribute("test_no", testno);
		
		return result;	
	}
	
 
	
	
	
	/**
	 *  Vamsi Krishna
	 *  Overall Performance & Question Analysis Start
	 * @throws JSONException 
	 * @throws IOException 
	 *  
	 * */ 
	
	public void overallPerformanceData() throws JSONException, IOException{
		
		JSONObject json = new JSONObject();
		JSONObject resultJson = new JSONObject();
		
		fulllengthController = FulllengthController.getInstance();
		LoginUserBO userbo = (LoginUserBO) sessionMap.get("user");
		json.put("userId", userbo.getUser_id());
		try {
			resultJson = fulllengthController.overallPerformanceData(json);
		} catch (ConnectionException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		json.put("correct",resultJson.get("pie-chart-data").toString().split("@")[0]);
		json.put("incorrect",resultJson.get("pie-chart-data").toString().split("@")[1]);
		json.put("totaltime",resultJson.get("others").toString().split("@")[0]);
		json.put("guessed",resultJson.get("pie-chart-data").toString().split("@")[2]);
		json.put("flagged", resultJson.get("pie-chart-data").toString().split("@")[3]);
		json.put("quantCorrectTime", resultJson.get("others").toString().split("@")[1]);
		json.put("verbalCorrectTime", resultJson.get("others").toString().split("@")[3]);
		json.put("quantIncorrectTime", resultJson.get("others").toString().split("@")[2]);
		json.put("verbalInorrectTime", resultJson.get("others").toString().split("@")[4]);
		//json.put("performanceSumary",resultJson.get("performanceSummary").toString());
		response.getWriter().write(json.toString());
	}
	
	
	
	public String performanceSummary() throws JSONException{
		
		JSONObject json = new JSONObject();
		fulllengthController = FulllengthController.getInstance();
		LoginUserBO userbo = (LoginUserBO) sessionMap.get("user");
		json.put("userId", userbo.getUser_id());
		try {
			performanceSummary = fulllengthController.performanceSummary(json);
		} catch (ConnectionException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Size : "+performanceSummary.size());
		return "success";
		
	}
	
	public String questionAnalysisData() throws JSONException, IOException{
		
		
		JSONObject json = new JSONObject();

		JSONArray jsonArray = new JSONArray();
		String questionsData = "";

		JSONObject quesAnalysisBOs =null;

		
		fulllengthController = FulllengthController.getInstance();
		LoginUserBO userbo = (LoginUserBO) sessionMap.get("user");
		json.put("userId", userbo.getUser_id());
		try {
			quesAnalysisBOs = fulllengthController.questionAnalysis(json);
		} catch (ConnectionException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	    jsonArray=(JSONArray) quesAnalysisBOs.get("tabledata");
	//	System.out.println("JSON A : "+jsonArray.toString());
		//questionsData = "{ \"Result\": \"INCORRECT\" , \"Question Title\": \"System Architect\",\"Guessed\": \"1\",\"Flagged\": \"Yes\",\"Date\": \"Edinburgh\",\"Reason\": \"5421\",\"Retry\": \"5421\"}";
		setQaDataTable(jsonArray.toString());
		
		setQaData(quesAnalysisBOs.get("graphsdata").toString());
		setAvgTimeData(quesAnalysisBOs.get("fastslowques").toString());
		//System.out.println("Data setted");
	//	System.out.println("Data : "+quesAnalysisBOs.get(quesAnalysisBOs.size()-1).getGraphsdata());
		return "success";
	}
	
	public void questionAnalysis() throws JSONException, IOException{
		
		JSONObject json = new JSONObject();
			
		json.put("totalQuestions",141);
		json.put("solvedQuestions",141);
		json.put("leftQuestions",141);
		json.put("totalAnswered",141);
		json.put("correctQuestions",141);
		json.put("incorrectQuestions",141);
		json.put("qcfQuestion","07:07:07");
		json.put("qcsQuestion","07:07:07");
		json.put("vcfQuestion","07:07:07");
		json.put("vcsQuestion","07:07:07");
		json.put("qwfQuestion","07:07:07");
		json.put("qwsQuestion","07:07:07");
		json.put("vwfQuestion","07:07:07");
		json.put("vwsQuestion","07:07:07");
		response.getWriter().write(json.toString());
	
	}
	
	/**
	 *  @author smittapalli
	 *  used to update SectionId and Section Number and Section Status
	 * */

	  public String updateSectionStatusforNextSection() throws ConnectionException, SQLException
		 {
		 
			 fulllengthController=FulllengthController.getInstance();
			JSONObject object=new JSONObject();
			try {
				object.put("tot_sec",0);
				LoginUserBO userbo = (LoginUserBO) sessionMap.get("user");
				object.put("userId", userbo.getUser_id());
				if(fulllengthController.updateSectionStatusforNextSection(object))
				{
					System.out.println("Section Updated Successfully");
				}
				else
				{
					System.out.println("Section Status Updation Failed");
				}
			} catch (JSONException e) { 
				e.printStackTrace();
			}
			System.out.println("Retrunnnnnnnnnnnnnn");
			return SUCCESS;
		 }
	public void  updateFulllengthStatus()
	{
		 fulllengthController=FulllengthController.getInstance();
			JSONObject object=new JSONObject();
			try {
				LoginUserBO userbo = (LoginUserBO) sessionMap.get("user");
				object.put("userId", userbo.getUser_id());
				object.put("tot_sec",1);
				 object.put("fullLengthRowId",sessionMap.get("fullLengthRowId"));
				try {
					if(fulllengthController.updateSectionStatusforNextSection(object))
					{
						System.out.println("Section Updated Successfully");
					}
					else
					{
						System.out.println("Section Status Updation Failed");
					}
				} catch (ConnectionException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (JSONException e) { 
				e.printStackTrace();
			}
	}
	
	  
	  public void fullRetrySave(){
		  try {
			  //System.out.println("in retry saveeeeeeeeeeeeeeeeeeeeeeeeeee");
		    String result="";
			boolean answer_status=true;
		    String answer_result="";
		    String isAnswered="";
			List<String> question_answers=new ArrayList<String>();   
			List<String> user_answers;
		    LoginUserBO bo =(LoginUserBO) sessionMap.get("user");
		    questionsUploadBO.setUser_id(bo.getUser_id());
		    if(questionsUploadBO.getAnswers()==null || questionsUploadBO.getAnswers().toString().trim().length()==0 || questionsUploadBO.getAnswers().toString().contains("-1"))
			 {
				isAnswered="UNANSWERED"; 
				answer_status=false;
				user_answers=new ArrayList<String>();
			 }
			 else
			 {
				 isAnswered="ANSWERED";
				 user_answers=questionsUploadBO.getAnswers();
				 if(questionsUploadBO.getAnswers().size()==question_answers.size())
				 {
					 for(String ans:question_answers)
						{
							if(!questionsUploadBO.getAnswers().contains(ans))
							{
								answer_status=false;
								break;
							} 
						}
				 }
				 else
				 {
					 answer_status=false;
				 }
			 } 
			 if(answer_status)
			 {
				 answer_result="CORRECT";
			 }
			 else
			 {
				 answer_result="INCORRECT";
			 }   
			 
		//	 System.out.println("in retry saveeeeeeeeeeeeeeeeeeeeeeeeeee");
			 questionsUploadBO.setResult(answer_result);
			 questionsUploadBO.setUser_answer(user_answers);
			 questionsUploadBO.setFlag_status(request.getParameter("flagStatus").toString());
			 questionsUploadBO.setUser_time(request.getParameter("timeSpent").toString());
		     questionsUploadBO.setTest_no(Integer.parseInt(request.getParameter("testt_no")));
			 questionsUploadBO.setSection_id(Integer.parseInt(request.getParameter("sectionidavlue")));
		     questionsUploadBO.setTypeId(Integer.parseInt(request.getParameter("typeidvalue")));
		     fulllengthController=FulllengthController.getInstance();
	      //	System.out.println("in retry saveeeeee and updateeeeeeeeeeeeeeeeeeeeeeeeeee"+request.getParameter("sectionidavlue")+"type id values"+request.getParameter("typeidvalue"));
		     fulllengthController.retrySave(questionsUploadBO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	  
	  
	
	
	@Override
	public void setSession(Map<String, Object> sessionMap) { 
		// TODO Auto-generated method stub
		this.sessionMap = sessionMap;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public int getTestNo() {
		return testNo;
	}

	public void setTestNo(int testNo) {
		this.testNo = testNo;
	}
	private Map<String, Object> requestMap;

	@Override
	public void setRequest(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		this.requestMap=requestMap;
		
	}
	

public String getRetryQuestions(){
		
		System.out.println("Full Lenght ------------> getRetryQuestions ------------> Start ");
		int quesid=Integer.parseInt(request.getParameter("ques_id"));
		int typeid=Integer.parseInt(request.getParameter("type_id"));
		int testno = Integer.parseInt(request.getParameter("retrytestno"));
		int sectionid=Integer.parseInt(request.getParameter("sectionnumber"));
	    LoginUserBO bo = (LoginUserBO) sessionMap.get("user");
		int userid=bo.getUser_id();
		System.out.println("Retry : "+request.getParameter("ques_id")+"testno : "+testno+"sectionid : "+sectionid+"Typed id : "+typeid+"User id: "+userid);
		requestMap.put("question_id", quesid);
		requestMap.put("test_no", testno);
		requestMap.put("sectionid", sectionid);
		requestMap.put("typeeid", typeid);
	String	query= "select qls.*,qmas.*,qs.*,le.*," +
				" (select GROUP_CONCAT(skill.skill_name) from cp_skill_level skill where skill.skill_id in(qs.skill_id1,qs.skill_id2,qs.skill_id3)) as skills,(select dlevel.diff_description from cp_difficulty_level dlevel where dlevel.diff_id=qs.diff_id) as difficultyLevel,cat.category_name" +
				"  from cp_questions qs left join cp_question_lessons qls on qs.question_id= qls.question_id ,"
				+ " (cp_test_results trs right join  cp_question_masters qmas on qmas.question_id=trs.question_id and "
				+ " trs.userid='"+userid+"')left join cp_reading_comprehension_passage rpas  on rpas.passage_id=qmas.passage_id  ,"
				+ " cp_difficulty_level le,cp_question_categories cat"
				+ " WHERE qmas.test_id like '1' and  qs.question_id=qmas.question_id  and le.diff_id=qs.diff_id AND cat.category_id=qmas.category_id and qmas.section_id = "+ sectionid+" and qmas.question_id = "+quesid+"  group by qs.question_id";
		 //  query="select qls.*,qmas.*,qs.*,le.* from cp_questions qs left join cp_question_lessons qls on qs.question_id= qls.question_id , (cp_test_results trs right join  cp_question_masters qmas on qmas.question_id=trs.question_id and  trs.userid='"+userid+"')left join cp_reading_comprehension_passage rpas  on rpas.passage_id=qmas.passage_id  , cp_difficulty_level le  WHERE qmas.test_id like '1'and qs.question_id=qmas.question_id and le.diff_id=qs.diff_id and qmas.section_id = 1 and qls.sublesson_id in ("+request.getParameter("sublessonid").toString()+")  group by qs.question_id ";
		   System.out.println("the data is for sublessonid----------------------------------------"+query);
		  
System.out.println("query is -------------------------------: "+query);
sessionMap.put("practiceQuery", query);
		
		System.out.println("Full Lenght ------------> getRetryQuestions ------------> End ");
		
		return SUCCESS;
	}
	
	
	
}

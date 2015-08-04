package com.greenbuds.crunchprep.action.contentcreator;




import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONException;
import org.json.JSONObject;





import com.greenbuds.crunchprep.bo.contentcreator.AnswersBO;
import com.greenbuds.crunchprep.bo.contentcreator.ChoicesBO;
import com.greenbuds.crunchprep.bo.contentcreator.DataInterpretionBO;
import com.greenbuds.crunchprep.bo.contentcreator.QuestionLessonsBO;
import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.controller.contentcreator.DataInterpretionController;
import com.greenbuds.crunchprep.controller.contentcreator.QuestionUploadController;
import com.greenbuds.crunchprep.dao.common.IdGenerationDAO;
import com.opensymphony.xwork2.ActionSupport;



public class NumericEntryAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,ApplicationAware,RequestAware{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<Integer, String> tests;
	private Map<Integer, String> skills;
	private Map<Integer, String> difficulties;
	private Map<Integer, String> lessons;
	private QuestionsUploadBO questionsBO;
	private QuestionUploadController controller;
	private List<QuestionsUploadBO> questionsList;
	public HttpServletRequest request;
	public HttpServletResponse response;
	public Map<String,Object> map;
	public static int qid1;
	public static int qid2;
	public String message1;
	public HttpServletRequest request2;
	
	
	private List<ChoicesBO>  choiceList;
	private List<AnswersBO> answerList;
	private Map<String, Object> requestMap;
	private List<QuestionLessonsBO> lessonsList;
	
	private String[] deleteQuestions;
	private String successMsg;
	private String errorMsg;
	
	private String type;
	
	private List<DataInterpretionBO> interpretionBOs;
	private DataInterpretionController interpretionController;
	
	
	
	
	 

	
	
	public List<DataInterpretionBO> getInterpretionBOs() {
		return interpretionBOs;
	}



	public void setInterpretionBOs(List<DataInterpretionBO> interpretionBOs) {
		this.interpretionBOs = interpretionBOs;
	}



	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}



	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}



	public String getSuccessMsg() {
		return successMsg;
	}



	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}



	public String getErrorMsg() {
		return errorMsg;
	}



	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}



	public String[] getDeleteQuestions() {
		return deleteQuestions;
	}



	public void setDeleteQuestions(String[] deleteQuestions) {
		this.deleteQuestions = deleteQuestions;
	}



	public String getMessage1() {
		return message1;
	}



	public void setMessage1(String message1) {
		this.message1 = message1;
	}



	



	



	public List<QuestionsUploadBO> getQuestionsList() {
		return questionsList;
	}



	public void setQuestionsList(List<QuestionsUploadBO> questionsList) {
		this.questionsList = questionsList;
	}



	public QuestionsUploadBO getQuestionsBO() {
		return questionsBO;
	}



	public void setQuestionsBO(QuestionsUploadBO questionsBO) {
		this.questionsBO = questionsBO;
	}



	


	public QuestionUploadController getController() {
		return controller;
	}



	public void setController(QuestionUploadController controller) {
		this.controller = controller;
	}



	public Map<Integer, String> getTests() {
		return tests;
	}



	public void setTests(Map<Integer, String> tests) {
		this.tests = tests;
	}



	public Map<Integer, String> getSkills() {
		return skills;
	}



	public void setSkills(Map<Integer, String> skills) {
		this.skills = skills;
	}



	public Map<Integer, String> getDifficulties() {
		return difficulties;
	}



	public void setDifficulties(Map<Integer, String> difficulties) {
		this.difficulties = difficulties;
	}



	public Map<Integer, String> getLessons() {
		return lessons;
	}



	public void setLessons(Map<Integer, String> lessons) {
		this.lessons = lessons;
	}



	public static Logger logger=Logger.getLogger(NumericEntryAction.class);
	
	
	public void initializeData(){
		this.controller=new QuestionUploadController();
		this.interpretionController=DataInterpretionController.getInstance();
		try {
			this.setLessons(this.controller.loadLessons(1,6));
			this.setDifficulties(this.controller.loadDifficulties());
			//this.setTests(this.controller.loadTests(2));
			this.setSkills(this.controller.loadSkills());
			this.setInterpretionBOs(this.interpretionController.getDataInterpretionList(new DataInterpretionBO()));
			//this.setQuestionsList(this.controller.getQuestionsList(2,2,6));
			//this.request.setAttribute("data",this.getQuestionsList());
				
		   // System.out.println(request.getAttribute("msg"));
			/*if(request.getAttribute("msg")!=null)
				this.setMessage((String)request.getAttribute("msg"));
			*/
			//this.setMessage("Hello!");
			//System.out.println("In Execute");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
			
		}
	}
	
	public String execute(){
		System.out.println("Hello in se");
		this.controller=new QuestionUploadController();
		this.interpretionController=DataInterpretionController.getInstance();
		try {
			this.setLessons(this.controller.loadLessons(1,6));
			this.setDifficulties(this.controller.loadDifficulties());
			//this.setTests(this.controller.loadTests(2));
			this.setSkills(this.controller.loadSkills());
			this.setInterpretionBOs(this.interpretionController.getDataInterpretionList(new DataInterpretionBO()));
			System.out.println(this.getInterpretionBOs().size());
			//this.setQuestionsList(this.controller.getQuestionsList(1,5,-1));
			//this.request.setAttribute("data",this.getQuestionsList());
				
		   // System.out.println(request.getAttribute("msg"));
			/*if(request.getAttribute("msg")!=null)
				this.setMessage((String)request.getAttribute("msg"));
			*/
			//this.setMessage("Hello!");
			//System.out.println("In Execute");
			/*this.setSuccessMsg("Question Inserted Successfully!");*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String viewData(){
		this.controller=new QuestionUploadController();
		try {
			
			this.setQuestionsList(this.controller.getQuestionsList(1,new int[]{5,6},new int[]{10,13,16,17}));
			this.request.setAttribute("data",this.getQuestionsList());
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.setErrorMsg("Error Occured While Table Data Loading!");
			logger.error(e);
			return ERROR;
		}
		
	}
	
	public String insertData(){	
		
	
		String result=ERROR;
		this.controller=new QuestionUploadController();
		System.out.println(this.getQuestionsBO().getQuestion_id());
		if((qid1== 0 && this.getQuestionsBO().getQuestion_id()==0)){
		System.out.println("Insert");
		int id=IdGenerationDAO.getInstance().createKey("cp_question_masters", "question_id");
		this.setQuestionsList(new ArrayList<QuestionsUploadBO>());
		this.getQuestionsList().add(getQuestionsBO());
		List<String> skill2=new ArrayList<String>();
		
		for(String ch:this.getQuestionsList().get(0).getSkills()){
			if(!ch.isEmpty())
				skill2.add(ch);
		}
		
		this.getQuestionsBO().setSkills(skill2);
		this.getQuestionsBO().setQuestion_id(id);
		this.getQuestionsBO().setSection_id(1);
		this.getQuestionsBO().setCategory_id(6);
		this.getQuestionsBO().setQuestiontype_id(Integer.parseInt(this.getType()));
		this.getQuestionsBO().setAvg_time("00:00:00");
		System.out.println(this.getQuestionsBO().getAccess_type());
		if(this.getQuestionsBO().getAccess_type()==null || this.getQuestionsBO().getAccess_type().equals("PAID"))
			this.getQuestionsBO().setAccess_type("PAID");
		if(this.getQuestionsBO().getActionType().equalsIgnoreCase("graph")){
			this.getQuestionsBO().setCategory_id(5); // data-interpretation
		    if(Integer.parseInt(this.getType())==10){
		    	this.getQuestionsBO().setQuestiontype_id(16);
		    }
		    if(Integer.parseInt(this.getType())==13){
		    	this.getQuestionsBO().setQuestiontype_id(17);
		    }
		    
		}
		
		
		
		try {
			if(this.controller.saveQuestions(this.getQuestionsList())){
			this.setSuccessMsg("Question Inserted Successfully!");
			this.setQuestionsList(this.controller.getQuestionsList(1,new int[]{5,6},new int[]{10,13,16,17}));
			this.request.setAttribute("data",this.getQuestionsList());
			result= SUCCESS;
			}
			else{
				this.setErrorMsg("Question Insertion Failed!");
				this.setQuestionsList(this.controller.getQuestionsList(1,new int[]{5,6},new int[]{10,13,16,17}));
				this.request.setAttribute("data",this.getQuestionsList());  
				result= ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			logger.error(e);
			this.setErrorMsg("Question Insertion Failed!");
			try {
				this.setQuestionsList(this.controller.getQuestionsList(1,new int[]{5,6},new int[]{10,13,16,17}));
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				this.setErrorMsg("Error Occured While Table Data Loading!");
				result= ERROR;
			}
			this.request.setAttribute("data",this.getQuestionsList());  
			//initializeData();
			result= ERROR;
		} 
		
		
		}
		else
		{ 
			System.out.println("Update");
			System.out.println(this.getQuestionsBO().getRefferal());
			if(this.getQuestionsBO().getRefferal()==null){
				this.getQuestionsBO().setRefferal("NO");
			}
			this.setQuestionsList(new ArrayList<QuestionsUploadBO>());
			if(this.getQuestionsBO().getAccess_type()==null || this.getQuestionsBO().getAccess_type().equals("PAID"))
				this.getQuestionsBO().setAccess_type("PAID");
			this.getQuestionsList().add(getQuestionsBO());
			//this.getQuestionsBO().setAvg_time("00:00:00");
			//this.getQuestionsBO().setStatus("Y");
			try {
				if(this.controller.updateQuestions(this.getQuestionsList())){
				//initializeData();
				this.setQuestionsList(this.controller.getQuestionsList(1,new int[]{5,6},new int[]{10,13,16,17}));
				this.request.setAttribute("data",this.getQuestionsList());
				this.setSuccessMsg("Question Updated Successfully!");
				result= SUCCESS;
				}
				else{ 
				//initializeData();
				this.setErrorMsg("Question Updation Failed!");
				result= ERROR;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				logger.error(e);
				//initializeData();
				try {
					this.setQuestionsList(this.controller.getQuestionsList(1,new int[]{5,6},new int[]{10,13,16,17}));
					this.request.setAttribute("data",this.getQuestionsList());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					logger.error(e);
				}
				this.setErrorMsg("Question Updation Failed!");
				result= ERROR;
			}
			
			
		}
	   
		
	return result;		
		
	}
	
	public String updateData(){
		System.out.println("Hello in update");
		String ss[]=request.getParameterValues("deleteRecords");
	     List<String> qids=new ArrayList<String>();
	     for(int i=0;i<ss.length;i++){
			 String val1[]=ss[i].split("@");
			 qids.add(val1[0]);
			
		 }
	     String [] ids = qids.toArray(new String[qids.size()]);
	    try {
	    	this.controller=new QuestionUploadController();
			this.setQuestionsList(this.controller.getQuestionsList(1,new int[]{5,6},new int[]{10,13,16,17}));
			this.choiceList=new ArrayList<ChoicesBO>();
			this.answerList=new ArrayList<AnswersBO>();
			this.lessonsList=new ArrayList<QuestionLessonsBO>();
			this.choiceList=this.controller.getChoicesList();
			this.answerList=this.controller.getAnswersList();
			this.lessonsList=this.controller.getQuestionLessonsList();
			for(QuestionsUploadBO bo:this.getQuestionsList()){
				if(bo.getQuestion_id()==Integer.parseInt(ids[0])){
					if(bo.getQuestion_no()==1){
					List<String> ch=new ArrayList<String>();
					List<String> chTools=new ArrayList<String>();
					List<String> ans=new ArrayList<String>();
					
					List<String> lessons=new ArrayList<String>();
					for(ChoicesBO choicesBO: this.choiceList){
						if(choicesBO.getQuestion_id()==bo.getQuestion_id() && choicesBO.getQuestion_no()==1){
							ch.add(choicesBO.getChoice());
							chTools.add(choicesBO.getTooltip());
						}
					}
					bo.setChoices(ch);
					bo.setTooltips(chTools);
					for(AnswersBO answersBO:this.answerList){
						if(answersBO.getQuestion_id()==bo.getQuestion_id() && answersBO.getQuestion_no()==1){
							ans.add(answersBO.getAnswer());
						}
					}
					bo.setAnswers(ans);
					for(QuestionLessonsBO lessonsBO:this.lessonsList){
						if(lessonsBO.getQuestion_id()==bo.getQuestion_id() && lessonsBO.getQuestion_no()==1){
							lessons.add(String.valueOf(lessonsBO.getSublesson_id()));
						}
					}
					bo.setLessons(lessons);
					
					
					this.setQuestionsBO(bo);
				}
					else {
						System.out.println("No records found");
					}
					
				}
			}
		//	System.out.println("Choices Size\t"+this.getQuestionsBO().getChoices().size());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
			return ERROR;
		}
	    for(int i=0;i<this.getQuestionsList().size();i++){
	    	QuestionsUploadBO bo=this.getQuestionsList().get(i);
	    	if(bo.getQuestion_id()==Integer.parseInt(ids[0])){
	    		//System.out.println("success");
	    		
	    		request.setAttribute("testId",bo.getTest_id());
	    		request.setAttribute("questionTitle",bo.getQuestion_title());
	    		request.setAttribute("QuestionDirections",bo.getQuestion_directions());
	    		request.setAttribute("Question1",bo.getQuestion());
	    		List<Integer> skillData=new ArrayList<Integer>();
	    		skillData.add(bo.getSkillid1());
	    		skillData.add(bo.getSkillid2());
	    		skillData.add(bo.getSkillid3());
	    		
	    		request.setAttribute("skills1", skillData);
	    		request.setAttribute("difficulty1",bo.getDifficulty_id());
	    		request.setAttribute("avgTime1",bo.getAvg_time());
	    		request.setAttribute("solutionText1",bo.getSolution_text());
	    		request.setAttribute("solutionVideo1",bo.getSolution_video());
	    		request.setAttribute("questionId",bo.getQuestion_id());
	    		request.setAttribute("sectionId",bo.getSection_id());
	    		request.setAttribute("categoryId",bo.getCategory_id());
	    		request.setAttribute("typeId",bo.getQuestiontype_id());
	    		request.setAttribute("accessType",bo.getAccess_type());
	    		request.setAttribute("status", bo.getStatus());
	    		request.setAttribute("referral", bo.getRefferal());
	    		request.setAttribute("graphId", bo.getGraphId());
	    		
	    	    request.setAttribute("choices1", bo.getChoices());
	    	    request.setAttribute("tooltips1", bo.getTooltips());
	    	    
	    	    request.setAttribute("answers1", bo.getAnswers()); 
	    	   /* System.out.println("choices \t"+bo.getChoices());
	    	    System.out.println("tooltips\t"+bo.getTooltips());
	    	    System.out.println("answers\t"+bo.getAnswers());
	    	    System.out.println("lessons\t"+bo.getLessons()); */
	    	    request.setAttribute("lessons1", bo.getLessons());
	    	    
	    	}
	    	    
	    	initializeData();
	    	
	    }
		return SUCCESS;
	}

	public String deleteData() {
		
		 this.controller=new QuestionUploadController();
		 try {
			 String ss[]=request.getParameterValues("deleteRecords");
			 List<String> qids=new ArrayList<String>();
		     for(int i=0;i<ss.length;i++){
				 String val1[]=ss[i].split("@");
				 qids.add(val1[0]);
				
			 }
		     String [] ids = qids.toArray(new String[qids.size()]);
		     System.out.println(ids.length);
		     boolean flag=controller.deleteQuestions(ids);
		     System.out.println(flag);
			if(flag==true){
				
				this.setSuccessMsg("Question Deleted Successfully!");
				this.setQuestionsList(this.controller.getQuestionsList(1,new int[]{5,6},new int[]{10,13,16,17}));
				this.request.setAttribute("data",this.getQuestionsList());
				return SUCCESS;
			}
			 else {
				
				 this.setErrorMsg("Question Deletion Failed!");
				 this.setQuestionsList(this.controller.getQuestionsList(1,new int[]{5,6},new int[]{10,13,16,17}));
				 this.request.setAttribute("data",this.getQuestionsList());
				 return ERROR;
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
			this.setErrorMsg("Question Already In Use!");
			try {
				this.setQuestionsList(this.controller.getQuestionsList(1,new int[]{5,6},new int[]{10,13,16,17}));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				return ERROR;
			}
			this.request.setAttribute("data",this.getQuestionsList());
			return ERROR;
		}
	   //  return SUCCESS;
		 
	     
		 
		
	}
	
	

	public String statusUpdate(){
		// System.out.println("in inactive");
		 String ss[]=request.getParameterValues("deleteRecords");
		 List<String> qids=new ArrayList<String>();
		 Map<String,String> map=new HashMap<String,String>();
		 
		 for(int i=0;i<ss.length;i++){
			 String val1[]=ss[i].split("@");
			 qids.add(val1[0]);
			 map.put(val1[0],val1[1]);
		 }
		 System.out.println("Hello");
		 System.out.println(qids);
		 System.out.println(map.get("8"));
		 String status=request.getParameter("status");
		// System.out.println(request.getParameter("status"));
		 String [] ids = qids.toArray(new String[qids.size()]);
		 
		// String[] ids=(String[])qids.toArray();
		// System.out.println(ids.length);
		 this.controller=new QuestionUploadController();
		 try {
			if(this.controller.updateQuestionStatus(ids, status, map)){
				 this.setSuccessMsg("Status Updated Successfully...!");
				 this.setQuestionsList(this.controller.getQuestionsList(1,new int[]{5,6},new int[]{10,13,16,17}));
				 this.request.setAttribute("data",this.getQuestionsList());
				 return SUCCESS;	 
			 }
			 else{
				 this.setErrorMsg("Status Updation Failed!");
				 this.setQuestionsList(this.controller.getQuestionsList(1,new int[]{5,6},new int[]{10,13,16,17}));
				 this.request.setAttribute("data",this.getQuestionsList());
				 return ERROR;
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
			this.setErrorMsg("Staus Updation failed!");
			try {
				this.setQuestionsList(this.controller.getQuestionsList(1,new int[]{5,6},new int[]{10,13,16,17}));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return ERROR;
			}
			 this.request.setAttribute("data",this.getQuestionsList());
			return ERROR;
		}
		 
		 
		
		
	}
	
	
	
   
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}



	
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}



	
	public void setApplication(Map<String, Object> map) {
		// TODO Auto-generated method stub
		this.map=map;
	}



	public void setRequest(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		this.requestMap=requestMap;
	}



	/**
	 * @return the lessonsList
	 */
	public List<QuestionLessonsBO> getLessonsList() {
		return lessonsList;
	}



	/**
	 * @param lessonsList the lessonsList to set
	 */
	public void setLessonsList(List<QuestionLessonsBO> lessonsList) {
		this.lessonsList = lessonsList;
	}

    

	


	
}

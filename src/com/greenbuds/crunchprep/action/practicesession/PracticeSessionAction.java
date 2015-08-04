package com.greenbuds.crunchprep.action.practicesession;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.poi.hssf.record.cf.PatternFormatting;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.FullResultsBO;
import com.greenbuds.crunchprep.bo.practicesession.CustomizationBO;
import com.greenbuds.crunchprep.bo.practicesession.ResultLessionBO;
import com.greenbuds.crunchprep.bo.practicesession.ResultsExcelBO;

 
 
import com.greenbuds.crunchprep.bo.practicesession.PracticeSessionQuestionsBO;

import com.greenbuds.crunchprep.bo.practicesession.ResultsBO;
import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.bo.server.ServerProperties;
 
import com.greenbuds.crunchprep.controller.fulllengthtest.FulllengthController;
 
import com.greenbuds.crunchprep.controller.contentcreator.IQuestionUploadController;
import com.greenbuds.crunchprep.controller.contentcreator.QuestionUploadController;
import com.greenbuds.crunchprep.controller.fulllengthtest.FulllengthController;
 
import com.greenbuds.crunchprep.controller.practicesession.IPracticeSessionController;

import com.greenbuds.crunchprep.controller.practicesession.PracticeSessionController; 

import com.greenbuds.crunchprep.exception.DBException;

import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException; 
import com.greenbuds.crunchprep.exception.EmailExceptions;
import com.greenbuds.crunchprep.setups.Categories;
import com.greenbuds.crunchprep.setups.DateFormate;
import com.greenbuds.crunchprep.setups.QuestionType;
import com.greenbuds.crunchprep.util.ConvertBeanToJSON;
import com.greenbuds.crunchprep.util.DateUtil;
import com.greenbuds.crunchprep.util.MailAttachementUtil;
import com.greenbuds.crunchprep.util.QuestionTypeUtil;
import com.opensymphony.xwork2.ActionSupport;


public class PracticeSessionAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,SessionAware,RequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5585911701415898421L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String, Object> sessionMap;
	private Map<String, Object> requestMap;
	private CustomizationBO customizationBO;
	private List<QuestionsUploadBO > questionsList;
	private QuestionsUploadBO questionsUploadBO;
 
	private String practiceSessionName;
 
	private ResultsExcelBO resultsExcelBO;
	private ResultLessionBO resultLessionBO;
	private List<ResultLessionBO>lessionsExcelData;
	
	private String sublessonId; 
	
   public String getSublessonId() {
		return sublessonId;
	}

	public void setSublessonId(String sublessonId) {
		this.sublessonId = sublessonId;
	}




private int testNo;
   private String adaptiveMode;
	    
	
	public String getTimeLimitInPage() {
		return timeLimitInPage;
	}
	
	public void setTimeLimitInPage(String timeLimitInPage) {
		this.timeLimitInPage = timeLimitInPage;
	}




public String getTimeLimitFromCustomization() {
		return timeLimitFromCustomization;
	}

	public void setTimeLimitFromCustomization(String timeLimitFromCustomization) {
		this.timeLimitFromCustomization = timeLimitFromCustomization;
	}




private String adaptiveModeInPage;
   private String timeLimitFromCustomization;
   private String timeLimitInPage;
 
		public String getAdaptiveModeInPage() {
	return adaptiveModeInPage;
}

public void setAdaptiveModeInPage(String adaptiveModeInPage) {
	this.adaptiveModeInPage = adaptiveModeInPage;
}

		public String getAdaptiveMode() {
		return adaptiveMode;
	}
	
	public void setAdaptiveMode(String adaptiveMode) {
		this.adaptiveMode = adaptiveMode;
	}

	public String getPracticeSessionName() {
		return practiceSessionName;
	}

	public void setPracticeSessionName(String practiceSessionName) {
		this.practiceSessionName = practiceSessionName;
	}

 
	public List<ResultLessionBO> getLessionsExcelData() {
		return lessionsExcelData;
	}

	public void setLessionsExcelData(List<ResultLessionBO> lessionsExcelData) {
		this.lessionsExcelData = lessionsExcelData;
	}

	public ResultLessionBO getResultLessionBO() {
		return resultLessionBO;
	}

	public void setResultLessionBO(ResultLessionBO resultLessionBO) {
		this.resultLessionBO = resultLessionBO;
	}

	public ResultsExcelBO getResultsExcelBO() {
		return resultsExcelBO;
	}

	public void setResultsExcelBO(ResultsExcelBO resultsExcelBO) {
		this.resultsExcelBO = resultsExcelBO;
	}
 
	public QuestionsUploadBO getQuestionsUploadBO() {
		return questionsUploadBO;
	}

	public void setQuestionsUploadBO(QuestionsUploadBO questionsUploadBO) {
		this.questionsUploadBO = questionsUploadBO;
	}

	public List<QuestionsUploadBO> getQuestionsList() {
		return questionsList;
	}

	public void setQuestionsList(List<QuestionsUploadBO> list) {
		this.questionsList = list;
	}


	 

	private ResultsBO resultsBO;

	private IPracticeSessionController  practiceSessionController;

	private PracticeSessionQuestionsBO practiceSessionQuestionsBO;
	 
    

	List<String> answers;
	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public String execute(){
		return SUCCESS;
	}
	
	public String getPracticeSessionQuestions() throws ConnectionException, DBException, SQLException, JSONException, IOException{
		//performanceBarActions();
		System.out.println("getPracticeSessionQuestions:Session Name:"+getPracticeSessionName());
		createTestNumber();
		//getActivityID();
	//createTestNumber();
		System.out.println("getPracticeSessionQuestions");
		return SUCCESS;
		
	}
	
	
	
	/*** Actions for performance Bar 
	 * @throws DBException 
	 * @throws ConnectionException 
	 * @throws SQLException 
	 * @throws JSONException 
	 * @throws IOException ****/
	
	

	public String performanceBarActions(int questionId) throws ConnectionException, DBException, SQLException, JSONException, IOException{


		
		JSONObject jsonObject=new JSONObject();
	//	practiceSessionQuestionsBO.getQuestionid();
		practiceSessionQuestionsBO=new PracticeSessionQuestionsBO();

		practiceSessionQuestionsBO.setQuestionid(questionId);


		LoginUserBO user=(LoginUserBO)sessionMap.get("user");
		practiceSessionQuestionsBO.setUser_id(user.getUser_id());
		String test_no=sessionMap.get("test_no").toString();
		practiceSessionQuestionsBO.setTest_no(Integer.parseInt(test_no));
		practiceSessionController=PracticeSessionController.getInstance();
		PracticeSessionQuestionsBO practiceSessionQuestions=new PracticeSessionQuestionsBO();
		practiceSessionQuestions=practiceSessionController.getPerformanceBarValues(practiceSessionQuestionsBO);
		System.out.println(" PREVIOUS "+practiceSessionQuestions.getLastFivequestions());
		String gotit="0.0";
		if(practiceSessionQuestions.getGotit_percent()==null)
		{
			gotit="0.0";	
		}
		else{
		int index=practiceSessionQuestions.getGotit_percent().indexOf(".");
		gotit=practiceSessionQuestions.getGotit_percent().substring(0,index)+"."+practiceSessionQuestions.getGotit_percent().substring(index+1,index+3);
		}
		List<String> secs=new ArrayList<String>();
		List<String> colorCodes=new ArrayList<String>();
		
		
		if(practiceSessionQuestions.getLastFivequestions().size()>0){
			for (int j = 0; j < practiceSessionQuestions.getLastFivequestions().size(); j++) {
				
			
			int n=practiceSessionQuestions.getLastFivequestions().get(j).indexOf("@");
 
			secs.add(practiceSessionQuestions.getLastFivequestions().get(j).substring(n+1));

			//secs.add(practiceSessionQuestions.getLastFivequestions().get(j).substring(0,n));
 
			if(practiceSessionQuestions.getLastFivequestions().get(j).substring(0, n).equalsIgnoreCase("CORRECT")){
				colorCodes.add("\"#179877\"");
			}
			else if(practiceSessionQuestions.getLastFivequestions().get(j).substring(0, n).equalsIgnoreCase("INCORRECT")){
				colorCodes.add("\"#e33244\"");
			}
			else{
				colorCodes.add("\"#7f8c8d\"");
			
			}
		}
		
		}	
		
		 
		System.out.println("Practice Sessionnnnnnnnnnnnnnnnnnnnn"+secs.toString().replace("[","")+"   :  "+secs.toString().replace("]",""));
		requestMap.put("avgTime",practiceSessionQuestions.getTotal_users_avg_time() );
		requestMap.put("gotItPercent", gotit);
		 System.out.println("Color codes"+colorCodes.toString()+" Secons : "+secs.toString());
		String values="0";
		String colors="\"#ffffff\"";
		 for (int i = 0; i < secs.size(); i++) {
		values=values+","+secs.get(i);	
		}
		 for (int i = 0; i < colorCodes.size(); i++) {
			 colors=colors+","+colorCodes.get(i);	
				}
		System.out.println("Values"+values+" Colors :"+colors);
		//String val = "0,30.00,10.00,80.00,80.00,66.67";
		//String col = "\"#ffffff\", \"#179877\", \"#e33244\", \"#7f8c8d\", \"#179877\",\"#e33244\"";
		request.setAttribute("chart", values);
		request.setAttribute("col", colors);
		
		 return SUCCESS;
		
	}
	
	public void getActivityID(){
		String result="";
		System.out.println("in testsessions()");
		LoginUserBO bo =(LoginUserBO) sessionMap.get("user");
		QuestionsUploadBO questionsUploadBO=new QuestionsUploadBO();
		questionsUploadBO.setUser_id(bo.getUser_id());
		System.out.println("UserId\t"+questionsUploadBO.getUser_id());
		practiceSessionController=PracticeSessionController.getInstance();
		try {
			practiceSessionController.testSessions(questionsUploadBO);
			System.out.println("Activity Id\t"+questionsUploadBO.getActivity_id());
			if(questionsUploadBO!=null){
				sessionMap.put("test_activity_id",questionsUploadBO.getActivity_id());
				
			}
		} catch (CPException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testSessionSaving(){
		QuestionsUploadBO questionsUploadBO=new QuestionsUploadBO();
		
		questionsUploadBO.setActivity_id(Integer.parseInt(sessionMap.get("test_activity_id").toString()));
		questionsUploadBO.setTest_no(1);
		questionsUploadBO.setQuestion_id(25);
		practiceSessionController=new PracticeSessionController();
		try {
			practiceSessionController.testSessionSaving(questionsUploadBO);
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		// TODO Auto-generated method stub
		this.sessionMap=sessionMap;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

	public CustomizationBO getCustomizationBO() {
		return customizationBO;
	}

	public void setCustomizationBO(CustomizationBO customizationBO) {
		this.customizationBO = customizationBO;
	}
	public ResultsBO getResultsBO() {
		return resultsBO;
	}

	public void setResultsBO(ResultsBO resultsBO) {
		this.resultsBO = resultsBO;
	}

	public void setPracticeSessionQuestionsBO(
			PracticeSessionQuestionsBO practiceSessionQuestionsBO) {
		this.practiceSessionQuestionsBO = practiceSessionQuestionsBO;
	}

	public PracticeSessionQuestionsBO getPracticeSessionQuestionsBO() {
		return practiceSessionQuestionsBO;
	}



	
	public String resultPage(){		 	
		LoginUserBO user=(LoginUserBO) sessionMap.get("user");
		String result="success";

		System.out.println("In result");

		resultsBO = new ResultsBO();
		
  // System.out.println("Test Id AT Last:"+request.getParameter("testno")+"\tTest Num:"+this.getTestNo());
		 //int testno = Integer.parseInt(request.getParameter("testno"));
		
		int testno=0;
		int sectionId=0;
		 if(request.getParameter("testno")==null)
		 {
			 testno=this.getTestNo();
		 }
		 else
		 {
			 testno = Integer.parseInt(request.getParameter("testno"));
			// sectionId =Integer.parseInt(request.getParameter("sectionid"));
		 }
		try {			
			System.out.println("im from Resultpage testNumber:::::::::::::"+request.getParameter("testno"));	
			//System.out.println("im from Resultpage SectionId:::::::::::::"+request.getParameter("sectionid"));	
			
			practiceSessionController = new PracticeSessionController();
			System.out.println(""+resultsBO);
			resultsBO.setUserid(user.getUser_id());
			resultsBO.setTest_no(testno);
			//resultsBO.setSection_id(resultsBO.getSection_id());
			this.setResultsBO(practiceSessionController.resultPage(resultsBO));
			
			//System.out.println("^^^^^^^^^^^^^^^"+resultsBO.getResult_page().length());
			request.setAttribute("result", resultsBO.getResult_page().substring(0, resultsBO.getResult_page().length()-1));					
			this.setResultsBO(practiceSessionController.resultPage(resultsBO));			
			//System.out.println("Resultttttttttttttt : "+resultsBO.getResult_page() + " ::::  "+resultsBO.getResult_page().length());
			request.setAttribute("result", resultsBO.getResult_page().substring(0,resultsBO.getResult_page().length()-1));					
			//System.out.println("Result : "+resultsBO.getResult_page() +" ***** SEC ID  :  "+resultsBO.getSection_id());
			
			 
 
			
			String cr = resultsBO.getStack_correct();
			String wr = resultsBO.getStack_wrong();


			  cr = "["+cr.substring(0, cr.length()-1)+"]";
			  wr = "["+wr.substring(0, wr.length()-1)+"]";


	 
			System.out.println("CR: ---------------> "+cr);
			System.out.println("WR: ---------------> "+wr);
			request.setAttribute("stack_correct", cr);
			request.setAttribute("stack_wrong",  wr);
	
			
			
		//	System.out.println("Stack Correct : "+resultsBO.getStack_correct()+"    Stackworng : "+resultsBO.getStack_wrong());
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			getStackedGraphForCategories(resultsBO.getSection_id(),testno);
			getUserPaceGraphValues(resultsBO.getSection_id(),testno);
			
			excelSheetDataPreparing(testno,sectionId);
		} catch (DBException | SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		requestMap.put("test_no", testno);
		
		return result;	
	}
	

	
	public String updateReason(){		 	
		LoginUserBO userbo = (LoginUserBO) sessionMap.get("user");
		String result="success";
		System.out.println("In result");

	resultsBO = new ResultsBO();
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
						
			practiceSessionController = PracticeSessionController.getInstance();			
			resultsBO.setUserid(userbo.getUser_id());			
			resultsBO.setTest_no(testno);
			resultsBO.setQuestion_id(Integer.parseInt(request.getParameter("qid").toString()));
			resultsBO.setReason(request.getParameter("reasonid").toString());
			this.setResultsBO(practiceSessionController.updateReason(resultsBO));
			System.out.println("** HTML** RESULT:"+resultsBO.getResult_page());
			request.setAttribute("result", resultsBO.getResult_page().substring(0, resultsBO.getResult_page().length()-1));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			} 
		request.setAttribute("test_no", testno);
		
		return result;	
	}
	
 
	
	public int getTestNo() {
		return testNo;
	}

	public void setTestNo(int testNo) {
		this.testNo = testNo;
	}

	public String excelSheetDataPreparing(int test_no,int sectionId){
		
	     resultsExcelBO = new ResultsExcelBO();
	     resultLessionBO =new ResultLessionBO();
		 LoginUserBO user=(LoginUserBO) sessionMap.get("user");
		// int testno = Integer.parseInt(request.getParameter("testno"));
		 int testno = test_no;
		  HSSFWorkbook workbook = new HSSFWorkbook();
		  HSSFSheet sheet = workbook.createSheet("Sample sheet");
		 
		  /* sheet styles area start */
		   
		   sheet.setGridsPrinted(false);
		   sheet.setPrintGridlines(false);
		   sheet.setDisplayGridlines(false);
		   sheet.autoSizeColumn((short)1);
		   
		   /* sheet styles area end */
		  
		   /* Hssf Cell styles area is begin */
		   
		  HSSFCellStyle styleHeader = (HSSFCellStyle) workbook.createCellStyle();
		  
		  styleHeader.setFillForegroundColor(HSSFColor.BROWN.index);          
		  styleHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);         
		    styleHeader.setBorderBottom((short) 1);
		    styleHeader.setBorderLeft((short) 1);           
		    styleHeader.setBorderRight((short) 1);
		    styleHeader.setBorderTop((short) 1);
		             
		    /* Hssf Cell styles area is END */    
		    
		    
		    /* Hssf Font styles area is Start */    
		    
		    HSSFFont fontHeader = (HSSFFont) workbook.createFont();
		     fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		     fontHeader.setColor(HSSFColor.WHITE.index);
		     styleHeader.setFont(fontHeader);
		     
		     /* HSSF Font styles area is END*/    
		    
		    HSSFCellStyle style1 = (HSSFCellStyle) workbook.createCellStyle();
		    
		    style1.setAlignment(style1.ALIGN_CENTER);
		    
		 
		  
		   /* problem area */
		    resultsExcelBO.setUserid(user.getUser_id());
		    resultsExcelBO.setTest_no(testno);
		    resultsExcelBO.setSection_id(resultsBO.getSection_id());
		    resultLessionBO.setUser_id(user.getUser_id());
		    resultLessionBO.setTest_no(testno);
		    resultLessionBO.setSection_id(resultsBO.getSection_id());
		 
		  try {
			  
			   List<ResultsExcelBO> excelDataList = new ArrayList<ResultsExcelBO>();
			   excelDataList=practiceSessionController.getExcelsheetDataPreparing(resultsExcelBO);
			   lessionsExcelData= practiceSessionController.getLessionExcelsheetData(resultLessionBO);
			  
			   Cell cell=null;
			   Row row =null;
			   int rownum=0;
			   
			   row = sheet.createRow(rownum++);
			   
			   cell = row.createCell(1);
			   cell.setCellValue(user.getFirst_name()+"    "+user.getLast_name());
			   cell.setCellStyle(styleHeader);
			   
			 
			    cell = row.createCell(3);
			   
			 
			   cell.setCellValue("Resport Date :"+DateUtil.getDate(DateFormate.MMMM_dd_YYY));
			   cell.setCellStyle(styleHeader);
			   styleHeader.setWrapText(true);
			   cell = row.createCell(4);
			   cell.setCellValue("   ");
			   row = sheet.createRow(rownum++);
			   row = sheet.createRow(rownum++);
			   row = sheet.createRow(rownum++);
			   row = sheet.createRow(rownum++);
			   cell = row.createCell(4);
			
			   cell.setCellValue("Test Summary");
			   cell.setCellStyle(styleHeader);
			   styleHeader.setWrapText(true);
			   row = sheet.createRow(rownum++);
			   row = sheet.createRow(rownum++);
			   cell = row.createCell(1);
			   cell.setCellValue("Question Type");
			   cell.setCellStyle(styleHeader);
			   styleHeader.setWrapText(true);
			   cell = row.createCell(2);
			   cell.setCellValue("Totla Questions ");
			   cell.setCellStyle(styleHeader);
			   styleHeader.setWrapText(true);
			   cell = row.createCell(3);
			   cell.setCellValue("Right");
			   cell.setCellStyle(styleHeader);
			   styleHeader.setWrapText(true);
			   cell = row.createCell(4);
			   cell.setCellValue("Wrong");
			   cell.setCellStyle(styleHeader);
			   styleHeader.setWrapText(true);
			   cell = row.createCell(5);
			   cell.setCellValue("Blank");
			   cell.setCellStyle(styleHeader);
			   styleHeader.setWrapText(true);
			 
			  
			
			   cell = row.createCell(6);
			   cell.setCellValue("% Right");
			   cell.setCellStyle(styleHeader);
			   styleHeader.setWrapText(true);
			  
			   cell = row.createCell(7);
			   cell.setCellValue("Average Difficulty Right");
			   cell.setCellStyle(styleHeader);
			   styleHeader.setWrapText(true);
			
			   cell = row.createCell(8);
			   cell.setCellValue("Average Difficulty Wrong");
			   cell.setCellStyle(styleHeader);
			   styleHeader.setWrapText(true);
			  
			  
			  
			     
			   for(ResultsExcelBO bb : excelDataList){
				   
				   row = sheet.createRow(rownum++);
				   cell = row.createCell(1);
				   cell.setCellValue(bb.getCategory_name()+"  "+bb.getQuestion_type_name());
				   cell.setCellStyle(style1);
				   style1.setWrapText(true);
				   cell = row.createCell(2);
				   cell.setCellValue(bb.getAttemptQuestions());
				   cell.setCellStyle(style1);
				   style1.setWrapText(true);
				   cell = row.createCell(3);
				   cell.setCellValue(bb.getCorrectQuestions());
				   cell.setCellStyle(style1);
				   style1.setWrapText(true);
				   cell = row.createCell(4);
				   cell.setCellValue(bb.getIncorrectQuestions());
				   cell.setCellStyle(style1);
				   style1.setWrapText(true);
				   cell = row.createCell(5);
				   cell.setCellValue(bb.getSkippedQuestions());
				   cell.setCellStyle(style1);
				   style1.setWrapText(true);
				   cell = row.createCell(6);
				   cell.setCellValue(bb.getPercentageCorrectQuestions()+"%");
				   cell.setCellStyle(style1);
				   style1.setWrapText(true);
				   cell = row.createCell(7);
				   cell.setCellValue(bb.getAvgDifficultyRightAnswers());
				   cell.setCellStyle(style1);
				   style1.setWrapText(true);
				   cell = row.createCell(8);
				   cell.setCellValue(bb.getAvgDifficultyWorngAnswers());
				   cell.setCellStyle(style1);
				   style1.setWrapText(true);
				   
				  
				 
			   }
			   
			   row = sheet.createRow(rownum++);
			   row = sheet.createRow(rownum++);
			   row = sheet.createRow(rownum++);
			   row = sheet.createRow(rownum++);
			   row = sheet.createRow(rownum++);
			   
			   cell = row.createCell(0);
			   cell.setCellValue("");
			  
			   cell = row.createCell(1);
			   cell.setCellValue("Lession Name");
			   cell.setCellStyle(styleHeader);
			   styleHeader.setWrapText(true);
			  
			   cell = row.createCell(2);
			   cell.setCellValue("Total Atempt Lessons");
			   cell.setCellStyle(styleHeader);
			   styleHeader.setWrapText(true);
			   cell = row.createCell(3);
			   cell.setCellValue("Right Lessons");
			   cell.setCellStyle(styleHeader);
			   styleHeader.setWrapText(true);
			   cell = row.createCell(4);
			   cell.setCellValue("Worng Lessons");
			   cell.setCellStyle(styleHeader);
			   styleHeader.setWrapText(true);
			   cell = row.createCell(5);
			   cell.setCellValue("% Righ Lessons");
			   cell.setCellStyle(styleHeader);
			   styleHeader.setWrapText(true);
			   cell =row.createCell(6);
			   cell.setCellValue("");
			   cell.setCellStyle(style1);
			   style1.setWrapText(true);
			
			    double maxValue=-1;
			    double minValue=-1;
			    double check = 0.0;
			    int maxLessionId=0;
			    int minLessionId=0;
			   
			     
			 

			    for(ResultLessionBO lessionsData:lessionsExcelData){
				 check = lessionsData.getPercentageOfLessons();
				 if(maxValue==-1)
				 {
					 maxValue=lessionsData.getPercentageOfLessons();
					 minValue=lessionsData.getPercentageOfLessons();
					 minLessionId=lessionsData.getLesson_id();
					 maxLessionId=lessionsData.getLesson_id();
				 }
				   if(check> maxValue){
				   maxValue = check;
				   maxLessionId=lessionsData.getLesson_id();
				   }
				   if(check<minValue){
					   minValue =check;
					   minLessionId=lessionsData.getLesson_id();
					   }	
			    }
			    System.out.println(" Min Lession id : "+minLessionId+" Max lession Id is; "+maxLessionId);
			    for(ResultLessionBO lessionsData:lessionsExcelData){
			    	System.out.println(" \n Lession id is; "+lessionsData.getLesson_id());
					   if(lessionsData.getLesson_id()==maxLessionId)
					   {
						   lessionsData.setLessonAccurate(" MOST ACCURATE");
						   lessionsData.setLessonResult("BEST");
					   }
					   if(lessionsData.getLesson_id()==minLessionId)
					   {
						   lessionsData.setLessonAccurate(" LEAST ACCURATE ");
						   lessionsData.setLessonResult("WORST");
					   }
				   }
			    for(ResultLessionBO lessionsData:lessionsExcelData){
					   
					   row = sheet.createRow(rownum++);
					   cell = row.createCell(0);
					   cell.setCellValue(lessionsData.getLessonResult());
					   cell.setCellStyle(style1);
					   style1.setWrapText(true);
					  
					   cell = row.createCell(1);
					   cell.setCellValue(lessionsData.getLesson_name());
					   cell.setCellStyle(style1);
					   style1.setWrapText(true);
					   
				    	   cell = row.createCell(2);
						   cell.setCellValue(lessionsData.getAttemp_lessons());
						   style1.setWrapText(true);
						   cell.setCellStyle(style1);
						   cell = row.createCell(3);
						   cell.setCellValue(lessionsData.getLessons_correct());
						   cell.setCellStyle(style1);
						   style1.setWrapText(true);
						  
						   cell = row.createCell(4);
						   cell.setCellValue(lessionsData.getLessons_incorrect());
						   cell.setCellStyle(style1);
						   style1.setWrapText(true);
						 
						   cell = row.createCell(5);
						   cell.setCellValue(lessionsData.getPercentageOfLessons()+"%");
						  
						   cell.setCellStyle(style1);
						   style1.setWrapText(true);
						   cell =row.createCell(6);
						   cell.setCellValue(lessionsData.getLessonAccurate());
						  
						   cell.setCellStyle(style1);
						   style1.setWrapText(true);
				    }
			   
			   row = sheet.createRow(rownum++);
			   row = sheet.createRow(rownum++);
			   row = sheet.createRow(rownum++);
			   row = sheet.createRow(rownum++);
			   
			   cell = row.createCell(1);
			   cell.setCellValue("This report was generated with DATA from the following exams:");
			   cell.setCellStyle(styleHeader);
			   style1.setWrapText(true);
			   row = sheet.createRow(rownum++);
			   for(ResultsExcelBO bb : excelDataList){
			   cell = row.createCell(1);
			   cell.setCellValue(bb.getTest_name());
			  
			   cell.setCellStyle(style1);
			   style1.setWrapText(true);
			   cell = row.createCell(3);
			   cell.setCellValue(bb.getTest_date());
			  
			   cell.setCellStyle(style1);
			   style1.setWrapText(true);
			   }
				    FileOutputStream out = null;
				    
					String filePath = request.getSession().getServletContext()
							.getRealPath("assets/img/Result/");
					File f = new File(filePath);
					if (!f.exists())
						f.mkdirs();
					filePath += File.separator + user.getUser_id() + ".xls";
					
				    System.out.println(filePath);
				    System.out.println(user.getEmail_id());
				   out = new FileOutputStream(new File(filePath));
					
				    workbook.write(out);
				    out.close();
				    
				    Date date=new Date();
				   
				    String subject="Practise session Result"+date;
				    String content="Thank for writeing the practise session";
				    
				    
				    try {
						MailAttachementUtil.sendMailToOne(subject,content, user.getEmail_id(),filePath );
					} catch (EmailExceptions e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    System.out.println("Excel written successfully..");
				   Desktop desktop=Desktop.getDesktop();
				    
				    
				   desktop.open(new File(filePath));
				 
			    
			   
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		
		
		
		
		return SUCCESS;
		
	}
	
	
	private HSSFWorkbook HSSFWorkbook_createCellStyle(HSSFWorkbook workbook) {
		// TODO Auto-generated method stub
		return null;
	}




	QuestionsUploadBO submitAnswerBO;  
	public QuestionsUploadBO getSubmitAnswerBO() {
		return submitAnswerBO;
	}

	
	public void setSubmitAnswerBO(QuestionsUploadBO submitAnswerBO) {
		this.submitAnswerBO = submitAnswerBO;
	}

	public void submitPraciceSessionAnswer() 
	{
		 
		System.out.println("IN ACTION CLASS ");
		int test_no=0;
		//QuestionsUploadBO submitAnswerBO=new QuestionsUploadBO();  
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();		
		System.out.println(" Question ID "+questionsUploadBO.getQuestion_id()+" : ANswersss :: "+this.getAnswers()+" :::  "+ questionsUploadBO.getLesson_id());
        
	     
		 System.out.println("Question answers:"+this.getAnswers().size()+"\t User answers:"+questionsUploadBO.getAnswers());
		 String skip_status=request.getParameter("skipStatus").toString();
		LoginUserBO userbo=(LoginUserBO)sessionMap.get("user"); 
		int user_id=userbo.getUser_id(); 
		questionsUploadBO.setUser_id(user_id);
		questionsUploadBO.setActivity_id(Integer.parseInt(sessionMap.get("test_activity_id").toString()));	
		questionsUploadBO.setTest_id(1);		
		questionsUploadBO.setTest_date(dateFormat.format(date));
		questionsUploadBO.setUser_time(request.getParameter("timeSpent").toString()); 		 
		practiceSessionController=new PracticeSessionController();	
	 
		String result="";
		boolean answer_status=true;
	    String answer_result="";
	    String isAnswered="";
		List<String> question_answers=new ArrayList<String>();   
		List<String> user_answers;
		 
		question_answers=this.getAnswers();
		test_no=Integer.parseInt(sessionMap.get("test_no").toString());
		 
		if(questionsUploadBO.getAnswers()==null || questionsUploadBO.getAnswers().toString().trim().length()==0 || questionsUploadBO.getAnswers().toString().contains("-1"))
		 {
			System.out.println(" IN NO SELECTED VALUES");
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
			 if(skip_status.equals(""))
				{
				    answer_result="INCORRECT"; 
					System.out.println("question un answered");
				}
				else
				{
					answer_result="SKIPPED";
					System.out.println("question is skipped");
				}
		 }   
		 questionsUploadBO.setResult(answer_result);
		 
		
		// submitAnswerBO.setQuestion_no(1);
		// submitAnswerBO.setStatus(isAnswered);
		 questionsUploadBO.setUser_answer(user_answers);
		 questionsUploadBO.setFlag_status(request.getParameter("flagStatus").toString());
		 questionsUploadBO.setTest_no(test_no);
		 questionsUploadBO.setSession_type("PRACTICE");
		 
		 System.out.println(" ANSWERRRRRRRRRRRRRR "+answer_result);
		JSONObject json = new JSONObject();
		try {
			json.put("result",answer_result);
			response.setContentType("text/JSON");
			response.getWriter().write(json.toString());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 requestMap.put("result", answer_result);
		 request.setAttribute("diff_level", questionsUploadBO.getDifficulty_id());
		  	 
		 List<QuestionsUploadBO> submitAnswerList=new ArrayList<QuestionsUploadBO>();
		 System.out.println("The lesson iddd is----------------------------"+questionsUploadBO.getLesson_id());
		 submitAnswerList.add(questionsUploadBO); 
		 try {
			practiceSessionController.submitPracticeSessionQuestion(submitAnswerList);
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result="success";
		//return result;
	}
		
	public String getQuestionTypePages(){
		String result="";
		System.out.println(request.getParameter("type_id"));
		int type=Integer.parseInt(request.getParameter("type_id"));
		int questionid=Integer.parseInt(request.getParameter("question_id"));
		try {
			performanceBarActions(questionid);
		} catch (ConnectionException | DBException | SQLException
				| JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" Type ID"+type+"     ::::  "+QuestionTypeUtil.getQuestionTypeID(Categories.QUNTITATIVE_COMPREHENSION, QuestionType.SINGLE_ANSWER));
		if(type==7)
		{
			result="quant_comparison";
		}
		else if(type==8)
		{
			result="problem_solving_single";
		}
		else if(type==9)
		{
			result="problem_solving_double";
		}
		else if(type==10)
		{
			result="problem_solving_single_fill";
		}
		else if(type==13)
		{
			result="problem_solving_double_fill";
		}
		else if(type==1)
		{
			result="text_completion_single";
		}
		else if(type==2)
		{
			result="text_completion_double";
		}
		else if(type==3)
		{
			result="text_completion_triple";
		}
		else if(type==6)
		{
			result="sentence_equivalence";
		}
		else if(type==4)
		{
			result="reading_comprehension_single";
		}
		else if(type==5)
		{
			result="reading_comprehension_double";
		}
		else if(type==11)
		{
			result="data_interpretation_single";
		}
		else if(type==12)
		{
			result="data_interpretation_double";
		}
		else if(type==15)
		{
			result="reading_comprehension_single";
		}
		else if(type==16)
		{
			result="data_interpretation_single_fill";
		}
		else if(type==17)
		{
			result="data_interpretation_double_fill";
		}

		
		System.out.println("Result   "+result);
		return result;
	}
	
	

	public void gettingQuestionsforPracticeTest(){
		System.out.println("Calling from start practice test");
		JSONObject jsonObject=new JSONObject();
		JSONObject jsonObjectString=new JSONObject();
		String result="";
		LoginUserBO bo = (LoginUserBO) sessionMap.get("user");
		System.out.println("Userid : "+bo.getUser_id()+"  Test No:"+sessionMap.get("test_no").toString());
		
		
		questionsUploadBO=new QuestionsUploadBO();
		questionsUploadBO.setUser_id(bo.getUser_id());
		questionsUploadBO.setTest_no(Integer.parseInt(sessionMap.get("test_no").toString()));
		try {
		  practiceSessionController=PracticeSessionController.getInstance();
		  System.out.println("Practice Query : "+(String)sessionMap.get("practiceQuery"));
		  this.setQuestionsList(practiceSessionController.gettingQuestionsforPracticeTest((String)sessionMap.get("practiceQuery")));	 
		  JSONArray jsonArray=new JSONArray();
		  for (int i = 0; i <this.getQuestionsList().size(); i++) {
			  try {
				  this.getQuestionsList().get(i).setQuestion_index(i+1);
			jsonObject=	ConvertBeanToJSON.ConvertObjectToMap(this.getQuestionsList().get(i));
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  jsonArray.put(jsonObject);
		}
		  
		  String questionsList=jsonArray.toString();
		  System.out.println("JSON OBJECT String     ::::: "+questionsList);
		  jsonObjectString.put("questions",questionsList);
		
		 System.out.println("JSON ARRAY "+jsonArray);
		  System.out.println("JSON OBJECT "+jsonObjectString.toString());
		  System.out.println("Total Questionssssssssss:"+getQuestionsList().size());
		  response.setContentType("text/JSON");
		  response.getWriter().write(jsonArray.toString());
		  
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result="quant_comparison";
		System.out.println(result);
		//return result;
	}
	
	
	
	
	
	
	
	
	
	
	public String createTestNumber(){
		System.out.println("In test creation------------------------------------------------");
		//System.out.println("Start Practice With:"+request.getParameter("sublessonid"));
		LoginUserBO bo = (LoginUserBO) sessionMap.get("user");
		int userid=bo.getUser_id();
		String query="";
		String sectionid=request.getParameter("sectionid");
		String lessonid=request.getParameter("sublessonid");
		if(request.getParameter("sublessonid")==null)
		{
		  query=checkData();
		  this.setAdaptiveModeInPage(getAdaptiveMode());
		  this.setTimeLimitInPage(getTimeLimitFromCustomization());
		}
		else
		{ 
			query= "select qls.*,qmas.*,qs.*,le.*," +
					" (select GROUP_CONCAT(skill.skill_name) from cp_skill_level skill where skill.skill_id in(qs.skill_id1,qs.skill_id2,qs.skill_id3)) as skills,(select dlevel.diff_description from cp_difficulty_level dlevel where dlevel.diff_id=qs.diff_id) as difficultyLevel,cat.category_name" +
					"  from cp_questions qs left join cp_question_lessons qls on qs.question_id= qls.question_id ,"
					+ " (cp_test_results trs right join  cp_question_masters qmas on qmas.question_id=trs.question_id and "
					+ " trs.userid='"+userid+"')left join cp_reading_comprehension_passage rpas  on rpas.passage_id=qmas.passage_id  ,"
					+ " cp_difficulty_level le,cp_question_categories cat"
					+ " WHERE qmas.test_id like '1' and  qs.question_id=qmas.question_id  and le.diff_id=qs.diff_id AND cat.category_id=qmas.category_id and qmas.section_id = "+ sectionid+" and qls.sublesson_id like '"+lessonid+"' group by qs.question_id";
			 //  query="select qls.*,qmas.*,qs.*,le.* from cp_questions qs left join cp_question_lessons qls on qs.question_id= qls.question_id , (cp_test_results trs right join  cp_question_masters qmas on qmas.question_id=trs.question_id and  trs.userid='"+userid+"')left join cp_reading_comprehension_passage rpas  on rpas.passage_id=qmas.passage_id  , cp_difficulty_level le  WHERE qmas.test_id like '1'and qs.question_id=qmas.question_id and le.diff_id=qs.diff_id and qmas.section_id = 1 and qls.sublesson_id in ("+request.getParameter("sublessonid").toString()+")  group by qs.question_id ";
			   System.out.println("the data is for sublessonid----------------------------------------"+query);
			   this.setAdaptiveModeInPage("YES");
			   this.setTimeLimitInPage("No Limit");
		}
	    
	System.out.println("query is -------------------------------: "+query);
	sessionMap.put("practiceQuery", query);
	practiceSessionController=new PracticeSessionController();	
	String testname=(String)request.getParameter("testname");
	if(testname==null)
		testname="Practise Test";
	
	try {
		int testno=practiceSessionController.createTestNumber(userid,1,testname);
		System.out.println("in test creation ::"+testno+"changes to be done"+request.getParameter("testname")+"time limit in customization page"+getTimeLimitFromCustomization()); 
		sessionMap.put("test_no", testno); 
		//this.setAdaptiveModeInPage(getAdaptiveMode());
		//this.setTimeLimitInPage(getTimeLimitFromCustomization());
		getActivityID();
		
	} catch (CPException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return "success";
	}
		
	public String getRetryQuestions(){
		
		
		int quesid=Integer.parseInt(request.getParameter("ques_id"));
		int typeid=Integer.parseInt(request.getParameter("type_id"));
		int testno = Integer.parseInt(request.getParameter("retrytestno"));
		//System.out.println("request the test number is"+request.getParameter("test_no")+"section id value"+request.getParameter("sectionidavlue")+"typeid value"+request.getParameter("typeidvalue"));
	    int sectionid=Integer.parseInt(request.getParameter("sectionnumber"));
	    System.out.println("Retry : "+request.getParameter("ques_id")+"testno : "+testno+"sectionid : "+sectionid+"Typed id : "+typeid);
	//	System.out.println("The data is "+sectionid+"test number is"+testno);
		
		requestMap.put("question_id", quesid);
		requestMap.put("test_no", testno);
		requestMap.put("sectionid", sectionid);
		requestMap.put("typeeid", typeid);
		
		return SUCCESS;
	}
	
	
	
	
public String averageTimeCalcultation(){
		
		String result ="Error".toLowerCase();
		
		QuestionsUploadBO questionUploadBO = null;
		try {
			practiceSessionController.averageTimeCalcultation(questionUploadBO);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return result;
		
		
		
	}
	



/**
 * @author kkatikala
 * @throws JSONException 
 * @throws SQLException 
 * @throws DBException 
 * 
 */
public void getStackedGraphForCategories(int sectionid,int test_no) throws DBException, SQLException, JSONException{
	
	
	System.out.println("*********************  getStackedGraphForCategories ****************************************************");
	
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
	
	
	System.out.println("Quant Query:::::::::::::::::::::::::::: "+query);
	
	
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
	
	if(sectionid == 1){
    jsonObject = practiceSessionController.getPercentageForCategories(query);
    categories="[\"Quantative Comparison\",\"Problem Solving Single\",\"Problem Solving Multiple\",\"Numeric Entry \",\"Data Interpretation\"]";
	}
	else if(sectionid == 2){
		jsonObject = practiceSessionController.getPercentageForCategories(verbalQuery);
		categories="[\"Sentence Equivalence\",\"Text Completion Single Blank\",\"Text Completion Double Blank\",\"Text Completion Triple Blank\",\"Reading Comprehension\",\"Critical Reasoning\"]";
	}
  //  System.out.println(jsonObject.get("TotalCount1")+"corect coount"+jsonObject.get("CorrectCount1")+"wrong count"+jsonObject.get("WrongCount1"));
    
	  
    System.out.println(jsonObject.toString());
    
    requestMap.put("datacount", jsonObject.toString());
    requestMap.put("categories", categories);
    requestMap.put("sectionid", sectionid);
	
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
	
		if(sectionid == 1){
			    jsonObject = practiceSessionController.getUserPaceValues(quantPaceQuery);
			 //   categories="[\"Quant Comparison\",\"Problem Solving\",\"Problem Solving Single\",\"Problem Solving Double\",\"Numeric Entry Single\",\"Numeric Entry Double\",\"Data Interpretation\"]";
		}
		else{
			jsonObject = practiceSessionController.getUserPaceValues(verbalPaceQuery);
			//categories="[\"Sentence Equivalence\",\"Text Completion\",\"Text Completion Single\",\"Text Completion Double\",\"Text Completion Triple\",\"Reading Comprehension\",\"Reading Comprehension Short\",\"Reading Comprehension Long\"]";
		}
			    
			    System.out.println(jsonObject.toString());
			    requestMap.put("userPace", jsonObject.toString());
				
	
}

 
	@Override
	public void setRequest(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		this.requestMap=requestMap;
		
	}  
	private List<ResultsBO> practiceSummaryTable_List;

	public String getPracticeSummaryChartData() {
		//System.out.println("calling summaryChart");
		QuestionsUploadBO questionUploadBO = new QuestionsUploadBO();
		IPracticeSessionController controller = PracticeSessionController
				.getInstance();
		LoginUserBO userBO = (LoginUserBO) sessionMap.get("user");
		
			
		if(userBO.getRole_id()!=4) questionUploadBO.setUser_id(Integer.parseInt(request.getParameterValues("selectedUser")[0].toString()));
		else questionUploadBO.setUser_id(userBO.getUser_id());
		try {
			practiceSummaryTable_List = controller
					.practiseSessionHistoryDetails(questionUploadBO);
		 
			 
			JSONArray xAxisData = new JSONArray();
			JSONArray Originaldata = new JSONArray();
			 

		
				for (int i = 0; i < practiceSummaryTable_List.size(); i++) {

					// Originaldata.put(10*i);
					// xAxisData.put("2014-Jun-0"+i);
					//System.out.println(practiceSummaryTable_List.get(i)
							//.getTest_date());
					xAxisData.put(practiceSummaryTable_List.get(i)
							.getTest_date()+"-"+practiceSummaryTable_List.get(i).getPractiseSessioName());
					Originaldata.put(Double
							.parseDouble(practiceSummaryTable_List.get(i)
									.getSessionCorrectPercentage())); 
					//sessionNames.put(practiceSummaryTable_List.get(i - 1).getTest_date(),practiceSummaryTable_List.get(i-1).getPractiseSessioName());
					//System.out.println("session Name : "+practiceSummaryTable_List.get(i-1).getPractiseSessioName());
					if (i ==9)
						break;

				}			 	
				//System.out.println(xAxisData + "\t" + Originaldata);
				//jsonData.put("xAxisData", xAxisData);
				//jsonData.put("Originaldata", Originaldata);
				//jsonData.put("sessionNames", sessionNames);
				//response.getWriter().write(jsonData.toString());
				
				
				requestMap.put("xAxisData", xAxisData);
				requestMap.put("Originaldata", Originaldata);
			} catch (JSONException e) {
				e.printStackTrace();

		} catch (ConnectionException e) {
			e.printStackTrace();
		}
return SUCCESS;
	}

	public List<ResultsBO> getPracticeSummaryTable_List() {
		return practiceSummaryTable_List;
	}
	
	

	public void setPracticeSummaryTable_List(
			List<ResultsBO> practiceSummaryTable_List) {
		this.practiceSummaryTable_List = practiceSummaryTable_List;
	}

	
	  public void retrySave(){
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
		     
	      	 practiceSessionController = new PracticeSessionController();
	      //	System.out.println("in retry saveeeeee and updateeeeeeeeeeeeeeeeeeeeeeeeeee"+request.getParameter("sectionidavlue")+"type id values"+request.getParameter("typeidvalue"));
			practiceSessionController.retrySave(questionsUploadBO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String checkData() {
		//System.out.println("Calling rom checkData()");

		LoginUserBO userBO = (LoginUserBO) sessionMap.get("user");

		int userId = userBO.getUser_id();
		String difficulty = request.getParameter("difficulty");
		String reuseMode = request.getParameter("reuseMode");
		String parents = request.getParameter("parents");
		String childs = request.getParameter("childs");
		String catigories = request.getParameter("catigories"); 
		String types = request.getParameter("types");
		System.out.println("Categories : "+catigories+"\n Types :"+types);
		String section = request.getParameter("section");

		String answered = request.getParameter("ANSWERED");
		String flag = request.getParameter("FLAG");
		String guessed = request.getParameter("GUESSED");
		
		String timelimitforcustomiza = request.getParameter("timeLimitFromCustomization");
		String questionslimit = request.getParameter("questionslimit");		
		
		
		if(types.contains("8")||types.contains("9")||types.contains("10")||types.contains("13"))
		{
			if(!catigories.isEmpty())
			catigories+=",6";
			else
				catigories+="6";
		}
		int time = 0;
				try{
				time=Integer.parseInt(request.getParameter("TIMEMODE"));
				}catch(NumberFormatException ex)
				{
					
				}

		String comprehenssion = request.getParameter("comprehenssion");
		String quiree = "select qls.*,qmas.*,qs.*,le.*," +
				" (select GROUP_CONCAT(skill.skill_name) from cp_skill_level skill where skill.skill_id in(qs.skill_id1,qs.skill_id2,qs.skill_id3)) as skills,(select dlevel.diff_description from cp_difficulty_level dlevel where dlevel.diff_id=qs.diff_id) as difficultyLevel,cat.category_name" +
				"  from cp_questions qs left join cp_question_lessons qls on qs.question_id= qls.question_id ,"
				+ " (cp_test_results trs right join  cp_question_masters qmas on qmas.question_id=trs.question_id and "
				+ " trs.userid="
				+ userId
				+ ")left join cp_reading_comprehension_passage rpas  on rpas.passage_id=qmas.passage_id  ,"
				+ " cp_difficulty_level le,cp_question_categories cat"
				+ " WHERE qmas.test_id like '1' and  qs.question_id=qmas.question_id  and le.diff_id=qs.diff_id AND cat.category_id=qmas.category_id and qmas.section_id = "
				+ section;
		String tail = "  group by qs.question_id order by rand() ";
		
		if(!questionslimit.equalsIgnoreCase("0"))
		{
			tail+= "Limit  "+questionslimit;
		}
		
		  //System.out.println("\n Difficulty : "+difficulty+" \n reuse Mode is: "
		//  +reuseMode+"\n Parents Is; "+parents+" childs is; "+childs+
		//  " categories : "+catigories+"" + "\n Types is; "+types+"\n GUessed : "+guessed+"\n Flag is: "+flag+"\n Answered : "+answered);
		  //System.out.println("\n Section : "+section+"\n ");
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
		/*if (!difficulty.isEmpty()) {
			quiree += "  and le.diff_description in (" + difficulty + ")";
		}
		if (!catigories.isEmpty()) {
			quiree += " and qmas.category_id in (" + catigories + ")";
		}
		if (!catigories.isEmpty()) {
			quiree += " and qmas.type_id in(select GROUP_CONCAT(qtype.question_type_id) from cp_question_type qtype where qtype.category_id in("+catigories.replace('6', '4')+") or qtype.question_type_id in("+types+"))";
		}*/
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
	// cat+="or qtype.question_type_id in("+types+"))";
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
	// System.out.println("\n\n\n\n\n Start Practice Query \n\t "+quiree);
		return quiree;

	} 
	
	
	/***
	 * 
	 * @author Srikanth ganji
	 * 
	 * Drill Chart for Practise Session
	 * 
	 * 
	 */
	
	
	 public void practiseSessionResultPieChartJsonData(){
		 
		//System.out.println("Practise Session  Pie Chart JsonData is calling:::::::::::::::::::::::::::::::::::::::::::::::::");
			QuestionsUploadBO questionUploadBO = new QuestionsUploadBO();
			practiceSessionController=PracticeSessionController.getInstance();
			LoginUserBO userBO = (LoginUserBO) sessionMap.get("user");
			   
		 //System.out.println("TestNumber::::::::::::::::::::::::"+request.getParameter("reviewTestNumber"));
			int testno=0;
			int sectionId=0;
			 if(request.getParameter("testno")==null)
			 {
				 testno=(int) sessionMap.get("test_no");
			 }
			 else
			 {
				 testno = Integer.parseInt(request.getParameter("testno"));
				// sectionId =Integer.parseInt(request.getParameter("sectionid"));
			 }
		 
		    System.out.println("practise session TestNumber::::::::::::::::::::::::::::::::::"+testno);
		 
		/* String practiseSessionResultPieChartJsonDataQuire="select (select count(*) from cp_test_results r " +
		 		"where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.result='CORRECT' )as TOTAL_CORRECT_ANSWERS," +
		 		"(select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" " +
		 		"and r.result='INCORRECT')as TOTAL_INCORRECT_ANSWERS,(select count(*) from cp_test_results r " +
		 		"where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.result='SKIPPED')as TOTAL_SKIPPED_ANSWERS," +
		 		"(select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and " +
		 		"r.flagstatus='FLAG' and r.result='CORRECT')as FLAG_QUESTIONS_CORRECT,(select count(*) " +
		 		"from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.guess='GUESS' " +
		 		"and r.result='CORRECT')as GUESS_QUESTIONS_CORRECT,(select count(*) from cp_test_results r" +
		 		" where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.guess='CONFIDENT' " +
		 		"and r.result='CORRECT')as CONFIDENT_QUESTIONS_CORRECT,(select count(*) from cp_test_results r" +
		 		" where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.guess='CONFIDENT' " +
		 		"and r.result='INCORRECT')as CONFIDENT_QUESTIONS_INCORRECT,(select count(*) from cp_test_results r" +
		 		" where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.guess='GUESS' " +
		 		"and r.result='INCORRECT')as GUESS_QUESTIONS_INCORRECT,(select count(*) from cp_test_results r " +
		 		"where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.flagstatus='FLAG' " +
		 		"and r.result='INCORRECT')as FLAG_QUESTIONS_INCORRECT,IFNULL(FORMAT(((select count(*) from cp_test_results r " +
		 		"where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.result='CORRECT')/(select count(*) from cp_test_results r " +
		 		"where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+"))*100,1),0.0) as TOTAL_CORRECT_QUESTIONS_PERCENTAGE," +
		 		"IFNULL(FORMAT(((select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and" +
		 		" r.test_no="+testno+" and r.result='INCORRECT')/(select count(*) from cp_test_results r " +
		 		"where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+"))*100,1),0.0) as TOTAL_INCORRECT_QUESTIONS_PERCENTAGE," +
		 		"IFNULL(FORMAT(((select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and " +
		 		"r.test_no="+testno+" and r.result='SKIPPED')/(select count(*) from cp_test_results r" +
		 		" where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+"))*100,1),0.0)as TOTAL_SKIPPED_ANSWERS_PERCENTAGE," +
		 		"IFNULL(FORMAT(((select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and " +
		 		"r.flagstatus='FLAG' and r.result='CORRECT' )/(select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and" +
		 		" r.test_no="+testno+" and r.result='CORRECT'))*100,1),0.0)as FLAG_QUESTIONS_CORRECT_PERCENTAGE," +
		 		"IFNULL(FORMAT(((select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and" +
		 		" r.flagstatus='FLAG' and r.result='INCORRECT')/(select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and" +
		 		" r.test_no="+testno+" and r.result='INCORRECT'))*100,1),0.0)as FLAG_QUESTIONS_INCORRECT_PERCENTAGE," +
		 		"IFNULL(FORMAT(((select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and" +
		 		" r.guess='GUESS' and r.result='CORRECT')/(select count(*) from cp_test_results r" +
		 		" where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.result='CORRECT'))*100,1),0.0)as" +
		 		" GUESS_QUESTIONS_CORRECT_PERCENTAGE,IFNULL(FORMAT(((select count(*) from cp_test_results r " +
		 		"where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.guess='GUESS' and r.result='INCORRECT')/" +
		 		"(select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and" +
		 		" r.result='INCORRECT'))*100,1),0.0)as GUESS_QUESTIONS_INCORRECT_PERCENTAGE," +
		 		"IFNULL(FORMAT(((select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and " +
		 		"r.test_no="+testno+" and r.guess='CONFIDENT' and r.result='CORRECT')/(select count(*) from cp_test_results r " +
		 		"where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.result='CORRECT'))*100,1),0.0)as " +
		 		"CONFIDENT_QUESTIONS_CORRECT_PERCENTAGE,IFNULL(FORMAT(((select count(*) from cp_test_results r" +
		 		" where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.guess='CONFIDENT' and r.result='INCORRECT')/" +
		 		"(select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.result='INCORRECT'))*100,1),0.0)as CONFIDENT_QUESTIONS_INCORRECT_PERCENTAGE," +
		 		"IFNULL(FORMAT(((select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and" +
		 		" r.test_no="+testno+" and r.flagstatus='FLAG')/(select count(*) from cp_test_results r" +
		 		" where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+"))*100,1),0.0)as FLAG_QUESTIONS_PERCENTAGE," +
		 		"IFNULL(FORMAT(((select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and" +
		 		" r.guess='GUESS')/(select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and" +
		 		" r.test_no="+testno+"))*100,1),0.0)as GUESS_QUESTIONS_PERCENTAGE from cp_test_results r " +
		 		"where r.test_no="+testno+" and r.userid="+userBO.getUser_id()+" and r.test_master_id=1 group by r.test_date" +
		 		" order by r.test_date desc limit 1";*/
		 
		 
		 String practiseSessionResultPieChartJsonDataQuire="select (select count(*) from cp_test_results r " +
			 		"where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.result='CORRECT' )as TOTAL_CORRECT_ANSWERS," +
			 		"(select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" " +
			 		"and r.result='INCORRECT')as TOTAL_INCORRECT_ANSWERS,(select count(*) from cp_test_results r " +
			 		"where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.result='SKIPPED')as TOTAL_SKIPPED_ANSWERS," +
			 		"(select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and " +
			 		"r.flagstatus='FLAG' and r.result='CORRECT')as FLAG_QUESTIONS_CORRECT,(select count(*) " +
			 		"from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.guess='GUESS' " +
			 		"and r.result='CORRECT')as GUESS_QUESTIONS_CORRECT,(select count(*) from cp_test_results r" +
			 		" where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.guess='CONFIDENT' " +
			 		"and r.result='CORRECT')as CONFIDENT_QUESTIONS_CORRECT,(select count(*) from cp_test_results r" +
			 		" where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.guess='CONFIDENT' " +
			 		"and r.result='INCORRECT')as CONFIDENT_QUESTIONS_INCORRECT,(select count(*) from cp_test_results r" +
			 		" where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.guess='GUESS' " +
			 		"and r.result='INCORRECT')as GUESS_QUESTIONS_INCORRECT,(select count(*) from cp_test_results r " +
			 		"where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.flagstatus='FLAG' " +
			 		"and r.result='INCORRECT')as FLAG_QUESTIONS_INCORRECT,IFNULL(FORMAT(((select count(*) from cp_test_results r " +
			 		"where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.result='CORRECT')/(select count(*) from cp_test_results r " +
			 		"where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+"))*100,1),0.0) as TOTAL_CORRECT_QUESTIONS_PERCENTAGE," +
			 		"IFNULL(FORMAT(((select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and" +
			 		" r.test_no="+testno+" and r.result='INCORRECT')/(select count(*) from cp_test_results r " +
			 		"where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+"))*100,1),0.0) as TOTAL_INCORRECT_QUESTIONS_PERCENTAGE," +
			 		"IFNULL(FORMAT(((select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and " +
			 		"r.test_no="+testno+" and r.result='SKIPPED')/(select count(*) from cp_test_results r" +
			 		" where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+"))*100,1),0.0)as TOTAL_SKIPPED_ANSWERS_PERCENTAGE," +
			 		"IFNULL(FORMAT(((select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and " +
			 		"r.flagstatus='FLAG' and r.result='CORRECT' )/(select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and" +
			 		" r.test_no="+testno+"))*100,1),0.0)as FLAG_QUESTIONS_CORRECT_PERCENTAGE," +
			 		"IFNULL(FORMAT(((select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and" +
			 		" r.flagstatus='FLAG' and r.result='INCORRECT')/(select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and" +
			 		" r.test_no="+testno+"))*100,1),0.0)as FLAG_QUESTIONS_INCORRECT_PERCENTAGE," +
			 		"IFNULL(FORMAT(((select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and" +
			 		" r.guess='GUESS' and r.result='CORRECT')/(select count(*) from cp_test_results r" +
			 		" where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+"))*100,1),0.0)as" +
			 		" GUESS_QUESTIONS_CORRECT_PERCENTAGE,IFNULL(FORMAT(((select count(*) from cp_test_results r " +
			 		"where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.guess='GUESS' and r.result='INCORRECT')/" +
			 		"(select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+"))*100,1),0.0)as GUESS_QUESTIONS_INCORRECT_PERCENTAGE," +
			 		"IFNULL(FORMAT(((select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and " +
			 		"r.test_no="+testno+" and r.guess='CONFIDENT' and r.result='CORRECT' and r.flagstatus='UNFLAG')/(select count(*) from cp_test_results r " +
			 		"where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+"))*100,1),0.0)as " +
			 		"CONFIDENT_QUESTIONS_CORRECT_PERCENTAGE,IFNULL(FORMAT(((select count(*) from cp_test_results r" +
			 		" where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and r.guess='CONFIDENT' and r.result='INCORRECT' and r.flagstatus='UNFLAG')/" +
			 		"(select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+"))*100,1),0.0)as CONFIDENT_QUESTIONS_INCORRECT_PERCENTAGE," +
			 		"IFNULL(FORMAT(((select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and" +
			 		" r.test_no="+testno+" and r.flagstatus='FLAG')/(select count(*) from cp_test_results r" +
			 		" where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+"))*100,1),0.0)as FLAG_QUESTIONS_PERCENTAGE," +
			 		"IFNULL(FORMAT(((select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and r.test_no="+testno+" and" +
			 		" r.guess='GUESS')/(select count(*) from cp_test_results r where r.userid="+userBO.getUser_id()+" and" +
			 		" r.test_no="+testno+"))*100,1),0.0)as GUESS_QUESTIONS_PERCENTAGE,(select count(*) from cp_test_results) as Total_Questions  from cp_test_results r " +
			 		"where r.test_no="+testno+" and r.userid="+userBO.getUser_id()+" and r.test_master_id=1 group by r.test_date" +
			 		" order by r.test_date desc limit 1";
		 
		 
		 
		 System.out.println("practise Session Result Page Json Data QUERY::::::::::::::::"+practiseSessionResultPieChartJsonDataQuire);
		 
		  JSONObject jsonObjectData = new JSONObject();
	       JSONArray jsonArray_Total_Correct= new JSONArray();
	       JSONArray jsonArray_Total_INCorrect= new JSONArray();
	       JSONArray jsonArray_Total_Skipped= new JSONArray();
	       JSONArray jsonArray_Total_Flagged= new JSONArray();
	       JSONArray jsonArray_Total_Guessed= new JSONArray();
	       JSONArray jsonArray_Confident_Correct= new JSONArray();
	       JSONArray jsonArray_Guess_Correct= new JSONArray();
	       JSONArray jsonArray_Flag_Correct= new JSONArray();
	       JSONArray jsonArray_Confident_INCorrect= new JSONArray();
	       JSONArray jsonArray_Guess_INCorrect= new JSONArray();
	       JSONArray jsonArray_Flag_INCorrect= new JSONArray();
	      
	       
	       IQuestionUploadController questionUploadController=new QuestionUploadController();
		   JSONObject object=new JSONObject();
		   try {
			jsonObjectData.put("inputQuiree",practiseSessionResultPieChartJsonDataQuire);
			  questionUploadController.requiredTableDataInJSONFormate(jsonObjectData);
			  JSONArray tableData=jsonObjectData.getJSONArray("tableData");
			  System.out.println("Jsosn Table Data for in practise session::::::::::::::::::::::"+tableData);
			    
			  for(int i=0;i<tableData.length();i++){
				  
				  jsonArray_Total_Correct.put(tableData.getJSONObject(i).getString("TOTAL_CORRECT_QUESTIONS_PERCENTAGE"));
				  jsonArray_Total_INCorrect.put(tableData.getJSONObject(i).getString("TOTAL_INCORRECT_QUESTIONS_PERCENTAGE"));
				  jsonArray_Total_Skipped.put(tableData.getJSONObject(i).getString("TOTAL_SKIPPED_ANSWERS_PERCENTAGE"));
				  jsonArray_Total_Flagged.put(tableData.getJSONObject(i).getString("FLAG_QUESTIONS_PERCENTAGE"));
				  jsonArray_Total_Guessed.put(tableData.getJSONObject(i).getString("GUESS_QUESTIONS_PERCENTAGE"));
				  jsonArray_Confident_Correct.put(tableData.getJSONObject(i).getString("CONFIDENT_QUESTIONS_CORRECT_PERCENTAGE"));
				  jsonArray_Guess_Correct.put(tableData.getJSONObject(i).getString("GUESS_QUESTIONS_CORRECT_PERCENTAGE"));
				  jsonArray_Flag_Correct.put(tableData.getJSONObject(i).getString("FLAG_QUESTIONS_CORRECT_PERCENTAGE"));
				  jsonArray_Confident_INCorrect.put(tableData.getJSONObject(i).getString("CONFIDENT_QUESTIONS_INCORRECT_PERCENTAGE"));
				  jsonArray_Guess_INCorrect.put(tableData.getJSONObject(i).getString("GUESS_QUESTIONS_INCORRECT_PERCENTAGE"));
				  jsonArray_Flag_INCorrect.put(tableData.getJSONObject(i).getString("FLAG_QUESTIONS_INCORRECT_PERCENTAGE"));
				  
				  
				  
			  }
			  
			  object.put("toatal_correct_percentage",jsonArray_Total_Correct );
			  object.put("toatal_Incorrect_percentage",jsonArray_Total_INCorrect);
			  object.put("total_skipped_percentage", jsonArray_Total_Skipped);
			  object.put("total_flagged_percentage",jsonArray_Total_Flagged);
			  object.put("total_guessed_percentage",jsonArray_Total_Guessed );
			  object.put("confident_correct_percentage",jsonArray_Confident_Correct);
			  object.put("guess_correct_percentage",jsonArray_Guess_Correct);
			  object.put("flag_correct_percentage",jsonArray_Flag_Correct);
			  object.put("confident_incorrect_percentage",jsonArray_Confident_INCorrect);
			  object.put("guess_incorrect_percentage",jsonArray_Guess_INCorrect);
			  object.put("flag_incorrect_percentage",jsonArray_Flag_INCorrect);
			  
			  
			  
			  
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
	
	
	

}

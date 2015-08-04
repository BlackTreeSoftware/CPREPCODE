package com.greenbuds.crunchprep.action.contentcreator;

 
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.directwebremoting.util.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.greenbuds.crunchprep.controller.common.CommonsController;
import com.greenbuds.crunchprep.controller.contentcreator.LessonController;
import com.greenbuds.crunchprep.controller.contentcreator.ProblemSolvingController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.bo.common.DifficultyBO;
import com.greenbuds.crunchprep.bo.common.SkillBO;
import com.greenbuds.crunchprep.bo.common.TestsBO;
import com.greenbuds.crunchprep.bo.contentcreator.SubLessonBO;
import com.greenbuds.crunchprep.bo.contentcreator.DataInterpretionBO;
import com.greenbuds.crunchprep.controller.contentcreator.DataInterpretionController;
import com.greenbuds.crunchprep.bo.contentcreator.QuestionLessonsBO;
import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.bo.contentcreator.ChoicesBO;
import com.greenbuds.crunchprep.bo.contentcreator.AnswersBO;
import com.greenbuds.crunchprep.controller.contentcreator.QuestionUploadController; 

public class ProblemSolvingAction extends ActionSupport implements ServletRequestAware{
private CommonsController commonsController;
private LessonController lessonController;
private List<TestsBO> tests;
private List<DifficultyBO> diff_levels;
private List<SkillBO> skills;
private List<QuestionsUploadBO> questionsList; 
private List<QuestionLessonsBO> questionsLessonList; 
private List<ChoicesBO> choiceList;
private List<AnswersBO> answersList;
private String[] recordsToDelete;
private List<String> choices_single;
private List<String> choices_multiple;
private List<String> answers_multiple;
public List<String> getAnswers_multiple() {
	return answers_multiple;
}

public void setAnswers_multiple(List<String> answers_multiple) {
	this.answers_multiple = answers_multiple;
}

public List<String> getChoices_single() {
	return choices_single;
}

public void setChoices_single(List<String> choices_single) {
	this.choices_single = choices_single;
}

public List<String> getChoices_multiple() {
	return choices_multiple;
}

public void setChoices_multiple(List<String> choices_multiple) {
	this.choices_multiple = choices_multiple;
}



private int question_id;
public int getQuestion_id() {
	return question_id;
}

public void setQuestion_id(int question_id) {
	this.question_id = question_id;
}

public String[] getRecordsToDelete() {
	return recordsToDelete;
}

public void setRecordsToDelete(String[] recordsToDelete) {
	this.recordsToDelete = recordsToDelete;
}

public List<ChoicesBO> getChoiceList() {
	return choiceList;
}

public void setChoiceList(List<ChoicesBO> choiceList) {
	this.choiceList = choiceList;
}

public List<AnswersBO> getAnswersList() {
	return answersList;
}

public void setAnswersList(List<AnswersBO> answersList) {
	this.answersList = answersList;
}

public List<QuestionsUploadBO> getQuestionsList() {
	return questionsList;
}

public void setQuestionsList(List<QuestionsUploadBO> questionsList) {
	this.questionsList = questionsList;
}
 

public List<QuestionLessonsBO> getQuestionsLessonList() {
	return questionsLessonList;
}

public void setQuestionsLessonList(List<QuestionLessonsBO> questionsLessonList) {
	this.questionsLessonList = questionsLessonList;
}



private List<SubLessonBO> subLessons;
private List<DataInterpretionBO> dataInterpretiongrahps;
private DataInterpretionController dataInterpretionController;
private QuestionLessonsBO questionLessonsBO;
private QuestionsUploadBO questionsUploadBO;
private QuestionUploadController  questionUploadController;
private ChoicesBO choicesBO;
private AnswersBO answersBO;
private String answer;
private String successMsg;
private String errorMsg;

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

public String getAnswer() {
	return answer;
}

public void setAnswer(String answer) {
	this.answer = answer;
}

private ProblemSolvingController problemSolvingController;

public QuestionLessonsBO getQuestionLessonsBO() {
	return questionLessonsBO;
}

public void setQuestionLessonsBO(QuestionLessonsBO questionLessonsBO) {
	this.questionLessonsBO = questionLessonsBO;
}

public QuestionsUploadBO getQuestionsUploadBO() {
	return questionsUploadBO;
}

public void setQuestionsUploadBO(QuestionsUploadBO questionsUploadBO) {
	this.questionsUploadBO = questionsUploadBO;
}

public ChoicesBO getChoicesBO() {
	return choicesBO;
}

public void setChoicesBO(ChoicesBO choicesBO) {
	this.choicesBO = choicesBO;
}

public AnswersBO getAnswersBO() {
	return answersBO;
}

public void setAnswersBO(AnswersBO answersBO) {
	this.answersBO = answersBO;
}

public List<SubLessonBO> getSubLessons() {
	return subLessons;
}
 
public void setSubLessons(List<SubLessonBO> subLessons) {
	this.subLessons = subLessons;
}
 
private Logger logger=Logger.getLogger(this.getClass());
private HttpServletRequest request;
 
 
public List<DifficultyBO> getDiff_levels() {
	return diff_levels;
}
public void setDiff_levels(List<DifficultyBO> diff_levels) {
	this.diff_levels = diff_levels;
}

public String problemSolvingTable()
{
	String result="success";
	questionUploadController=new QuestionUploadController();
	try {
		questionsList=questionUploadController.getQuestionsList(1, new int[]{5,6}, new int[]{8,9,11,12});
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result;
}
public String execute()
{
	String result="";
	result="success";
	commonsController=commonsController.getInstance();
	lessonController=lessonController.getInstance();
	dataInterpretionController=dataInterpretionController.getInstance();
	questionUploadController=new QuestionUploadController();
	
	try {
		tests=commonsController.getTestsList();  
		try {
			 String sectionid=request.getParameter("sectionId").toString();
			if(request.getParameter("questionid")==null)
			{
				diff_levels=commonsController.getDifficultiesList();
				skills=commonsController.getSkillsList(sectionid); 
				subLessons=lessonController.getSubLessons(-1); 
				dataInterpretiongrahps=dataInterpretionController.getDataInterpretionList(new DataInterpretionBO()); 
			}
			else
			{
				System.out.println("Questionid:"+request.getParameter("questionid"));
				diff_levels=commonsController.getDifficultiesList();
				skills=commonsController.getSkillsList(sectionid); 
				subLessons=lessonController.getSubLessons(-1);  
				dataInterpretiongrahps=dataInterpretionController.getDataInterpretionList(new DataInterpretionBO()); 
				questionsLessonList=questionUploadController.getQuestionLessonsList(); 
				questionsList=questionUploadController.getQuestionsList(1, new int[]{5,6}, new int[]{8,9,11,12}); 
				choiceList=questionUploadController.getChoicesList();
				answersList=questionUploadController.getAnswersList();
				System.out.println("Called");
				System.out.println("Total Questions:"+questionsList.size());
				for(int i=0;i<questionsList.size();i++)
				{
					QuestionsUploadBO questions=(QuestionsUploadBO)questionsList.get(i);
					List<String> lesson=new ArrayList<String>();
					List<String> answer=new ArrayList<String>();
					List<String> choice=new ArrayList<String>();
					List<String> skills=new ArrayList<String>();
					if(questions.getQuestion_id()==Integer.parseInt(request.getParameter("questionid").toString()))
					{    
						this.setQuestionsUploadBO(questions);
						System.out.println("Question title:"+getQuestionsUploadBO().getQuestion_title());
						for(QuestionLessonsBO lessons:questionsLessonList)
						{
							if(lessons.getQuestion_id()==questions.getQuestion_id())
							{
								lesson.add(""+lessons.getSublesson_id());
							}
						}
						for(AnswersBO answers:answersList)
						{
							if(answers.getQuestion_id()==questions.getQuestion_id())
							{
								answer.add(answers.getAnswer());
							}
						}
						for(ChoicesBO choices:choiceList)
						{
							if(choices.getQuestion_id()==questions.getQuestion_id())
							{
								//System.out.println("choice:"+choices.getChoice());
								choice.add(choices.getChoice());
							}
						}
						skills.add(""+questions.getSkillid1());
						skills.add(""+questions.getSkillid2());
						skills.add(""+questions.getSkillid3()); 
						 
						questionsUploadBO.setLessons(lesson);
						questionsUploadBO.setSkills(skills);
						/*questionsUploadBO.setChoices(choice);
						questionsUploadBO.setAnswers(answer);*/
						if(questions.getQuestiontype_id()==8||questions.getQuestiontype_id()==11) 
						{
							setChoices_single(choice);
							setAnswer(answer.get(0));
						}
						if(questions.getQuestiontype_id()==9||questions.getQuestiontype_id()==12) 
						{
							setAnswers_multiple(answer);
							setChoices_multiple(choice);
						}
					}  
					
					
				} 
			} 
			
		} catch (CPException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.debug(""+e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.debug(""+e);
		}
	} catch (ConnectionException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		logger.debug(""+e);
	} catch (DBException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		logger.debug(""+e);
	}
	return result;
}
public String saveProblemSolvingQuestion()
{
	String result=""; 
	problemSolvingController=problemSolvingController.getInstance();
	/*      System.out.println(" Test Name:"+getQuestionsUploadBO().getTest_id()+"\t Lesson"+getQuestionsUploadBO().getLesson_id()+"\t Title:"+getQuestionsUploadBO().getQuestion_title()
			+"\t Directions:"+this.getQuestionsUploadBO().getQuestion_directions()+"\t Question :"+request.getParameter("question")+"\t Choice1:"+getQuestionsUploadBO().getChoices().get(0)+
			"\t Choice2:"+getQuestionsUploadBO().getChoices().get(1)+"\t Choice3:"+getQuestionsUploadBO().getChoices().get(2)+"\t Choice4:"+getQuestionsUploadBO().getChoices().get(4)+
			"\t Choice5:"+getQuestionsUploadBO().getChoices().get(4)+"\t Answers:"+getQuestionsUploadBO().getAnswers().get(0)+"\t skill1:"+getQuestionsUploadBO().getSkills().get(0)
			+"\t skill2:"+getQuestionsUploadBO().getSkills().get(1)+"\t Difficulty level:"+getQuestionsUploadBO().getDifficulty_id()+"\t solution:"+request.getParameter("solution")
			+"\t Video:"+getQuestionsUploadBO().getSolution_video()+"\t Question type:"+request.getParameter("question_type")+"\t Graph id:"+getQuestionsUploadBO().getGraphId()
			+"\t Category:"+request.getParameter("question_category")+"\t Fee type:"+request.getParameter("fee_type")+"\t Referral:"+request.getParameter("referral")
			+"\t Status:"+getQuestionsUploadBO().getStatus());   */ 
	
	
	getQuestionsUploadBO().setQuestion_no(1);
	getQuestionsUploadBO().setQuestion(request.getParameter("question").toString());
    getQuestionsUploadBO().setSolution_text(request.getParameter("solution").toString());
    getQuestionsUploadBO().setAccess_type(request.getParameter("fee_type").toString());
    getQuestionsUploadBO().setRefferal(request.getParameter("referral").toString()); 
    getQuestionsUploadBO().setSection_id(1);
	
	if(request.getParameter("question_category").toString().equals("normal"))
	{
		getQuestionsUploadBO().setCategory_id(6);
		if(request.getParameter("question_type").toString().equals("single"))
		{
			getQuestionsUploadBO().setQuestiontype_id(8);
			getQuestionsUploadBO().setTypeId(8);
			List<String> ans=new ArrayList<String>();
			ans.add(this.getAnswer());
			getQuestionsUploadBO().setAnswers(ans);
			getQuestionsUploadBO().setChoices(getChoices_single());
		} 
		if(request.getParameter("question_type").toString().equals("multiple"))
		{
			getQuestionsUploadBO().setQuestiontype_id(9);
			List<String> ans=new ArrayList<String>();
			getQuestionsUploadBO().setTypeId(9);
			/*System.out.println("Choice 1:"+getAnswers_multiple().get(0)+"\tChoice 2:"+getAnswers_multiple().get(1)+"\tChoice 3:"+getAnswers_multiple().get(2)+
					"\tChoice 4:"+getAnswers_multiple().get(3)+"\tChoice 5:"+getAnswers_multiple().get(4)+"\tChoice 6:"+getAnswers_multiple().get(5));*/
			 
			int i=0;
			for(String s:getAnswers_multiple())
			{
				i++;
				if(s.equals(""))
				{
					
				}
				else
				{
					//System.out.println("Choice:"+i+":"+s);
					ans.add(s);
				}
				
			} 
			getQuestionsUploadBO().setChoices(getChoices_multiple());
			getQuestionsUploadBO().setAnswers(ans); 
		} 
	}
	if(request.getParameter("question_category").toString().equals("datainter"))
	{
		getQuestionsUploadBO().setCategory_id(5);
		if(request.getParameter("question_type").toString().equals("single"))
		{
			getQuestionsUploadBO().setQuestiontype_id(11);
			getQuestionsUploadBO().setTypeId(11);
			List<String> ans=new ArrayList<String>();
			ans.add(this.getAnswer());
			getQuestionsUploadBO().setAnswers(ans);
			getQuestionsUploadBO().setChoices(getChoices_single());
		} 
		if(request.getParameter("question_type").toString().equals("multiple"))
		{
			getQuestionsUploadBO().setQuestiontype_id(12);
			getQuestionsUploadBO().setTypeId(12);
			List<String> ans=new ArrayList<String>();
			int i=0;
			for(String s:getAnswers_multiple())
			{
				i++;
				if(s.equals(""))
				{
					
				}
				else
				{
					//System.out.println("Choice:"+i+":"+s);
					ans.add(s);
				}
				
			} 
			getQuestionsUploadBO().setChoices(getChoices_multiple());
			getQuestionsUploadBO().setAnswers(ans); 
		} 
	}
  try {
	  if(this.getQuestion_id()!=0)
	  {
		  questionUploadController=new QuestionUploadController();
		  List<QuestionsUploadBO> updateBo=new ArrayList<QuestionsUploadBO>();
		  getQuestionsUploadBO().setQuestion_id(getQuestion_id());
		  getQuestionsUploadBO().setAvg_time("00:00:00");
		  updateBo.add(getQuestionsUploadBO()); 
		  if(questionUploadController.updateQuestions(updateBo))
		  {
			    this.setSuccessMsg("Problem solving question updated successfully!");
				result="success";
		  }
			else
			{
				this.setErrorMsg("Error Occured while updating Problem solving question!");
				result="error";
			}
	  }
	  else
	  {
		  questionUploadController=new QuestionUploadController(); 
		  if(problemSolvingController.saveProblemSolvingQuestion(getQuestionsUploadBO()))
			{ 
				this.setSuccessMsg("Problem solving question saved successfully");
				result="success";
			}
			else
			{
				this.setErrorMsg("Error Occured while saving Problem solving question");
				result="error";
			} 
	  }
	  questionsList=questionUploadController.getQuestionsList(1, new int[]{5,6}, new int[]{8,9,11,12}); 
		
	} catch (CPException e) {
		// TODO Auto-generated catch block
		logger.debug(""+e);
		//e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}    
	return result;

}
public String deleteProblemSolvingQuestion()
{
	String result=""; 
	questionUploadController=new QuestionUploadController();
	try {
		 if(questionUploadController.deleteQuestions(getRecordsToDelete()))
		{
			this.setSuccessMsg("Problem solving question deleted successfully!");
			result="success";
		}
		else
		{
			this.setErrorMsg("Error Occured while deleting Problem solving question!");
			result="error";
		} 
	    questionsList=questionUploadController.getQuestionsList(1, new int[]{5,6}, new int[]{8,9,11,12});
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		logger.debug(""+e);
	} 
	return result;
}

public String viewProblemSolving()
{
	try {
		questionUploadController=new QuestionUploadController();
		questionsList=questionUploadController.getQuestionsList(1, new int[]{5,6}, new int[]{8,9,11,12});
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		logger.debug(""+e);
	}
	return SUCCESS;
}
public List<DataInterpretionBO> getDataInterpretiongrahps() {
	return dataInterpretiongrahps;
}

public void setDataInterpretiongrahps(
		List<DataInterpretionBO> dataInterpretiongrahps) {
	this.dataInterpretiongrahps = dataInterpretiongrahps;
}

public List<SkillBO> getSkills() {
	return skills;
}
public void setSkills(List<SkillBO> skills) {
	this.skills = skills;
}
public List<TestsBO> getTests() {
	return tests;
}
public void setTests(List<TestsBO> tests) {
	this.tests = tests;
}

@Override
public void setServletRequest(HttpServletRequest request) {
	// TODO Auto-generated method stub
	this.request=request;
}
}

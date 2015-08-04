package com.greenbuds.crunchprep.util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.controller.contentcreator.IQuestionUploadController;
import com.greenbuds.crunchprep.controller.contentcreator.QuestionUploadController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.MathAlgorithm;
import com.greenbuds.crunchprep.setups.Quantitative.EIGHT_QUESTIONS;
import com.greenbuds.crunchprep.setups.Quantitative.SEVEN_QUESTIONS;

import com.greenbuds.crunchprep.setups.DifficultyAlgorithm;
import com.greenbuds.crunchprep.setups.QuantQuestions;
import com.greenbuds.crunchprep.setups.Quantitative;
import com.greenbuds.crunchprep.util.DoubtsCLear;


public class MathQuestions {

	/**
	 * @param args
	 * @throws JSONException 
	 */

static String userQueAnsCondition=" and if(0!=sub1.cnt,mast.question_id NOT IN (SELECT results.question_id FROM cp_test_results results WHERE results.userid LIKE '10069') ,true)";
static String userQueAnsCondition1="  and mast.question_id not in(SELECT results.question_id FROM cp_test_results results WHERE results.userid LIKE '10069')";

static	 String userAnsQue=",(select count(*) cnt from";
static	   String userAnsQueEnd="sub)sub1";
static  String temp="";
		 
public static int userId=10069;
	 public static void main(String a[]) throws JSONException{
		 JSONObject object=new JSONObject();
		/* try {
			 object.put("limit",3.0);
			getMathQuestions(object,userId);
			boolean isQuestionsAvilable=object.getBoolean("isQuestionAvilable");
			System.out.println("\n isQuetiion Avialbi is: "+isQuestionsAvilable);
			if(isQuestionsAvilable)
			{
				
			}
			else{
				System.out.println("\n Questions Not Avilable ");
			}
			
			
		} catch (ConnectionException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
	        //Difficulty Rating List	 
		  Integer i[]={1, 3, 3, 3, 4, 4, 5, 2, 2, 3, 4, 5, 2, 1, 1, 1, 4, 5, 3, 4};
      //List<Integer> randomList=Arrays.asList(i);
     // String QC7_QFinalQUery=prepareMathQuery(MathAlgorithm._7_4_1_1_3_2_1_1,randomList);
     // String QC8_QFinalQUery=prepareMathQuery(MathAlgorithm._8_1_4_4_2_1,randomList); 
      //Random Question Pattern
      
      //Data _1_2_1_2_1   _7_4_1_1_3_2_1_1 
    System.out.println("\n Final quiree is: \n\t"+prepareMathQuery(MathAlgorithm._7_4_1_1_3_2_1_1,Arrays.asList(i)));
	 }
		        //Difficulty Rating List	 
			 // String QC7_QFinalQUery=prepareMathQuery(MathAlgorithm._7_4_1_1_3_2_1_1,randomList);
	        // String QC8_QFinalQUery=prepareMathQuery(MathAlgorithm._8_1_4_4_2_1,randomList); 
	         //Random Question Pattern
	 public static void  getMathQuestions(JSONObject object,int userId) throws JSONException, ConnectionException, DBException{
		 
	 MathAlgorithm algorithm=null ;
	     	DoubtsCLear clear=new DoubtsCLear();
	     	
	     	JSONObject jsonObject = new JSONObject();
	        CPException exception;
	     	IQuestionUploadController questionUploadController=new QuestionUploadController();
	     	int rowCount=0;
	     	  String finalQuiree="";
	     	int loop=1;
	     	List<Integer> randomList=null;
	     	try{
	     	do{
	     	
	     		algorithm=MathAlgorithm.getRandomPattern();
	     	//	object.put("limit",3.0);
	     		double difficultyRange=3.0;//default if not specifiedany thing
				difficultyRange=object.getDouble("limit");
				object.put("limit",difficultyRange);
	     		object.put("pattern",algorithm);
	     		randomList=clear.alogirithmPreparation(object);	
	     		finalQuiree=prepareMathQuery(algorithm,randomList);
	     		//System.out.println("\n\n Final Quiree is :"+finalQuiree);
	     		//System.out.println("\n inputQuiree is : "+finalQuiree.replace("10069",String.valueOf(userId)));
	     		jsonObject.put("inputQuiree", (finalQuiree=finalQuiree.replace("10069",String.valueOf(userId))));
	     		//System.out.println("final query after user Id user id : "+userId+"  : "+finalQuiree);
	     		questionUploadController.requiredTableDataInJSONFormate(jsonObject);
                object.put("isDataAvilable", jsonObject.get("isDataAvilable"));
	     		
	     		if( jsonObject.getBoolean("isDataAvilable"))
	     		{
	     			object.put("tableData", jsonObject.get("tableData"));
	     		}
	     		object.put("generatedPattern",algorithm.getPatternNumber());
	     		rowCount=jsonObject.getInt("rowCount");
				//System.out.println("\n Row Count is: "+rowCount);
				
				if(rowCount!=20){
				//System.out.println("\n\n   Row COunt is; "+rowCount);
				jsonObject.remove("rowCount");
				jsonObject.remove("tableData");
				jsonObject.remove("isDataAvilable");
				jsonObject.remove("inputQuiree");
				}else{
					System.out.println("\n Ok  Data Getting successflly");
				}
			
				if(loop++==50)rowCount=20;
	     	}while(rowCount!=20);
	     	System.out.println("\n\n\n Loop COunt is; "+loop);
	     	if(loop>50) System.out.println("\n DataBase Not Contained Data");
	    	object.put("isQuestionAvilable", loop<50);
		//	System.out.println("\n Getting Quant Questions is; \n"+randomList);
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
		}catch(Exception ex)
		{
			System.err.println("Error is: "+ex.getMessage());
			ex.printStackTrace();
		}
	      
	       }        
		 
		 public static  String prepareMathQuery(MathAlgorithm algorithm,
					List<Integer> randomList) {
			  
			 String finalQuery="",quantComparision=" ",psSingle="",psDouble="",numericEntryQuery="";
			 String union=" union ";
			 
			 System.out.println("List of Difficulty Id's : "+randomList);
			 //Generating Main lessons in random order
			 List<Integer> mainLessonIds=new ArrayList<Integer>();
			 mainLessonIds.add(3);//FDP
			 mainLessonIds.add(4);//Arithmetic
			 mainLessonIds.add(5);//Algebra
			 mainLessonIds.add(6);//Geometry
			 mainLessonIds.add(8);//wordProblems 
			 mainLessonIds.add(9);//Statistics
			 java.util.Collections.shuffle(mainLessonIds);
		     System.out.println("\n Lesson Id's List for 4Q "+mainLessonIds+"\t sublist "+mainLessonIds.subList(0, 4));
		     //this is for Problem solving single selection 2Q categories
		     List<Integer> mainLessonIds1=new ArrayList<Integer>();
		     //this is for problem solving multiple selection and Numeric Entry
			  List<Integer> mainLessonIds2=new ArrayList<Integer>(); 
			  mainLessonIds1.addAll(mainLessonIds);
			  mainLessonIds1.add(7);//Coordinate Geometry 
			 java.util.Collections.shuffle(mainLessonIds1);
			  System.out.println("\n Lesson Id's List for 2Q "+mainLessonIds1+"\t sublist "+mainLessonIds1.subList(0,2));
		
			   if(mainLessonIds1.subList(0, 2).contains(7))
			   {
				   mainLessonIds2=mainLessonIds1.subList(3,7);
				   System.out.println("\n Lesson Id's list for MS and NumericEntry  SS2Q contains id '7' then sublist is :"+mainLessonIds2);
			   }
			   else
			   {
				   mainLessonIds2=mainLessonIds1.subList(0,4);
				   System.out.println("\n Lesson Id's list for MS and NumericEntry  SS2Q doesn't contains id '7' then sublist is :"+mainLessonIds2);
			   }
			 
			 	 switch (algorithm) {
			 	case _7_4_1_1_3_2_1_1: 
			 		 System.out.println("Matched Pattern : _7_4_1_1_3_2_1_1 ");
			 		 
			 		 
			 		 //Quant Comparision
			         quantComparision= getQuantComparisionData(Quantitative.SEVEN_QUESTIONS._2_2_1_1_1,randomList); 
			 		 System.out.println("QUant Comparision QUery : "+quantComparision);
			 		 
			 		 
			 		//Problem solving single choice 
			 		 psSingle=getProblemSolvingSingle(8,randomList.get(7),randomList.get(8),randomList.get(9),randomList.get(10),mainLessonIds.subList(0, 4)); 
			 		 System.out.println("Problem Solving Single : "+psSingle);
			 		 
			 		 
			 		//Problem solving Multiple choice 
			 		 psDouble=getProblemSolvingDouble(9, randomList.get(11),randomList.get(18),1,mainLessonIds2.get(0)); 
			 		 System.out.println("Problem Solving Double : "+psDouble);
			 		 
			 		 
			 		//Numeric Entry 
			 		if(randomList.get(12)==randomList.get(19))
			 		{
			 			 numericEntryQuery=getNumericEntry(randomList.get(12),randomList.get(19),1,mainLessonIds2.get(1));
			 		}
			 		else
			 		{
			 			 numericEntryQuery=getNumericEntry(randomList.get(12),mainLessonIds2.get(1));
			 		} 
			 	   System.out.println("Numeric Entry: "+ numericEntryQuery); 
			 	   
			 		//Data Interpretation 
			       System.out.println("Data Interpretation : "+getDataInterpretation((byte)3,randomList.get(13))); 

			 	   finalQuery=quantComparision+union+psSingle+union+psDouble+union+numericEntryQuery+union+getDataInterpretation((byte)3,randomList.get(13));
			 		
			 	
			 		//Problem Solving Single
			 	    psSingle=getProblemSolvingSingle(8,randomList.get(16),randomList.get(17),0,0,mainLessonIds1.subList(0,2)); 
			 	    System.out.println("Problem Solving Single1 : "+psSingle);
			 	    
			        //problem Solving Multiple
			 	     psDouble=getProblemSolvingDouble(9,randomList.get(18),randomList.get(11),2,mainLessonIds2.get(2)); 
			 		 System.out.println("Problem Solving Double1 : "+psDouble);
			 		 
			 		 
			 		//Numeric Entry
			 	
			 		if(randomList.get(12)==randomList.get(19))
			 		{
			 			 numericEntryQuery=getNumericEntry(randomList.get(12),randomList.get(19),2,mainLessonIds2.get(3));
			 		}
			 		else
			 		{
			 			 numericEntryQuery=getNumericEntry(randomList.get(19),mainLessonIds2.get(3));
			 		}
			 		 
			 	    System.out.println("Numeric Entry1 : "+numericEntryQuery);
			 		
			 	   finalQuery=finalQuery+union+psSingle+union+psDouble+union+numericEntryQuery; 
			 	   System.out.println("final QUery is : "+finalQuery);
			 		
			 		break;
				case _7_4_1_1_4_2_1:
					
					System.out.println("Matched Pattern :_7_4_1_1_4_2_1");
					
					//Quant Comparision
					quantComparision=getQuantComparisionData(Quantitative.SEVEN_QUESTIONS.getRandomPattern(),randomList);
					System.out.println("QUant Comparision QUery : "+quantComparision);
					
					//Problem solving single choice 
			 		psSingle=getProblemSolvingSingle(8,randomList.get(7),randomList.get(8),randomList.get(9),randomList.get(10),mainLessonIds.subList(0, 4));
			 	    System.out.println("Problem Solving Single : "+psSingle);
			 	    
			 		//Problem solving Multiple choice 
			 		psDouble=getProblemSolvingDouble(9, randomList.get(11),randomList.get(19),1,mainLessonIds2.get(0));			 		
			 		System.out.println("Problem Solving Double : "+psDouble);
			 		
			 		//Numeric Entry
			 		System.out.println("Numeric Entry: "+getNumericEntry(randomList.get(12),mainLessonIds2.get(1)) ); 
			 		
			 		//Data Interpretation 
			 		System.out.println("Data Interpretation : "+getDataInterpretation((byte)4,randomList.get(13)));
			 		
			 		finalQuery=quantComparision+union+psSingle+union+psDouble+union+getNumericEntry(randomList.get(12),mainLessonIds2.get(1))+union+getDataInterpretation((byte)4,randomList.get(13));
			 		//System.out.println("final QUery is : "+finalQuery);
			 		
			 		//Problem Solving Single
			 		psSingle=getProblemSolvingSingle(8, randomList.get(17),randomList.get(18),0,0,mainLessonIds1.subList(0,2));
			 		System.out.println("Problem Solving Single1 : "+psSingle);
			 		
			 		//problem Solving Multiple
			 		psDouble=getProblemSolvingDouble(9,randomList.get(19), randomList.get(11),2,mainLessonIds2.get(2));
			 		System.out.println("Problem Solving Double1 : "+psDouble);
			 		 
			 		finalQuery=finalQuery+union+psSingle+union+psDouble; 
			 	    System.out.println("final QUery is : "+finalQuery); 
					break;
					
			   case _8_4_1_4_2_1: 
					System.out.println("Matched Pattern :_8_1_4_4_2_1");
					
					//Quant Comparision
					quantComparision=getQuantComparisionData(Quantitative.EIGHT_QUESTIONS.getRandomPattern(),randomList);
					System.out.println("Quant Comparision : "+quantComparision); 
					
					//Problem solving single choice 
			 		psSingle=getProblemSolvingSingle(8, randomList.get(8),randomList.get(9),randomList.get(10),randomList.get(11),mainLessonIds.subList(0, 4));
			 		System.out.println("Problem Solving Single : "+psSingle); 
			 		
			 		//Problem solving Multiple choice 
			 		psDouble=getProblemSolvingDouble(9,randomList.get(12),randomList.get(19),1,mainLessonIds2.get(0));
			 		System.out.println("Problem Solving Double : "+psDouble);
			 		
			 		//Data Interpretation 
			 		System.out.println("Data Interpretation : "+getDataInterpretation((byte)4,randomList.get(13)));
			 		
			 		finalQuery=quantComparision+union+psSingle+union+psDouble+union+getDataInterpretation((byte)4,randomList.get(13)); 
			 		
			 		//Problem Solving Single
			 		psSingle=getProblemSolvingSingle(8,randomList.get(17),randomList.get(18),0,0,mainLessonIds1.subList(0,2));
			 		System.out.println("Problem Solving Single1 : "+psSingle);
			 		
			 		//problem Solving Multiple
			 		psDouble=getProblemSolvingDouble(9,randomList.get(19),randomList.get(12),2,mainLessonIds2.get(1));
			 		System.out.println("Problem Solving Double1 : "+psDouble);
			 		
			 		 
			 		finalQuery=finalQuery+union+psSingle+union+psDouble; 
			 	    System.out.println("final QUery is : "+finalQuery);
			 	    
					break;
		 case _8_4_1_1_3_2_1:
					System.out.println("Matched Pattern :_8_4_1_1_3_2_1");
					
					//Quant Comparision
					quantComparision=getQuantComparisionData(Quantitative.EIGHT_QUESTIONS._1_2_2_1_2,randomList);
					System.out.println("Quant Comparision : "+quantComparision); 
					
					//Problem solving single choice 
			 		psSingle=getProblemSolvingSingle(8,randomList.get(8),randomList.get(9),randomList.get(10),randomList.get(11),mainLessonIds.subList(0, 4));
			 		System.out.println("Problem Solving Single : "+psSingle);
			 		
			 		//Problem solving Multiple choice 
			 		psDouble=getProblemSolvingDouble(9, randomList.get(12),randomList.get(19),1,mainLessonIds2.get(0));
			 		System.out.println("Problem Solving Double : "+psDouble);
			 		
			 		//Numeric Entry 
			 		System.out.println("Numeric Entry: "+getNumericEntry(randomList.get(13),mainLessonIds2.get(1))); 
			 		
			 		//Data Interpretation 
			 		System.out.println("Data Interpretation : "+getDataInterpretation((byte)3,randomList.get(14)));
			 		
			 		finalQuery=quantComparision+union+psSingle+union+psDouble+union+getNumericEntry(randomList.get(12),mainLessonIds2.get(1))+union+getDataInterpretation((byte)3,randomList.get(14));
			 		 
			 		//Problem Solving Single
			 		psSingle=getProblemSolvingSingle(8, randomList.get(17),randomList.get(18),0,0,mainLessonIds1.subList(0,2));
			 		System.out.println("Problem Solving Single1 : "+psSingle);
			 		
			 		//problem Solving Multiple
			 		psDouble=getProblemSolvingDouble(9, randomList.get(19), randomList.get(12),2,mainLessonIds2.get(2));
			 		System.out.println("Problem Solving Double1 : "+psDouble);
			 		 
			 		finalQuery=finalQuery+union+psSingle+union+psDouble; 
			 	    System.out.println("final QUery is : "+finalQuery);
					break; 
				 
				}
			 return finalQuery;
		 }
		 
		 
		 
			public static String getQuantComparisionData(SEVEN_QUESTIONS seven_QUESTIONS,List<Integer> randomList)
			{
				String union="union",finalQuery="";
			//	String countCondition=userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd;
				 
				String query=" (select que.diff_id,mast.question_id,mast.category_id,mast.type_id from cp_question_masters mast,cp_questions que";
				String query11=" where mast.question_id=que.question_id and  mast.category_id like '4' and mast.type_id like '7'  and que.diff_id in(";
			  
				
				
			    String diffId="";	
			    String query1=") and mast.question_id in (select qlesn.question_id from cp_question_lessons qlesn where qlesn.sublesson_id" +
			 				          " in(select lessons.sublesson_id from cp_lessons lessons where lessons.lesson_id like '" ;
			    int lessonId=0;
			    String query2="";
			    String query21="')) "+userQueAnsCondition;
			    String query22="GROUP BY que.diff_id" ;
			 	String query23=" ORDER BY mast.category_id,mast.type_id,que.diff_id ASC  LIMIT ";
			    String limit="";
			    String end=")";
				switch(seven_QUESTIONS){
				case  _2_2_1_1_1:
					System.out.println("Matched : _2_2_1_1_1");
					
					
					//this is nothing but questions from FDP
					diffId=randomList.get(0)+","+randomList.get(1);
				    lessonId=QuantQuestions.FDP.getLessionId();
					limit="2";
					
					if(randomList.get(0)!=randomList.get(1))
					{  
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
						
					}
					else
					{
						
						query2=query21+query23;	
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=query+userAnsQue+temp.replace(userQueAnsCondition,userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
						 
					}
					 
					//this is nothing but questions from Arithmetic
					diffId=randomList.get(2)+","+randomList.get(3);
					lessonId=QuantQuestions.ARITHMETIC_NUMBER_PROPERTIES.getLessionId();
					limit="2";
					if(randomList.get(2)!=randomList.get(3))
					{
						query2=query21+query22+query23;
						temp=query+query11 +diffId+query1+lessonId+query2+limit+end;
						 
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition,userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;	
						temp=query +query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
				 	//this is nothing but questions from Algebra
					diffId=String.valueOf(randomList.get(4));
					lessonId=QuantQuestions.ALGEBRA.getLessionId();
					limit="1";
					temp=query +query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					//this is nothing but questions from Geometry
					diffId=String.valueOf(randomList.get(5));
					lessonId=QuantQuestions.GEOMETRY.getLessionId();
					limit="1";
					temp=query +query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					//this is nothing but questions from WordProblem
					diffId=String.valueOf(randomList.get(6));
					lessonId=QuantQuestions.WORD_PROBLEMS.getLessionId();
					limit="1";
					temp=query +query11+diffId+query1+lessonId+(query21+query23)+limit+end; 
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition,userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end; 
					
					 return finalQuery;
					 
				case _1_2_1_1_2:
					System.out.println("Matched :_1_2_1_1_2");

					//this is nothing but questions from FDP
					diffId=String.valueOf(randomList.get(0));
					 lessonId=QuantQuestions.FDP.getLessionId();
					limit="1";
					temp=query +query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					System.out.println("Final FDP QUERY : "+finalQuery);
					
					
					
				 
					//this is nothing but questions from Arithmetic
					diffId=randomList.get(1)+","+randomList.get(2);
					lessonId=QuantQuestions.ARITHMETIC_NUMBER_PROPERTIES.getLessionId();
					limit="2";
					if(randomList.get(1)!=randomList.get(2))
					{
						query2=query21+query22+query23;
						temp=query +query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition,userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23; 
						temp=query +query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
						 
					}
					
					 
					//this is nothing but questions from Algebra
					diffId=String.valueOf(randomList.get(3));
					lessonId=QuantQuestions.ALGEBRA.getLessionId();
					limit="1";
					temp=query +query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					//this is nothing but questions from Geometry
					diffId=String.valueOf(randomList.get(4));
					lessonId=QuantQuestions.GEOMETRY.getLessionId();
					limit="1";
					temp=query+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					//this is nothing but questions from WordProblem
					diffId=randomList.get(5)+","+randomList.get(6);
					lessonId=QuantQuestions.WORD_PROBLEMS.getLessionId();
					limit="2";
					if(randomList.get(5)!=randomList.get(6))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end; 
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end; 
					}
					else
					{
						query2=query21+query23;	
						temp=query+query11+diffId+query1+lessonId+query2+limit+end; 
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end; 
					}
				
				 
					 return finalQuery;
					
					
				case  _1_2_1_2_1:
					 System.out.println("Matched :_1_2_1_2_1");
					 
					//this is nothing but questions from FDP
					diffId=String.valueOf(randomList.get(0));
					 lessonId=QuantQuestions.FDP.getLessionId();
					limit="1";
					temp=query+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					//this is nothing but questions from Arithmetic
					diffId=randomList.get(1)+","+randomList.get(2);
					lessonId=QuantQuestions.ARITHMETIC_NUMBER_PROPERTIES.getLessionId();
					limit="2";
					if(randomList.get(1)!=randomList.get(2))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;	
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
				 
					
					//this is nothing but questions from Algebra
					diffId=String.valueOf(randomList.get(3));
					lessonId=QuantQuestions.ALGEBRA.getLessionId();
					limit="1";
					temp=query+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					//this is nothing but questions from Geometry
					diffId=randomList.get(4)+","+randomList.get(5);
					lessonId=QuantQuestions.GEOMETRY.getLessionId();
					limit="2";
					if(randomList.get(4)!=randomList.get(5))
					{
						query2=query21+query22+query23;
						temp=query +query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition,userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;	
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
				
				 
					//this is nothing but questions from WordProblem
					diffId=String.valueOf(randomList.get(6));
					lessonId=QuantQuestions.WORD_PROBLEMS.getLessionId();
					limit="1";
					temp=query +query11+diffId+query1+lessonId+(query21+query23)+limit+end; 
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end; 
					
					 return finalQuery; 
					 
				case _1_2_2_1_1:
					System.out.println("Matched : _1_2_2_1_1");
					
					//this is nothing but questions from FDP
					 diffId=String.valueOf(randomList.get(0));
					 lessonId=QuantQuestions.FDP.getLessionId();
					 limit="1";
						temp=query +query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					
					//this is nothing but questions from Arithmetic
					diffId=randomList.get(1)+","+randomList.get(2);
					lessonId=QuantQuestions.ARITHMETIC_NUMBER_PROPERTIES.getLessionId();
					limit="2";
					if(randomList.get(1)!=randomList.get(2))
					{
						query2=query21+query22+query23;
						temp=query +query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;	
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
				 
					
					//this is nothing but questions from Algebra
					diffId=randomList.get(3)+","+randomList.get(4);
					lessonId=QuantQuestions.ALGEBRA.getLessionId();
					limit="2";
					if(randomList.get(3)!=randomList.get(4))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;	
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					 
					
					//this is nothing but questions from Geometry
					diffId=String.valueOf(randomList.get(5));
					lessonId=QuantQuestions.GEOMETRY.getLessionId();
					limit="1";
					temp=query +query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					//this is nothing but questions from WordProblem
					diffId=String.valueOf(randomList.get(6));
					lessonId=QuantQuestions.WORD_PROBLEMS.getLessionId();
					limit="1";
					temp=query+query11+diffId+query1+lessonId+(query21+query23)+limit+end; 
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition,userQueAnsCondition1)+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end; 
					
					 
				}
				return finalQuery; 
				}
			 	public static String getQuantComparisionData(EIGHT_QUESTIONS eight_QUESTIONS,List<Integer> randomList)
				{
			 		//String countCondition=userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd;
					String union="union",finalQuery="";
					String query=" (select que.diff_id,mast.question_id,mast.category_id,mast.type_id from cp_question_masters mast,cp_questions que";
					String query11=" where mast.question_id=que.question_id and  mast.category_id like '4' and mast.type_id like '7'  and que.diff_id in(";
				    String diffId="";	
				    String query1=") and mast.question_id in (select qlesn.question_id from cp_question_lessons qlesn where qlesn.sublesson_id" +
				 				          " in(select lessons.sublesson_id from cp_lessons lessons where lessons.lesson_id like '" ;
				    int lessonId=0;
				    String query2="";
				    String query21="')) "+userQueAnsCondition;
				    String query22="GROUP BY que.diff_id" ;
				 	String query23=" ORDER BY mast.category_id,mast.type_id,que.diff_id ASC  LIMIT ";
				    String limit="";
				    String end=")";
					switch(eight_QUESTIONS){		
			case  _2_2_2_1_1:
					System.out.println("Matched :  _2_2_2_1_1");
					
					//this is nothing but questions from FDP
					 diffId=randomList.get(0)+","+randomList.get(1);
					 lessonId=QuantQuestions.FDP.getLessionId();
				 	 limit="2";
					if(randomList.get(0)!=randomList.get(1))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;	
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					 
					
					//this is nothing but questions from Arithmetic
					diffId=randomList.get(2)+","+randomList.get(3);
					lessonId=QuantQuestions.ARITHMETIC_NUMBER_PROPERTIES.getLessionId();
					limit="2";
					if(randomList.get(2)!=randomList.get(3))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;	
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					
					
					//this is nothing but questions from Algebra
					diffId=randomList.get(4)+","+randomList.get(5);
					lessonId=QuantQuestions.ALGEBRA.getLessionId();
					limit="2";
					if(randomList.get(4)!=randomList.get(5))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;	
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					
					
					//this is nothing but questions from Geometry
					diffId=String.valueOf(randomList.get(6));
					lessonId=QuantQuestions.GEOMETRY.getLessionId();
					limit="1";
					temp=query+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					//this is nothing but questions from WordProblem
					diffId=String.valueOf(randomList.get(7));
					lessonId=QuantQuestions.WORD_PROBLEMS.getLessionId();
					limit="1";
					temp=query+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end; 
					
					 return finalQuery; 
					
					
				case _2_2_1_2_1:
					
					System.out.println("Matched : _2_2_1_2_1");
					
					//this is nothing but questions from FDP
					diffId=randomList.get(0)+","+randomList.get(1);
					 lessonId=QuantQuestions.FDP.getLessionId();
					limit="2";
					if(randomList.get(0)!=randomList.get(1))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;;
						finalQuery=query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;	
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					
				
					//this is nothing but questions from Arithmetic
					diffId=randomList.get(2)+","+randomList.get(3);
					lessonId=QuantQuestions.ARITHMETIC_NUMBER_PROPERTIES.getLessionId();
					limit="2";
					if(randomList.get(2)!=randomList.get(3))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;	
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					
					
					//this is nothing but questions from Algebra
					diffId=String.valueOf(randomList.get(4));
					lessonId=QuantQuestions.ALGEBRA.getLessionId();
					limit="1";
					temp=query+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					//this is nothing but questions from Geometry
					diffId= randomList.get(5)+","+randomList.get(6);
					lessonId=QuantQuestions.GEOMETRY.getLessionId();
					limit="2";
					if(randomList.get(5)!=randomList.get(6))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					
					
					//this is nothing but questions from WordProblem
					diffId=String.valueOf(randomList.get(7));
					lessonId=QuantQuestions.WORD_PROBLEMS.getLessionId();
					limit="1";
					temp=query+query11+diffId+query1+lessonId+(query21+query23)+limit+end; 
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end; 
					
					 return finalQuery; 
					
					
				case _2_2_1_1_2:
					
					System.out.println("Matched :  _2_2_1_1_2");
					
					//this is nothing but questions from FDP
					diffId=randomList.get(0)+","+randomList.get(1);
					 lessonId=QuantQuestions.FDP.getLessionId();
					limit="2";
					if(randomList.get(0)!=randomList.get(1))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;	
					}
					
					//this is nothing but questions from Arithmetic
					diffId=randomList.get(2)+","+randomList.get(3);
					lessonId=QuantQuestions.ARITHMETIC_NUMBER_PROPERTIES.getLessionId();
					limit="2";
					if(randomList.get(2)!=randomList.get(3))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;	
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					
					
					//this is nothing but questions from Algebra
					diffId=String.valueOf(randomList.get(4));
					lessonId=QuantQuestions.ALGEBRA.getLessionId();
					limit="1";
					temp=query+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					//this is nothing but questions from Geometry
					diffId= String.valueOf(randomList.get(5));
					lessonId=QuantQuestions.GEOMETRY.getLessionId();
					limit="1";
					temp=query+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					//this is nothing but questions from WordProblem
					diffId= randomList.get(6)+","+randomList.get(7);
					lessonId=QuantQuestions.WORD_PROBLEMS.getLessionId();
					limit="2";
					if(randomList.get(6)!=randomList.get(7))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end; 
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end; 
					}
					else
					{
						query2=query21+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end; 
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end; 
					}
					
					
					 return finalQuery; 
					
					
					
				case  _1_2_2_1_2:
					
					System.out.println("Matched :  _1_2_2_1_2");
					
					//this is nothing but questions from FDP
					diffId=String.valueOf(randomList.get(0));
					 lessonId=QuantQuestions.FDP.getLessionId();
					limit="1";
					temp=query+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					//this is nothing but questions from Arithmetic
					diffId=randomList.get(1)+","+randomList.get(2);
					lessonId=QuantQuestions.ARITHMETIC_NUMBER_PROPERTIES.getLessionId();
					limit="2";
					if(randomList.get(1)!=randomList.get(2))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;	
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					
					
					//this is nothing but questions from Algebra
					diffId=randomList.get(3)+","+randomList.get(4);
					lessonId=QuantQuestions.ALGEBRA.getLessionId();
					limit="2";
					if(randomList.get(3)!=randomList.get(4))
					{
						query2=query21+query22+query23;	
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;	
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
				
					
					//this is nothing but questions from Geometry
					diffId= String.valueOf(randomList.get(5));
					lessonId=QuantQuestions.GEOMETRY.getLessionId();
					limit="1";
					temp=query+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					//this is nothing but questions from WordProblem
					diffId= randomList.get(6)+","+randomList.get(7);
					lessonId=QuantQuestions.WORD_PROBLEMS.getLessionId();
					limit="2";
					if(randomList.get(6)!=randomList.get(7))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end; 
					}
					else
					{
						query2=query21+query23;	
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end; 
					}
					
					
					 return finalQuery; 
					
					
				case  _1_2_1_2_2:
					
					System.out.println("Matched :  _1_2_1_2_2");
					
					//this is nothing but questions from FDP
					diffId=String.valueOf(randomList.get(0));
					 lessonId=QuantQuestions.FDP.getLessionId();
					limit="1";
					temp=query+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					//this is nothing but questions from Arithmetic
					diffId=randomList.get(1)+","+randomList.get(2);
					lessonId=QuantQuestions.ARITHMETIC_NUMBER_PROPERTIES.getLessionId();
					limit="2";
					if(randomList.get(1)!=randomList.get(2))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;	
					}
					
					
					//this is nothing but questions from Algebra
					diffId=String.valueOf(randomList.get(3));
					lessonId=QuantQuestions.ALGEBRA.getLessionId();
					limit="1";
					temp=query+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					//this is nothing but questions from Geometry
					diffId=   randomList.get(4)+","+randomList.get(5);
					lessonId=QuantQuestions.GEOMETRY.getLessionId();
					limit="2";
					if(randomList.get(4)!=randomList.get(5))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;	
					}
					
					
					//this is nothing but questions from WordProblem
					diffId= randomList.get(6)+","+randomList.get(7);
					lessonId=QuantQuestions.WORD_PROBLEMS.getLessionId();
					limit="2";
					if(randomList.get(6)!=randomList.get(7))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end; 
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end; 
					}
					else
					{
						query2=query21+query23;	
						temp=query+query11+diffId+query1+lessonId+query2+limit+end; 
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end; 
					}
					
					
					 return finalQuery; 
				case _1_2_2_2_1:
					
					System.out.println("Matched : _1_2_2_2_1");
					
					//this is nothing but questions from FDP
					diffId=String.valueOf(randomList.get(0));
					 lessonId=QuantQuestions.FDP.getLessionId();
					limit="1";
					temp=query+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					//this is nothing but questions from Arithmetic
					diffId=randomList.get(1)+","+randomList.get(2);
					lessonId=QuantQuestions.ARITHMETIC_NUMBER_PROPERTIES.getLessionId();
					limit="2";
					if(randomList.get(1)!=randomList.get(2))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;	
					}
					
					
					//this is nothing but questions from Algebra
					diffId= randomList.get(3)+","+randomList.get(4);
					lessonId=QuantQuestions.WORD_PROBLEMS.getLessionId();
					limit="2";
					if(randomList.get(3)!=randomList.get(4))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end; 
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end; 
					}
					else
					{
						query2=query21+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end; 
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end; 
					}
					
					
					//this is nothing but questions from Geometry
					diffId=   randomList.get(5)+","+randomList.get(6);
					lessonId=QuantQuestions.GEOMETRY.getLessionId();
					limit="2";
					if(randomList.get(5)!=randomList.get(6))
					{
						query2=query21+query22+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;
					}
					else
					{
						query2=query21+query23;
						temp=query+query11+diffId+query1+lessonId+query2+limit+end;	
						finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+query2+limit+end;	
					}
					
					
					//this is nothing but questions from WordProblem
					
					diffId=String.valueOf(randomList.get(7));
					lessonId=QuantQuestions.ALGEBRA.getLessionId();
					limit="1";
					temp=query+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					finalQuery=finalQuery+union+query+userAnsQue+temp.replace(userQueAnsCondition, "")+userAnsQueEnd+query11+diffId+query1+lessonId+(query21+query23)+limit+end;
					
					 return finalQuery; 
					
			
				}
				 return null; 
			}
			 
			public static String getProblemSolvingSingle(int psType,int diffid1,int diffid2,int diffid3,int diffid4,List<Integer> lessonslist)
			{
				 
				 String problemSolving="";
				 int diff[]={diffid1,diffid2,diffid3,diffid4};				 
				 for(int i=0;i<lessonslist.size();i++)
				 {
					 
					 /* String ps1="( SELECT que.diff_id,mast.question_id,mast.category_id,mast.type_id FROM cp_question_masters mast,cp_questions que " +
					 		" WHERE mast.question_id=que.question_id AND mast.category_id LIKE '6' AND mast.type_id LIKE '"+psType+"' AND que.diff_id IN("+diff[i]+")" +
					 		"  AND que.question_id NOT IN (SELECT results.question_id FROM cp_test_results results WHERE results.userid LIKE '10069') " +
					 		" and que.question_id in(select suble.question_id  from cp_question_lessons suble where suble.sublesson_id in(select le.sublesson_id " +
					 		" from cp_lessons le  where le.lesson_id like '"+lessonslist.get(i)+"' ))ORDER BY mast.category_id,mast.type_id,que.diff_id ASC limit 1 )";
					*/  
					 String ps1="";
					 String ps11="( SELECT que.diff_id,mast.question_id,mast.category_id,mast.type_id FROM cp_question_masters mast,cp_questions que ";
					 String ps12=" WHERE mast.question_id=que.question_id AND mast.category_id LIKE '6' AND mast.type_id LIKE '"+psType+"' AND que.diff_id IN("+diff[i]+")" +						 		 
						 		" and que.question_id in(select suble.question_id  from cp_question_lessons suble where suble.sublesson_id in(select le.sublesson_id " +
						 		" from cp_lessons le  where le.lesson_id like '"+lessonslist.get(i)+"' ))";
					 String ps13=" ORDER BY mast.category_id,mast.type_id,que.diff_id ASC limit 1 )";
					  
					  ps1=ps11+ps12+ps13;
					 
				       problemSolving=problemSolving+(ps11+userAnsQue+ps1+userAnsQueEnd+ps12+userQueAnsCondition+ps13);
				         
				 
				 if(i==0||(i!=lessonslist.size()-1)){ 
					 problemSolving=problemSolving+" union ";
					 }
					  
				 }
				   
				 
				return problemSolving;	
			}
			public static String getProblemSolvingDouble(int psType,int diffid1,int diffid2,int ord,int lessonId)
			{ 	/*	 
				 String   problemSolving="( SELECT que.diff_id,mast.question_id,mast.category_id,mast.type_id FROM cp_question_masters mast,cp_questions que " +
					 		" WHERE mast.question_id=que.question_id AND mast.category_id LIKE '6' AND mast.type_id LIKE '"+psType+"' AND que.diff_id IN("+diffid1+")" +
					 		"  AND que.question_id NOT IN (SELECT results.question_id FROM cp_test_results results WHERE results.userid LIKE '10069') AND que.question_id IN(" +
				 		" SELECT suble.question_id FROM cp_question_lessons suble WHERE suble.sublesson_id IN( SELECT le.sublesson_id FROM cp_lessons le " +
				 		" WHERE le.lesson_id LIKE '"+lessonId+"'))" +
					 		"   ORDER BY mast.category_id,mast.type_id,que.diff_id ASC limit ";*/
				 String ps1="( SELECT que.diff_id,mast.question_id,mast.category_id,mast.type_id FROM cp_question_masters mast,cp_questions que ";
				 String ps2=" WHERE mast.question_id=que.question_id AND mast.category_id LIKE '6' AND mast.type_id LIKE '"+psType+"' AND que.diff_id IN("+diffid1+")" +
					 		"    AND que.question_id IN(" +
				 		" SELECT suble.question_id FROM cp_question_lessons suble WHERE suble.sublesson_id IN( SELECT le.sublesson_id FROM cp_lessons le " +
				 		" WHERE le.lesson_id LIKE '"+lessonId+"'))";
				 String ps3="   ORDER BY mast.category_id,mast.type_id,que.diff_id ASC limit ";
				 
				 String problemSolving=ps1+ps2+ps3;
				 String tail="";
				 if(diffid1==diffid2)
				 {
					 if(ord==1)
					 {
						 problemSolving=problemSolving+" 0,1";tail=" 0,1";
					 }
					 if(ord==2)
					 {
						 problemSolving=problemSolving+" 1,1"; tail=" 1,1";
					 }
				 }
				 else
				 {
					 problemSolving=problemSolving+" 1";tail=" 1";
				 }
				problemSolving=problemSolving+")";
				String finalQuery=ps1+userAnsQue+problemSolving+userAnsQueEnd+ps2+userQueAnsCondition+ps3+tail+")";
				
				problemSolving=finalQuery;
				
				
				 
				 
				return problemSolving;	
			}
			public static String getNumericEntry(int numericDiffId,int lessonId)
			{
				/* 
				 String numericEntry="(select que.diff_id,mast.question_id,mast.category_id,mast.type_id from cp_question_masters mast,cp_questions que" +
				 		" where mast.question_id=que.question_id and  mast.category_id like '6' and mast.type_id  in(10,13)  and que.diff_id in("+numericDiffId+") " +
				 		"   AND que.question_id NOT IN (SELECT results.question_id FROM cp_test_results results WHERE results.userid LIKE '10069') AND que.question_id IN(" +
				 		" SELECT suble.question_id FROM cp_question_lessons suble WHERE suble.sublesson_id IN( SELECT le.sublesson_id FROM cp_lessons le " +
				 		" WHERE le.lesson_id LIKE '"+lessonId+"'))" +
				 		" ORDER BY mast.category_id,mast.type_id,que.diff_id ASC ,rand() LIMIT 1)";	
				*/
				String numericEntry="";
				String numEntry1="(select que.diff_id,mast.question_id,mast.category_id,mast.type_id from cp_question_masters mast,cp_questions que" ;
				 String numEntry2=" where mast.question_id=que.question_id and  mast.category_id in(6,5) and mast.type_id  in(10,13,16,17)  and que.diff_id in("+numericDiffId+") " +
				 		"   AND que.question_id IN( SELECT suble.question_id FROM cp_question_lessons suble WHERE suble.sublesson_id IN( SELECT le.sublesson_id FROM cp_lessons le " +
				 		" WHERE le.lesson_id LIKE '"+lessonId+"'))";
				 String numEntry3=" ORDER BY mast.category_id,mast.type_id,que.diff_id ASC ,rand() LIMIT 1)";	
				 
				 numericEntry= numEntry1+userAnsQue+(numEntry1+numEntry2+numEntry3)+userAnsQueEnd+numEntry2+userQueAnsCondition+numEntry3;
				 
				 
				 return numericEntry;
			}
			public static String getNumericEntry(int numericDiffId1,int numericDiffId2,int num,int lessonId)
			{
				int numericDiffId=0;
				if(num==1)
				{
					numericDiffId=numericDiffId1;	
				}
				else if(num==2)
				{
					numericDiffId=numericDiffId2;
				}
				/* String numericEntry="(select que.diff_id,mast.question_id,mast.category_id,mast.type_id from cp_question_masters mast,cp_questions que" +
					 		" where mast.question_id=que.question_id and  mast.category_id like '6' and mast.type_id  in(10,13)  and que.diff_id in("+numericDiffId+") " +
					 		" AND que.question_id IN( SELECT suble.question_id FROM cp_question_lessons suble WHERE suble.sublesson_id IN( SELECT le.sublesson_id FROM cp_lessons le " +
							 		" WHERE le.lesson_id LIKE '"+lessonId+"'))" + 
					 		" ORDER BY mast.category_id,mast.type_id,que.diff_id ASC ,rand() LIMIT";	
			*/	
				String numEntry1="(select que.diff_id,mast.question_id,mast.category_id,mast.type_id from cp_question_masters mast,cp_questions que" ;
				 String numEntry2=" where mast.question_id=que.question_id and  mast.category_id like '6' and mast.type_id  in(10,13)  and que.diff_id in("+numericDiffId+") " +
					 		       "  AND que.question_id IN(SELECT suble.question_id FROM cp_question_lessons suble WHERE suble.sublesson_id IN( SELECT le.sublesson_id FROM cp_lessons le " +
							 		" WHERE le.lesson_id LIKE '"+lessonId+"'))" ;
				 String numEntry3=" ORDER BY mast.category_id,mast.type_id,que.diff_id ASC ,rand() LIMIT";
				 String numericEntry=numEntry1+numEntry2+numEntry3; 
				 
				 String tail="";
				 if(numericDiffId1==numericDiffId2&&num==1)
				 {
					 numericEntry=numericEntry+" 0,1)";
					 tail=" 0,1)";
				 }
				 else if(numericDiffId1==numericDiffId2&&num==2)
				 {
					 numericEntry=numericEntry+" 1,1)";tail=" 1,1)";
				 }
				 else
				 {
					 numericEntry=numericEntry+" 1)"; tail=" 1)";
				 }
			 String finalQUery=numEntry1+userAnsQue+(numericEntry)+userAnsQueEnd+numEntry2+userQueAnsCondition+numEntry3+tail;
		     numericEntry=finalQUery;
		     return numericEntry;
				
			}
		 
            public static String getDataInterpretation( byte dicount, int DIdiffId)
            {
             	/* String dataInterpretation="(SELECT "+DIdiffId+",mast.question_id,mast.category_id,mast.type_id" +
			 		" FROM cp_question_masters mast,cp_questions que WHERE mast.graph_id=(select masters.graph_id graphId " +
			 		" from cp_question_masters masters,(select graph.graph_id  from cp_data_interpretation_graph graph where graph.difficulty like '"+DIdiffId+"')" +
			 		"  graphs where masters.graph_id=graphs.graph_id and masters.question_id NOT IN (SELECT results.question_id " +
			 		" FROM cp_test_results results WHERE results.userid LIKE '10069') group by masters.graph_id having count(*)="+dicount+")" +
			 		"  AND mast.question_id=que.question_id)";
			 */
			 String di1="(SELECT "+DIdiffId+",mast.question_id,mast.category_id,mast.type_id" +
				 		" FROM cp_question_masters mast,cp_questions que";
			 String di2=" WHERE mast.graph_id=(select masters.graph_id graphId " +
				 		" from cp_question_masters masters,(select graph.graph_id  from cp_data_interpretation_graph graph where graph.difficulty like '"+DIdiffId+"')" +
				 		"  graphs where masters.graph_id=graphs.graph_id ";
			 String di3=" group by masters.graph_id having count(*)="+dicount+" limit 1) AND mast.question_id=que.question_id" ;
			 String end=")";
			 
			 String dataInterpretation=di1+userAnsQue+di1+di2+di3+end+userAnsQueEnd+di2+di3+userQueAnsCondition+end; 
			  
			 return dataInterpretation;
			 
            }

}

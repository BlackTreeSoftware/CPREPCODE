package com.greenbuds.crunchprep.util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import jdk.nashorn.internal.ir.LiteralNode.ArrayLiteralNode.ArrayUnit;

import com.greenbuds.crunchprep.controller.contentcreator.IQuestionUploadController;
import com.greenbuds.crunchprep.controller.contentcreator.QuestionUploadController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.DifficultyAlgorithm;
import com.greenbuds.crunchprep.setups.MathAlgorithm;
import com.greenbuds.crunchprep.util.ArraysUtil;
import com.opensymphony.xwork2.util.ArrayUtils;


public class Verbal_Questions {
	//static int userid=0;
	 String userAnsQue=",(select count(*) cnt from";
	  String userAnsQueEnd="sub)sub1";
	  int userId;
	 
	 
		 
	 
	 public static void main(String a[]) throws JSONException{
		 Integer i[]={4, 5, 5, 5, 4, 5, 4, 4, 4, 4, 4, 4, 4,3, 4,1, 1, 5, 5, 5}; 
		 System.out.println((new Verbal_Questions()).prepareVerbalQuery(DifficultyAlgorithm._2_2_2_2_1_4_4_3, Arrays.asList(i), 10069));
		 
	 }
		 // Integer i[]={1, 5, 2, 4, 3, 5, 3, 3, 3, 2, 2, 2, 2, 1,2, 3,2, 1, 1, 1};//when All Rc Difficulties are different
		//  Integer i[]={1, 5, 2, 4, 3, 5, 3, 3, 3, 3,3, 3, 3, 1,2, 3,2, 3,3,3};//when All three rc difficulties equal
		// Integer i[]={1, 5, 2, 4, 3, 5, 3, 3, 3, 3,3, 3, 3, 1,2, 3,2, 2,2,2};//when rc1 and rc2 are same
		// Integer i[]={1, 5, 2, 4, 3, 5, 2, 2, 2, 3,3, 3, 3, 1,2, 3,2, 2,2,2};//when rc1 and rc3 are same
	//	 Integer i[]={4, 5, 5, 5, 4, 5, 4, 4, 4, 4, 4, 4, 4,3, 4,1, 1, 5, 5, 5};
	 public void getVerbalQuestions(JSONObject object,int userId) throws JSONException, ConnectionException, DBException{   
		 userId=userId;
	
	 ArrayList<Integer> randomList=null;//Arrays.asList(i);\
         
         String finalQuiree="";
         CPException exception;
       IQuestionUploadController questionUploadController=new QuestionUploadController();
         
		JSONObject jsonObject = new JSONObject();
		
		DoubtsCLear clear=new DoubtsCLear();
		
		int rowCount=0;
		DifficultyAlgorithm algorithm=null;
		
		int loop=1;
		
		try {
			do{
				algorithm=DifficultyAlgorithm.getRandomPattern();
				double difficultyRange=3.0;//default if not specifiedany thing
				difficultyRange=object.getDouble("limit");
				object.put("limit",difficultyRange);
				object.put("pattern",algorithm);
				randomList=clear.alogirithmPreparation(object);
				finalQuiree=prepareVerbalQuery(algorithm,
						  randomList,userId);
				jsonObject.put("inputQuiree", (finalQuiree=finalQuiree.replace("10069",String.valueOf(userId))));
				//System.out.println("final query after user Id user id : "+userId+"  : "+finalQuiree);
		      // System.out.println("\n Final quiree is: \n\t"+finalQuiree);
			questionUploadController.requiredTableDataInJSONFormate(jsonObject);
			rowCount=jsonObject.getInt("rowCount");
			if( jsonObject.getBoolean("isDataAvilable"))
     		{
     			object.put("tableData", jsonObject.get("tableData"));
     		}
			 
			if(rowCount!=20){
			System.out.println("\n\n   Row COunt is; "+rowCount);
			jsonObject.remove("rowCount");
			jsonObject.remove("tableData");
			jsonObject.remove("isDataAvilable");
			jsonObject.remove("inputQuiree");
			}else{
				System.out.println("\n Ok  Data Getting successflly");
			}
			if(loop++==50)rowCount=20;
			}while(rowCount!=20);
	 		
			if(loop>50) System.out.println("\n DataBase Not Contained Data");
			
			object.put("isQuestionAvilable", loop<50);
			
			System.out.println("\n Getting Quant Questions is; \n"+clear.alogirithmPreparation(object));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw e;
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			exception = e;
			//e.printStackTrace();
			throw e;
		} catch (DBException e) {
			// TODO Auto-generated catch block
			exception = e;
			//e.printStackTrace();
			throw e;
			
		}catch(RuntimeException ex)
		{
			//System.err.println("Error is: "+ex.getMessage());
		//	ex.printStackTrace();
			throw ex;
		}
	      
	
	       
	    }        
	 
	 public  String prepareVerbalQuery(DifficultyAlgorithm algorithm,
				List<Integer> randomList,int userId) {
		 String finalQuery="";
		 String union=" union ";
		// String userAnsQueChecking="and que.question_id not in (select results.question_id from cp_test_results results where results.userid like '10069') ";
		 String userAnsQueChecking=" AND IF(0!=sub1.cnt,mast.question_id NOT IN (SELECT results.question_id FROM cp_test_results results WHERE results.userid LIKE '10069'), TRUE)";
		 String userAnsQueChecking1="  and mast.question_id not in(SELECT results.question_id FROM cp_test_results results WHERE results.userid LIKE '10069')";
			
		 //System.out.println(randomList+"\t"+algorithm.getPattern());
		 String textCompletion="",textCompletion1="",textCompletion2="",textCompletion3="",tail="",criticalReasoning="";
		 String tc1="(select   que.diff_id,mast.question_id,mast.category_id,mast.type_id  from cp_question_masters mast ,cp_questions que ";
		 String tc11=" where que.question_id=mast.question_id " +
		 		" and mast.category_id like '1' and mast.type_id like '1' and que.diff_id in("+randomList.get(0)+","+randomList.get(1)+") " +userAnsQueChecking;
		 textCompletion1=tc1+tc11;		 
		        if(randomList.get(0)!=randomList.get(1))
		 		{
		 			textCompletion1=textCompletion1+" group by que.diff_id " ; tail=" group by que.diff_id " ; 
		 		}
		        else
		        {
		        	textCompletion1=textCompletion1+"" ; tail="" ; 
		        }
		 textCompletion1=textCompletion1+" order by mast.category_id,mast.type_id,que.diff_id asc limit 2) ";
		 textCompletion1=tc1+userAnsQue+textCompletion1.replace(userAnsQueChecking, userAnsQueChecking1)+userAnsQueEnd+tc11+tail+" order by mast.category_id,mast.type_id,que.diff_id asc limit 2) ";
		 //System.out.println("TC1 : "+textCompletion1);
		 		 
        String tc2= "(select   que.diff_id,mast.question_id,mast.category_id,mast.type_id  from  cp_question_masters mast ,cp_questions que " ;
		 String tc22=" where que.question_id=mast.question_id and mast.category_id like '1' and mast.type_id like '2' and que.diff_id in("+randomList.get(2)+","+randomList.get(3)+") " +userAnsQueChecking;
		 textCompletion2=tc2+tc22;				 
		  if(randomList.get(2)!=randomList.get(3))
	 		{
	 			textCompletion2=textCompletion2+" group by que.diff_id " ;
	 			tail=" group by que.diff_id " ;
	 		}
		  else
	        {
			  textCompletion2=textCompletion2+"" ;
	 			tail="" ;
	        }
	 		
	 textCompletion2=textCompletion2+"  order by mast.category_id,mast.type_id,que.diff_id asc limit 2)";
	 textCompletion2=tc2+userAnsQue+textCompletion2.replace(userAnsQueChecking, userAnsQueChecking1)+userAnsQueEnd+tc22+tail+"  order by mast.category_id,mast.type_id,que.diff_id asc limit 2)";
	// System.out.println("TC2 : "+textCompletion2);
	 
     String tc3= " (select   que.diff_id,mast.question_id,mast.category_id,mast.type_id  from cp_question_masters mast ,cp_questions que " ;
	 
	 String tc33= " where que.question_id=mast.question_id and mast.category_id like '1' and mast.type_id like '3' and que.diff_id in("+randomList.get(4)+","+randomList.get(5)+")" +userAnsQueChecking;
	 textCompletion3=tc3+tc33;		 
	 if(randomList.get(4)!=randomList.get(5))
		{
		 textCompletion3=textCompletion3+" group by que.diff_id " ;
		 tail=" group by que.diff_id " ;
		}
	 else
	 {
		 textCompletion3=textCompletion3+"" ;
		 tail=" " ; 
	 }
		
	 textCompletion3=textCompletion3+" order by mast.category_id,mast.type_id,que.diff_id asc limit 2) ";
	 textCompletion3=tc3+userAnsQue+textCompletion3.replace(userAnsQueChecking, userAnsQueChecking1)+userAnsQueEnd+tc33+tail+"  order by mast.category_id,mast.type_id,que.diff_id asc limit 2)";
	// System.out.println("TC3 : "+textCompletion3);
	   
        textCompletion=textCompletion1+union+textCompletion2+union+textCompletion3;
       // System.out.println("Text Completion Query : "+textCompletion);
		 
		 
		 String sentenceEquivalance=sentenceEquivalanceQuiree(randomList.subList(13, 17));/*"(select   que.diff_id,mast.question_id,mast.category_id,mast.type_id  from cp_question_masters mast ,cp_questions que" +
		 		"  where que.question_id=mast.question_id and mast.category_id like '2' and mast.type_id like '6'" +
		 		"  and que.diff_id in"+ sublist.replace("[", "(").replace("]", ")"); 
		 if(!ArraysUtil.isListHasDuplicates(randomList.subList(13, 17)))
		 {
			 sentenceEquivalance=sentenceEquivalance+ "group by que.diff_id"; 
		 } 
		 sentenceEquivalance=sentenceEquivalance+ " order by que.diff_id asc limit 4) "; */
			String rc1="(select que.diff_id,mast.question_id,mast.category_id,mast.type_id  from cp_question_masters mast ,cp_questions que";
			String rc2=" where mast.passage_id=";
			String cr1="(select   que.diff_id,mast.question_id,mast.category_id,mast.type_id  from " +
					" cp_question_masters mast ,cp_questions que ";
			String cr2=" where que.question_id=mast.question_id " +
					" and mast.category_id like '9' and mast.type_id like '15'" ;
		
		 String readingComprehension1=null,readingComprehension2=null,readingComprehension3=null;
		 switch (algorithm) {
			case _2_2_2_2_1_4_4_3:
			    System.out.println("matched Pattern is :_2_2_2_2_1_4_4_3 ");
				criticalReasoning=cr1+cr2+"and que.diff_id in("+randomList.get(8)+") " +userAnsQueChecking+"  limit 1)  ";
				criticalReasoning=cr1+userAnsQue+criticalReasoning.replace(userAnsQueChecking, userAnsQueChecking1)+userAnsQueEnd+cr2+"and que.diff_id in("+randomList.get(8)+") " +userAnsQueChecking+"  limit 1)  ";
				// System.out.println("Critical Reasoning  :"+criticalReasoning);
				 // readingComprehension1=rc1+rc2+" '"+randomList.get(6)+"' ";
				 
				  readingComprehension1=rc1+rc2+getReadingComprehensionQuery(randomList.get(6),2,userAnsQueChecking);//3 rd parameter is for number of questions limit				  
				 readingComprehension1=rc1+userAnsQue+readingComprehension1.replace(userAnsQueChecking, userAnsQueChecking1)+userAnsQueEnd+rc2+getReadingComprehensionQuery(randomList.get(6),2,userAnsQueChecking);
				    //readingComprehension2=rc1+rc2+"'"+randomList.get(9)+"' ";
				  readingComprehension2=rc1+rc2+ getReadingComprehensionQuery(randomList.get(9),4,userAnsQueChecking);
				  readingComprehension2=rc1+userAnsQue+readingComprehension2.replace(userAnsQueChecking, userAnsQueChecking1)+userAnsQueEnd+rc2+ getReadingComprehensionQuery(randomList.get(9),4,userAnsQueChecking);
				  
				  // readingComprehension3=rc1+rc2+"'"+randomList.get(17)+"' ";
				  readingComprehension3=rc1+rc2+ getReadingComprehensionQuery(randomList.get(17),3,userAnsQueChecking);
				  readingComprehension3=rc1+userAnsQue+readingComprehension3.replace(userAnsQueChecking, userAnsQueChecking1)+userAnsQueEnd+rc2+ getReadingComprehensionQuery(randomList.get(17),3,userAnsQueChecking);
				  //System.out.println("Reading Comprehension : rc1 : "+readingComprehension1+"\n rc2 :"+readingComprehension2+"\n rc3"+readingComprehension3);
				  
				  finalQuery=textCompletion+union+readingComprehension1+union+criticalReasoning+union+readingComprehension2+union+sentenceEquivalance+union+readingComprehension3;
				
				
	
				break;
			case _2_2_2_3_4_4_3:
				 // System.out.println("matched Pattern is :_2_2_2_3_4_4_3 ");
				  
				 // readingComprehension1=rc1+rc2+" '"+randomList.get(6)+"' ";
				 
				  readingComprehension1=rc1+rc2+getReadingComprehensionCriteria(randomList.get(6), 3, 1,userAnsQueChecking);//3 rd parameter is for number of questions limit				  
				  readingComprehension1=rc1+userAnsQue+readingComprehension1.replace(userAnsQueChecking, userAnsQueChecking1)+userAnsQueEnd+rc2+getReadingComprehensionCriteria(randomList.get(6), 3, 1,userAnsQueChecking);
				// readingComprehension2=rc1+rc2+"'"+randomList.get(9)+"' ";
				  readingComprehension2=rc1+rc2+getReadingComprehensionQuery(randomList.get(9), 4,userAnsQueChecking);
				  readingComprehension2=rc1+userAnsQue+readingComprehension2.replace(userAnsQueChecking, userAnsQueChecking1)+userAnsQueEnd+rc2+getReadingComprehensionQuery(randomList.get(9), 4,userAnsQueChecking);
				 //  readingComprehension3=rc1+rc2+"'"+randomList.get(17)+"' ";
				  readingComprehension3=rc1+rc2+getReadingComprehensionCriteria(randomList.get(17),3,2,userAnsQueChecking);
				  readingComprehension3=rc1+userAnsQue+readingComprehension3.replace(userAnsQueChecking, userAnsQueChecking1)+userAnsQueEnd+rc2+getReadingComprehensionCriteria(randomList.get(17),3,2,userAnsQueChecking);
				  finalQuery=textCompletion+union+readingComprehension1+union+readingComprehension2+union+sentenceEquivalance+union+readingComprehension3; 
	
				break;
			case _2_2_2_3_4_4_1_2:
				  System.out.println("matched Pattern is : _2_2_2_3_4_4_1_2");
				criticalReasoning=cr1+cr2+"and que.diff_id in("+randomList.get(17)+")  " +userAnsQueChecking+" limit 1)  ";
				criticalReasoning=cr1+userAnsQue+criticalReasoning.replace(userAnsQueChecking, userAnsQueChecking1)+userAnsQueEnd+cr2+"and que.diff_id in("+randomList.get(17)+")  " +userAnsQueChecking+" limit 1)  ";
				// System.out.println("Critical Reasoning  :"+criticalReasoning);
				 // readingComprehension1=rc1+rc2+" '"+randomList.get(6)+"' ";
					 
				  readingComprehension1=rc1+rc2+ getReadingComprehensionQuery(randomList.get(6),3,userAnsQueChecking);//3 rd parameter is for number of questions limit		
				  readingComprehension1=rc1+userAnsQue+readingComprehension1.replace(userAnsQueChecking,userAnsQueChecking1)+userAnsQueEnd+rc2+ getReadingComprehensionQuery(randomList.get(6),3,userAnsQueChecking);
				
				// readingComprehension2=rc1+rc2+"'"+randomList.get(9)+"' ";
				  readingComprehension2=rc1+rc2+ getReadingComprehensionQuery(randomList.get(9),4,userAnsQueChecking);
				  readingComprehension2=rc1+userAnsQue+readingComprehension2.replace(userAnsQueChecking,userAnsQueChecking1)+userAnsQueEnd+rc2+getReadingComprehensionQuery(randomList.get(9),4,userAnsQueChecking);
				  // readingComprehension3=rc1+rc2+"'"+randomList.get(18)+"' ";
				  readingComprehension3=rc1+rc2+ getReadingComprehensionQuery(randomList.get(18),2,userAnsQueChecking);
				  readingComprehension3=rc1+userAnsQue+readingComprehension3.replace(userAnsQueChecking, userAnsQueChecking1)+userAnsQueEnd+rc2+ getReadingComprehensionQuery(randomList.get(18),2,userAnsQueChecking);
				   
				  finalQuery=textCompletion+union+readingComprehension1+union+readingComprehension2+union+sentenceEquivalance+union+criticalReasoning+union+readingComprehension3;
			
				break;
			case _2_2_2_2_5_4_3:
				System.out.println("matched Pattern is : _2_2_2_2_5_4_3");
				 
				  //readingComprehension1=rc1+rc2+" '"+randomList.get(6)+"' ";
					 
				  readingComprehension1=rc1+rc2+getReadingComprehensionQuery(randomList.get(6),2,userAnsQueChecking);//3 rd parameter is for number of questions limit				  
				 readingComprehension1=rc1+userAnsQue+readingComprehension1.replace(userAnsQueChecking,userAnsQueChecking1)+userAnsQueEnd+rc2+getReadingComprehensionQuery(randomList.get(6),2,userAnsQueChecking);
				// readingComprehension2=rc1+rc2+"'"+randomList.get(8)+"' ";
				  readingComprehension2=rc1+rc2+getReadingComprehensionQuery(randomList.get(8),5,userAnsQueChecking);
				  readingComprehension2=rc1+userAnsQue+readingComprehension2.replace(userAnsQueChecking, userAnsQueChecking1)+userAnsQueEnd+rc2+getReadingComprehensionQuery(randomList.get(8),5,userAnsQueChecking);
				  // readingComprehension3=rc1+rc2+"'"+randomList.get(17)+"' ";
				  readingComprehension3=rc1+rc2+getReadingComprehensionQuery(randomList.get(17),3,userAnsQueChecking);
				  readingComprehension3=rc1+userAnsQue+readingComprehension3.replace(userAnsQueChecking, userAnsQueChecking1)+userAnsQueEnd+rc2+getReadingComprehensionQuery(randomList.get(17),3,userAnsQueChecking);
				  finalQuery=textCompletion+union+readingComprehension1+union+readingComprehension2+union+sentenceEquivalance+union+readingComprehension3;
			
				
			
				break;
			case _2_2_2_2_5_4_1_2:
				System.out.println("matched Pattern is : _2_2_2_2_5_4_1_2");
				criticalReasoning=cr1+cr2+"and que.diff_id in("+randomList.get(17)+") " +userAnsQueChecking+"  limit 1)  ";
				criticalReasoning=cr1+userAnsQue+criticalReasoning.replace(userAnsQueChecking,userAnsQueChecking1)+userAnsQueEnd+cr2+"and que.diff_id in("+randomList.get(17)+") " +userAnsQueChecking+"  limit 1)  ";
				// System.out.println("Critical Reasoning  :"+criticalReasoning);
				 // readingComprehension1=rc1+rc2+" '"+randomList.get(6)+"' ";
					 
				  readingComprehension1=rc1+rc2+getReadingComprehensionCriteria(randomList.get(6),2,1,userAnsQueChecking);//3 rd parameter is for number of questions limit				  
				  readingComprehension1=rc1+userAnsQue+readingComprehension1.replace(userAnsQueChecking,userAnsQueChecking1)+userAnsQueEnd+rc2+getReadingComprehensionCriteria(randomList.get(6),2,1,userAnsQueChecking);
				// readingComprehension2=rc1+rc2+"'"+randomList.get(8)+"' ";
				  readingComprehension2=rc1+rc2+getReadingComprehensionQuery(randomList.get(8), 5,userAnsQueChecking);
				  readingComprehension2=rc1+userAnsQue+readingComprehension2.replace(userAnsQueChecking, userAnsQueChecking1)+userAnsQueEnd+rc2+getReadingComprehensionQuery(randomList.get(8), 5,userAnsQueChecking);
				//  System.out.println(readingComprehension2);
				  // readingComprehension3=rc1+rc2+"'"+randomList.get(18)+"' ";
				  readingComprehension3=rc1+rc2+getReadingComprehensionCriteria(randomList.get(18),2,2,userAnsQueChecking);
				  readingComprehension3=rc1+userAnsQue+readingComprehension3.replace(userAnsQueChecking,userAnsQueChecking1)+userAnsQueEnd+rc2+getReadingComprehensionCriteria(randomList.get(18),2,2,userAnsQueChecking);
				//  System.out.println(readingComprehension3);
				  finalQuery=textCompletion+union+readingComprehension1+union+readingComprehension2+union+sentenceEquivalance+union+criticalReasoning+union+readingComprehension3;
			     System.out.println("Queries : "+readingComprehension1+"\n"+readingComprehension2+"\n"+readingComprehension3);
			
				break;
			}
		 return finalQuery;
	 }
	 public static String getReadingComprehensionQuery(int diffId,int rowCount,String userAnsQueChecking)
	 {
		 String criteria=" (select mast.passage_id  from cp_question_masters mast where mast.passage_id " +
		 		"  in (select pa.passage_id  ss from cp_reading_comprehension_passage pa where pa.difficulty like '"+diffId+"' ) group by mast.passage_id  having count(*)="+rowCount+" LIMIT 1)" +userAnsQueChecking+" AND mast.question_id=que.question_id )";
		 return criteria;
	 }
	 public static String getReadingComprehensionCriteria(int diffId,int rowCount,int rcCount,String userAnsQueChecking)
	 {
		 String criteria=" (select mast.passage_id  from cp_question_masters mast where mast.passage_id " +
			 		"  in (select pa.passage_id  ss from cp_reading_comprehension_passage pa where pa.difficulty";
		 switch(rcCount)
		 {
		 case 1:criteria=criteria+" like '"+diffId+"' ) group by mast.passage_id   having count(*)="+rowCount+" LIMIT 0,1)"+ userAnsQueChecking+" AND mast.question_id=que.question_id )";
			 break;
		 case 2:criteria=criteria+" like '"+diffId+"' ) group by mast.passage_id   having count(*)="+rowCount+" LIMIT 1,1)"+userAnsQueChecking+" AND mast.question_id=que.question_id )";
			 break;
		 }
		 return criteria; 
		 
	 }
	 
	 private String sentenceEquivalanceQuiree(List<Integer> randomList){
		 //System.out.println("randomList : "+randomList);
		 String sentenceEquivalance="";
		String topQuiree="( SELECT que.diff_id,mast.question_id,mast.category_id,mast.type_id " +
		 		"  FROM cp_question_masters mast,cp_questions que ",tp=topQuiree;
		
		String topQuiree1=" WHERE que.question_id=mast.question_id AND mast.category_id LIKE '2' AND mast.type_id LIKE '6'" +
		 		"  AND que.diff_id IN(",tp1=" WHERE que.question_id=mast.question_id AND mast.category_id LIKE '2' AND mast.type_id LIKE '6'" +
		 		"  AND que.diff_id IN";
		String groupBy=" GROUP BY que.diff_id	";
		String endQuiree=" ORDER BY que.diff_id ASC LIMIT ";
	    String userAnsQueCondition="AND IF(0!=sub1.cnt,mast.question_id NOT IN (SELECT results.question_id FROM cp_test_results results WHERE results.userid LIKE '10069'), TRUE)";
		 
		 if(ArraysUtil.isListHasDuplicates(randomList))
		 {
			
			 //When  4 Difficulties are equal
		 if(randomList.get(0)==randomList.get(1)&&randomList.get(0)==randomList.get(2)&&randomList.get(0)==randomList.get(3))
		 { 
			 //System.out.println("0=1=2=3");
			 sentenceEquivalance=sentenceEquivalance+("" +topQuiree+topQuiree1+randomList.get(0)+")  "+endQuiree+ 4+")"); 
			 sentenceEquivalance=tp+userAnsQue+sentenceEquivalance+userAnsQueEnd+topQuiree1+(randomList.get(0)+")  "+userAnsQueCondition+endQuiree+ 4+")");
			// System.out.println("SE when all Equal : "+sentenceEquivalance);
		 }
		 else if(randomList.get(0)==randomList.get(1)&&randomList.get(0)==randomList.get(2)){
			// System.out.println("0=1&0=2");
			 sentenceEquivalance=sentenceEquivalance+(" " +topQuiree+topQuiree1+randomList.get(0)+") "+endQuiree+ 3+")");
			 sentenceEquivalance=tp+userAnsQue+sentenceEquivalance+userAnsQueEnd+topQuiree1+randomList.get(0)+") "+userAnsQueCondition+endQuiree+ 3+")";
			 String se1=topQuiree+topQuiree1+randomList.get(3)+") "+endQuiree+ 1+")";
			 sentenceEquivalance=sentenceEquivalance+(" UNION " +tp+userAnsQue+se1+userAnsQueEnd+topQuiree1+randomList.get(3)+") "+userAnsQueCondition+endQuiree+ 1+")");
			
		 }
		 else if(randomList.get(0)==randomList.get(1)&&randomList.get(0)==randomList.get(3)){
			// System.out.println("0=1&0=3");
			 sentenceEquivalance=sentenceEquivalance+(" " +topQuiree+topQuiree1+randomList.get(0)+") "+endQuiree+ 3+")");
			 sentenceEquivalance=tp+userAnsQue+sentenceEquivalance+userAnsQueEnd+topQuiree1+randomList.get(0)+") "+userAnsQueCondition+endQuiree+ 3+")";
			 String se1=topQuiree+topQuiree1+randomList.get(2)+") "+endQuiree+ 1+")";
			 sentenceEquivalance=sentenceEquivalance+(" UNION " +tp+userAnsQue+se1+userAnsQueEnd+topQuiree1+randomList.get(2)+") "+userAnsQueCondition+endQuiree+ 1+")");
			
		 }
		  else if(randomList.get(1)==randomList.get(2)&&randomList.get(1)==randomList.get(3))
		 {
			 //System.out.println(" 1=2 && 1==3");
			 sentenceEquivalance=sentenceEquivalance+(" " +topQuiree+topQuiree1+randomList.get(0)+") "+endQuiree+ 1+")");
			 sentenceEquivalance=tp+userAnsQue+sentenceEquivalance+userAnsQueEnd+topQuiree1+randomList.get(0)+") "+userAnsQueCondition+endQuiree+ 1+")"; 
			 String s1=	topQuiree+topQuiree1+randomList.get(1)+") "+endQuiree+ 3+")";
			 sentenceEquivalance=sentenceEquivalance+(" UNION " +tp+userAnsQue+s1+userAnsQueEnd+topQuiree1+randomList.get(1)+") "+userAnsQueCondition+endQuiree+ 3+")");
			 
			 
		 } 
		 else if(randomList.get(0)==randomList.get(1)){
			// System.out.println("0=1");
			 
			 sentenceEquivalance=sentenceEquivalance+(" " +topQuiree+topQuiree1+randomList.get(0)+") "+endQuiree+ 2+")");
			 sentenceEquivalance=tp+userAnsQue+sentenceEquivalance+userAnsQueEnd+topQuiree1+randomList.get(0)+") "+userAnsQueCondition+endQuiree+ 2+")";
			  if(randomList.get(2)==randomList.get(3))
			 {
				//  System.out.println("2=3");
				  String s1=topQuiree+topQuiree1+randomList.get(2)+") "+endQuiree+ 2+")";
				  sentenceEquivalance=sentenceEquivalance+(" UNION " +tp+userAnsQue+s1+userAnsQueEnd+topQuiree1+randomList.get(2)+") "+userAnsQueCondition+endQuiree+ 2+")");
				
			 }
			 else{
				 topQuiree=(topQuiree).replace("(",""); 
				 topQuiree="("+topQuiree; 
				 String s2=(" " +topQuiree+tp1+ArraysUtil.getArrayListAsSet(randomList.subList(2,4))+groupBy+endQuiree+ 2+")"); 
				 sentenceEquivalance=sentenceEquivalance+"union"+tp+userAnsQue+s2+userAnsQueEnd+tp1+" "+ArraysUtil.getArrayListAsSet(randomList.subList(2,4))+groupBy+userAnsQueCondition+endQuiree+ 2+")";
				 
			 }
			 
		 }
		 else if(randomList.get(0)==randomList.get(2)){
			// System.out.println(" 0= 2");
			 sentenceEquivalance=sentenceEquivalance+(" " +topQuiree+topQuiree1+randomList.get(0)+") "+endQuiree+ 2+")");
			 sentenceEquivalance=tp+userAnsQue+sentenceEquivalance+userAnsQueEnd+topQuiree1+randomList.get(0)+") "+userAnsQueCondition+endQuiree+ 2+")";
			
			 if(randomList.get(1)==randomList.get(3))
			 {
				 //System.out.println("1 =3 ");
				  String s1=topQuiree+topQuiree1+randomList.get(1)+") "+endQuiree+ 2+")";
				  sentenceEquivalance=sentenceEquivalance+(" UNION " +tp+userAnsQue+s1+userAnsQueEnd+topQuiree1+randomList.get(1)+") "+userAnsQueCondition+endQuiree+ 2+")");
				
			 }
			 else{
				 topQuiree=(topQuiree).replace("(",""); 
				 topQuiree="("+topQuiree; 
				 ArrayList<Integer> l1=new ArrayList<Integer>();
				 l1.add(randomList.get(1));
				 l1.add(randomList.get(3));
				 String s2=(" " +topQuiree+tp1+ArraysUtil.getArrayListAsSet(l1)+groupBy+endQuiree+ 2+")"); 
				 sentenceEquivalance=sentenceEquivalance+" union"+tp+userAnsQue+s2+userAnsQueEnd+tp1+" "+ArraysUtil.getArrayListAsSet(l1)+userAnsQueCondition+groupBy+endQuiree+ 2+")";
				 
			 }
			   
		 } 
		 else if(randomList.get(0)==randomList.get(3)){
			// System.out.println(" 0= 3");
			 sentenceEquivalance=sentenceEquivalance+(" " +topQuiree+topQuiree1+randomList.get(0)+") "+endQuiree+ 2+")");
			 sentenceEquivalance=tp+userAnsQue+sentenceEquivalance+userAnsQueEnd+topQuiree1+randomList.get(0)+") "+userAnsQueCondition+endQuiree+ 2+")";
			
			 if(randomList.get(1)==randomList.get(2))
			 {
				// System.out.println("1 =2 ");
				  String s1=topQuiree+topQuiree1+randomList.get(1)+") "+endQuiree+ 2+")";
				  sentenceEquivalance=sentenceEquivalance+(" UNION " +tp+userAnsQue+s1+userAnsQueEnd+topQuiree1+randomList.get(1)+") "+userAnsQueCondition+endQuiree+ 2+")");
				
			 }
			 else{
				 topQuiree=(topQuiree).replace("(",""); 
				 topQuiree="("+topQuiree; 
				 ArrayList<Integer> l1=new ArrayList<Integer>();
				 l1.add(randomList.get(1));
				 l1.add(randomList.get(2));
				 String s2=(" " +topQuiree+tp1+ArraysUtil.getArrayListAsSet(l1)+groupBy+endQuiree+ 2+")"); 
				 sentenceEquivalance=sentenceEquivalance+" union"+tp+userAnsQue+s2+userAnsQueEnd+tp1+" "+ArraysUtil.getArrayListAsSet(l1)+userAnsQueCondition+groupBy+endQuiree+ 2+")";
				 
			 }
			   
		 } 
		 
		 else if(randomList.get(1)==randomList.get(2))
		 {
			// System.out.println("1=2");
			 sentenceEquivalance=sentenceEquivalance+(" " +topQuiree+topQuiree1+randomList.get(0)+") "+endQuiree+ 1+")");
			 sentenceEquivalance=tp+userAnsQue+sentenceEquivalance+userAnsQueEnd+topQuiree1+randomList.get(0)+") "+userAnsQueCondition+endQuiree;
			 if(randomList.get(0)==randomList.get(3))
			 {
				 sentenceEquivalance=sentenceEquivalance+"0,1)";
			 }
			 else
				 sentenceEquivalance=sentenceEquivalance+1+")";	 
			
			 String s1=topQuiree+topQuiree1+randomList.get(1)+") "+endQuiree+ 2+")";
			 sentenceEquivalance=sentenceEquivalance+(" UNION " +tp+userAnsQue+s1+userAnsQueEnd+topQuiree1+randomList.get(1)+") "+userAnsQueCondition+endQuiree+ 2+")");
			 String s2=topQuiree+topQuiree1+randomList.get(3)+") "+endQuiree+ 1+")";
			 sentenceEquivalance=sentenceEquivalance+(" UNION " +tp+userAnsQue+s2+userAnsQueEnd+topQuiree1+randomList.get(3)+") "+userAnsQueCondition+endQuiree);
				
			 if(randomList.get(0)==randomList.get(3))
			 {
				 sentenceEquivalance=sentenceEquivalance+"1,1)";
			 }
			 else
				 sentenceEquivalance=sentenceEquivalance+1+")";	 
			 
		 }
		 else if(randomList.get(1)==randomList.get(3)){
			// System.out.println(" 1= 3");
			 sentenceEquivalance=sentenceEquivalance+(" " +topQuiree+topQuiree1+randomList.get(1)+") "+endQuiree+ 2+")");
			 sentenceEquivalance=tp+userAnsQue+sentenceEquivalance+userAnsQueEnd+topQuiree1+randomList.get(1)+") "+userAnsQueCondition+endQuiree+ 2+")";
			
			 if(randomList.get(0)==randomList.get(2))
			 {
				// System.out.println("0 =2 ");
				  String s1=topQuiree+topQuiree1+randomList.get(0)+") "+endQuiree+ 2+")";
				  sentenceEquivalance=sentenceEquivalance+(" UNION " +tp+userAnsQue+s1+userAnsQueEnd+topQuiree1+randomList.get(2)+") "+userAnsQueCondition+endQuiree+ 2+")");
				
			 }
			 else{
				 topQuiree=(topQuiree).replace("(",""); 
				 topQuiree="("+topQuiree; 
				 ArrayList<Integer> l1=new ArrayList<Integer>();
				 l1.add(randomList.get(0));
				 l1.add(randomList.get(2));
				 String s2=(" " +topQuiree+tp1+ArraysUtil.getArrayListAsSet(l1)+groupBy+endQuiree+ 2+")"); 
				 sentenceEquivalance=sentenceEquivalance+" union"+tp+userAnsQue+s2+userAnsQueEnd+tp1+" "+ArraysUtil.getArrayListAsSet(l1)+userAnsQueCondition+groupBy+endQuiree+ 2+")";
				 
			 }
			   
		 }
		
		
		 
		 else if(randomList.get(2)==randomList.get(3))
		 {
			 //System.out.println(" 2=3");
			 sentenceEquivalance=sentenceEquivalance+(" " +topQuiree+topQuiree1+randomList.get(0)+") "+endQuiree+ 1+")");
			 sentenceEquivalance=tp+userAnsQue+sentenceEquivalance+userAnsQueEnd+topQuiree1+randomList.get(0)+") "+userAnsQueCondition+endQuiree;
			 if(randomList.get(0)==randomList.get(1))
			 {
				 sentenceEquivalance=sentenceEquivalance+"0,1)";
			 }
			 else
				 sentenceEquivalance=sentenceEquivalance+1+")";	 
			 
			 String s1=topQuiree+topQuiree1+randomList.get(1)+") "+endQuiree+ 1+")";
			 sentenceEquivalance=sentenceEquivalance+(" UNION " +tp+userAnsQue+s1+userAnsQueEnd+topQuiree1+randomList.get(1)+") "+userAnsQueCondition+endQuiree);
			 if(randomList.get(0)==randomList.get(1))
			 {
				 sentenceEquivalance=sentenceEquivalance+"1,1)";
			 }
			 else
				 sentenceEquivalance=sentenceEquivalance+1+")";	 
			 String s2=topQuiree+topQuiree1+randomList.get(3)+") "+endQuiree+ 2+")";
			 sentenceEquivalance=sentenceEquivalance+(" UNION " +tp+userAnsQue+s2+userAnsQueEnd+topQuiree1+randomList.get(3)+") "+userAnsQueCondition+endQuiree+ 2+")");
			 	
		 }
		 }
	 else{
		// System.out.println("Else");
		 topQuiree=(topQuiree+topQuiree1).replace("(",""); 
		 topQuiree="("+topQuiree; 
		 sentenceEquivalance=sentenceEquivalance+(" " +topQuiree+" "+ArraysUtil.getArrayListAsSet(randomList.subList(0,4))+endQuiree+ 4+")"); 
		 sentenceEquivalance=tp+userAnsQue+sentenceEquivalance.replace(userAnsQueCondition, "")+userAnsQueEnd+tp1+" "+ArraysUtil.getArrayListAsSet(randomList.subList(0,4))+userAnsQueCondition+groupBy+endQuiree+ 4+")";
			 
		 }
	    /*if(sentenceEquivalance.toString().contains("GROUP BY"))
		 {
			sentenceEquivalance=sentenceEquivalance.toString().replace("GROUP BY", finishingTouch+"GROUP BY");
		 }
		 else finishingTouch=sentenceEquivalance.toString().replace("ORDER BY", finishingTouch+"      ORDER BY");
		 */
		 //  System.out.println("Sentance Equalnace is: ; \n "+sentenceEquivalance);
		  
		 return sentenceEquivalance;
		 
	 }
}
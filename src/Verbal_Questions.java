import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import jdk.nashorn.internal.ir.LiteralNode.ArrayLiteralNode.ArrayUnit;

import com.greenbuds.crunchprep.controller.contentcreator.IQuestionUploadController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.DifficultyAlgorithm;
import com.greenbuds.crunchprep.util.ArraysUtil;
import com.opensymphony.xwork2.util.ArrayUtils;


public class Verbal_Questions {
	 @Autowired
		static IQuestionUploadController questionUploadController;
	 public static void main(String a[]){
		
		// Integer i[]={1, 5, 2, 4, 3, 5, 3, 3, 3, 2, 2, 2, 2, 1,2, 3,2, 1, 1, 1};//when All Rc Difficulties are different
		//  Integer i[]={1, 5, 2, 4, 3, 5, 3, 3, 3, 3,3, 3, 3, 1,2, 3,2, 3,3,3};//when All three rc difficulties equal
		// Integer i[]={1, 5, 2, 4, 3, 5, 3, 3, 3, 3,3, 3, 3, 1,2, 3,2, 2,2,2};//when rc1 and rc2 are same
		// Integer i[]={1, 5, 2, 4, 3, 5, 2, 2, 2, 3,3, 3, 3, 1,2, 3,2, 2,2,2};//when rc1 and rc3 are same
		 Integer i[]={4, 5, 5, 5, 4, 5, 4, 4, 4, 4, 4, 4, 4,1,3,4,4, 5, 5, 5};
         List<Integer> randomList=Arrays.asList(i);
       String finalQuiree=prepareVerbalQuery(DifficultyAlgorithm._2_2_2_2_1_4_4_3,
				  randomList);
         System.out.println("\n Final quiree is: \n\t"+finalQuiree);
       CPException exception = null;
		JSONObject jsonObject = new JSONObject();
	try {
			jsonObject.put("inputQuiree", finalQuiree);
			questionUploadController.requiredTableDataInJSONFormate(jsonObject);
			jsonObject.remove("inputQuiree");
			System.out.println("\n\n Row Count is :  Json Data is: "+jsonObject.toString());//+"\n----Row COunt is; "+jsonObject.getInt("rowCount"));
	 		
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
	      
		jsonObject.remove("inputQuiree");

	    }        
	 
	 public static String prepareVerbalQuery(DifficultyAlgorithm algorithm,
				List<Integer> randomList) {
		 String finalQuery="";
		 String union=" union ";
		 String userAnsQueChecking="and que.question_id not in (select results.question_id from cp_test_results results where results.userid like '10069') ";
			
		 //System.out.println(randomList+"\t"+algorithm.getPattern());
		 String textCompletion="(select   que.diff_id,mast.question_id,mast.category_id,mast.type_id  from cp_question_masters mast ,cp_questions que " +
		 		" where que.question_id=mast.question_id " +
		 		" and mast.category_id like '1' and mast.type_id like '1' and que.diff_id in("+randomList.get(0)+","+randomList.get(1)+") " +userAnsQueChecking;
		 				 
		        if(randomList.get(0)!=randomList.get(1))
		 		{
		 			textCompletion=textCompletion+" group by que.diff_id " ;
		 		}
		 		
		 textCompletion=textCompletion+" order by mast.category_id,mast.type_id,que.diff_id asc limit 2) " +
		 		"union " +
		 		" (select   que.diff_id,mast.question_id,mast.category_id,mast.type_id  from  cp_question_masters mast ,cp_questions que " +
		 		" where que.question_id=mast.question_id and mast.category_id like '1' and mast.type_id like '2' and que.diff_id in("+randomList.get(2)+","+randomList.get(3)+") " +userAnsQueChecking;
		 				 
		  if(randomList.get(2)!=randomList.get(3))
	 		{
	 			textCompletion=textCompletion+" group by que.diff_id " ;
	 		}
	 		
	 textCompletion=textCompletion+
		 		"  order by mast.category_id,mast.type_id,que.diff_id asc limit 2)" +
		 		" union  " +
		 		" (select   que.diff_id,mast.question_id,mast.category_id,mast.type_id  from cp_question_masters mast ,cp_questions que " +
		 		" where que.question_id=mast.question_id and mast.category_id like '1' and mast.type_id like '3' and que.diff_id in("+randomList.get(4)+","+randomList.get(5)+")" +userAnsQueChecking;
 				 
	 if(randomList.get(4)!=randomList.get(5))
		{
			textCompletion=textCompletion+" group by que.diff_id " ;
		}
		
textCompletion=textCompletion+
		 		" order by mast.category_id,mast.type_id,que.diff_id asc limit 2) ";
		 
		 
		 String sentenceEquivalance=sentenceEquivalanceQuiree(randomList.subList(13, 17));/*"(select   que.diff_id,mast.question_id,mast.category_id,mast.type_id  from cp_question_masters mast ,cp_questions que" +
		 		"  where que.question_id=mast.question_id and mast.category_id like '2' and mast.type_id like '6'" +
		 		"  and que.diff_id in"+ sublist.replace("[", "(").replace("]", ")"); 
		 if(!ArraysUtil.isListHasDuplicates(randomList.subList(13, 17)))
		 {
			 sentenceEquivalance=sentenceEquivalance+ "group by que.diff_id"; 
		 } 
		 sentenceEquivalance=sentenceEquivalance+ " order by que.diff_id asc limit 4) "; */
			String readingcomprehension="(select que.diff_id,mast.question_id,mast.category_id,mast.type_id  from cp_question_masters mast ,cp_questions que" +
					 " where mast.passage_id=";
			String criticalReasoning="(select   que.diff_id,mast.question_id,mast.category_id,mast.type_id  from " +
					" cp_question_masters mast ,cp_questions que where que.question_id=mast.question_id " +
					" and mast.category_id like '9' and mast.type_id like '15'" ;
		
		 String readingComprehension1=null,readingComprehension2=null,readingComprehension3=null;
		 switch (algorithm) {
			case _2_2_2_2_1_4_4_3:
			 // System.out.println("matched Pattern is :_2_2_2_2_1_4_4_3 ");
				criticalReasoning=criticalReasoning+"and que.diff_id in("+randomList.get(8)+") " +userAnsQueChecking+"  limit 1)  ";
				 // readingComprehension1=readingcomprehension+" '"+randomList.get(6)+"' ";
				 
				  readingComprehension1=readingcomprehension+getReadingComprehensionQuery(randomList.get(6),2,userAnsQueChecking);//3 rd parameter is for number of questions limit				  
				
				    //readingComprehension2=readingcomprehension+"'"+randomList.get(9)+"' ";
				  readingComprehension2=readingcomprehension+ getReadingComprehensionQuery(randomList.get(9),4,userAnsQueChecking);
				  
				  // readingComprehension3=readingcomprehension+"'"+randomList.get(17)+"' ";
				  readingComprehension3=readingcomprehension+ getReadingComprehensionQuery(randomList.get(17),3,userAnsQueChecking);
				  //System.out.println("Reading Comprehension : rc1 : "+readingComprehension1+"\n rc2 :"+readingComprehension2+"\n rc3"+readingComprehension3);
				  
				  finalQuery=textCompletion+union+readingComprehension1+union+criticalReasoning+union+readingComprehension2+union+sentenceEquivalance+union+readingComprehension3;
				
				
	
				break;
			case _2_2_2_3_4_4_3:
				//  System.out.println("matched Pattern is :_2_2_2_3_4_4_3 ");
				  
				 // readingComprehension1=readingcomprehension+" '"+randomList.get(6)+"' ";
				 
				  readingComprehension1=readingcomprehension+getReadingComprehensionCriteria(randomList.get(6), 3, 1,userAnsQueChecking);//3 rd parameter is for number of questions limit				  
				
				// readingComprehension2=readingcomprehension+"'"+randomList.get(9)+"' ";
				  readingComprehension2=readingcomprehension+getReadingComprehensionQuery(randomList.get(9), 4,userAnsQueChecking);
				  
				 //  readingComprehension3=readingcomprehension+"'"+randomList.get(17)+"' ";
				  readingComprehension3=readingcomprehension+getReadingComprehensionCriteria(randomList.get(17),3,2,userAnsQueChecking);
				   
				  finalQuery=textCompletion+union+readingComprehension1+union+readingComprehension2+union+sentenceEquivalance+union+readingComprehension3; 
	
				break;
			case _2_2_2_3_4_4_1_2:
				//  System.out.println("matched Pattern is : _2_2_2_3_4_4_1_2");
				criticalReasoning=criticalReasoning+"and que.diff_id in("+randomList.get(17)+")  " +userAnsQueChecking+" limit 1)  ";
				
				 // readingComprehension1=readingcomprehension+" '"+randomList.get(6)+"' ";
					 
				  readingComprehension1=readingcomprehension+ getReadingComprehensionQuery(randomList.get(6),3,userAnsQueChecking);//3 rd parameter is for number of questions limit				  
				
				// readingComprehension2=readingcomprehension+"'"+randomList.get(9)+"' ";
				  readingComprehension2=readingcomprehension+ getReadingComprehensionQuery(randomList.get(9),4,userAnsQueChecking);
				  
				  // readingComprehension3=readingcomprehension+"'"+randomList.get(18)+"' ";
				  readingComprehension3=readingcomprehension+ getReadingComprehensionQuery(randomList.get(18),2,userAnsQueChecking);
				   
				  finalQuery=textCompletion+union+readingComprehension1+union+readingComprehension2+union+sentenceEquivalance+union+criticalReasoning+union+readingComprehension3;
			
				break;
			case _2_2_2_2_5_4_3:
				//System.out.println("matched Pattern is : _2_2_2_2_5_4_3");
				 
				  //readingComprehension1=readingcomprehension+" '"+randomList.get(6)+"' ";
					 
				  readingComprehension1=readingcomprehension+getReadingComprehensionQuery(randomList.get(6),2,userAnsQueChecking);//3 rd parameter is for number of questions limit				  
				
				// readingComprehension2=readingcomprehension+"'"+randomList.get(8)+"' ";
				  readingComprehension2=readingcomprehension+getReadingComprehensionQuery(randomList.get(8),5,userAnsQueChecking);
				  
				  // readingComprehension3=readingcomprehension+"'"+randomList.get(17)+"' ";
				  readingComprehension3=readingcomprehension+getReadingComprehensionQuery(randomList.get(17),3,userAnsQueChecking);
				   
				  finalQuery=textCompletion+union+readingComprehension1+union+readingComprehension2+union+sentenceEquivalance+union+readingComprehension3;
			
				
			
				break;
			case _2_2_2_2_5_4_1_2:
				//System.out.println("matched Pattern is : _2_2_2_2_5_4_1_2");
				criticalReasoning=criticalReasoning+"and que.diff_id in("+randomList.get(17)+") " +userAnsQueChecking+"  limit 1)  ";
				
				 // readingComprehension1=readingcomprehension+" '"+randomList.get(6)+"' ";
					 
				  readingComprehension1=readingcomprehension+getReadingComprehensionCriteria(randomList.get(6),2,1,userAnsQueChecking);//3 rd parameter is for number of questions limit				  
				
				// readingComprehension2=readingcomprehension+"'"+randomList.get(8)+"' ";
				  readingComprehension2=readingcomprehension+getReadingComprehensionQuery(randomList.get(8), 5,userAnsQueChecking);
				//  System.out.println(readingComprehension2);
				  // readingComprehension3=readingcomprehension+"'"+randomList.get(18)+"' ";
				  readingComprehension3=readingcomprehension+getReadingComprehensionCriteria(randomList.get(18),2,2,userAnsQueChecking);;
				//  System.out.println(readingComprehension3);
				  finalQuery=textCompletion+union+readingComprehension1+union+readingComprehension2+union+sentenceEquivalance+union+criticalReasoning+union+readingComprehension3;
		//	System.out.println("Queries : "+readingComprehension1+"\n"+readingComprehension2+"\n"+readingComprehension3);
			
				break;
			}
		 return finalQuery;
	 }
	 public static String getReadingComprehensionQuery(int diffId,int rowCount,String userAnsQueChecking)
	 {
		 String criteria=" (select mast.passage_id  from cp_question_masters mast where mast.passage_id " +
		 		"  like (select pa.passage_id  ss from cp_reading_comprehension_passage pa where pa.difficulty like '"+diffId+"' )" +userAnsQueChecking+" having count(*)="+rowCount+" LIMIT 1) AND mast.question_id=que.question_id )";
		 return criteria;
	 }
	 public static String getReadingComprehensionCriteria(int diffId,int rowCount,int rcCount,String userAnsQueChecking)
	 {
		 String criteria=" (select mast.passage_id  from cp_question_masters mast where mast.passage_id " +
			 		"  like (select pa.passage_id  ss from cp_reading_comprehension_passage pa where pa.difficulty";
		 switch(rcCount)
		 {
		 case 1:criteria=criteria+" like '"+diffId+"' )" +userAnsQueChecking+
			 				"  having count(*)="+rowCount+" LIMIT 0,1) AND mast.question_id=que.question_id )";
			 break;
		 case 2:criteria=criteria+" like '"+diffId+"' )" +userAnsQueChecking+
	 				"  having count(*)="+rowCount+" LIMIT 1,1) AND mast.question_id=que.question_id )";
			 break;
		 }
		 return criteria;
		/* //System.out.println("Randome List is : "+randomList);
		 System.out.println("Diffictulty Ids : diffId1 :  "+diffId1+"\t diffId2 :   "+diffId2+"\t  diffId3   "+diffId3);
		 if(((diffId1==diffId2)&&(diffId1==diffId3)) && rcCount==1 )//if 3 rc have the same diff's the rc1 condition
			{
		  
		  criteria=" limit 0,1) and  mast.question_id=que.question_id limit "+questionCount+")";
			}
		 if(((diffId1==diffId2)&&(diffId1==diffId3)) && rcCount==2 )//if 3 rc have the same diff's then rc2 condition
			{
			
		  criteria=" limit 1,1) and  mast.question_id=que.question_id limit "+questionCount+")";
			}
		  else if(((diffId1==diffId2)&&(diffId1==diffId3)) && rcCount==3 )//if 3 rc have the same diff's then rc3 condition
			{
		 	//System.out.println("rc1==rc3 & rc3 ");;
		  criteria=" limit 2,2) and  mast.question_id=que.question_id limit "+questionCount+")";
			} 
		  
		 else  if((diffId1==diffId2)  && rcCount==1 )//if rc1 and rc2 are same then rc1 condition
	     {
		 //System.out.println("(diffId1==diffId2)  && rcCount==1 ");
		  criteria=" limit 0,1) and  mast.question_id=que.question_id limit "+questionCount+")";
	      } 
	    else if((diffId1==diffId2)  && rcCount==2 )//if rc1 and rc2 are same then rc2 condition
	  {
		 // System.out.println("(diffId1==diffId2)  && rcCount==2");
		  criteria=" limit 1,1) and  mast.question_id=que.question_id limit "+questionCount+")"; 
	  }
		 
	  else if(((diffId2==diffId3)) && rcCount==2)//if rc2 and rc3 are same then rc1 conditon
		{
	// System.out.println("((diffId2==diffId3)) && rcCount==2 ");
	  criteria=" limit 0,1) and  mast.question_id=que.question_id limit "+questionCount+")";
		}
	  else if(((diffId2==diffId3)) && rcCount==3)//if if rc2 and rc3 are same then rc3 conditon
		{
 // System.out.println("((diffId2==diffId3)) && rcCount==3 ");
	  criteria=" limit 1,1) and  mast.question_id=que.question_id limit "+questionCount+")";
		}
	  else if(((diffId1==diffId3)) && rcCount==1 )//if  rc1 and rc2 are same then rc1 condition
		{
		 // System.out.println("((diffId1==diffId3)) && rcCount==1 ");
	  criteria=" limit 0,1) and  mast.question_id=que.question_id limit "+questionCount+")";
		}
	  else if(((diffId1==diffId3)) && rcCount==3)//if  rc1 and rc2 are same then rc3 condition
		{
		// System.out.println("((diffId1==diffId3)) && rcCount==3 ");
	  criteria=" limit 1,1) and  mast.question_id=que.question_id limit "+questionCount+")";
		} 
	
	  else  if(((diffId1!=diffId2)&&(diffId1!=diffId3)))
	  {
	 	 //System.out.println(" ((diffId1!=diffId2)&&(diffId1!=diffId3)) ");
		  criteria=" limit 1) and  mast.question_id=que.question_id limit "+questionCount+")";  
	  }
	  else if(((diffId1==diffId2)&&(diffId1!=diffId3))||((diffId2==diffId3)&&(diffId1!=diffId2))||((diffId1==diffId3)&&(diffId1!=diffId2)) )
	  {
		 // System.out.println("either rc11==rc2 !rc3 (or)  rc2=rc3!rc1 (or)  rc1=rc3!rc2");
		  criteria=" limit 1) and  mast.question_id=que.question_id limit "+questionCount+")";    
	  }*/
	  
		 
	 }
	 
	 private static String sentenceEquivalanceQuiree(List<Integer> randomList){
		 StringBuffer sentenceEquivalance=new StringBuffer();
		String topQuiree="( SELECT que.diff_id,mast.question_id,mast.category_id,mast.type_id " +
		 		"  FROM cp_question_masters mast,cp_questions que " +
		 		" WHERE que.question_id=mast.question_id AND mast.category_id LIKE '2' AND mast.type_id LIKE '6'" +
		 		"  AND que.diff_id IN(";
		String groupBy=" GROUP BY que.diff_id	";
		String endQuiree=" ORDER BY que.diff_id ASC LIMIT ";
		 if(ArraysUtil.isListHasDuplicates(randomList))
		 {
			 
		 if(randomList.get(0)==randomList.get(1)&&randomList.get(0)==randomList.get(2)&&randomList.get(0)==randomList.get(3))
		 {
			 
			 sentenceEquivalance.append(" " +
			 		topQuiree+randomList.get(0)+")  "+endQuiree+ 4+")");
		 }
		 else if(randomList.get(0)==randomList.get(1)&&randomList.get(0)==randomList.get(2)){
			 sentenceEquivalance.append(" " +
				 		topQuiree+randomList.get(0)+") "+endQuiree+ 3+")");
			 sentenceEquivalance.append(" UNION " +
				 		topQuiree+randomList.get(3)+") "+endQuiree+ 1+")");
		 }
		 else if(randomList.get(0)==randomList.get(1)){
			 
			 sentenceEquivalance.append(" " +
				 		topQuiree+randomList.get(0)+") "+endQuiree+ 2+")");
			  if(randomList.get(2)==randomList.get(3))
			 {
				  sentenceEquivalance.append(" UNION " +
					 		topQuiree+randomList.get(2)+") "+endQuiree+ 2+")");
				
			 }
			 else{
				 topQuiree=topQuiree.replace("(","");
				 topQuiree="union ("+topQuiree;
				  sentenceEquivalance.append(" " +
					 		topQuiree+" "+ArraysUtil.getArrayListAsSet(randomList.subList(2,4))+groupBy+endQuiree+ 2+")");
			 }
		 }
		 
		 else if(randomList.get(1)==randomList.get(2)&&randomList.get(1)==randomList.get(3))
		 {
			
			 sentenceEquivalance.append(" " +
				 		topQuiree+randomList.get(0)+") "+endQuiree+ 1+")");
			
			 sentenceEquivalance.append(" UNION " +
				 		topQuiree+randomList.get(1)+") "+endQuiree+ 3+")");
			 
		 }
		 else if(randomList.get(1)==randomList.get(2))
		 {
			 sentenceEquivalance.append(" " +
				 		topQuiree+randomList.get(0)+") "+endQuiree+ 1+")");
			 sentenceEquivalance.append(" UNION " +
				 		topQuiree+randomList.get(1)+") "+endQuiree+ 2+")");
			 sentenceEquivalance.append(" UNION " +
				 		topQuiree+randomList.get(3)+") "+endQuiree+ 1+")");
			
			 
		 }
		 
		 else if(randomList.get(2)==randomList.get(3))
		 {
			 sentenceEquivalance.append(" " +
				 		topQuiree+randomList.get(0)+") "+endQuiree+ 1+")");
			 sentenceEquivalance.append(" UNION " +
				 		topQuiree+randomList.get(1)+") "+endQuiree+ 1+")");
			 sentenceEquivalance.append(" UNION " +
				 		topQuiree+randomList.get(3)+") "+endQuiree+ 2+")");
			 
		 }
		 }
	 else{
		 topQuiree=topQuiree.replace("(","");
		 topQuiree="("+topQuiree;
		  sentenceEquivalance.append(" " +
			 		topQuiree+" "+ArraysUtil.getArrayListAsSet(randomList.subList(0,4))+endQuiree+ 4+")");
			 
		 }
		 String finishingTouch="and que.question_id not in (select results.question_id from cp_test_results results where results.userid like '10069')";
		 if(sentenceEquivalance.toString().contains("GROUP BY"))
		 {
			finishingTouch=sentenceEquivalance.toString().replace("GROUP BY", finishingTouch+"      GROUP BY");
		 }
		 else finishingTouch=sentenceEquivalance.toString().replace("ORDER BY", finishingTouch+"      ORDER BY");
		 
		// System.out.println("\n\n\n  fINAL qUIREE For Sentance Equalnace is: ; \n "+finishingTouch);
		 return finishingTouch;
		 
	 }
}

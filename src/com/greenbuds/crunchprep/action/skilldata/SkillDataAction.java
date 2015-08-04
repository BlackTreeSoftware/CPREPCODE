package com.greenbuds.crunchprep.action.skilldata;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO;
import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.bo.skilldata.SkillDataBO;
import com.greenbuds.crunchprep.controller.lessons.LessonsController;
import com.greenbuds.crunchprep.controller.skilldata.SkillDataController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.Section;
import com.opensymphony.xwork2.ActionSupport;

public class SkillDataAction extends ActionSupport implements SessionAware,ServletRequestAware,ServletResponseAware{
	
    private SkillDataController skillDataController;
    HttpServletRequest request;
    List<SkillDataBO> skillData=new ArrayList<SkillDataBO>();
    List<SkillDataBO> quantSkillData=new ArrayList<SkillDataBO>();
    List<SkillDataBO> verbalSkillData=new ArrayList<SkillDataBO>();
    List<LessonsmasterBO> skillbasedlessons= new ArrayList<LessonsmasterBO>();
    LessonsmasterBO lessonsmasterBO =null;
    
    private Map<String, Object> sessionMap;
    private String skillDataList;
    private String quantSkillDataList;
    private String verbalSkillDataList;
    private String quantSkills;
    private String verbalSkills;
    List<String> quantList=new ArrayList<String>();
    List<String> verbalList=new ArrayList<String>();
    
	public String skillData()
	{
		skillDataController=skillDataController.getInstance();
		LoginUserBO bo = (LoginUserBO) sessionMap.get("user");
		int userid=bo.getUser_id();
		try {
			skillData=skillDataController.getSkillData(userid);  
			for(int i=0;i<skillData.size();i++)
			{
				SkillDataBO skillDataBO=skillData.get(i);
				if(skillDataBO.getSection_name().equals("Quantitative"))
				{
					quantSkillData.add(skillDataBO);
					quantList.add(skillDataBO.getSkill()); 
				}
				if(skillDataBO.getSection_name().equals("Verbal"))
				{
					
					verbalSkillData.add(skillDataBO); 
					verbalList.add(skillDataBO.getSkill()); 
				}
			}
			/*System.out.println("Quant Skills:"+quantSkills);
			System.out.println("Verbal Skills:"+verbalSkills);*/
			
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		skillDataList = new net.sf.json.JSONArray().fromObject(skillData).toString();
		quantSkillDataList= new net.sf.json.JSONArray().fromObject(quantSkillData).toString();
		verbalSkillDataList= new net.sf.json.JSONArray().fromObject(verbalSkillData).toString();
		quantSkills=new net.sf.json.JSONArray().fromObject(quantList).toString();
		verbalSkills=new net.sf.json.JSONArray().fromObject(verbalList).toString();
		 
		//request.setAttribute("skillData", skillDataList);
		// System.out.println("Skilldata::::"+skillDataList);
		request.setAttribute("quantSkills", quantSkills);
		request.setAttribute("verbalSkills", verbalSkills);
		request.setAttribute("quantSkillData", quantSkillDataList);
		request.setAttribute("verbalSkillData", verbalSkillDataList);
		return "success";
	}
	
	private HttpServletResponse response;
	public void getDashboardSkillData()
	{
		String[][] top3Verbal=new String[3][2],top3Quant=new String[3][2],least3Verbal=new String[3][3],least3Quant=new String[3][3];
		String[] least_skillIds=new String[3];
		int quant_index=0;
		 JSONArray list=new JSONArray();
		 JSONArray skillBasedLessonsList=new JSONArray();
	     JSONObject jsonobject=new JSONObject();
		 
		skillDataController=SkillDataController.getInstance();
		LoginUserBO bo = (LoginUserBO) sessionMap.get("user"); 
		int userid=bo.getUser_id();
		 //int userid=10278;
		//   int userid=10069;
		try {
			 byte i1=0,leastVindex=0,verb=0;
			skillData=skillDataController.getSkillData(userid);
			 for(int k=0;k<skillData.size();k++)
			{
				SkillDataBO bo1=(SkillDataBO)skillData.get(k);
				 //System.out.println(" k : "+k+"\t"+bo1.getSkill()+"\t skill id : "+bo1.getSkillId());
			} 
			
			
			
			
            int leastVerbalIndex=(skillData.size()-3);
			for(int i=0;i<skillData.size();i++){
				SkillDataBO bo1=(SkillDataBO)skillData.get(i); 
				if(i<3)
				{
				String array[]=new String[2]; 
				array[0]=bo1.getSkill();
				array[1]=bo1.getProgress();
				top3Quant[i]=array;  
				} 
				//System.out.println("Section id : "+bo1.getSection_id()+"\t"+Section.VERBAL.getSectionId());
			   if(bo1.getSection_id()==Section.VERBAL.getSectionId())
			   {
				  // System.out.println("verb : "+verb); 
				   if(verb<3)
				   {
                    String array[]=new String[2];						 
					array[0]=bo1.getSkill();
					array[1]=bo1.getProgress();
					top3Verbal[verb]=array;
			    //  System.out.println("top 3 verbal : "+top3Verbal[verb][0]);
			      ++verb;
				   }
				 
				 if(verb==0)
				 {
					 quant_index=skillData.size()-1;
				 }
				 else if(verb==1)
				 {
					 quant_index=skillData.size()-2;
				 }
				 else if(verb==2)
				 {
					 quant_index=skillData.size()-3;
				 }
			 } 
			
				
			}
			
			//for least3 verbal skills
			if(skillData.size()!=0){
				System.out.println("size of skill Data (((((((((((((((((((9       "+skillData.size());
			for(int i=(skillData.size()-1);leastVindex<3;i--){ 
				
				SkillDataBO bo1=(SkillDataBO)skillData.get(i);  
				 
				 	 if(bo1.getSection_id()==Section.VERBAL.getSectionId())
					   {
				 
						 
							    String array[]=new String[3];						 
								array[0]=bo1.getSkill();
								array[1]=bo1.getProgress();
								array[2]=String.valueOf(bo1.getSkillId());
								least3Verbal[leastVindex]=array;  
						 //  System.out.println("least index data : "+least3Verbal[leastVindex][0]+"\t index Is : "+leastVindex); 
								
								++leastVindex;
					   } 
				 	 if(i==0)
				 	 {
				 		 break;
				 	 }
			}
			}
			
			byte quant=0;
	 if(quant_index==0)
	 {
		 quant_index=skillData.size();
	 }
	 
	 
		   if(quant_index!=0){
			  //System.out.println("quant_index :"+quant_index);
			 for(int j=quant_index-2;quant<3;j--)
			{
 
				SkillDataBO bo1=(SkillDataBO)skillData.get(j);
				String array[]=new String[3];						 
				array[0]=bo1.getSkill();
				array[1]=bo1.getProgress();
				array[2]=String.valueOf(bo1.getSkillId());
				least3Quant[quant]=array;
				++quant; 
		  //System.out.println("least 3 Quant skills"+j+"\t "+bo1.getSkill());
			}
		   }
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		 try{
		 
		
		//for Top3 Skills
		  boolean top1=false,top2=false,top3=false;
			if((top3Quant[0][0]!=null))
			{
		  // System.out.println("Q1");
			JSONArray array=new JSONArray();
			String a[]=top3Quant[0];
			array.put(a[0]);
			array.put(a[1]);
			list.put(array);
			top1=true;
		 
			}
			else if(top3Quant[0][0]==null)
			{
				// System.out.println("Q1 is null");
				for(int p2=0;p2<3;p2++)
				{
					JSONArray array=new JSONArray();
					String a[]=top3Verbal[p2];
					array.put(a[0]);
					array.put(a[1]);
					list.put(array);
				}
				top1=true;
				top2=true;top3=true;
			}
			if((top3Quant[1][0]!=null))
			{
				// System.out.println("Q2");
			JSONArray array=new JSONArray();
			String a[]=top3Quant[1];
			array.put(a[0]);
			array.put(a[1]);
			list.put(array);
			 top2=true;
		 
			}
			else if((top3Quant[1][0]==null)&&(top3Quant[0][0]!=null)) 
			{
				// System.out.println("Q2 is null");
				for(int p2=0;p2<2;p2++)
				{
					JSONArray array=new JSONArray();
					String a[]=top3Verbal[p2];
					array.put(a[0]);
					array.put(a[1]);
					list.put(array);
				}
				top2=true;top3=true;
			}
			if((top3Verbal[0][0]!=null))
			{
				// System.out.println("V1");
			JSONArray array=new JSONArray();
			String a[]=top3Verbal[0];
			array.put(a[0]);
			array.put(a[1]);
			//System.out.println("TOP 1 Verbal : "+a[1]);
			list.put(array);
			 top3=true;
			} 
			else if(top3Verbal[0][0]==null)
			{
				 //System.out.println("Verbal doesn't contain any data");
				 byte control=0,index=0;
				 if(top1==true&&top2==true&&top3==false)
				 {
					 control=1;
					 index=2;
				 }
				 else if(top1==false&&top2==false&&top3==false) 
				 {
					 control=3;
				 }
				for(int p2=0;p2<control;p2++)
				{
					JSONArray array=new JSONArray();
					String a[]=top3Quant[index];
					array.put(a[0]);
					array.put(a[1]);
					list.put(array);
					++index;
				}
			}  
			// System.out.println("Data is for Top3  Skills: "+list);
				 
			 	//this is for least three skills
			   boolean least1=false,least2=false,least3=false;
				if((least3Quant[0][0]!=null))
				{
			    //System.out.println("Q1");
				JSONArray array=new JSONArray();
				String a[]=least3Quant[0];
				array.put(a[0]);
				array.put(a[1]);
				least_skillIds[0]=a[2];
				list.put(array);
				least1=true;
			 
				}
				else if(least3Quant[0][0]==null)
				{
			// System.out.println("Q1 is null");
					for(int p2=0;p2<3;p2++)
					{
						JSONArray array=new JSONArray();
						String a[]=least3Verbal[p2];
						array.put(a[0]);
						array.put(a[1]);
						least_skillIds[p2]=a[2];
						list.put(array);
					}
					least1=true;
					least2=true;least3=true;
				}
				if((least3Quant[1][0]!=null))
				{
			// System.out.println("Q2");
				JSONArray array=new JSONArray();
				String a[]=least3Quant[1];
				array.put(a[0]);
				array.put(a[1]);
				least_skillIds[1]=a[2];
				list.put(array);
				 least2=true;
			 
				}
				else if((least3Quant[1][0]==null)&&(least3Quant[0][0]!=null)) 
				{
					// System.out.println("Q2 is null");
					for(int p2=0;p2<2;p2++)
					{
						JSONArray array=new JSONArray();
						String a[]=least3Verbal[p2];
						array.put(a[0]);
						array.put(a[1]);
						least_skillIds[p2]=a[2];
						list.put(array);
					}
					least2=true;least3=true;
				}
				if((least3Verbal[0][0]!=null))
				{
					// System.out.println("V1");
				JSONArray array=new JSONArray();
				String a[]=least3Verbal[0];
				array.put(a[0]);
				array.put(a[1]);
				list.put(array);
				least_skillIds[2]=a[2];
				 least3=true;
				} 
				else if(least3Verbal[0][0]==null)
				{
				// System.out.println("Verbal doesn't contain any data");
					 byte control=0,index=0;
					 if(least1==true&&least2==true&&least3==false)
					 {
						 control=1;
						 index=2;
					 }
					 else if(least1==false&&least2==false&&least3==false) 
					 {
						 control=3;
					 }
					for(int p2=0;p2<control;p2++)
					{
						JSONArray array=new JSONArray();
						String a[]=least3Quant[index];
						array.put(a[0]);
						array.put(a[1]);
						least_skillIds[p2]=a[2];
						list.put(array);
						++index;
					}
				}
				  lessonsmasterBO= new LessonsmasterBO();
				  int skillId1=0,skillId2=0,skillId3=0;
				     if(least_skillIds[0]!=null)  
				     { 
				    	 skillId1= Integer.parseInt(least_skillIds[0].toString() );
				     }
				     if(least_skillIds[1]!=null)
				    	 skillId2 =Integer.parseInt(least_skillIds[1].toString() );
				     if(least_skillIds[2]!=null)
					     skillId3=Integer.parseInt(least_skillIds[2].toString() );
					 lessonsmasterBO.setSkill_id(new int[]{skillId1,skillId2,skillId3});
				     skillbasedlessons= skillDataController.displyLeastLessonsBasedOnSkillData(lessonsmasterBO);
				     
				     
				     for(LessonsmasterBO displyList:skillbasedlessons){
						 
				    	 JSONArray lessonsArray=new JSONArray();
				    	 lessonsArray.put(displyList.getSublesson_id());
				    	 lessonsArray.put(displyList.getLesson_id());
				    	 lessonsArray.put(displyList.getLesson_name());
				    	 lessonsArray.put(displyList.getSection_id());
				    	 lessonsArray.put(displyList.getLesson_master());
				    	 lessonsArray.put(displyList.getSection_name());
				    	 skillBasedLessonsList.put(lessonsArray);
				     }
				    
			 
			 	// System.out.println("Data is for Least3  Skills: "+list);
				
					 try {
						 jsonobject.put("data1", list);
						 jsonobject.put("skillBasedLessons", skillBasedLessonsList);
						 response.getWriter().write((jsonobject.toString()));  
					}  catch (JSONException e) {
						 //System.out.println("JSON exception");
						e.printStackTrace();
					} catch (IOException e) {
						 //System.out.println("IO Exception");
						e.printStackTrace();
					} 

					// System.out.println("Data is for Least3  Skills!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: "+list);
				     //System.out.println("skill Id's for least skills :"+least_skillIds[0]+"\t"+least_skillIds[1]+"\t"+least_skillIds[2]);
				     
				   

					 //System.out.println("Data is for Least3  Skills: "+list);
				     //System.out.println("skill Id's for least skills :"+least_skillIds[0]+"\t"+least_skillIds[1]+"\t"+least_skillIds[2]);

	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	 
		
	}
	
	
	

	
	public List<LessonsmasterBO> getSkillbasedlessons() {
		return skillbasedlessons;
	}


	public void setSkillbasedlessons(List<LessonsmasterBO> skillbasedlessons) {
		this.skillbasedlessons = skillbasedlessons;
	}


	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		 this.response=arg0;
	}
	public String getSkillDataList() {
		return skillDataList;
	}
	public void setSkillDataList(String skillDataList) {
		this.skillDataList = skillDataList;
	}
	public List<SkillDataBO> getSkillData() {
		return skillData;
	}
	public void setSkillData(List<SkillDataBO> skillData) {
		this.skillData = skillData;
	}
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		// TODO Auto-generated method stub
		this.sessionMap=sessionMap;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
 
}

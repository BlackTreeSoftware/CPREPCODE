package com.greenbuds.crunchprep.controller.skilldata;
import java.sql.SQLException;
import java.util.List;

import com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO;
import com.greenbuds.crunchprep.bo.skilldata.SkillDataBO;
import com.greenbuds.crunchprep.controller.contentcreator.LessonController;
import com.greenbuds.crunchprep.dao.lessons.LessonsDAO;
import com.greenbuds.crunchprep.dao.skilldata.SkillDataDAO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.DBException;
public class SkillDataController implements ISkillDataController{
	private static SkillDataController skillDataController;
	private SkillDataController(){
		
	}
	private SkillDataDAO skillDataDAO;
	public static synchronized SkillDataController getInstance(){
		if(skillDataController==null)
			skillDataController=new SkillDataController();
		return skillDataController;
	}

	@Override
	public List<SkillDataBO> getSkillData(int userid) throws CPException {
		// TODO Auto-generated method stub
		 skillDataDAO=skillDataDAO.getInstance();
		 return skillDataDAO.getSkillData(userid);
	}

	
 	public List<LessonsmasterBO> displyLeastLessonsBasedOnSkillData(LessonsmasterBO lessonsmasterBO) throws DBException, SQLException{
   		
 		skillDataDAO=skillDataDAO.getInstance();
	     return skillDataDAO.displyLeastLessonsBasedOnSkillData(lessonsmasterBO);
	   		
	   		
	   		
	   	}
	
	
}

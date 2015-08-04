package com.greenbuds.crunchprep.action.registration;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.registration.ChangePasswordBo;
import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
import com.greenbuds.crunchprep.bo.registration.VrplBO;
import com.greenbuds.crunchprep.controller.registration.ChangepasswordController;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.exception.EmailExceptions;
import com.greenbuds.crunchprep.setups.DateFormate;
import com.greenbuds.crunchprep.util.DateUtil;
import com.greenbuds.crunchprep.util.MailUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ChangePasswordAction extends ActionSupport implements
		SessionAware, ServletResponseAware, ServletRequestAware {

	private ChangePasswordBo chgpassbo;
	private RegistrationBO registrationBO = new RegistrationBO();
	private ChangepasswordController chgpasscontroller;
	private HttpServletRequest servletRequest;
	private Map<String, Object> map;
	private Map<String, Object> sessionMap;
	private HttpServletResponse response;
	private HttpServletRequest request;
	public static Logger logger = Logger.getLogger(ChangePasswordAction.class);

	public ChangePasswordBo getChgpassbo() {
		return chgpassbo;
	}

	public void setChgpassbo(ChangePasswordBo chgpassbo) {
		this.chgpassbo = chgpassbo;
	}

	/*
	 * method for change password
	 * 
	 * @author acheemakurthi
	 */

	public String ChangePass() {

		String result = "error";

		if (chgpassbo.getPassword().equalsIgnoreCase(
				chgpassbo.getConfpassword())) {
			System.out.println("In if block" + chgpassbo.getPassword());
			// String currentPassword = (String) session.getAttribute("");
			chgpasscontroller = new ChangepasswordController();
			// LoginUserBO userBo = new LoginUserBO();
			LoginUserBO userBo = (LoginUserBO) sessionMap.get("user");
			// userBo.setUser_id(9976);
			System.out.println("The user id is" + userBo.getUser_id());
			chgpassbo.setUserid("" + userBo.getUser_id());
			try {
				String s = chgpasscontroller.changePassword(chgpassbo);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("JSP values are:" + chgpassbo.getPassword() + ""
					+ chgpassbo.getPassword() + ""
					+ chgpassbo.getConfpassword());
			addActionError("Password changed successfully");
			return "success";
		} else {
			addActionError("Password change failed");
			return "error";
		}

	}

	/*
	 * method for image upload into a folder named images in server path
	 * 
	 * @author acheemakurthi
	 */
	public void Uploadimage() throws JSONException, IOException {
		 System.out.println("Upload image is calling @@@@@@@@@@@@@@@@");
		JSONObject obj = new JSONObject();
		String s = "";
		System.out.println("In image upload action class");
		try {

			LoginUserBO userBo = (LoginUserBO) sessionMap.get("user");
			String imagepath = request.getSession().getServletContext()
					.getInitParameter("userimagepath");
			String filePath = ServletActionContext.getServletContext()
					.getRealPath(imagepath);
			/*System.out.println("the file path is.111111111111.........----------------------- "
							+ filePath + "user image content type");
			*/
			/*System.out.println("the file path is 2222222222.................................. "+ filePath);*/
			
			File dir = new File("filePath");
			
			if (!dir.exists()) {
				/*System.out.println("creating directory3333333333::::::::::::::::::::::::::::::: " + dir);*/
				dir.mkdir();
			/*	System.out.println("dir created44444444444444444444444" + dir);*/
			}
			/*String fileName = userBo.getUser_id()
					+ "."
					+ chgpassbo.getUserimageContentType()
							.substring(
									chgpassbo.getUserimageContentType()
											.indexOf("/") + 1);*/
			String fileName = userBo.getUser_id()+ ".jpg";
			
		  /* System.out.println("file name is555555555555555555555555555555:" + chgpassbo.getUserimage()+ "image content type"+ chgpassbo.getUserimageContentType());*/
			
			
			
			
			File fileToCreate = new File(filePath, fileName);
			/*System.out
					.println("filepath666666666666666666666666666------------------------------------------------------------"
							+ filePath);
*/
			FileUtils.copyFile(chgpassbo.getUserimage(), fileToCreate);
		} catch (Exception e) {
			e.printStackTrace();

		addActionError(e.getMessage());

			// return "error";
		}
		// return "success";

		obj.put("message", "Image Uploaded Successfully");
		response.getWriter().write(obj.toString());

	}

	/*
	 * 
	 * user Settings method to customize settings
	 * 
	 * @author acheemakurthi
	 */

	public void Usersettings() throws JSONException, IOException {
		JSONObject obj = new JSONObject();
		// LoginUserBO userBo = new LoginUserBO();
		LoginUserBO userBo = (LoginUserBO) sessionMap.get("user");
		// userBo.setUser_id(9976);
		System.out.println("The user id is" + userBo.getUser_id());
		this.registrationBO.setUser_id(userBo.getUser_id());
		this.registrationBO.setProfile(request.getParameter("profile"));
		this.registrationBO.setTestperformance(request.getParameter("testperformance"));
		chgpasscontroller = new ChangepasswordController();
		String s = "";
		System.out.println("user settings are:"+ this.registrationBO.getTestperformance());
		try {
			s = chgpasscontroller.usersettings(registrationBO);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hai you are in user settings");
		// System.out.println("user settings are:"
		// +chgpassbo.getSettingsid()+""+chgpassbo.getTestperformance());
		obj.put("message", s);
		response.getWriter().write(obj.toString());
	}

	public String getUserProfileDetails() {
		try {
			
			System.out.println("Get User Profile Details is calling@@@@@@@@@@@@@@@@@@@@@@");
			// LoginUserBO userBo = new LoginUserBO();
			LoginUserBO userBo = (LoginUserBO) sessionMap.get("user");
			// userBo.setUser_id(9976);
			System.out.println("The user id is" + userBo.getUser_id());
			chgpasscontroller = new ChangepasswordController();
			this.registrationBO = chgpasscontroller.getUserDetails(userBo
					.getUser_id());

			this.getRegistrationBO().setTest_date(
					DateUtil.getDate(this.getRegistrationBO().getTest_date(),
							DateFormate.MYSQL));

			String imagepath = request.getSession().getServletContext()
					.getInitParameter("userimagepath");
			String filePath = ServletActionContext.getServletContext()
					.getRealPath("/");
			
			String temppath = request.getSession().getServletContext()
					.getInitParameter("DefaultImage");

		  File fileToCreate = new File(filePath+imagepath + registrationBO.getUser_id()	+ ".jpg");
		  String userimagepath=imagepath + registrationBO.getUser_id()	+ ".jpg";
		
		  //  System.out.println("Image path\n\t"+imagepath +"\t\nfilepath\t"+filePath+"\t\ntemppath"+temppath+"user id"+registrationBO.getUser_id()	+ ".jpg");
	    //	System.out.println("The userimgpath"+userimagepath);
			
		 
			filePath += registrationBO.getUser_id() + ".jpg";
			if (fileToCreate.exists()) {
               System.out.println("Image exists"+userimagepath);
			   //registrationBO.setAvatar_path(userimagepath);
			   userBo.setAvatar_path(userimagepath);
			} else {
				System.out.println("Image not exists"+temppath);
				
				//registrationBO.setAvatar_path(temppath);
			  userBo.setAvatar_path(temppath);
			}
			//System.out.println("the file path is@@@@@@@@$$$$$%%%%%%%%.................................. "+userBo.setAvatavatar_path(temppath));
		
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		return Action.SUCCESS;
	}

	public void updateUserProfile() throws IOException, JSONException {
		JSONObject ob = new JSONObject();
		String s = "";
		try {
			System.out.println("............................in update ");
			// LoginUserBO userBo = new LoginUserBO();
			LoginUserBO userBo = (LoginUserBO) sessionMap.get("user");
			// userBo.setUser_id(9976);
			getRegistrationBO().setUser_id(userBo.getUser_id());
		
			chgpasscontroller = new ChangepasswordController();
			System.out.println("The bean details are"
					+ getRegistrationBO().getFirst_name());
			s = chgpasscontroller.updateUserDetails(getRegistrationBO());
			/*System.out
					.println("hai you are in user settings--------------" + s);
*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		ob.put("message", s);
		response.getWriter().write(ob.toString());
		// return "success";
	}
	
	
	public String referFriend() throws Exception{
		VrplBO vrplBO =null;
		int quant_questions=0;
		int verbal_question=0;
		LoginUserBO userBo = (LoginUserBO) sessionMap.get("user");
		//InetAddress.getLocalHost().getHostAddress();
		String url="http://"+request.getServerName()+":"+request.getServerPort()+""+request.getContextPath()+"/refer.action?referId=CRINV"+userBo.getUser_id();
		System.out.println(url);
		registrationBO=new RegistrationBO();
		registrationBO.setProfile(url);
		
		
		chgpasscontroller = new ChangepasswordController();
		
		vrplBO=chgpasscontroller.getQuestions();
		quant_questions=vrplBO.getQuant_questions();
		verbal_question=vrplBO.getVerbal_question();
		
		
		 vrplBO = new VrplBO();
		
		String atype = request.getParameter("actiontype");
		if(atype==null)
		{
			atype = "votes";
			
		}
		vrplBO.setActiontype(atype);
		vrplBO.setUser_id(userBo.getUser_id());
		vrplBO.setQuant_questions(quant_questions);
		vrplBO.setVerbal_question(verbal_question);
		
	
		
		System.out.println("**************************************** VRPL  : "+atype);
		
		String vote = chgpasscontroller.vrplMethod(vrplBO);
		System.out.println("Final Votes : "+vote);
		int progress = 0;
		int intvotes = Integer.parseInt(vote);
		if(intvotes>0)
		{
			progress = Math.round(((intvotes*100)/7));
			System.out.println("Progress : "+progress);
			
		}
		request.setAttribute("votes", vote);
		request.setAttribute("progress", progress);
		request.setAttribute("refurl", url);
		
		getPrintWriter().write(vote);
		return SUCCESS;
	}
	
	
	
	public void gmailContacts()
	{
		LoginUserBO userBo = (LoginUserBO) sessionMap.get("user");		
		String url="http://"+request.getServerName()+":"+request.getServerPort()+""+request.getContextPath()+"/refer.action?referId=CRINV"+userBo.getUser_id();
		System.out.println(url);
		registrationBO=new RegistrationBO();
		registrationBO.setProfile(url);		
		String emails = request.getParameter("emails");
		System.out.println("Emails GOt IT : "+emails);
		
		
		String content = "<h3>Thank you for register with CrunchPrep.Click the below link to verify your account.</h3> " +
					"<br />" +url;
		
		
		String[] split = emails.split(",");
		//String[] split = {"satyajohnny@gmail.com","satyajohnny1@gmail.com","crunchprep.com@gmail.com"};
		//System.out.println("Array of Strings : "+split[0]+split[1]+split[2]+"   : "+ww[0]+ww[1]+ww[2]);
		try {
			boolean flag = MailUtil.sendMailToMany("Inivitation From Your Friend", content, split);
			if(flag){
				System.out.println("Mail Sent Sucesssfully");
			}else{
				System.out.println("Mail Failed");
			}
			
		} catch (EmailExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	
	
	
	public RegistrationBO getRegistrationBO() {
		return registrationBO;
	}

	public void setRegistrationBO(RegistrationBO registrationBO) {
		this.registrationBO = registrationBO;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		// TODO Auto-generated method stub
		this.sessionMap = sessionMap;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;

	}
	public PrintWriter getPrintWriter() {
		PrintWriter pw = null;
		try {
			pw = getResponse().getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pw;
	}
	public static HttpServletResponse getResponse() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		return response;
	}

}

package com.greenbuds.crunchprep.action.contentcreator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.contentcreator.PolicyBO;
import com.greenbuds.crunchprep.controller.contentcreator.IPolicyController;
import com.greenbuds.crunchprep.controller.contentcreator.PolicyController;
import com.greenbuds.crunchprep.exception.CPException;
import com.opensymphony.xwork2.ActionSupport;

public class PolicyAction extends ActionSupport implements SessionAware,
		ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(PolicyAction.class);
	private IPolicyController policyController;
	private String result = null;
	private PolicyBO policyBO;
	private Map<String, Object> sessionMap;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private List<PolicyBO> policyBOList;
	

	public void policySave() throws JSONException, IOException {
		JSONObject responseData = new JSONObject();
		policyController = new PolicyController();
		try {
			System.out.println("before save");
			if (policyController.policySave(policyBO)) {
				System.out.println("in save");
				result = "success";
				//setPolicyBOList(policyController.getTermsPolicies());
			}
		} catch (CPException e) {
			LOGGER.error(e);
			addActionMessage("Terms and Policy Save Failed");
			e.printStackTrace();
			result = "error";
		}
		responseData.put("data", result);
		response.getWriter().write(responseData.toString());
		//return result;
	}

	

	public void policyUpdate() throws JSONException, IOException {
		policyController = new PolicyController();
		JSONObject responseData = new JSONObject();
		try {
			if (policyController.policyUpdate(policyBO)) {
				result = "success";
				setPolicyBOList(policyController.getTermsPolicies());
			}
		} catch (CPException e) {
			LOGGER.error(e);
			addActionMessage("Terms and Policy Update Failed");
			result = "error";
		}
		responseData.put("data", result);
		response.getWriter().write(responseData.toString());
	}

	
	public String policyDelete() {
		System.out.println("IN DELETE");
		policyController = new PolicyController();
		String arrayValues=request.getParameter("checkboxvalues");
		StringTokenizer tokens=new StringTokenizer(arrayValues,",");
		while (tokens.hasMoreElements()) {
			String nextId=(String) tokens.nextElement();
			int id=Integer.parseInt(""+nextId);
			PolicyBO bo = new PolicyBO();
			bo.setPolicy_id(Integer.parseInt(nextId));
			try {
				if (policyController.policyDelete(bo)) {
					result = "success";
					setPolicyBOList(policyController.getTermsPolicies());
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error(e);
				addActionMessage("Terms and Policy Delete Failed");
				result = "error";
			}
		}
		
		return result;
	}

	
	
	public String getTermsPolicies() throws CPException {
		policyController = new PolicyController();
		setPolicyBOList(policyController.getTermsPolicies());
		return "success";
	}

	public void setSession(Map<String, Object> sessionMap) {
		// TODO Auto-generated method stub
		this.sessionMap = sessionMap;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public List<PolicyBO> getPolicyBOList() {
		return policyBOList;
	}

	public void setPolicyBOList(List<PolicyBO> policyBOList) {
		this.policyBOList = policyBOList;
	}

	public PolicyBO getPolicyBO() {
		return policyBO;
	}

	public void setPolicyBO(PolicyBO policyBO) {
		this.policyBO = policyBO;
	}



	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
}

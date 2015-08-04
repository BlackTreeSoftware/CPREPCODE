/**
 * 
 */
package com.greenbuds.crunchprep.setups;

/**
 * @author rrajulapati
 *
 */
public enum ActionsSetups {
   SESSION_EXPIRED("sessionexpired"),LOGIN_PAGE("homepage");
   private String status;
   private ActionsSetups(String status)
   {
	   this.status=status;
   }
   public String getStatus()
   {
	   return this.status;
   }
}

package com.greenbuds.crunchprep.setups;

import org.json.JSONArray;

public enum ReviewReason {
	R1("Select","R1"),
	R2("I did't Read the Lesson","R2"),
	R3("I Forgot the Answer","R3"),
	R4("It's Out of my Knowledge","R4"),
	R5("I think it's very difficult","R5"),
	R6("Some Grammer Mistake","R6"),
	R7("The Question is Wrong","R7"),
	R8("Actually It's Correct, But..","R8"),
	R9("Time is Not Enough","R9"),
	R10("The Best and Tuff Qsn Ever!","R10")	
	
	
	;
public String reason;
private String reasonName;
private static JSONArray array;

private ReviewReason(String reason,String reasonName)
{
	this.reason=reason;
	this.reasonName=reasonName;
}
String getReasongType()
{
	return reason;
}
public String getReasonName(){
	return this.reasonName;
}

public static String getMyReason(String reason){
	for(ReviewReason reviewReason:ReviewReason.values()){
		if(reviewReason.getReasonName().equalsIgnoreCase(reason))return reviewReason.getReasongType();
	}
	return "";
}
public static JSONArray getMyReasons(){
	if(array==null)
	{	array=new JSONArray();
	for(ReviewReason reviewReason:ReviewReason.values()){
		array.put(reviewReason.getReasongType());
	}
	}

	return array;
}



public static void main(String[] args) {
	ReviewReason val[] = 	ReviewReason.values();
	for(int i=0;i<val.length;i++)
	{
	//op=op+"<option value=\'"+val[i]+"\'>"+val[i].reason+"</option>";
	System.out.println(val[i]+":"+val[i].reason);		
	}
	
	
}

}

/**
 * 
 */
package com.greenbuds.crunchprep.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.setups.DifficultyAlgorithm;
import com.greenbuds.crunchprep.setups.MathAlgorithm;
import com.greenbuds.crunchprep.setups.QuantComparssion;
import com.greenbuds.crunchprep.setups.Quantitative;



/**
 * @author rrajulapati
 *
 */
class A{
	int x=10;
    @Override
    public boolean equals(Object obj) {
    	// TODO Auto-generated method stub
    	return this.hashCode()==obj.hashCode();
    }
    @Override
    public int hashCode() {
    	// TODO Auto-generated method stub
    	return x;
    }
	
}
class B{
	int y=10;
	 @Override
	    public boolean equals(Object obj) {
	    	// TODO Auto-generated method stub
	    	return this.hashCode()==obj.hashCode();
	    }
	    @Override
	    public int hashCode() {
	    	// TODO Auto-generated method stub
	    	return y;
	    }
		
}
public class DummyClass {
public static void main(String[] args) throws JSONException {
		// TODO Auto-generated method stub

	A oa=new A();
	B ob=new B();
	String str="sairam";
	String str1=new String("sairam");
	System.out.println(" "+(str==str1));
	
	System.out.println("  "+oa.equals(ob));
	}


}

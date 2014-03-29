package com.js.questionme;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Questionss")
public class Questionclass extends ParseObject{
	 public String getAnswer() {
		    return getString("MA");
		  }
	 public void setMCA(String value){
		 put("Question", value);
	 }
	 public void setMC1(String value){
		 put("MC1", value);
	 }
	 public void setMC2(String value){
		 put("MC2", value);
	 }
	 public void setMC3(String value){
		 put("MC3", value);
	 }
	 public void setMA(String value){
		 put("MA", value);
	 }
	 public void setQid(int value){
		 put("Qid", value);
	 }
	 public String getq(){
		return getString("Question");
		 
	 }
	 
}



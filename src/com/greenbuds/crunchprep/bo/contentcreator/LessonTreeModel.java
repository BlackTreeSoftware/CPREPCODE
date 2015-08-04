package com.greenbuds.crunchprep.bo.contentcreator;

import java.util.List;

public class LessonTreeModel {
	private int id;  
    private String text;  
    private List<LessonTreeModel> children;  
    public int getId() {  
        return id;  
    }  
    public void setId(int id) {  
        this.id = id;  
    }  
    public String getText() {  
        return text;  
    }  
    public void setText(String text) {  
        this.text = text;  
    }  
    public List<LessonTreeModel> getChildren() {  
        return children;  
    }  
    public void setChildren(List<LessonTreeModel> children) {  
        this.children = children;  
    }  
}

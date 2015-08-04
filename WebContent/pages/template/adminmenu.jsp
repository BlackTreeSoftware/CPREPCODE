<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@ page session="true" %>
    <%@taglib prefix="s" uri="/struts-tags" %>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <!-- <script src="assets/js/charts/flot/jquery.flot.min.js"></script> -->
   
   
</head>
<body>
  <!-- .aside -->
  
        <aside class="bg-black nav-xs hidden-print" id="nav">          
          <section class="vbox">
            <section class="w-f scrollable">
              <div class="clearfix slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0" data-size="10px" data-railopacity="0.2">
               

               <!-- nav -->                 
                <nav class="nav-primary hidden-xs">
                  <div class="text-muted text-sm hidden-nav-xs padder m-t-sm m-b-sm">Start</div>
                  <ul class="nav nav-main" data-ride="collapse">
                  
                    <c:if test="${session.user.role_id == 1}">
                    <li>
                      <a href="#" class="auto">
                        <span class="pull-right text-muted">
                          <i class="i i-circle-sm-o text"></i>
                          <i class="i i-circle-sm text-active"></i>
                        </span>
                       <!--  <b class="badge bg-danger pull-right">4</b> -->
                        <i class="fa fa-user">
                        </i>
                        <span class="font-bold">Admin Content</span>
                      </a>
                      <ul class="nav dk">
                      <li >
                          <a href="AdminRegistration.action" class="auto">                                                        
                            <i class="i i-dot"></i>
                            <span>Admin Registration</span>
                          </a>
                        </li>
                        <%-- <li >
                          <a href="#" class="auto">                                                        
                            <i class="i i-dot"></i>
                            <span>Problems Solved</span>
                          </a>
                        </li>
                        <li >
                          <a href="QuestionsUploading.action" class="auto">                                                        
                            <i class="i i-dot"></i>
                            <span>Lessons Taken</span>
                          </a>
                        </li> --%>
                        <li >
                          <a href="viewlogs" class="auto">                                                        
                            <i class="i i-dot"></i>
                            <span>View Logs</span>
                          </a>
                        </li>
                        <li >
                          <a href="ipaddress" class="auto">                                                        
                            <i class="i i-dot"></i>
                            <span>IP Address Tracker</span>
                          </a>
                        </li>
                        
                        <li >
                          <a href="assignmentorview" class="auto">                                                        
                            <i class="i i-dot"></i>
                            <span>Assign Mentor to Student</span>
                          </a>
                        </li>
                        
                        <li >
                          <a href="mentoraccounts" class="auto">                                                        
                            <i class="i i-dot"></i>
                            <span>Mentor Accounts</span>
                          </a>
                        </li>
                        
                       <%--  <li >
                          <a href="#" class="auto">                                                        
                            <i class="i i-dot"></i>
                            <span>Student Accounts</span>
                          </a>
                        </li> --%>
                         <li >
                          <a href="questionsperDay" class="auto">                                                        
                            <i class="i i-dot"></i>
                            <span>Question per Day</span>
                          </a>
                        </li>
                        <li >
                          <a href="lessonsperDay" class="auto">                                                        
                            <i class="i i-dot"></i>
                            <span>Lessons per Day</span>
                          </a>
                        </li>
                        
                        <%--  <li >
                          <a href="#" class="auto">                                                        
                            <i class="i i-dot"></i>
                            <span>Mentor Accounts</span>
                          </a>
                        </li> --%>
                    
                        
                      </ul>
                    </li>
                    </c:if>
                     <c:if test="${session.user.role_id == 1 || session.user.role_id == 3}">
                    <li  class="active">
                      <a href="#" class="auto">
                        <span class="pull-right text-muted">
                          <i class="i i-circle-sm-o text"></i>
                          <i class="i i-circle-sm text-active"></i>
                        </span>
                        <i class="i i-lab icon">
                        </i>
                        <span class="font-bold">Content Creator</span>
                      </a>
                      <ul class="nav dk">
                        
                        <li >
                          <a href="manage-skill-levels.action" class="auto">                            
                           <!--  <b class="badge bg-info pull-right">369</b>      -->                                                   
                            <i class="i i-dot"></i>

                            <span>Add Skill Levels</span>
                          </a>
                        </li>
                        <li >
                          <a href="adddifficulties" class="auto">                                                        
                            <i class="i i-dot"></i>

                            <span>Add Difficulty Levels</span>
                          </a>
                        </li>
                        <li >
                          <a href="orintationQuestions" class="auto">                                                        
                            <i class="i i-dot"></i>

                            <span>Orientation Questions</span>
                          </a>
                        </li>
                        <li >
                          <a href="obtain_lesson.action" class="auto">                            
                           <!--  <b class="badge bg-dark pull-right">8</b>   -->                                                      
                            <i class="i i-dot"></i>

                            <span>Add Lessons</span>
                          </a>
                        </li>
                        <li >
                          <a href="addSubLessons.action" class="auto">                                                        
                            <i class="i i-dot"></i>

                            <span>Add Sub-lessons</span>
                          </a>
                        </li>
						
						 <li >
                          <a href="addReferralMasters.action" class="auto">                            
                           <!--  <b class="badge bg-info pull-right">369</b>      -->                                                   
                            <i class="i i-dot"></i>

                            <span>Add Referral Masters</span>
                          </a>
                        </li> 
                        
                         <li >
                          <a href="referral-condition-panel.action" class="auto">                            
                           <!--  <b class="badge bg-info pull-right">369</b>      -->                                                   
                            <i class="i i-dot"></i>

                            <span>Add Referral Conditions</span>
                          </a>
                        </li>  
                        
                        <li >
                          <a href="addQuestionActions.action" class="auto">                            
                           <!--  <b class="badge bg-info pull-right">369</b>      -->                                                   
                            <i class="i i-dot"></i>

                            <span>Add Question Actions</span>
                          </a>
                        </li>           
						
                        <%-- <li >
                          <a href="list.html" class="auto">                                                        
                            <i class="i i-dot"></i>

                            <span>Add Math Cheat Sheet</span>
                          </a>
                        </li> --%>
                        
                        <!--Add Lesson Start-->
                           <li >
                          <a href="#table" class="auto">                            
                            <span class="pull-right text-muted">
                              <i class="i i-circle-sm-o text"></i>
                              <i class="i i-circle-sm text-active"></i>
                            </span>                            
                            <i class="i i-dot"></i>

                            <span>Add Questions</span>
                          </a>
                          <ul class="nav dker">
                          <li>
                          <a href="AwaQuestionslist.action">
                                <i class="i i-dot"></i>
                                <span><strong>AWA</strong></span>
                              </a>
                          </li>
                            <li >
                            	 
                              <a href="#">
                                <i class="i i-dot"></i>
                                <span><strong>Verbal</strong></span>
                              </a>
                                            <ul class="nav dker">
                                              <li >
                                              <a href="SentanceEqualanceQuestions.action" class="auto">
                                                <i class="i i-dot"></i>
                                                <span>Sentence Equvilance</span>
                                              </a>
                                            </li>
                                            <li >
                                              <a href="TextCompletionQuestions.action" class="auto">
                                                <i class="i i-dot"></i>
                                                <span>Text Completion</span>
                                              </a>
                                            </li>
                                             <li >
                                              <a href="readingComprehensionPage.action">
                                                <i class="i i-dot"></i>
                                                <span>Reading Comprehension Passage</span>
                                              </a>
                                            </li>
                                            
                                               <li >
                                              <a href="reading-comprehension-questions.action?sectionId=2">
                                                <i class="i i-dot"></i>
                                                <span>Reading Comprehension Questions</span>
                                              </a>
                                            </li>
                                             <li >
                                              <a href="obtain_CriticalReasoning.action?sectionId=2" class="auto">
                                                <i class="i i-dot"></i>
                                                <span>Critical Reasoning</span>
                                              </a>
                                            </li>
                                            
                                          </ul>
                            </li>
                            <li >
                              <a href="#">
                                <i class="i i-dot"></i>
                                <span><strong>Quantitative</strong></span>
                              </a>
                              		<ul class="nav dker">
                                            <li >
                                              <a href="quant-comparision-questions.action" class="auto">
                                           
                                                <i class="i i-dot"></i>
                                                <span>Quantitative Comparision</span>
                                              </a>
                                            </li>
                                            <li >
 
                                              <a href="problemSolvingTable.action" class="auto">                                                 
 


                                                <i class="i i-dot"></i>
                                                <span>Problem Solving</span>
                                              </a>
                                            </li>
                                            
                                             <li >
                                              <a href="NumericEntryTable.action" class="auto">
                                                <i class="i i-dot"></i>
                                                <span>Numeric Entry</span>
                                              </a>
                                            </li>
                                            
                                           <li >
                                              <a href="Data-Interpretation.action" class="auto">
                                                <i class="i i-dot"></i>
                                                <span>Data Interpretation Graph</span>
                                              </a>
                                            </li> 

                                          </ul>
                                	<!--End of Add Quant Sub questions-->
                            </li>
                             
                          </ul>
                        </li>
                        <li >
                          <a href="Mailmaster.action" class="auto">                            
                           <!--  <b class="badge bg-info pull-right">369</b>      -->                                                   
                            <i class="i i-dot"></i>

                            <span>Add Mail Template</span>
                          </a>
                        </li>
                        <%-- <li >
                          <a href="Data-Interpretation.action" class="auto">                            
                           <!--  <b class="badge bg-info pull-right">369</b>      -->                                                   
                            <i class="i i-dot"></i>

                            <span>Data Interpretation Graph</span>
                          </a>
                        </li> --%>
                        
                        <li >
                          <a href="TermsConditions.action" class="auto">                            
                           <!--  <b class="badge bg-info pull-right">369</b>      -->                                                   
                            <i class="i i-dot"></i>

                            <span>Add Terms &amp; Conditions</span>
                          </a>
                        </li>
                        <!--Add Lesson End-->
                        
                        
                        
                      </ul>
                    </li>
                    </c:if>
                    <!--Add Questions-->
                    <c:if test="${session.user.role_id == 2}">
                    <li>
                      <a href="#" class="auto">
                        <span class="pull-right text-muted">
                          <i class="i i-circle-sm-o text"></i>
                          <i class="i i-circle-sm text-active"></i>
                        </span>
                        <i class="fa fa-smile-o">
                        </i>
                        <span class="font-bold">Mentor</span>
                      </a>
                      <ul class="nav dk">
                       <%--  <li >
                          <a href="mentorAssignedStudentList" class="auto">                                                        
                            <i class="i i-dot"></i>

                            <span>Student List </span>
                          </a>
                        </li> --%>
                        <li >
                          <a href="mentorStudentList" class="auto">                                                        
                            <i class="i i-dot"></i>

                            <span>Mentor Student List </span>
                          </a>
                        </li>
                       
                  <%--       <li >
                          <a href="studentProgressbar" class="auto">                                                        
                            <i class="i i-dot"></i>

                            <span>StudentProgressBar</span>
                          </a>
                        </li>
                        <li >
                          <a href="#" class="auto">                                                        
                            <i class="i i-dot"></i>

                            <span>Send Mail</span>
                          </a>
                        </li>
                        <li >
                          <a href="mentorStudentLogs" class="auto">                                                        
                            <i class="i i-dot"></i>

                            <span>Check Student Logs</span>
                          </a>
                        </li>
                        <li >
                          <a href="#" class="auto">                                                        
                            <i class="i i-dot"></i>

                            <span>Student Doubts</span>
                          </a>
                        </li> --%>
                                                                                              
                        
                      </ul>
                    </li>
                    </c:if>
                    
                    
                    <c:if test="${session.user.role_id == 1}">
                    <li>
                      <a href="#" class="auto">
                        <span class="pull-right text-muted">
                          <i class="i i-circle-sm-o text"></i>
                          <i class="i i-circle-sm text-active"></i>
                        </span>
                        <i class="fa fa-smile-o">
                        </i>
                        <span class="font-bold">Mentor</span>
                      </a>
                      <ul class="nav dk">
                       <%--  <li >
                          <a href="mentorAssignedStudentList" class="auto">                                                        
                            <i class="i i-dot"></i>

                            <span>Student List </span>
                          </a>
                        </li> --%>
                        <li >
                          <a href="adminMentorStudents" class="auto">                                                        
                            <i class="i i-dot"></i>

                            <span>Admin Mentor Students </span>
                          </a>
                        </li>
                       
                  
                      </ul>
                    </li>
                    </c:if>
                    
                    
                    <c:if test="${session.user.role_id == 1 }">
                    <li>
                      <a href="#" class="auto">
                        <span class="pull-right text-muted">
                          <i class="i i-circle-sm-o text"></i>
                          <i class="i i-circle-sm text-active"></i>
                        </span>
                        <i class="i i-docs icon">
                        </i>
                        <span class="font-bold">Flash Cards</span>
                      </a>
                      <ul class="nav dk">
                        <li >
                          <a href="#" class="auto">                                                        
                            <i class="i i-dot"></i>

                            <span>Math Flash Cards </span>
                          </a>
                        </li>   
                        <li >
                          <a href="#" class="auto">                                                        
                            <i class="i i-dot"></i>

                            <span>Verbal Flash Cards </span>
                          </a>
                        </li>                                                                         
                                     
                        
                      </ul>
                    </li>
                    
                    
                    <li>
                      <a href="#" class="auto">
                        <span class="pull-right text-muted">
                          <i class="i i-circle-sm-o text"></i>
                          <i class="i i-circle-sm text-active"></i>
                        </span>
                        <i class="fa fa-money">
                        </i>
                        <span class="font-bold">Sub<br>Scriptions</span>
                      </a>
                      <ul class="nav dk">
                        <li >
                          <a href="#" class="auto">                                                        
                            <i class="i i-dot"></i>

                            <span>Add Subscriptions</span>
                          </a>
                        </li>     
                        
                        <li >
                          <a href="#" class="auto">                                                        
                            <i class="i i-dot"></i>

                            <span>Add Subscription Plans</span>
                          </a>
                        </li>                                                                                      
                        
                      </ul>
                    </li>     
                    
                    
                    <li>
                      <a href="#" class="auto">
                        <span class="pull-right text-muted">
                          <i class="i i-circle-sm-o text"></i>
                          <i class="i i-circle-sm text-active"></i>
                        </span>
                        <i class="fa fa-sun-o">
                        </i>
                        <span class="font-bold">Badges</span>
                      </a>
                      
                    </li>                                        
                    </c:if>
                    
                  </ul>
                  
                  
                  
                </nav>
                <!-- / nav -->
              </div>
            </section>
            
             
          </section>
        </aside>
        <!-- /.aside -->
        
</body>
</html>
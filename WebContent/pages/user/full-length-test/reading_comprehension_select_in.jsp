  
 <div class="well-practice practice-content" id="ajaxarea">
												<p class="text-center m-b" ><strong>Instructions</strong> :<label id="directions"></label></p>

												<div class="col-xs-12 col-lg-6 m-t  text-scroller" >
													 <p id="passagetext" class="select-in-passage"> 
                                                       </p>
													 
													
													 
													<div class="clearfix"></div>
													
													<hr class=" m-ten">
													
													 
												</div>
                                                
                                                
                                                
                                               
                                             
											<div class="row">                                                
                                                
                                                <div class="col-xs-12 col-lg-6  text-scroller">
                                                <hr class=" hr-hidden">	
                                                	<p class="qarea m-left" id="questiontext"></p>
													<div class="form-group m-left">
													 
													</div>
												</div>
                                             </div>
                                             </div> 
     <script type="text/javascript">
    /*  $(document).ready(function(){
    	  $('.select-in-passage').click(function(){
    		 var flagStatus="";
    		  alert($('#index').val()+" selected line:"+$('#previousSelection').val());
    		  if($('#_mark').hasClass("active"))
			  { 
			   flagStatus="FLAG";
			  }
		     else
			  { 
			  flagStatus="UNFLAG";
			  } 
			  var ans=JSON.parse($('#updatedAnswers').val()); 
			  var index=$('#index').val();
			  var array=[];
				 for(var i=0;i<ans.length;i++){
					 if(ans[i].Question_index==index){ 
						 array.push($('#previousSelection').val());
						 ans[i].UserAnswer=array;  
						 ans[i].IsFlagged=flagStatus; 
						 alert('User answer:'+ans[i].UserAnswer);
					 }
				 }  
				 $('#updatedAnswers').val(JSON.stringify(ans));    
				 
    	 });    
     }); */
     $(".select-in-passage").delegate(".textPart", "click", function(){ 
	      var flagStatus="";
	      var array=[];  
	      var linen=0;
	      var swords = $(".select-in-passage").text().split(". "); 
	      var index=$('#index').val();
	      if($('#_mark').hasClass("active"))
		  { 
		   flagStatus="FLAG";
		  }
	     else
		  { 
		  flagStatus="UNFLAG";
		  }  
	      for(i=0;i< (swords.length-1); i++){ 
			    if(swords[i]==$(this).text()){ 
			    	 lineno = i;  
			    	 if($('#previousSelection').val()!=''||$('#previousSelection').val()!='undefined')
			    		 {
			    		 $(".textPart").removeClass("select-in-paragraph"); 
			    		 }
			    	 $('#previousSelection').val((lineno+1)); 
			    	 $('#selectedText').val($(this).text());
			    	 $(this).addClass("select-in-paragraph"); 
			    	 var ans=JSON.parse($('#updatedAnswers').val());  
					 for(var i=0;i<ans.length;i++){
						 if(ans[i].Question_index==index){ 
							 array.push((lineno+1));
							 ans[i].UserAnswer=array;  
							 ans[i].IsFlagged=flagStatus;  
							 ans[i].Reading_comprehension_text=$(this).text();  
						 }
					 }   
					 $('#updatedAnswers').val(JSON.stringify(ans));   
			    }  
	      	}   
	  });  
     </script>                                        
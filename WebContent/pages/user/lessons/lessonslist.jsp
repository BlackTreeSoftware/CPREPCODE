<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="assets/js/select2-3.4.8/select2.css" rel="stylesheet" />
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
<link rel="stylesheet" href="assets/js/dropdown/style.css"
	type="text/css">
<link rel="stylesheet" href="assets/js/notes/css/notes-style.css"
	type="text/css" />
<link href="assets/plug-ins/toastr-master/toastr.min.css"
	rel="stylesheet" type="text/css" />
<!-- Select 2 CSS -->
<link rel="stylesheet" href="assets/css/custom.css" type="text/css" />
<link href="assets/js/select2-3.4.8/select2.css" rel="stylesheet" />
<!-- Select 2 CSS -->
<!-- Lesson Css  -->
<style type="text/css">
.lessonCss {
	margin-left: 15px;
	margin-right: 15px
}
</style>
<!-- Lesson Css -->
<script src="assets/plug-ins/toastr-master/toastr.min.js" type="text/javascript"></script>
<%-- <script type="text/javascript"  src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>  --%>
<%-- <script src="assets/js/timer/timernew.js" type="text/javascript"></script>
<script src="assets/js/libs/underscore-min.js"></script> --%>

<script type="text/javascript">
	$(document).ready(function() {
		
	//	alert(' data is ; '+'<s:property value="#request.lessionId"/>');
	//	alert(' page is : '+'<s:property value="#request.pagesIs"/>');
		
				
				
				$('#sectiontype').val('math');
				$('#notesdata').click(function() {
					
					filteringdata();
				});

				$('#bookmarksdata').click(function() {
					filteringdata();
				});

				$("#confidence").select2({
					placeholder : 'Select',
					data : [ {
						id : 'Select',
						text : 'Select'
					}, {
						id : 'LOW',
						text : 'Low'
					}, {
						id : 'MEDIUM',
						text : 'Medium'
					}, {
						id : 'HIGH',
						text : 'High'
					} ]
				});
				
				
				$.ajax({
					type : 'POST',
					url : 'LessonsList.action',
					dataType : 'json',
					success : succesdata,
					errror : formerror

				});
				

				$("#confidence").on("change",function(e) {
							var conf = $('#confidence').val();
							//alert("in conf vlau" + conf);

							if (conf == 'Select'
									&& (!$('#notesdata').is(":checked") && !$(
											'#bookmarksdata').is(":checked"))) {
								$('.math').html($('#mathdata').val());
								$('.verbal').html($('#verbaldata').val());
							} else {
								filteringdata();
							}
						});

				$("#e10").on("change",function(e) {
					lessondropdownchanges();
						});
				
				function lessondropdownchanges()
				{
					//alert("The--------------------- math data value is"+$('#mathdata').val());
					$('.math').html($('#mathdata').val());
					$('.verbal').html($('#verbaldata').val());
					var dropd = "id" + $("#e10").val();
					//alert("the e10 values are"+dropd);
					// var str = "id1parent";
					var res = dropd.substr(dropd.length - 3,
							dropd.length);
					//  alert("Res"+res);
					var se = $('#sectiontype').val();
					// alert("the values are-------------"+se);
					if (res == "ent") {
						//alert($('div.panels-body.'+se).find('li#'+dropd+'.mainmenu').html());
						var parentdata = $('div.panels-body.' + se)
								.find('li#' + dropd + '.mainmenu')
								.html();
						if(parentdata==undefined)
							{
							var dropd = "ids" + $("#e10").val();
					         	//	alert("helloo---------"+dropd);
							var parentdata1 =$('div.panels-body.' + se).find('li#' + dropd).parents('li.mainmenu').html();
							if(parentdata1==undefined)
								{
								var dropd = "idc" + $("#e10").val();
								var s2=$('div.panels-body.' + se).find('li#' + dropd).parents('li.mainmenu').attr('id');
								alert("The s2 values are------------------------"+s2);
								var parentdata2= "<ul id=\"mainul\"><li id=\""+s2+"\" class=\"mainmenu\">"+$('div.panels-body.' + se)
								.find('li#' + dropd).parents('li.mainmenu').html()+"</li></ul>";
								//alert(parentdata2+"scond with c-----------------");
								parentdata=parentdata2;
								}
							else
								{		
								//alert(parentdata1+"scond with s-----------------");
									var s2=$('div.panels-body.' + se).find('li#' + dropd).parents('li.mainmenu').attr('id');
							    //alert("The s2 values are------------------------"+s2);
								
								var parentdata1 ="<ul id=\"mainul\"><li id=\""+s2+"\" class=\"mainmenu\">"+ $('div.panels-body.' + se).find('li#' + dropd).parents('li.mainmenu').html()+"</li></ul>";
								parentdata=parentdata1;
								}
							}
						else
							{
							var s2=$('div.panels-body.' + se).find('li#' + dropd + '.mainmenu').attr('id');
						//alert("The s2 values are------------------------"+s2);
							var parentdata = "<ul id=\"mainul\"><li id=\""+s2+"\" class=\"mainmenu\">"+$('div.panels-body.' + se).find('li#' + dropd + '.mainmenu').html()+"</li></ul>";
							}						
						$('.' + se).html(parentdata);
					} else {
						//alert("the drop id value in else"+dropd);
						var s = $('div.panels-body.' + se).find(
								'.' + dropd).parents('li.mainmenu')
								.attr('class');
						//alert("The class details are--------------"+s);
						var s2 = $('li.' + s).find('.' + dropd).parents('li.mainmenu').attr('id');
						//var s1=$('li.' + s).find('.' + dropd).parents('li.mainmenu').html();
						var s1 ="<ul id=\"mainul\"><li id=\""+s2+"\" class=\"mainmenu\">"+$('li.' + s).find('.' + dropd).parents('li.mainmenu').html()+"</li></ul>";
					    // alert("the id values are"+s2);
					    // alert("the html data--------------------"+s1);
						$('.' + se).html(s1);
					}
				}
				
				
				/** get Selected Tab value**/
				$('#mathtabSelect').click(function() {
					$('#sectiontype').val("math");
					var s = $('#mathSamp').val();
					$("#e10").select2({
						data : JSON.parse(s),
						placeholder : "Please Select Lesson"

					});

					$('#section').val("QUANTITATIVE");
					$('#s2id_e10').removeAttr('style');
					$('#s2id_e10').css('width', '130px');

				});
				$('#verbaltabSelect').click(function() {
					$('#section').val("VERBAL");
					$('#sectiontype').val("verbal");
					var s = $('#Samp').val();
					$("#e10").select2({
						data : JSON.parse(s)
					});
					$('#s2id_e10').removeAttr('style');
					$('#s2id_e10').css('width', '130px');

				});	
				
				
				
				
			});

	function filteringdata() {
		//	alert("Hai filtering");
		//  $('.math').html($('mathSamp').val());
		var conf = $('#confidence').val();
		var lessons = $('#e10').val();
		//alert("the lessons values"+lessons);
		var s1, s2, s3, selected;
		//alert("The confidence level value" + conf);
		if (conf == "" || conf == 'Select') {
			//alert("in iffff"+conf);
			if ($('#notesdata').is(":checked")
					&& !$('#bookmarksdata').is(":checked")) {
					//alert("in if notes and no bookmarks");
				s1 = "LOW";
				s2 = "MEDIUM";
				s3 = "HIGH";
				selected = "NO";

				$('.YES.UNMARKED.' + selected).show();
				$('.YES.UNMARKED.' + s1).hide();
				$('.YES.UNMARKED.' + s2).hide();
				$('.YES.UNMARKED.' + s3).hide();

				$('.YES.MARKED.' + selected).hide();
				$('.YES.MARKED.' + s1).hide();
				$('.YES.MARKED.' + s2).hide();
				$('.YES.MARKED.' + s3).hide();

				$('.NONOTES.MARKED.' + selected).hide();
				$('.NONOTES.MARKED.' + s1).hide();
				$('.NONOTES.MARKED.' + s2).hide();
				$('.NONOTES.MARKED.' + s3).hide();

				$('.NONOTES.UNMARKED.' + selected).hide();
				$('.NONOTES.UNMARKED.' + s3).hide();
				$('.NONOTES.UNMARKED.' + s1).hide();
				$('.NONOTES.UNMARKED.' + s2).hide();

			} else if (!$('#notesdata').is(":checked")
					&& $('#bookmarksdata').is(":checked")) {
				//alert("in if notes check & no bookamrks ");

				s1 = "LOW";
				s2 = "MEDIUM";
				s3 = "HIGH";
				selected = "NO";

				$('.NONOTES.MARKED.' + selected).show();
				$('.NONOTES.MARKED.' + s1).show();
				$('.NONOTES.MARKED.' + s2).show();
				$('.NONOTES.MARKED.' + s3).show();

				$('.YES.UNMARKED.' + selected).hide();
				$('.YES.UNMARKED.' + s1).hide();
				$('.YES.UNMARKED.' + s2).hide();
				$('.YES.UNMARKED.' + s3).hide();

				$('.YES.MARKED.' + selected).hide();
				$('.YES.MARKED.' + s1).hide();
				$('.YES.MARKED.' + s2).hide();
				$('.YES.MARKED.' + s3).hide();

				$('.NONOTES.UNMARKED.' + selected).hide();
				$('.NONOTES.UNMARKED.' + s3).hide();
				$('.NONOTES.UNMARKED.' + s1).hide();
				$('.NONOTES.UNMARKED.' + s2).hide();

			}

			else if ($('#notesdata').is(":checked")
					&& $('#bookmarksdata').is(":checked")) {

				//alert("in if notes check & bookamrks check ");

				s1 = "LOW";
				s2 = "MEDIUM";
				s3 = "HIGH";
				selected = "NO";

				$('.YES.MARKED.' + selected).show();
				$('.YES.MARKED.' + s1).hide();
				$('.YES.MARKED.' + s2).hide();
				$('.YES.MARKED.' + s3).hide();

				$('.YES.UNMARKED.' + selected).hide();
				$('.YES.UNMARKED.' + s1).hide();
				$('.YES.UNMARKED.' + s2).hide();
				$('.YES.UNMARKED.' + s3).hide();

				$('.NONOTES.MARKED.' + selected).hide();
				$('.NONOTES.MARKED.' + s1).hide();
				$('.NONOTES.MARKED.' + s2).hide();
				$('.NONOTES.MARKED.' + s3).hide();

				$('.NONOTES.UNMARKED.' + selected).hide();
				$('.NONOTES.UNMARKED.' + s1).hide();
				$('.NONOTES.UNMARKED.' + s2).hide();
				$('.NONOTES.UNMARKED.' + s3).hide();
			}
			else if (!$('#notesdata').is(":checked")
					&& !$('#bookmarksdata').is(":checked")
					&& (conf == '' || conf == 'Select') && ((lessons == '')||(lessons != ''))) {
				//alert("helloooooooooooooooooo");
				$('.math').html($('#mathdata').val());
				$('.verbal').html($('#verbaldata').val());
				if(lessons!='')
					{
					$("#e10").trigger( "change" );
					}
			}
		}
		else {
		//alert("IN Else block");
			if ($('#notesdata').is(":checked")
					&& $('#bookmarksdata').is(":checked")) {
				//alert("notes check and bookmarks checked the confidence value is"+ conf);
				if (conf == 'LOW') {
					s1 = "MEDIUM";
					s2 = "HIGH";
					s3 = "NO";
					selected = "LOW";

				} else if (conf == 'MEDIUM') {
					s1 = "LOW";
					s2 = "HIGH";
					s3 = "NO";
					selected = "MEDIUM";

				} else if (conf == 'HIGH') {
					s1 = "LOW";
					s2 = "MEDIUM";
					s3 = "NO";
					selected = "HIGH";
				}

				$('.YES.MARKED.' + selected).show();
				$('.YES.MARKED.' + s1).hide();
				$('.YES.MARKED.' + s2).hide();
				$('.YES.MARKED.' + s3).hide();

				$('.YES.UNMARKED.' + selected).hide();
				$('.YES.UNMARKED.' + s1).hide();
				$('.YES.UNMARKED.' + s2).hide();
				$('.YES.UNMARKED.' + s3).hide();

				$('.NONOTES.MARKED.' + selected).hide();
				$('.NONOTES.MARKED.' + s1).hide();
				$('.NONOTES.MARKED.' + s2).hide();
				$('.NONOTES.MARKED.' + s3).hide();

				$('.NONOTES.UNMARKED.' + selected).hide();
				$('.NONOTES.UNMARKED.' + s1).hide();
				$('.NONOTES.UNMARKED.' + s2).hide();
				$('.NONOTES.UNMARKED.' + s3).hide();

			}

			else if ($('#notesdata').is(":checked")
					&& !$('#bookmarksdata').is(":checked")) {
			//alert("in notes check & no bookamrks " + conf);
				if (conf == 'LOW') {
					s1 = "MEDIUM";
					s2 = "HIGH";
					s3 = "NO";
					selected = "LOW";
				} else if (conf == 'MEDIUM') {
					s1 = "LOW";
					s2 = "HIGH";
					s3 = "NO";
					selected = "MEDIUM";

				} else if (conf == 'HIGH') {
					s1 = "LOW";
					s2 = "MEDIUM";
					s3 = "NO";
					selected = "HIGH";
				}
				$('.YES.UNMARKED.' + selected).show();
				$('.YES.UNMARKED.' + s1).hide();
				$('.YES.UNMARKED.' + s2).hide();
				$('.YES.UNMARKED.' + s3).hide();

				$('.YES.MARKED.' + selected).hide();
				$('.YES.MARKED.' + s1).hide();
				$('.YES.MARKED.' + s2).hide();
				$('.YES.MARKED.' + s3).hide();

				$('.NONOTES.MARKED.' + selected).hide();
				$('.NONOTES.MARKED.' + s1).hide();
				$('.NONOTES.MARKED.' + s2).hide();
				$('.NONOTES.MARKED.' + s3).hide();

				$('.NONOTES.UNMARKED.' + selected).hide();
				$('.NONOTES.UNMARKED.' + s3).hide();
				$('.NONOTES.UNMARKED.' + s1).hide();
				$('.NONOTES.UNMARKED.' + s2).hide();

			}

			else if (!$('#notesdata').is(":checked")
					&& $('#bookmarksdata').is(":checked")) {
			//	alert("hello in no notes and boomarks data---------");
				if (conf == 'LOW') {
					s1 = "MEDIUM";
					s2 = "HIGH";
					s3 = "NO";
					selected = "LOW";
				} else if (conf == 'MEDIUM') {
					s1 = "LOW";
					s2 = "HIGH";
					s3 = "NO";
					selected = "MEDIUM";

				} else if (conf == 'HIGH') {
					s1 = "LOW";
					s2 = "MEDIUM";
					s3 = "NO";
					selected = "HIGH";
				}

				$('.NONOTES.MARKED.' + selected).show();
				$('.NONOTES.MARKED.' + s1).hide();
				$('.NONOTES.MARKED.' + s2).hide();
				$('.NONOTES.MARKED.' + s3).hide();

				$('.YES.UNMARKED.' + selected).hide();
				$('.YES.UNMARKED.' + s1).hide();
				$('.YES.UNMARKED.' + s2).hide();
				$('.YES.UNMARKED.' + s3).hide();

				$('.YES.MARKED.' + selected).hide();
				$('.YES.MARKED.' + s1).hide();
				$('.YES.MARKED.' + s2).hide();
				$('.YES.MARKED.' + s3).hide();

				$('.NONOTES.UNMARKED.' + selected).hide();
				$('.NONOTES.UNMARKED.' + s3).hide();
				$('.NONOTES.UNMARKED.' + s1).hide();
				$('.NONOTES.UNMARKED.' + s2).hide();
			} else if (!$('#notesdata').is(":checked")
					&& !$('#bookmarksdata').is(":checked")) {
				//alert("in confidence levele----------------habin");
				if (conf == 'LOW') {
					s1 = "MEDIUM";
					s2 = "HIGH";
					s3 = "NO";
					selected = "LOW";
				} else if (conf == 'MEDIUM') {
					s1 = "LOW";
					s2 = "HIGH";
					s3 = "NO";
					selected = "MEDIUM";

				} else if (conf == 'HIGH') {
					s1 = "LOW";
					s2 = "MEDIUM";
					s3 = "NO";
					selected = "HIGH";

				}

				$('.NONOTES.UNMARKED.' + selected).show();
				$('.NONOTES.UNMARKED.' + s3).hide();
				$('.NONOTES.UNMARKED.' + s1).hide();
				$('.NONOTES.UNMARKED.' + s2).hide();

				$('.NONOTES.MARKED.' + selected).hide();
				$('.NONOTES.MARKED.' + s3).hide();
				$('.NONOTES.MARKED.' + s1).hide();
				$('.NONOTES.MARKED.' + s2).hide();

				$('.YES.UNMARKED.' + selected).hide();
				$('.YES.UNMARKED.' + s1).hide();
				$('.YES.UNMARKED.' + s2).hide();
				$('.YES.UNMARKED.' + s3).hide();

				$('.YES.MARKED.' + selected).hide();
				$('.YES.MARKED.' + s1).hide();
				$('.YES.MARKED.' + s2).hide();
				$('.YES.MARKED.' + s3).hide();

			} else if (!$('#notesdata').is(":checked")
					&& !$('#bookmarksdata').is(":checked")
					&& (conf == '' || conf == 'Select') && ((lessons == '')||(lessons != ''))) {
			//	alert("in all deselected value");
				$('.math').html($('#mathdata').val());
				$('.verbal').html($('#verbaldata').val());
				if(lessons!='')
				{
				$("#e10").trigger( "change" );
				}
			}
		}

	}
	function succesdata(data) {
		
	    $('.math').html(data.Quantitative.QuantitativeHierarchy);
		$('#mathdata').val(data.Quantitative.QuantitativeHierarchy);
		$('#verbaldata').val(data.Verbal.VerbalHierarchy);
		$('.verbal').html(data.Verbal.VerbalHierarchy);
		var s = data.Quantitative.QuantitativeComboBox;
		$('#mathSamp').val(data.Quantitative.QuantitativeComboBox);
		// $('.math').html(data.Quantitative.QuantitativeComboBox);
		$('#Samp').val(data.Verbal.VerbalComboBox);
		$("#e10").select2({
			data : JSON.parse(s)
		});   

		/*** KEYSETS FOR INDEXES ***/
		   var lastIndex = 0;
		var json = JSON.stringify(data.Quantitative.QuantitativekeySet);
		$('#keySet').val(json);
		var verbaljson = JSON.stringify(data.Verbal.VerbalkeySet);
		$('#verbalkeySet').val(verbaljson);
		$.each(JSON.parse(json), function(key, val) {
			lastIndex = key;
		}); 
		$('#lastIndex').val(lastIndex);
		lastIndex = 0;
		$.each(JSON.parse(verbaljson), function(key, val) {
			lastIndex = key;
		});

		$('#verballastIndex').val(lastIndex); 
		
		if('<s:property value="#request.pagesIs"/>'=='page1')
		{
			lessionId('<s:property value="#request.lessionId"/>');
		}
	
		
		
	}
	function formerror() {
		alert("the data is");
	}

	function lessionId(sublessonid) {
           //  alert("_________________________________________________________________________");
		var section = $('#section').val();
		var keySet;
		if (section == 'QUANTITATIVE')
			keySet = $('#keySet').val();
		else
			keySet = $('#verbalkeySet').val();
		$.each(JSON.parse(keySet), function(key, val) {
			if (val == sublessonid) {
				$('#index').attr('value', key);
			}
		});

		$('#sublesson_id').attr('value', sublessonid);
		$.ajax({
			type : 'POST',
			url : 'lessonDetails.action',
			data : 'lessonsmasterBO.sublesson_id=' + sublessonid,
			success : succesdata,
			errror : formerror

		});
		function succesdata(data) {
			$("#body1").addClass("lessonCss");
			$("#body1").html(data);

			var index = $('#index').val();
			var nextIndex = parseInt(index);
			var section = $('#section').val();

			if (section == 'QUANTITATIVE') {
				lastIndex = $('#lastIndex').val();
			} else {
				lastIndex = $('#verballastIndex').val();
			}
			if (nextIndex == 1)
				$('#previous').hide();
			//alert('Oops.. Sorry.. You are in the first Lesson');
			else if ((nextIndex == parseInt(lastIndex)))
				$('#next').hide();
			//alert('Oops.. Sorry.. You are in the last Lesson'); 
			else {
				$('#previous').show();
				$('#next').show();
			}

		}
		function formerror() {
			alert("error");

		}
		 

	}
</script>

<style>
.toppbar {
	padding-top: 5px;
}

.border-left {
	border-left: #333 1px solid;
}

.clear {
	clear: both;
}

@media screen and (max-width:480px) {
	.panels-body ul {
		padding-left: -40px;
		margin-left: -20px;
		font-size: 14px
	}
}

@media screen and (max-width:320px) {
	.panels-body ul {
		padding-left: -40px;
		margin-left: -20px;
		font-size: 12px
	}
}
</style>


<!-- CSS AND SCRIPTS FOR LESSON VIEW -->
<style>
.amPmCheckbox input[type="checkbox"] {
	display: none;
}

.amPmCheckbox input[type="checkbox"]+label {
	background: url(assets/images/icons/bookmarkicon-hover32.png) no-repeat;
	height: 32px;
	width: 32px;
	padding: px;
	cursor: pointer;
}

.amPmCheckbox input[type="checkbox"]:checked+label {
	background: url(assets/images/icons/bookmarkicon32.png) no-repeat;
	height: 32px;
	width: 32px;
	cursor: pointer;
}

.book {
	margin-top: -14px;
}

.book-xs {
	margin-top: -2px;
}

a.buttonfornotes {
	opacity: 1;
}

a.buttonfornotes:hover {
	opacity: 1;
}

.tx-xs {
	position: fixed;
	top: 80px;
	width: 100%;
}

.txtp {
	font-family: Verdana, Arial, sans-serif;
	font-size: 14px;
}

a.bug {
	color: #444;
	font-size: 22px;
}

a.bug:hover {
	color: #999;
}

.head-bar-xs {
	background-color: #f2f4f8;
	margin-top: -15px;
	position: fixed;
	height: 60px;
	z-index: 9999
}

.note-xs {
	margin-top: 60px;
}

.lesson-content p {
	font-family: Verdana, Arial, sans-serif;
	font-size: 15px;
	padding-left: 20px;
	padding-right: 20px;
}

.lesson-content h1 {
	font-size: 28px;
	margin-top: 0px;
	margin-bottom: 15px;
	font-family: sans-serif;
	padding-left: 20px;
	padding-right: 20px;
}

.lesson-content h2 {
	font-size: 24px;
	margin-top: 0px;
	margin-bottom: 10px;
	font-family: sans-serif;
	padding-left: 20px;
	padding-right: 20px;
}

.lesson-content h3 {
	font-size: 20px;
	margin-top: 0px;
	margin-bottom: 10px;
	font-family: sans-serif;
	padding-left: 20px;
	padding-right: 20px;
}

.note_style {
	z-index: 9999;
	position: absolute;
	right: 0px
}

.page_style {
	padding-right: 800px;
}

.txt-main-notes {
	width: 100%;
}

.col-sm-95 {
	width: 77%
}

.col-sm-35 {
	width: 22%
}

.col-sm-95-fuller {
	width: 96%
}

.col-sm-35-fuller {
	width: 3.5%
}

@media ( min-width :1200px) {
	/*#sideTab {width: 78%;}
								.sider {width:22%; */
	/*.sideTabfull{ width:97%;}
								.sideTabnill{ width:75%; margin-right:20px;}
								
								.siderfull {width:21%;margin-right:20px; }
								.sidernill {width:1%;  }*/
	.btn-chevron {
		font-size: 180%;
		padding-right: 8px;
		margin-top: 15px;
	}
}

@media ( max-width :1200px) {
	.btn-chevron {
		font-size: 150%;
		margin-left: 0px;
		margin-top: 15px;
	}
	.col-sm-35-fuller {
		width: 4%
	}
	.btn-default {
		font-size: 12px
	}
	.btn-test {
		font-size: 12px;
	}
}

@media ( max-width :1100px) {
	.btn-chevron {
		font-size: 150%;
		margin-left: -5px;
		margin-top: 15px;
	}
	.col-sm-35-fuller {
		width: 4%
	}
	.btn-default {
		font-size: 11px
	}
	.btn-test {
		font-size: 11px;
	}
}

@media ( max-width :900px) {
	.btn-test {
		font-size: 9px;
	}
}

/*.sideTabfull{ width:97%;}
	.sideTabnill{ width:75%; margin-right:20px;}
	
	.siderfull {width:21%;margin-right:20px; }
	.sidernill {width:1%;  }*/
@media screen and (max-width:800px) {
	.headr-padding {
		margin-top: 40px;
	}
	.lesson-content p {
		font-family: Verdana, Arial, sans-serif;
		font-size: 15px;
		padding-left: 5px;
		padding-right: 5px;
	}
	.lesson-content h1,.lesson-content h2,.lesson-content h3 {
		padding-left: 0px;
		padding-right: 0px;
	}
	.link-pading-xs-lft {
		padding-left: 15px;
	}
	.link-pading-xs-rht {
		padding-right: 15px;
	}
	.sider {
		right: -6000px;
	}
	.col-sm-95 {
		width: 100%;
	}
	.btn-chevron {
		margin-left: 0px;
	}
	*
	/
	
	
	
										


}

@media ( min-width :800px) {
	.link-pading-xs-lft {
		padding-left: 35px;
	}
	.link-pading-xs-rht {
		padding-right: 35px;
	}
	.head-bar-xs {
		display: none;
	}
}

@media ( max-width :392px) {
	/*.askdoubt-hide{display:none;}*/
	.askdoubt-hide {
		display: none
	}
	.btn-group {
		margin-left: -45px;
	}
	.book-xs {
		margin-top: -50px;
		margin-right: -34px;
	}
	#note-icon {
		margin-left: -40px;
	}
	.lesson-content p {
		font-family: Verdana, Arial, sans-serif;
		font-size: 12px;
	}
}

@media ( max-width :320px) {
	.btn-group {
		margin-left: -15px;
	}
	.book-xs {
		margin-top: -50px;
		margin-right: -25px;
	}
	.btn-test {
		font-size: 9px;
	}
}
</style>


<script>
	$(document).ready(function() {
		startCount();
		toastr.options = {
			"closeButton" : true,
			"debug" : false,
			"positionClass" : "toast-top-right",
			"onclick" : null,
			"showDuration" : "300",
			"hideDuration" : "1000",
			"timeOut" : "5000",
			"extendedTimeOut" : "1000",
			"showEasing" : "swing",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
		};

	});
</script>
<script type="text/javascript">
	function saveConfidence(level) {
		var formData = $('#confidenceFrorm').serialize();
		// alert('in confidence list');
		$.ajax({
					type : 'POST',
					url : 'add-Lesson-ConfidenceLevel.action?lessonsmasterBO.lesson_confidence='
							+ level,
					data : formData,
					datatype : 'json',
					success : successInfo,
					error : errorInfo
				});

		function successInfo(result) {
			toastr.success("Confidence Level Selected Succesfully");
			
			
		}
		function errorInfo(result) {
			toastr.error("Confidence Level Selected Failed");
			lessionId(sublessonid);
		}

	}

  function Refresh() {
  //   alert("Refresh is calling @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		var sub_id = $('#sublesson_id').val();
		$.ajax({
			type : 'POST',
			url : 'lessonDetails.action',
			data : 'lessonsmasterBO.sublesson_id=' + sub_id,
			success : succesdata,
			errror : formerror

		});
		function succesdata(data) {
			$("#body1").addClass("lessonCss");

			$("#body1").html(data);
		}
		function formerror() {
			alert("error");

		}
	} 
</script>

<script type="text/javascript">
	function saveConfidence(level) {
		var formData = $('#confidenceFrorm').serialize();
		// alert('in confidence list');

		$.ajax({
					type : 'POST',
					url : 'add-Lesson-ConfidenceLevel.action?lessonsmasterBO.lesson_confidence='
							+ level,
					data : formData,
					datatype : 'json',
					success : successInfo,
					error : errorInfo
				});

		function successInfo(result) {
			
			Refresh();
			toastr.success("Confidence Level Selected Succesfully");
		}
		function errorInfo(result) {
			Refresh();
		
			toastr.error("Confidence Level Selected Failed");
		}

	}

	/* function Refresh() {
		
	  alert("Refresh function is calling@@@@@@@@@@@@@@@@@@@@@@@@");

		var sub_id = $('#sublesson_id').val();
		$.ajax({
			type : 'POST',
			url : 'lessonDetails.action',
			data : 'lessonsmasterBO.sublesson_id=' + sub_id,
			success : succesdata,
			errror : formerror

		});
	} */

	function saveNotes() {

		var note = $("#lession_note").val();
		$.ajax({
			tye : 'POST',
			url : 'addNotes.action',
			data : 'lesson_note_name=' + note,
			success : successInfo1,
			error : errorInfo1
		});

	}
	function successInfo1(result) {
		toastr.success("Your Note is Saved!");
		Refresh();
	}
	function errorInfo1(result) {
		toastr.error(" Failed");
	}
</script>


<script type="text/javascript">
	function saveNotesPhone() {
		var note = $("#lession_note_phn").val();
		$.ajax({
			tye : 'POST',
			url : 'addNotes.action',
			data : 'lesson_note_name=' + note,
			success : successInfo1,
			error : errorInfo1
		});

	}
	function successInfo1(result) {
		toastr.success("Your Note is Saved!");
		Refresh();
	}
	function errorInfo1(result) {
		toastr.error(" Failed");
	}
</script>

<script type="text/javascript">
	function saveBookmark(bookmark) {
		//alert(''+bookmark);
		$.ajax({
			tye : 'POST',
			url : 'addBookMarks.action',
			data : 'lesson_bookmark=' + bookmark,
			success : function(data) {
				toastr.success(bookmark);
				Refresh();
			},
			error : function(error) {

			}

		});

	}

	/* Previous and Next Navigations*/
	function navigationControl(control) {

		var lastIndex;
		var section = $('#section').val();
		var keySet;
		if (section == 'QUANTITATIVE') {
			keySet = $('#keySet').val();
			lastIndex = $('#lastIndex').val();
		} else {
			lastIndex = $('#verballastIndex').val();
			keySet = $('#verbalkeySet').val();
		}

		var index = $('#index').val();
		var sublessonid;
		var nextIndex = 0;

		var last = parseInt(lastIndex) + 1;
		if (control == 'PREVIOUS')
			nextIndex = parseInt(index) - 1;
		else if (control == 'NEXT')
			nextIndex = parseInt(index) + 1;

		if (nextIndex == 1) {
			$('#previous').hide();
			alert('You are in first lesson');
		} else if ((nextIndex == parseInt(lastIndex))) {
			$('#next').hide();
			alert('You are in last lesson');
		} else {
			$('#previous').show();
			$('#next').show();
		}

		$.each(JSON.parse(keySet), function(key, val) {
			if (key == nextIndex) {
				sublessonid = val;
				getNavigatedLessons(sublessonid, nextIndex);
			}

		});

	}

	function getNavigatedLessons(sublessonid, nextIndex) {
		$('#index').val(nextIndex);
		$('#sublesson_id').val(sublessonid);
		$.ajax({
			type : 'POST',
			url : 'lessonDetails.action',
			data : 'lessonsmasterBO.sublesson_id=' + sublessonid,
			success : succesdata,
			errror : formerror

		});
		function succesdata(data) {

			$("#body1").addClass("lessonCss");
			$("#body1").html(data);
			var index = $('#index').val();
			var nextIndex = parseInt(index);
			var section = $('#section').val();
			if (section == 'QUANTITATIVE') {
				lastIndex = $('#lastIndex').val();
			} else {
				lastIndex = $('#verballastIndex').val();
			}
			if (nextIndex == 1) {
				$('#previous').hide();
				//alert('first');
			} else if ((nextIndex == parseInt(lastIndex)))
				$('#next').hide();
			else {
				$('#previous').show();
				$('#next').show();
			}

		}
		function formerror() {
			alert("error");

		}

	}
</script>
<script type="text/javascript">
	function sendMail() {

		var formData = $('#doubt').serialize();
		//alert(formData);
		$.ajax({
			type : 'POST',
			url : 'lessonaction.action',
			dataType : 'json',
			data : formData,
			success : function(data, textStatus, jqXHR) {
				if (data.message == "Mail Sent Successfully") {
					toastr.success("Mail Sent Successfully");
					Refresh();
				} else if (data.message == "Doesnt Have Mentor") {
					toastr.error("Doesnt Have Mentor");
				}
			},
			error : function actionFailed(data, textStatus, errorThrown) {
				toastr.error("Error");
			}

		});
	}
</script>

<script type="text/javascript">
	function saveConfidence1(level) {
		var formData = $('#confidenceFrorm1').serialize();
		$.ajax({
					type : 'POST',
					url : 'add-Lesson-ConfidenceLevel.action?lessonsmasterBO.lesson_confidence='
							+ level,
					data : formData,
					datatype : 'json',
					success : successInfo,
					error : errorInfo
				});

		function successInfo(result) {
			toastr.success("Confidence Level Selected Succesfully");
			Refresh();
		}
		function errorInfo(result) {
			toastr.error("Confidence Level Selected Failed");
		}
	}
</script>

<script type="text/javascript">
	function lessonsTaken(timer) {
		var formData = $('#myform').serialize();
		$.ajax({
			type : 'POST',
			url : 'lessonsTaken.action?lessonsmasterBO.time_spent=' + timer,
			data : formData,
			datatype : 'json',
			success : successInfo,
			error : errorInfo
		});
		function successInfo(result) { 
			var obj=JSON.parse(result);  
			var lessonid=obj.result;
			//str = lessonid.replace("success", "");
			//alert('Haiiii:'+obj.result.replace("success", ""));
			//alert("the s value is-------------");
			var sectionid;
			var s=$('#sectiontype').val();
			//alert("the s value is-------------"+s);
			if(s=='math')
				sectionid="1";
			else
				sectionid="2";
			//alert("the s value is-------------"+sectionid);
			$('#sublesson_id').val(obj.result.replace("success", "")); 
			//alert($('#sublesson_id').val()+"section id is"+sectionid);
		   // $('#myform').attr('action','Get-Practice-Session-Questions.action?sublessonid='+$('#sublesson_id').val());
		      
		    $('#myform').attr('action','Get-Practice-Session-Questions.action?sublessonid='+$('#sublesson_id').val()+'&sectionid='+sectionid);
		   // alert("hellooo");
			$('#myform').submit();  
			toastr.succes("Done !!!"); 
			Refresh();
		}

		function errorInfo(rs) {
			toastr.error("Failed !!");
		}
	}
	function getTime() {
		var timer = document.getElementById("realtime").innerHTML;
		lessonsTaken(timer);
		document.getElementById("realtime").innerHTML = "00:00:00";
	}
</script>
<script type="text/javascript">
	/*** GET LAST LESSON ****/

	function lastlesson(lastlessonid) {

		$('#sublesson_id').val(lastlessonid);

		$.ajax({
			type : 'POST',
			url : 'lessonDetails.action',
			data : 'lessonsmasterBO.sublesson_id=' + lastlessonid,
			success : succesdata,
			errror : formerror

		});
		function succesdata(data) {

			$("#body1").addClass("lessonCss");
			$("#body1").html(data);
		}
		function formerror() {
			alert("error");

		}

	}
</script>


</head>
<body>

	<input type="hidden" id="section" name="section" value="QUANTITATIVE">
	<input type="hidden" id="keySet" name="keySet" value="">
	<input type="hidden" id="verbalkeySet" name="verbalkeySet" value="">
	<input type="hidden" id="index" value="">
	<input type="hidden" id="lastIndex" value="">
	<input type="hidden" id="verballastIndex" value="">
	<input type="hidden" id="sublesson_id"
		name="lessonsmasterBO.sublesson_id" value="0" />
	<section id="content"> <section class="vbox"> <section
		class="scrollable wrapper"> <s:form>
		<s:hidden id="Samp"></s:hidden>
		<s:hidden id="mathSamp"></s:hidden>
		<s:hidden id="verbaldata"></s:hidden>
		<s:hidden id="mathdata"></s:hidden>
		<s:hidden id="sectiontype"></s:hidden>
	</s:form>
	<div class="row" id="body1">
		<div class="col-sm-12">
			<div class="panel panel-white">
				 <div class="panel-heading text-center">
					<span class="panel-title">Lessons </span>
				</div> 
				<!--panel heading close-->

				<div class="panel-body">
					
					<div class="row">

						<div class="col-lg-6">

							<div class="alerts alerts-lesson-read">
								<h2>You are back, great!</h2>
								<p>
									Yesterday you left your reading session at: &nbsp; &nbsp;

									<s:set var="lastlesson" value="%{#session.last_lessonid}" />

									<s:if test="#lastlesson==0">

										<a href="#" class="btn btn-sm btn-success">Start reading
											lessons</a> &nbsp;
								</s:if>
									<s:else>
										<a
											href="javascript:lastlesson('<s:property value="#session.last_lessonid" />');"
											class="btn btn-sm btn-success"><s:property
												value="#session.last_lesson" /></a> &nbsp;
									</s:else>

								</p>
							</div>
						</div>
						<div class="col-lg-6 col-md-12">
							<div class="row">

								<div class="col-lg-6 ">
								<div class="row">
									<div class="col-md-3 col-lg-6  " style="line-height: 2.6">
										Search Lessons</div>
									<div class="col-lg-6 col-md-4" id="data_array">
										<input type="hidden" id="e10" class="padd"
											style="width: 120px">

									</div>
									</div>
								</div>
								<div class="col-lg-6 ">
									<div class="row">
										
											<div class="col-lg-6 col-md-3 " style="line-height: 2.6">
												Confidence Level</div>
											<div class="col-lg-4 col-md-4" id="data_array">
												<input type="hidden" id="confidence" class="padd"
													style="width: 120px" />
											</div>
										


									</div>
									<!-- Internal row 1 -->

									<hr>

									<div class="row">

										<div class="col-lg-12">

											<div class="col-sm-10 col-sm-offset-2">

												<div class="checkbox m-l m-r-xs"
													style="margin-top: 2px; float: left">

													<label class="i-checks"> <input type="checkbox"
														id="notesdata"><i></i> Notes
													</label>

												</div>

												<div class="checkbox m-l m-r-xs"
													style="margin-top: 2px; float: left">

													<label class="i-checks"> <input type="checkbox"
														id="bookmarksdata"><i></i>Bookmark
													</label>

												</div>
											</div>

										</div>


									</div>
									<!-- Internal row 2 -->


								</div>


							</div>
							<!---second col block for sort type-->

							<!--<div class="col-lg-2">
              <div class="checkbox m-l m-r-xs" style="margin-top:2px">
                      <label class="i-checks">
                        <input type="checkbox"><i></i> Bookmark 
                      </label>
              </div></div>-->

						</div>

					</div>
					<!--row close for upper menus-->



				</div>

				<hr>

				<div class="row" style="padding-left: 0px;">
					<div class="col-sm-12 ">

						<section class="panel panel-default" style="margin-bottom:0px">
						<header class="panel-heading bg-light">

						<ul class="nav nav-tabs nav-justified">
							<li class="active"><a href="#math" id="mathtabSelect"
								data-toggle="tab">Math</a></li>
							<li class=""><a href="#verbal" id="verbaltabSelect"
								data-toggle="tab">Verbal</a></li>
						</ul>
						</header>
						<div class="panels-body" style="cursor: pointer;">
							<div class="tab-content">
								<div class="tab-pane active" id="math">


                              
									<div class="panels-body math" id="mathhirarchy" >
							 	 
 							
									</div>
								</div>
								<!-------MATH TAB END----->
								<div class="tab-pane" id="verbal">

									<div class="panels-body verbal"></div>

								</div>
							</div>
						</div>
						</section>


						<!--Nestable table close-->

					</div>
				</div>
				<!--row close for upper menus-->


			</div>
			<!--panel heading close-->

		</div>
		<!--Panel close-->

	</div>
	<!--Main row close--> </section> </section> <a href="#" class="hide nav-off-screen-block"
		data-toggle="class:nav-off-screen" data-target="#nav"></a> </section>
	<script src="assets/js/dropdown/MultiNestedList.js"></script>
	<!--select 2 plugin-->
	<script src="assets/js/select2-3.4.8/select2.js"></script>


	<script src="assets/js/libs/backbone-min.js"></script>
	<script src="assets/js/libs/backbone.localStorage-min.js"></script>
	<script src="assets/js/libs/moment.min.js"></script>
	<!-- Notes -->
	<script src="assets/js/apps/notes.js"></script>
	<script src="assets/js/notes/js/notes-script.js"></script>
	<script src="assets/js/boot-box/bootbox.min.js"></script>
	<script>
		$(document).on("click", ".alert", function(e) {
			bootbox.alert("Hello world!", function() {
				console.log("alert Callback");
			});

		});
	</script>
	<script>
		$(window).resize(function() {

			if ($(window).width() <= 320) {

				/*$('.book-xs').css('margin-right','-90px');*/
				/* $('.askdoubt').removeClass('col-xs-3').addClass('col-xs-1');*/

			}

		});
		$(document)
				.ready(
						function(e) {

							$('.note-xs').hide();

							$('.btn-sider')
									.click(
											function(e) {

												$(
														'#confidencearea,#notesarea,#notes_section,.book,#ask')
														.toggle();

												if ($('#tog')
														.hasClass(
																'fa-chevron-circle-right')) {
													$('#tog')
															.removeClass(
																	'fa-chevron-circle-right')
															.addClass(
																	'fa-chevron-circle-left');
												} else {
													$('#tog')
															.removeClass(
																	'fa-chevron-circle-left')
															.addClass(
																	'fa-chevron-circle-right');

												}

												$('.col-sm-95').toggleClass(
														'col-sm-95-fuller');
												$('.col-sm-35').toggleClass(
														'col-sm-35-fuller');

												$('.sider').toggleClass(
														'no-sider');

											});

							$('#show_notes').hide();
							$(".buttonfornotes").click(
									function() {

										// Set the effect type
										var effect = 'slide';

										// Set the options for the effect type chosen
										var options = {
											direction : 'up'
										};

										// Set the duration (default: 400 milliseconds)
										var duration = 400;

										$('#show_notes').toggle(effect,
												options, duration);
									});

							$("#notes_section").click(function() {

								// Set the effect type
								//var effect = 'slide';

								// Set the options for the effect type chosen
								var options = {
									direction : 'left'
								};

								// Set the duration (default: 400 milliseconds)
								var duration = 400;

								$(this).toggle(effect, options, duration);
							});

							$("#send")
									.click(
											function() {

												////alert("hai ");

												var formData = $('#doubt')
														.serialize();
												////alert(formData);
												$
														.ajax({
															type : 'POST',
															url : 'lessonaction.action',
															dataType : 'json',
															data : formData,
															success : function(
																	data,
																	textStatus,
																	jqXHR) {
																////alert("Mail Sent Staus \n"+data.mailSentStatus);
																////alert(textStatus);
																////alert(data.data);
																if (data.message == "Mail Sent Successfully") {
																	toastr
																			.success("Mail Sent Successfully");
																} else if (data.message == "Doesnt Have Mentor") {

																	toastr
																			.error("Doesnt Have Mentor");
																}
																clean();

															},
															error : function actionFailed(
																	data,
																	textStatus,
																	errorThrown) {
																toastr
																		.error("Error");
															}

														});

											});

							function clean() {
								 alert("clean");

								$("#title").val("");
								$("#desc").val("");
							}


							
							
						});
	</script>
<script>
 
	 function accordianCall(this1)
	{ 
		var ul_hide="#"+this1+">ul";
		// alert("Parent Id is : "+ul_hide);
		//$(ul_hide).css("display","none");
		var down='#down'+this1;
		//$(down).css('display','none');
		var right='#right'+this1;
		//$(right).css('display','inline-block');
	   if($(right).css('display') != 'none')
		{
		   // alert("calling RIght");
		    
		    $(ul_hide).removeAttr("display");
			$(down).css('display','inline-block');
			$(right).css('display','none');		
			$(ul_hide).css('display','block');
			var id="'#"+this1+">ul'"; 
			//alert(id);
			$(id).toggle(3000);
			//$(id).slideToggle('slow');
			 
		} 
	   
	   else if($(down).css('display') != 'none')
		{
		  // alert("Calling down");
			$(down).css('display','none');
			$(right).css('display','inline-block');
			$(ul_hide).css('display','none');
			$(ul_hide).css('overflow','hidden');
			var id="'#"+this1+">ul'"; 			 
			$(id).toggle(3000);
			//$(id).slideToggle('slow');
		} 
	    
		 
	} 

</script>


</body>
</html>
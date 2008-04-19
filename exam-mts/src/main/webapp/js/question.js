function sbt(){
		var editor=FCKeditorAPI.GetInstance("content");   
		var content=FCKeditorAPI.GetInstance("content").GetXHTML(true);//editor.EditorDocument.body.innerHTML; 
		var matchingOptions = QuestionForm.matchingOptions;
		var matchingAnswers = QuestionFrom.matchingAnswers;
		var optionContent = QuestionForm.optionContent;
		var answer = QuestionForm.answer;
		var t = false;
		var rtflag = true;
		
		if(content== ""||content==null){
			alert("请输入题目内容!");
			rtflag=false;
			return false;
		}	
			
		if(optionContent!=null){
			for(var i = 0; i < optionContent.length; i ++){
				if(optionContent[i].value == "" || optionContent[i].value == null){
					alert("请填写完整选项信息!");
					optionContent[i].focus();
					rtflag=false;
					return false;
				}
				if(answer[i].checked == true){
					t = true;
				}
			}
		}
		var answers = QuestionForm.answers;
		if(answers!=null){

			if(answers[0].type==radio){
				for(var l = 0; l < answers.length; l ++){
					if(answers[l].checked == true){
						t = true;
					}
				}
				
			}
			else{
				t = true;
			}
			
		}
		else{
			t = true;
		}
		if(!t){
			alert("请至少选择一个答案!");
			rtflag=false;
			return false;
		}
		
		if(matchingOptions!=null){
			for(var j = 0; j < matchingOptions.length; j ++){
				if(matchingOptions[j].value == "" || matchingOptions[j].value == null){
					alert("请填写完整匹配选项信息!");
					matchingOptions[j].focus();
					rtflag=false;
					return false;
				}
			}
		}
		
		if(matchingAnswers!=null){
			for(var k = 0; k < matchingAnswers.length; k ++){
				if(matchingAnswers[k].value == "" || matchingAnswers[k].value == null){
					alert("请填写完整匹配答案信息!");
					matchingAnswers[k].focus();
					rtflag=false;
					return false;
				}
			}
		}
		
	    return rtflag;
	}
	function removeOption(){
		if(document.getElementById("options_j").innerHTML != ""){
			document.getElementById("options_j").innerHTML = "";
		}else if(document.getElementById("options_i").innerHTML != ""){
			document.getElementById("options_i").innerHTML = "";
		}else if(document.getElementById("options_h").innerHTML != ""){
			document.getElementById("options_h").innerHTML = "";
		}else if(document.getElementById("options_g").innerHTML != ""){
			document.getElementById("options_g").innerHTML = "";
		}else if(document.getElementById("options_f").innerHTML != ""){
			document.getElementById("options_f").innerHTML = "";
		}else if(document.getElementById("options_e").innerHTML != ""){
			document.getElementById("options_e").innerHTML = "";
		}else if(document.getElementById("options_d").innerHTML != ""){
			document.getElementById("options_d").innerHTML = "";
		}else if(document.getElementById("options_c").innerHTML != ""){
			document.getElementById("options_c").innerHTML = "";
		}
		else{
			alert("至少必须有2个选项!");
			return;
		}
	}
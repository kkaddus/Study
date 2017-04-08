var dialog = new ax5.ui.dialog(); 
var firstGrid = new ax5.ui.grid();
var documentWidth = $(window).width();
var documentHeight = $(window).height();
var valCheck = true;
var emailRegex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;  

function fnModalOpen(modalType, theme, strWidth, strHeight, strzIndex, title, url, paramData){
	var modal = new ax5.ui.modal();
	var mask = new ax5.ui.mask();
	var toast = new ax5.ui.toast();
	modal.open({
	    width: parseInt(strWidth),
	    height: parseInt(strHeight),
        zIndex: parseInt(strzIndex),
        theme: theme,
        header: {
            title: "<i class='fa fa-arrows' aria-hidden='true'></i> " + title,
            btns: {
                close: {
                    label: '<i class="fa fa-times-circle" aria-hidden="true"></i>', onClick: function () {
                    	modal.close();
                    }
                }
            }
        },
	    onStateChanged: function(){
	        if (this.state === "open") {
	            mask.open({
		        	theme: theme,
	            	zIndex: parseInt(strzIndex)-1
	            });
	        } else if (this.state === "close") {
	            mask.close();
	        }
	    },
	    onResize: function(){
	         console.log(this);
	    }
	},function(){
		var targetEl = this.$["body-frame"];
		if(modalType == "url"){
			$.ajax({
				type : "POST",
				url : url,
				data : paramData,
				success : function(data) {
					targetEl.append(data);
				}
			});
		}else{
			targetEl.append(paramData);
		}
		$('body').on('click','.modal-close',function(){
			$(this).closest('.ax5modal').find('.fa-times-circle').click();
		});
    });
}

function validationCheck(){
	$('.validationCheck').each(function( index ) {
		if($(this).val() == ""){
			alert("필수 항목을 입력해 주세요.");
			$(this).focus();
			valCheck = false;
			return false;
		}else{
			if($(this)[0].id == "email"){
				if(emailRegex.test($(this).val()) === false) {  
				    alert("잘못된 이메일 형식입니다.");
					valCheck = false;
					return false;
				}
			}
			valCheck = true;
			return true;
		}
	});
}

/**
 * Form에 Disabled되어 있는 컨트롤을 활성화(disabled='false')하고 serialize()한 이후 다시 비활성화(disabled='true')
 * @param userForm
 * @returns param
 */
function fn_disabledSerialize(userForm, type){
	var myform = $(userForm);
	var disabled = myform.find(':input:disabled').removeAttr('disabled');
	var param = "";
	if(type == "json"){
		param = myform.serializeJSON();
	}else{
		var param = myform.serialize();
	}
	disabled.attr('disabled','disabled');
	return param;
}

/**
 *한글체크
 * @param string
 * @returns t/f
 */
function fnCheckNotKorean(koreanStr){                  
    for(var i=0;i<koreanStr.length;i++){
        var koreanChar = koreanStr.charCodeAt(i);
        if( !( 0xAC00 <= koreanChar && koreanChar <= 0xD7A3 ) && !( 0x3131 <= koreanChar && koreanChar <= 0x318E ) ) { 
        }else{
            //hangul finding....
            return false;
        }
    }
    return true;
}

/**
 * 날짜변환
 * @param string
 * @returns t/f
 */
Date.prototype.format = function(f) { 
	if (!this.valueOf()) return " "; 
	var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"]; 
	var d = this; 
	return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) { 
		switch ($1) {
			case "yyyy": return d.getFullYear(); 
			case "yy": return (d.getFullYear() % 1000).zf(2); 
			case "MM": return (d.getMonth() + 1).zf(2); 
			case "dd": return d.getDate().zf(2); 
			case "E": return weekName[d.getDay()]; 
			case "HH": return d.getHours().zf(2); 
			case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2); 
			case "mm": return d.getMinutes().zf(2); 
			case "ss": return d.getSeconds().zf(2); 
			case "a/p": return d.getHours() < 12 ? "오전" : "오후"; 
			default: return $1; } 
		}); 
};

String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;}; 
String.prototype.zf = function(len){return "0".string(len - this.length) + this;}; 
Number.prototype.zf = function(len){return this.toString().zf(len);};

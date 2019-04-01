?/**
 * Forbid to drag item on screen.
 */
function prohibitDragDrop() {
    event.dataTransfer.effectAllowed = "none";
}
if (document.all) {
    document.ondragstart = prohibitDragDrop;
}
document.oncontextmenu = function() { return false;} 

/**
 * Check number.
 * Used in function [dateCheck]
 */
function numCheck(value) {
    len = value.length;
    decPoint = false;
    if (value == "-" || value == ".") {
        return false;
    }
    for (i = 0; i < len; i++) {
        c = value.charAt(i);
        switch (c) {
            case '0' :
            case '1' :
            case '2' :
            case '3' :
            case '4' :
            case '5' :
            case '6' :
            case '7' :
            case '8' :
            case '9' :
           //case ' ' :
                break;
            case '-' :
                if (i != 0) {
                    return false;
                }
                break;
            case '.' :
                if (decPoint == true) {
                    return false;
                }
                decPoint = true;
                break;
            default :
                return false;
        }
    }
    return true;
}

/**
 * Change data type to Int.
 * Used in function [dateCheck]
 */
function parseIntX(value) {
    str = allSpaceDel(value);
    len = str.length;
    for (i = 0; i < len; i++) {
        c = str.charAt(i);
        if (c != '0') {
            break;
        }
    }
    if (i >= len) {
        i = i - 1;
    }
    if ( i >= 0 ) {
        return parseInt(str.substring(i,len));
    } else {
        return str;
    }
}

/**
 * Delete space.
 * Used in function [parseIntX]
 */
function allSpaceDel(value) {
    len = value.length;
    str = "";
    for (i = 0; i < len; i++) {
        c = value.charAt(i);
        if (c == ' ' || c == '�@') {
        } else {
            str = str + c;
        }
    }
    return str;
}

/**
 * Trim space.
 */
function trimX(value) {
    len = value.length;
    start = 0;
    end = value.length;
    for (i = 0; i < len; i++) {
        c = value.charAt(i);
        if (c == " ") {
            start++;
        } else {
            break;
        }
    }   
	if(start == end){
		return "";
	}	
    for (j = value.length - 1; j > 0; j--) {
        c = value.charAt(j);
         if (c == " ") {
            end--;
        } else {
            break;
        }
    }
    return value.substring(start, end);
}

//Trim �S���p space
function delFirstLastSpace( str ){
	var i
 	if( str.match("[ �@]$") != null ){
  		while(1){
  	 		if( str.match("[ �@]$") == null )break;
   				strNew = str.substring(0,(str.length-1));
   				str = strNew;
  		}
 	}
 	for(i=0;i<str.length;i++){
  		if( str.charAt(i) !=" "&&str.charAt(i) != "�@"){
   			break;
  		}
 	}
 	str = str.substring(i);
 	return str
}

function createPassword() {
	var nowDate = new Date();
	return Math.random() * 10000000000000000000 + nowDate.getTime();
}

/**************************************************************
 * �֐���		�FcheckDateFromTo(fromDate,toDate)
 * ����			�FFrom�ATo���t�Aflg
 * �߂�l			�F����
 * �@�\����		�F���t��From�ATo�̃`�F�b�N
 * 				�F���t�͐���������(yyyy/mm/dd)
 * 				�FcheckDateFromTo("2006/12/01","2006/12/01") true
 * 				�FcheckDateFromTo("2006/12/01","2006/12/02") true
 * 				�FcheckDateFromTo("2006/12/01","2006/11/30") false
 * 				�FcheckDateFromTo("2006/12/01","2006/12/01","1") false
 * 				�FcheckDateFromTo("2006/12/01","2006/12/02","1") true
 * 				�FcheckDateFromTo("2006/12/01","2006/11/30","1") false
 **************************************************************/
function checkDateFromTo(fromDate,toDate,flg) {
	if ( "" == fromDate || "" == toDate ) { 
		return true; 
	}
	fd = fromDate.split("/");
	td = toDate.split("/");
	for( i = 0 ; i < 3 ; i++ ) {
		if ( eval(fd[i]) < eval(td[i]) ) { 
			return true;
		} else if(eval(fd[i]) > eval(td[i])) {
			return false;
		}
	}
	if (arguments.length == 3) {
		return false;
	}

	return true;
}

/**
 * ������Byte�����Z�o
 * @param str
 * @returns ������Byte��
 */
function getByte(str) {
	// �o�C�g�����i�[����ϐ�
	var count = 0;
	for (i = 0; i < str.length; i++){
		var checkKana = str.charAt(i);
		// ���p�����̏ꍇ
		if ((checkKana >= " " && checkKana <= "~")||(checkKana >= "�" && checkKana <= "�")) {
		// ���p����1�o�C�g�𑫂�
			count+=1;
		}else{
			// �S�p�����̏ꍇ
			// �S�p����2�o�C�g�𑫂�
			count+=2;
		}
	}
	// �o�C�g����Ԃ�
	return count;
}

/**
 * �t�@�C���g���q�`�F�b�N
 * @param fileName
 */
function checkFileSuffix(fileName, fileSuffixAll){
	var checkResult = false;
	var fileSuffix = getFileSuffix(fileName);
	fileSuffixAll = fileSuffixAll.replace(/\n/,"");
	var fileSuffixArr = fileSuffixAll.toUpperCase().split(",");
	for (i=0;i<fileSuffixArr.length ;i++ ) {
	    if (fileSuffix.toUpperCase() === fileSuffixArr[i]) {
	    	checkResult = true;
	    }
	}
	return checkResult;
}

/**
 * �t�@�C���g���q���擾
 * @param fileName
 * @returns
 */
function getFileSuffix(fileName) {
    var ret;
    if (!fileName) {
    	return ret;
    }
    var fileTypes = fileName.split(".");
    var len = fileTypes.length;
    if (len === 0) {
    	return ret;
    }
    ret = fileTypes[len - 1];
    return ret;
}

/**
 * �ő咷���`�F�b�N
 */
function checkstr(str,len) {    
	num=getByte(str);
	if(num > len){
		return false;
	} else{
		return true;
	}
}

/**
 * �Y�t�t�@�C���`�F�b�N
 * @param fileDom
 * @param acceptType
 * @param nameMaxLength
 * @param maxSize MB
 * @param necessaryFlag
 * @param name
 * @returns
 */
function checkFile(fileDom, acceptType, nameMaxLength, maxSize, necessaryFlag, name) {
	
	var filePath = fileDom.value;
	
	// �K�{�w��t���O
	if (necessaryFlag == "1"){
		if (filePath == ""){
			window.top.showAlertMsg("MSGOE012", name);
			fileDom.focus();
			return false;
		}
	}
	
	// �t�@�C���p�X�`�F�b�N
	if(filePath != "" && filePath.match(/^[a-zA-Z]:\\/) == null) {
		window.top.showAlertMsg("MSGOE019", name);
		fileDom.focus();
		return false;
	}

	if(filePath){
		var file = fileDom.files[0];
		var fileName = file.name;
		
		// �t�@�C���ތ^�`�F�b�N
		if (!checkFileSuffix(fileName, acceptType)){
			window.top.showAlertMsg("MSGOE021", name, acceptType);
			fileDom.focus();
			return false;
		}
		
		// �t�@�C�����������`�F�b�N
		if (getByte(fileName) > nameMaxLength) {
			window.top.showAlertMsg("MSGOE023", name, nameMaxLength);
			fileDom.focus();
			return false;
		}
		
		var fileSize = file.size;
		// ��t�@�C���`�F�b�N
		if (fileSize == 0){
			window.top.showAlertMsg("MSGOE022", name);
			fileDom.focus();
			return false;
		}
		
		// �t�@�C���T�C�Y�`�F�b�N
		if (fileSize > maxSize * 1024 * 1024){
			window.top.showAlertMsg("MSGOE020", name, maxSize);
			fileDom.focus();
			return false;
		}
	}
	return true;
}

// �d���T�u�~�b�g����
function repeatSubmitCtrl(formId){
	$("#" + formId + " input[type='button']").attr("disabled",true);
	$("#" + formId + " button").attr("disabled",true);		
}

//�@���ڑO��X�y�[�X������
function trimLRAll(){
	$("input:text").not("[readonly]").each(function(index, item){
		if (!item.disabled){
			var str = $(this).val(); 
			str = delFirstLastSpace(str); 
			$(this).val(str);   
		}
	});
}

//�A�����X�g���擾
function linkageList(actionNm, formId, toCode, list, fromCode){
	var url = $("#contextPath").val() + "/" + actionNm + "_" + list + ".do";
	var parm = {};
	parm[formId + '.' + fromCode] = $("#" + fromCode).val();
	$.post(
		url,
		parm,
		function(data){
			$("#" + toCode).replaceWith(data);
			return false;
		}
	);
}
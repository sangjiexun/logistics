<%@page contentType="text/html; charset=MS932" pageEncoding="MS932"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/css/common.css" type="text/css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/bootstrap/3.3.4/css/bootstrap-custom.css" rel="stylesheet">
<link href='<%=request.getContextPath()%>/css/bootstrap/3.3.4/css/bootstrap.css' rel="stylesheet">
<style>
body{margin:0}
#gview_resultListJqGrid{
	overflow-x:hidden;
}
</style>
<script src='<%=request.getContextPath()%>/js/jquery.min.js'></script>

<script type="text/javascript">
function onloadgo(){
	$("#gyousyaCd").focus();
	if ($("#searchFlg").val()=="1") {
		$.jgrid.gridUnload("resultListJqGrid");
		defaultObj.url = "<%=request.getContextPath()%>/h0020/doSearch";
		$("#resultListJqGrid").jqGrid($.extend(defaultObj,{datatype : "json"}));
		return false;
	}
}

/**
 * ���׃����N����
 */
function linkSubmit(rowdata){
	console.log(rowdata);
	document.forms[0].action = '<%=request.getContextPath()%>/c0130/doShousai';
	document.forms[0].submit();
}
//�I�������N���N���b�N�����Ƃ�
function dlgOK(outtext)
{
	// 2017/08/07 NTS_�� �y��B309�z �h���C�o�[�����e�i���X�@�\�̉��C UPD Start
//	window.returnValue=outtext;
	window.parent.returnValue=outtext;
	// 2017/08/07 NTS_�� �y��B309�z �h���C�o�[�����e�i���X�@�\�̉��C UPD End
    //returnValue�͌Ăяo�����ɕԂ��l(���l�A������A���̑��̒l���Ƃ�)
	window.close();
}
function dlgCancel(){
	window.returnValue="";
	window.close();
   //[�~]�{�^�����������ꍇ�� null ��Ԃ��悤�ł���
}
var defaultObj = {
		caption : "�ꗗ",
		datatype : "json",
		mtype : "POST",
		styleUI : 'Bootstrap',
		colModel : [ 
			{label : '�I��',name : 'listKey',width : 20, 
				formatter: function(cellvalue, options, rowdata) {
					return "<a name='link' href='#' onclick='dlgOK(\""+rowdata.listKey + "\")'; >�I��</a>";
				}
			},  
			{label : '�Ǝ҂b�c',name : 'gyousyaCd',width : 20},
			{label : '�ƎҖ�',name : 'gyousyaNm',width : 20},
			{label : '�Ǘ���',name : 'kanrimotoKaisyaNm',width : 20},
		],
		jsonReader : {
			root : "gridModel",
			records : "record",
			repeatitems : false,
		},
		 prmNames : {
			page : "page",		//
			rows : "rows", 		//
			sort : "sort_key",  //
			order : "asc_desc"  //?
		},
		viewrecords : true,
		rowList : [ 10, 20, 30 ],
		width: 860,
		height:360,
		rowNum : 10,
		pager : "#resultListJqGridPager",
		beforeRequest:function(){
			$("#searchRowNum").val($("#resultListJqGrid").jqGrid('getGridParam','rowNum'));
		},
		gridComplete:function(){
			 document.getElementById("input_resultListJqGridPager").innerHTML="";
			$(".ui-pg-button").css("padding-left","10px");
			$(".ui-th-ltr").css("background","#CEE7F7");
			$(".ui-pg-selbox").css("height","30px");
			$(".ui-jqgrid-titlebar").css("text-align","left");
			$(".ui-pg-button").css("position","relative");
			$(".ui-pg-button").css("left","-130px");
			$(".ui-pg-selbox").css("position","relative");
			$(".ui-pg-selbox").css("left","-100px");
			$(".ui-paging-info").css("position","relative");
			$(".ui-paging-info").css("left","-20px");
			$(".ui-th-column").css("height","5px");
		}
	};
	$(document).ready(function() {
		$("#BtnQuery").on("click", function() {
			//
			//trimLRAll();
			$.jgrid.gridUnload("resultListJqGrid");
			defaultObj.url = "<%=request.getContextPath()%>/h0020/doSearch";
			
			$("#resultListJqGrid").jqGrid($.extend(defaultObj,{datatype : "json"}));
			return false;
		});
	});
</script>
</head>

<body onload="onloadgo()" >
<table>
<tr>
<td>
<script type="text/ecmascript" src="<%=request.getContextPath()%>/css/jqGrid/jquery.jqGrid.js"></script>
<script type="text/ecmascript" src="<%=request.getContextPath()%>/css/jqGrid/grid.locale-ja.js"></script>
<form:form name="searchForm" action="/h0020" method="post" modelAttribute="h0020model">
<input type="hidden" id="searchFlg" name="searchFlg" value="${h0020model.searchFlg}" />
<div style="width:860px;height:20px;background:#337AB7"></div>
<br>
<TABLE class="main">
	<colgroup>
		<col class="labelField char07Col"/>
		<col class="valueField char18Col"/>
		<col class="labelField char07Col"/>
		<col class="valueField char18Col"/>
		<col class="labelField char07Col"/>
		<col class="valueField char06Col"/>
	</colgroup>
    <TR>
      <TD>�Ǝ҂b�c</TD>
      <TD>
      <form:input type="text" path="gyousyaCd" maxlength="6"  class="txtInp10N" style="ime-mode: disabled" value="${h0020model.gyousyaCd}" /></TD>
      <TD>�ƎҖ�</TD>
      <TD>
      <form:input type="text" path="gyousyaNm" maxlength="15" class="txtInp10N" style="ime-mode: disabled" value="${h0020model.gyousyaNm}" /></TD>
      <TD>�Ǘ���</TD>
	  <TD>
	   <c:if test="${h0020model.kanrimotoFlg ne '0'}">
	  <form:select path="kanrimotoFlg"  onchange='changeDp()'>
	  		<form:option value="" label="" />
			<form:options items="${h0020model.kanrimotoFlgItems}" itemLabel="fullName" itemValue="codeId" attributes="tabindex='3'  disabled='true' style='background-color: #ffffff'"/>
	  </form:select>
	  </c:if>
	  <c:if test="${h0020model.kanrimotoFlg eq '0'}">
	  <form:select path="kanrimotoFlg"  onchange='changeDp()'>
	  		<form:option value="" label="" />
			<form:options items="${h0020model.kanrimotoFlgItems}" itemLabel="fullName" itemValue="codeId"/>
	  </form:select>
	  </c:if>	
	  </TD>
    </TR>
</TABLE>
<br>
<TABLE>
     <TR>
      <TD colspan="7" class="btnLine">
      	<input id="BtnQuery" name="BtnQuery" type="button" class="button" value="����"  />
      	<input id="Btn01" name="Btn01" type="button" class="button" value="����" onclick="dlgCancel()"/>
      </TD>
    </TR>
</TABLE>
<br>
	<div align="left">
	<table id="resultListJqGrid"></table>
	<div id="resultListJqGridPager"></div>
	<%-- list:${list} --%>
	</div>

</form:form>
</td>
</tr>
</table>
</body>
</html>
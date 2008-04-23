<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<html>
<head>
<META name=VI60_defaultClientScript content=VBScript>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">

<%String context =request.getContextPath();%>
<!--Style Sheets First one to adjust fonts on input fields.-->
<LINK rel=stylesheet type=text/css HREF="<%=context%>/cell/control/olstyle.css">

<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/control/function.vbs"></SCRIPT>

<SCRIPT  LANGUAGE=JAVASCRIPT src="<%=context%>/cell/control/buttons.js"></SCRIPT>

<title>华表模板编辑器</title>

<SCRIPT LANGUAGE=javascript>
<!--
function InitFontname(){
	strFontnames = CellWeb1.GetDisplayFontnames();
	var arrFontname = strFontnames.split('|');
	arrFontname.sort();
	var i;
	var sysFont;
	if( CellWeb1.GetSysLangID () == 2052)
		sysFont = "宋体";
	else sysFont = "Arial";
		

	for( i =0; i < arrFontname.length;i++ ){
		var oOption = document.createElement("OPTION");
		FontNameSelect.options.add(oOption);
		oOption
		oOption.innerText = arrFontname[i];
		oOption.value = arrFontname[i];
		if( arrFontname[i] == sysFont ) oOption.selected = true;
	}
}

function window_onresize() {
	var lWidth = document.body.offsetWidth;
	if( lWidth <= 0) lWidth = 1;
	CellWeb1.style.width = lWidth;
	Menu1.style.width = lWidth;

	var lHeight = document.body.offsetHeight - parseInt(CellWeb1.style.top);
	if( lHeight <= 0 ) lHeight = 1;
	CellWeb1.style.height = lHeight;
}

function window_onload(strURL) {
	/*strURL=strURL+"/cell/emenu.clm";
	strURL="/"+strURL.replace(/\//g,'\\'); */

	Menu1.style.left = 0;
	Menu1.style.top = 0;

	CellWeb1.EnableUndo(true);
	CellWeb1.Mergecell = true;
	CellWeb1.border = 0;
	CellWeb1.style.left = 0;	
	
	CellWeb1.style.top = idTBDesign.offsetTop + idTBDesign.offsetHeight;
	var lWidth = document.body.offsetWidth;
	if( lWidth <= 0) lWidth = 1;
	CellWeb1.style.width = lWidth;
	Menu1.style.width = lWidth;

	
	var lHeight = document.body.offsetHeight - parseInt(CellWeb1.style.top);
	if( lHeight <= 0 ) lHeight = 1;
	CellWeb1.style.height = lHeight;	

	/*var href = strURL;//window.location.pathname;	
	href = unescape(href);
	start = href.indexOf("/");
	end = href.lastIndexOf("\\");
	href = href.substring(start + 1, end + 1);
	href = href + "emenu.clm";	
	Menu1.ReadMenuFromLocalFile(href);*/
	
	CellWeb1.style.display="";
	InitFontname();
}

//-->
</SCRIPT>	

<!--BUTTON-->
<SCRIPT FOR="cbButton" EVENT="onmousedown()"	LANGUAGE="JavaScript" >
	return onCbMouseDown(this);
</SCRIPT>

<SCRIPT FOR="cbButton" EVENT="onclick()"		LANGUAGE="JavaScript" >
	return onCbClickEvent(this);
</SCRIPT>

<SCRIPT FOR="cbButton" EVENT="oncontextmenu()"	LANGUAGE="JavaScript" >
	return(event.ctrlKey);
</SCRIPT>

</HEAD>

<BODY id="mainbody" class="mainBody" LANGUAGE=javascript onresize="return window_onresize()" onload="return window_onload('F:/goa/goa')">
<OBJECT id=Menu1 style="LEFT: 0px; WIDTH: 927px; TOP: -1px; HEIGHT: 0px" 
height=19 width=927 classid=clsid:F82DB98D-842D-4DAB-9312-E478D8255720><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="24527"><PARAM NAME="_ExtentY" VALUE="503"><PARAM NAME="_StockProps" VALUE="0"></OBJECT>
<!--Top Toolbar-->
<TABLE class="cbToolbar" id="idTBGeneral" cellpadding='0' cellspacing='0' width="100%">
	<TR>
	<TD NOWRAP><A class=tbButton id=cmdFileNew title=新建 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/new.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdFileOpen title=打开远程文档 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/openweb.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdFileSave title=保存 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/save.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdFilePrint title=打印 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/print.gif" width="16" height="16"></A></TD>
	<TD class="tbDivider" NOWRAP><A class=tbButton id=cmdFilePrintPreview title=打印预览 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/printpreview.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdEditCut title=剪切 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/cut.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdEditCopy title=复制 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/copy.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdEditPaste title=粘贴 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/paste.gif" width="16" height="16"></A></TD>
	<TD class="tbDivider" NOWRAP><A class=tbButton id=cmdEditFind title=查找 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/find.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdEditUndo title=撤消 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/undo.gif" width="16" height="16"></A></TD>
	<TD class="tbDivider" NOWRAP><A class=tbButton id=cmdEditRedo title=重做 href="#" name=cbButton  sticky="true"><IMG align=absMiddle src="<%=context%>/cell/general/redo.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdViewFreeze title=不滚动区域 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/freeze.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdFormatPainter title=格式刷 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/painter.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdSortAscending title=升序排列 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/sorta.gif" width="16" height="16"></A></TD>
	<TD class="tbDivider" NOWRAP><A class=tbButton id=cmdSortDescending title=降序排列 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/sortd.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdFormulaInput title=输入公式 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/formula.gif" width="16" height="16"></A></TD>
	<TD class="tbDivider" NOWRAP><A class=tbButton id=cmdFormulaSerial title=填充单元公式序列 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/formulaS.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdFormulaSumH title=水平求和 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/sumh.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdFormulaSumV title=垂直求和 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/sumv.gif" width="16" height="16"></A></TD>
	<TD class="tbDivider" NOWRAP><A class=tbButton id=cmdFormulaSumHV title=双向求和 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/sum.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdChartWzd title=图表向导 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/chartw.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdInsertPic title=插入图片 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/insertpic.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdHyperlink title=超级链接 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/hyperlink.gif" width="16" height="16"></A></TD>
	<TD class="tbDivider" NOWRAP><A class=tbButton id=cmdWzdBarcode title=条形码向导 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/barcode.gif" width="16" height="16"></A></TD>

	<TD class="tbDivider" NOWRAP id="cmdViewScale" Title="显示比例">
		<SELECT name="viewScaleSelect" style="WIDTH: 89px; HEIGHT: 23px" onChange="changeViewScale(viewScaleSelect.value)" ACCESSKEY="v" size="1" 
     >
          <option value="200">200%</option>
          <option value="150">150%</option>
          <option value="120">120%</option>
          <option value="110">110%</option>
          <option selected value="100">100%</option>
          <option value="90">90%</option>
          <option value="80">80%</option>
          <option value="70">70%</option>
          <option value="50">50%</option>
          <option value="30">30%</option>
          <option value="20">20%</option>
          <option value="15">15%</option>
          <option value="10">10%</option>
          <option value="5">5%</option>
          <option value="3">3%</option>
          <option value="1">1%</option>
        </SELECT>
	</TD>


	<TD NOWRAP><A class=tbButton id=cmdShowGridline title=显示/隐藏背景网格线 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/gridline.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdVPagebreak title=垂直分隔线 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/vpagebreak.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdHPagebreak title=水平分隔线 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/hpagebreak.gif" width="16" height="16"></A></TD>
	<TD class="tbDivider" NOWRAP><A class=tbButton id=cmdShowPagebreak title=显示/隐藏分隔线 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/pagebreak.gif" width="16" height="16"></A></TD>
	<TD class="tbDivider" NOWRAP><A class=tbButton id=cmdAbout title=关于华表插件 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/general/about.gif" width="16" height="16"></A></TD>
	<TD NOWRAP width="100%"></TD>
	</TR>
</TABLE>
<TABLE class="cbToolbar" id="idTBFormat" cellpadding='0' cellspacing='0' width="100%">
	<TR>
	<TD NOWRAP id="cmdFontName" Title="字体">
		<SELECT name="FontNameSelect" style="WIDTH: 225px; HEIGHT: 23px" onChange="changeFontName(FontNameSelect.value)" ACCESSKEY="v" size="1">
        &nbsp; </SELECT>
	</TD>
	<TD NOWRAP class="tbDivider" id="cmdFontSize" Title="字号">
		<SELECT name="FontSizeSelect" style="WIDTH: 67px; HEIGHT: 23px" onChange="changeFontSize(FontSizeSelect.value)" ACCESSKEY="v" size="1">
          <option value="5">5</option>
          <option value="6">6</option>
          <option value="7">7</option>
          <option value="8">8</option>
          <option value="9">9</option>
          <option selected value="10">10</option>
          <option value="11">11</option>
          <option value="12">12</option>
          <option value="14">14</option>
          <option value="16">16</option>
          <option value="18">18</option>
          <option value="20">20</option>
          <option value="22">22</option>
          <option value="24">24</option>
          <option value="26">26</option>
          <option value="28">28</option>
          <option value="30">30</option>
          <option value="36">36</option>
          <option value="42">42</option>
          <option value="48">48</option>
          <option value="72">72</option>
          <option value="100">100</option>
          <option value="150">150</option>
          <option value="300">300</option>
          <option value="500">500</option>
          <option value="800">800</option>
          <option value="1200">1200</option>
          <option value="2000">2000</option>
        </SELECT>
	</TD>

	<TD NOWRAP><A class=tbButton id=cmdBold title=粗体 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/format/bold.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdItalic title=斜体 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/format/italic.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdUnderline title=下划线 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/format/underline.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdBackColor title=背景色 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/format/backcolor.gif" width="16" height="16"></A></TD>
	<TD class="tbDivider" NOWRAP><A class=tbButton id=cmdForeColor title=前景色 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/format/forecolor.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdWordWrap title=自动折行 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/format/wordwrap.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdAlignLeft title=居左对齐 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/format/alignleft.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdAlignCenter title=居中对齐 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/format/aligncenter.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdAlignRight title=居右对齐 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/format/alignright.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdAlignTop title=居上对齐 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/format/aligntop.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdAlignMiddle title=垂直居中 href="#" name=cbButton  sticky="true"><IMG align=absMiddle src="<%=context%>/cell/format/alignmiddle.gif" width="16" height="16"></A></TD>
	<TD class="tbDivider" NOWRAP><A class=tbButton id=cmdAlignBottom title=居下对齐 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/format/alignbottom.gif" width="16" height="16"></A></TD>
	<TD NOWRAP id="cmdBoderType" Title="边框类型">
		<SELECT name="BorderTypeSelect" style="WIDTH: 109px; HEIGHT: 23px" ACCESSKEY="v" size="1" 
     >
          <option value="2" selected>细线</option>
          <option value="3">中线</option>
          <option value="4">粗线</option>
          <option value="5">划线</option>
          <option value="6">点线</option>
          <option value="7">点划线</option>
          <option value="8">点点划线</option>
          <option value="9">粗划线</option>
          <option value="10">粗点线</option>
          <option value="11">粗点划线</option>
          <option value="12">粗点点划线</option>
          
        </SELECT>
	</TD>
	<TD NOWRAP><A class=tbButton id=cmdDrawBorder title=画边框线 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/format/border.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdEraseBorder title=抹边框线 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/format/erase.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdCurrency title=货币符号 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/format/currency.gif" width="16" height="16"></A></TD>
	<TD NOWRAP><A class=tbButton id=cmdPercent title=百分号 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/format/percent.gif" width="16" height="16"></A></TD>
	<TD class="tbDivider" NOWRAP><A class=tbButton id=cmdThousand title=千分位 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/format/thousand.gif" width="16" height="16"></A></TD>
	<TD class="tbDivider" NOWRAP width="100%"></TD>
	</TR>
</TABLE>
<TABLE class="cbToolbar" id="idTBDesign" cellpadding='0' cellspacing='0' width="100%">
	<TR>
	<TD NOWRAP width="21"><A class=tbButton id=cmdInsertCol title=插入列 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/insertcol.gif" width="16" height="16"></A></TD>
	<TD NOWRAP width="21"><A class=tbButton id=cmdInsertRow title=插入行 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/insertrow.gif" width="16" height="16"></A></TD>
	<TD NOWRAP width="21"><A class=tbButton id=cmdAppendCol title=追加列 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/appendcol.gif" width="16" height="16"></A></TD>
	<TD class="tbDivider" NOWRAP width="21"><A class=tbButton id=cmdAppendRow title=追加行 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/appendrow.gif" width="16" height="16"></A></TD>
	<TD NOWRAP width="21"><A class=tbButton id=cmdDeleteCol title=删除列 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/deletecol.gif" width="16" height="16"></A></TD>
	<TD NOWRAP width="21"><A class=tbButton id=cmdDeleteRow title=删除行 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/deleterow.gif" width="16" height="16"></A></TD>
	<TD class="tbDivider" NOWRAP width="21"><A class=tbButton id=cmdSheetSize title=表页尺寸 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/sheetsize.gif" width="16" height="16"></A></TD>
	<TD NOWRAP width="21"><A class=tbButton id=cmdMergeCell title=组合单元格 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/mergecell.gif" width="16" height="16"></A></TD>
	<TD NOWRAP width="21"><A class=tbButton id=cmdUnMergeCell title=取消单元格组合 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/unmergecell.gif" width="16" height="16"></A></TD>
	<TD NOWRAP width="21"><A class=tbButton id=cmdMergeRow title=行组合 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/mergerows.gif" width="16" height="16"></A></TD>
	<TD class="tbDivider" NOWRAP width="21"><A class=tbButton id=cmdMergeCol title=列组合 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/mergecols.gif" width="16" height="16"></A></TD>
	<TD NOWRAP width="21"><A class=tbButton id=cmdReCalcAll title=重算全表 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/calculateall.gif" width="16" height="16"></A></TD>
	<TD NOWRAP width="21"><A class=tbButton id=cmdFormulaSum3D title=设置汇总公式 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/sum3d.gif" width="16" height="16"></A></TD>
	<TD class="tbDivider" NOWRAP width="21"><A class=tbButton id=cmdReadOnly title=单元格只读 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/readonly.gif" width="16" height="16"></A></TD>
	<TD NOWRAP id="cmdFillType" Title="填充方式">
		<SELECT name="FillTypeSelect" style="WIDTH: 102px; HEIGHT: 23px" onChange="changeFillType(FillTypeSelect.value)" ACCESSKEY="v" size="1">
          <option value="1" selected 
       >向下填充</option>
          <option value="2">向右填充</option>
          <option value="4">向上填充</option>
          <option value="8">向左填充</option>
          <option value="16">重复填充</option>
          <option value="32">等差填充</option>
          <option value="64">等比填充</option>
        </SELECT>
	</TD>
	<TD class="tbDivider" NOWRAP width="21"><A class=tbButton id=cmdFillSerial title=序列填充 href="#" name=cbButton><IMG align=absMiddle src="<%=context%>/cell/design/fillserial.gif" width="16" height="16"></A></TD>
	
	<TD NOWRAP id="cmdDateType" Title="日期类型">
		<SELECT name="DateTypeSelect" style="WIDTH: 191px; HEIGHT: 23px" onChange="changeDateType(DateTypeSelect.value)" ACCESSKEY="v" size="1">
          <option value="1" selected 
       >1997-11-6</option>
          <option value="2">1997-11-6 13:30:12</option>
          <option value="3">1997-11-6 1:30 PM</option>
          <option value="4">1997-11-6 13:30</option>
          <option value="5">97-11-6</option>
          <option value="6">11-6-97</option>
          <option value="7">11-06-97</option>
          <option value="8">11-6</option>
          <option value="9">一九九七年十一月六日</option>
          <option value="10">一九九七年十一月</option>
          <option value="11">十一月四日</option>
          <option value="12">1997年11月4日</option>
          <option value="13">1997年11月</option>
          <option value="14">11月6日</option>
          <option value="15">星期二</option>
          <option value="16">二</option>
          <option value="17">6-Nov</option>
          <option value="18">6-Nov-97</option>
          <option value="19">06-Nov-97</option>
          <option value="20">Nov-97</option>
          <option value="21">November-97</option>
        </SELECT>
	</TD>
	<TD class="tbDivider" NOWRAP id="cmdTimeType" Title="时间类型">
		<SELECT name="TimeTypeSelect" style="WIDTH: 154px; HEIGHT: 23px" onChange="changeTimeType(TimeTypeSelect.value)" ACCESSKEY="v" size="1">
          <option value="1" selected 
       >1:30</option>
          <option value="2">1:30 PM</option>
          <option value="3">13:30:00</option>
          <option value="4">1:30:00 PM</option>
          <option value="5">13时30分</option>
          <option value="6">13时30分00秒</option>
          <option value="7">下午1时30分</option>
          <option value="8">下午1时30分00秒</option>
          <option value="9">十三时三十分</option>
          <option value="10">下午一时三十分</option>
        </SELECT>
	</TD>

	<TD NOWRAP width="100%"></TD>
	</TR>
</TABLE>

<div style="LEFT: 0px; POSITION: relative">正在装载华表插件模块......</div>

<p>

<p>
<OBJECT id=CommonDialog1 style="DISPLAY: none; POSITION: relative" height=32 
width=32 classid=clsid:F9043C85-F6F2-101A-A3C9-08002B2F49FB><PARAM NAME="_ExtentX" VALUE="688"><PARAM NAME="_ExtentY" VALUE="688"><PARAM NAME="_Version" VALUE="393216"><PARAM NAME="CancelError" VALUE="1"><PARAM NAME="Color" VALUE="0"><PARAM NAME="Copies" VALUE="1"><PARAM NAME="DefaultExt" VALUE=""><PARAM NAME="DialogTitle" VALUE=""><PARAM NAME="FileName" VALUE=""><PARAM NAME="Filter" VALUE=""><PARAM NAME="FilterIndex" VALUE="0"><PARAM NAME="Flags" VALUE="0"><PARAM NAME="FontBold" VALUE="0"><PARAM NAME="FontItalic" VALUE="0"><PARAM NAME="FontName" VALUE=""><PARAM NAME="FontSize" VALUE="8"><PARAM NAME="FontStrikeThru" VALUE="0"><PARAM NAME="FontUnderLine" VALUE="0"><PARAM NAME="FromPage" VALUE="0"><PARAM NAME="HelpCommand" VALUE="0"><PARAM NAME="HelpContext" VALUE="0"><PARAM NAME="HelpFile" VALUE=""><PARAM NAME="HelpKey" VALUE=""><PARAM NAME="InitDir" VALUE=""><PARAM NAME="Max" VALUE="0"><PARAM NAME="Min" VALUE="0"><PARAM NAME="MaxFileSize" VALUE="260"><PARAM NAME="PrinterDefault" VALUE="1"><PARAM NAME="ToPage" VALUE="0"><PARAM NAME="Orientation" VALUE="1"></OBJECT>
<OBJECT id=CellWeb1 
style="DISPLAY: none; LEFT: 184px; POSITION: absolute; TOP: 190px" height=183 
width=367 classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="9710"><PARAM NAME="_ExtentY" VALUE="4842"><PARAM NAME="_StockProps" VALUE="0"></OBJECT>
</p>

</BODY>
</HTML>

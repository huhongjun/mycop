function saveCell
	Dim buffer 
	Dim rtnval

	rtnval = 0
	 
	On Error Resume Next
	rtnval = CellWeb1.SaveToBuffer(buffer)

	document.forms(0).cellcontent.value =  Base64Encode(buffer)

	If Err.Number <> 0 Or rtnval <= 0 Then
		msgbox "�ύ�������ݳ���",48,"������Ϣ"
		saveCell = false
	Else
		saveCell = true
	End If
End function

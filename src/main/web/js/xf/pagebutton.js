function pressButton(name, value, content) {
    //if ( confirm("请确认要"+content+"吗?") ) {
    name.value = value;
    winform.submit();
    //}
    document.all("addbtn").disabled = "true";
}
function pressDeleteButton(name, value, content) {
    if (confirm("请确认要" + content + "吗?")) {
        name.value = value;
        winform.submit();
        document.all("deletebtn").disabled = "true";
    }
}

function pressSearch(name, value, action) {
    name.value = value;
    winform.action = action;
    winform.submit();
    document.all("searchbtn").disabled = "true";
}

function pressSelfSysButton(value, button) {
    if (check(winform)) {
        if (checkSelfSubmit(button)) {
            winform.Plat_Form_Request_Button_Event.value = value;
            winform.Plat_Form_Request_Event_ID.value = '9';
            winform.submit();
            button.disabled = "true";
        }
    }
}

function pressSelfSysButtonOpenNewWin(newurl, winname, newsize, button) {
    if (check(winform)) {
        if (checkSelfSubmit(button)) {
            window.open(newurl, winname, newsize);
        }
    }
}

function pressSaveButton(name, value) {
    name.value = value;
    if (check(winform)) {
        if (checkSubmit()) {
            winform.submit();
            document.all("savebtn").disabled = "true";
        }
    }
}

function pressSelfButton(value) {
    winform.Plat_Form_Request_Button_Event.value = value;
    winform.Plat_Form_Request_Event_ID.value = '9';
    winform.submit();
}

function pressSelfButtonOpenNewWin(newurl, winname, newsize) {
    window.open(newurl, winname, newsize);
}


function pressSelfSubmit(value) {
    winform.Plat_Form_Request_Button_Event.value = value;
    winform.Plat_Form_Request_Event_ID.value = '9';
    if (check(winform)) {
        //if ( checkSubmit() )
        if (checkSelfSubmit(value))
            winform.submit();
    }
}
function changeFunc() {
    winform.Plat_Form_Request_Event_ID.value = winform.Plat_Form_Request_Event_Value.value;
    winform.submit();

}
function pressKeyDown(keyCode) {
    if (keyCode == 117) { //F6-增加
        if (document.all.addbtn != "undefined") {
            document.all.addbtn.click();
        }
    } else if (keyCode == 119) { //F8-查找
        if (document.all.searchbtn != "undefined") {
            document.all.searchbtn.click();
        }
    } else if (keyCode == 121) { //F10-存盘
        if (document.all.savebtn != "undefined") {
            document.all.savebtn.click();
        }
    }
}

function PostFieldEvent(field) {
    var xml = new ActiveXObject("Microsoft.XMLDOM");
    var http = new ActiveXObject("Microsoft.XMLHTTP");
    var rowNodes;
    var rowNode;
    var type,name,value;
    var outstr;

    var URL = "/templates/xmlform.jsp?Plat_Form_Request_Instance_ID=" + winform.Plat_Form_Request_Instance_ID.value +
              "&Plat_Form_Request_Button_Event=" + field.name + "&Plat_Form_Request_Event_ID=14";
    //alert(URL);
    http.open("POST", URL, false);
    //outstr = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>";
    //outstr = outstr + "<data><row><name>ttt</name><value>-555--测试</value></row></data>";
    //outstr = outstr + "<data name=\"test\" value=\"ttttsdf\"/>";
    outstr = combineField();
    //alert(outstr);
    http.send(outstr);

    xml.async = false; //must set async to false
    xml.loadXML(http.responseText);

    if (xml.parseError.errorCode == 0)
    {
        rowNodes = xml.selectNodes("data/row");
        for (rowNode = rowNodes.nextNode(); rowNode != null; rowNode = rowNodes.nextNode())
        {
            type = rowNode.selectSingleNode("type").text;
            name = rowNode.selectSingleNode("name").text;
            value = rowNode.selectSingleNode("value").text;
            //alert(type+name+value);

            if (type.indexOf('FLDVALUE') >= 0)
            {
                //alert(rowNode.selectSingleNode("type").text+rowNode.selectSingleNode("name").text+rowNode.selectSingleNode("value").text);
                if (typeof(document.all[name]) != "undefined" && document.all[name] != null)
                {
                    document.all[name].value = value;
                }
            }
            else if (type.indexOf('READONLY') >= 0)
            {
                if (typeof(document.all[name]) != "undefined" && document.all[name] != null)
                {
                    document.all[name].readOnly = value;
                }
            }
            else if (type.indexOf('DISABLE') >= 0)
                {
                    if (typeof(document.all[name]) != "undefined" && document.all[name] != null)
                    {
                        document.all[name].disabled = value;
                    }
                }
                else if (type.indexOf('MESSAGE') >= 0)
                    {
                        //alert(name + "\n" + value);
                        alert(value);
                    }
                    else if (type.indexOf('HIDDEN') >= 0)
                        {
                            if (typeof(document.all[name]) != "undefined" && document.all[name] != null)
                            {
                                document.all[name].style.visibility = value;
                            }
                        }
                        else if (type.indexOf('FOCUS') >= 0)
                            {
                                if (typeof(document.all[name]) != "undefined" && document.all[name] != null)
                                {
                                    document.all[name].focus();
                                }
                            }
        }
    }
    if (xml != null) delete(xml);
    if (http != null) delete(http);
}


function BeforeFieldEvent(field) {

    var xml = new ActiveXObject("Microsoft.XMLDOM");
    var http = new ActiveXObject("Microsoft.XMLHTTP");
    var rowNodes;
    var rowNode;
    var type,name,value;
    var outstr;

    var URL = "/templates/xmlform.jsp?Plat_Form_Request_Instance_ID=" + winform.Plat_Form_Request_Instance_ID.value +
              "&Plat_Form_Request_Button_Event=" + field.name + "&Plat_Form_Request_Event_ID=15";
    //alert(URL);
    http.open("POST", URL, false);
    //outstr = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>";
    //outstr = outstr + "<data><row><name>ttt</name><value>-555--测试</value></row></data>";
    //outstr = outstr + "<data name=\"test\" value=\"ttttsdf\"/>";
    outstr = combineField();

    http.send(outstr);

    xml.async = false; //must set async to false
    xml.loadXML(http.responseText);

    if (xml.parseError.errorCode == 0)
    {
        rowNodes = xml.selectNodes("data/row");
        for (rowNode = rowNodes.nextNode(); rowNode != null; rowNode = rowNodes.nextNode())
        {
            type = rowNode.selectSingleNode("type").text;
            name = rowNode.selectSingleNode("name").text;
            value = rowNode.selectSingleNode("value").text;
            //alert(type+name+value);

            if (type.indexOf('FLDVALUE') >= 0)
            {
                //alert(rowNode.selectSingleNode("type").text+rowNode.selectSingleNode("name").text+rowNode.selectSingleNode("value").text);
                if (typeof(document.all[name]) != "undefined" && document.all[name] != null)
                {
                    document.all[name].value = value;
                }
            }


        }
    }
    if (xml != null) delete(xml);
    if (http != null) delete(http);

}

function combineField()
{
    var str = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>";
    str += "<data>";
    for (i = 0; i < winform.elements.length; i++)
    {
        if (typeof(winform.elements[i].name) != "undefined" && winform.elements[i].name != null
                && typeof(winform.elements[i].value) != "undefined" && winform.elements[i].value != null)
        {
            str += "<row>";
            str += "<name>" + winform.elements[i].name + "</name>";
            str += "<value>" + winform.elements[i].value + "</value>";
            str += "</row>";
        }
    }
    str += "</data>"
    return str;
}

function pageWinClose()
{
    try
    {   
        if (typeof(opener.document.all.submit5) != "undefined" && opener.document.all.submit5 != null)
        {
            if (typeof(opener.document.all.submit5) != "undefined" && opener.document.all.submit5.value != null)
            {
                if (opener.document.all.submit5.value.indexOf('刷新') >= 0 || opener.document.all.submit5.value.indexOf('刷 新') >= 0)
                {
//                    alert("上层");         haiyu 2010-08-09 delete
                    opener.document.all.submit5.onclick();
                }
            }
        }
    }
    catch (Error) {
    }
    if (typeof(window.parent) != "undefined") {//lj added in 20090409
        window.parent.opener = null;       //lj added in 20090324
        window.parent.open("", "_self");   //lj added in 20090324
        window.parent.close();              //lj added in 20090324
        //parent.close();                     //lj deleted in 20090324
    } else window.close();                //lj added in 20090409
}


for (t = 0; t < document.all.length; t++)
{
    if (document.all[t].name != null && document.all[t].name != '' && document.all[t].style.visibility != "hidden" && document.all[t].type != "hidden" && document.all[t].readOnly != true && document.all[t].disabled != true)
    {
        document.all[t].focus();
        break;
    }
}


/*
 关闭弹出类页面处理
 lj added in 20090407
 */
function refreshOp() { //这个不会提示是否关闭浏览器
    //        alert(window.parent.opener.location);
    if (window.parent.opener != undefined)window.parent.opener.location.reload();
    CloseWin();
    //return true;
}
function CloseWin() { //这个不会提示是否关闭浏览器
    window.opener = null;
    window.open("", "_self");
    window.close();
}
function gopage(url) {
    document.location = encodeURI(url);
}

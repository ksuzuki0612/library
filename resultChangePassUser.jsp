<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<link rel="stylesheet" href="updateD.css">
	<title>パスワード再設定</title>
</head>
<body>

<%
	try{
		boolean pass = (boolean)session.getAttribute("result");
  		if(pass == true){
%>
    <h1  class="flame16">パスワード再設定</h1>
    <h3><a href= choiceMenuUser.jsp class="btnChoice">パスワードが更新されました</a></h3>
<%
  		}else{
%>
	<h1  class="flame16">パスワード再設定</h1>
    <h3><a href= choiceMenuUser.jsp class="btnChoice">パスワードの更新に失敗しました</a></h3>
<%
  		}
	}
	catch(Exception e){
		out.println("例外が発生し，パスワードの更新に失敗しました");
		e.printStackTrace();
		out.println(e);
%>
	<h1  class="flame16">パスワード再設定</h1>
	<h3><a href= choiceMenuUser.jsp class="btnChoice">メニューに戻る</a></h3>
<%
	}
%>


</body>
</html>

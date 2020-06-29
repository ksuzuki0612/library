<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>ログイン</title>
    <link rel="stylesheet" href="updateD.css">
</head>
<body>

    <h1  class="flame16">IDまたはパスワードが間違っています</h1>
    	
    <a class="btn-square" href="shutDownBrowser.jsp" >終了</a>
    	
    <form action="LoginServlet" method="POST">
    	
        <h3 class="textConfiguration">従業員ID</h3>
        <input class="flameUI" name="empID" type="number">
        <h3 class="textConfiguration">パスワード</h3>
        <input class="flameUI" name="password" type="text">
        	
        <p><input class="btn-square-shadow" type="submit" value = "ログイン">
        <input class="btn-square-shadow" type="reset" value="リセットする"></p>
    </form>
	
</body>
</html>
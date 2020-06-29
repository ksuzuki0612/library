<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>返却受取</title>
    <link rel="stylesheet" href="updateD.css">
</head>
<body>

    <h1  class="flame16">返却受取</h1>
    <a class="btn-square" href="adminMenuUI.jsp" >メニューに戻る</a>
    	
    <form action="ReturnBookServlet" method="POST">
    	
        <h3 class="textConfiguration">ISBNを入力してください</h3>
        <input class="flameUI" name="isbnUi" type="number">
        	
        <h3 class="textConfiguration">従業員IDを入力してください</h3>
        <input class="flameUI" name="employeeUi" type="number">
        	
        <p><input class="btn-square-shadow" type="submit" value = "送信">
        <input class="btn-square-shadow" type="reset" value="リセット"></p>
    </form>
	
</body>
</html>
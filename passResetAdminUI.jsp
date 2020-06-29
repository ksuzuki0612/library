<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>パスワード再設定</title>
    <link rel="stylesheet" href="updateD.css">
</head>
<body>

    <h1  class="flame16">パスワード再設定</h1>
    	
    <a class="btn-square" href="choiceMenuAdmin.jsp" >メニューに戻る</a>
    	
    <form action="ChangePasswordAdminServlet" method="POST">
    	
        <h3 class="textConfiguration">従業員ID</h3>
        <input class="flameUI" name="empID" type="number">
        	
        <h3 class="textConfiguration">新しいパスワードを入力してください</h3>
        <input class="flameUI" name="password" type="text">
        	
        <h3 class="textConfiguration">確認のため，もう一度入力してください</h3>
        <input class="flameUI" name="checkPassword" type="text">
       	
        <p><input class="btn-square-shadow" type="submit" value = "変更">
        <input class="btn-square-shadow" type="reset" value="リセットする"></p>
    </form>
	
</body>
</html>

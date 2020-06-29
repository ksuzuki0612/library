<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>図書削除</title>
    <link rel="stylesheet" href="updateD.css">
</head>
<body>

    <h1  class="flame16">図書削除</h1>
    	
    <a class="btn-square" href="adminMenuUI.jsp" >メニューに戻る</a>
    	
    <form action="DeleteBookServlet" method="POST">
    	
        <h3 class="textConfiguration">削除したい本のISBNを入力してください</h3>
        <input class="flameUI" name="deleteISBN" type="number">

        	
        <p><input class="btn-square-shadow" type="submit" value="送信">
        <input class="btn-square-shadow" type="reset" value="リセット"></p>
    </form>
	
</body>
</html>

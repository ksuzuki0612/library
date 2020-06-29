<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>書籍在庫数変更</title>
    <link rel="stylesheet" href="updateD.css">
</head>
<body>

    <h1  class="flame16">書籍在庫数の更新</h1>
    <a class="btn-square" href="updateBook.jsp" >登録変更メニューに戻る</a>
    <form action="UpdateInventoryServlet" method="POST">
        <h3 class="textConfiguration">ISBNを入力してください</h3>
        <input class="flameUI" name="ISBN" type="number">
        <h3 class="textConfiguration">在庫数を入力してください</h3>
        <input class="flameUI" name="inventory" type="number">
        <p><input class="btn-square-shadow" type="submit" value = "送信">
        <input class="btn-square-shadow" type="reset" value="リセット"></p>
    </form>
	
</body>
</html>


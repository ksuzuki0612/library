<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>貸出承認</title>
    <link rel="stylesheet" href="updateD.css">
</head>
<body>

    <h1  class="flame16">各項目を入力してください</h1>
    <a class="btn-square" href="adminMenuUI.jsp" >メニューに戻る</a>
    	
    <form action="BorrowBookServlet" method="POST">
    	
        <h3 class="textConfiguration">ISBN(ハイフンを抜いてください)</h3>
        <input class="flameUI" name="lendISBN" type="number">
        	
        <h3 class="textConfiguration">従業員ID</h3>
        <input class="flameUI" name="borrowempID" type="number">
        	
        <h3 class="textConfiguration">貸出開始日(yyyyMMdd形式)</h3>
        <input class="flameUI" name="borrowStart" type="number">
        	
        <h3 class="textConfiguration">貸出終了日(yyyyMMdd形式)</h3>
        <input class="flameUI" name="borrowEnd" type="number">
        	
        <p><input class="btn-square-shadow" type="submit" value = "送信">
        <input class="btn-square-shadow" type="reset" value="リセット"></p>
    </form>
	
</body>
</html>
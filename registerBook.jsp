<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>図書登録</title>
    <link rel="stylesheet" href="updateD.css">
</head>
<body>

    <h1  class="flame16">図書登録</h1>
    <a class="btn-square" href="adminMenuUI.jsp" >メニューに戻る</a>
    	
    <form action="RegisterBookServlet" method="POST">
    	
        <h3 class="textConfiguration">出版日(yyyyMMdd形式)</h3>
        <input class="flameUI" name="regPubdate" type="number">
        	
        <h3 class="textConfiguration">著者名</h3>
        <input class="flameUI" name="regAuthor" type="text">
    	
        <h3 class="textConfiguration">タイトル</h3>
        <input class="flameUI" name="regTitle" type="text">
        	
        <h3 class="textConfiguration">出版社</h3>
        <input class="flameUI" name="regPublisher" type="text">

		<h3 class="textConfiguration">分野</h3>
        <input class="flameUI" name="regCategory" type="text">

        <h3 class="textConfiguration">ISBNを入力してください(ハイフンは抜いてください)</h3>
        <input class="flameUI" name="regISBN" type="number">
        
        <h3 class="textConfiguration">在庫数を入力してください</h3>
        <input class="flameUI" name="regInv" type="number">
        
        <p><input class="btn-square-shadow" type="submit" value = "送信">
        <input class="btn-square-shadow" type="reset" value="リセット"></p>
    </form>
	
</body>
</html>

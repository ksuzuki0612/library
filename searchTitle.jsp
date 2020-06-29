<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>タイトル検索</title>
    <link rel="stylesheet" href="updateD.css">
</head>
<body>

    <h1  class="flame16">タイトル検索</h1>
    	
    <a class="btn-square" href="searchMenu.jsp" >戻る</a>
    	
    <form action="SearchTitleServlet" method="POST">
    	
		<h3 class="textConfiguration">タイトル</h3>
        <input class="flameUI" name="searchtitle" type="text">
        	
        <p><input class="btn-square-shadow" type="submit" value = "検索">
        <input class="btn-square-shadow" type="reset" value="リセット"></p>
    </form>
	
</body>

</html>
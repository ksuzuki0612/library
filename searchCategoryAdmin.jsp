<%@ page contentType="text/html; charset=UTF-8"%>
<head>
    <title>分野検索</title>
    <link rel="stylesheet" href="updateD.css">
</head>
<body>

    <h1  class="flame16">分野検索</h1>
    	
    <a class="btn-square" href="searchMenuAdmin.jsp" >戻る</a>
    	
    <form action="SearchCategoryServlet" method="POST">
    	
		<h3 class="textConfiguration">分野</h3>
        <input class="flameUI" name="searchcategory" type="text">
        	
        <p><input class="btn-square-shadow" type="submit" value = "送信">
        <input class="btn-square-shadow" type="reset" value="リセット"></p>
    </form>
	
</body>
</html>
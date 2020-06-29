<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
  <head>
    <title>Untitled</title>
    <link rel="stylesheet" href="updateD.css">
  </head>
  <body>
    <h1  class="flame16">貸出数の更新</h1>
    <a class="btn-square" href="updateBook.jsp" >登録変更メニューに戻る</a>
    <form action="UpdateLendServlet" method="POST">
      <h3 class="textConfiguration">ISBNを入力してください</h3>
      <input class="flameUI" type="number"  name="ISBN">
      <h3 class="textConfiguration">貸出数を入力してください</h3>
      <input class="flameUI" type="number" name="addBorrowedAmount">
      <p> <input type="submit" class="btn-square-shadow">
        <input class="btn-square-shadow" type="reset" value="リセットする"></p>
    </form>	
  </body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import ="library.Book" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>検索結果</title>
    </head>
    <body>
        <h1>検索結果</h1>
        <table border="1" style="width:400">
            <tr bgcolor="blue">
                <th>ISBN</th><th>Title</th><th>Publisher</th><th>Publish Date</th><th>Author</th><th>Category</th>
            </tr>
            <%
                ArrayList<Book> book = (ArrayList<Book>)request.getAttribute("book");
                for (Book b : book){%>                     
            <tr>
                <td><%=b.getISBN()%></td>
                <td><%=b.getTitle()%></td>
                <td><%=b.getPublisher()%></td>
                <td><%=b.getPublishDate()%></td>
                <td><%=b.getAuthors()%></td>
                <td><%=b.getField()%></td>
            </tr>
            <%}%>
        </table>
    </hr>
        <a href="searchMenu.jsp">検索メニューに戻る</a>
    </body>
</html>

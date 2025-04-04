<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Управление пользователями</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Подключаем jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: url('${pageContext.request.contextPath}/uploaded_images/car2.webp') no-repeat center center fixed;
            background-size: cover;
            color: #000;
        }
        /* Шапка с заголовком и кнопкой "Назад" */
        header {
            background: #f4f4f4;
            display: flex;
            align-items: center;
            padding: 10px 20px;
            width: 100%;
            position: fixed;
            top: 0;
            z-index: 10;
        }
        .back-button {
            background-color: #444;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.3s;
            margin-right: 1cm; /* Отступ от правого края */
            margin-left: auto; /* Заставляем кнопку сдвинуться вправо */
        }
        .back-button:hover {
            background-color: #666;
        }
        header h2 {
            flex-grow: 1;
            text-align: center;
            margin: 0;
            font-size: 24px;
        }
        .container {
            margin-top: 70px;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: rgba(255,255,255,0.9);
        }
        th, td {
            padding: 12px 15px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .admin-buttons {
            display: flex;
            gap: 10px;
        }
        .admin-buttons a, .admin-buttons button {
            background-color: #222;
            color: white;
            padding: 8px 12px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .admin-buttons a:hover, .admin-buttons button:hover {
            background-color: #333;
        }
        .disabled-button {
            background-color: grey;
            cursor: not-allowed;
        }
    </style>
</head>
<body>
<header>
    <!-- Заголовок страницы, поднятый в верхнюю плитку -->
    <h2>Управление пользователями</h2>
    <!-- Кнопка "Назад" остаётся справа с отступом 1 см от правого края -->
    <a href="${pageContext.request.contextPath}/cars/" class="back-button">Назад</a>
</header>
<div class="container">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Имя пользователя</th>
            <th>Email</th>
            <th>Role ID</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr id="user-row-${user.id}">
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.roleId}</td>
                <td>
                    <div class="admin-buttons">
                        <a href="${pageContext.request.contextPath}/admin/users?action=edit&id=${user.id}">Редактировать</a>
                        <c:if test="${user.roleId != 1}">
                            <button class="delete-button" data-id="${user.id}">Удалить</button>
                        </c:if>
                        <c:if test="${user.roleId == 1}">
                            <button class="delete-button disabled-button" disabled title="Нельзя удалить администратора">Удалить</button>
                        </c:if>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>
    $(document).ready(function(){
        $(".delete-button").click(function(){
            var button = $(this);
            var userId = button.data("id");
            if(button.hasClass("disabled-button")){
                alert("Удаление администратора запрещено.");
                return;
            }
            if(confirm("Вы уверены, что хотите удалить пользователя?")){
                $.ajax({
                    url: "${pageContext.request.contextPath}/admin/users",
                    type: "POST",
                    data: { action: "delete", id: userId },
                    headers: { "X-Requested-With": "XMLHttpRequest" },
                    success: function(response) {
                        if(response.status === "success"){
                            $("#user-row-" + userId).remove();
                        } else {
                            alert(response.message || "Ошибка при удалении пользователя.");
                        }
                    },
                    error: function(){
                        alert("Ошибка при удалении пользователя.");
                    }
                });
            }
        });
    });
</script>
</body>
</html>

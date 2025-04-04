<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Редактирование пользователя</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      /* Фон в едином стиле с предыдущей страницей */
      background: url('${pageContext.request.contextPath}/uploaded_images/car2.webp') no-repeat center center fixed;
      background-size: cover;
      color: #000;
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
    }
    header {
      background: #f4f4f4;
      display: flex;
      justify-content: flex-end;
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
      margin-right: 1cm; /* Отступ от правого края на 1 см */
    }
    .back-button:hover {
      background-color: #666;
    }
    .form-container {
      background: rgba(255, 255, 255, 0.9);
      border-radius: 15px;
      box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
      padding: 30px 20px;
      width: 400px;
      text-align: center;
      margin-top: 60px;
    }
    h2 {
      margin-bottom: 20px;
      color: #333;
    }
    label {
      display: block;
      margin: 8px 0;
      font-size: 16px;
      text-align: left;
    }
    input {
      width: 90%;
      padding: 12px;
      margin: 8px 0;
      border: 1px solid #ccc;
      border-radius: 5px;
      font-size: 14px;
    }
    button {
      width: 100%;
      padding: 12px;
      background-color: #222;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      font-size: 16px;
    }
    button:hover {
      background-color: #333;
    }
  </style>
</head>
<body>
<header>
  <!-- Кнопка "Назад" возвращает к списку пользователей, расположена справа с отступом 1 см -->
  <a href="${pageContext.request.contextPath}/admin/users" class="back-button">Назад</a>
</header>
<div class="form-container">
  <h2>Редактирование пользователя</h2>
  <form action="${pageContext.request.contextPath}/admin/users" method="post">
    <input type="hidden" name="action" value="update" />
    <input type="hidden" name="id" value="${user.id}" />
    <label>Имя пользователя:</label>
    <input type="text" name="username" value="${user.username}" required/>
    <label>Email:</label>
    <input type="email" name="email" value="${user.email}" required/>
    <label>Role ID:</label>
    <input type="number" name="roleId" value="${user.roleId}" required/>
    <button type="submit">Обновить</button>
  </form>
</div>
</body>
</html>

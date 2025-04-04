<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Вход</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background: url('${pageContext.request.contextPath}/uploaded_images/car.jpg') no-repeat center center fixed;
      background-size: cover;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }
    .login-container {
      background: rgba(255, 255, 255, 0.9);
      border-radius: 15px;
      box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
      padding: 30px 20px;
      width: 350px;
      text-align: center;
    }
    h2 {
      margin-bottom: 20px;
      color: #333;
    }
    input {
      width: 90%;
      padding: 12px;
      margin: 15px 0;
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
    p {
      margin-top: 15px;
      font-size: 14px;
    }
    p a {
      color: #222;
      text-decoration: none;
    }
  </style>
</head>
<body>
<div class="login-container">
  <h2>Вход</h2>
  <form action="${pageContext.request.contextPath}/login" method="post">
    <input type="text" name="username" placeholder="Имя пользователя" required>
    <input type="password" name="password" placeholder="Пароль" required>
    <button type="submit">Войти</button>
  </form>
  <p>Нет аккаунта? <a href="${pageContext.request.contextPath}/register">Зарегистрируйтесь</a></p>
  <c:if test="${not empty errorMessage}">
    <p style="color: red;">${errorMessage}</p>
  </c:if>
</div>
</body>
</html>

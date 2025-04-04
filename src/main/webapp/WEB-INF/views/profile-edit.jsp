<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Редактирование профиля</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #111;
      color: #fff;
    }

    .background-image {
      background-image: url('${pageContext.request.contextPath}/uploaded_images/car3.webp');
      background-size: cover;
      background-position: center;
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      z-index: -1;
    }

    .top-container {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      background-color: rgba(255, 255, 255, 0.7);
      padding: 15px 0;
      z-index: 10;
      display: flex;
      justify-content: space-between;
      padding-right: 20px;
      padding-left: 20px;
    }

    .royal-cars-title {
      font-size: 32px;
      font-weight: bold;
      color: #fff;
      background: linear-gradient(45deg, #ff7e5f, #feb47b);
      -webkit-background-clip: text;
      background-clip: text;
      text-shadow: 2px 2px 10px rgba(0, 0, 0, 0.6);
      letter-spacing: 1px;
    }

    .top-buttons {
      display: flex;
      gap: 15px;
    }

    .top-buttons a {
      color: white;
      text-decoration: none;
      padding: 10px 15px;
      background-color: #222;
      border-radius: 5px;
      transition: background-color 0.3s;
    }

    .top-buttons a:hover {
      background-color: #333;
    }

    .container {
      padding: 20px;
      display: flex;
      flex-direction: column;
      align-items: center;
      margin-top: 80px;
      background-color: rgba(255, 255, 255, 0.7);
      border-radius: 10px;
      padding-top: 40px;
    }

    .back-button {
      position: absolute;
      top: 15px;
      right: 1cm;
      background-color: #222;
      color: white;
      padding: 10px 15px;
      border-radius: 5px;
      text-decoration: none;
    }

    .back-button:hover {
      background-color: #333;
    }

    .form-container {
      width: 100%;
      max-width: 400px;
      background-color: #fff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .form-container input {
      width: 100%;
      padding: 10px;
      margin: 10px 0;
      border-radius: 5px;
      border: 1px solid #ddd;
    }

    .form-container button {
      width: 100%;
      padding: 10px;
      background-color: #222;
      color: white;
      border: none;
      border-radius: 5px;
      font-size: 16px;
      cursor: pointer;
    }

    .form-container button:hover {
      background-color: #333;
    }
  </style>
</head>
<body>

<!-- Фон с изображением машины на всю страницу (фиксированный) -->
<div class="background-image"></div>

<!-- Верхняя плита с названием -->
<div class="top-container">
  <div class="royal-cars-title">
    RoyalCars
  </div>
  <div class="top-buttons">
    <!-- Кнопка назад будет справа -->
    <a href="${pageContext.request.contextPath}/cars" class="back-button">Назад</a>
  </div>
</div>

<!-- Основной контейнер для редактирования профиля -->
<div class="container">
  <h2>Редактирование профиля</h2>

  <!-- Форма для редактирования профиля -->
  <div class="form-container">
    <form action="${pageContext.request.contextPath}/profile/edit" method="post">
      <label for="username">Логин:</label>
      <input type="text" id="username" name="username" value="${user.username}" required />

      <label for="email">Email:</label>
      <input type="email" id="email" name="email" value="${user.email}" required />

      <button type="submit">Сохранить изменения</button>
    </form>
  </div>
</div>

</body>
</html>

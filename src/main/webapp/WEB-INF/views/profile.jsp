<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Личный кабинет</title>
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
      justify-content: space-between; /* Разместим элементы по краям */
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
      transition: transform 0.3s;
    }

    .top-buttons {
      display: flex;
      gap: 15px;
      padding-right: 0.5mm;
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
      background-color: rgba(255, 255, 255, 0.7); /* Полупрозрачный фон для контента */
      border-radius: 10px;
      padding-top: 40px; /* Отступ сверху, чтобы текст не прилипал */
    }

    .back-button {
      position: absolute;
      top: 15px;
      right: 1cm; /* Кнопка справа с отступом 1 см */
      background-color: #222;
      color: white;
      padding: 10px 15px;
      border-radius: 5px;
      text-decoration: none;
    }

    .back-button:hover {
      background-color: #333;
    }

    .user-info p {
      font-size: 18px;
      margin: 10px 0;
      color: #000; /* Черный цвет текста */
    }

    .orders-table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 30px;
      color: #000;
    }

    .orders-table th, .orders-table td {
      padding: 12px;
      border: 1px solid #ddd;
      text-align: left;
    }

    .orders-table th {
      background-color: #222;
      color: white;
    }

    .orders-table tr:hover {
      background-color: #f1f1f1;
    }

    .profile-buttons {
      margin-top: 20px;
    }

    .profile-buttons a {
      color: white;
      background-color: #222;
      padding: 10px 15px;
      border-radius: 5px;
      text-decoration: none;
      margin-top: 10px;
    }

    .profile-buttons a:hover {
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

<!-- Основной контейнер для личного кабинета -->
<div class="container">
  <h2>Личный кабинет</h2>

  <!-- Информация о пользователе -->
  <div class="user-info">
    <p><strong>Имя пользователя:</strong> ${user.username}</p>
    <p><strong>Email:</strong> ${user.email}</p>
    <p><strong>Роль:</strong> ${user.roleId == 1 ? 'Админ' : 'Пользователь'}</p>
  </div>

  <!-- Кнопка редактирования профиля -->
  <div class="profile-buttons">
    <a href="${pageContext.request.contextPath}/profile/edit">Редактировать профиль</a>
  </div>

  <!-- Таблица с заказами -->
  <h3>Ваши заказы</h3>
  <table class="orders-table">
    <thead>
    <tr>
      <th>Марка</th> <!-- Новый заголовок для марки -->
      <th>Модель</th>
      <th>Машина</th>
      <th>Дата заказа</th>
      <th>Статус</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${orders}">
      <tr>
        <td>${order.carMake}</td>
        <td>${order.carModel}</td>
        <td>${order.carId}</td>
        <td>${order.orderDate}</td>
        <td>${order.status}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

</body>
</html>

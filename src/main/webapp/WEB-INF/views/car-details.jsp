<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Детали автомобиля</title>
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
      padding: 15px 20px;
      z-index: 10;
      display: flex;
      align-items: center;
      justify-content: space-between;
    }

    .brand-title {
      font-size: 32px;
      font-weight: bold;
      color: #000;
    }

    .back-button {
      background-color: #444;
      color: white;
      padding: 10px 20px;
      border-radius: 5px;
      font-size: 16px;
      cursor: pointer;
      text-decoration: none;
      transition: background-color 0.3s;
      margin-right: 10mm; /* 1 см */
    }

    .back-button:hover {
      background-color: #555;
    }

    .car-details-container {
      width: 80%;
      margin: auto;
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 30px;
      position: relative;
      top: 60px;
    }

    .car-image-container {
      text-align: center;
      margin-bottom: 20px;
      padding: 20px;
      background-color: rgba(255, 255, 255, 0.5);
      border-radius: 15px;
    }

    .car-image {
      max-width: 80%;
      height: auto;
      object-fit: cover;
      border-radius: 10px;
    }

    .car-info {
      margin-top: 20px;
      text-align: center;
      color: black;
      background-color: rgba(255, 255, 255, 0.9);
      padding: 20px;
      border-radius: 10px;
    }

    .car-info h2 {
      font-size: 32px;
      margin: 10px 0;
      color: black;
    }

    .car-info p {
      font-size: 18px;
      margin: 10px 0;
      color: black;
    }

    .order-button {
      margin-top: 20px;
      background-color: #222;
      color: white;
      padding: 15px 30px;
      border-radius: 5px;
      font-size: 18px;
      cursor: pointer;
      text-decoration: none;
      transition: background-color 0.3s;
    }

    .order-button:hover {
      background-color: #333;
    }
  </style>
</head>
<body>
<div class="background-image"></div>

<!-- Верхняя панель -->
<div class="top-container">
  <div class="brand-title">RoyalCars</div>
  <a href="${pageContext.request.contextPath}/cars" class="back-button">Назад</a>
</div>

<!-- Контейнер с деталями автомобиля -->
<div class="car-details-container">
  <div class="car-image-container">
    <c:forEach items="${car.imagePaths}" var="imagePath">
      <img src="${pageContext.request.contextPath}/${imagePath}" alt="Car Image" class="car-image">
    </c:forEach>
  </div>

  <div class="car-info">
    <h2>${car.model} (${car.year})</h2>
    <p>${car.make}</p>
    <p>${car.description}</p>
    <p>Цена: ${car.price} ₽</p>

    <form action="${pageContext.request.contextPath}/orders" method="post">
      <input type="hidden" name="carId" value="${car.id}">
      <input type="hidden" name="userId" value="${sessionScope.user.id}">
      <button type="submit" class="order-button">Оформить заказ</button>
    </form>
  </div>
</div>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Ваши заказы</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    body {
      font-family: Arial, sans-serif;
      background: url('${pageContext.request.contextPath}/uploaded_images/car2.webp') no-repeat center center fixed;
      background-size: cover;
      color: #000;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      background: rgba(255, 255, 255, 0.9);
    }
    th, td {
      padding: 12px 15px;
      border-bottom: 1px solid #ddd;
      text-align: left;
    }
    tr:hover {
      background-color: #f1f1f1;
    }
  </style>
</head>
<body>
<h2>Ваши заказы</h2>

<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Машина</th>
    <th>Дата заказа</th>
    <th>Статус</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="order" items="${orders}">
    <tr>
      <td>${order.id}</td>
      <td>${order.carId}</td> <!-- В реальной ситуации нужно добавить запрос для получения информации о машине -->
      <td>${order.orderDate}</td>
      <td>${order.status}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<h3>Создать новый заказ</h3>
<form action="${pageContext.request.contextPath}/user/orders" method="post">
  <label for="carId">Выберите машину:</label>
  <input type="number" name="carId" id="carId" required>
  <button type="submit">Создать заказ</button>
</form>

</body>
</html>

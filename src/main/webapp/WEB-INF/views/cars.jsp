<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Список машин</title>
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
            justify-content: flex-end;
            padding-right: 20px;
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

        .search-box {
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
            width: 250px;
            margin-left: auto;
        }

        /* Сетка для плиток машин */
        .container {
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 80px;
        }

        .car-list {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); /* Увеличена минимальная ширина плиток */
            gap: 30px; /* Увеличено расстояние между плитками */
            width: 100%;
        }

        /* Плитка с машиной */
        .car-tile {
            background-color: rgba(255, 255, 255, 0.8);
            border-radius: 10px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
            padding: 30px;
            text-align: center;
            transition: transform 0.3s;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            height: 450px; /* Увеличена высота плитки */
            cursor: pointer;
            text-decoration: none;
            color: inherit;
            position: relative; /* Позволяет разместить кнопки внутри плитки */
        }

        .car-tile:hover {
            transform: translateY(-10px);
            box-shadow: 0 12px 24px rgba(0, 0, 0, 0.2);
        }

        .car-tile img {
            max-width: 100%;
            height: 300px; /* Увеличена высота фотографии */
            object-fit: cover;
            border-radius: 10px;
            margin-bottom: 20px;
        }

        .car-tile h3, .car-tile p {
            margin: 10px 0;
            color: #333;
        }

        /* Кнопки для админа внутри плиток */
        .admin-buttons {
            position: absolute;
            bottom: 20px;
            left: 50%;
            transform: translateX(-50%);
            display: flex;
            gap: 10px;
            width: 100%;
            justify-content: center;
        }

        .admin-buttons a, .admin-buttons form button {
            background-color: #222;
            color: white;
            height: 40px;
            font-size: 14px;
            width: 120px;
            align-items: center;
            border-radius: 5px;
            padding: 10px 15px;
            text-decoration: none;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s;
            display: inline-block;
            box-sizing: border-box;
        }

        .admin-buttons a:hover, .admin-buttons form button:hover {
            background-color: #333;
        }

        .footer-photo {
            display: none;
        }
    </style>
</head>
<body>

<!-- Фон с изображением машины на всю страницу (фиксированный) -->
<div class="background-image"></div>

<!-- Прозрачный верхний отступ с кнопками -->
<div class="top-container">
    <div style="flex: 1; font-size: 32px; font-weight: bold; color: #fff; padding-left: 20px;
                background: linear-gradient(45deg, #ff7e5f, #feb47b);
                -webkit-background-clip: text; background-clip: text;
                text-shadow: 2px 2px 10px rgba(0, 0, 0, 0.6);
                letter-spacing: 1px; transition: transform 0.3s;">
        RoyalCars
    </div>
    <div class="top-buttons">
        <input type="text" placeholder="Поиск..." class="search-box">
        <c:if test="${isAdmin}">
            <a href="${pageContext.request.contextPath}/cars/add">Добавить машину</a>
            <a href="${pageContext.request.contextPath}/admin/users">Управление пользователями</a>
        </c:if>
        <a href="${pageContext.request.contextPath}/profile">Личный кабинет</a>
        <a href="${pageContext.request.contextPath}/logout">Выход</a>
    </div>
</div>

<!-- Основной контейнер для списка машин -->
<div class="container">
    <div class="car-list">
        <c:forEach var="car" items="${cars}">
            <div class="car-tile">
                <a href="${pageContext.request.contextPath}/cars/${car.id}">
                    <c:if test="${not empty car.imagePaths}">
                        <c:forEach var="imagePath" items="${car.imagePaths}">
                            <img src="${pageContext.request.contextPath}${imagePath}" alt="${car.model}">
                        </c:forEach>
                    </c:if>
                    <h3>${car.model}</h3>
                    <p>${car.make} (${car.year})</p>
                </a>
                <c:if test="${isAdmin}">
                    <div class="admin-buttons">
                        <!-- Кнопка редактирования -->
                        <a href="${pageContext.request.contextPath}/cars/update/${car.id}">Редактировать</a>
                        <!-- Кнопка удаления -->
                        <form action="${pageContext.request.contextPath}/cars" method="post" class="delete-form">
                            <input type="hidden" name="id" value="${car.id}">
                            <input type="hidden" name="action" value="delete">
                            <button type="submit" class="delete-button" data-id="${car.id}" onclick="return false;">Удалить</button>
                        </form>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>
<script>
    $(document).ready(function() {
        $(".delete-button").on("click", function() {
            var carId = $(this).data("id");

            if (confirm("Вы уверены, что хотите удалить эту машину?")) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/cars",
                    type: "POST",
                    data: {
                        id: carId,
                        action: "delete"
                    },
                    success: function(response) {
                        $("button[data-id='" + carId + "']").closest(".car-tile").remove();
                        alert("Машина успешно удалена!");
                    },
                    error: function(xhr, status, error) {
                        alert("Ошибка при удалении машины. Попробуйте снова.");
                    }
                });
            }
        });
    });
</script>
</body>
</html>

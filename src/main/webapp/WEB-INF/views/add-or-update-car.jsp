<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${car != null ? 'Обновить машину' : 'Добавить машину'}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: url('${pageContext.request.contextPath}/uploaded_images/car2.webp') no-repeat center center fixed;
            background-size: cover;
            color: #000; /* Черный цвет текста */
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }

        /* Верхняя панель с кнопкой "Назад" */
        header {
            background: #f4f4f4; /* Белый или светло-серый цвет */
            display: flex;
            justify-content: flex-end; /* Кнопка будет справа */
            align-items: center;
            padding: 10px 20px; /* Уменьшил высоту панели */
            width: 100%;
            z-index: 10;
            position: fixed;
            top: 0;
        }

        .back-button {
            background-color: #444;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s;
            margin-right: 1cm; /* Переместил на 1 см от правого края */
        }

        .back-button:hover {
            background-color: #666;
        }

        .form-container {
            background: rgba(255, 255, 255, 0.9); /* Прозрачный белый фон */
            border-radius: 15px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
            padding: 30px 20px;
            width: 400px;
            text-align: center;
            z-index: 5;
        }

        h2 {
            margin-bottom: 20px;
            color: #333;
        }

        label {
            text-align: left;
            width: 100%;
            margin: 8px 0;
            font-size: 16px;
            display: block;
        }

        input, textarea, select {
            width: 90%;
            padding: 12px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        /* Запрещаем растягивание поля textarea */
        textarea {
            resize: none;
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

        .file-input-container {
            text-align: left;
            margin-top: 15px;
        }
    </style>
</head>
<body>
<!-- Верхняя панель с кнопкой "Назад" -->
<header>
    <a href="${pageContext.request.contextPath}/cars" class="back-button">Назад</a>
</header>

<!-- Форма добавления машины -->
<div class="form-container">
    <h2>${car != null ? 'Обновить машину' : 'Добавить машину'}</h2>
    <form action="${pageContext.request.contextPath}/cars" method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="${car != null ? 'update' : 'add'}">

        <!-- Для обновления ID передается, для добавления его нет -->

        <input type="hidden" name="id" value="${car != null ? car.id : ''}">


        <label for="model">Модель:</label>
        <input type="text" name="model" id="model" value="${car != null ? car.model : ''}" required><br>

        <label for="make">Марка:</label>
        <input type="text" name="make" id="make" value="${car != null ? car.make : ''}" required><br>

        <label for="description">Описание:</label>
        <textarea name="description" id="description" required>${car != null ? car.description : ''}</textarea><br>

        <label for="price">Цена:</label>
        <input type="number" name="price" id="price" value="${car != null ? car.price : ''}" required><br>

        <label for="year">Год:</label>
        <input type="number" name="year" id="year" value="${car != null ? car.year : ''}" required><br>

        <c:if test="${car == null}">
            <div class="file-input-container">
                <label for="image">Изображения:</label>
                <input type="file" name="image" id="image" multiple><br>
            </div>
        </c:if>

        <button type="submit">${car != null ? 'Обновить' : 'Добавить'}</button>
    </form>
</div>
</body>
</html>


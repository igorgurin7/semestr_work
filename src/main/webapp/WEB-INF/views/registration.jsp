<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <style>

        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: url('${pageContext.request.contextPath}/uploaded_images/car.jpg') no-repeat center center fixed;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #333;
        }


        .form-container {
            background: rgba(255, 255, 255, 0.9);
            border-radius: 15px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
            padding: 30px 20px;
            width: 350px;
            text-align: center;
        }


        h2 {
            color: #333;
            margin-bottom: 20px;
            font-size: 24px;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 90%;
            padding: 12px;
            margin: 15px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
            box-sizing: border-box;
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
            transition: background-color 0.3s ease;
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

        p a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Регистрация</h2>
    <form action="${pageContext.request.contextPath}/register" method="post">
        <div>
            <input type="text" id="username" name="username" placeholder="Имя пользователя" required>
        </div>
        <div>
            <input type="email" id="email" name="email" placeholder="Электронная почта" required>
        </div>
        <div>
            <input type="password" id="password" name="password" placeholder="Пароль" required>
        </div>
        <button type="submit">Зарегистрироваться</button>
    </form>
    <p>Уже зарегистрированы? <a href="${pageContext.request.contextPath}/login">Войти</a></p>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ошибка</title>
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
            color: #333;
        }

        .error-container {
            background: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            padding: 40px 30px;
            width: 400px;
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
            color: #e74c3c;
        }

        p {
            margin: 20px 0;
            color: #e74c3c;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: #222;
            background-color: #fff;
            padding: 10px 20px;
            border-radius: 5px;
            border: 1px solid #222;
        }

        a:hover {
            background-color: #222;
            color: white;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h2>Произошла ошибка</h2>
    <p>${errorMessage}</p>
    <a href="${pageContext.request.contextPath}/register">Попробовать снова</a>
</div>
</body>
</html>

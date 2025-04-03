<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>SignUp</title>
</head>
<body>
<h1>Sign Up Page</h1>
<h2>Please Enter your data:</h2>
<form action="/signUp" method="post">
  <label for="firstName"> Enter first name:</label>
  <input id="firstName" name="firstName" placeholder="Your first name">
  <br>
  <label for="lastName"> Enter last name: </label>
  <input id="lastName" name="lastName" placeholder="Your last name">
  <br>
  <label for="email"> Enter email:</label>
  <input type="email" id="email" name="email" placeholder="Your email">
  <br>
  <label for="password"> Enter password: </label>
  <input type="password" id="password" name="password" placeholder="Your password">
  <input type="submit" value="Sign Up">
</form>
</body>
</html>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="View.css">
</head>
<body>
<div class="juu">
    BLACKSCHOOL MANAGEMENT SYSTEM
</div>
<div class="kushoto">

</div>
<div class="main">
    <h2>
        Register
    </h2>
        <form method="POST" action="">
            <div class="label">Student's First Name:</div>
            <input type="text" name="jina1" placeholder="Student's First name" required><br>
            <div class="label">Student's Last Name:</div>
            <input type="text" name="jina2" placeholder="Student's Last name" required><br>
            <div class="label">Enter User ID:</div>
            <input type="text" name="id" placeholder="Student's User ID" required><br>
            <div class="label">Email:</div>
            <input type="email" name="email" placeholder="Student's email" required><br>
            <div class="label">Gender:</div>
            <input type="radio" name="gender"required>Male<input type="radio" name="gender"required>Female<br>
            <div class="label">Password:</div>
            <input type="password" name="password" placeholder="Password" required><br>
            <div class="label">Confirm Password:</div>
            <input type="password" name="cpassword" placeholder="Confirm Password" required><br><br>
            <input type="submit" name="submit" class="login" value="Register">
        </form>
        <br>
        Have you been registered yet? <a href="Login.jsp">Login Now</a>
</div>
</body>
</html>
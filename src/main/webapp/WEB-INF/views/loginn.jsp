<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="View.css">
    <script type="text/javascript">
        function link() {
            location.href="Courses.jsp";
        }
    </script>
</head>
<body>
<div class="juu">
    BLACKSCHOOL MANAGEMENT SYSTEM
</div>
<div class="kushoto">

</div>
<div class="main">
    <h3>Login</h3>
    <form>
        <div class="label">User ID:</div>
        <input type="text" name="ID" placeholder="User ID" required><br><br>
        <div class="label">Password:</div>
        <input name="password" type="password" placeholder="Password" required><br><br>
        <button name="submit" type="submit"  class="login"><a href="Courses.jsp">Login</a></button>
    </form>
</div>
</body>
</html>
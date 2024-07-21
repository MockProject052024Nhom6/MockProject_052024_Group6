<?php
    // code ở đây
?>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="{{ asset('template/css/Login.css') }}">
</head>

<body>
    <div class="container">
        <div class="Login">
            <h1>Hello there</h1>
            <p>Sign In now for great values at <span>GovDeals.</span></p>
            <input type="text" name="username" placeholder="Username" id="username"> <br />
            <input type="password" name="password" placeholder="Password" id="password"> <br />
            <input type="checkbox" name="checkbox" id="checkbox"> Stay Signed In <br />
            <button class="signin">Sign In</button>
            <p class="forgot">Forgot your <a href="#">username</a> or <a href="#">password</a>?</p>
            <h2>Don’t have an account?</h2>
            <button class="register">Register</button>
        </div>
    </div>
</body>

</html>
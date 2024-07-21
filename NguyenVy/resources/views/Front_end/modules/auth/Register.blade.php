<?php


?>


<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<style>
    body {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
        background-color: #f0f0f0;
    }

    .container {
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        max-width: 400px;
        width: 100%;
    }

    .register input {
        border: none;
        outline: none;
        margin-top: 5px;
        margin-bottom: 15px;
        padding: 10px;
        width: calc(100% - 20px);
        border-radius: 5px;
    }

    .register label {
        display: block;
        margin-top: 10px;
        font-weight: bold;
    }

    .register button {
        background-color: #007bff;
        color: white;
        padding: 10px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        width: 100%;
    }

    .register button:hover {
        background-color: #0056b3;
    }

    .checkbox {
        margin-left: -1px;
    }

    .checkbox-container {
        display: flex;
        align-items: center;
        margin-bottom: 15px;
    }
    input.checkbox{
        width: auto;
        height: auto;
    }
    .checkbox-container input {
        margin-top: 21px;
    }
</style>

<body>
    <!-- header -->
    <div class="container">
        <div class="register">
            <div class="title">
                <h1>Sign up an account</h1>
            </div>
            <!-- form register  -->
            <form action="" method="post">
                @csrf
                <label for="">Username</label> </br>
                <input type="text" id="name" name="name"> </br>
                <label for="">Email</label> </br>
                <input type="email" id="email" name="email"> </br>
                <label for="">Password</label> </br>
                <input type="password" id="password" name="password"> </br>
                <label for="">Confirm Password</label> </br>
                <input type="password" id="password" name="confirm_password"> </br>
                <div class="checkbox-container">
                    <input type="checkbox" class="checkbox" name="checkbox">
                    <label for="checkbox">I agree to the user agreement</label>
                </div>
                <button type="submit">Register</button>
            </form>
        </div>
    </div>
</body>

</html>
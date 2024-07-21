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
        align-items: center;
        justify-content: center;
    }

    .reset {
        height: 300px;
        width: 350px;
        /* background-color: red; */
        padding: 20px;
        border: 1px solid black
    }

    h1 {
        text-align: center;

    }

    input {
        width: 100%;
        margin-top: 10px;
        margin-bottom: 20px;
        font-size: 25px;
    }

    .btn_reset {
        text-align: center;
    }

    .btn_reset button {
        padding: 10px 20px;
        border-radius: 12px;
        border: none;
        transition: 0.5s;
    }

    .btn_reset button:hover {
        cursor: pointer;
        background-color: #0056b3;
        color: white;
    }
</style>

<body>
    <div class="container">
        <div class="reset">
            <h1>Reset Password</h1>
            <form action="">
                <label for="">New password</label> </br>
                <input type="password" name="password"> </br>
                <label for="">Comfirm password</label> </br>
                <input type="password" name="comfirm_password">
            </form>
            <div class="btn_reset">
                <button type="submit">Reset</button>
            </div>
        </div>
    </div>
</body>

</html>
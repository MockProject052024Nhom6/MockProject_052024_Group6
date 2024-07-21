<?php ?>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<style>
    body{
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .forgot{
        width: 350px;
        height: 300px;
        /* background-color: red; */
        text-align: center;
        padding: 20px;
        border: 1px solid black;
    }
    input{
        width: 100%;
        margin-bottom: 15px;
        font-size: 25px;
    }
    button{
        padding: 10px 20px;
        border-radius: 12px;
        border: none;
        transition: 0.5s;
    }
    button:hover{
        cursor: pointer;
        background-color: #0056b3;
        color: white;
    }
</style>
<body>
    <div class="container">
        <div class="forgot">
            <h1>Forgot Password</h1>
            <p>Enter your email address and we'll resend you a link to reset your password</p>
            <input type="email" name="email" class="email">
            <div>
                <button type="submit">Submit</button>
            </div>
        </div> 
    </div>
    <script>
        const email = document.querySelector('.email');
        const button = document.querySelector('button');
        button.addEventListener('click', function(){
            console.log(email.value);
        })    
    </script>
</body>
</html>
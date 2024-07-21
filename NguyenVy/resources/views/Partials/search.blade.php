<?php

?>

<style>
    .search{
        text-align: center;
    }
    .content_search{
        display: flex;
        align-items: center;    
        justify-content: center;
        position: relative;
    }
    .input_2 input{
        padding-left: 25px;
        padding-top: 5px;
    }
    .input_1 input{
        padding-left: 25px;
        padding-top: 5px;
    }
    .input_1 svg{
        position: absolute;
        left: 440px;
        top:4px;
    }
    .input_2 svg{
        position: absolute;
        top:4px;
        right: 693px;
    }
    .btn_search button{
        padding: 5px 10px;
        margin-left: 10px;
        border-radius: 10px;
        border: 1px solid black;
        transition: 0.25s;
    }
    .btn_search button:hover{
        cursor: pointer;
        background-color: #0056b3;
        color: white;
    }
</style>
<div class="search">
    <div class="search_all_contents">
        <h1>Search all contents</h1>
    </div>
    <div class="content_search">
        <div class="input_1">
            <input type="text">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-geo-alt-fill" viewBox="0 0 16 16">
                <path d="M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10m0-7a3 3 0 1 1 0-6 3 3 0 0 1 0 6" />
            </svg>
        </div>
        <div class="input_2">
            <input type="text">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
            </svg>
        </div>
        <div class="btn_search">
            <button type="submit">Search</button>
        </div>
    </div>
</div>
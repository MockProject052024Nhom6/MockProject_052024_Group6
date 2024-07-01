<?php 
    session_start();
    require_once('config.php');

    $module = _MODULE;
    $action = _ACTION;

    // kiểm tra module có tồn tại hay không 
    if(!empty($_GET['module']))
    {
        if(is_string($_GET['module']))
        {
            $module = trim($_GET['module']);
        }
    }
    // kiểm tra action có tồn tại hay không 
    if(!empty($_GET['action']))
    {
        if(is_string($_GET['action']))
        {
            $action = trim($_GET['action']);
        }
    }

    // tạo đường dẫn path 
    $path = 'modules/'. $module. '/'. $action. '.php';
    // kiểm tra file có tồn tại không 
    if(file_exists($path))
    {
        require_once($path);
    }
    else 
    {
        require_once('modules/error/404.php');
    }
?>
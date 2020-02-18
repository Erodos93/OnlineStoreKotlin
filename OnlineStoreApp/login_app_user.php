<?php

$connection= new mysqli("localhost","root","","online_store_db");
$check_where_user = $connection->prepare("select* from app_users where email=? AND pass=?");
$check_where_user->bind_param("ss",$_GET["email"],$_GET["pass"]);
$check_where_user->execute();

$login_resul=$check_where_user->get_result();

if($login_resul->num_rows==0){
    echo 'The user doesnt exist';
}else{
    echo 'The user does exist';
}


<?php

$connection = new mysqli("localhost","root","","online_store_db");
$emailCheckSQLCommand = $connection->prepare("select *from app_users where email=?");
$emailCheckSQLCommand ->bind_param("s",$_GET["email"]);
$emailCheckSQLCommand ->execute();
$emailResult=$emailCheckSQLCommand->get_result();

if($emailResult->num_rows==0){
    $sqlCommand=$connection->prepare("insert into app_users values(?,?,?)");
    $sqlCommand->bind_param("sss",$_GET["email"],$_GET["name"],$_GET["pass"]);
    $sqlCommand->execute();
    echo 'Congratulation!You are registrated';
}else{
    echo 'A user with this email Address already exist.';
}
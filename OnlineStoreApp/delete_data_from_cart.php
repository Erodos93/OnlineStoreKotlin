<?php

$connection =new mysqli("localhost", "root", "", "online_store_db");
$deleteCommand=$connection->prepare("delete from temporary_place_order where email=?");
$deleteCommand->bind_param("s",$_GET["email"]);
$deleteCommand->execute();

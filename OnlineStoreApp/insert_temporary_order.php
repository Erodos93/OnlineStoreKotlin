<?php

$connection = new mysqli("localhost", "root", "", "online_store_db");
$insertTemporaryOrder = $connection ->prepare("insert into temporary_place_order values(?,?,?)");
$insertTemporaryOrder->bind_param("sii", $_GET["email"],$_GET["product_id"],$_GET["amount"]);
$insertTemporaryOrder->execute();
        

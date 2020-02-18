<?php

$connection = new mysqli("localhost","root","","online_store_db");
$brandEproduct = $connection -> prepare("select * from electronic_products where brand=?");
$brandEproduct->bind_param("s", $_GET["brand"]);
$brandEproduct->execute();
$eproductResult=$brandEproduct->get_result();
$eProductArray = Array();

while ($rows=$eproductResult->fetch_assoc()){
    array_push($eProductArray,$rows);
}
echo json_encode($eProductArray);
        

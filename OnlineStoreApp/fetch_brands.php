<?php

$connection = new mysqli("localhost","root","","online_store_db");
$selectBrandCommand = $connection->prepare("select distinct brand from electronic_products");
$selectBrandCommand->execute();

$brandResult=$selectBrandCommand->get_result();
$brand=array();

while($row=$brandResult->fetch_assoc()){
    array_push($brand,$row);
}
echo json_encode($brand);

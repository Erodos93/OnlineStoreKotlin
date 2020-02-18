<?php

$connection = new mysqli("localhost", "root", "", "online_store_db");

$fench_T_order = $connection->prepare("SELECT id,name,price,email,amount from temporary_place_order INNER JOIN electronic_products ON temporary_place_order.product_id = electronic_products.id where email=?");
$fench_T_order->bind_param("s", $_GET["email"]);
$fench_T_order->execute();
$fetchArray = array();
$fenchResult = $fench_T_order->get_result();

while ($frow=$fenchResult->fetch_assoc()){
    array_push($fetchArray,$frow);
}
echo json_encode($fetchArray);
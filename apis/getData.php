<?php
include_once('config.php');
if($_SERVER['REQUEST_METHOD'] == "GET"){
    $stmt = $conn->prepare("SELECT * FROM `data`");
    $stmt->execute();
    $result = $stmt->get_result();
    $outp = $result->fetch_all();

    $data = json_encode($outp);
    return $data;
}
@mysqli_close($conn);
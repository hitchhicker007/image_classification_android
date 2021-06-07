<?php
include_once('config.php');
if($_SERVER['REQUEST_METHOD'] == "POST"){
	// Get data from the REST client
    if(isset($_POST['result']) && isset($_POST['flag']) && isset($_POST['correct_value'])){
        $result = $_POST['result'];
        $flag = $_POST['flag'];
        $correct_value = $_POST['correct_value'];
        $timestamp = date('m/d/Y h:i:s a', time());
        // Insert data into database
        $sql = "INSERT INTO `data` (`id`, `result`, `is_correct`, `correct_value`, `timestamp`) VALUES (NULL, '$result', '$flag', '$correct_value', '$timestamp');";
        $post_data_query = mysqli_query($conn, $sql);
        if($post_data_query){
            $data = array('message' => 'data added successfully!');
        }
        else{
            $data = array('message' => 'error!');
        }
    }else{
        $data = array('message' => 'api is not collecting data');
    }
}
else{
    $data = array('message' => 'Request method not accepted!');
}
return json_encode($data);
@mysqli_close($conn);
<?php
	$con = mysqli_connect('localhost:142', 'root', 'root1357', 'buddy');

	$id = $_POST["id"];
	$pwd = $_POST["pwd"];
	$name = $_POST["name"];
	$email = $_POST["email"];

	$statement = mysqli_prepare($con, "SELECT id FROM USER WHERE id = ? AND pwd = ?");
	mysqli_stmt_bind_param($statement, "ss", $id, $pwd);
	mysqli_stmt_execute("$statement)'
	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement, $id);

	$response = array();
	$response["success"] = false;

	while(mysqli_stmt_fetch($statement)){
		$response["success"] = true;
		$response["id"] = $id;
	}

	echo json_encode($response);
?>
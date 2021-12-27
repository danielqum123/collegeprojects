<?php
session_start();
header('location:login.php');
$con = mysqli_connect('localhost', 'root', '');
mysqli_select_db($con, 'webproject');
$name = $_POST['user'];
$pass = $_POST['password'];

$s = "select * from login where username = '$name'";
$result = mysqli_query($con, $s);
$num = mysqli_num_rows($result);

if ($num == 1) {
    echo "Username Already Taken";
} else {
    $reg = "Insert into login(username, password) values ('$name', '$pass')";
    mysqli_query($con, $reg);
    echo "Registration Successful";
}
?>

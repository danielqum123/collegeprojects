<?php
session_start();
if (!isset($_SESSION['username'])) {
    header('location:login.php');
}
?>
<html>

<head>
    <title>Home Page</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
    crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <a class="float-end" href="login.php">Log out</a>
        <h1>Welcome <?php echo $_SESSION['username']; ?></h1>
    </div>
</body>

</html>
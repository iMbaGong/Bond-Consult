<?php

//连接数据库
$dbhost = '108.61.223.76';
$dbuser = 'root';
$dbpass = 'baozhu03210321';
$conn = mysqli_connect($dbhost, $dbuser, $dbpass);
if(! $conn )
{
    exit('Could not connect: ' . mysqli_error());
}

header('Content-type: application/json;charset=utf-8');

//获取数据
$arr['name']=$usr_name = $_POST['name'];

///////获取图片信息/////////
// $file_path = $_FILES['avatar']['tmp_name'];
// $usr_avatar = mysqli_real_escape_string(file_get_contents($file_path));
////选择数据库///////
mysqli_select_db($conn,'BondConsult');


$ifExit = "SELECT usr_name FROM user_data WHERE usr_name='$usr_name'";
$res = mysqli_query( $conn, $ifExit);
if(!$res)
{
  $arr['state']="failed";
  //exit('name is already exit'. mysqli_error($conn));
}
while($row = mysqli_fetch_assoc($res)){
  $arr['state']="exist";
  exit(json_encode($arr));
}

$arr['psw']=$usr_psw = $_POST['psw'];
$usr_avatar = $_POST['avatar'];
$arr['state']="success";
$sql = "INSERT INTO user_data (usr_name,usr_psw,usr_avatar) VALUES ('$usr_name','$usr_psw','$usr_avatar')";
$retval = mysqli_query( $conn, $sql); 
if(! $retval )
{
  $arr['state']="failed";
  //exit('无法插入数据: ' . mysqli_error($conn));
}
mysqli_close($conn);


exit(json_encode($arr));
?>

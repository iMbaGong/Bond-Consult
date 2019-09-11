<?php
$dbhost = '108.61.223.76';
$dbuser = 'root';
$dbpass = 'baozhu03210321';
$conn = mysqli_connect($dbhost, $dbuser, $dbpass);
if(! $conn )
{
    exit('Could not connect: ' . mysqli_error());
}

header('Content-type: application/json;charset=utf-8');

$arr['name']=$usr_name = $_POST['name'];
$usr_psw = $_POST['psw'];
$arr['id']='0';
mysqli_select_db( $conn, 'BondConsult');
$ifExit = "SELECT * FROM user_data WHERE usr_name='$usr_name'";
$res = mysqli_query( $conn, $ifExit);
if($res)
{
    // $arr['name']='find it';
    // exit(json_encode($arr));
    while($row = mysqli_fetch_assoc($res))
    {
        // $arr['name']=$row['usr_psw'];
        // exit(json_encode($arr));
        if($row['usr_psw']==$usr_psw){
            $arr['id']=$row['id'];
            $arr['avatar']=$row['usr_avatar'];
            exit(json_encode($arr));
        }
    }
  //exit('name is already exit'. mysqli_error($conn));
}
$arr['id']='-1';
exit(json_encode($arr));
?>
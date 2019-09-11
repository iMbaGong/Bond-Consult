<?php
$dbhost = '108.61.223.76';
$dbuser = 'root';
$dbpass = 'baozhu03210321';
$conn = mysqli_connect($dbhost, $dbuser, $dbpass);
if(! $conn )
{
    echo 'Could not connect: ';
    exit('Could not connect: ' . mysqli_error());
}
 

 

 

 
// $arr['count']=$count;
// $arr['name']=$_POST['name'];
// $arr['avatar']=$_FILES['avatar']['name'];

exit("nothing");
// foreach($_FILES as $key => $value){
//   //循环遍历数据
//   $tmp = $value['name'];//获取上传文件名
//   $tmpName = $value['tmp_name'];//临时文件路径
//   //上传的文件会被保存到php临时目录，调用函数将文件复制到指定目录
//   if(move_uploaded_file($tmpName,$dirPath.date('YmdHis').'_'.$tmp)){
//     $success++;
//   }else{
//     $failure++;
//   }
// }
?>

<?php
$curl = curl_init();

curl_setopt_array($curl, array(
  CURLOPT_URL => "https://sandbox.apihub.citi.com/gcb/api/v2/accounts/details",
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => "",
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 30,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => "GET",
  CURLOPT_HTTPHEADER => array(
    "accept: application/json",
    "authorization: Bearer AAIkMzkwNmRkNmQtNTM0Yi00ZDIwLTgxZDctMGU3ODg0ODAxM2EzvvlOKFHcofokMXjuAswI0iadJAwmK4OsCHRoBWS_Vat48UUNFa6QzZpJjNcekbf1UOcaztVBy5BLq4i3ND9FgfbIX6FZTsp042a-XdwfZmjVm03630xozCEafblZwb2WDl3HvRct0BWUk9cYvTlEOKTFfHelGGp0qmtTr_J8iM02OqPJEcznhlVB1rrHAEPPWOhtMrZ6oPfFe85Puu9WbQ",
    "client_id: 3906dd6d-534b-4d20-81d7-0e78848013a3",
    "uuid: 33504145-57c3-476f-33f3-5d1a0a65adc6"
  ),
));

$response = curl_exec($curl);
$err = curl_error($curl);

curl_close($curl);

if ($err) {
  echo "cURL Error #:" . $err;
} else {
  echo $response;
}
?>
#!/usr/bin/php -q
<?php

$host = "https://cse-01.onetransport.uk.net";
$path = "/ONETCSE01/Worldsensing/BitCarrier/v1.0/InterdigitalDemo/silverstone/config/sketches/s";
$sketches = array(1,2,3,4,5,7,8,9,10,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,31,32,33,34,
                  35,37,38,39,40,41,42,43,44,45,46);

$opts = array(
       'http' => array(
                'method' => 'GET',
                'header' => 'Authorization: Basic '
                           . base64_encode('pthomas:EKFYGUCC') . "\r\n"
                           ."X-M2M-Origin: C-pthomas-php\r\n"
                           ."X-M2M-RI: 123456\r\n"
                           ."Accept: application/json\r\n"));

$context = stream_context_create($opts);

echo "DROP TABLE IF EXISTS config_sketch;\n";
echo "CREATE TABLE IF NOT EXISTS config_sketch (sid INTEGER PRIMARY KEY, vid INTEGER, json TEXT);\n";

foreach ($sketches as $sketch) {
  $file = file_get_contents("$host$path$sketch?rcn=6", false, $context);
  $json = json_decode($file, true);
  $cis = $json['m2m:cnt']['ch'];
  $ci_name = $cis[count($cis)-1]['-nm'];

  $ci = file_get_contents("$host$path$sketch/$ci_name", false, $context);
  $json = json_decode($ci, true);
  $json = json_decode($json['m2m:cin']['con'], true);
  echo 'INSERT INTO config_sketch values (', $json['sid'], ',', $json['vid'], ",'", $json['json'], "');\n";
}

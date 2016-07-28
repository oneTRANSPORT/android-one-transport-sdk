#!/usr/bin/php -q
<?php

set_time_limit(0);

$host = "https://cse-01.onetransport.uk.net";
$path = "/ONETCSE01/Worldsensing/BitCarrier/v1.0/InterdigitalDemo/silverstone/data/sketches/s";
$sketches = array(1,2,4,3,5,7,8,9,10,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,31,32,33,34,35,37,38,39,40,41,
                  42,43,44,45,46);

$opts = array(
       'http' => array(
                'method' => 'GET',
                'header' => 'Authorization: Basic '
                           . base64_encode('pthomas:EKFYGUCC') . "\r\n"
                           ."X-M2M-Origin: C-pthomas-php\r\n"
                           ."X-M2M-RI: 123456\r\n"
                           ."Accept: application/json\r\n"));

$context = stream_context_create($opts);

foreach ($sketches as $sketch) {
  $file = file_get_contents("$host$path$sketch?rcn=6", false, $context);
  $json = json_decode($file, true);
  $cis = $json['m2m:cnt']['ch'];
  foreach ($cis as $ci_bit) {
    echo $host, $ci_bit['#text'], "\n";
  }
}

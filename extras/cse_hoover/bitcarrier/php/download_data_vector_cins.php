#!/usr/bin/php -q
<?php

set_time_limit(0);

$host = "https://cse-01.onetransport.uk.net";
$path = "/ONETCSE01/Worldsensing/BitCarrier/v1.0/InterdigitalDemo/silverstone/data/vectors/v";
$vectors = array(276,277,278,279,280,282,283,284,285,288,289,290,291,292,293,294,295,296,297,298,299,300,301,302,303,304,
                 308,310,311,312,313,315,316,317,318,319,320,321,322,346,347);

$opts = array(
       'http' => array(
                'method' => 'GET',
                'header' => 'Authorization: Basic '
                           . base64_encode('pthomas:EKFYGUCC') . "\r\n"
                           ."X-M2M-Origin: C-pthomas-php\r\n"
                           ."X-M2M-RI: 123456\r\n"
                           ."Accept: application/json\r\n"));

$context = stream_context_create($opts);

foreach ($vectors as $vector) {
  $file = file_get_contents("$host$path$vector?rcn=6", false, $context);
  $json = json_decode($file, true);
  $cis = $json['m2m:cnt']['ch'];
  foreach ($cis as $ci_bit) {
    echo $host, $ci_bit['#text'], "\n";
  }
}

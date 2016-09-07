#!/usr/bin/php -q
<?php

set_time_limit(0);

$host = "https://cse-01.onetransport.uk.net";
$path = "/ONETCSE01/Worldsensing/BitCarrier/v1.0/InterdigitalDemo/silverstone/data/vectors";

$opts = array(
       'http' => array(
                'method' => 'GET',
                'header' => 'Authorization: Basic '
                           . base64_encode('pthomas:EKFYGUCC') . "\r\n"
                           ."X-M2M-Origin: C-pthomas-php\r\n"
                           ."X-M2M-RI: 123456\r\n"
                           ."Accept: application/json\r\n"));

$context = stream_context_create($opts);
$old_hash = make_hash(file('vector_cins_old.txt'));

$file  = file_get_contents("$host$path?rcn=6", false, $context);
$json  = json_decode($file, true);
$vectors = $json['m2m:cnt']['ch'];

foreach ($vectors as $vector) {
  $vector_name = $vector['-nm'];
  $file = file_get_contents("$host$path/$vector_name?rcn=6", false, $context);
  $json = json_decode($file, true);
  $cis = $json['m2m:cnt']['ch'];

  foreach ($cis as $ci) {
    $ci_name   = $ci['-nm'];
    if (!$old_hash[$ci_name]) {
      echo "$host$path/$vector_name/$ci_name\n";
    }
  }
}

function make_hash($lines) {
  foreach ($lines as $line) {
    $hash[trim($line)] = true;
  }
  return $hash;
}

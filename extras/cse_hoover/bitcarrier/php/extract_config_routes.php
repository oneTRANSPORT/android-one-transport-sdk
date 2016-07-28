#!/usr/bin/php -q
<?php

$host = "https://cse-01.onetransport.uk.net";
$path = "/ONETCSE01/Worldsensing/BitCarrier/v1.0/InterdigitalDemo/silverstone/config/routes/r";
$routes = array(133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,
                154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,
                175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,
                196,197,198,199,200,201,202,203,204,205,206,207,208,209,210,211,212,213,214,215);

$opts = array(
       'http' => array(
                'method' => 'GET',
                'header' => 'Authorization: Basic '
                           . base64_encode('pthomas:EKFYGUCC') . "\r\n"
                           ."X-M2M-Origin: C-pthomas-php\r\n"
                           ."X-M2M-RI: 123456\r\n"
                           ."Accept: application/json\r\n"));

$context = stream_context_create($opts);

echo "DROP TABLE IF EXISTS config_route;\n";
echo "CREATE TABLE IF NOT EXISTS config_route (id INTEGER PRIMARY KEY, customer_name TEXT, ",
                                              "metavector INTEGER);\n";

foreach ($routes as $route) {
  $file = file_get_contents("$host$path$route?rcn=6", false, $context);
  $json = json_decode($file, true);
  $cis = $json['m2m:cnt']['ch'];
  $ci_name = $cis[count($cis)-1]['-nm'];

  $ci = file_get_contents("$host$path$route/$ci_name", false, $context);
  $json = json_decode($ci, true);
  $json = json_decode($json['m2m:cin']['con'], true);
  echo 'INSERT INTO config_route values (', $json['id'], ',"', $json['customer_name'], '",',
                                            $json['metavector'], ");\n";
}

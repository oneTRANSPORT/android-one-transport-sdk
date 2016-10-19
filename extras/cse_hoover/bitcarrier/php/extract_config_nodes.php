#!/usr/bin/php -q
<?php
/* Copyright 2016 InterDigital Communications, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

$host = "https://cse-01.onetransport.uk.net";
$path = "/ONETCSE01/Worldsensing/BitCarrier/v1.0/InterdigitalDemo/silverstone/config/nodes/n";
$nodes = array(1159, 1160, 1161, 1162, 1163, 1164, 1165, 1166, 1167, 1168, 1169,
               1170, 1171, 1172);

$opts = array(
       'http' => array(
                'method' => 'GET',
                'header' => 'Authorization: Basic '
                           . base64_encode('pthomas:EKFYGUCC') . "\r\n"
                           ."X-M2M-Origin: C-pthomas-php\r\n"
                           ."X-M2M-RI: 123456\r\n"
                           ."Accept: application/json\r\n"));

$context = stream_context_create($opts);

echo "DROP TABLE IF EXISTS config_node;\n";
echo "CREATE TABLE IF NOT EXISTS config_node (id INTEGER PRIMARY KEY, customer_name TEXT, lat REAL, lon REAL);\n";

foreach ($nodes as $node){
  $file = file_get_contents("$host$path$node?rcn=6", false, $context);
  $json = json_decode($file, true);
  $cis = $json['m2m:cnt']['ch'];
  $ci_name = $cis[count($cis)-1]['-nm'];

  $ci = file_get_contents("$host$path$node/$ci_name", false, $context);
  $json = json_decode($ci, true);
  $json = json_decode($json['m2m:cin']['con'], true);
  echo 'INSERT INTO config_node values (', $json['id'], ',"', $json['customer_name'], '",', $json['lat'], ',', $json['lon'], ");\n";
}

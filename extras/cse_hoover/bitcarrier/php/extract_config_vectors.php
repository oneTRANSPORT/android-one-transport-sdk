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
$path = "/ONETCSE01/Worldsensing/BitCarrier/v1.0/InterdigitalDemo/silverstone/config/vectors/v";
$vectors = array(276,277,278,279,280,282,283,284,285,288,289,290,291,292,293,294,295,296,297,298,299,300,301,302,303,304,
                 307,308,310,311,312,313,315,316,317,318,319,320,321,322,323,324,325,326,327,328,329,330,331,332,333,334,
                 335,336,337,338,339,340,341,342,343,344,345,346,347,348,349,350,351,352,353,354,355,356,357,358,359,360,
                 361,362,363,364,365);

$opts = array(
       'http' => array(
                'method' => 'GET',
                'header' => 'Authorization: Basic '
                           . base64_encode('pthomas:EKFYGUCC') . "\r\n"
                           ."X-M2M-Origin: C-pthomas-php\r\n"
                           ."X-M2M-RI: 123456\r\n"
                           ."Accept: application/json\r\n"));

$context = stream_context_create($opts);

echo "DROP TABLE IF EXISTS config_vector;\n";
echo "CREATE TABLE IF NOT EXISTS config_vector (id INTEGER PRIMARY KEY, customer_name TEXT, distance INTEGER, ",
     "node_from INTEGER, node_to INTEGER, sid INTEGER, name TEXT);\n";

foreach ($vectors as $vector){
  $file = file_get_contents("$host$path$vector?rcn=6", false, $context);
  $json = json_decode($file, true);
  $cis = $json['m2m:cnt']['ch'];
  $ci_name = $cis[count($cis)-1]['-nm'];

  $ci = file_get_contents("$host$path$vector/$ci_name", false, $context);
  $json = json_decode($ci, true);
  $json = json_decode($json['m2m:cin']['con'], true);
  $sid = $json['sid'];
  if ($sid == '') {
    $sid = '0';
  }
  echo 'INSERT INTO config_vector values (', $json['id'], ',"', $json['customer_name'], '",', $json['distance'], ',',
        $json['from'], ',', $json['to'], ',', $sid, ',"', $json['name'], '"', ");\n";
}

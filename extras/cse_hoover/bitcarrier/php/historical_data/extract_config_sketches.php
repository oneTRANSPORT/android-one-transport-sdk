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

date_default_timezone_set('UTC');
$host = 'https://cse-01.onetransport.uk.net';
$path = '/ONETCSE01/Worldsensing/BitCarrier/v1.0/InterdigitalDemo/silverstone/config/sketches';

$opts = array(
       'http' => array(
                'method' => 'GET',
                'header' => 'Authorization: Basic '
                          .  base64_encode('pthomas:EKFYGUCC') . "\r\n"
                          . "X-M2M-Origin: C-pthomas-php\r\n"
                          . "X-M2M-RI: 123456\r\n"
                          . "Accept: application/json\r\n"));

$context = stream_context_create($opts);

echo "DROP TABLE IF EXISTS bit_carrier_silverstone_sketch;\n";
echo 'CREATE TABLE bit_carrier_silverstone_sketch (',
                  '_id INTEGER PRIMARY KEY AUTOINCREMENT,',
                  'sketch_id INTEGER,',
                  'vector_id INTEGER,',
                  'visible INTEGER,',
                  'copyrights TEXT,',
                  'coordinates TEXT,',
                  'cin_id TEXT UNIQUE ON CONFLICT REPLACE,',
                  "creation_time INTEGER);\n";

$file  = file_get_contents("$host$path?rcn=6", false, $context);
$json  = json_decode($file, true);
$sketches = $json['m2m:cnt']['ch'];

foreach ($sketches as $sketch) {
  $sketch_name = $sketch['-nm'];
  $file        = file_get_contents("$host$path/$sketch_name?rcn=6", false, $context);
  $json        = json_decode($file, true);
  $cis         = $json['m2m:cnt']['ch'];
  $ci_name     = $cis[count($cis) - 1]['-nm'];
  $ci_data     = file_get_contents("$host$path/$sketch_name/$ci_name", false, $context);

  $json          = json_decode($ci_data, true);
  $creation_time = strtotime($json['m2m:cin']['ct']);
  $resource_name = $json['m2m:cin']['rn'];

  $json = json_decode($json['m2m:cin']['con'], true);
  $sketch_id   = $json['sid'];
  $vector_id   = $json['vid'];
  $visible     = $json['visible'];
  $copyrights  = $json['copyrights'];
  $coordinates = $json['json'];

  echo 'INSERT INTO bit_carrier_silverstone_sketch VALUES (',
                   'NULL,',
                   "$sketch_id,",
                   "$vector_id,",
                   "$visible,",
                  "'$copyrights',",
                  "'$coordinates',",
                  "'$resource_name',",
                   "$creation_time);\n";
}

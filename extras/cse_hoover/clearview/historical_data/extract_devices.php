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
$host = "https://cse-01.onetransport.uk.net";
$path = "/ONETCSE01/ClearviewIntelligence_VBV/DEVICES";

$opts = array(
       'http' => array(
                'method' => 'GET',
                'header' => 'Authorization: Basic '
                           . base64_encode('pthomas:EKFYGUCC') . "\r\n"
                           ."X-M2M-Origin: C-pthomas-php\r\n"
                           ."X-M2M-RI: 123456\r\n"
                           ."Accept: application/json\r\n"));
$context = stream_context_create($opts);

//{"m2m:cin":{"cnf":"text/plain","con":"{\"title\":\"Silverstone10\",\"description\":\"Site 10, North Car Parks\",\"type\":\"M680\",\"latitude\":\"52.078907\",\"longitude\":\"-1.026202\",\"changed\":\"2016-06-27 12:28:09\"}","cs":159,"ct":"20160705T095746","et":"99991231T235959","lt":"20160705T095746","or":"http://www.google.com","pi":"cnt_20160705T095746_1372","ri":"cin_20160705T095746_1373","rn":"cin19880629T135052583595452139872849356544","st":1,"ty":4}}

echo 'CREATE TABLE IF NOT EXISTS clearview_silverstone_device (',
                  '_id INTEGER PRIMARY KEY AUTOINCREMENT,',
                  'sensor_id INTEGER NOT NULL,',
                  'title TEXT,',
                  'description TEXT,',
                  'type TEXT,',
                  'latitude REAL,',
                  'longitude REAL,',
                  'changed TEXT,',
                  'cin_id TEXT NOT NULL,',
                  'creation_time INTEGER,'
                  "UNIQUE (sensor_id,cin_id) ON CONFLICT IGNORE);\n";

$file    = file_get_contents("$host$path?rcn=6", false, $context);
$json    = json_decode($file, true);
$devices = $json['m2m:cnt']['ch'];

foreach ($devices as $device) {
  $device_name = $device['-nm'];
  $file        = file_get_contents("$host$path/$device_name?rcn=6", false, $context);
  $json        = json_decode($file, true);
  $cis         = $json['m2m:cnt']['ch'];
  $ci_name     = $cis[count($cis) - 1]['-nm'];
  $ci_data     = file_get_contents("$host$path/$device_name/$ci_name", false, $context);

  $json          = json_decode($ci_data, true);
  $creation_time = strtotime($json['m2m:cin']['ct']);
  $resource_name = $json['m2m:cin']['rn'];

  $json        = json_decode($json['m2m:cin']['con'], true);
  $sensor_id   = ereg_replace('[^0-9]+', '', $device_name);
  $title       = $json['title'];
  $description = $json['description'];
  $type        = $json['type'];
  $latitude    = $json['latitude'];
  $longitude   = $json['longitude'];
  $changed     = $json['changed'];

  echo 'INSERT INTO clearview_silverstone_device values (NULL,',
                   "$sensor_id,",
                  "'$title',",
                  "'$description',",
                  "'$type',",
                   "$latitude,",
                   "$longitude,",
                  "'$changed',",
                  "'$resource_name',",
                   "$creation_time);\n";
}

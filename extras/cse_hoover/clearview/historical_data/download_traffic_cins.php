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

set_time_limit(0);

$host = "https://cse-01.onetransport.uk.net";
$path = "/ONETCSE01/ClearviewIntelligence_VBV";

$opts = array(
       'http' => array(
                'method' => 'GET',
                'header' => 'Authorization: Basic '
                           . base64_encode('pthomas:EKFYGUCC') . "\r\n"
                           ."X-M2M-Origin: C-pthomas-php\r\n"
                           ."X-M2M-RI: 123456\r\n"
                           ."Accept: application/json\r\n"));

$context = stream_context_create($opts);

$file  = file_get_contents("$host$path?rcn=6", false, $context);
$json  = json_decode($file, true);
$devices = $json['m2m:ae']['ch'];

foreach ($devices as $device) {
  $device_name = $device['-nm'];
  if (ereg('[0-9]', $device_name)) { // Ignore DEVICES directory.
    $file = file_get_contents("$host$path/$device_name?rcn=6", false, $context);
    $json = json_decode($file, true);
    $cis = $json['m2m:cnt']['ch'];
    foreach ($cis as $ci) {
      $ci_name   = $ci['-nm'];
      echo "$host$path/$device_name/$ci_name\n";
//      $file = file_get_contents("$host$path/$device_name/$ci_name", false, $context);
//      echo "$file\n";
    }
  }
}
//{"m2m:cin":{"cnf":"text/plain","con":"{\"title\":\"Silverstone10\",\"description\":\"Site 10, North Car Parks\",\"type\":\"M680\",\"latitude\":\"52.078907\",\"longitude\":\"-1.026202\",\"changed\":\"2016-06-27 12:28:09\"}","cs":159,"ct":"20160705T095746","et":"99991231T235959","lt":"20160705T095746","or":"http://www.google.com","pi":"cnt_20160705T095746_1372","ri":"cin_20160705T095746_1373","rn":"cin19880629T135052583595452139872849356544","st":1,"ty":4}}

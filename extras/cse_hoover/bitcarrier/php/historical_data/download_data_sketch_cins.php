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
$path = "/ONETCSE01/Worldsensing/BitCarrier/v1.0/InterdigitalDemo/silverstone/data/sketches";

$opts = array(
       'http' => array(
                'method' => 'GET',
                'header' => 'Authorization: Basic '
                           . base64_encode('pthomas:EKFYGUCC') . "\r\n"
                           ."X-M2M-Origin: C-pthomas-php\r\n"
                           ."X-M2M-RI: 123456\r\n"
                           ."Accept: application/json\r\n"));

$context = stream_context_create($opts);
$old_hash = make_hash(file('sketch_cins_old.txt'));

$file  = file_get_contents("$host$path?rcn=6", false, $context);
$json  = json_decode($file, true);
$sketches = $json['m2m:cnt']['ch'];

$count = 0;
$missed = 0;
foreach ($sketches as $sketch) {
  $sketch_name = $sketch['-nm'];
  $file      = file_get_contents("$host$path/$sketch_name?rcn=6", false, $context);
  $json      = json_decode($file, true);
  $cis       = $json['m2m:cnt']['ch'];
  foreach ($cis as $ci) {
    $ci_name   = $ci['-nm'];
    if ($old_hash[$ci_name]) {
      $count++;
    } else {
      $missed++;
    }
  }
}
echo "Matched $count Missed $missed\n";

function make_hash($lines) {
  foreach ($lines as $line) {
    $hash[trim($line)]=true;
  }
  return $hash;
}

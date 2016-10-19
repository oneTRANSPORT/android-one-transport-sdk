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
set_time_limit(0);
$path = 'traffic_cins';
$urls = 'traffic_cins_urls.txt';

//https://cse-01.onetransport.uk.net/ONETCSE01/ClearviewIntelligence_VBV/DEVICE_1752/cin19910314T211401668985241140077808219904

$lines = file($urls);
foreach ($lines as $line) {
  $bits              = explode('/', trim($line));
  $cin_map[$bits[6]] = ereg_replace('[^0-9]+', '', $bits[5]);
}
unset($lines);

//{"m2m:cin":{"cnf":"text/plain","con":"
//[
//{\"vehicleNumber\":0,\"time\":\"2016-09-06 20:41:24\",\"lane\":0,\"subSite\":1,
//\"indexMark\":null,\"speed\":36,\"length\":1.6,\"headway\":25.5,\"grossWeight\":null,
//\"gap\":25.5,\"direction\":false,\"vehicleClass\":0,\"overhang\":null,
//\"classScheme\":3,\"chassisHeightCode\":4,\"wheelbase\":null,\"axleData\":[],
//\"occupancyTime\":160,\"resultCode\":null,\"deltaTime\":84300},
//
//{\"vehicleNumber\":0,\"time\":\"2016-09-06 20:42:18\",\"lane\":0,\"subSite\":1,
//\"indexMark\":null,\"speed\":39,\"length\":3.9,\"headway\":25.5,\"grossWeight\":null,
//\"gap\":25.5,\"direction\":false,\"vehicleClass\":0,\"overhang\":null,
//\"classScheme\":3,\"chassisHeightCode\":1,\"wheelbase\":null,\"axleData\":[],
//\"occupancyTime\":360,\"resultCode\":null,\"deltaTime\":138600}
//]
//","cs":666,"ct":"20160906T194310","et":"99991231T235959","lt":"20160906T194310","or":"http://www.google.com","pi":"cnt_20160705T145839_91","ri":"cin_20160906T194310_693645","rn":"cin19700102T030915097755140234941646592","st":4257,"ty":4}}

echo "DROP TABLE IF EXISTS clearview_silverstone_traffic;\n";
echo 'CREATE TABLE clearview_silverstone_traffic (',
                  '_id INTEGER PRIMARY KEY AUTOINCREMENT,',
                  'sensor_id INTEGER,',
                  'timestamp TEXT,',
                  'lane INTEGER,',
                  'direction BOOLEAN,',
                  'cin_id TEXT,', // Not unique.
                  "creation_time INTEGER);\n";

$dh = opendir($path);
while (($file = readdir($dh)) !== false) {
  if (ereg('^cin', $file)) {
    $cin           = file_get_contents("$path/$file");
    $json          = json_decode($cin, true);
    $creation_time = strtotime($json['m2m:cin']['ct']);
    $resource_name = $json['m2m:cin']['rn'];

    $sensor_id = $cin_map[$file];

    $traffic_array = json_decode($json['m2m:cin']['con'], true);
    foreach ($traffic_array as $traffic) {
      $timestamp = $traffic['time'];
      $lane = $traffic['lane'];
      $direction = $traffic['direction'];

      if ($direction != 1) {
        $direction = 0;
      }
      $insert = 'INSERT INTO clearview_silverstone_traffic VALUES (NULL,'
                          . "$sensor_id,"
                         . "'$timestamp',"
                          . "$lane,"
                          . "$direction,"
                         . "'$resource_name',"
                          . "$creation_time);\n";
 
      if (ereg('[0-9]', $insert)) {
        $insert = str_replace(',)', ',NULL)', $insert, $count);
        $count = 1;
        while ($count > 0) {
          $insert = str_replace(',,', ',NULL,', $insert, $count);
        }
        echo $insert;
      }
    }
  }
}
closedir($dh);
// Remove values between F1 and Moto GP that we don't need.
$midnight_jul_08 = strtotime('20160708T010000'); // UTC, so one hour forward for BST.
$midnight_jul_11 = strtotime('20160711T010000');
$midnight_sep_02 = strtotime('20160902T010000');
$midnight_sep_05 = strtotime('20160905T010000');
echo 'delete from clearview_silverstone_traffic',
                " where creation_time < $midnight_jul_08;\n";
echo 'delete from clearview_silverstone_traffic',
                " where creation_time > $midnight_sep_05;\n";
echo 'delete from clearview_silverstone_traffic',
                " where creation_time > $midnight_jul_11",
                "   and creation_time < $midnight_sep_02;\n";
echo "vacuum;\n";

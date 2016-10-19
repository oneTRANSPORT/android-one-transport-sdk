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

$path = 'sketch_cins';

echo "DROP TABLE IF EXISTS data_sketch;\n";
echo "CREATE TABLE IF NOT EXISTS data_sketch (id INTEGER PRIMARY KEY, vid INTEGER, sid INTEGER, timestamp TEXT, ",
     "level_of_service TEXT, coordinates TEXT);\n";

$dh = opendir($path);
while (($file = readdir($dh)) !== false) {
  if (ereg('^cin', $file)) {
    $cin = file_get_contents("$path/$file");
    $json = json_decode($cin, true);
    $created_time = $json['m2m:cin']['ct'];
    $json = json_decode($json['m2m:cin']['con'], true);
    if ($json['levelofservice'] != '') {
      $insert = 'INSERT INTO data_sketch values (NULL,'. $json['vid'] . ','
                                                       . $json['sid'] . ',"'
                                                       . $created_time . '","'
                                                       . $json['levelofservice'] . '","'
                                                       . trim($json['coordinates']) . '"' . ");\n";
      echo $insert;
    }
  }
}
closedir($dh);

//    $ci_name = $ci_bit['-nm'];
//    $ci = file_get_contents("$host$path$vector/$ci_name", false, $context);
//    $json = json_decode($ci, true);
//    $json = json_decode($json['m2m:cin']['con'], true);
//    $insert = 'INSERT INTO data_traveltime values (NULL,'. $json['rid']. ',"'.
//                                                 $json['time']. '",'.
//                                                 $json['traveltimes'][0]['tid']. ','.
//                                                 $json['last']['calculated']['readings']. ");\n";
//    if (ereg('[0-9]', $insert)) {
//      $insert = str_replace(',)', ',NULL)', $insert, $count);
//      $count = 1;
//      while ($count > 0) {
//        $insert = str_replace(',,', ',NULL,', $insert, $count);
//      }     
//      echo $insert;
//    }

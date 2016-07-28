#!/usr/bin/php -q
<?php

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

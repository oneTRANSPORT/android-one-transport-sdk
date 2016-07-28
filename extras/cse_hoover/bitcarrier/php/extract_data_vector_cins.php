#!/usr/bin/php -q
<?php

set_time_limit(0);

$path = 'vector_cins';

echo "DROP TABLE IF EXISTS data_vector;\n";
echo "CREATE TABLE IF NOT EXISTS data_vector (id INTEGER PRIMARY KEY, vid INTEGER, timestamp TEXT, level_of_service TEXT);\n";

$dh = opendir($path);
while (($file = readdir($dh)) !== false) {
  if (ereg('^cin', $file)) {
    $cin = file_get_contents("$path/$file");
    $json = json_decode($cin, true);
    $created_time = $json['m2m:cin']['ct'];
    $json = json_decode($json['m2m:cin']['con'], true);
    if ($json['levelofservice'] != '') {
      $insert = 'INSERT INTO data_vector values (NULL,'. $json['vid'] . ',"'
                                                       . $created_time . '","'
                                                       . $json['levelofservice'] . '"' . ");\n";
      echo $insert;
    }
  }
}
closedir($dh);

#!/usr/bin/php -q
<?php

// Create a summary for rapid import into the map app.
//
// One table, columns are:
//
// id INTEGER PRIMARY KEY,
// rid INTEGER,
// timestamp TEXT, tid INTEGER,
// node_from TEXT,
// node_to TEXT,
// average_score INTEGER,
// average_publish_speed REAL,
// average_publish_elapsed REAL,
// average_publish_trend REAL,
// average_calculated_speed REAL,
// average_calculated_elapsed REAL,
// average_calculated_readings INTEGER,	not needed?
// last_score INTEGER,
// last_publish_speed REAL,
// last_publish_elapsed REAL,
// last_publish_trend REAL,
// last_calculated_speed REAL,
// last_calculated_elapsed REAL,
// last_calculated_readings INTEGER	not needed?

set_time_limit(0);

$path = 'traveltime_cins';

$dh = opendir($path);
while (($file = readdir($dh)) !== false) {
  if (ereg('^cin', $file)) {
    $cin = file_get_contents("$path/$file");
    $json = json_decode($cin, true);
    $json = json_decode($json['m2m:cin']['con'], true);
    if ($json['time'] != '') {
      $insert = 'INSERT INTO data_traveltime values (NULL,'. $json['rid'] . ',"' .
                                                   $json['time'] . '",' .
                                                   $json['traveltimes'][0]['tid'] . ',"' .
                                                   $json['traveltimes'][0]['from'] . '","' .
                                                   $json['traveltimes'][0]['to'] . '",' .
                                                   $json['average']['score'] . ',' .
                                                   $json['average']['publish']['speed'] . ',' .
                                                   $json['average']['publish']['elapsed'] . ',' .
                                                   $json['average']['publish']['trend'] . ',' .
                                                   $json['average']['calculated']['speed'] . ',' .
                                                   $json['average']['calculated']['elapsed'] . ',' .
                                                   $json['average']['calculated']['readings'] . ',' .
                                                   $json['last']['score'] . ',' .
                                                   $json['last']['publish']['speed'] . ',' .
                                                   $json['last']['publish']['elapsed'] . ',' .
                                                   $json['last']['publish']['trend'] . ',' .
                                                   $json['last']['calculated']['speed'] . ',' .
                                                   $json['last']['calculated']['elapsed'] . ',' .
                                                   $json['last']['calculated']['readings'] . ");\n";
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

//Array
//(
//    [rid] => 133
//    [time] => 2016-07-06T10:11:00Z
//    [traveltimes] => Array
//        (
//            [0] => Array
//                (
//                    [tid] => 1
//                    [offset] => 0
//                    [from] => 2
//                    [to] => 15
//                )
//        )
//    [average] => Array
//        (
//            [score] => 100
//            [publish] => Array
//                (
//                    [speed] => 39.63117
//                    [elapsed] => 1155
//                    [trend] => 65.710187
//                )
//            [calculated] => Array
//                (
//                    [speed] => 39.63117
//                    [elapsed] => 1155
//                    [readings] => 1
//                )
//        )
//    [last] => Array
//        (
//            [score] => 100
//            [publish] => Array
//                (
//                    [speed] => 52.735023
//                    [elapsed] => 52.735023
//                )
//            [calculated] => Array
//                (
//                    [speed] => 52.735023
//                    [elapsed] => 868
//                    [readings] => 2
//                )
//        )
//)


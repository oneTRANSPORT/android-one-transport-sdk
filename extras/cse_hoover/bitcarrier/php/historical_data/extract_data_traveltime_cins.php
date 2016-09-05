#!/usr/bin/php -q
<?php

set_time_limit(0);
date_default_timezone_set('UTC');

$path = 'traveltime_cins';

echo "DROP TABLE IF EXISTS bit_carrier_silverstone_travel_summary;\n";
echo 'CREATE TABLE bit_carrier_silverstone_travel_summary (',
                  '_id INTEGER PRIMARY KEY AUTOINCREMENT,',
                  'travel_summary_id INTEGER,',
                  'clock_time TEXT,',
                  'from_location TEXT,',
                  'to_location TEXT,',
                  'score REAL,',
                  'speed REAL,',
                  'elapsed REAL,',
                  'trend REAL,',
                  'cin_id TEXT UNIQUE ON CONFLICT REPLACE,',
                  "creation_time INTEGER);\n";

$dh = opendir($path);
while (($file = readdir($dh)) !== false) {
  if (ereg('^cin', $file)) {
    $cin           = file_get_contents("$path/$file");
    $json          = json_decode($cin, true);
    $creation_time = strtotime($json['m2m:cin']['ct']);
    $resource_name = $json['m2m:cin']['rn'];

    $json              = json_decode($json['m2m:cin']['con'], true);
    $travel_summary_id = $json['traveltimes']['0']['tid'];
    $clock_time        = $json['time'];
    $from_location     = $json['traveltimes']['0']['from'];
    $to_location       = $json['traveltimes']['0']['to'];
    $score             = $json['average']['score'];
    $speed             = $json['average']['publish']['speed'];
    $elapsed           = $json['average']['publish']['elapsed'];
    $trend             = $json['average']['publish']['trend'];
    if ($clock_time != '') {
      $insert = 'INSERT INTO bit_carrier_silverstone_travel_summary values ('
                          . 'NULL,'
                          . "$travel_summary_id,"
                         . "'$clock_time',"
                         . "'$from_location',"
                         . "'$to_location',"
                          . "$score,"
                          . "$speed,"
                          . "$elapsed,"
                          . "$trend,"
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

// Remove anything outside the F1 weekend, 8-10 July.
echo 'DELETE FROM bit_carrier_silverstone_travel_summary ',
     'WHERE substr(clock_time,0,11) > "2016-07-10" ',
     "OR substr(clock_time,0,11) < '2016-07-08';\n";

$midnight_friday = strtotime('20160708T010000'); // UTC, so one hour forward for BST.
$midnight_monday = strtotime('20160711T010000');
echo 'DELETE from bit_carrier_silverstone_sketch ',
     "WHERE creation_time >= $midnight_friday ",
     "AND creation_time <= $midnight_monday;\n";
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

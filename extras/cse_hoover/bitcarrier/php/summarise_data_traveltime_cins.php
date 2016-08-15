#!/usr/bin/php -q
<?php

// Create a summary for rapid import into the map app.
//
// Create scratch-db, then import this:
//
// ./summarise_data_traveltime_cins.php | sqlite3 scratch-db
//
// Then export group by hours:
//
// sqlite3 scratch-db 'select tid,date,hour,avg(score),avg(speed),avg(elapsed),avg(trend) from traveltimes
//                     group by tid,date,hour;' > group_by_hour.txt
//
// Then just need to fix lines where no average trend exists by appending a zero
// eg. with Vim :%s/|\n/|0^M/g .
//
// And use awk to replace | with ,
//
// awk -F\| '{print $1",\""$2"\","$3","$4","$5","$6","$7}' group_by_hour.txt > group_by_hour_fixed.txt


set_time_limit(0);

echo "DROP TABLE IF EXISTS traveltimes;\n";
echo 'CREATE TABLE traveltimes (id INTEGER PRIMARY KEY, rid INTEGER, tid INTEGER, date TEXT, hour INTEGER, ',
     "from_location TEXT, to_location TEXT, score REAL, speed REAL, elapsed REAL, trend REAL);\n";

$path = 'traveltime_cins';

$dh = opendir($path);
while (($file = readdir($dh)) !== false) {
  if (ereg('^cin', $file)) {
    $cin = file_get_contents("$path/$file");
    $json = json_decode($cin, true);
    $json = json_decode($json['m2m:cin']['con'], true);
    if ($json['time'] != '') {

      $t = $json['time']; // 2016-07-06T10:11:00Z
      $date = ereg_replace('T.*', '', $t);
      $hour = ereg_replace(':.*', '', ereg_replace('.*T', '', $t));

      $rid     = $json['rid'];
      $tid     = $json['traveltimes'][0]['tid'];
      $from    = $json['traveltimes'][0]['from'];
      $to      = $json['traveltimes'][0]['to'];
      $score   = $json['average']['score'];
      $speed   = $json['average']['publish']['speed'];
      $elapsed = $json['average']['publish']['elapsed'];
      $trend   = $json['average']['publish']['trend'];

      $insert = "INSERT INTO traveltimes values (NULL,$rid,$tid,'$date',$hour,'$from','$to',$score,"
              . "$speed,$elapsed,$trend);\n";

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


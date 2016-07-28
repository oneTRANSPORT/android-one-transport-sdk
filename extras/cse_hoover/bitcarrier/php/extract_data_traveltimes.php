#!/usr/bin/php -q
<?php

set_time_limit(0);

$host = "https://cse-01.onetransport.uk.net";
$path = "/ONETCSE01/Worldsensing/BitCarrier/v1.0/InterdigitalDemo/silverstone/data/traveltimes/t";
$traveltimes = array(133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,
                     158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,
                     183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,207,
                     208,209,210,211,212,213,214,215);

$opts = array(
       'http' => array(
                'method' => 'GET',
                'header' => 'Authorization: Basic '
                           . base64_encode('pthomas:EKFYGUCC') . "\r\n"
                           ."X-M2M-Origin: C-pthomas-php\r\n"
                           ."X-M2M-RI: 123456\r\n"
                           ."Accept: application/json\r\n"));

$context = stream_context_create($opts);

//echo "DROP TABLE IF EXISTS data_traveltime;\n";
//echo "CREATE TABLE IF NOT EXISTS data_traveltime (id INTEGER PRIMARY KEY, rid INTEGER, timestamp TEXT, tid INTEGER, ",
//     "node_from INTEGER, node_to INTEGER, average_score INTEGER, average_publish_speed REAL, average_publish_elapsed REAL, ",
//     "average_publish_trend REAL, average_calculated_speed REAL, average_calculated_elapsed REAL, ",
//     "average_calculated_readings INTEGER, last_score INTEGER, last_publish_speed REAL, last_publish_elapsed REAL, ",
//     "last_publish_trend REAL, last_calculated_speed REAL, last_calculated_elapsed REAL, ",
//     "last_calculated_readings INTEGER);\n";

foreach ($traveltimes as $traveltime) {
  $file = file_get_contents("$host$path$traveltime?rcn=6", false, $context);
  $json = json_decode($file, true);
  $cis = $json['m2m:cnt']['ch'];
  foreach ($cis as $ci_bit) {
    echo $host, $ci_bit['#text'], "\n";
//    $ci_name = $ci_bit['-nm'];
//    $ci = file_get_contents("$host$path$traveltime/$ci_name", false, $context);
//    $json = json_decode($ci, true);
//    $json = json_decode($json['m2m:cin']['con'], true);
//    $insert = 'INSERT INTO data_traveltime values (NULL,'. $json['rid']. ',"'.
//                                                 $json['time']. '",'.
//                                                 $json['traveltimes'][0]['tid']. ','.
//                                                 $json['traveltimes'][0]['from']. ','.
//                                                 $json['traveltimes'][0]['to']. ','.
//                                                 $json['average']['score']. ','.
//                                                 $json['average']['publish']['speed']. ','.
//                                                 $json['average']['publish']['elapsed']. ','.
//                                                 $json['average']['publish']['trend']. ','.
//                                                 $json['average']['calculated']['speed']. ','.
//                                                 $json['average']['calculated']['elapsed']. ','.
//                                                 $json['average']['calculated']['readings']. ','.
//                                                 $json['last']['score']. ','.
//                                                 $json['last']['publish']['speed']. ','.
//                                                 $json['last']['publish']['elapsed']. ','.
//                                                 $json['last']['publish']['trend']. ','.
//                                                 $json['last']['calculated']['speed']. ','.
//                                                 $json['last']['calculated']['elapsed']. ','.
//                                                 $json['last']['calculated']['readings']. ");\n";
//    if (ereg('[0-9]', $insert)) {
//      $insert = str_replace(',)', ',NULL)', $insert, $count);
//      $count = 1;
//      while ($count > 0) {
//        $insert = str_replace(',,', ',NULL,', $insert, $count);
//      }     
//      echo $insert;
//    }
  }
}

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


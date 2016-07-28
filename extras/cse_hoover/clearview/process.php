#!/usr/bin/php -q
<?php

set_time_limit(0);
$devices = array(1745, 1746, 1747, 1748, 1749, 1750, 1751, 1752, 1753, 1754);

foreach ($devices as $device) {
  $filename = 'DEVICE_'.$device.'_list.txt';

  $lines = file($filename);
  foreach ($lines as $line) {
    $bits = explode('"', $line);
    $cin_name = $bits[7];

    $cin = file_get_contents("cin/$cin_name");
    $cin_lines = explode(',', $cin);

    foreach ($cin_lines as $cin_line) {
      $no_quotes = str_replace('\"', '', trim($cin_line));
      if (ereg('time', $cin_line)) {
        $time = ereg_replace('^[^:]*:','', $no_quotes);
      }
      if (ereg('lane', $cin_line)) {
        $lane = ereg_replace('^[^:]*:', '', $no_quotes);
      }
      if (ereg('direction', $cin_line)) {
        $direction = ereg_replace('^[^:]*:', '', $no_quotes);
        if ($direction == 'false') {
          $direction = 0;
        } else {
          $direction = 1;
        }
        echo "INSERT INTO history values (NULL,$device,\"$time\",$lane,$direction);\n";
      }
    }
  }
}

// Which fields do we need?  time, lane, direction
//
//\"vehicleNumber\":0
//\"time\":\"2016-07-17 14:14:25\"
//\"lane\":0
//\"subSite\":1
//\"indexMark\":null
//\"speed\":11
//\"length\":3.2
//\"headway\":25.5
//\"grossWeight\":null
//\"gap\":25.5
//\"direction\":false
//\"vehicleClass\":0
//\"overhang\":null
//\"classScheme\":3
//\"chassisHeightCode\":4
//\"wheelbase\":null
//\"axleData\":[]
//\"occupancyTime\":1047
//\"resultCode\":null
//\"deltaTime\":265000}


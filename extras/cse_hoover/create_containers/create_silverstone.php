#!/usr/bin/php -q
<?php

set_time_limit(0);

$root = 'ONETCSE01/C-clearview-silverstone-summary';
$lines = file('clearview_total_by_device_by_time.txt');
foreach ($lines as $line) {
  list($device_id, $days, $hours, $minutes, $lane, $direction) = explode('|', $line);
  $result[$device_id][$days][$hours][($minutes / 15)][trim($direction)]++;
}

mkdir("$root/sensors");
foreach ($result as $device_id => $days) {
  mkdir("$root/sensors/sensor$device_id");
  $occupancy = 0; // Best we can tell.
  foreach ($days as $day => $hours) {
    mkdir("$root/sensors/sensor$device_id/day$day");
    $day_total_in      = 0;
    $day_total_out     = 0;
    $day_min_in        = 9999999;
    $day_min_out       = 9999999;
    $day_min_occupancy = 9999999;
    $day_max_in        = -9999999;
    $day_max_out       = -9999999;
    $day_max_occupancy = -9999999;
    foreach ($hours as $hour => $minutes) {
      mkdir("$root/sensors/sensor$device_id/day$day/hour$hour");
      $hour_total_in      = 0;
      $hour_total_out     = 0;
      $hour_min_in        = 9999999;
      $hour_min_out       = 9999999;
      $hour_min_occupancy = 9999999;
      $hour_max_in        = -9999999;
      $hour_max_out       = -9999999;
      $hour_max_occupancy = -9999999;
      foreach ($minutes as $minute => $values) {
        $json = '{"in":'            . (int)$values[0] 
              . ',"out":'           . (int)$values[1] 
              . ',"occupancy-end":' . (int)($values[0] - $values[1] + $occupancy)
              . "}\n";
        $occupancy      += $values[0] - $values[1];
        $hour_total_in  += $values[0];
        $hour_total_out += $values[1];
        if ($hour_min_in        > $values[0]) { $hour_min_in        = $values[0]; }
        if ($hour_min_out       > $values[1]) { $hour_min_out       = $values[1]; }
        if ($hour_min_occupancy > $occupancy) { $hour_min_occupancy = $occupancy; }
        if ($hour_max_in        < $values[0]) { $hour_max_in        = $values[0]; }
        if ($hour_max_out       < $values[1]) { $hour_max_out       = $values[1]; }
        if ($hour_max_occupancy < $occupancy) { $hour_max_occupancy = $occupancy; }
        switch ($minute) {
          case 0:
            mkdir("$root/sensors/sensor$device_id/day$day/hour$hour/mins00-14");
            file_put_contents("$root/sensors/sensor$device_id/day$day/hour$hour/mins00-14/cin", $json);
            break;
          case 1:
            mkdir("$root/sensors/sensor$device_id/day$day/hour$hour/mins15-29");
            file_put_contents("$root/sensors/sensor$device_id/day$day/hour$hour/mins15-29/cin", $json);
            break;
          case 2:
            mkdir("$root/sensors/sensor$device_id/day$day/hour$hour/mins30-44");
            file_put_contents("$root/sensors/sensor$device_id/day$day/hour$hour/mins30-44/cin", $json);
            break;
          case 3:
            mkdir("$root/sensors/sensor$device_id/day$day/hour$hour/mins45-59");
            file_put_contents("$root/sensors/sensor$device_id/day$day/hour$hour/mins45-59/cin", $json);
            break;
        }
      }
      // Hourly average.
      mkdir("$root/sensors/sensor$device_id/day$day/hour$hour/hour");
      $json = '{"total-in":'      . (int)$hour_total_in
            . ',"total-out":'     . (int)$hour_total_out
            . ',"occupancy-end":' . (int)$occupancy
            . ',"min-in":'        . (int)$hour_min_in
            . ',"min-out":'       . (int)$hour_min_out
            . ',"min-occupancy":' . (int)$hour_min_occupancy
            . ',"max-in":'        . (int)$hour_max_in
            . ',"max-out":'       . (int)$hour_max_out
            . ',"max-occupancy":' . (int)$hour_max_occupancy
            . "}\n";
      file_put_contents("$root/sensors/sensor$device_id/day$day/hour$hour/hour/cin", $json);
        $day_total_in  += $hour_total_in;
        $day_total_out += $hour_total_out;
        if ($day_min_in        > $hour_min_in)        { $day_min_in        = $hour_min_in; }
        if ($day_min_out       > $hour_min_out)       { $day_min_out       = $hour_min_out; }
        if ($day_min_occupancy > $hour_min_occupancy) { $day_min_occupancy = $hour_min_occupancy; }
        if ($day_max_in        < $hour_max_in)        { $day_max_in        = $hour_max_in; }
        if ($day_max_out       < $hour_max_out)       { $day_max_out       = $hour_max_out; }
        if ($day_max_occupancy < $hour_max_occupancy) { $day_max_occupancy = $hour_max_occupancy; }
    }
    // Daily average.
      mkdir("$root/sensors/sensor$device_id/day$day/day");
      $json = '{"total-in":'      . (int)$day_total_in
            . ',"total-out":'     . (int)$day_total_out
            . ',"occupancy-end":' . (int)$occupancy
            . ',"min-in":'        . (int)$day_min_in
            . ',"min-out":'       . (int)$day_min_out
            . ',"min-occupancy":' . (int)$day_min_occupancy
            . ',"max-in":'        . (int)$day_max_in
            . ',"max-out":'       . (int)$day_max_out
            . ',"max-occupancy":' . (int)$day_max_occupancy
            . "}\n";
      file_put_contents("$root/sensors/sensor$device_id/day$day/day/cin", $json);
  }
}

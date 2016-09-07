#!/usr/bin/php -q
<?php

date_default_timezone_set('UTC');
set_time_limit(0);
$path = 'vector_cins';

// {"m2m:cin":{"cnf":"application/json","con":"
//{
//\"vid\":\"277\",
//\"time\":\"2016-09-03T17:59:00Z\",
//\"average\":{
//  \"calculated\":{
//  \"speed\":13.8923855,
//  \"elapsed\":1831,
//  \"readings\":1
//  }
//},
//\"last\":{
//  \"calculated\":{
//  \"speed\":18.687572,
//  \"elapsed\":1199,
//  \"readings\":1
//  }
//},
//\"levelofservice\":\"yellow\"
//}
//","cs":213,"ct":"20160903T180050","et":"20180916T000000","lt":"20160903T180050","pi":"cnt_20160706T095347_4690","ri":"cin_20160903T180050_139979","rn":"cin19970910T021010873857410140234933253888","st":4712,"ty":4}}

echo "DROP TABLE IF EXISTS bit_carrier_silverstone_vector;\n";
echo 'CREATE TABLE bit_carrier_silverstone_vector (',
                  '_id INTEGER PRIMARY KEY AUTOINCREMENT,',
                  'vector_id INTEGER,',
                  'timestamp TEXT,',
                  'speed REAL,',
                  'elapsed REAL,',
                  'level_of_service TEXT,',
                  'cin_id TEXT UNIQUE ON CONFLICT REPLACE,',
                  "creation_time INTEGER);\n";

$dh = opendir($path);
while (($file = readdir($dh)) !== false) {
  if (ereg('^cin', $file)) {
    $cin           = file_get_contents("$path/$file");
    $json          = json_decode($cin, true);
    $creation_time = strtotime($json['m2m:cin']['ct']);
    $resource_name = $json['m2m:cin']['rn'];

    $json = json_decode($json['m2m:cin']['con'], true);
    $vector_id = $json['vid'];
    $timestamp = $json['time'];
    $speed     = $json['average']['calculated']['speed'];
    $elapsed   = $json['average']['calculated']['elapsed'];
    $level_of_service = $json['levelofservice'];

    if ($level_of_service != '') {
      $insert = 'INSERT INTO bit_carrier_silverstone_vector VALUES (NULL,'
                         .  "$vector_id,"
                         . "'$timestamp',"
                         .  "$speed,"
                         .  "$elapsed,"
                         . "'$level_of_service',"
                         . "'$resource_name',"
                         .  "$creation_time);\n";
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
echo 'delete from bit_carrier_silverstone_vector',
                " where creation_time < $midnight_jul_08;\n";
echo 'delete from bit_carrier_silverstone_vector',
                " where creation_time > $midnight_sep_05;\n";
echo 'delete from bit_carrier_silverstone_vector',
                " where creation_time > $midnight_jul_11",
                "   and creation_time < $midnight_sep_02;\n";
echo "vacuum;\n";

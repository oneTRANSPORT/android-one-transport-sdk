#!/bin/bash

CSE_BASE_URL=https://cse-01.onetransport.uk.net
WGET_OPT="-S --no-check-certificate --user=pthomas --password=EKFYGUCC"
CSE_ID=ONET-CSE-01
CSE_NAME=ONETCSE01
AE_ID="C-pault-script"

wget $WGET_OPT \
     --header="X-M2M-RI: paul-data-hoover" \
     --header="X-M2M-Origin: $AE_ID" \
     --header="Accept: application/json" \
     -i big_list2.txt

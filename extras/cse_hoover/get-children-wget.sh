#!/bin/bash

CSE_BASE_URL=https://cse-01.onetransport.uk.net
WGET_OPT="-S --no-check-certificate --user=pthomas --password=EKFYGUCC"
CSE_ID=ONET-CSE-01
CSE_NAME=ONETCSE01
AE_ID="C-pault-script"

AE_NAME="Worldsensing"
CO_NAME="BitCarrier/v1.0/InterdigitalDemo/silverstone/data/traveltimes/t134/cin19780316T021417258862457139983602165504"

#AE_NAME="ClearviewIntelligence_VBV"
#CO_NAME="DEVICE_1747/cin19810729T010921365216961140508393481984"
#CO_NAME="DEVICES/DEVICE_1750/cin19860116T050832506236112139872948041472"
#CO_NAME="DEVICE_1754"

wget $WGET_OPT \
     --header="X-M2M-RI: xyz3" \
     --header="X-M2M-Origin: $AE_ID" \
     --header="Accept: application/json" \
     --save-headers=on \
     "$CSE_BASE_URL/$CSE_NAME?rcn=6"

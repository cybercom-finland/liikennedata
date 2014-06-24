#!/bin/bash

####################################
#
# Scriptillä muutetaan runstatus tiedostossa 
# olevaa arvoa, jotta saadaan tkl:n data keräys pysäytettyä
# Ajetaan ajastettuna crontabilla
#
####################################
STATUS=0;
RUNSCRIPTFILENAME="/home/ess/script/runstatus";
echo $STATUS > $RUNSCRIPTFILENAME;
exit 0;

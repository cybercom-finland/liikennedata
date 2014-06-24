#!/bin/bash
############################################
# Scripti jolla kerätään tkl:n liikennedataa
# käyttää WGET komentoa sekunnin välein, jolla 
# pyytää http://data.itsfactory.fi/siriaccess/vm/json 
# osoitteesta bussien liikkumistiedot ja muita tietoja
# Dataa kertyy 121 MB / tunti.
# Ajetaan ajastettuna crontabilla.
#
# Ajo pysäytetään ajamalla stopdatadwln.sh scripti tai
# muuttamalla runstatus tiedoston arvoa 1 -> 0 tai
# kill -9 $(cat pid)
# HUOM! Pakkolopetuksen jälkeen (muu kuin stopdatadwln.sh scriptin ajo)  
# pitää tuhota runstatus ja pid tiedostot käsin. 
# 
# Author: Esa Saarinen esa.saarinen@cybercom.com
#
############################################
# muuttujaan laitetaan arvo jonka avulla "hallitaan" scriptin ajoa
RUNSCRIPTSTATUS=1;
DATE=$(date +"%d-%m-%Y_%H-%M");
# Tähän laitetaan absoluutinen polku mistä scriptit löytyy
FILEPATH="/home/ess/script"
# runstatus tiedostossa on RUNSCRIPTSTATUS:ssa oleva arvo
# scripti tutkii runstatus tiedoston, jos tiedostossa oleva arvo (RUNSCRIPTSTATUS) <> 1 ajo pysähtyy  
RUNSCRIPTFILENAME="$FILEPATH/runstatus";
# liikennedata tiedoston nimi
FILENAME="$FILEPATH/tkldata_$DATE";
# pid tiedosto. tämän avulla tutkitaan onko ajo kenties jo aloitettu, ja jos on, ei käynnistetä uudelleen.
PIDFILENAME="$FILEPATH/pid";
# hakusykli sekunteina
TIMECYCLE=1;
# ohjataan aluksi status, jolla ajo voidaan ajaa, tiedostoon
echo "$RUNSCRIPTSTATUS" > $RUNSCRIPTFILENAME;
# jos pid (process id) jo löytyy, lopetetaan ajo.
if [ -f "$PIDFILENAME" ]; then
	echo "Data collection allready started.";
	exit 1;
fi
# ohjataan pid tiedostoon
echo $$ > $PIDFILENAME;
# HUOM! IKUINEN silmukka
for (( ; ; ))
do
	sleep $TIMECYCLE;
	# runstatus tiedostoa ei ole mudostunut -> exit.	
	if [ ! -f "$RUNSCRIPTFILENAME" ]; then
		exit 1;
	fi

	file="$RUNSCRIPTFILENAME";
	# haetaan statuksen  arvo
	typeset -i runnstatus=$(cat $file);
	# jos ei ole -> exit
	if [ -z "$runnstatus" ]; then
		exit 2; 
	fi
	# tutkitaan onko  runstatus on ajon mahdollistava
	# jos on suoritetaan wget, muuten tuhotaan ohjausiedostot ja poistutaan
	if [ $runnstatus == $RUNSCRIPTSTATUS ] 
	then
   		teksti=$(wget  -q -O - http://data.itsfactory.fi/siriaccess/vm/json);
		# lisätään rivin vaihto joka responsen perään
		printf "%s\n" "$teksti" >> $FILENAME;
	 
	else
		rm "$RUNSCRIPTFILENAME";
		rm "$PIDFILENAME";
		exit 0; 

	fi
done
exit 0;

#!/bin/sh
cat weather.dat | grep -e "^[[:space:]]\{2,3\}[0-9]\{1,2\}" | awk '{print $2-$3 " is the minimum spread", "which was the " $1 "th of the month"}' | sort -n | head -1

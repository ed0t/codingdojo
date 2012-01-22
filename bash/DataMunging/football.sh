#!/bin/sh
cat football.dat | grep -e '[0-9]\. ' | awk '{ if($7-$9 >= 0) { print $7-$9,$2 } else { print ($7-$9)*(-1),$2 } }' | sort -n | head -1



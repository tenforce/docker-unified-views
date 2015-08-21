#!/bin/bash

echo "Converting environment variables to Java properties"
IFS=$'\\n'

echo "" >> /config/frontend-config.properties
echo "" >> /config/backend-config.properties
if [ ! -f "/.settings_set" ];
then
    printenv | grep -P "^UV_" | while read setting
    do
	key=`echo "$setting" | grep -o -P "^UV_[^=]+" | sed 's/^.\{3\}//g' | sed 's/_/./g' | awk '{print tolower($0)}'`
	value=`echo "$setting" | grep -o -P "=.*$" | sed 's/^=//g'`
	echo "Registering $key to be $value"
	echo "$key=$value" >> /config/frontend-config.properties
	echo "$key=$value" >> /config/backend-config.properties
    done
    touch /.dba_pwd_set
fi
echo "Finished converting environment variables to Java properties"


#!/bin/bash

echo "Converting environment variables to Java properties"
IFS=$'\\n'

if [ ! -f "/.settings_set" ];
then
    mkdir -p /unified-views/backend/working/temp/export /unified-views/backend/working/temp/import
    chmod 777 /unified-views/backend/working/temp/export
    chmod 777 /unified-views/backend/working/temp/import
    cp /etc/unifiedviews/config/config.properties /unified-views/config/frontend-config.properties
    cp /etc/unifiedviews/config/config.properties /unified-views/config/backend-config.properties
    printenv | grep -P "^UV_" | while read setting
    do
	key=`echo "$setting" | grep -o -P "^UV_[^=]+" | sed 's/^.\{3\}//g' | sed 's/_/./g' | awk '{print tolower($0)}'`
	value=`echo "$setting" | grep -o -P "=.*$" | sed 's/^=//g'`
	echo "Registering $key to be $value"
	echo "$key=$value" >> /unified-views/config/frontend-config.properties
	echo "$key=$value" >> /unified-views/config/backend-config.properties
    done
    touch /.settings_set
fi
echo "Finished converting environment variables to Java properties"


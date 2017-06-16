#!/bin/bash

chmod +x /config/env-to-java-properties-file.sh 

mkdir -p /etc/unifiedviews/config
mkdir -p /unified-views/config
cp /config/*.properties /unified-views/config/

ln -s /unified-views/config/config.properties /etc/unifiedviews/config/config.properties 

mkdir -p /unified-views/logs
mkdir -p /unified-views/dpu
mkdir -p /unified-views/lib
mkdir -p /unified-views/backend
mkdir -p /unified-views/backend/working/temp/export 
mkdir -p /unified-views/backend/working/temp/import
mkdir -p /unified-views/feeds
mkdir -p /unified-views/ttl
chmod -R 777 /unified-views

/config/env-to-java-properties-file.sh 


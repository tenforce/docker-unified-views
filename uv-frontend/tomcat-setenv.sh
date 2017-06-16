#!/bin/sh
if [ -f /config/frontend-config.properties ] ; then
    echo "using /config/backend-config.properties"
    JAVA_OPTS="${JAVA_OPTS} -DconfigFileLocation=/config/frontend-config.properties -Xmx1g -Xms512m"
else 
    echo "using /unified-views/config/backend-config.properties"
    JAVA_OPTS="${JAVA_OPTS} -DconfigFileLocation=/unified-views/config/frontend-config.properties -Xmx1g -Xms512m"
fi

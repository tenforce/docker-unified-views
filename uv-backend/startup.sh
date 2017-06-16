# ensure all directories are available and writable

mkdir -p /unified-views/backend/working 
mkdir -p /unified-views/dpu 
mkdir -p /unified-views/lib 
mkdir -p /unified-views/logs 
touch /unified-views/logs/backend.log
chmod 666 /unified-views/logs/backend.log
chmod 777 /unified-views/backend/working 
cp /backend.jar /unified-views/backend.jar

if [ -f /config/backend-config.properties ] ; then
    echo "using /config/backend-config.properties"
    java -DconfigFileLocation=/config/backend-config.properties -jar /unified-views/backend.jar 
else 
    echo "using /unified-views/config/backend-config.properties"
    java -DconfigFileLocation=/unified-views/config/backend-config.properties -jar /unified-views/backend.jar 
fi


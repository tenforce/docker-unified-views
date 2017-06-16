#!/bin/bash
# Wait till Tomcat startup has finished and webapps are started (max 3 minutes)
if [ "$UV_PORT" != "" ] ; then
   FRONTENDPORT=$UV_PORT
else 
   FRONTENDPORT=8080
fi

i=0
until $(curl --output /dev/null --silent --head --fail --user $MASTER_USER:$MASTER_PASSWORD http://$FRONTEND:$FRONTENDPORT/master/api/1/pipelines) || [ "$i" -gt 36 ]; do
    i=$((i+1))
    printf '.'
    sleep 5
done

# Add DPUs
for f in /dpus/*.jar; do bash /usr/local/bin/add-dpu.sh "$f"; done

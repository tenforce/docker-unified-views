#!/bin/bash

URL="http://127.0.0.1:8080/master/api/1/import/dpu/jar"
#MASTER_USER=master
#MASTER_PASSWORD=commander

echo "---------------------------------------------------------------------"
echo "Installing DPU.."
echo "..target instance: $URL"
echo "---------------------------------------------------------------------"

dpu_file=$(ls $1)

echo -n "..installing $dpu_file: "
outputfile="/tmp/dpu_out.out"

# fire cURL and wait until it finishes
curl --user $MASTER_USER:$MASTER_PASSWORD --fail --silent --output $outputfile -X POST -H "Cache-Control: no-cache" -H "Content-Type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW" -F file=@$dpu_file $URL?force=true 
wait $!

# check if the installation went well
outcontents=`cat $outputfile`
echo $outcontents

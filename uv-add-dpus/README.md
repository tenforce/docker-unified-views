# uv-add-dpus

A docker image to add dpus to your unified-views instance using the master api. 

Usage: 
Add all official dpus using `docker run --rm --link uv-frontend:frontend tenforce/uv-add-dpus` 
Add your own dpus using `docker run --rm --link uv-frontend:frotend -v /path/to/your/dpus:/dpus tenforce/uv-add-dpus`

Provide your master user and password using environment
#MASTER_USER=master
#MASTER_PASSWORD=commander

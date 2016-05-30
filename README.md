# Unified Views docker
Docker images for hosting Unified Views, as of version 2.3 this repository switched to a modular approach.

The following unified views dockers are provided:
 * [uv-shared](uv-shared): a data docker providing the shared libraries and configuration
 * [uv-frontend](uv-frontend): a docker image that runs the unified views frontend and master api, requires uv-shared and uv-backend
 * [uv-backend](uv-backend): a docker image that runs the unified views backend, requires uv-shared
 * [uv-mariadb](uv-mariadb): a docker image for mariadb that has the unified-views schema and required data preloaded
 * [uv-add-dpus](uv-add-dpus): a docker image that can be used to add dpus to unified-views via the master api


## Usage with uv-shared
The backend and frontend share a config file (`/config/config.properties`), libraries (`/unified-views/lib`) and a folder to store dpu's (`/unified-views/dpu`). We've provided a docker image that provides these volumes for you, so you can just run it and use [docker's volumes-from directive](https://docs.docker.com/engine/userguide/containers/dockervolumes/#creating-and-mounting-a-data-volume-container).

The unified-views-shared docker image runs a script on start that allows you to change the config file via the environment variables. The environment variable should be prefixed with UV_, in uppercase and all dots (.) in the property name should be converted to underscores (_). E.g. property foo.bar=baz should be configured as UV_FOO_BAR=BAZ. See [the config file](uv-shared/config/config.properties) for a complete list of properties.

Public images usage:

```
docker run --name uv-shared tenforce/unified-views-shared
docker run --name uv-mysql tenforce/unified-views-mariadb
docker run --name uv-backend --volumes-from uv-shared --link uv-mysql:mysql tenforce/unified-views-backend
docker run --name uv-frontend --volumes-from uv-shared --link uv-backend:backend --link uv-mysql:mysql tenforce/unified-views-frontend
```


Local images usage, assuming the docker images are created using as tag the directory name:
```
docker run --name uv-shared uv-shared
docker run -d --name uv-mariadb -e MYSQL_ROOT_PASSWORD=iamroot!  uv-mariadb
docker run -d --name uv-backend --volumes-from uv-shared --link=uv-mariadb:mysql uv-backend 
docker run -i --name uv-frontend --port 8080:8080 --volumes-from uv-shared --link uv-backend:backend --link uv-mariadb:mysql uv-frontend 
```

Problem: at this moment the tomcat is not running is a detached mode.


## Usage with host directories as a data volume
You can also choose to have the config, libraries and dpus available on your host system and mount them as data volumes. This may be more convenient if you often need access to the configuration file.
In this case copy the libraries and configuration to a local directories and mount them accordingly.

```
docker run --name uv-sql tenforce/unified-views-mariadb
docker run --name uv-backend -v /your/config/dir:/config -v /your/library/dir:/unified-views/lib -v /your/dpu/dir:/unified-views/dpu --link mysql:mysql tenforce/unified-views-backend
docker run --name uv-frontend -v /your/config/dir:/config -v /your/library/dir:/unified-views/lib -v /your/dpu/dir:/unified-views/dpu --link mysql:mysql tenforce/unified-views-frontend
```


## adding dpu's
By default the unified-views installation is provided without DPU's, DPU's can be added through the GUI or the master API. To quickly add a set of dpu's a convience docker image is provided. The following command will add all official DPU's to your unified-views installation.

`docker run --rm --link uv-frontend:frontend tenforce/unified-views-add-dpus` 
`docker run --rm --link uv-frontend:frontend uv-add-dpus` 

To add your own dpu's use the following command with your dpu directory.
`docker run --rm -v /your/dpu/directory:/dpus --link uv-frontend:frontend tenforce/unified-views-add-dpus` 

## Update the SQL connection details
By default the configuration from uv-shared is configured to connect to a mariadb as follows, this corresponds to the configuration of uv-mariadb.
```
database.sql.driver  =  org.mariadb.jdbc.Driver
database.sql.url  = jdbc:mariadb://mysql:3306/unified_views?characterEncoding=utf8
database.sql.user = unified_views
database.sql.password = s00pers3cur3
```

It is recommended to update these credentials, you can do so by running uv-shared with the following environment variables: `UV_DATABASE_SQL_USER`, `UV_DATABASE_SQL_PASSWORD` and for uv-mariadb use `MYSQL_USER` and `MYSQL_PASSWORD`. 

*NOTE*: Currently it is not possible to change the database name when using uv-mariadb

### use your own SQL service
If you want to use an existing SQL installation on your host, run the following scripts on a database of your choice:
  * [schema.sql](https://raw.githubusercontent.com/UnifiedViews/Core/UV_Core_v2.3.0/debian/unifiedviews-backend-mysql/src/deb/usr/share/unifiedviews/mysql/schema.sql)
  * [data-core.sql](https://raw.githubusercontent.com/UnifiedViews/Core/UV_Core_v2.3.0/debian/unifiedviews-backend-mysql/src/deb/usr/share/unifiedviews/mysql/data-core.sql)
  * [data-permissions.sql](https://raw.githubusercontent.com/UnifiedViews/Core/UV_Core_v2.3.0/debian/unifiedviews-backend-mysql/src/deb/usr/share/unifiedviews/mysql/data-permissions.sql)
  * [2.2.0-update.sql](https://raw.githubusercontent.com/UnifiedViews/Core/UV_Core_v2.3.0/debian/unifiedviews-backend-mysql/src/deb/usr/share/unifiedviews/mysql/2.2.0-update.sql)

Provide the correct connection details in uv-shared using the `UV_DATABASE_SQL_URL`, `UV_DATABASE_SQL_USER`, `UV_DATABASE_SQL_PASSWORD` and `UV_DATABASE_SQL_DRIVER` environment variables. 
*Note*: You can not use localhost in your SQL_URL as that will link to the running container and not your host machine. Use the --add-host directive to link a hostname to your machine.

For example
```
docker run --name uv-shared -e UV_DATABASE_SQL_USER=uv -e EV_DATABASE_SQL_PASSWORD=uv123 tenforce/unified-views-shared
docker run --name uv-backend --volumes-from uv-shared --add-host mysql:$(route -n | awk '/UG[ \t]/{print $2}') tenforce/unified-views-backend
docker run --name uv-frontend --volumes-from uv-shared --link uv-backend:backend --add-host mysql:$(route -n | awk '/UG[ \t]/{print $2}') tenforce/unified-views-frontend

```

## Integrating Virtuoso
If the resulting RDF should be written to a Virtuoso running in a Docker container it might be helpful to link the [Virtuoso container](https://hub.docker.com/r/tenforce/virtuoso/) to the Unified Views container using the option `--link my-virtuoso:virtuoso`.

If you want to use Virtuoso instead of the local RDF store as RDF platform to run the Unified Views pipelines, you have to configure the following environment variables: `UV_DATABASE_RDF_PLATFORM=virtuoso`, `UV_DATABASE_RDF_URL=jdbc:virtuoso://virtuoso:1111/charset=UTF-8/log_enable=2`, `UV_DATABASE_RDF_USER=dba`, `UV_DATABASE_RDF_PASSWORD=dba_pwd`.

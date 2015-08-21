# Unified Views docker
Docker for hosting Unified Views 2.1.0

## Database setup
Unified Views requires an SQL database to store its configuration. If you want to run the SQL database in a Docker container, you can use [MariaDB](https://registry.hub.docker.com/_/mariadb/).

    docker run --name my-mysql \
               -p 3306:3306 \
               -e MYSQL_ROOT_PASSWORD=password \
               -e MYSQL_USER=unified_views_user \
               -e MYSQL_PASSWORD=unified_views_pwd \
               -e MYSQL_DATABASE=unified_views_db \
               -d mariadb

The following scripts must be executed on the SQL database to create the required tables and populate the Unified Views database before the Unified Views container is started:
- [schema.sql](https://raw.githubusercontent.com/UnifiedViews/Core/UV_v2.1.0/db/mysql/schema.sql)
- [data-core.sql](https://raw.githubusercontent.com/UnifiedViews/Core/UV_v2.1.0/db/mysql/data-core.sql)
- [data-permission.sql](https://raw.githubusercontent.com/UnifiedViews/Core/UV_v2.1.0/db/mysql/data-permissions.sql)

## Running your Unified Views
    docker run --name unified-views \
        -p 8080:8080 --link my-mysql:mysql \
        -v /path/to/lib:/unified-views/lib \
        -v /path/to/dpus:/dpus \
        -e UV_DATABASE_SQL_URL=jdbc:mysql://188.12.34.56:3306/unified_views_db?characterEncoding=utf8 \
        -e UV_DATABASE_SQL_USER=unified_views_user \
        -e UV_DATABASE_SQL_PASSWORD=unified_views_pwd \
        -e MASTER_API_USER=master \
        -e MASTER_API_PASSWORD=mysecretpassword \
        -d tenforce/unified-views

Additional JAR libraries to be loaded on startup (for example DPU dependencies) should be mounted in `/unified-views/lib`. DPU JAR files mounted in `/dpus` will be installed automatically on container startup.

All properties defined in `frontend-config.properties` and `backend-config.properties` can be configured via the environment variables. The environment variable should be prefixed with `UV_`, in uppercase and all dots (`.`) in the property name should be converted to underscores (`_`). E.g. property `foo.bar=baz` should be configured as `UV_FOO_BAR=BAZ`. 

The Docker image exposes port 8080 (frontend) and 5001 (backend). The Unified Views GUI is available at http://docker-container-ip:8080/unifiedviews. The master REST API is available at http://docker-container-ip:8080/master. The credentials of the master REST API can be configured via the environment variables `UV_MASTER_API_USER` and `UV_MASTER_API_PASSWORD`. They default to `master` and `commander` respectively.

If the SQL database is setup using a Docker container, the container should be linked as `mysql` to the Unified Views container. The SQL connection details can be configured using the following enviroment variables (default value between brackets): `UV_DATABASE_SQL_URL` (`jdbc:mysql://mysql:3306/unified_views?characterEncoding=utf8`), `UV_DATABASE_SQL_USER` (unified_views), `UV_DATABASE_SQL_PASSWORD` (unified_views).1

If the resulting RDF should be written to a Virtuoso running in a Docker container it might be helpful to link the [Virtuoso container](https://hub.docker.com/r/tenforce/virtuoso/) to the Unified Views container using the option `--link my-virtuoso:virtuoso`. Moreover if you want to use Virtuoso instead of the local RDF store as RDF platform to run the Unified Views pipelines, you will have to configure the following environment variables: `UV_DATABASE_RDF_PLATFORM=virtuoso`, `UV_DATABASE_RDF_URL=jdbc:virtuoso://virtuoso:1111/charset=UTF-8/log_enable=2`, `UV_DATABASE_RDF_USER=dba`, `UV_DATABASE_RDF_PASSWORD=dba_pwd`


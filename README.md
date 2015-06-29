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
        -v /path/to/my/unified-views:/unified-views \
        -e MYSQL_HOST=188.12.34.56 \
        -e MYSQL_PORT=3306 \
        -e MYSQL_USER=unified_views_user \
        -e MYSQL_PASSWORD=unified_views_pwd \
        -e MYSQL_DATABASE=unified_views_db \
        -e MASTER_USER=master \
        -e MASTER_PASSWORD=commander \
        -d unified-views

The Unified Views folder is mounted in `/unified-views`. This folder should contain a `/dpu` folder with the DPUs and a `/lib` folder with the additional JAR libraries to be loaded on startup.

The Docker image exposes port 8080 (frontend) and 5001 (backend). The Unified Views GUI is available at http://docker-container-ip:8080/unifiedviews. The master REST API is available at http://docker-container-ip:8080/master. The credentials of the master REST API can be configured via the environment variables `MASTER_USER` and `MASTER_PASSWORD`. They default to `master` and `commander` respectively.

If the SQL database is setup using a Docker container, the container should be linked as `mysql` to the Unified Views container. The SQL connection details can be configured using the following enviroment variables (default value between brackets): `MYSQL_HOST` (mysql), `MYSQL_PORT` (3306), `MYSQL_USER` (unified_views), `MYSQL_PASSWORD` (unified_views) and `MYSQL_DATABASE` (unified_views).

If the resulting RDF should be written to a Virtuoso running in a Docker container it might be helpful to link the Virtuoso container to the Unified Views container using the option `--link my-virtuoso:virtuoso`.
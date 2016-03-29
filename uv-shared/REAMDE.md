# Unified Views Shared docker
Docker image that hosts shared data for the unified-views frontend and backend dockers.

## Typical use case

```
docker run --name uv-shared tenforce/uv-shared
docker run --name mysql tenforce/uv-mysql
docker run --volumes-from uv-shared --link mysql:mysql tenforce/uv-frontend
docker run --volumes-from uv-shared --link mysql:mysql tenforce/uv-backend
```

## Configuration
All properties defined in `config.properties` can be configured via the environment variables. The environment variable should be prefixed with `UV_`, in uppercase and all dots (`.`) in the property name should be converted to underscores (`_`). E.g. property `foo.bar=baz` should be configured as `UV_FOO_BAR=BAZ`. 

The credentials of the master REST API can be configured via the environment variables `UV_MASTER_API_USER` and `UV_MASTER_API_PASSWORD`. They default to `master` and `commander` respectively.

The SQL connection details can be configured using the following enviroment variables (default value between brackets): `UV_DATABASE_SQL_URL` (`jdbc:mysql://mysql:3306/unified_views?characterEncoding=utf8`), `UV_DATABASE_SQL_USER` (unified_views), `UV_DATABASE_SQL_PASSWORD` (unified_views). You can use a linked container for the SQL URL, make sure to link it correctly when running your frontend or backend unified-views container (eg --link mariadb:mysql in the example).


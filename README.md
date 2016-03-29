# Unified Views docker
Docker images for hosting Unified Views, as of version 2.3 this repository switched to a modular approach.

The following unified views dockers are provided:
 * [uv-shared](uv-shared): a data docker providing the shared libraries and configuration
 * [uv-frontend](uv-frontend): a docker image that runs the unified views frontend, requires uv-shared and uv-backend
 * [uv-backend](uv-backend): a docker image that runs the unified views backend, requires uv-shared
 * [uv-master](uv-master): a docker image that runs the unified views master api, requires uv-shared and uv-backend
 * [uv-mariadb](uv-mariadb): a docker image for mariadb that has the unified-views schema and required data preloaded



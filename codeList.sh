export EXTERNAL_PORT=8090
export APP_PORT=8080
export DB_USERNAME=admin
export DB_PASSWORD=admin

[ ! -z "$1" ] && export $1

docker-compose up -d
docker rm aplmu -f
docker rmi localaplmu

docker build -f localDockerfile -t localaplmu .
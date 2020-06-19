# aplmu_backend

Aplmu 项目 后端

## Docker 部署

```sh
docker rm aplmu -f
docker rmi 117503445/aplmu
docker run --name aplmu -d -e var1="var 1" -e var2="var 2" -p 80:80 --restart=always 117503445/aplmu:latest
```

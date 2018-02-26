# Project Setup

####To run dockerfile use following command
```
docker build --build-arg url=https://github.com/pandeyarpit88/jenkinsfile.git --build-arg project=demo --build-arg artifactid=demo --build-arg version=0.0.1-SNAPSHOT --build-arg app.port=9090 -t arpit/demo - < Dockerfile
```

````
docker run -ti -p9090:5000 arpit/demo
````




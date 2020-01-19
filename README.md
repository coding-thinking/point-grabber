# Point-grabber


### Prepared 

* jdk 1.8 +

### IDE

* Eclipse, IDEA, VSCode all support `gradle` IDE.

### Development


### Build

* build jar 

```
./gradlew clean build
```

* build image

```
./gradlew clean build docker
```

* build image and push to `snapshot`

```
./gradlew clean build -PimageRepo=snapshot dockerPush
```


* build image and push to `stage`

```
./gradlew clean build -PimageRepo=stage dockerPush
```

### Endpoint

* for local

> localhost:8080/point-grabber/swagger-ui.html


### Deploy

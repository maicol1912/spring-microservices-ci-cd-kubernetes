### JENKINS
#### ENVIROMENTS
- ARTIFACT_REGISTRY - nombre del repository de artifact registry :: spring-microservice-1
- CLUSTER - nombre del cluster de google cloud platform :: microservices-service-2
- PROJECT_ID - nombre del proyecto google cloud plaftorm que estamos trabajando :: starlit-link-395301
- REGISTRY_URL - nombre de la region del artifact registry :: us-central1-docker.pkg.dev
- ZONE - nombre de la region que estamos usando en gcp :: us-central1

#### SONARQUBE SERVER
- Name - Sonar
- Url - http://IP-maquina virtual:9000
- server autheticacion - credentials de sonar guardads en credentials

### CREDENTIALS
git - personal access token generado en github
gcp - secret file generado en gcp
starlit-link-395301 - Google Service Account from private key
sonar - token generado desde sonar desde el security de sonar :: myccount/security/token


### GITHUB
- personal access token
- webhook en cada proyecto 

### SONARQUBE
- webhook jenkins - administration/configuration/webhook/name- Jenkins / url- http://34.125.44.89:8080/sonarqube-webhook
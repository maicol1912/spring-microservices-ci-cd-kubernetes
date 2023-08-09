### GOOGLE CLOUD PLATFORM DEPLOY 
#### STEPS TO DEPLOY KUBERNETES ON GCP
- Enable kubernetes Engine Api 

- Artifact registry: / Create repository / Docker / Estandar / Region / claves administradas por google

- Create Vm Instance: Compute Engine/Create Instance / Permitir Http

- Instance created: Ssh / Open in browser window

- After open console in browser windows install dependencies 

- Nat Gateway: creamos un nat gateay sin configuraciones adicionales

- Firewall: debemos crear reglas las cuales permitan acceder a diferentes puertos/
 Create new rule/name rule /ipv4 origen 0.0.0.0/0/protocolos esperados TCP puerto a liberar

- Copiar la clave de administrador que se ve en esta ruta /var/lib/jenkins/secrets/initialAdminPassword

- Generar Token de accessos en Github para pdoer acceder a recursos /settings/developer settings
personal access tokens/ tokens/generate new token / put expiration /allow repo and admin:repo_hok

- despues debemos colocar el token en jenkins / manage jenkins/ credentials / global 
/new credentials/ usuario de github / colocamos el token en el password / id es git 

- IAM: debemos crear un service account /IAM /create new service account/ creamos / despues vamos a key
agregar kew/decargamos la key 

- configurar plugin en jenkins: manage jenkins /  plugin / available plugins / google kubernetes engine /instalar- credenciales en jenkins: manage jenkins/ manage credentials / global / add credentials
/ kind como secret file / id gcp

- credencials from google service: manage jenkins/ manage credentials / global / kind like
google service account from private key / subir archivo que descargamos de claves /  en project name debemos colocar el nombre del proyecto en gcp
/ my first project/name of the project 

- Kubernetes cluster : kubernetes cluster/ create /name / region /private cluster / crear

- Jenkins enviroment : manage jenkins / system / variables de entonorno -allow /
ARTIFACT-REGISTRY - nombre del artefcato de gcp - spring-microservices
CLUSTER - nombre del cluster de gcp - microservices-cluster-1
PROJECT_ID - nombre del proyecto en mi first project - starlit-link-395301
REGISTRY_URL - entramos al artifact registry y entrmaos al artfacto y copiamos la ruta de arriba para dejar la region - us-west4-docker.pkg.dev
ZONE - zona donde se esta ejecutando el artifacto - us-west4

#### NOTES ABOUT THE PROCESS
- Enable kuberenets Engine Api
- Artifact registry of Docker and making the configurations
- Create Vm Instance to Jenkins
- Abrir consola en navegador
- Instalar depenendencias
- sudo apt-get install openjdk-17-jdk
- curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key | sudo tee \
  /usr/share/keyrings/jenkins-keyring.asc > /dev/null
  echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null
- sudo apt-get update
- sudo apt-get install jenkins
- sudo apt-get install git
- sudo apt-get install kubectl
- Podemos acceder a jenkins por medio de la IP y el puerto de Jenkins 8080
- Primero debemos desbloquear el puerto para que el firewall no lo bloquee
- creamos instancia de nat-gateway para redireccionar peticiones a la maquinas virtuales
- Firewall: debemos crear reglas las cuales permitan acceder a diferentes puertos/
  Create new rule/name rule /ipv4 origen 0.0.0.0/0  /protocolos esperados TCP puerto a liberar para permitir que puedan consumir el puerto de jenkins desde cualquier instancia 
- Despues de esto podremos consumir el puerto 8080
- obtener la clave de administrador de la ruta b519ea95bd7a428faa509caa5293f66a
- Instalar pluggins sugeridos
- Crear usuario y contrasena 
- administrar jenkins / global tool configuration
- path git neceistmaos agregar el path del servidor lo podemos ver en / which git
- y descargamos automaticamente el maven

### FUNCION DE DOCKERHUB EN GOOGLE CLOUD PLATFORM
- en el artifact registry buscamos registrar todas las imagenes de nuestro docker para que estas esten privadas
- por eso enviamos la URL IMAGE_URL alli subimos las imagenes
- debemos tener los permisos de la app jenkins para poderlo hacer asi

### KUBERNETES CLUSTER 
- en workloads podemos ver los contenedores o los trabajos que fueron subidos desde jenkins
- en services and ingress podemos ver todos los servicios creados 


### Despliegue de Volumenes en GCP
En un principio debemos definir el PersistentVolume porque en PersistenceVolumeClaim
tenemos como manual y lo debemos definir pero como lo vamos a desplegar y vamos a trabajar con standar
podemos borrar PersistenceVolumeClaim y definir standar en PersistentVolumeClaim

#### TODOS ESTOS DETALLES SE PUEDEN VER DESDE KUBERNETES ENGINE / STORAGE

              apiVersion: v1
              kind: PersistentVolume
              metadata:
              name: postgresql-pw
              spec:
              capacity:
              storage: 1Gi
              accessModes:
              - ReadWriteOnce
              storageClassName: manual
              hostPath:
              path: "/run/desktop/mnt/host/c/temp/testfiles" # /mnt/data
              type: DirectoryOrCreate
        
    
              apiVersion: v1
              kind: PersistentVolumeClaim
              metadata:
              name: postgresql-pvc
              spec:
              resources:
              requests:
              storage: 1Gi
              storageClassName: standard
              accessModes:
              - ReadWriteOnce

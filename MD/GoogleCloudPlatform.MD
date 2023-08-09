## TOOLS NECESARIES TO DEPLOY MICROSERVICES IN CLOUD

### ARTIFACT REGISTRY
el artifact registry es un servicio de GCP en el cual se pueden registrar diferentes proyectos
como un tipo de github donde podemos crear un repositorio de tipo docker y alli almacenar dichas imagenes
- en Jenkins colocamos este enviroment de dicho repositorio para alli almacenar mis imagenes de docker
- para hacer el push debemos definir registry-url que es un nombre de archivo genero .dkg project-id que es el nombre del proyecto que estamos trabajando y el artifact registry que es el nombre del artefacto

### KUBERNETES ENGINE
Alli es donde se crean diferentes clusters donde corren nuestros pods y kubernetes
- este parametro tambien se pasa en jenkins para que se haga el despliegue del development en dicho cluster
- Podemos Conectarnos a la shell de cada cluster para validar instancias y cosas que debamos ver 

### VIRTUAL MACHINE
son maquinas virtuales que nos sirven para almacenar aplicaciones
- Usamos la maquina virtual praa correr Jenkins en el puerto 8080
- Usamos la maquina virtual praa correr sonar en el puerto 9000

### IAM
es el servicio de autenticacion donde podemos crear politicas de seguridad para nuestros proyectos


### FIREWALL
es el servicio que usamos para crera reglas de seguiridad como apertura de puertos y otras reglas

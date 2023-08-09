## ZIPKIN
es usado para llevar una trazabilidad de logs en todos los microservicios

## REDIS
se usa como una base de datos en memoria en este caso se usara para evitar el numero de peticiones por
segundo por usuario pra evitar ataques DDOS

# RATE LIMITER
rate limiter es un patron de diseño que se usa para definiri cuantas peticiones puede tener un 
usuario por segundo se define desde un bean de la aplicacion con el KeyRestore pero aparte de esto
necesitamos a Redis como dependencias para usarlo

# USAR LOS NUEVOS SERVICIOS ZIPKIN SLEUTH
para usar los serivicios como zipkin o redis debemos desplegar el docker con esas aplicaciones
y correrlas en el puerto que deberian correr originalmente

## SPRING SECURITY
se usa para proteger el api gateway
los microservicios tienen un scope interno para solo poder ser llamados desde un servicio en especifico

## UNIT TESTING
se usa para testear porciones de codigo
### COVERAGE 
aca podemos ver el cubrimiento de codigo que hace nuestro test se saca desde la calse de test y corremos run test with coverage

## Plugin JIB para hacer deploy en docker hub al compilar
debemos definir la dependencia y el plugin y debemos configurar
- config.json  - C:\Users\User\.docker\config.json y debe quedar asi
                            
                            {
                              "auths": {
                              "https://index.docker.io/v1/": {}
                              }
                              }
                            <plugin>
                            <groupId>com.google.cloud.tools</groupId>
                            <artifactId>jib-maven-plugin</artifactId>
                            <version>3.3.0</version>
                            <configuration>
                            <from>
                            <image>openjdk:17</image>
                            </from>
                            <to>
                            <image>registry.hub.docker.com/maicol1912/identityservice</image>
                            <tags>latest</tags>
                            <auth>
                            <username>maicol</username>
                            <password>Maico</password>
                            </auth>
                            </to>
                            </configuration>
                            </plugin>

## DOCKER
build image:  docker build -t maicol1912/serviceregistry:0.0.1 .
run conatiner:  docker run -d -p8761:8761 --name serviceregistry f567a8b3d3a6

### VOLUMES
podemos definir volumenes de docker para mantener la data anterior desde el dokcer compose
ademas de esto tambien podemos definir un sql que se ejecute desde los volumes

### Obtener Host docker container corriendo 
para enviar el host de nuestro servicio en un contenedor basta con colocar el nombre
- Healtcheck podemos agregar el condicional del depends_on y el healtcheck para que solo se corra
- si el healtcheck esta en status UP

### KUBERNETES

#### Contenedor: 
Un contenedor es una unidad de ejecución aislada que contiene una aplicación junto con todas sus dependencias y configuraciones. Kubernetes se basa en contenedores, generalmente en formato Docker.

#### Pod: 
Es la unidad básica de despliegue en Kubernetes. Un pod es un grupo de uno o más contenedores que se ejecutan juntos en el mismo nodo. Comparten recursos y red, y generalmente contienen una sola instancia de una aplicación.

#### Nodo (Node): 
Es una máquina física o virtual en el clúster de Kubernetes donde se pueden ejecutar los pods. Cada nodo tiene su capacidad de recursos (CPU, memoria, almacenamiento, etc.).

#### Clúster (Cluster):
Es un conjunto de nodos que trabajan juntos para ejecutar aplicaciones en Kubernetes. El clúster proporciona la infraestructura para la orquestación y la alta disponibilidad de las aplicaciones.

#### Kubelet: 
Es el agente que se ejecuta en cada nodo y se comunica con el servidor de control de Kubernetes. Es responsable de ejecutar y monitorear los pods en el nodo.

#### Kube-proxy: 
Es un componente que se ejecuta en cada nodo y gestiona el enrutamiento de red para los pods.

#### Servicio (Service): 
Es un recurso de Kubernetes que expone una aplicación como un servicio de red dentro del clúster. Permite la comunicación entre pods y el acceso externo a la aplicación.

#### Controlador (Controller): 
Es una parte fundamental de Kubernetes que se encarga de garantizar el estado deseado del clúster. Por ejemplo, el controlador de replicación se asegura de que el número de réplicas de un pod se mantenga según lo configurado.

#### Despliegue (Deployment): 
Es una abstracción que define el estado deseado de una aplicación y cómo se debe desplegar. Los despliegues gestionan actualizaciones y despliegues automáticos.

#### Namespace: 
Es un espacio virtual dentro de un clúster de Kubernetes que permite agrupar y aislar recursos en diferentes entornos.

#### Secrets: 
Son objetos de Kubernetes utilizados para almacenar información sensible, como contraseñas o tokens de acceso.

#### Etiqueta (Label): 
Es una clave-valor asociada a un recurso de Kubernetes, como un pod o un servicio. Se utilizan para identificar, filtrar y organizar recursos.

#### Ingress: 
Es un recurso de Kubernetes que administra el acceso externo a los servicios dentro del clúster. Permite la configuración de reglas de enrutamiento y balanceo de carga.


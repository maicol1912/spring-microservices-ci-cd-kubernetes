### JACOCO PLUGIN
- jacoco plugin sirve para generar un archivo el cual tiene el porcentje de cubrimiento y calidad de lso proyectos
- sonarqube tomara este archivo y lo evaluara dependiendo de los parametros definidos


### INSTALAR SONARQUBE DATABASE
- Este se instala en la misma maquina en donde esta jenkins
- se necesita crear una database de sonar para almacenar dicho requerimiento

- sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt/
`lsb_release -cs`-pgdg main" >> /etc/apt/sources.list.d/pgdg.list'

- sudo apt-get install wget

- wget -q https://www.postgresql.org/media/keys/ACCC4CF8.asc -O -
| sudo apt-key add -

- sudo apt install postgresql postgresql-contrib -y

- sudo systemctl enable postgresql

- sudo systemctl start postgresql

- sudo passwd postgres

- su - postgres

- createuser sonar

- psql

- ALTER USER sonar WITH ENCRYPTED password 'PostgresAdmin';

- CREATE DATABASE sonarqube OWNER sonar;

- GRANT ALL PRIVILEGES ON DATABASE sonarqube to sonar;

- \q

-exit 

### INSTALAR SONARQUBE

- sudo apt-get install zip -y

- sudo wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-8.9.9.56886.zip

- sudo unzip sonarqube-8.9.9.56886.zip

- sudo mv sonarqube-8.9.9.56886 /opt/sonarqube

- sudo groupadd sonar

- sudo useradd -d /opt/sonarqube -g sonar sonar

- sudo chown sonar:sonar /opt/sonarqube -R

- sudo nano /opt/sonarqube/conf/sonar.properties / descomentar 
sonar.jdbc.password= and sonar.jdbc.username=  // username es sonar, y password el que definimos en la db PostgresAdmin

- sonar.jdbc.url=jdbc:postgresql://localhost:5432/sonarqube agregr esta linea 2 espacios abajo del username y password

- sudo nano /opt/sonarqube/bin/linux-x86-64/sonar.sh

- colocamos esta linea RUN_AS_USER=sonar debajo de la linea DEF_APP_LONG_NAME

- sudo nano /etc/systemd/system/sonar.service

- pegar todo este codigo en el documento
[Unit]
Description=SonarQube service
After=syslog.target network.target
[Service]
Type=forking
ExecStart=/opt/sonarqube/bin/linux-x86-64/sonar.sh start
ExecStop=/opt/sonarqube/bin/linux-x86-64/sonar.sh stop
User=sonar
Group=sonar
Restart=always
LimitNOFILE=65536
LimitNPROC=4096
[Install]
WantedBy=multi-user.target


- sudo systemctl enable sonar

- sudo systemctl start sonar

- sudo systemctl status sonar

- sudo nano /etc/sysctl.conf

- pegar al final del archivo 
vm.max_map_count=262144
fs.file-max=65536
ulimit -n 65536
ulimit -u 4096

- sudo reboot esto lanzara un error


### CONFIGURACION DE FIREWALL

- debemos crear una regla la cual permita conectarse desde cualquier Ip al puerto TCP 9000

### DESPUES DE LIBERAR EL PUERTO 9000

- Ya tendremos disponible sonarQube

- tendremos que entrar con las credenciales por defecto admin- admin despues la debemos cambiar


### USAR SONARQUBE CON JENKINS

- vamos al panel de control de jenkins 

- Administrar jenkins/ plugins / availables / sonarQube Scanner/ install sin reiniciar

- Administrar jenkins/ credentials / global / add credentials / 

- sonar / perfile / my account / security / colocamos un nombre al token jenkinstoken lo copiamios

- jenkins / kind / secret text / secret- token copiado / Id - sonar

- jenkins / manage jenkins / system / sonarQube / add sonarQube / name -sonar / url - ip server:9090 / token - seleccionamos el token gaurdado de sonar en credentials

- Sonarqube / administration / configurations / webhooks / create / name - Jenkins / url: http://34.125.77.15:8080/sonarqube-webhook


### Adding sonar checks in pipeline

stage('Build'){
        sh "${mvnCMD} clean install "
    }
stage('SonarQube analysis'){
    withSonarQubeEnv('Sonar'){
        sh "${mvnCMD} sonar:sonar"
    }
}
stage('Quality gate'){
    waitForQualityGate abortPipeline: true

}

debemos agregar estos 3 stages


y despues de esto estamos listos para hacer el quality test y podemos ver el historia y las ejecuciones en el server de sonar
### HOW TO CREATE A JENKINS PIPELINE 
1. login
2. new Item
3. name item - ConfigServer
4. select pipeline 
5. check github Project and put the url - https://github.com/maicol1912/ConfigServer
6. select Github hook trigger for GITscm pullinf in the section buildTriggers
7. save
8. and pipeline is created 
9. // AFTER CREATGE WEBHOOK IN GITHUB MAKE THIS
10. go to pipeline/ go to script
11. select pipeline script from SCM
12. in SCM select git
13. add the repository URL
14. in credentials select github credentilas which created before
15. in branch to build select the branch which activate the trigger to generate new pipeline
16. in script path add the path of the jenkins file normally only Jenkinsfile
17. save
18. pipeline is created and can be started

#### Configurate webhooks in Github
1. go to project/ settings/ webhooks / add new webHook
2. IP Virtual Machine and port jenkins and /github-webhook/- http://34.125.44.89:8080/github-webhook/
3. content-type: Application/Json
4. go to configure/Api Token/ create new token/ put the name github
5. put the token in secret in github
6. add the webhook

### WHEN YOU HAVE THE PIPELINE READY 
- you start the pipeline it start to works
- you can check work historial
- you can check the logs in console Output
- IN IAM GCP -create a new role to the project jenkins and add basic editor permisos para subir el docker en el registry

### FUNCION DE DOCKERHUB EN GOOGLE CLOUD PLATFORM
- en el artifact registry buscamos registrar todas las imagenes de nuestro docker para que estas esten privadas
- por eso enviamos la URL IMAGE_URL alli subimos las imagenes 
- debemos tener los permisos de la app jenkins para poderlo hacer asi
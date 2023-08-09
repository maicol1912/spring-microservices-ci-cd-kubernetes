# COMANDOS MINIKUBE
status: minikube status
stop: minikube stop
start: minikube start
minikube dashboard

# COMANDOS KUBECTL
- kubectl get all: se usa para obtener informacion de todos los registros de kubernetes
- kubectl get all -o wide
- kubectl get pods
- kubectl get deployment
- kubectl get services
- kubectl get namespace
- kubectl delete all --all
- kubectl create deployment nginx --image=nginx
- kubectl describe deployment nginx
- kubectl exec -it nginx-748c667d99-5lwsg sh
- kubectl edit deployment
- kubectl delete deployment nginx
- kubectl apply -f .\deploy.yaml
- kubectl apply -f .\svc.yaml
- kubectl create namespace my-namespace
- kubectl apply -f .\svc.yaml -n my-namespace -especificar namespace
- kubectl get all -n my-namespace -  ontener los recursos de un namespace especifico

- kubectl exec -it <nombre-pod> -c <nombre-contenedor> -- /bin/bash
- kubectl logs <nombre-pod> -c <nombre-contenedor>
- kubectl logs <nombre-pod> 
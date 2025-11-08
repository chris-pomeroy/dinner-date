# Kubernetes

1) Build a dinner-date docker image and push to docker hub
```
docker build . -t <docker-username>/dinner-date
docker push <docker-username>/dinner-date
```
2) Set up a local k8s cluster
3) Spin everything up with the following commands:
```
kubectl create secret docker-registry regcred --docker-username=<your-name> --docker-password=<your-pword> --docker-email=<your-email>
cd manifests
kubectl apply -Rf .
```
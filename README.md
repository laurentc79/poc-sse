# poc-sse
POC Server Sent Event

## Back

Une fois l'application lancée, 2 points d'entrée sont disponibles :

- [GET] http://localhost:8081/api/v1/sse/subscribe

Permet de souscrire une écoute aux événements envoyé par l'api

- [POST] http://localhost:8081/api/v1/sse/publish

Permet de poster un message envoyé en json

## Front

2 commandes permettent le lancement et l'arrêt du serveur web :

- Démarrer le serveur web

```bash
make start
```

- Arrêter le serveur web

```bash
make stop
```

Ensuite on accède au site https://localhost/

## Description



La configuration du serveur nginx est importante, des paramètres permettent d'effectuer des écoutes SSE

```
    ...

    // permet de faire du ssl en hhtp/2
    listen       443 ssl http2;

    ...

    location /xxx {
        ...
        // ne pas retarder le flush du tampon nginx
        proxy_buffering off;
        proxy_cache off;
        // résoud le problème de convertion de l'event
        chunked_transfer_encoding off;
```
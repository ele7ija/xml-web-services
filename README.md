# XML i web servisi projekat

## Video prezentacija projekta
Dostupna na linku: [https://drive.google.com/file/d/1td-tMmIQNnalByMkL161-xuyEL-1qPpv/view?usp=sharing](https://drive.google.com/file/d/1td-tMmIQNnalByMkL161-xuyEL-1qPpv/view?usp=sharing)

## Pokretanje svega preko Docker-a
Obavezno pokrenuti u redosledu koji je naveden u ovom fajlu (zbog eksternih docker mreza)

### Pokretanje email servisa

1. Pozicionirati se u ./emailservice
2. Pokrenuti `docker-compose up`

### Pokretanje organa vlasti

1. Pozicionirati se u ./apiorganvlasti
2. Pokrenuti `docker-compose up`

frontend:
1. Pozicionirati se u frontend-organ-vlasti
2. Pokrenuti `npm install`
3. Pokrenuti `npm run serve`

### Pokretanje poverenika

1. Pozicionirati se u ./apipoverenik
2. Pokrenuti `docker-compose up`

frontend:
1. Pozicionirati se u frontend-poverenik
2. Pokrenuti `npm install`
3. Pokrenuti `npm run serve`

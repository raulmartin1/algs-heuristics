# 🤖 Pràctica 1: Cerca informada – Intel·ligència Artificial (IA)

Aquest repositori conté la implementació dels algorismes de cerca **A\*** i **Best-First Search** aplicats a la resolució de camins òptims en un mapa amb alçades, com a part de la **Pràctica 1** de l’assignatura d’Intel·ligència Artificial.

## 🧩 Descripció del problema

- El mapa és una matriu X×Y on cada cel·la conté una alçada.
- El cost del moviment depèn de la diferència d’alçades:
  - `1 + Δaltura` si pugem o ens mantenim.
  - `0.5` si baixem.
  - `∞` si hi ha un precipici (`-1`), no és transitable.
- Es pot moure **en horitzontal i vertical**, però **no en diagonal**.

## ⚙️ Algorismes implementats

- `AStar.java`: Cerca informada A* amb càlcul `f(n) = g(n) + h(n)`.
- `BFirst.java`: Cerca Best-First amb càlcul `f(n) = h(n)`.

## 🧠 Heurístiques

Totes implementen la interfície `IHeuristica.java`:

- `HManhattan`: Distància Manhattan + cost segons alçada.
- `HEuclidiana`: Distància Euclidiana + penalització.
- `HAltura`: Manhattan + variació d’alçades.

## 🧪 Execució

1. **Carregar el mapa:**
   ```java
   Mapa m = new Mapa();
   m.carregarMapa("fitxer.txt");

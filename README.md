![Book My Book logo](logo.png)

# Book My Book

Book My Book est une application Android conçue pour faciliter la gestion des prêts au sein de votre librairie de bouquins. Développée en utilisant le langage Kotlin pendant le module JAV1, elle offre une expérience conviviale et intuitive pour vous aider dans cette tâche.

## Application

Le code se trouve dans le dossier `bookmybook`.

### Comment l'essayer ?

Pour le tester, veuillez suivre les étapes suivantes :

1. Clonez ce projet sur votre machine.
2. Ouvrir le dossier `bookmybook` dans Android Studio
3. Assurez-vous de récupérer toutes les dépendances nécessaires en exécutant le fichier `build.gradle`.
4. Compilez le projet en effectuant un build avant de le lancer.
5. Enfin, lancez le projet pour commencer à l'utiliser et explorer ses fonctionnalités.

## Base de données

Au sein du répertoire `documentation/database`, vous découvrirez les schémas relatifs à la base de données :

- Modèle Conceptuel de Données (MCD)
- Modèle Logique de Données (MLD)

## UI

Au sein du répertoire `documentation`, vous découvrirez les éléments suivants concernant le design de Book My Book :

- Une chronologie des différentes versions des maquettes
- Une exportation locale depuis Figma

### Maquettes

En examinant chaque version, observons comment les maquettes ont évolué tout au long du développement du projet.

#### Version 1

Cette version représente la première conception du projet, réalisée avant le début du développement.

#### Version 2

La deuxième version marque un changement radical dans le design. Ce choix a été motivé par le constat que la [Version 1](#version-1) était incomplète et après quelques semaines de développement, j'ai rencontré plusieurs problèmes dans le code. J'ai donc pris la décision de recommencer le projet depuis le début.

Le design de cette version s'inspire de l'application iOS de Spotify, apportant une esthétique similaire et une expérience utilisateur optimisée.

#### Version 3

La troisième version apporte quelques modifications mineures, telles que la correction des titres et l'ajout de quelques champs manquants, notamment dans le formulaire d'ajout d'un livre.

#### Version 4

La quatrième version introduit une nouvelle interface qui permet d'afficher en détail les informations d'un livre. Cette fonctionnalité offre aux utilisateurs la possibilité d'accéder facilement et rapidement à toutes les informations pertinentes concernant un livre spécifique. Cela améliore la convivialité et l'utilité de l'application en fournissant une vue détaillée et organisée des données relatives aux livres.

#### Version 5

La version finale du projet, la cinquième version, apporte deux modifications importantes pour améliorer l'expérience utilisateur. Tout d'abord, l'affichage des prêts a été simplifié en supprimant l'image de couverture du livre. Cela permet de mettre en avant les données pertinentes du prêt de manière plus claire et concise.

De plus, le choix d'un livre dans le formulaire d'ajout d'un prêt a été simplifié. Désormais, seuls le titre et l'auteur du livre sont affichés, facilitant ainsi la sélection rapide et précise d'un livre parmi les options disponibles.

Ces changements visent à rendre l'application plus conviviale, en éliminant les éléments superflus et en mettant en avant les informations les plus importantes pour les utilisateurs lors de la gestion des prêts de livres.

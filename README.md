# TDVB Android application
Application to consult tdvb series

## Sommaire

- [Participants](#participants)
- [Description](#description)
  - [Fonctionnalités attendues](#fonctionnalités-attendues)
  - [Attentes spécifiques](#attentes-spécifiques)
  - [API](#api)

## Participants
- Antoine PELLETIER

## Description

Application mobile Android qui s’appuiera sur l’API du service TheTVDB.com.
Cette application implémentera les méthodes proposées par cette API et proposera une ergonomie simple d’utilisation.

### Fonctionnalités

Voici les fonctionnalités principales attendues au sein de l’application :
- Se connecter / Se déconnecter : Attention la connexion nécessite la création au
préalable d’un compte sur theTVDB
- Consulter mon profil
- Accéder au menu permettant de consulter les différents écrans
- Afficher les séries mises à jour dernièrement sur la page d’accueil
- Rechercher de séries
- Voir le détail d’une série
o Notes : la note theTVDB et celle de l’utilisateur o Épisodes
o Casting
o Images
o Poster
- Mettre en favoris une série
- Noter une série
- Partager une série
- Consulter la fiche détaillée d’un acteur via une webview (lien IMDB)
- Mise en cache des données
Les textes de l’application devront être consultable en anglais et en français, pas les textes provenant de theTVDB
Fonctionnalité bonus
- Pouvoir changer de langue pour les données liées aux séries



### Attentes spécifiques

Version d’Android
API 19 minimum
Organisation
- Architecture MVC
- Organisation en packages de vos classes
- Stockage des données via une base de données SQLite
- Le nom des classes et attributs exclusivement en Anglais
- Utilisation cohérente de Styles, Strings et Colors
- Internationalisation
Expérience utilisateur
- Utilisation de fragments et d’Activity
- Une expérience utilisateur simple et intuitive
Livrables
- Codes sources
- Accès au repository GIT dès le début de projet: vathenbala@digipolitan.com
- APK pour mobile

### API

https://api.thetvdb.com/swagger#

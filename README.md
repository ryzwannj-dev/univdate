# 🎓 UnivDate — Application de rencontres universitaires  

**UnivDate** est une application mobile pensée pour les étudiants qui souhaitent élargir leur cercle social, faire de nouvelles rencontres et partager des centres d’intérêts communs au sein ou autour de leur université.  
Elle combine un design moderne et intuitif avec un backend sécurisé pour gérer les profils, les photos et les préférences de rencontre.  

---

## ✨ Fonctionnalités principales  
- **Création de profil complet**  
  - Email étudiant et mot de passe sécurisés  
  - Photo de profil, pseudo, date de naissance et localisation  
  - Informations universitaires (nom de l’université, filière)  
  - Bio et centres d’intérêts personnalisables  
  - Pièce d’identité et carte étudiante pour vérification  

- **Connexion sécurisée**  
  - Authentification via PHP/MySQL avec mot de passe hashé  
  - Validation de l’email étudiant pour plus de fiabilité  

- **Gestion des préférences**  
  - Genre  
  - Type de rencontre  
  - Sexe recherché  

- **Upload d’images**  
  - Gestion et stockage des photos sur le serveur  
  - Protection des chemins et gestion d’upload multiple  

---

## 🛠️ Stack technique  
- **Frontend** : Android (Java/Kotlin, XML)  
- **Backend** : PHP 8 + MySQL  
- **Serveur local** : XAMPP + phpMyAdmin (migrable vers un hébergement distant)  
- **Communication** : API REST (JSON)  
- **Sécurité** :  
  - Hashage des mots de passe avec `password_hash()`  
  - Validation côté serveur et client  
  - Protection contre les doublons d’email  

---

## 📌 Objectif du projet  
Créer une application fiable, fluide et sécurisée dédiée aux étudiants, permettant non seulement les rencontres amicales ou romantiques, mais aussi la découverte de nouveaux horizons au sein de la vie universitaire.  

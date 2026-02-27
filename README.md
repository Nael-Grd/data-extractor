# Multi-Format Data Miner (Template Method Pattern)

***FR* Documentation (ENG below)**

Ce projet est une application Java implémentant le **Design Pattern Template Method**. L'objectif est de proposer un moteur de recherche capable d'extraire et d'analyser du texte à travers différents formats de fichiers (CSV, PDF, DOCX).


## Fonctionnalités
* **Extraction Multi-Format** : Support des fichiers `.csv`, `.pdf` et `.docx` 
* **Moteur de Recherche** : Identification des occurrences d'un mot-clé avec affichage du numéro de ligne.
* **Interface Graphique (Swing)** : Interface avec sélecteur de fichiers, barre de recherche et affichage des résultat.

## Installation et Démarrage
### Prérequis
* Java 17+ 
* Bibliothèques incluses dans le dossier `/lib` (Apache PDFBox, Apache POI)

> [!IMPORTANT]
> **Utilisateurs WSL2 / Linux (sans écran local)** : 
> Pour afficher l'interface graphique, vous devez utiliser un serveur X (comme **XLaunch / VcXsrv**) sur votre machine hôte Windows.

**Configuration XLaunch recommandée :**
1. Sélectionnez **Multiple windows**.
2. Sélectionnez **Start no client**.
3. **Important** : Décochez **Native OpenGL** et cochez **Disable access control**.

### Lancement
```bash
# 1. Compilation
javac -cp ".:lib/*" abs/*.java concr/*.java App.java

# 2. Détection de l'affichage (WSL)
export DISPLAY=$(route.exe print | grep 0.0.0.0 | head -1 | awk '{print $4}'):0.0

# 3. Exécution
java -cp ".:lib/*" App
```
---

**ENGLISH VERSION**

This project is a Java application implementing the **Template Method Design Pattern**. The goal is to provide a search engine capable of extracting and analyzing text from various file formats (CSV, PDF, DOCX).


## Features
* **Multi-Format Extraction**: Supports `.csv`, `.pdf`, and `.docx` files.
* **Search Engine**: Identifies occurrences of a keyword and displays the line number.
* **Graphical User Interface (Swing)**: Interface with file selector, search bar, and results display.

## Installation and Startup
### Prerequisites
* Java 17+
* Libraries included in the `/lib` folder (Apache PDFBox, Apache POI)

> [!IMPORTANT]
> **WSL2 / Linux users (without a local display)**: 
> To display the GUI, you must use an X server (such as **XLaunch / VcXsrv**) on your Windows host machine.

**Recommended XLaunch configuration:**
1. Select **Multiple windows**.
2. Select **Start no client**.
3. **Important**: Uncheck **Native OpenGL** and check **Disable access control**.

### Launch
```bash
# 1. Compilation
javac -cp “.:lib/*” abs/*.java concr/*.java App.java

# 2. Display detection (WSL)
export DISPLAY=$(route.exe print | grep 0.0.0.0 | head -1 | awk ‘{print $4}’):0.0

# 3. Execution
java -cp “.:lib/*” App
```

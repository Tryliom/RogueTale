# Build

## Software

1. Download [JarSplice](http://www.java2s.com/Code/Jar/j/Downloadjarsplice040jar.htm)

## How to build

1. In Eclipse, go to File > Export... > Java > JAR file
    1. Select RogueTale as the resource to export
    1. Uncheck files at right (readme, docs, ...)
    1. Select the export destination (chemin complet + nom du fichier)
1. Open JarSplice
    1. In "Add JARS", add :
        * the previous exported jar 
        * all the jars in the libs folder of the project
    1. In "MAIN CLASS", put "ch.cpnv.roguetale.main.Main"
    1. It is now possible to create the jar, or to create an executable for a particular OS
    
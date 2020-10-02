# Build

## Software

1. Download [JarSplice](http://www.java2s.com/Code/Jar/j/Downloadjarsplice040jar.htm)

## How to build

1. In Eclipse, right click on project > Export... > Select JAR file
  * Uncheck files at right (readme, docs)
  * Finish
2. Open JarSplice
3. On "Add JARS", add the previous exported jar and all the jars in libs folder in project
4. On "Add NATIVES", go to libs\native folder, open each folder and select all files inside.
5. On "MAIN CLASS", put "ch.cpnv.roguetale.main.Main"
6. Here you can create your Jar or create executable for Windows, Mac or Linux
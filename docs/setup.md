# Setup environement

## Eclipse

1. Go to [Eclipse download page](https://www.eclipse.org/downloads/)
2. Download Eclipse IDE
3. Run the downloaded installer, and install `Eclipse IDE for Java Developers`

## Maven

1. Download Maven [here](https://maven.apache.org/download.cgi)
2. Install Maven using [these instructions](https://maven.apache.org/install.html)

## Import project in Eclipse

1. In Eclipse, go to File menu > Open projects from file system... > Directory > select the folder of the project

## Import libraries

1. Project > Properties > Java build path > Build path > Libraries > Add externals jars > Select all .jar in libs folder
2. After importing lwjgl, click on the plus button or arrow button, select Native library location > Edit > select the folder for your OS in native folder
3. After importing slick, click on the plus button or arrow button, select Javadoc location > Edit > select the folder slick_docs in libs folder

## Update Maven dependency

1. On Eclipse, right click on project > Maven > Update project...

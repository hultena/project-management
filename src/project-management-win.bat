set mypath=%cd%
javac -cp "./;./lib/jackson-annotations-2.9.7.jar;./lib/jackson-databind-2.9.7.jar;./lib/jackson-core-2.9.7.jar;./lib/jansi-1.15.jar;./lib/JCDP-2.0.3.1.jar;./lib/jackson-datatype-jsr310-2.9.8.jar" ProjectManager.java

java -cp "./;./lib/jackson-annotations-2.9.7.jar;./lib/jackson-databind-2.9.7.jar;./lib/jackson-core-2.9.7.jar;./lib/jansi-1.15.jar;./lib/JCDP-2.0.3.1.jar;./lib/jackson-datatype-jsr310-2.9.8.jar" ProjectManager template.json

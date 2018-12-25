javac -cp "./:./lib/jackson-annotations-2.9.7.jar:./lib/jackson-databind-2.9.7.jar:./lib/jackson-core-2.9.7.jar" ProjectManager.java;

java -cp "./:./lib/jackson-annotations-2.9.7.jar:./lib/jackson-databind-2.9.7.jar:./lib/jackson-core-2.9.7.jar" ProjectManager $1;

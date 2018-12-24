# Team programming | DIT092

##  Our goal
Make it easier to manage software projects by providing an easy to use and lightweight tool aimed at developers.

## Usage
#### Unix
    git clone https://github.com:bartekspitza/project-management.git ~/project-management
    cd ~/project-management/src
    javac -cp "./:./lib/jackson-annotations-2.9.7.jar:./lib/jackson-databind-2.9.7.jar:./lib/jackson-core-2.9.7.jar" ProjectManager.java
    java -cp "./:./lib/jackson-annotations-2.9.7.jar:./lib/jackson-databind-2.9.7.jar:./lib/jackson-core-2.9.7.jar" ProjectManager template.json 
or:
    git clone https://github.com:bartekspitza/project-management.git ~/project-management
    cd ~/project-management/src
    chmod +x project-management.sh
    ./project-management.sh your_data.json
    
### Windows
todo
    

## Requirements
* Java SE
* JSON file according to specification ([Look here for template](https://github.com/bartekspitza/project-management/blob/master/template.json))

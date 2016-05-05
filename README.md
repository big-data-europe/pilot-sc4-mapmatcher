MapMatch 
========
Match locations to streets. 

## Description
This application is a client of a Rserve in which an R script has been imported with the functions that provide the map matching algorithm.
Rserve is used to call R function from java. The R script are imported in the docker container with Rserve.
##Documentation 
The application is based on Rserve (https://rforge.net/Rserve/)
##Requirements 
A docker container of pilot-sc4-docker-r (https://github.com/big-data-europe/pilot-sc4-docker-r) with Rserve, the map matching script and 
the geographical area of interest must be started.
##Build 
The software is based on Maven and can be build from the project root folder simply running the command

    $ mvn install

##Install and Run 
The build creates a jar file with all the dependences and the configuration of the main class in the target folder. The application with the command

    $ java -jar target/pilot-sc4-mapmatcher-0.0.1-SNAPSHOT-jar-with-dependencies.jar <data_set_url> <Rserve_host> <Rserve_port>

If no arguments are passed the application will use default values.
 
##Usage 
This application is used to test the integration of R scripts in Java code for the SC4 Pilot. 

##License 
TBD


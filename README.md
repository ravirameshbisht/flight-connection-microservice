# Flight Connection Microservice
This repository contains the source code for an airport flight connection builder. Flight
Connection Builder is used to find all possible flights arriving to a particular city which can be
connected with all flights departing from the same city. For example, if you want to travel from
Mumbai (BOM) to New York (JFK) via London (LHR), the system finds all possible flights arriving
to London from Mumbai which can connect with all flights departing from London to New York City.
The application has Connection Builder Microservice, Master Data Microservice, Spring Cloud Api
Gateway Microservice and ReactJS app Web. The Connection
Builder Microservice owns all the business logic to build the flights according to the requirement.
Master Data Microservice owns all data about an airport such as airport code, airport name, airport
city, airport coordinate, and flight schedule from one airport to other airports. I have used Event
Driven Architecture using RabbitMQ so the microservices are loosely coupled. I have also applied
TDD using Mockito.

## Prerequisites
- Java SDK (version 11 or higher)

## Running Using Docker Compose
- Download or clone the repository and ```cd``` to the repo and run the command:
- ```docker-compose up --build```
- RabbitMQ runs on port 5672 and Consult runs on port 8502 so be informed if they are already running locally at the same port as it will cause port already in use problem.
- navigate your browser to http://localhost:3000/ 
- Before searching for connection flights, new Airports, and Flight Schedules need be to created first

## Installation
1. Clone the repository or download and extract the archive file to your local directory.
2. Build each SpringBoot microservice by running the command ```./gradlew clean bootJar```. This will create a modern cloud native fully self-contained JAR file.
3. Install the dependencies for the frontend by running ```npm install --legacy-peer-deps```. The ```--legacy-peer-deps``` flag is used to solve a possible conflicting peer dependency error.
4. Start the frontend by running ```npm start```.




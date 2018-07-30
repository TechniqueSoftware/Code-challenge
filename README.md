# Code-challenge

## Getting started
Clone the repo and navigate to the binaries folder and run the self contained jar by executing the following commands in your command terminal

```
  cd binaries;
  java -jar web-server-1.0.0.jar;
```

This is start a local web server which opens port 9999 and you should see and output like this

```
28 May 12:22:45,413  INFO CheckinService:27 - Checkins loaded from file
28 May 12:22:45,657  INFO MemberService:27 - Members loaded from file
28 May 12:22:45,853  INFO ClubOSResourceConfig:38 - Registering com.technique.code.challenge.checkins.api.CodeChallengeResource@6ed3ccb2
28 May 12:22:45,873  INFO ClubOSResourceConfig:38 - Registering com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider@1a677343
May 28, 2018 12:22:46 PM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [0.0.0.0:9999]
May 28, 2018 12:22:46 PM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer] Started.
Jersey app started with WADL available at http://0.0.0.0:9999/api/application.wadl
Hit ctrl-c to stop it...

```

To test the server is functioning as expected and to get the API documentation navigate to the following URL 

```
  http://localhost:9999/api-docs/index.html
```

## Code Challenge Instructions
The server you are currently running contains two endpoints checkins and members.
The checkins end point contains checkins for 10 locations from 2018-01-01 to 2018-04-01.
The members end point contains all the members for each location and their membership type.

For this challenge you will need to answer the following questions

1. What is the busiest hour in the day for checkins across all locations?
2. What is the busiest day of the week for checkins? E.g Monday 6 am or Friday 3pm
3. What is the most common agreement type for members that checkin on Tuesdays?
4. What is the most common agreement used for each location on Fridays?

### Notes
The code for the server is provided but no questions about the code itself will be answered. This is because all the information you need to answer the coding challenge's questions should be documented in the api docs or in this README.

### What is a good submission?

1. Self contained code. 
Your submission will be able to be compiled and run with a single main file. 
E.g does not require a local running database or multiple commands to run.

2. Is fast. 
Your submission will be judged against others based on how fast your code can answer the 4 question in section [Code Challenge Instructions] .
Your code will be run against multi gigabyte data set so please keep that in mind.

3. Is correct. 
Your code submission will be run against several small data sets to ensure your algorithms accuracy. 

4. Have fun :) 
These are they types of problems you will solving day to day as a software engineer at Club OS so we hope you enjoy this small challenge since there will be many to come 

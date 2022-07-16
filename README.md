# Intuit Principal A4A

This craft contains 2 sub-projects:
1. intuit-twitter-sentiment-analysis - An Apache Flink pipeline that consumes tweets from tweeter API and does some filtering + sentiment analysis and persists the tweets along with their sentiment analysis into MongoDB.
2. intuit-twitter-api - Spring boot REST API for integration with Intuit customer service applications.


## High Level Architecture

![Principal A4A - Impl](https://user-images.githubusercontent.com/9553244/179374218-ac371819-20c7-4c93-8117-fc61908b4c2b.png)


## How to run

1. Install MongoDB
2. create 'test' DB
3. create 'tweets' collection
4. create user for the flink app and the spring app (update the creds in the application.properties file)
5. run each project

* Unfortunately, I didn't have enghout time to dockerize the stack. Sorry for the manual installation.

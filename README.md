# TimeForFood

#### Java app for ordering food in restaurants

**Technologies:** Java 11, CQRS,Kafka, Event Sourcing, Docker, Kubernetes

**About:**

There are several microservices that are divided by business tasks. Each service has its own database. 
Microservices design patterns are used for communication and maintaining consistency. 
Each microservice is represented as a separate git repository. 
The container is build from the maven repository, where the versioned jars are stored.

### Consumer: https://github.com/Chundrikov/Consumer
### Order: https://github.com/Chundrikov/Order

![Illustration to the project](https://avatars.mds.yandex.net/get-zen_doc/4470750/pub_60605db39a49cb03ec86959c_60605e97188a9f7359a4cf73/scale_1200)

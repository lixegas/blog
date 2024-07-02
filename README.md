# Blog
### Overview

This is the repository containing the source code for my Blog.

#### Built with
[![My Skills](https://skillicons.dev/icons?i=java,spring,mysql&theme=light)](https://skillicons.dev)


## Installation
### Getting the files and the requisites
Clone the repository:

    git clone https://github.com/lixegas/blog.git
Go to the project directory and install the necessary dependencies using Maven:

    cd your_directory
    mvn clean install

### Configuration
Before starting the app, we have to do a little bit of configuration.

#### Connecting to the database
Inside the application.yaml there are two fields that need to be modified in order to connect our application to our database.

The first one:

    datasource:
      url: jdbc:connector_://address:port/database
      username: your_username
      password: your_passowrd

#### Using the JWT tokens.
Inside the application.yaml file, there is also a field for RSA keys used by our JWT encrypter and decrypter.

    rsa:
      private-key: "classpath:certs/private.pem"
      public-key: "classpath:certs/public.pem"
 You will need to generate an asymmetric key. Just use a tool like OpenSSL.
  
### Usage
Start the application:

    mvn spring-boot:run
      
Access the application in your web browser at http://localhost:8080 or the port you specified in the application.yaml file, and you're ready to go

# RUN
mvn clean package
http://localhost:8080/api

## run app via nohup
nohup java -jar /home/workspace/spring-daily-menu-zomato-api/target/zomatoapi.jar 2>log.out &
tail -f nohup.out

## kill app
ps -ef | grep java
kill 9 {id}

## ---------------------------------------

# UNIT TEST

## integration test
mvn clean package verify

## test
mvn clean package test


# zomato

https://developers.zomato.com/documentation?lang=cs#!/restaurant/search

## url
https://developers.zomato.com/api/v2.1/dailymenu?res_id=16506246

## headers
Accept: application/json
user_key: 8911dd7bb2b15478a236b1c549dba198

user key is hash generated on the https://developers.zomato.com/api?lang=cs


# jackson-objectmapper
http://tutorials.jenkov.com/java-json/jackson-objectmapper.html

http://tutorials.jenkov.com/

https://www.journaldev.com/2324/jackson-json-java-parser-api-example-tutorial

http://websystique.com/java/json/jackson-convert-java-object-to-from-json/

https://springframework.guru/processing-json-jackson/

https://dzone.com/articles/a-regulatory-framework-for-facebook-and-other-plat

https://www.javacodegeeks.com/2013/01/json-deserialization-with-jackson-and-super-type-tokens.html

# thymeleaf
https://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html
http://www.baeldung.com/spring-thymeleaf-3-expressions

# spring cache
http://www.baeldung.com/spring-cache-tutorial


# google map
https://developers.google.com/maps/documentation/javascript/get-api-key

# charts
http://www.chartjs.org/docs/latest/developers/api.html
http://www.chartjs.org/samples/latest/

# bxslider
https://bxslider.com/install/

# jsonschema2pojo
http://www.jsonschema2pojo.org/

# apifaketory
https://apifaketory.docs.apiary.io/#reference

#Swagger 2
http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api

http://localhost:8080/v2/api-docs

## without spring security
http://localhost:8080/swagger-ui.html





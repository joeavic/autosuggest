# Auto Suggest / Type-Ahead using spring boot - redis sorted sets

required 

- java
  - java -version
- maven
  - mvn -v
- redis
  - redis-cli

Steps to run locally

1. git clone git@github.com:joeavic/autosuggest.git
2. cd python_script 
3. python3 auto_suggest.py
4. cd .. 
5. mvn spring-boot:run
6. test using curl localhost:8080?q=<query>

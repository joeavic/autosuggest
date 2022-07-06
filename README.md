# autosuggest

required 

- java
  - java -version
- maven
  - mvn -v
- redis
  - redis-cli

Steps to run locally

1. clone repository - git clone git@github.com:joeavic/autosuggest.git
2. cd python_script and run python3 auto_suggest.py - this will create redis sorted sets for autocomplete
3. cd .. and run mvn spring-boot
4. test using curl localhost:8080?q=<query>

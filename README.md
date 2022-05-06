# sda-application-transporeon-template-software-testing-advanced

## ENDPOINTS
- GET All Weather records
  - GET http://localhost:8080/api/weather

- GET Weather for City
  - GET http://localhost:8080/api/weather/London/

- GET Weather for City and Date
  - GET http://localhost:8080/api/weather/London/2022-05-05 23:00:00

- GET City with Highest Temperature
  - GET http://localhost:8080/api/weather/temperature/highest

- POST Weather for City
  - POST http://localhost:8080/api/weather/withCityName
  - { "cityName": "London", "date": "2022-05-05" }

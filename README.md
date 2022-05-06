# Software Testing Advanced - Template

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

## EXERCISES

1. Create a test(s) which checks if the communication with the database works correctly.
2. Create a test(s) which checks if the communication with the WeatherAPI.
3. Create a test(s) which checks the behaviour of the public methods.
4. Implement the methods described in the WeatherController.
5. Create a test(s) which checks the behaviour of the implemented methods and its dependencies.
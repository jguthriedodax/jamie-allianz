# jamie-allianz

This is my test project for the Allianz technical test

## Run Application
In order to run the application:
1. Clone the app to your machine - `git clone git@github.com:jguthriedodax/jamie-allianz.git`
2. Run `mvn compile` in the git directory
3. Run `mvn exec:java -Dexec.mainClass="main.Application"`

Now the app should be accessible at `localhost:8080`

## Commands/endpoints

There are 4 main endpoints groups
* `/city`
* `/district`
* `/sensor`
* `/reading`

Each of those can then be followed by a number of more specific endpoints
* `/all` - this retrieves all of the cities/districts/sensors etc that the user is authorized to retrieve
* `/{id}` - this retrieves the city/district etc with the specific id

There are also endpoints to add / delete records. More detail on these can be found looking in the controllers.

### Example API calls
* `GET localhost:8080/districts/all` will return a list of all the districts and any nested elements (i.e. sensors and readings) that the user is authorized to see
* `GET localhost:8080/reading/23` will return sensor reading with id: 23

## Authentication
The example accounts are set up to respond to `Basic Authentication`. The three available username:password combos are `barcelonaUser:barcelona123`, `wienUser:wien123`, and `leedsUser:leeds123`.
Each endpoint will only return data that is permitted for each account

## Curl examples
`curl -u leedsUser:leeds123 -i -H 'Accept:application/json' http://localhost:8080/city/all` - returns all city data and readings for Leeds

`curl -u barcelonaUser:barcelona123 -i -H 'Accept:application/json' http://localhost:8080/district/3` - returns a 401 error as the user for Barcelona is not permitted to access district 3

`curl -u wienUser:wien123 -i -H 'Accept:application/json' http://localhost:8080/district/3` - returns all data for district with id 3 (Währing in Wien)

## Remarks about the code
* At the moment the json serialization is slightly problematic. I tried to make it so that the parent object is only displayed for the root element, (and any child elements don't display their parent object, which is always redundant data),
 however the serialization needs work. At the moment I can only get it to either display parent elements for all objects, or no parent elements at all. I have left it displaying parent elements 
 for now so that more data can be seen. To disable that, in the various `Serializer` classes, `printParent = false` can be set.
 
* Authentication isn't done properly using Spring-Auth. Instead I manually check the username/password for each request using a HandlerInterceptor filter, as authentication isn't based on allowing certain endpoints, but rather allowing certain data within each endpoint.

* New readings are generated automatically every 20 seconds in the `GeneratorService` class - good for testing


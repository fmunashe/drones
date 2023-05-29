How to run the app.

Navigate to the root directory of the code base, where there is the POM file. Inside this directory, execute the following command to run the application: mvn clean spring-boot:run

Assumptions

Can only register a drone in IDLE state.
Scheduled task runs after every 10 minutes.
Weight is measured in Kilograms.
Can only load a drone in IDLE, LOADING states.
Medication Image: I am storing the actual image on disk, only the image name is saved in the DB.
Since the api must return JSON, in case of Medication Image, instead of returning the Base 64 encoded string, we return the name;
Preloaded Medication [

{ 
"code":"MED001", "image":"med1", "name":"Paracet", "weight":"0.45"
}, 
{
"code":"MED002", "image":"med2", "name":"CMP", "weight":"2.33"
},
{ 
"code":"MED003", "image":"med2", "name":"Brufen", "weight":"1.21"
}

]
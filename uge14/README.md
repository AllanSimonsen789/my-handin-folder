# --------Monday---------------
## OPGAVE: React Router - getting started

### Task 1 - 8
-completed all
The code can be seen here ->my-handin-folder -> uge14 -> reactrouterdemo -> src
alternatively this deployed project can be found her: https://hangovergaming.surge.sh/


## OPGAVE: React Router with Nested Routes

### Task 1 - 9
-completed all
The code can be seen here ->my-handin-folder -> uge14 -> demomonday -> src
alternatively this deployed project can be found her: https://hangovergaming2.surge.sh/

## OPGAVE: React Router "everything you need to know"


# --------TUESDAY---------------
% Completed wednesday!


# --------Wednesday---------------
## OPGAVE: A JWT-based Authentication Strategy for a REST-API.

### Task backend
-completed all greens
the code for handling passwords can be found here ->my-handin-folder -> uge14 -> rest-jpa-devops-startcode -> src -> main -> java -> entities -> User.java. The set password and constructor methods hashes the password which is then saved in the database. we use the imported compare method to compare when trying to login

#### Endpoints secured
we cannot access the endpoints without correct authorisation or we get this:
  "code": 403,
  "message": "Not authenticated - do login"

  with correct login we get a token returned
  we can use this token to log into the corresponding endpoints. example admin we get:
  "msg": "Hello to (admin) User: admin"

#### What is the advantage (if any) for a REST-based API of using JWT’s compared to session Cookies
The advantage is that all systems can use JWT where as session cookies are for browsers. example another rest service wouldnt be able to connect and use our endpoints.
#### What is the disadvantage (if any) with the implemented JWT-solution
Its not totally secure.
#### What will a client (Single Page WEB, Mobile App, etc.) have to do in order to use this API
make and read json. the login are sent as json and the token is sent back as json. the client also needs to send this token as a header for all furture request.

### Task frontend
-completed all greens
the react project ->my-handin-folder -> uge14 -> authreact -> src

Questions:

#### Did this logout involve the server
No the login token is removed locally in the localstorage
#### Is the token (if kept somewhere, still valid?)
yes we can see the token is still valid in postman(it only expires after 30 min)
#### If your  answer to the question above was yes, is this a problem?, and if, how could it have been solved?
First of all we save it in localstorage so we expect that the user secures their own pc. and the token can only be seen if someone has access to their computer. we use https to transfer so we expect this data so be secure. we could also set the time from 30min to a little bit less than 30 min.

#### How username and password are passed to server
Its passed as a request payload which is json with username and password. we can see this in the login request
#### How the server sets the CORS headers
We can see this in the response from our login request. the response headers:
Access-Control-Allow-Credentials: true
Access-Control-Allow-Headers: Origin, Accept, Content-Type, Authorization,x-access-token
Access-Control-Allow-Methods: GET, POST, DELETE, PUT
Access-Control-Allow-Origin: *
#### How the server returns the token (in the Response)
we get a json response which include the token that we can use
#### How we (you) attach the token to the outgoing request up against the protected endpoint
we save the token in localstorage and pass it with future request as x-access-token


# --------Thursday---------------
## OPGAVE: External “Momondo-like” Requests From our Servers

### Task 1 External requests and performance 	
-completed all, also red 
The tester class has the runSequential and runParralel methods as requested in the assignment
the code can be found here ->my-handin-folder -> uge14 -> webscraber-momondo-demo -> src -> main -> java -> webscraper ->tester.java

### Task 2 Creating an endpoint using one of the versions from above	
-completed all 
same project as before
the endpoint can be found here ->my-handin-folder -> uge14 -> webscraber-momondo-demo -> src -> main -> java -> rest
the counter and tagDTO can be found here: ->my-handin-folder -> uge14 -> webscraber-momondo-demo -> src -> main -> java -> webscraper

### Task 3 Rest/Json based example
completed 1-4 (not completed 5 and 6)
you can the test class in the jokeFethcer package. all the DTOs are in the DTO package. and the endpoint can be found in the rest package.
the project can be found here: ->my-handin-folder->uge14->jokeFetcher-momondo-demo->src->main->java

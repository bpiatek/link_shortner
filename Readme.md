Link shortener project
=

This is my small project which is trying to mimic behavior of a platform like 
bitly.com. The general idea is that you send a request with your original
whatever how long link and in return you get your shortened version 
which is easier to share with others.

Project is in **early development** phase.

Inspiration
-
I was inspired by Jakub Nabrdalik talk titled **Hexagonal Architecture in practice**, 
which represents clean and easy to to maintain architecture based on Facade Pattern
and heavy use of package private classes. It sounds really promising.

Tech
-
The project as it stands is based on:
* SpringBoot 2
    * Data
    * Security
* Java 11
* Lombok
* Swagger 2
* H2
* Yet Another UserAgent Analyzer
* Jenkins
* Junit 5 with AssertJ
* Mockito

The application is deployed on VPS where you can access it from the internet.
There is a simple Jenkins pipeline for deploying application on the server
as well as PR checks on github.

Functionality
-
At the moment there is not to much functionality implemented.
* there are some CRUD operations for:
    * creating shortened links 
    * finding all links or by id
    * checking where shortened link leads to
    * redirecting to original site based on shortened link  

The redirecting part is an interesting one, because I decided to collect information
about the useragent (phone, operating system etc.) of the user who used the shortened
link. Each time the link is clicked the counter of visits increments and the event is 
emitted about that action, which then triggers parsing user agent information and saving
them in DB.   

In the future I want users to be able to register and menage their own links, that is why
spring security is already partially implemented with 2 hardcoded users.

Links 
-
If you want to play with the api here is the link to swagger:
<a href="http://bpiatek.pl:8080/swagger-ui.html" rel="nofollow">http://bpiatek.pl:8080/swagger-ui.html</a></h1>

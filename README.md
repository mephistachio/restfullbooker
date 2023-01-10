Restful-booker API Test Automation



#### Installation

1. [Instal git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
2. Start git, inialiaze and run next command in git: ```git clone https://github.com/mephistachio/restfullbooker```
3. [Install Maven](https://maven.apache.org/install.html) Repository 

#### To run tests execute next in command line:

```mvn clean test```

or

right mouse click on testng.xml in the project and select ```Run '...\testng.xml'```



Followind test-cases are covered:
- GetBookingIds
- PartialUpdateBooking
- DeleteBooking


P.S.: 
- There is some hardcoded new parameter for PartialUpdateBooking test - to solve.
- The date in post is incorrectly displayed - todo

#Robot Cleaners Application

##Overview
This application is designed to follow the movement and final positions of robot cleaners on a grid according to certain
instructions. It is implemented using Kotlin and follows Hexagonal Architecture to an extent, given the simplicity of
the application.

##Architecture
###Hexagonal Architecture
The application follows Hexagonal Architecture. I have left aside certain aspects of it due to the application being
simple enough, namely the Infrastructure (since we don't have any data persistance) and the Application (since we don't
depend on REST APIs or any other external sources). Thus, we are left with the following elements of Hexagonal
Architecture:

- **Domain**: Contains the domain models, rich enough so they contain their own logic.
- **Adapters**: Handle interaction with the outside world (in our case, this would be reading input from files and 
generating an output from it).

###About the Domain Model
The application employs a rich domain model to encapsulate business logic within the domain objects themselves. This
approach ensures certain things:

- **Encapsulation**: Business rules and constraints are enforced within domain objects, making everything more cohesive.
- **Maintainability**: The domain logic is closely tied to the data it manipulates, which makes the code easier to 
understand and modify.
- **Flexibility**: Changes in business logic are localized within the domain layer, reducing the impact on other parts 
of the application.

##How to Run

First, build the project with the following command:
`./gradlew build`

Then execute the main application file.
`./gradlew run`

For running the tests, you can use the following command:
`./gradlew test`

The application will be running with a sample set of instructions. Please read Assumptions and Limitations for a better
understanding of how the instructions work, so you can modify them and play with them a little to test the application.

##Testing
The application includes unit tests to validate the functionality of the RobotCleanerService. The tests cover the
following cases:

- Processing instructions and generating correct output.
- Handling of empty input and invalid cases.
- Validation of business rules and constraints.

##Assumptions and Limitations
Input Specifications: For an example of input specificacions, please check sampleInstructions.txt, a file located in the
resources folder of the project. With it, we can assume certain things:
- The first line of the commands will always contain the dimensions of the grid that the robot is cleaning.
- From then, a correct set of instructions will contain a couple of lines for each robot. The first one will be the
initial position of the robot, with the first number expressing its X axis, the second one stating its Y axis, and then
a letter matching its facing, corresponding with a cardinal point (N, E, W or S). The second line of the couple will be
one or more letters out of a selection of three: L (the robot will change its facing by rotating 90º to the left), R
(with the same behavior but rotating to the right), and M (the robot will advance forward in the grid, taking into
account its current facing).

Error Handling: The application throws exceptions for invalid input formats or missing commands.

##Author
[Juan Gabriel Martínez]
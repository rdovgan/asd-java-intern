# README #

This project was designed for Internship process to help each intern to achieve certain skills with specific tools/technologies. 

### What is this repository for? ###

* To train your skills
* To learn you some specifics in practice
* To show you how to work with GIT and review process

### How do I get set up? ###

Environment: Linux/MacOS, Java 8, Maven, GIT, IDEA
<br>
Configure Code Style Scheme for IDEA with [template](data/idea-formatter.xml) in `/data` project folder
<br>
Change default maven user setting file to [provided](data/nexus-settings.xml) in `/data` project folder to use Nexus repository

### Workflow ###

* Create a personal working branch with template `implementation/{your_surname}`. 
* Create a package with your surname for implementation. For example, for `services` implementation you should create directory: `team.asd.services.{your_surname}`.
* Implement an interface in your package. You can commit your changes per implemented method or by implemented class. *But do not several class implementation into one commit.*
* Before or after you made a commit, pull changes from `master` branch. This is a required step.

**All your changes should be committed only in your working branch.**

> ##### Where I should start?
>
> Check `team.asd.services.IsConverterService`. It's a good point to start. Create a class in your own package with name `ConverterService` and try to implement all methods from the interface. Then check your implementation with requirements (javadocs) and make first commit. Push your changes and try to make a PR.

### Pull requests ###

* After you fully-implement one of classes, you can create a Pull-request to check your implementation with JUnit tests.
* Choose your working branch and `test` branch as target.
* Add all mentors as a reviewers.
* Wait for a build result and check Slack channel or Pipeline tab in BitBucket.
* After at least one approval, you can merge your PR.



### Additional information ###

* You should NOT change any interface.
* No need to create own implementations for all interfaces at one. Do it step by step, class by class. Go ahead to the next interface implementation after tests will pass for the previous.
* You can create own JUnit tests to check your services.
* If you found a bug or misspells, please contact with your mentor.
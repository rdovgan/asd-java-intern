# README

This project was designed for Internship process to help each intern to achieve certain skills with specific tools/technologies.

### What is this repository for?

* To train your skills
* To learn you some specifics in practice
* To show you how to work with GIT and review process

### How to set up project?

Setup environment: Linux/MacOS, Java 11, Maven, GIT, IDEA
<br>
Fork [this](https://github.com/rdovgan/asd-java-intern) repository
<br>
Configure GitHub Action
<br>
Configure Code Style Scheme for IDEA with [template](data/idea-formatter.xml) in `/data` project folder
<br>
Change default maven user setting file to [provided](data/nexus-settings.xml) in `/data` project folder to use Nexus repository

### Set up your repository

#### Enable `Issues` feature

* Go to your GitHub forked repository
* Go to `Settings` tab
* On `General` page, scroll down to `Features` block
* Check `Issues` option

#### Configure default issue Assignee

* Go to your GitHub forked repository
* Go to `Settings` tab
* On `General` page, scroll down to `Features` block
* Click `Set up templates` button on `Issues` area
* Select `Preview and edit` button for `Bug` template and click pencil button near the `Issue: Bug` template name
* Scroll to the bottom of template to `Assignees` section and click `assign yourself` link or choose yourself from dropdown
* Do the same with `Feature` template
* Submit template by `Propose changes` button

#### Set up your deployment

* Go to your GitHub forked repository
* Choose `Action` tab
* Find `Java with Maven` configuration option and click `Configure`
* Check script workflow but do not change anything
* Press `Start commit` and commit directly to the master branch

#### Protect your `master` branch

* Go to your GitHub forked repository
* Choose `Settings` tab
* Go to `Branches` item in `Code and automation` block
* Add branch protection rule by clicking button `Add rule`
* Set branch name pattern as `master`
* Check `Require a pull request before merging` option and `Dismiss stale pull request approvals when new commits are pushed` in the same block
* Click `Create` at the page bottom

#### Add all mentors as collaborators

* Go to your GitHub forked repository
* Choose `Settings` tab
* Go to `Collaborators` item in `Access` block
* Click `Add people` block and set your mentors usernames
* Submit by clicking `Select a collaborator above`

### Workflow

* Get a ticket from your mentor
* Create a working branch with template `implementation/{ticket-id_ticket-name}` for [FEATURE] type and `fix/{ticket-id_ticket-name}` for [BUG] type
* [Pull changes](#how-to-fetch-changes-from-parent-repositorys-specific-branch) from specific branch in parent repository (if provided)
* Implement an interface in `service` package
* Commit your changes per implemented method or by implemented class. *But do not combine several class implementation into one commit*
* Before you make a commit and after, fetch changes from parent repository's `master` branch. This is a required step

**All your changes should be committed only in your working branch. Do not use master branch for direct commits. Add your changes only by pull requests**

### Pull requests ###

* After you fully-implement one of classes, you can create a Pull-request to check your implementation with JUnit tests.
* Choose your working branch and `master` branch as target.
* Add all mentors as a reviewers.
* Wait for a build result or check email or Action tab in GitHub.
* After at least one approval from mentor, you can merge your PR.

### How to fetch changes from parent repository's specific branch

* Go to your GitHub forked repository
* Choose `Pull requests` tab
* Click `New pull request` button
  ![Fetch branch from parent repository](data/pull_parent_branch.png?raw=true "Fetch branch from parent repository")
* Click `compare actoss forks` link (1)
* Choose your repository and branch (not `master`) as a base and parent repository with specific branch as a head (2)
* Create and merge a pull request (3)

> ##### Where I should start?
> Check `team.asd.service.IsConverterService`. It's a good point to start. Create a class in your own package with name `ConverterService` and try to implement
> all methods from the interface. Then check your implementation with requirements (javadocs) and make first commit. Push your changes and try to make a PR.

### Additional information ###

* You should NOT change any interface.
* No need to create own implementations for all interfaces at one. Do it step by step, class by class. Go ahead to the next interface implementation after tests
  will pass for the previous.
* You can create own JUnit tests to check your services.
* If you found a bug or misspells, please contact with your mentor.

A social platform for matching people with projects.

## 1.0 Minimum Viable Puppy

People can create project descriptions.

People can indicate their interest in a project.

People can recommend each other to projects in public.

People can recommend projects to each other in private.

People can create a public skill profile for themselves.

## 1.1 First User Feedback

Project creators can mark recommended people as selected participants.

People can indicate that they aren't interested in a project.

Project creators can archive project descriptions.

## 1.2 Availability and vacations

People can indicate periods of availability on their calendars.

People can indicate their travel capability.

People can indicate their vacation plans on their calendars.

## 2.0 Curriculum Vitae

People can create a CV for themselves.

People can print out each others' CVs.


# How to run the program

```
mvn clean spring-boot:run
```

Point your browser to http://localhost:9090/


# How to develop the frontend and backend without having to do complete rebuilds

1. Start the backend with IDEA using the `main` runner.

2. In the IDEA terminal, run `ng serve --watch` which will continuously rebuild the frontend.

3. Reload http://localhost:9090/ to refresh the frontend.

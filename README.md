# NotesApi
## OTA Initial Coding Challenge

## Dependencies:
Java: v17
SpringBoot: v3.2.3

## To run app on VSCode:
* Make sure you have [Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) for Java plugin installed.
* Make sure you have Java version 17 installed in your machine and JAVA_HOME configured correctly.
* Once you have completed the above requirements, you can now run the application in VSCode.

## Use [Postman](https://www.postman.com/) for testing API endpoints.

## Example Http Requests:
* GET http://localhost:8080/notes
* POST http://localhost:8080/notes
    example request body:
        {
            "title": "Note 1",
            "body": "This is a note!"
        }
* GET http://localhost:8080/notes/:id
* PUT http://localhost:8080/notes/:id
    example request body:
        {
            "title": "Note 3",
            "body": "This is changed!"
        }
* DELETE http://localhost:8080/notes/:id


# Help Center Hub

## About the Project
The Help Center Hub is a help desk developed as part of a software development challenge. This system allows users to log in to open tickets and rate the service received. Operators can log into the system to support users, receive real-time updates on requested tickets, and close services. Tickets can be listed with pagination and filtered by subject or ID.

## Technologies Used
- **Backend:** Java 17, Spring Boot 3
- **Frontend:** Angular 17 (standalone: true)
- **Database:** PostgreSQL 16
- **Authentication:** OAuth2.0 with Google authentication
- **Real-Time Communication:** SSE (Server-Sent Events)
- **API Documentation:** Swagger

## Project Structure
- **Backend:** Located in the `backend` directory, using Clean Architecture with the entities User, Ticket, TicketFeedback, TicketUpdate.
- **Frontend:** In the `frontend` directory, built with Angular 17 in standalone mode.
- **Database:** Uses PostgreSQL 16, with tables being automatically created when the service is started through Docker.

## DevOps Environment & Kubernetes
The `diagrama.png` file illustrates how the Help Center Hub platform functions within a DevOps environment utilizing Kubernetes. It shows the structure of the systems and how they are deployed and managed to ensure scalability and reliability.

## Running the Project with Docker
This project is configured to be easily run using Docker and Docker Compose. To do this, make sure you have Docker and Docker Compose installed on your system.

1. Clone the repository to your local machine.
2. Navigate to the root folder of the project.
3. Run the command: `docker-compose up --build`.
    - This command will build the necessary images for the backend, frontend, and database, and will start the services.
4. After the services start, the frontend will be accessible via `http://localhost:4200` and the backend through `http://localhost:8080`.
5. The API documentation can be accessed via Swagger at `http://localhost:8080/swagger-ui.html`.

## Authentication
- For regular users: Authentication is performed via the standard Google OAuth2.0 process.
- For manager-type users: It is necessary to directly register the same email used with Google into the database. This is required to authenticate and access manager-specific functionalities within the system.

## CI/CD Pipeline Steps
The CI/CD pipeline, defined in the `.gitlab-ci.yml` file, automates the process of running tests and preparing the application for deployment. The pipeline includes the following steps:

1. **Dependency Installation:**
   - Installs all the necessary dependencies for both the backend and frontend parts of the project.

2. **Unit Testing:**
   - Executes unit tests to validate the integrity and reliability of the code for both backend and frontend.
   - Ensures that the new changes do not break existing functionalities.

3. **Build:**
   - Compiles the backend source code into an executable `api.jar` file.
   - Prepares the frontend for deployment by running the build process.

4. **Artifact Storage:**
   - After a successful build and test run, the `api.jar` file is stored as a downloadable artifact within GitLab.
   - The `api.jar` can be downloaded from the 'Jobs' page of the CI/CD pipeline in GitLab. Look for the latest successful build job with artifacts and download the `api.jar` from there.


## Real-Time Communication
The backend uses SSE to communicate real-time updates to the frontend. This is especially useful for operators to follow tickets in real time.

## Contributions
Contributions are always welcome! To contribute, please fork the repository, make your changes, and submit a pull request.

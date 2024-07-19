# American Auction Backend

This is the backend for the American Auction system, built with Django Rest Framework.

## Requirements

- Docker desktop: 
    
    https://docs.docker.com/desktop/install/windows-install/
- Git: 

    https://git-scm.com/downloads

## Setup and Running the Server

1. Clone the project:
   ```sh
   git clone https://github.com/MockProject052024Nhom6/MockProject_052024_Group6.git

   cd MockProject_052024_Group6

   git checkout dev-python

   cd backend
2. Environment Variables:

    Create a `.env` file in the root directory with the following content:

    ```env
    DEBUG=True

    DB_ENGINE=django.db.backends.mysql
    DB_NAME=american_auction
    DB_USER=root
    DB_PASSWORD=root
    DB_HOST=db
    DB_PORT=3306

    ADMIN_EMAIL=admin@gmail.com
    ADMIN_PASSWORD=1234root
3. Build and run the Docker containers (Initial setup):

    ```sh
    docker-compose up --build
4. Run run the Docker containers (Run server):

    ```sh
    docker-compose up
5. Access the application:

    http://localhost:8000

6. Access API docs (Swagger), Admin site:

    http://localhost:8000/swagger/

    http://localhost:8000/admin/

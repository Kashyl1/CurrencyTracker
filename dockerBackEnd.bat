call docker build -t backend-image .
call docker run -d --name backend-container -p 8080:8080 backend-image
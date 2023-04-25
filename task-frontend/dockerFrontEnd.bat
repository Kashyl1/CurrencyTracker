call docker build -t frontend-image .
call docker run -d --name frontend-container -p 3000:3000 frontend-image
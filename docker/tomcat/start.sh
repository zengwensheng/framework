docker build -t zws/tomcat:8.0 .

docker run -it --rm -p 8888:8080 zws/tomcat:8.0
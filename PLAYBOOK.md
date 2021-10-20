## COS 460/540 - Computer Networks
# Project 2: HTTP Server

# Paul Keffer

This project is written in Java on macOS.

## How to compile

I'm using Java version 17. To compile make sure your current directory is the project-2-http-server-paulkeffer folder.
Run the below command
javac myHTTPServerStuff/*.java

## How to run

Run the below command in the same directory as above
java myHTTPServerStuff.HTTPServer <port> <root directory>
You can replace <port> with the port you want it to run on, and replace <root directory> with the document root that you want to use.
## My experience with this project

Fill in here a brief summary of your experience with the project. What did you learn?
I learned how to write an HTTP server. In my testing i've found that it works but when I load a directory (not a file) without a trailing / in the URl, when it comes up with the existing index.html file, the images dont load, but when I add a trailing / it works fine. Sililarly when loading a file, if I dont include a trailing / it works fine, but if I do then the images dont load. I could only determine that the resources were trying to be loaded from the wrong location.

Read Me
--------------------------
We are developed this solution with the help of Google Vision API's.
So to run this project you should configure Google Vision account setup in your machine.
 
We have divided this project into 3 modules for dividing the functionalities
	1. dunzo
	2. google-vision_lib
	3. DatabaseManager
	
1. dunzo 			 : This module is a Spring boot application. Having Sring MVC, exposes REST API's for the Image upload and fetching data from DB.
2. google-vision_lib : This module contains the Google Vision library/API's. 
					   "dunzo" module uses this google-vision_lib to analyse the uploaded image to extarct the text.
3. DatabaseManager   : This module is for interaction with Dadabase. Here we used msql database.

Steps to execute the project
--------------------------
1. Import all the modules into eclipse IDE
2. Open the file "MySqlQueries.txt" in "DatabaseManager" module with contains SQL Queries to create DB and Tables for this project.
3. Open MySql admin console and run the above SQL Queries
4. Add "google-vision_lib" and "DatabaseManager" as project dependecy to "dunzo" module
5. Add "DatabaseManager" as project dependecy to "google-vision_lib" module
6. Run Maven build on all projects
7. Run the "DunzoApplication.java" class from "dunzo" module to start the backend API's
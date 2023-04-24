# CurrencyTracker_Dynatrace

Internship task for Dynatrace

This application is a simple runnable local front-end and back-end server, that exposes some endpoints which take arguments and return plain simple data after performing certain internal operations. The goal is to query data from the Narodowy Bank Polski's public APIs and return relevant information from them. 

I used two servers in my project. One is responsible for the front-end, the other for the back-end. Both servers must be started to run the application. The front-end is written using react in Java Script and the back-end in Java


# Table of contents
[Java installation](#java-installation)

[Docker installation](#docker-installation)

[Maven installation](#maven-installation)

[Node.js installation](#node-installation)

[Docker Desktop installation](#docker-installation)

[Operations](#operations)

[Information](#examples)


# Installations

Below you can see how to install my app step by step

<a id="java-installation"></a>

# Java


1. Open your terminal 

2. Clone my github repositorium

`` git clone https://github.com/Kashyl1/CurrencyTracker_Dynatrace ``

3) Go to the project directory

`` cd CurrencyTracker_Dynatrace ``

4) Execute backEndStart script to create Jar file and turn on back-end server. Make sure that you have downloaded [Maven](#maven-installation)

``backEndStart.bat``

If you have already done it once and want to run the program, run this script:

 `` backStart.bat `` 

5) Go to cd task-frontend and execute frontEndStart script to install libraries and modules needed for the frontend application to work
   Make sure that you downloaded [Node.js](#node-installation)

``frontEndStart.bat``

If you have already done it once and want to run the program, run this script:

 `` frontStart.bat ``
 
6) Open your browser and go to:

http://localhost:3000

<a id="docker-installation"></a>

# Docker

1) Open your terminal

2) Clone my github repositorium

`` git clone https://github.com/Kashyl1/CurrencyTracker_Dynatrace ``

3) Go to the project directory

`` cd CurrencyTracker_Dynatrace ``

4) Create Back-end image and create container by executing a script. Make sure that you downloaded [Docker Desktop](#dockerDesktop-installation). on your device

``dockerBackEnd.bat``

 If you have already created image once and want to run the program, run this script:
 ``dockerBackStart``

5) Create Front-end image and container.

a) go to:

``cd task-frontend``

and execute this script:

``dockerFrontEnd.bat``

If you have already created image once and want to run the program, run this script:

 ``dockerFrontStart``
 
 6. Open your browser and go to:
 
 http://localhost:3000

 
<a id="maven-installation"></a>
# Maven installation
 
To download Maven, you can follow these steps:

1) Go to the Maven website: https://maven.apache.org/download.cgi
2) Under "Files", find the latest version of Maven and click on the link to download it.
3) Once the download is complete, extract the downloaded archive to a directory of your choice.
4) Set the environment variable MAVEN_HOME to the directory where Maven was extracted.
5) Add the bin directory of the Maven installation to your system's PATH variable.
6) To verify that Maven was installed correctly, open a command prompt and type mvn -version. If Maven was installed correctly, you should see the version of Maven displayed in the output.

<a id="node-installation"></a>
# Node installation
 
To download Node.js, follow these steps:

1) Go to the Node.js website at https://nodejs.org/en/
2) Click on the "Download" button for the recommended version of Node.js
3) Select your operating system and click on the appropriate download link
4) Once the download is complete, run the installer and follow the installation prompts
5) After the installation is complete, open a terminal or command prompt and type "node -v" to verify that Node.js was installed correctly.

<a id="dockerDesktop-installation"></a>
# Docker Installation

To download Docker, follow these steps:

1) Go to the official Docker website: https://www.docker.com/products/docker-desktop
2) Click the "Get Started" button and select the appropriate version for your operating system.
3) Follow the installation instructions for your operating system.
4) Once the installation is complete, open a terminal window and verify that Docker is installed by running the command "docker version".
If Docker is installed, you should see version information for both the client and server.
 
 <a id="operations"></a>

# Operations

Given a date (formatted YYYY-MM-DD) and a currency code (list: https://nbp.pl/en/statistic-and-financial-reporting/rates/table-a/), provide its average exchange rate.

Given a currency code and the number of last quotations N (N <= 255), provide the max and min average value (every day has a different average).

Given a currency code and the number of last quotations N (N <= 255), provide the major difference between the buy and ask rate (every day has different rates).

 <a id="examples"></a>

# Information

If you did everything according to the instructions you should be able to see a page with three tabs

First one "Get Exchange Rate" is responsible for showing the user information about the average exchange rate of the value for the day
If you click it you will see two labels "Currency code" when user should imput the currency code of the value for example:

U.S. Dollar currency code is "USD" so you should input "USD"

Second label is "Date" this is the date that will show the user the price of the currency on that day

Second tab "Get Max and Min Exchange Rates" is responsible for getting the highest and lowest exchange rate by x quotations
If you click it you will see "Top count" label which is our quotation that must be less than 255 and the previous currency code

for example: 

Currency code: USD

Top count: 232

It will show you the maximum and minimum exchange rates from the last 232 quotations

Third tab "Get Major Difference Between Bid and Ask" is responsible for getting the major difference from x quotations.
If you click it you will see previous top count and currency code

for example: 

Currency code: USD

Top count: 232

It will show you the Major difference between bid and ask from the last 232 quotations

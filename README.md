# BSC

Java application to process packages their weights and prices.

To build app run `gralew.bat build` on Windows or `./gradlew build` on Linux.

The application may take two command line arguments: 
* file with weights and postal codes
* file with weights and prices

Then it takes input with weights and postal codes and print all the results in 1 min interval.

If any error occurs then during parsing of input the input line is skipped.
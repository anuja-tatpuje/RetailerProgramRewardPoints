# RetailerProgramRewardPoints
Retailer Program Assignment

This Code calculates reward points for user. How reward points are calculated --> if user spends amount over $100 then all amount over 100$ will be calculated 
with rewardPoints * 2 and for spends over 50$ will be divided into 50$ and added into reward points.


To run spring boot application independently. Please run below command where jar file is placed. According to current configurations, jar file is placed in target folder.
Command - 
java -jar <JAR_FILE_NAME>

API call 
http://localhost:8080/rewards/calculate-rewards/{customerId}/{amount}
  

  

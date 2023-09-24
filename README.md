Login url:
http://localhost:7573/login

GET OAuth2 Token:
need open login page, login will return token

GET LDAP Token：
curl 'http://localhost:7573/login' --data-raw 'username=+ldap_user_1&password=+ldap_user_1'

GET MYSQL Token ：
curl 'http://localhost:7573/login' --data-raw 'username=user_1&password=user_1'

Select product:
curl --location --request GET 'http://localhost:7573/product/all' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ7XCJjaGFubmVsXCI6XCJkYlwiLFwicm9sZXNcIjpbXCJOb3JtYWxcIl0sXCJ1c2VybmFtZVwiOlwidXNlcl8xXCJ9IiwiZXhwIjoxNjk4MTUyMDQxfQ.g112hpXP-Y2Cdu1YaysmCN2igaXgEK2_N2pITNLzqec' \

Add product:
curl --location --request POST 'http://localhost:7573/product' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ7XCJjaGFubmVsXCI6XCJkYlwiLFwicm9sZXNcIjpbXCJOb3JtYWxcIl0sXCJ1c2VybmFtZVwiOlwidXNlcl8xXCJ9IiwiZXhwIjoxNjk4MTUyMDQxfQ.g112hpXP-Y2Cdu1YaysmCN2igaXgEK2_N2pITNLzqec' \
--header 'Content-Type: application/json' \
--data-raw '{
"productName" : "NIKE"
}'

Update product:
curl --location --request PUT 'http://localhost:7573/product' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ7XCJjaGFubmVsXCI6XCJnaXRodWJcIixcInJvbGVzXCI6W1wiRURJVE9SXCJdLFwidXNlcm5hbWVcIjpcIkxvSGlNZ1wifSIsImV4cCI6MTY5ODE1ODYwOH0.A73ncmndPG63a9bODcHEGOhZ8bXGuXu49vuekOeNBXg' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": 1,
"productName": "Iphone 99"
}'

Delete product
curl --location --request DELETE 'http://localhost:7573/product/2' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ7XCJjaGFubmVsXCI6XCJnaXRodWJcIixcInJvbGVzXCI6W1wiRURJVE9SXCJdLFwidXNlcm5hbWVcIjpcIkxvSGlNZ1wifSIsImV4cCI6MTY5ODE1ODYwOH0.A73ncmndPG63a9bODcHEGOhZ8bXGuXu49vuekOeNBXg' \





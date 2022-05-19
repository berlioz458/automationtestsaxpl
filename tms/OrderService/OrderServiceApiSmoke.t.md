# Smoke API checklist for Order Service
* C1 Get Version
  * call get-method
  * check status code 200


* C2 Get "Counteragent" Entity
  * call get-method(without params)
  * check status code 200
  * check json-schema answer


* C3 Create "Counteragent" ph
  * call post-method with test user
  * check status code
  * check user data
  * check json-schema answer


* C4 Create "Counteragent" lg
  * call post-method with test user
  * check status code
  * check user data
  * check json-schema answer


* C5 Get "Contract" Entity
  * call get-method(without params)
  * check status code 200
  * check json-schema answer


* C6 Get "Order" Entity
  * call get-method(without params)
  * check status code 200
  * check json-schema answer


* C7 Create Document
  * call post-method with test data
  * check status code
  * check responce data
  * check json-schema answer


* C8 Create Billing Account
  * call post-method with test data
  * check status code
  * check responce data
  * check json-schema answer


* C9 Create Contract Template
  * call post-method with test data
  * check status code
  * check responce data
  * check json-schema answer


* C10 Create Loyal Policy
  * call post-method with test data
  * check status code
  * check responce data
  * check json-schema answer


* C11 Create Marketing Action
  * call post-method with test user
  * check status code
  * check responce data
  * check json-schema answer


* C12 Change Document
  * call put-method with test data
  * check status code
  * check responce data
  * check json-schema answer


* C13 Change Billing Account
  * call put-method with test data
  * check status code
  * check responce data
  * check json-schema answer


* C14 Change Contract Template
  * call put-method with test data
  * check status code
  * check responce data
  * check json-schema answer


* C15 Change Loyal Policy
  * call put-method with test data
  * check status code
  * check responce data
  * check json-schema answer


* C16 Change Marketing Action
  * call put-method with test data
  * check status code
  * check responce data
  * check json-schema answer


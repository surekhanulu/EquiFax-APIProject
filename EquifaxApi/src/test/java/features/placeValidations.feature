Feature: Validating Employee API's 
@GetEmployee 
Scenario Outline: Verify employee record get using GET API 
	Given GetEmployee API "<id>"
	When user calls "getEmployeeApi" with "Get" http request 
	Then validate response code 200 "GET"
	And validate the response data for get API "<employee_name>" <employee_salary> <age>
Examples:
	|id| employee_name   | employee_salary | age |    
    |1 | Tiger Nixon     | 320800          | 61  |
    |2 | Garrett Winters | 170750          | 63  |
  
  
	
@DeleteEmployee 
Scenario Outline: Verify employee record delete using DELETE API 
	Given DeleteEmployee API "<id>"
	When user calls "deleteEmployeeApi" with "Delete" http request 
	Then validate response code 200 "Delete"
	And validate the reponse data for delete API "<status>" "<message>"
Examples:
    |id| status     | message                               |
    |2 | success    | Successfully! Record has been deleted |
Feature: Validating Employee API's 
@GetEmployee 
Scenario Outline: Vefify if the details of employee is retrieved successfully using GetEmployeeAPI 
	Given GetEmployee Api "<id>"
	When user calls "getEmployeeApi" with "Get" http request 
	Then the API call is success with status code 200 "GET"
	Examples:
    |id|
    |1 | 
    |2 |
  
  
	
@DeleteEmployee 
Scenario Outline: Verify if delete employee functionality is working 
	Given DeleteEmployee Api "<id>"
	When user calls "deleteEmployeeApi" with "Delete" http request 
	Then the API call is success with status code 200 "Delete"
	
Examples:
    |id|
    |2 | 
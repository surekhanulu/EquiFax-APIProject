package resources;

public enum ApiResources {
	getEmployeeApi("api/v1/employee/{id}"), deleteEmployeeApi("api/v1/delete/{id}");

	private String resource;

	ApiResources(String resource) {

		this.resource = resource;
	}

	public String getResource() {
		return resource;

	}

}
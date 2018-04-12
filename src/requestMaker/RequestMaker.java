package requestMaker;

public class RequestMaker {
	
	private Boolean request = false;
	private Integer id;
	
	public RequestMaker(Boolean _request, Integer _id) {
		request = _request;
		id = _id;
	}
	
	public Boolean GetRequest() {
		return request;
	}
	
	public Integer GetId() {
		return id;
	}
	
	
}

package fi.pickup.domain;

public class Result {
	private String id;
	private int median;
	
	public Result(String id, int median) {
		this.id = id;
		this.median = median;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMedian() {
		return median;
	}
	public void setMedian(int median) {
		this.median = median;
	}
	
}

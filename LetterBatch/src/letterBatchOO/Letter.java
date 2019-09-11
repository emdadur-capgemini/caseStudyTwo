package letterBatchOO;

public abstract class Letter {
	
	final private String type;
	private String companyName;
	
	public Letter( String type, String companyName){
		
		this.type = type;
		this.companyName = companyName;
		
	}

	public String getType() {
		return type;
	}

	public String getCompanyName() {
		return companyName;
	}
	
	
	
	
	
}

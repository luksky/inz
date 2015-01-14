package AllegroWebApi.H2Database;

public class DataModelItemAttribs {

	long itId;
	String attribName;
	String attribValuesStart;
	String attribValuesEnd;

	public DataModelItemAttribs(long itId, String attribName,
			String attribValuesStart, String attribValuesEnd) {
		this.itId=itId;
		this.attribName=attribName;
		this.attribValuesStart=attribValuesStart;
		this.attribValuesEnd=attribValuesEnd;
	}
	public long getItId() {
		return itId;
	}

	public void setItId(long itId) {
		this.itId = itId;
	}

	public String getAttribName() {
		return attribName;
	}

	public void setAttribName(String attribName) {
		this.attribName = attribName;
	}

	public String getAttribValuesStart() {
		return attribValuesStart;
	}

	public void setAttribValuesStart(String attribValuesStart) {
		this.attribValuesStart = attribValuesStart;
	}

	public String getAttribValuesEnd() {
		return attribValuesEnd;
	}

	public void setAttribValuesEnd(String attribValuesEnd) {
		this.attribValuesEnd = attribValuesEnd;
	}


}

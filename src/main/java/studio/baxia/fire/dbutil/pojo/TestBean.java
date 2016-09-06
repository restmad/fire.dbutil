package studio.baxia.fire.dbutil.pojo;

import java.util.Date;

public class TestBean {
	private String name;
	private Integer id;
	private Date saveDate;
	
	@Override
	public String toString() {
		return "TestBean [name=" + name + ", id=" + id + ", saveDate="
				+ saveDate + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getSaveDate() {
		return saveDate;
	}
	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}
	
}

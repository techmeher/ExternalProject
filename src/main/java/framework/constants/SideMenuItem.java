package framework.constants;

public enum SideMenuItem {

	CASES("CASES"), BLOG("BLOG"), EVENTS("EVENTS"), SERVICES("SERVICES"), JOBS(
			"JOBS");
	private String item;

	private SideMenuItem(String item) {
		this.item = item;
	}

	public String getItem() {
		return item;
	}

}

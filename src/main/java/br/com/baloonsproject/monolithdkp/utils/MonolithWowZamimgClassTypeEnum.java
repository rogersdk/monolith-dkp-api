package br.com.baloonsproject.monolithdkp.utils;

public enum MonolithWowZamimgClassTypeEnum {

	WARRIOR("https://wow.zamimg.com/images/wow/icons/large/classicon_warrior.jpg", "warrior"),
	WARLOCK("https://wow.zamimg.com/images/wow/icons/large/classicon_warlock.jpg", "warlock"),
	PRIEST("https://wow.zamimg.com/images/wow/icons/large/classicon_priest.jpg", "priest"),
	HUNTER("https://wow.zamimg.com/images/wow/icons/large/classicon_hunter.jpg", "hunter"),
	MAGE("https://wow.zamimg.com/images/wow/icons/large/classicon_mage.jpg", "mage"),
	DRUID("https://wow.zamimg.com/images/wow/icons/large/classicon_druid.jpg", "druid"),
	ROGUE("https://wow.zamimg.com/images/wow/icons/large/classicon_rogue.jpg", "rogue"),
	SHAMAN("https://wow.zamimg.com/images/wow/icons/large/classicon_shaman.jpg", "shaman"),
	NONE("https://wow.zamimg.com/images/wow/icons/large/classicon_none.jpg", "none");

	String icon;
	String classType;

	MonolithWowZamimgClassTypeEnum(String icon, String classType) {
		this.icon = icon;
		this.classType = classType;
	}

	public String getIcon() {
		return this.icon;
	}

	public String getClassType() {
		return this.classType;
	}

	public static MonolithWowZamimgClassTypeEnum getByIcon(String iconStr) {
		for (MonolithWowZamimgClassTypeEnum classTypeEnum : MonolithWowZamimgClassTypeEnum.values()) {
			if (classTypeEnum.getIcon().equals(iconStr)) {
				return classTypeEnum;
			}
		}
		return null;
	}
}

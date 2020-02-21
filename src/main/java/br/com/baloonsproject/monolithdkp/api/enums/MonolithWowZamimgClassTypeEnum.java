package br.com.baloonsproject.monolithdkp.api.enums;

public enum MonolithWowZamimgClassTypeEnum {
	NONE("https://wow.zamimg.com/images/wow/icons/large/classicon_none.jpg", "none"),
	DRUID("https://wow.zamimg.com/images/wow/icons/large/classicon_druid.jpg", "druid"),
	HUNTER("https://wow.zamimg.com/images/wow/icons/large/classicon_hunter.jpg", "hunter"),
	MAGE("https://wow.zamimg.com/images/wow/icons/large/classicon_mage.jpg", "mage"),
	PALADIN("https://wow.zamimg.com/images/wow/icons/large/classicon_paladin.jpg", "paladin"),
	PRIEST("https://wow.zamimg.com/images/wow/icons/large/classicon_priest.jpg", "priest"),
	ROGUE("https://wow.zamimg.com/images/wow/icons/large/classicon_rogue.jpg", "rogue"),
	SHAMAN("https://wow.zamimg.com/images/wow/icons/large/classicon_shaman.jpg", "shaman"),
	WARRIOR("https://wow.zamimg.com/images/wow/icons/large/classicon_warrior.jpg", "warrior"),
	WARLOCK("https://wow.zamimg.com/images/wow/icons/large/classicon_warlock.jpg", "warlock");

	private String iconStr;
	private String classType;

	private MonolithWowZamimgClassTypeEnum(String iconStr, String classType) {
		this.iconStr = iconStr;
		this.classType = classType;
	}

	public static MonolithWowZamimgClassTypeEnum getByIcon(String iconStr) {
		for (MonolithWowZamimgClassTypeEnum classTypeEnum : MonolithWowZamimgClassTypeEnum.values()) {
			if (classTypeEnum.getIconStr().equals(iconStr)) {
				return classTypeEnum;
			}
		}
		return NONE;
	}

	public String getIconStr() {
		return iconStr;
	}

	public String getClassType() {
		return classType;
	}

}

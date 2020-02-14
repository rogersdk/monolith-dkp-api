package br.com.baloonsproject.monolithdkp.api.enums;

public enum ClassTypeEnum {
	NONE, DRUID, HUNTER, MAGE, PALADIN, PRIEST, ROGUE, SHAMAN, WARRIOR, WARLOCK;

	public static ClassTypeEnum getByString(String classTypeStr) {

		switch (classTypeStr) {
		case "druid":
			return DRUID;
		case "hunter":
			return HUNTER;
		case "mage":
			return MAGE;
		case "paladin":
			return PALADIN;
		case "priest":
			return PRIEST;
		case "rogue":
			return ROGUE;
		case "shaman":
			return SHAMAN;
		case "warrior":
			return WARRIOR;
		case "warlock":
			return WARLOCK;
		default:
			break;
		}

		return NONE;
	}
}

package net.exodiac.skills.SkillManagement;

public class SkillUtilities {

    public static final int POWER_LEVEL = 1;
    public static final int SKILL_NAME = 1;

    public static String getEssenceDetailsFromString(String essenseString, int part) {
        // Lore string should be "+ Essence of essence_name [power_level]"
        // parts:
        // [0] = "+"
        // [1] = "Essence"
        // [2] = "of"
        // [3] = "essence_name" <------- Essence Name at 3
        // [4] = power_level

        String[] parts = essenseString.split(" ");

        String skillName = parts[3];

        String powerLevel = parts[4].replace("[", "").replace("]", "");

        if (part == POWER_LEVEL) {
            return powerLevel;
        } else if (part == SKILL_NAME) {
            return skillName;
        } else {
            return "";
        }
    }
}

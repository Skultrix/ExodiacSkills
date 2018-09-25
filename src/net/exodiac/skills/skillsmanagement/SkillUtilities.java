package net.exodiac.skills.skillsmanagement;

import org.bukkit.ChatColor;

public class SkillUtilities {

    public static final int POWER_LEVEL = 1;
    public static final int SKILL_NAME = 2;

    public static String getEssenceDetailsFromString(String essenseString, int part) {
        // Lore string should be "+ Essence of essence_name [power_level]"
        // parts:
        // [0] = "+"
        // [1] = "Essence"
        // [2] = "of"
        // [3] = "essence_name" <------- Essence Name at 3
        // [4] = power_level

        String[] parts = ChatColor.stripColor(essenseString).split(" ");

        String skillName = parts[3];

        String powerLevel = parts[4].replace("[", "").replace("]", "");

        if (part == POWER_LEVEL) {
            return powerLevel;
        } else if (part == SKILL_NAME) {
            return skillName;
        }
        return null;
    }
    public static String getSkillNameFromPearl(String pearlDisplayName) {
        // Display string should be "Essence of x [Pearl]"
        // parts:
        // [0] = "Essence"
        // [1] = "of"
        // [2] = "x" <------- Essence Name at 2
        // [3] = "[Pearl]"
        String[] parts = ChatColor.stripColor(pearlDisplayName).split(" ");
        return parts[2];
    }
}

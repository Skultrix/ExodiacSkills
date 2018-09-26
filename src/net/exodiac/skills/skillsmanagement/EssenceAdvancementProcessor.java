package net.exodiac.skills.skillsmanagement;

import net.exodiac.core.EssentialMethods;
import net.exodiac.skills.SkillsPlugin;
import net.exodiac.skills.skillsmanagement.Skill;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public class EssenceAdvancementProcessor {

    private Skill skill;

    private int exp;
    private int power;
    private int level;

    public EssenceAdvancementProcessor(String informationalLoreLine, Skill skill) {
        //Informational Lore Line: "Level x [x/x]"
        this.skill = skill;

        String[] parts = ChatColor.stripColor(informationalLoreLine).split(" ");
        String[] expParts = parts[2].split("/");

        level = Integer.parseInt(parts[1]);
        exp = Integer.parseInt(expParts[0].replace("[", ""));

        updateStatistics();

        System.out.println("Level = " + level + "Exp/power" + exp + power);
    }

    public void addExp(int amount) {
        System.out.println(exp);
        exp += amount;
        System.out.println(exp);
        checkExpLevels();
    }

    public void checkExpLevels() {
        if (level != 10) {
            int nextLvlExp = level * 50;
            if (nextLvlExp <= exp) {
                level += 1;
                updateStatistics();
            }
        }
    }

    private void updateStatistics() {
        switch (level) {
            case 10:
                power = 5;
                break;
            case 9:
            case 8:
                power = 4;
                break;
            case 7:
            case 6:
                break;
            case 5:
                power = 3;
                break;
            case 4:
                break;
            case 3:
                power = 2;
                break;
            case 2:
            case 1:
                power = 1;
                break;
        }
    }

    public List<String> getNewLoreLines() {

        String levelStr = String.valueOf(level);
        String expForLevel = String.valueOf(exp);
        String expNeeded = String.valueOf(50);

        List<String> lore = Arrays.asList(
                SkillsPlugin.getSkillHandler().getSkillFromName(skill.getName()).getEssenceLoreDesc(power),
                //I'll do this with String.format()
                EssentialMethods.color("&7Level &6&l" + levelStr + " [" + expForLevel + "/" + expNeeded + "]")
        );

        return lore;
    }
}

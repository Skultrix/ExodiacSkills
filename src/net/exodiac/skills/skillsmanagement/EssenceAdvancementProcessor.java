package net.exodiac.skills.skillsmanagement;

import net.exodiac.core.EssentialMethods;
import net.exodiac.skills.SkillsPlugin;
import net.exodiac.skills.skillsmanagement.Skill;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public class EssenceAdvancementProcessor {

    private int expNeeded;

    private Skill skill;

    private int exp;
    private int power;
    private int level;

    public EssenceAdvancementProcessor(String informationalLoreLine, Skill skill) {
        //Informational Lore Line: "Level x [x/x]"
        this.skill = skill;

        String[] parts = ChatColor.stripColor(informationalLoreLine).split(" ");

        level = Integer.parseInt(parts[1]);
        expNeeded = (int) (Math.pow(1.5, level)) * 50;

        //experimental?
        if (level == 10) {
            return;
        }

        String[] expParts = parts[2].split("/");
        exp = Integer.parseInt(expParts[0].replace("[", ""));

        updateStatistics();

    }

    public EssenceAdvancementProcessor addExp(int amount) {
        if (level == 10) return this;
        exp += amount;
        checkExpLevels();
        return this;
    }

    private void checkExpLevels() {
        if (level != 10) {
            if (this.expNeeded <= exp) {
                level += 1;
                exp = exp - (int) (Math.pow(1.5, level - 1)) * 50;
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
            case 5:
                power = 3;
                break;
            case 4:
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
        String expNeeded = String.valueOf(this.expNeeded);

        List<String> lore = Arrays.asList(
                SkillsPlugin.getSkillHandler().getSkillFromName(skill.getName()).getEssenceLoreDesc(power),
                //I'll do this with String.format()
                level == 10 ?
                        EssentialMethods.color("&7Level &c&l10 &8[&6&lMAX&8]"):
                        EssentialMethods.color("&7Level &6&l" + levelStr + " &8[" + expForLevel + "/" + expNeeded + "]")
        );

        return lore;
    }
}

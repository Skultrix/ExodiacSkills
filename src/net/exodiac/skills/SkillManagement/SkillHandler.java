package net.exodiac.skills.SkillManagement;

import net.exodiac.core.EssentialMethods;
import net.exodiac.skills.SkillManagement.Skills.Soul;
import net.exodiac.skills.SkillsPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SkillHandler implements Listener {

    private Map<String, Skill> skills = new HashMap<>();

    public SkillHandler(SkillsPlugin plugin) {
        skills.put("Soul", new Soul());
    }

    @EventHandler
    public void onExecute(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack executable = event.getItem();

        boolean shouldExecute = false;
        int essenceNamePos = -1;

        if (!event.getAction().name().contains("RIGHT")) return;

        if (!executable.hasItemMeta() || !executable.getItemMeta().hasLore()) return;

        for (String entry : executable.getItemMeta().getLore()) {
            if (entry.contains("Essence of")) {
                shouldExecute = true;
                essenceNamePos = executable.getItemMeta().getLore().indexOf(entry);
                break;
            }
        }

        if (shouldExecute) {
            String essenceName = executable.getItemMeta().getLore().get(essenceNamePos);
            int powerLevel = Integer.parseInt(SkillUtilities.getEssenceDetailsFromString(essenceName, SkillUtilities.POWER_LEVEL));
            String skillName = SkillUtilities.getEssenceDetailsFromString(essenceName, SkillUtilities.SKILL_NAME);

            getSkillFromName(skillName).execute(event, powerLevel);
        }
    }

    public Skill getSkillFromName(String name) {
        return skills.get(name);
    }

    public Map<String, Skill> getSkills() {
        return skills;
    }

}

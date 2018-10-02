package net.exodiac.skills.skillsmanagement;

import net.exodiac.skills.skillsmanagement.skills.Soul;
import net.exodiac.skills.SkillsPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
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

        //START: Checking whether item has essence.

        if (!event.getAction().name().contains("RIGHT")) return;

        if (!executable.hasItemMeta() || !executable.getItemMeta().hasLore()) return;

        for (String entry : executable.getItemMeta().getLore()) {
            if (entry.contains("Essence of")) {
                shouldExecute = true;

                //Position of the lore line where the name is.
                essenceNamePos = executable.getItemMeta().getLore().indexOf(entry);
                break;
            }
        }

        if (!shouldExecute) return;

        //END: Checking whether item has essence.

        String essenceName = executable.getItemMeta().getLore().get(essenceNamePos);

        //Safety parsing to integer.
        int powerLevel = Integer.parseInt(
                SkillUtilities.getEssenceDetailsFromString(essenceName, SkillUtilities.POWER_LEVEL)
        );
        String skillName = SkillUtilities.getEssenceDetailsFromString(essenceName, SkillUtilities.SKILL_NAME);

        Skill skill = getSkillFromName(skillName);

        //We know what skill we're dealing with, now we are adding exp to the essence of the item.

        List<String> advancement = new EssenceAdvancementProcessor(
                executable.getItemMeta().getLore().get(essenceNamePos + 1), skill
        ).addExp(10).getNewLoreLines();

        //if (skill.getCooldown().containsKey(player))

        skill.execute(event, powerLevel);

        //Changing lore of the executable.
        List<String> lore = executable.getItemMeta().getLore();

        //Essence should always be at the bottom of the item's lore.
        //Safely assuming that there is a line after the essence's name.
        lore.remove(essenceNamePos);
        //Second line will be in the same position as the first one (cus it got removed)
        lore.remove(essenceNamePos);

        //Adding updated lore lines: contains the new exp, level, and power level.
        lore.addAll(advancement);

        //"Reloading" item's lore.
        ItemMeta meta = executable.getItemMeta();
        meta.setLore(lore);
        executable.setItemMeta(meta);

        //Done. We executed the skill's ability, then added 10 exp, updated the lore lines to show
        //new exp, increase level and power if necessary.

    }

    public Skill getSkillFromName(String name) {
        return skills.get(name);
    }

    public Map<String, Skill> getSkills() {
        return skills;
    }

    public boolean skillExists(String name) {
        return skills.containsKey(name);
    }

}

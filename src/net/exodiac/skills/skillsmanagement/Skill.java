package net.exodiac.skills.skillsmanagement;

import net.exodiac.core.EssentialMethods;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class Skill {

    private String name;
    private int cooldownTime;

    private Map<ItemStack, Integer> cooldown = new HashMap<>();

    public Skill(String name, int cooldownTime) {
        this.name = name;
        this.cooldownTime = cooldownTime;
    }

    public abstract void execute(PlayerInteractEvent event, int power);
    public abstract ItemStack getEssencePearl();

    public String getSkillSuccessfulExecuteMessage() {
        if (name != null) {
            return EssentialMethods.color(
                    "&6&l" + getName() + "&7&l essence activated!"
            );
        }
        return null;
    }

    public String getEssenceLoreDesc(int powerLevel) {
        return EssentialMethods.color("&a&l+ &7&lEssence of " + getName() + " &8[" + String.valueOf(powerLevel) + "]");
    }

    public String getName() {
        return name;
    }

    public int getCooldownTime() {
        return cooldownTime;
    }

    public Map<ItemStack, Integer> getCooldown() {
        return cooldown;
    }
}

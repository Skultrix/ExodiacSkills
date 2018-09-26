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

    private Map<UUID, Integer> cooldown = new HashMap<>();

    public Skill(String name) {
        this.name = name;
    }

    public abstract void execute(PlayerInteractEvent event, int power);
    public abstract ItemStack getEssencePearl();

    public String getSkillSuccessfulExecuteMessage() {
        if (name != null) {
            return EssentialMethods.color(
                    "&7&lYou have successfully used your &6&l" + getName() + "&7&l essence!"
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

    public void setName(String name) {
        this.name = name;
    }

    public int getCooldownTime() {
        return cooldownTime;
    }

    public void setCooldown(int cooldown) {
        this.cooldownTime = cooldown;
    }

    public Map<UUID, Integer> getCooldown() {
        return cooldown;
    }
}

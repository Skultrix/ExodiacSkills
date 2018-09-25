package net.exodiac.skills.SkillManagement;

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

    public abstract void execute(PlayerInteractEvent event, int power);
    public abstract ItemStack getEssence();

    public String getSkillSuccessfulExecuteMessage() {
        if (name != null) {
            return EssentialMethods.color(
                    "&7&lYou have successfully used your &6&l" + getName() + "&7&l essence!"
            );
        }
        return null;
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

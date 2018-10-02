package net.exodiac.skills.skillsmanagement.skills;

import net.exodiac.skills.skillsmanagement.Skill;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Darkness extends Skill {
    public Darkness() {
        super("Darkness");
    }

    @Override
    public void execute(PlayerInteractEvent event, int power) {

    }

    @Override
    public ItemStack getEssencePearl() {
        return null;
    }
}

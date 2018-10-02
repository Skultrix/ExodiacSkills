package net.exodiac.skills.skillsmanagement.skills;

import net.exodiac.core.Essential.ItemBuilder;
import net.exodiac.core.EssentialMethods;
import net.exodiac.skills.skillsmanagement.Skill;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Soul extends Skill {

    public Soul() {
        super("Soul", 10);
    }

    @Override
    public void execute(PlayerInteractEvent event, int power) {

        Player player = event.getPlayer();

        //2 Hearts for every power level.
        double healthAddition = player.getHealth() + 4 * power;

        player.setHealth(healthAddition >= 20 ? 20.0 : healthAddition);
        player.sendMessage(super.getSkillSuccessfulExecuteMessage());

    }

    @Override
    public ItemStack getEssencePearl() {
        ItemStack essenceItem = new ItemBuilder(Material.FIRE_CHARGE)
                .setDisplayName("&c&lEssence of Soul &8[Pearl]")
                .addLoreLine("&7Contained within are magical")
                .addLoreLine("&7healing abilities.")
                .addLoreLine("&8Drag n' Drop onto weapon.")
                .build();
        return essenceItem;
    }

}

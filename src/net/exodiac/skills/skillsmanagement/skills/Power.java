package net.exodiac.skills.skillsmanagement.skills;

import net.exodiac.core.Essential.ItemBuilder;
import net.exodiac.skills.skillsmanagement.Skill;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Power extends Skill {

    public Power() {
        super("Power", 10);
    }

    @Override
    public void execute(PlayerInteractEvent event, int power) {
        final int time = 30 * power * 20;
        int str = -1;

        switch (power) {
            case 5:
                str = 2;
                break;
            case 4:
            case 3:
                str = 1;
                break;
            case 2:
            case 1:
                str = 0;
                break;
        }

        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HARM, time, str));
        event.getPlayer().playEffect(event.getPlayer().getLocation(), Effect.SMOKE, 10);
    }

    @Override
    public ItemStack getEssencePearl() {
        ItemStack essenceItem = new ItemBuilder(Material.FIRE_CHARGE)
                .setDisplayName("&c&lEssence of Power &8[Pearl]")
                .addLoreLine("&7Contained within are magical")
                .addLoreLine("&7strength abilities.")
                .addLoreLine("&8Drag n' Drop onto weapon.")
                .build();
        return essenceItem;
    }
}

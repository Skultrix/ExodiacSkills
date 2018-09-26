package net.exodiac.skills;

import net.exodiac.core.Essential.ItemBuilder;
import net.exodiac.skills.skillsmanagement.SkillHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class SkillsPlugin extends JavaPlugin implements Listener {
    private static SkillHandler handler;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(handler = new SkillHandler(this), this);
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new PearlPlacementListener(), this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        ItemStack item = new ItemBuilder(Material.IRON_SWORD)
                .setDisplayName("&cTest Sword")
                .addLoreLine("tesssts")
                .addLoreLine("blahblahblah")
                .addLoreLine("")
                .addLoreLine("&a&l+ &6&lEssence of Soul [5]")
                .build();
        player.getInventory().addItem(item);
        player.getInventory().addItem(getSkillHandler().getSkillFromName("Soul").getEssencePearl());
    }

    public static SkillHandler getSkillHandler() {
        return handler;
    }
}

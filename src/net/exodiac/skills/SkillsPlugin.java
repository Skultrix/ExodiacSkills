package net.exodiac.skills;

import net.exodiac.core.Essential.ItemBuilder;
import net.exodiac.skills.SkillManagement.SkillHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class SkillsPlugin extends JavaPlugin implements Listener {
    public void onEnable() {
        SkillHandler handler = new SkillHandler(this);
        getServer().getPluginManager().registerEvents(handler, this);
        getServer().getPluginManager().registerEvents(this, this);
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
    }
}

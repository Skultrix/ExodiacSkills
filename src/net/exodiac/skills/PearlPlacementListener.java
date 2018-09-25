package net.exodiac.skills;

import net.exodiac.skills.skillsmanagement.SkillUtilities;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PearlPlacementListener implements Listener {

    SkillsPlugin plugin;

    PearlPlacementListener(SkillsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPearlPlacement(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        Inventory inventory = e.getInventory();

        ItemStack pearl = e.getCursor();
        ItemStack gear = e.getCurrentItem();

        if (inventory == null) return;
        if (pearl == null || gear == null) return;

        if (!pearl.hasItemMeta()) return;
        if (!pearl.getItemMeta().hasDisplayName()) return;

        if (!pearl.getItemMeta().getDisplayName().contains("Pearl")) return;

        if (!getValidItems().contains(gear.getType())) return;

        //Now we are sure that the player has a pearl on the cursor
        //and the item that's being modified is a valid piece of gear.

        //We are finding the name of the skill.

        String skillName = SkillUtilities.getSkillNameFromPearl(pearl.getItemMeta().getDisplayName());


    }

    private List<Material> getValidItems() {
        List<Material> gear = new ArrayList<>();

        gear.add(Material.DIAMOND_SWORD);
        gear.add(Material.GOLDEN_SWORD);
        gear.add(Material.IRON_SWORD);
        gear.add(Material.WOODEN_SWORD);
        gear.add(Material.STONE_SWORD);

        gear.add(Material.DIAMOND_AXE);
        gear.add(Material.GOLDEN_AXE);
        gear.add(Material.IRON_AXE);
        gear.add(Material.WOODEN_AXE);
        gear.add(Material.STONE_AXE);

        return gear;
    }
}

package net.exodiac.skills;

import net.exodiac.core.EssentialMethods;
import net.exodiac.skills.skillsmanagement.SkillHandler;
import net.exodiac.skills.skillsmanagement.SkillUtilities;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PearlPlacementListener implements Listener {

    private SkillHandler handler;

    PearlPlacementListener() {
        handler = SkillsPlugin.getSkillHandler();
    }

    @EventHandler
    public void onPearlPlacement(InventoryClickEvent e) {
        Inventory inventory = e.getInventory();

        ItemStack pearl = e.getCursor();
        ItemStack gear = e.getCurrentItem();

        if (inventory == null) return;
        if (pearl == null || gear == null) return;

        if (!pearl.hasItemMeta()) return;
        if (!pearl.getItemMeta().hasDisplayName()) return;

        if (!pearl.getItemMeta().getDisplayName().contains("Pearl")) return;

        if (!this.getValidItems().contains(gear.getType())) return;

        for (String line : gear.getItemMeta().getLore())
            if (ChatColor.stripColor(line).equals("+ Essence of")) return;


        //Now we are sure that the player has a pearl on the cursor
        //and the item that's being modified is a valid piece of gear.

        //We are finding the name of the skill.

        e.setCancelled(true);

        String skillName = SkillUtilities.getSkillNameFromPearl(pearl.getItemMeta().getDisplayName());
        String loreLine = handler.getSkillFromName(skillName).getEssenceLoreDesc(1);

        List<String> gearLore = gear.getItemMeta().hasLore() ? gear.getItemMeta().getLore() : new ArrayList<>();

        gearLore.add("");
        gearLore.add(loreLine);
        gearLore.add(EssentialMethods.color("&7Level &61 [0/50]"));

        //"Reloaded" the item meta.
        ItemMeta meta = gear.getItemMeta();
        meta.setLore(gearLore);
        gear.setItemMeta(meta);

        //Removing the pearl used.
        pearl.setAmount(pearl.getAmount() - 1);

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

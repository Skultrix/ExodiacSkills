package net.exodiac.skills.skillsmanagement;

import net.exodiac.core.EssentialMethods;
import net.exodiac.skills.SkillsPlugin;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class EssenceGiveCommand implements CommandExecutor {

    private static final String USAGE_MESSAGE = EssentialMethods.color("&cUsage: /essence give <essence> <amount> [player]");
    private static final String PLAYER_ONLY_MESSAGE = EssentialMethods.color("&cOnly players can use this command like this.");
    private static final String NO_SKILL_MESSAGE = EssentialMethods.color("&cThe skill name entered does not exist. (case-sensitive)");
    private static final String NOT_A_NUMBER = EssentialMethods.color("&cThe item amount should be a number. Defaulted to 1.");
    private static final String INVALID_PLAYER = EssentialMethods.color("&cInvalid player name! Player might be offline.");

    private static final List<String> HELP_MESSAGES = Arrays.asList(
            "&6&lEssence Commands Guide:",
            "&7/essence give <essence> <amount> [player]",
            "&7/essence list"
    );

    /*
     * Commands:
     * - /essence give <pearl_name> <amount> <player_name>
     * - /essence give <pearl_name> <amount>
     * - /essence give <pearl_name>
     *
     * args[0] = give
     * args[1] = pearl_name
     * args[2] = amount
     * args[3] = player_name
     */

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (!cmd.getName().equalsIgnoreCase("essence")) return false;

        if (args.length == 0) {
            //Scenario: /essence
            //Send message
        }

        if (args[0].equalsIgnoreCase("give")) {
            if (args.length == 1) {
                //"/essence give"
                sender.sendMessage(USAGE_MESSAGE);
            } else if (args.length == 2) {
                //"/essence give <pearl_name>"
                givePlayerPearl(sender, args[1], null, true, null);

            } else if (args.length == 3) {
                //"/essence give <pearl_name> [amount]"
                givePlayerPearl(sender, args[1], args[2], true, null);

            } else if (args.length == 4) {
                //"/essence give <pearl_name> <amount> [player]"
                givePlayerPearl(sender, args[1], args[2], false, args[3]);

            } else {
                sender.sendMessage(USAGE_MESSAGE);
            }
        } else {
            EssentialMethods.sendColoredMessageList(sender, HELP_MESSAGES);
        }

        return false;
    }

    private void givePlayerPearl(CommandSender sender, String pearlName, String amount, boolean noPlayerCheck, String playerName) {
        int number;

        if (amount == null) {
            number = 1;
        } else {
            if (isInteger(amount)) {
                number = Integer.parseInt(amount);
            } else {
                sender.sendMessage(NOT_A_NUMBER);
                number = 1;
            }
        }

        if (!SkillsPlugin.getSkillHandler().skillExists(pearlName)) {
            sender.sendMessage(NO_SKILL_MESSAGE);
            return;
        }

        Player target;

        if (!noPlayerCheck) {
            if (Bukkit.getPlayer(playerName) == null) {
                sender.sendMessage(INVALID_PLAYER);
                return;
            }
            target = Bukkit.getPlayer(playerName);
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage(PLAYER_ONLY_MESSAGE);
                return;
            }
            target = (Player) sender;
        }

        ItemStack pearl = SkillsPlugin.getSkillHandler().getSkillFromName(pearlName).getEssencePearl();

        for (int i = 0; i <= number; i++) {
            if (target.getInventory().firstEmpty() != -1) {
                target.getInventory().addItem(pearl);
            } else {
                target.getWorld().dropItem(target.getLocation(), pearl);
            }
        }

        target.sendMessage(EssentialMethods.color("&7You have received " + number + " &6" + pearlName + " &7pearls."));
    }

    private boolean isInteger(String num) {
        return StringUtils.isNumeric(num);
    }
}

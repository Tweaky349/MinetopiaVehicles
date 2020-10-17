package nl.mtvehicles.core.Commands.VehiclesSubs;

import nl.mtvehicles.core.Infrastructure.Models.MTVehicleSubCommand;
import nl.mtvehicles.core.Infrastructure.Models.Vehicle;
import nl.mtvehicles.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VehicleGiveCar extends MTVehicleSubCommand {
    @Override
    public boolean execute(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (!checkPermission("mtvehicles.givecar")) return true;

        if (args.length != 3) {
            player.sendMessage(Main.messagesConfig.getMessage("useGiveCar"));
            return true;
        }

        try {
            Integer.parseInt(args[1]);
        } catch (Throwable e) {
            player.sendMessage(Main.messagesConfig.getMessage("useGiveCar"));
            return false;
        }

        Player of = Bukkit.getPlayer(args[2]);

        if (of == null || !of.hasPlayedBefore()) {
            player.sendMessage(Main.messagesConfig.getMessage("playerNotFound"));
            return true;
        }

        Vehicle.getByDamage(Integer.parseInt(args[1]), of);

        return true;
    }


}

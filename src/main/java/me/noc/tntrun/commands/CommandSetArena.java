package me.noc.tntrun.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import me.noc.tntrun.TnTRun;

public class CommandSetArena extends Command {

    private TnTRun plugin;

    public CommandSetArena(String name, String description, String usageMessage, TnTRun plugin) {
        super(name, description, usageMessage);
        this.plugin = plugin;
        this.setPermission("tntrun.commands.setarena");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (!player.hasPermission("tntrun.commands.setarena")) { return false; }

            if (args.length != 1) { player.sendMessage(usageMessage); }

            String name = args[0];
            this.plugin.getLocationSystem().addLocation(name.toLowerCase(), player.getLocation());

            player.sendMessage(this.plugin.getConfigSystem().getPrefix() + this.plugin.getConfigSystem().getLocationSet());

        }
        return false;
    }
}

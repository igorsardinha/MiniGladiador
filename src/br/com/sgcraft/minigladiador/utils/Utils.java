package br.com.sgcraft.minigladiador.utils;

import org.bukkit.command.*;
import org.bukkit.entity.*;

import br.com.sgcraft.minigladiador.*;

import org.bukkit.*;

public class Utils implements CommandExecutor
{
    private Main plugin;
    static int delay;
	private Location castelo;
    
    public Utils(final Main main) {
        this.plugin = main;
    }
    
    public static void addClan(final String tag) {
        Main.pl.getConfig().set("ClanVenceu", tag);
        Main.pl.saveConfig();
    }
    

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("minicastelo")) {
            if (args.length == 0 && sender instanceof Player) {
                final Player p = (Player)sender;
                if (this.plugin.core1.getClanManager().getClanPlayer((Player)sender) == null) {
                    sender.sendMessage("§6[MiniCastelo] §cVoce não tem clan");
                    return true;
                }
                final String tag = this.plugin.core1.getClanManager().getClanPlayerName(((Player)sender).getName()).getTag();
                if (!Main.pl.getConfig().contains("ClanVenceu")) {
                    sender.sendMessage("§6[MiniCastelo] §cSem clan vencedor no momento!");
                    return true;
                }
                final String tagV = Main.pl.getConfig().getString("ClanVenceu");
                if (!tag.equalsIgnoreCase(tagV)) {
                    sender.sendMessage("§6[MiniCastelo] §cVocê não esta no clan vencedor do Gladiador!");
                }
                else {
                final String[] cas = Main.pl.getConfig().getString("Arena.Castelo").split(";");
                this.castelo = new Location(Main.pl.getServer().getWorld(cas[0]), Double.parseDouble(cas[1]), Double.parseDouble(cas[2]), Double.parseDouble(cas[3]), Float.parseFloat(cas[4]), Float.parseFloat(cas[5]));
                p.teleport(castelo);
                sender.sendMessage(ChatColor.GOLD + "[MiniCastelo] " + ChatColor.GREEN + "Teleportado para o Castelo!");
                }
                return true;
            }
            else if (args[0].equalsIgnoreCase("setcastelo")) {
                if (!sender.hasPermission("gladiador.admin")) {
                    sender.sendMessage("§7[§6MiniGladiador§7] §cVocê nao tem permissao para executar esse comando!");
                    return true;
                }
                final Player p = (Player)sender;
                final Location i = p.getLocation();
                this.plugin.getConfig().set("Arena.Castelo", (Object)(String.valueOf(String.valueOf(p.getWorld().getName())) + ";" + i.getX() + ";" + i.getY() + ";" + i.getZ() + ";" + i.getYaw() + ";" + i.getPitch()));
                this.plugin.saveConfig();
                sender.sendMessage(ChatColor.GOLD + "[MiniCastelo] " + ChatColor.GREEN + "Spawn marcado!");
                return true;
            }
        }
		return false;
    }
}

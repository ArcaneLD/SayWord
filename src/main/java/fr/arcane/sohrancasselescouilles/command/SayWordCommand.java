package fr.arcane.sohrancasselescouilles.command;

import fr.arcane.sohrancasselescouilles.SohranCasseLesCouilles;
import fr.arcane.sohrancasselescouilles.twitchevent.MessageEvent;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class SayWordCommand implements CommandExecutor {

    String word = "[PAS DE MOT]";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        new BukkitRunnable() {

            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&l[METTEZ UN MOT DANS LE CHAT DANS LES PROCHAINES 20 SEC]"));

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        ArrayList<String> lastMessages = MessageEvent.lastMessages;
                        if (lastMessages.size() <= 0) {
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e[SohranSayWord] Personne n'a parlé dans ton chat, Looser va"));
                        } else {

                            Random r = new Random();
                            int index = r.nextInt(lastMessages.size() - 1);

                            word = lastMessages.get(index);

                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e[SohranSayWord] Le nouveau mot à ne pas dire est : " + word));

                        }

                    }
                }.runTaskLater(SohranCasseLesCouilles.plugin, 400);
            }
        }.runTaskTimer(SohranCasseLesCouilles.plugin, 0, 6000);

        new BukkitRunnable() {

            @Override
            public void run() {
                for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                    players.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', "&aMot à ne pas dire : " + word.toUpperCase(Locale.ROOT))));
                }
            }
        }.runTaskTimer(SohranCasseLesCouilles.plugin, 0, 10);




        return false;
    }
}

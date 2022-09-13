package fr.arcane.sohrancasselescouilles;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.ITwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import fr.arcane.sohrancasselescouilles.command.SayWordCommand;
import fr.arcane.sohrancasselescouilles.twitchevent.MessageEvent;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SohranCasseLesCouilles extends JavaPlugin {

    public ITwitchClient twitchClient;
    // token app twitch secret : yk959o3grgwzkcr4oma3rootthat04 id : utqc6ie7isiad9b6en736bhnnwhnfs

    public static SohranCasseLesCouilles plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getCommand("sayword").setExecutor(new SayWordCommand());

        twitchClient = TwitchClientBuilder.builder()
                .withClientId("utqc6ie7isiad9b6en736bhnnwhnfs")
                .withClientSecret("yk959o3grgwzkcr4oma3rootthat04")
                .withEnableHelix(true)
                .withDefaultEventHandler(SimpleEventHandler.class)
                .withEnableChat(true)
                .build();

        System.out.println("[SayWord] Initializing");
        System.out.println("[SayWord] Registering Twitch Events");

        twitchClient.getChat().joinChannel("sohranlive");

        twitchClient.getEventManager().getEventHandler(SimpleEventHandler.class).registerListener(new MessageEvent());
    }

    @Override
    public void onDisable() {
        if (twitchClient != null)
            twitchClient.close();

    }

    public ITwitchClient getTwitchClient() {
        return this.twitchClient;
    }
}

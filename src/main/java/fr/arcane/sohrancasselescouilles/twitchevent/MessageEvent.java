package fr.arcane.sohrancasselescouilles.twitchevent;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

import java.util.ArrayList;
import java.util.Locale;

public class MessageEvent {

    public static ArrayList<String> lastMessages = new ArrayList<>();


    @EventSubscriber
    public void onMessageinChat(ChannelMessageEvent event) {
        String msg = event.getMessage();
        System.out.println("(" + event.getChannel().getName().toUpperCase(Locale.ROOT) +  ")" + "[" + event.getUser().getName() + "] : " + msg);
        if (lastMessages.size() <= 24) {
            lastMessages.add(0, msg);
        } else {
            lastMessages.clear();

        }

    }
}

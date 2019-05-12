package com.krupoderov.discordbot;

import com.krupoderov.discordbot.commands.filter.FilterSwitch;
import com.krupoderov.discordbot.database.Storage;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import static com.krupoderov.discordbot.commands.filter.FilterMessage.allowed;

public class Filter extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

        Storage words = new Storage();
        String[] englishBadWords = words.getLIST_OF_BAD_WORDS();
        String[] russianBadWords = words.getRUSSIAN_BAD_WORDS();

        String[] message = e.getMessage().getContentRaw().split(" ");

        if (FilterSwitch.filterOn) {

            //english words
            for (int i = 0; i < message.length; i++) {
                for (int b = 0; b < englishBadWords.length; b++) {
                    if (message[i].equalsIgnoreCase(englishBadWords[b])) {
                        e.getMessage().delete().queue();
                        if (allowed == true) {
                            e.getChannel().sendMessage("Do not use foul language. Here we communicate culturally.").queue();
                        }
                    }
                }
            }

            //russian words
            for (int i = 0; i < message.length; i++) {
                for (int b = 0; b < russianBadWords.length; b++) {
                    if (message[i].equalsIgnoreCase(russianBadWords[b])) {
                        e.getMessage().delete().queue();
                        if (allowed == true) {
                            e.getChannel().sendMessage("Не употребляйте нецензурную брань. Здесь мы общаемся культурно.").queue();
                        }
                    }
                }
            }
        } else {
            System.out.println("the filter is disabled");
        }
    }
}

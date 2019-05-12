package com.krupoderov.discordbot.commands.filter;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class FilterSwitch extends ListenerAdapter {

    public static boolean filterOn = true;

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

        if (e.getMessage().getContentRaw().equalsIgnoreCase("!tooglefilter") && filterOn) {
            filterOn = false;
            e.getChannel().sendMessage("The Curse-Filter has been disabled by " + e.getMember().getUser().getName()).queue();
        } else if (e.getMessage().getContentRaw().equalsIgnoreCase("!tooglefilter") && filterOn == false) {
            filterOn = true;
            e.getChannel().sendMessage("The Curse-Filter has been enabled by " + e.getMember().getUser().getName()).queue();
        }
    }
}

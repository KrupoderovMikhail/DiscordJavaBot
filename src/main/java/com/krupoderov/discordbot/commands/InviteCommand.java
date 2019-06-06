package com.krupoderov.discordbot.commands;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Класс, представляющий собой описание команды invite,
 * осуществляющей создание приглашения на текущий сервер
 *
 * @version 1.0
 * @author Krupoderov Mikhail
 */
/* Создание ссылки приглашения на текущий сервер */
public class InviteCommand extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        int timeString = 3600;
        String[] message = e.getMessage().getContentRaw().split(" ");
        if (message[0].equalsIgnoreCase("!invite") && message.length == 1) {
            e.getChannel().sendMessage("Чтобы создать приглашение на сервер напишите: !invite create").queue();
        } else if (message.length >= 2 && message[0].equalsIgnoreCase("!invite") && message[1].equalsIgnoreCase("create")) {
            e.getChannel().sendMessage("Эй " + e.getAuthor().getName() + "! Ты хочешь кого-то пригласить на сервер? Круто!").queue();
            e.getChannel().sendMessage("Дай ему эту ссылку: " + e.getChannel().createInvite().setMaxAge(3600).complete().getURL()).queue();
            e.getChannel().sendMessage("Срок действия приглашение истекает через: " + timeString + " секунд.").queue();
        }
    }
}

package com.krupoderov.discordbot.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс, представляющий собой описание команды "Инфомация о пользователе"
 * Появляется ошибка NullPointerException, если никнейм содержит больше одного слова(исправление не найдено)
 *
 * @version 1.0
 * @author Krupoderov Mikhail
 */
public class UserInfoCommand extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String[] message = e.getMessage().getContentRaw().split(" ");

        if (message.length == 1 && message[0].equalsIgnoreCase("!user")) {
            e.getChannel().sendMessage("Чтобы получить информацию о пользователей используйте команду(без скобок): !user [name]").queue();

        } else if (message.length == 2 && message[0].equalsIgnoreCase("!user")) {
            String username = message[1];
            User user = e.getGuild().getMembersByName(String.valueOf(username), true).get(0).getUser();
            String avatar = user.getAvatarUrl();
            EmbedBuilder avatarEmbed = new EmbedBuilder();

            avatarEmbed.setTitle(username);
            avatarEmbed.setColor(Color.GREEN);
            avatarEmbed.addField("Имя", user.getName(), true);
            avatarEmbed.addField("Онлайн статус: ", e.getGuild().getMembersByName(username, true).get(0).getOnlineStatus().toString(), true);
            avatarEmbed.addField("Информация: ", "Пригласил на сервер, " + e.getMember().getAsMention(), true);
            avatarEmbed.setImage(avatar);
            avatarEmbed.setFooter("Информация была запрошена @ " + formatter.format(date), e.getGuild().getIconUrl());

            e.getChannel().sendMessage(avatarEmbed.build()).queue();
        } else if (message.length >= 3 && message[0].equalsIgnoreCase("!user")) {
            e.getChannel().sendMessage("В данный момент наблюдаются проблемы, если никнейм состоит из более одного слова.\nИзвините за предоставляенные неудобства.").queue();
        }
    }
}
package com.krupoderov.discordbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Класс, представляющий собой описание команды serverinfo,
 * осуществляющей отображение информации о текущем сервере
 *
 * @version 1.0
 * @author Krupoderov Mikhail
 */
public class ServerInfo  extends Command {

        public ServerInfo() {
            this.name = "serverinfo";
            this.aliases = new String[]{"serverinfo"};
            this.help = "Gives information about the server.";
        }

        @Override
        protected void execute(CommandEvent e) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            EmbedBuilder eb = new EmbedBuilder();
            String[] members = new String[e.getGuild().getMembers().size()];

            for (int i = 0; i < e.getGuild().getMembers().size(); i++) {
                members[i] = e.getGuild().getMembers().get(i).getEffectiveName();
            }
            eb.setColor(Color.RED);
            eb.setAuthor(e.getGuild().getName());
            eb.setThumbnail("https://i.ytimg.com/vi/Y2IznD5gnvI/maxresdefault.jpg");
            eb.addField("Владелец сервера: ", e.getGuild().getOwner().getEffectiveName(), false);
            eb.addField("Количество участников:", Integer.toString(e.getGuild().getMembers().size()), true);
            eb.setDescription("**Участники:** \n" + Arrays.toString(members) +
                    "\n **Приглашение на сервер:**" +
                    "\n" + "https://discord.gg/d374tV8");

            eb.setFooter("Информация была запрошена @ " + formatter.format(date), e.getGuild().getIconUrl());

            e.getChannel().sendMessage(eb.build()).queue();
        }
    }


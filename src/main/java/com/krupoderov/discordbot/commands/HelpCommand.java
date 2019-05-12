package com.krupoderov.discordbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelpCommand extends Command {

    public HelpCommand() {
        this.name = "help";
        this.aliases = new String[]{"help"};
        this.help = "Gives information about the commands.";
    }

    @Override
    protected void execute(CommandEvent e) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle(":information_source: Команды бота: ");
        eb.setColor(Color.ORANGE);
        eb.addField(":keyboard: !calculate", "Калькулятор для работы с двумя числами.", false);
        eb.addField(":children_crossing: !tooglefilter", "Включить-выключить фильтр плохих слов(Временное решение, позже команда будет удалена).", false);
        eb.addField(":children_crossing: !filtermessage", "Включить-выключить предупреждающее сообщение(Временное решение, позже команда будет удалена).", false);
        eb.addField(":couple: !invite", "Создать приглашение на сервер.", false);
        eb.addField(":mag_right: !user", "Получить информацию о пользователе.", false);
        eb.setFooter("Информация была запрошена @ " + formatter.format(date), e.getGuild().getIconUrl());

        e.getChannel().sendMessage(eb.build()).queue();
    }
}

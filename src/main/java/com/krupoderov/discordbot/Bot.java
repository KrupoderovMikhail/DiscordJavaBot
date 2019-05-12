package com.krupoderov.discordbot;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.krupoderov.discordbot.commands.*;
import com.krupoderov.discordbot.commands.filter.FilterMessage;
import com.krupoderov.discordbot.commands.filter.FilterSwitch;
import com.krupoderov.discordbot.secret.Secrets;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;

public class Bot {

    private Bot() {

        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setOwnerId(Secrets.getOWNER());
        builder.setPrefix("!");
        builder.setHelpWord("helpme");
        builder.addCommand(new HelpCommand());
        builder.addCommand(new ServerInfo());

        CommandClient client = builder.build();

        try {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setAudioEnabled(false)
                    .setToken(Secrets.getTOKEN())
                    .setGame(Game.playing("!help - показать команды"))
                    .build().awaitReady();

            jda.addEventListener(new CalculateCommand());
            jda.addEventListener(new InviteCommand());
            jda.addEventListener(new UserInfoCommand());
            jda.addEventListener(new Filter());
            jda.addEventListener(new FilterSwitch());
            jda.addEventListener(new FilterMessage());

            jda.addEventListener(client);
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Bot();
    }
}

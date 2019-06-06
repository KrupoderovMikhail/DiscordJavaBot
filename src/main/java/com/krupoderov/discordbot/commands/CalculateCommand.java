package com.krupoderov.discordbot.commands;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


/**
 * Класс, представляющий собой описание команды "Калькулятор"
 *
 * @version 1.0
 * @author Krupoderov Mikhail
 */
public class CalculateCommand extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

        String[] message = e.getMessage().getContentRaw().split(" ");

        try {
            /* Команда калькулятор, умеет производить операции с двумя числами */
            if (message[0].equalsIgnoreCase("!calculate") && message.length == 1) {
                e.getChannel().sendMessage("Чтобы использовать эту команду введите(без скобок): !calculate [add/sub/mult/div] [первое число] [второе число].\nЕсли возникнут дополнительные вопросы по команде, введите !calculate help").queue();

            } else if (message[0].equalsIgnoreCase("!calculate") && message[1].equalsIgnoreCase("help")) {
                e.getChannel().sendMessage("Addition(add) - Сложение\nSubtraction(sub) - Вычитание\nMultiplication(mult) - Умножение\nDivision(div) - Деление").queue();

            } else if (message[0].equalsIgnoreCase("!calculate") && message[1].equalsIgnoreCase("add")) {
                double num1 = Double.parseDouble(message[2]);
                double num2 = Double.parseDouble(message[3]);
                e.getChannel().sendMessage("Результат: " + (num1 + num2)).queue();
            } else if (message[0].equalsIgnoreCase("!calculate") && message[1].equalsIgnoreCase("sub")) {
                double num1 = Double.parseDouble(message[2]);
                double num2 = Double.parseDouble(message[3]);
                e.getChannel().sendMessage("Результат: " + (num1 - num2)).queue();
            } else if (message[0].equalsIgnoreCase("!calculate") && message[1].equalsIgnoreCase("mult")) {
                double num1 = Double.parseDouble(message[2]);
                double num2 = Double.parseDouble(message[3]);
                e.getChannel().sendMessage("Результат: " + (num1 * num2)).queue();
            } else if (message[0].equalsIgnoreCase("!calculate") && message[1].equalsIgnoreCase("div")) {
                double num1 = Double.parseDouble(message[2]);
                double num2 = Double.parseDouble(message[3]);
                e.getChannel().sendMessage("Результат: " + (num1 / num2)).queue();
            }
        } catch (NumberFormatException ex) {
            e.getChannel().sendMessage("Слишком большое число, я не могу его посчитать, я же не вундеркуйнд").queue();
        }
    }
}

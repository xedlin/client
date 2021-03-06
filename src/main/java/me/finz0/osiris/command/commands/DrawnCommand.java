package me.finz0.osiris.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.finz0.osiris.command.Command;
import me.finz0.osiris.module.ModuleManager;

public class DrawnCommand extends Command {
    boolean found;
    @Override
    public String[] getAlias() {
        return new String[]{"drawn", "visible", "d"};
    }

    @Override
    public String getSyntax() {
        return ChatFormatting.RED + "Usage: " + ChatFormatting.WHITE + Command.prefix + "drawn <module>";
    }

    @Override
    public void onCommand(String command, String[] args) throws Exception {
        found = false;
        ModuleManager.getModules().forEach(m -> {
            if(m.getName().equalsIgnoreCase(args[0])){
                if(m.isDrawn()){
                    m.setDrawn(false);
                    found = true;
                    Command.sendClientMessage(ChatFormatting.AQUA + m.getName() + ChatFormatting.WHITE + " drawn = " + ChatFormatting.RED +"false");
                } else if(!m.isDrawn()){
                    m.setDrawn(true);
                    found = true;
                    Command.sendClientMessage(ChatFormatting.AQUA + m.getName() + ChatFormatting.WHITE + " drawn = " + ChatFormatting.GREEN +"true");
                }
            }
        });
        if(!found && args.length == 1) Command.sendClientMessage(ChatFormatting.DARK_RED + "Module not found!");
    }
}

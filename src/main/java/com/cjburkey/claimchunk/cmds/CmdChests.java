package com.cjburkey.claimchunk.cmds;

import com.cjburkey.claimchunk.ClaimChunk;
import com.cjburkey.claimchunk.Utils;
import com.cjburkey.claimchunk.cmd.Argument;
import com.cjburkey.claimchunk.cmd.ICommand;
import com.cjburkey.claimchunk.cmd.MainHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdChests implements ICommand {

    @Override
    public String getCommand() {
        return "chests";
    }

    @Override
    public String getDescription() {
        return ClaimChunk.getInstance().getMessages().cmdChests;
    }

    @Override
    public boolean hasPermission(CommandSender sender) {
        return Utils.hasPerm(sender, true, "chests");
    }

    @Override
    public String getPermissionMessage() {
        return ClaimChunk.getInstance().getMessages().containerAccessNoPerm;
    }

    @Override
    public Argument[] getPermittedArguments() {
        return new Argument[] {new Argument("player", Argument.TabCompletion.OFFLINE_PLAYER)};
    }

    @Override
    public int getRequiredArguments() {
        return 0;
    }

    @Override
    public boolean onCall(String cmdUsed, Player executor, String[] args) {
        if (args.length == 0) MainHandler.listContainerAccessors(executor);
        else MainHandler.containerAccessChunk(executor, args[0].split(","));
        return true;
    }

}
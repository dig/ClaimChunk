package com.cjburkey.claimchunk.player;

import com.cjburkey.claimchunk.Config;
import com.cjburkey.claimchunk.data.newdata.IClaimChunkDataHandler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.bukkit.entity.Player;

public class PlayerHandler {

    private final IClaimChunkDataHandler dataHandler;

    public PlayerHandler(IClaimChunkDataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public Collection<SimplePlayerData> getJoinedPlayers() {
        return dataHandler.getPlayers();
    }

    public List<String> getJoinedPlayersFromName(String start) {
        List<String> out = new ArrayList<>();
        for (SimplePlayerData ply : dataHandler.getPlayers()) {
            if (ply.lastIgn != null && ply.lastIgn.toLowerCase().startsWith(start.toLowerCase())) {
                out.add(ply.lastIgn);
            }
        }
        return out;
    }

    // Returns whether the player NOW has access
    public boolean toggleAccess(UUID owner, UUID accessee) {
        boolean newVal = !hasAccess(owner, accessee);
        dataHandler.setPlayerAccess(owner, accessee, newVal);
        return newVal;
    }

    public boolean toggleContainerAccess(UUID owner, UUID accessee) {
        boolean newVal = !hasContainerAccess(owner, accessee);
        dataHandler.setPlayerContainerAccess(owner, accessee, newVal);
        return newVal;
    }

    public boolean hasAccess(UUID owner, UUID accessee) {
        return dataHandler.playerHasAccess(owner, accessee);
    }

    public boolean hasContainerAccess(UUID owner, UUID accessee) {
        return dataHandler.playerHasContainerAccess(owner, accessee);
    }

    public UUID[] getAccessPermitted(UUID owner) {
        return dataHandler.getPlayersWithAccess(owner);
    }
    public UUID[] getContainerAccessPermitted(UUID owner) {
        return dataHandler.getPlayersWithContainerAccess(owner);
    }

    public boolean toggleAlerts(UUID player) {
        boolean newVal = !hasAlerts(player);
        dataHandler.setPlayerReceiveAlerts(player, newVal);
        return newVal;
    }

    public boolean hasAlerts(UUID owner) {
        return dataHandler.getPlayerReceiveAlerts(owner);
    }

    public void setChunkName(UUID owner, String name) {
        dataHandler.setPlayerChunkName(owner, name);
    }

    public void clearChunkName(UUID owner) {
        setChunkName(owner, null);
    }

    public String getChunkName(UUID owner) {
        String chunkName = dataHandler.getPlayerChunkName(owner);
        if (chunkName != null) return chunkName;
        return dataHandler.getPlayerUsername(owner);
    }

    public boolean hasChunkName(UUID owner) {
        return dataHandler.getPlayerChunkName(owner) != null;
    }

    public String getUsername(UUID player) {
        return dataHandler.getPlayerUsername(player);
    }

    public UUID getUUID(String username) {
        return dataHandler.getPlayerUUID(username);
    }

    public void setLastJoinedTime(UUID player, long time) {
        dataHandler.setPlayerLastOnline(player, time);
    }

    public void onJoin(Player ply) {
        if (!dataHandler.hasPlayer(ply.getUniqueId())) {
            dataHandler.addPlayer(ply.getUniqueId(),
                    ply.getName(),
                    Config.getBool("chunks", "defaultSendAlertsToOwner"));
        }
    }

}

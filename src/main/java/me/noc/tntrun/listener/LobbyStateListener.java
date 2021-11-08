package me.noc.tntrun.listener;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockTNT;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.player.*;
import cn.nukkit.math.BlockFace;
import cn.nukkit.scheduler.NukkitRunnable;
import me.noc.tntrun.TnTRun;
import me.noc.tntrun.gamestates.GameStateManager;
import me.noc.tntrun.gamestates.LobbyState;

public class LobbyStateListener implements Listener {

    private NukkitRunnable runnable;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.teleport(TnTRun.getPlugin().getLocationSystem().getLocation("lobby"));
        player.getInventory().clearAll();
        player.setGamemode(0);

        LobbyState lobbyState = (LobbyState) GameStateManager.getInstance().getGameState();
        lobbyState.recalculateTime();
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        new NukkitRunnable() {
            @Override
            public void run() {
                LobbyState lobbyState = (LobbyState) GameStateManager.getInstance().getGameState();
                lobbyState.recalculateTime();
            }
        }.runTaskLater(TnTRun.getPlugin(), 1);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getPlayer().getGamemode() != 1) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (event.getPlayer().getGamemode() != 1) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(PlayerFoodLevelChangeEvent event) {
        if (event.getPlayer() != null) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        Block block = player.getLocation().getLevelBlock().getSide(BlockFace.DOWN);

        if(block instanceof BlockTNT){
            runnable = new NukkitRunnable() {
                @Override
                public void run() {
                    block.level.setBlock((int)block.x, (int)block.y, (int)block.z, Block.get(Block.AIR), true, true);
                }
            };
            runnable.runTaskLater(TnTRun.getPlugin(), 10);
        }
    }
}

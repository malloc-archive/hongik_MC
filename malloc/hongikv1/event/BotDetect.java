package malloc.hongikv1.event;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.net.InetAddress;
import java.time.Duration;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import malloc.hongikv1.main.Main; 

public class BotDetect implements Listener {
    
    private ConcurrentHashMap<UUID, Integer> botTasks = new ConcurrentHashMap<>();
    
    @EventHandler
    public void possibleBotHandshake(AsyncPlayerPreLoginEvent e) {
        UUID uuid = e.getUniqueId();
        String ip = e.getAddress().getHostAddress();
        
        int taskId = Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getMain(), () -> {
            
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW
            		+ "\n================================\n\n" 
                    + "Possible bot handshaked. IP: " + ip 
                    + "\n\n================================");
     

            @SuppressWarnings("unchecked")
			BanList<InetAddress> ipBanList = (BanList<InetAddress>) Bukkit.getBanList(BanList.Type.IP);
            ipBanList.addBan(
            		e.getAddress(), 
            		ChatColor.RED + "You have been banned by an automatic bot detector.\n"
            		+ "If you think this is a mistake, please contact us on Discord at sfinae_cpp.", 
            		(Duration)null, 
            		"Bot Detector");
            botTasks.remove(uuid);
            
        }, 100L).getTaskId(); 
        
        botTasks.put(uuid, taskId);
    }
    
    @EventHandler
    public void loginFinished(PlayerJoinEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        
        if(botTasks.containsKey(uuid)) {
            int taskId = botTasks.get(uuid);
            Bukkit.getScheduler().cancelTask(taskId);
            
            botTasks.remove(uuid);
        }
    }
}
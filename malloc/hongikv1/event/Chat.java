package malloc.hongikv1.event;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import malloc.hongikv1.config.ConfigMain;

public class Chat implements Listener{
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		String uuid = e.getPlayer().getUniqueId().toString();
		
		String nickName = ConfigMain.get().getString("nickname." + uuid);
		
		if (nickName != null) {
			e.setCancelled(true);
			Bukkit.broadcastMessage("[" + nickName + "]" + " " + e.getMessage());
		}
	}

}

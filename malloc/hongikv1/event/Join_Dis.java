package malloc.hongikv1.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import malloc.hongikv1.config.ConfigMain;

public class Join_Dis implements Listener{
	
	@EventHandler
	public void Join(PlayerJoinEvent e) {
		
		ConfigMain.registerAccount(e);
		e.getPlayer().sendMessage(Integer.toString(ConfigMain.getMoney(e.getPlayer().getName())));
		ScoreBoard.setScoreboard(e);
	
	}

}

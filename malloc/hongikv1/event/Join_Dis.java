package malloc.hongikv1.event;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import malloc.hongikv1.config.ConfigMain;

public class Join_Dis implements Listener{
	
	@EventHandler
	public void Join(PlayerJoinEvent e) {
		
		ConfigMain.registerAccount(e);
		ScoreBoard.setScoreboard(e);
		e.getPlayer().sendMessage(ChatColor.YELLOW + "---------------------------------\n"
				+ "명령어가 추가되었습니다!\n"
				+ "/의견제출 할말\n"
				+ "   -익명 의견을 제출합니다.\n"
				+ "/송금 이름 금액\n"
				+ "   -해당 플레이어에게 돈을 송금합니다.\n---------------------------------\n ");
	}

}

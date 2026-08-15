package malloc.hongikv1.thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import malloc.hongikv1.main.Main;

public class Tpa extends Thread{
	
	Collection<TpaStruct> list = new ArrayList<TpaStruct>();
	boolean accepted = false;

	public Collection<TpaStruct> getList(){
		return list;
	}
	
	public boolean inList(String from, String to) {
		for(TpaStruct tmp : list) {
			if(tmp.from.equals(from) && tmp.to.equals(to)) {
				return true;
			}
		}
		return false;
	}
	
	public void addRequest(String from, String to) {
		TpaStruct tmp = new TpaStruct(from, to, 15);
		if(inList(tmp.from, tmp.to)) {
			Bukkit.getPlayer(from).sendMessage("이미 해당 요청이 대기중입니다!");
		} else {
			list.add(tmp);
			Bukkit.getPlayer(from).sendMessage(to + "님에게 요청이 전송되었습니다!");
			Bukkit.getPlayer(to).sendMessage(from + "님이 텔레포트를 요청했습니다. 명령어: /tpaccept 혹은 /todeny");
		}
	}
	
	public boolean inAcceptList(String to) {
		for(TpaStruct tmp : list) {
			if(tmp.to.equals(to)) {
				return true;
			}
		}
		return false;
	}
	
	public void tpAccept(String to) {
	    Iterator<TpaStruct> iter = list.iterator();
	    while(iter.hasNext()) {
	        TpaStruct tmp = iter.next();
	        if(tmp.to.equals(to)) {
	            iter.remove(); 
	            
	            if(!Main.isOnline(tmp.from)) {
	                Bukkit.getPlayer(to).sendMessage(ChatColor.RED + "해당 플레이어는 온라인이 아닙니다!");
	                return;
	            }
	            
	            Bukkit.getPlayer(to).sendMessage(ChatColor.GREEN + "요청을 수락했습니다.");
	            Bukkit.getPlayer(tmp.from).sendMessage(ChatColor.YELLOW + "요청이 수락되었습니다!");
	            Bukkit.getPlayer(tmp.from).teleport(Bukkit.getPlayer(to));
	            return; 
	        }
	    }
	    
	    Bukkit.getPlayer(to).sendMessage(ChatColor.RED + "요청이 없거나 만료되었습니다.");
	}
	
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			Iterator<TpaStruct> iter = list.iterator();
	        while(iter.hasNext()) {
	            TpaStruct tmp = iter.next();
	            tmp.sec -= 0.2;
	            if(tmp.sec <= 0) {
	                if(Main.isOnline(tmp.from)) {
	                    Bukkit.getPlayer(tmp.from).sendMessage(tmp.to + "님에게 보낸 요청이 만료되었습니다.");
	                }
	                iter.remove(); 
	            }
	        }
		}
	}
	
}

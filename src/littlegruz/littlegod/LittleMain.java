package littlegruz.littlegod;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LittleMain extends JavaPlugin{
   Logger log = Logger.getLogger("This is MINECRAFT!");
   private final LittlePlayerListener playerListener = new LittlePlayerListener(this);

   public void onDisable(){
      log.info("LittleGod is now disabled");
   }

   public void onEnable(){
      
      //Listeners set up
      PluginManager pm = this.getServer().getPluginManager();
      pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
      
      log.info("LittleGod v0.1 is enabled");
   }

}

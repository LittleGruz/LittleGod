package littlegruz.littlegod;

import java.util.logging.Logger;

import littlegruz.littlegod.listeners.LittleBlockListener;
import littlegruz.littlegod.listeners.LittlePlayerListener;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LittleMain extends JavaPlugin{
   Logger log = Logger.getLogger("This is MINECRAFT!");
   private final LittlePlayerListener playerListener = new LittlePlayerListener(this);
   private final LittleBlockListener blockListener = new LittleBlockListener(this);
   private Location mosesStart;
   private Location mosesEnd;
   private boolean mosesActive;

   public void onDisable(){
      log.info("LittleGod is now disabled");
   }

   public void onEnable(){
      setMosesActive(false);
      
      //Listeners set up
      PluginManager pm = this.getServer().getPluginManager();
      pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
      pm.registerEvent(Event.Type.BLOCK_FROMTO, blockListener, Event.Priority.Normal, this);
      
      log.info("LittleGod v0.1 is enabled");
   }
   
   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
      if(commandLabel.compareToIgnoreCase("numberwang") == 0){
         mosesActive = false;
         return true;
      }
      return false;
   }

   public void setMosesStart(Location mosesStart){
      this.mosesStart = mosesStart;
   }

   public Location getMosesStart(){
      return mosesStart;
   }

   public void setMosesEnd(Location mosesEnd){
      this.mosesEnd = mosesEnd;
   }

   public Location getMosesEnd(){
      return mosesEnd;
   }

   public void setMosesActive(boolean mosesActive){
      this.mosesActive = mosesActive;
   }

   public boolean isMosesActive(){
      return mosesActive;
   }

}

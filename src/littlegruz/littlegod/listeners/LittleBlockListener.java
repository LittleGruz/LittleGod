package littlegruz.littlegod.listeners;

import littlegruz.littlegod.LittleMain;

import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockListener;

public class LittleBlockListener extends BlockListener{
   private LittleMain plugin;
   
   public LittleBlockListener(LittleMain instance){
      plugin = instance;
   }

   public void onBlockFromTo(BlockFromToEvent event){
      if(plugin.isMosesActive()){
         if((int) plugin.getMosesStart().getX() == (int) plugin.getMosesEnd().getX()){
            if(plugin.getMosesStart().getZ() > plugin.getMosesEnd().getZ()){
               if(event.getToBlock().getLocation().getZ() >= plugin.getMosesEnd().getZ()
                     && event.getToBlock().getLocation().getZ() <= plugin.getMosesStart().getZ()
                     && event.getToBlock().getLocation().getX() == plugin.getMosesEnd().getX()){
                  event.setCancelled(true);
               }
            }
            else{
               if(event.getBlock().getLocation().getZ() <= plugin.getMosesEnd().getZ()
                     && event.getToBlock().getLocation().getZ() >= plugin.getMosesStart().getZ()
                     && event.getToBlock().getLocation().getX() == plugin.getMosesEnd().getX()){
                  event.setCancelled(true);
               }
            }
         }
         else{
            if(plugin.getMosesStart().getX() > plugin.getMosesEnd().getX()){
               if(event.getBlock().getLocation().getX() >= plugin.getMosesEnd().getX()
                     && event.getToBlock().getLocation().getX() <= plugin.getMosesStart().getX()
                     && event.getToBlock().getLocation().getZ() == plugin.getMosesEnd().getZ()){
                  event.setCancelled(true);
               }
            }
            else{
               if(event.getToBlock().getLocation().getX() <= plugin.getMosesEnd().getX()
                     && event.getToBlock().getLocation().getX() >= plugin.getMosesStart().getX()
                     && event.getToBlock().getLocation().getZ() == plugin.getMosesEnd().getZ()){
                  event.setCancelled(true);
               }
            }
         }
      }
   }
}

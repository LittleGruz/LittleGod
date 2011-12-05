package littlegruz.littlegod.listeners;

import littlegruz.littlegod.LittleMain;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class LittlePlayerListener extends PlayerListener{
   private LittleMain plugin;
   
   public LittlePlayerListener(LittleMain instance){
      plugin = instance;
   }
   
   public void onPlayerInteract(PlayerInteractEvent event){
      //If it is air, then a fist was used when right clicking
      if(event.getMaterial().compareTo(Material.AIR) == 0
            && event.getAction().compareTo(Action.RIGHT_CLICK_BLOCK) == 0
            && !plugin.isMosesActive()){
         Location loc;
         Block startingBlock;
         loc = event.getClickedBlock().getLocation();
         loc.setY(loc.getY() + 1);
         startingBlock = loc.getBlock();
         
         if(startingBlock.getType().compareTo(Material.STATIONARY_WATER) == 0){
            boolean part = false;
            int dir = 0;

            //Determine direction the water needs to part in
            loc.setX(loc.getX() + 1);
            if(loc.getBlock().getType().compareTo(Material.STATIONARY_WATER) == 0){
               loc.setX(loc.getX() - 2);
               //If not water then part water in other direction
               if(loc.getBlock().getType().compareTo(Material.STATIONARY_WATER) != 0){
                  part = !part;
                  dir = 0;
               }
            }
            else{
               loc.setX(loc.getX() - 2);
               //If not water then part water in this direction
               if(loc.getBlock().getType().compareTo(Material.STATIONARY_WATER) == 0){
                  part = !part;
                  dir = 1;
               }
            }
            loc = startingBlock.getLocation();
            loc.setZ(loc.getZ() + 1);
            if(loc.getBlock().getType().compareTo(Material.STATIONARY_WATER) == 0){
               loc.setZ(loc.getZ() - 2);
               //If not water then part water in other direction
               if(loc.getBlock().getType().compareTo(Material.STATIONARY_WATER) != 0){
                  part = !part;
                  dir = 2;
               }
            }
            else{
               loc.setZ(loc.getZ() - 2);
               //If not water then part water in this direction
               if(loc.getBlock().getType().compareTo(Material.STATIONARY_WATER) == 0){
                  part = !part;
                  dir = 3;
               }
            }
            
            if(part){
               //TODO: Learn to throw a custom event to make the water flow back
               //Could make it part from ticks given off by the liquid flow event
               Location locDown;
               plugin.setMosesActive(true);
               loc = startingBlock.getLocation();
               plugin.setMosesStart(loc.clone());
               switch(dir){
               case 0:
                  loc.getBlock().setType(Material.AIR);
                  loc.setX(loc.getX() + 1);
                  while(loc.getBlock().getType().compareTo(Material.WATER) == 0
                        || loc.getBlock().getType().compareTo(Material.STATIONARY_WATER) == 0){
                     loc.getBlock().setType(Material.AIR);
                     
                     locDown = loc.clone();
                     locDown.setY(locDown.getY() - 1);
                     while(locDown.getBlock().getType().compareTo(Material.STATIONARY_WATER) == 0
                           || locDown.getBlock().getType().compareTo(Material.WATER) == 0){
                        locDown.getBlock().setType(Material.AIR);
                        locDown.setY(locDown.getY() - 1);
                     }
                     
                     loc.setX(loc.getX() + 1);
                  }
                  break;
               case 1:
                  loc.getBlock().setType(Material.AIR);
                  loc.setX(loc.getX() - 1);
                  while(loc.getBlock().getType().compareTo(Material.WATER) == 0
                        || loc.getBlock().getType().compareTo(Material.STATIONARY_WATER) == 0){
                     loc.getBlock().setType(Material.AIR);
                     
                     locDown = loc.clone();
                     locDown.setY(locDown.getY() - 1);
                     while(locDown.getBlock().getType().compareTo(Material.STATIONARY_WATER) == 0
                           || locDown.getBlock().getType().compareTo(Material.WATER) == 0){
                        locDown.getBlock().setType(Material.AIR);
                        locDown.setY(locDown.getY() - 1);
                     }
                     
                     loc.setX(loc.getX() - 1);
                  }
                  break;
               case 2:
                  loc.getBlock().setType(Material.AIR);
                  loc.setZ(loc.getZ() + 1);
                  while(loc.getBlock().getType().compareTo(Material.WATER) == 0
                        || loc.getBlock().getType().compareTo(Material.STATIONARY_WATER) == 0){
                     loc.getBlock().setType(Material.AIR);
                     
                     locDown = loc.clone();
                     locDown.setY(locDown.getY() - 1);
                     while(locDown.getBlock().getType().compareTo(Material.STATIONARY_WATER) == 0
                           || locDown.getBlock().getType().compareTo(Material.WATER) == 0){
                        locDown.getBlock().setType(Material.AIR);
                        locDown.setY(locDown.getY() - 1);
                     }
                     
                     loc.setZ(loc.getZ() + 1);
                  }
                  break;
               case 3:
                  loc.getBlock().setType(Material.AIR);
                  loc.setZ(loc.getZ() - 1);
                  while(loc.getBlock().getType().compareTo(Material.WATER) == 0
                        || loc.getBlock().getType().compareTo(Material.STATIONARY_WATER) == 0){
                     loc.getBlock().setType(Material.AIR);
                     
                     locDown = loc.clone();
                     locDown.setY(locDown.getY() - 1);
                     while(locDown.getBlock().getType().compareTo(Material.STATIONARY_WATER) == 0
                           || locDown.getBlock().getType().compareTo(Material.WATER) == 0){
                        locDown.getBlock().setType(Material.AIR);
                        locDown.setY(locDown.getY() - 1);
                     }
                     
                     loc.setZ(loc.getZ() - 1);
                  }
                  break;
               }
               plugin.setMosesEnd(loc.clone());
            }
         }
      }
      else if(event.getMaterial().compareTo(Material.AIR) == 0
            && event.getAction().compareTo(Action.RIGHT_CLICK_BLOCK) == 0
            && plugin.isMosesActive()){
         event.getPlayer().sendMessage("Moses is already parting a sea. Wait until he is done");
      }
   }
}

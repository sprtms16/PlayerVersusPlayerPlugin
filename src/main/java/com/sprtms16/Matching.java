package com.sprtms16;

import com.sprtms16.Domain.PVP;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

public class Matching implements Listener {
    private final JavaPlugin plugin;

    private static List<PVP> pvps = new ArrayList<>();

    public Matching(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMatchingEvent(PlayerInteractEntityEvent event){
        if (event.getHand (). equals (EquipmentSlot.HAND)) { // 메인 손으로 하는 Interaction 만 감지
            if (event.getRightClicked() instanceof Player) {
                Player player = event.getPlayer();
                Player targetPlayer = (Player) event.getRightClicked();
                plugin.getLogger().log(Level.INFO, "Event originator : " + player.getDisplayName());
                plugin.getLogger().log(Level.INFO, "Event targetPlayer : " + targetPlayer.getDisplayName());
                Optional<ItemStack> maybeItem = Optional.ofNullable(event.getPlayer().getInventory().getItemInMainHand());
                if(maybeItem.map(ItemStack::getItemMeta).map(ItemMeta::getDisplayName).map(s -> s.equals("Box")).orElse(false)) {
                    pvps.removeIf(p -> {
                        if(player.equals(p.getPlayer1())){
                            player.sendTitle("PVP","이전 경기를 중단 후 재 시작 합니다.",1,40,1);
                            p.getPlayer2().sendTitle("PVP","상대 플레이어가 다른 경기를 시작하였스니다.",1,40,1);
                            return true;
                        }
                        if(targetPlayer.equals(p.getPlayer2())){
                            targetPlayer.sendTitle("PVP","이전 경기를 중단 후 재 시작 합니다.",1,40,1);
                            p.getPlayer1().sendTitle("PVP","상대 플레이어가 다른 경기를 시작하였스니다.",1,40,1);
                            return true;
                        }
                        if(player.equals(p.getPlayer2())){
                            player.sendTitle("PVP","이전 경기를 중단 후 재 시작 합니다.",1,40,1);
                            p.getPlayer1().sendTitle("PVP","상대 플레이어가 다른 경기를 시작하였스니다.",1,40,1);
                            return true;
                        }
                        if(targetPlayer.equals(p.getPlayer1())){
                            targetPlayer.sendTitle("PVP","이전 경기를 중단 후 재 시작 합니다.",1,40,1);
                            p.getPlayer2().sendTitle("PVP","상대 플레이어가 다른 경기를 시작하였스니다.",1,40,1);
                            return true;
                        }
                        return false;
                    });
                    pvps.add(new PVP(player,targetPlayer));
                    player.sendTitle("PVP시작","start",1,40,1);
                    targetPlayer.sendTitle("PVP시작","start",1,40,1);
                    pvps.forEach(pvp -> plugin.getLogger().log(Level.INFO,pvp.getPlayer1().getDisplayName() + " vs " + pvp.getPlayer2().getDisplayName()));
                }
            }
        }

    }

    @Deprecated
    @EventHandler
    public void onDeadPlayerOnMatchingEvnet(EntityDamageEvent event){
        if (event.getEntity () instanceof Player) {
            Player player = (Player) event.getEntity ();
            pvps.removeIf(p -> {
                if(event.getEntity().equals(p.getPlayer1())){
                    if ((player.getHealth ()-event.getFinalDamage ()) <= 0) {
                        player.setHealth(player.getMaxHealth());//PotionEffect(PotionEffectType.HEAL);
                        event.setCancelled (true);
                        // 여기에 코드
                        p.getPlayer2().sendTitle("PVP결과","우승!",1,40,1);
                        p.getPlayer1().sendTitle("PVP결과","패배",1,40,1);
                        return true;
                    }

                }
                if(event.getEntity().equals(p.getPlayer2())){
                    if ((player.getHealth ()-event.getFinalDamage ()) <= 0) {
                        player.setHealth(player.getMaxHealth());//PotionEffect(PotionEffectType.HEAL);
                        event.setCancelled (true);
                        // 여기에 코드
                        p.getPlayer1().sendTitle("PVP결과","우승!",1,40,1);
                        p.getPlayer2().sendTitle("PVP결과","패배",1,40,1);
                        return true;
                    }
                }
                return false;
            });

        }





    }

}

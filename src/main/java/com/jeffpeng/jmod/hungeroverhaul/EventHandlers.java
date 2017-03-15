package com.jeffpeng.jmod.hungeroverhaul;

import iguanaman.hungeroverhaul.config.IguanaConfig;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

import com.jeffpeng.jmod.types.items.CoreBucket;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


public class EventHandlers {
	
	@SubscribeEvent(priority=EventPriority.HIGH)
	public void milkCow(EntityInteractEvent event){
		EntityPlayer ep = event.entityPlayer;
		ItemStack is = event.entityPlayer.getCurrentEquippedItem();
		if(is == null) return;
		Item item = is.getItem();
		if(!(item instanceof CoreBucket) || !((CoreBucket)item).hasBucketsOf("milk")) return;
		
		if((event.target instanceof EntityCow) && is.getItemDamage() == 0){
			NBTTagCompound tags = event.target.getEntityData();
			if (tags.hasKey("Milked"))
			{
				System.out.println(tags.getInteger("Milked"));
				event.setCanceled(true);
				if (!ep.worldObj.isRemote)
				event.target.playSound("mob.cow.hurt",  0.4F, 0.9F);
				return;
			} else tags.setInteger("Milked", IguanaConfig.milkedTimeout * 60);
				
			ep.getCurrentEquippedItem().stackSize--;
			if (!ep.inventory.addItemStackToInventory(((CoreBucket)item).getBucketOf("milk")))
            {
                ep.dropPlayerItemWithRandomChoice(((CoreBucket)item).getBucketOf("milk"), false);
            }
		}
	}
	

}

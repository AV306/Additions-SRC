
package net.mcreator.additions.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import net.mcreator.additions.itemgroup.AdditionsItemGroup;
import net.mcreator.additions.AdditionsModElements;

import java.util.List;

@AdditionsModElements.ModElement.Tag
public class PlasticShardItem extends AdditionsModElements.ModElement {
	@ObjectHolder("additions:plastic_shard")
	public static final Item block = null;
	public PlasticShardItem(AdditionsModElements instance) {
		super(instance, 26);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(AdditionsItemGroup.tab).maxStackSize(64).rarity(Rarity.UNCOMMON));
			setRegistryName("plastic_shard");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("This is a shard of plastic."));
		}
	}
}

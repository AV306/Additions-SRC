
package net.mcreator.additions.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.additions.itemgroup.AdditionsItemGroup;
import net.mcreator.additions.AdditionsModElements;

@AdditionsModElements.ModElement.Tag
public class PitchblendeItem extends AdditionsModElements.ModElement {
	@ObjectHolder("additions:pitchblende")
	public static final Item block = null;
	public PitchblendeItem(AdditionsModElements instance) {
		super(instance, 27);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(AdditionsItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("pitchblende");
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
	}
}

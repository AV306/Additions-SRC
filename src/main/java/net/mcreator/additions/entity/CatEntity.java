
package net.mcreator.additions.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.additions.itemgroup.AdditionsItemGroup;
import net.mcreator.additions.AdditionsModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@AdditionsModElements.ModElement.Tag
public class CatEntity extends AdditionsModElements.ModElement {
	public static EntityType entity = null;
	public CatEntity(AdditionsModElements instance) {
		super(instance, 45);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("cat")
						.setRegistryName("cat");
		elements.entities.add(() -> entity);
		elements.items
				.add(() -> new SpawnEggItem(entity, -1, -1, new Item.Properties().group(AdditionsItemGroup.tab)).setRegistryName("cat_spawn_egg"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 20, 4, 4));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::canMonsterSpawn);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modelcustom_model(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("additions:textures/black.png");
				}
			};
		});
	}
	public static class CustomEntity extends AnimalEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			setCustomName(new StringTextComponent("Cat"));
			setCustomNameVisible(true);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(4, new TemptGoal(this, 1, Ingredient.fromItems(new ItemStack(Items.COD, (int) (1)).getItem()), false));
			this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8));
			this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5);
			if (this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
			this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK);
			this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(1D);
		}

		@Override
		public AgeableEntity createChild(AgeableEntity ageable) {
			CustomEntity retval = (CustomEntity) entity.create(this.world);
			retval.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(retval)), SpawnReason.BREEDING,
					(ILivingEntityData) null, (CompoundNBT) null);
			return retval;
		}

		@Override
		public boolean isBreedingItem(ItemStack stack) {
			if (stack == null)
				return false;
			if (new ItemStack(Items.COD, (int) (1)).getItem() == stack.getItem())
				return true;
			return false;
		}
	}

	// Made with Blockbench 3.7.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modelcustom_model extends EntityModel<Entity> {
		private final ModelRenderer body;
		private final ModelRenderer belly;
		private final ModelRenderer head;
		private final ModelRenderer tail1;
		private final ModelRenderer tail2;
		private final ModelRenderer backLegL;
		private final ModelRenderer backLegR;
		private final ModelRenderer frontLegL;
		private final ModelRenderer frontLegR;
		public Modelcustom_model() {
			textureWidth = 64;
			textureHeight = 32;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 17.0F, 1.0F);
			belly = new ModelRenderer(this);
			belly.setRotationPoint(0.0F, 0.0F, 0.0F);
			body.addChild(belly);
			setRotationAngle(belly, 1.5708F, 0.0F, 0.0F);
			belly.setTextureOffset(20, 0).addBox(-2.0F, -8.0F, -3.0F, 4.0F, 16.0F, 6.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, -2.0F, -10.0F);
			body.addChild(head);
			head.setTextureOffset(0, 0).addBox(-2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);
			head.setTextureOffset(0, 24).addBox(-1.5F, -0.0156F, -4.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(0, 10).addBox(-2.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(6, 10).addBox(1.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			tail1 = new ModelRenderer(this);
			tail1.setRotationPoint(0.0F, -2.0F, 7.0F);
			body.addChild(tail1);
			setRotationAngle(tail1, 0.7854F, 0.0F, 0.0F);
			tail1.setTextureOffset(0, 15).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, 0.0F, false);
			tail2 = new ModelRenderer(this);
			tail2.setRotationPoint(0.0F, 8.0F, 0.0F);
			tail1.addChild(tail2);
			setRotationAngle(tail2, 0.7854F, 0.0F, 0.0F);
			tail2.setTextureOffset(4, 15).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, 0.0F, false);
			backLegL = new ModelRenderer(this);
			backLegL.setRotationPoint(1.1F, 1.0F, 6.0F);
			body.addChild(backLegL);
			backLegL.setTextureOffset(8, 13).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
			backLegR = new ModelRenderer(this);
			backLegR.setRotationPoint(-1.1F, 1.0F, 6.0F);
			body.addChild(backLegR);
			backLegR.setTextureOffset(8, 13).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
			frontLegL = new ModelRenderer(this);
			frontLegL.setRotationPoint(1.2F, -3.0F, -5.0F);
			body.addChild(frontLegL);
			frontLegL.setTextureOffset(40, 0).addBox(-1.0F, -0.2F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
			frontLegR = new ModelRenderer(this);
			frontLegR.setRotationPoint(-1.2F, -3.0F, -5.0F);
			body.addChild(frontLegR);
			frontLegR.setTextureOffset(40, 0).addBox(-1.0F, -0.2F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.backLegL.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.frontLegR.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.backLegR.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.frontLegL.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		}
	}
}

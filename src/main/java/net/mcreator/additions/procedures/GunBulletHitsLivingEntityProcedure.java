package net.mcreator.additions.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import net.mcreator.additions.AdditionsModElements;

import java.util.Map;

@AdditionsModElements.ModElement.Tag
public class GunBulletHitsLivingEntityProcedure extends AdditionsModElements.ModElement {
	public GunBulletHitsLivingEntityProcedure(AdditionsModElements instance) {
		super(instance, 24);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure GunBulletHitsLivingEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.attackEntityFrom(DamageSource.GENERIC, (float) 70);
	}
}

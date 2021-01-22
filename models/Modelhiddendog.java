// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelhiddendog extends EntityModel<Entity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer upperBody;
	private final ModelRenderer backLegR;
	private final ModelRenderer backLegL;
	private final ModelRenderer frontLegR;
	private final ModelRenderer frontLegL;
	private final ModelRenderer tail;

	public Modelhiddendog() {
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this);
		head.setRotationPoint(-1.0F, 13.5F, 2.0F);
		head.setTextureOffset(0, 0).addBox(-3.0F, -3.0F, -12.0F, 6.0F, 6.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(16, 14).addBox(-3.0F, -5.0F, -10.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(16, 14).addBox(1.0F, -5.0F, -10.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(0, 10).addBox(-1.5F, -0.0156F, -15.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 14.0F, 2.0F);
		setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
		body.setTextureOffset(18, 14).addBox(-4.0F, -2.0F, -3.0F, 6.0F, 9.0F, 6.0F, 0.0F, false);

		upperBody = new ModelRenderer(this);
		upperBody.setRotationPoint(-1.0F, 14.0F, 2.0F);
		setRotationAngle(upperBody, -1.5708F, 0.0F, 0.0F);
		upperBody.setTextureOffset(21, 0).addBox(-4.0F, 2.0F, -4.0F, 8.0F, 6.0F, 7.0F, 0.0F, false);

		backLegR = new ModelRenderer(this);
		backLegR.setRotationPoint(-2.5F, 16.0F, 7.0F);
		backLegR.setTextureOffset(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		backLegL = new ModelRenderer(this);
		backLegL.setRotationPoint(0.5F, 16.0F, 7.0F);
		backLegL.setTextureOffset(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		frontLegR = new ModelRenderer(this);
		frontLegR.setRotationPoint(-2.5F, 16.0F, -4.0F);
		frontLegR.setTextureOffset(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		frontLegL = new ModelRenderer(this);
		frontLegL.setRotationPoint(0.5F, 16.0F, -4.0F);
		frontLegL.setTextureOffset(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(-1.0F, 12.0F, 8.0F);
		setRotationAngle(tail, 0.9599F, 0.0F, 0.0F);
		tail.setTextureOffset(9, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		upperBody.render(matrixStack, buffer, packedLight, packedOverlay);
		backLegR.render(matrixStack, buffer, packedLight, packedOverlay);
		backLegL.render(matrixStack, buffer, packedLight, packedOverlay);
		frontLegR.render(matrixStack, buffer, packedLight, packedOverlay);
		frontLegL.render(matrixStack, buffer, packedLight, packedOverlay);
		tail.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.backLegL.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.frontLegR.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.backLegR.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.frontLegL.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
	}
}
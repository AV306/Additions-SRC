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
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.backLegL.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.frontLegR.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.backLegR.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.frontLegL.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
	}
}
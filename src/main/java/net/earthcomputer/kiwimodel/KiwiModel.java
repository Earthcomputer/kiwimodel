// Made with Blockbench 4.4.3
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports
package net.earthcomputer.kiwimodel;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class KiwiModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = ModelLayers.register("kiwimodel/kiwi");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public KiwiModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -3.0F, -3.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(16, 15).addBox(4.0F, -3.0F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 18.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(9, 12).addBox(0.0F, 3.0F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(0.0F, 0.0F, -0.5F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 21.0F, 1.5F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(2, 0).addBox(-1.0F, 0.0F, -0.5F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(15, 12).addBox(-1.0F, 3.0F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, -1.5F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.zRot = headPitch * (float) (Math.PI / 180.0);
        this.head.yRot = netHeadYaw * (float) (Math.PI / 180.0);
        this.right_leg.zRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.zRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(90));
		if (this.young) {
			float f;
			poseStack.pushPose();
			poseStack.translate(-2.0f / 16.0f, 5.0f / 16.0f, 0.0);
			this.headParts().forEach(modelPart -> modelPart.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha));
			poseStack.popPose();
			poseStack.pushPose();
			f = 1.0f / 2.0f;
			poseStack.scale(f, f, f);
			poseStack.translate(0.0, 24.0f / 16.0f, 0.0);
			this.bodyParts().forEach(modelPart -> modelPart.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha));
			poseStack.popPose();
		} else {
			this.headParts().forEach(modelPart -> modelPart.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha));
			this.bodyParts().forEach(modelPart -> modelPart.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha));
		}
		poseStack.popPose();
	}

	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of(head);
	}

	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(body, left_leg, right_leg);
	}
}
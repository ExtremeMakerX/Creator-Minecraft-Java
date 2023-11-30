package creatorminecraft.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Direction;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;


@Environment(EnvType.CLIENT)
public class ModelPartBuilder {

    private float xTexSize;
    private float yTexSize;
    private int xTexOffs;
    private int yTexOffs;
    public float x;
    public float y;
    public float z;
    public float xRot;
    public float yRot;
    public float zRot;
    public boolean mirror;
    public boolean visible;
    private final ObjectList<ModelPartBuilder.Cube> cubes;
    private final ObjectList<ModelPartBuilder> children;

    public ModelPartBuilder(ModelExtender modelExtender) {
        this.xTexSize = 64.0F;
        this.yTexSize = 32.0F;
        this.visible = true;
        this.cubes = new ObjectArrayList<>();
        this.children = new ObjectArrayList<>();
        modelExtender.accept(this);
        this.setTexSize(modelExtender.texWidth, modelExtender.texHeight);
    }

    public ModelPartBuilder(ModelExtender modelExtender, int i, int j) {
        this(modelExtender.texWidth, modelExtender.texHeight, i, j);
        modelExtender.accept(this);
    }

    public ModelPartBuilder(int i, int j, int k, int l) {
        this.xTexSize = 64.0F;
        this.yTexSize = 32.0F;
        this.visible = true;
        this.cubes = new ObjectArrayList<>();
        this.children = new ObjectArrayList<>();
        this.setTexSize(i, j);
        this.texOffs(k, l);
    }

    ModelPartBuilder() {
        this.xTexSize = 64.0F;
        this.yTexSize = 32.0F;
        this.visible = true;
        this.cubes = new ObjectArrayList<>();
        this.children = new ObjectArrayList<>();
    }

    public ModelPartBuilder createShallowCopy() {
        ModelPartBuilder modelPart = new ModelPartBuilder();
        modelPart.copyFrom(this);
        return modelPart;
    }

    public void copyFrom(ModelPartBuilder modelPart) {
        this.xRot = modelPart.xRot;
        this.yRot = modelPart.yRot;
        this.zRot = modelPart.zRot;
        this.x = modelPart.x;
        this.y = modelPart.y;
        this.z = modelPart.z;
    }

    public void addChild(ModelPartBuilder modelPart) {
        this.children.add(modelPart);
    }

    public ModelPartBuilder texOffs(int i, int j) {
        this.xTexOffs = i;
        this.yTexOffs = j;
        return this;
    }

    public ModelPartBuilder addBox(String string, float f, float g, float h, int i, int j, int k, float l, int m, int n) {
        this.texOffs(m, n);
        this.addBox(this.xTexOffs, this.yTexOffs, f, g, h, (float)i, (float)j, (float)k, l, l, l, this.mirror, false);
        return this;
    }

    public ModelPartBuilder addBox(float f, float g, float h, float i, float j, float k) {
        this.addBox(this.xTexOffs, this.yTexOffs, f, g, h, i, j, k, 0.0F, 0.0F, 0.0F, this.mirror, false);
        return this;
    }

    public ModelPartBuilder addBox(float f, float g, float h, float i, float j, float k, boolean bl) {
        this.addBox(this.xTexOffs, this.yTexOffs, f, g, h, i, j, k, 0.0F, 0.0F, 0.0F, bl, false);
        return this;
    }

    public void addBox(float f, float g, float h, float i, float j, float k, float l) {
        this.addBox(this.xTexOffs, this.yTexOffs, f, g, h, i, j, k, l, l, l, this.mirror, false);
    }

    public void addBox(float f, float g, float h, float i, float j, float k, float l, float m, float n) {
        this.addBox(this.xTexOffs, this.yTexOffs, f, g, h, i, j, k, l, m, n, this.mirror, false);
    }

    public void addBox(float f, float g, float h, float i, float j, float k, float l, boolean bl) {
        this.addBox(this.xTexOffs, this.yTexOffs, f, g, h, i, j, k, l, l, l, bl, false);
    }

    private void addBox(int i, int j, float f, float g, float h, float k, float l, float m, float n, float o, float p, boolean bl, boolean bl2) {
        this.cubes.add(new Cube(i, j, f, g, h, k, l, m, n, o, p, bl, this.xTexSize, this.yTexSize));
    }

    public void setPos(float f, float g, float h) {
        this.x = f;
        this.y = g;
        this.z = h;
    }

    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j) {
        this.render(poseStack, vertexConsumer, i, j, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
        if (this.visible) {
            if (!this.cubes.isEmpty() || !this.children.isEmpty()) {
                poseStack.pushPose();
                this.translateAndRotate(poseStack);
                this.compile(poseStack.last(), vertexConsumer, i, j, f, g, h, k);

                for (ModelPartBuilder child : this.children) {
                    child.render(poseStack, vertexConsumer, i, j, f, g, h, k);
                }

                poseStack.popPose();
            }
        }
    }

    public void translateAndRotate(PoseStack poseStack) {
        poseStack.translate(this.x / 16.0F, this.y / 16.0F, (double)(this.z / 16.0F));
        if (this.zRot != 0.0F) {
            poseStack.mulPose(Axis.ZP.rotation(this.zRot));
        }

        if (this.yRot != 0.0F) {
            poseStack.mulPose(Axis.YP.rotation(this.yRot));
        }

        if (this.xRot != 0.0F) {
            poseStack.mulPose(Axis.XP.rotation(this.xRot));
        }

    }

    public void setRotationAngle(float rotationX, float rotationY, float rotationZ) {
        xRot = rotationX;
        yRot = rotationY;
        zRot = rotationZ;
    }

    private void compile(PoseStack.Pose pose, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
        Matrix4f matrix4f = pose.pose();
        Matrix3f matrix3f = pose.normal();
        ObjectListIterator<Cube> var11 = this.cubes.iterator();

        while(var11.hasNext()) {
           Cube cube = (Cube)var11.next();
            Polygon[] var13 = cube.polygons;
            int var14 = var13.length;

            for(int var15 = 0; var15 < var14; ++var15) {
                Polygon polygon = var13[var15];
                Vector3f vector3f = polygon.normal.absolute();
                vector3f.mul(matrix3f);
                float l = vector3f.x();
                float m = vector3f.y();
                float n = vector3f.z();

                for(int o = 0; o < 4; ++o) {
                    Vertex vertex = polygon.vertices[o];
                    float p = vertex.pos.x() / 16.0F;
                    float q = vertex.pos.y() / 16.0F;
                    float r = vertex.pos.z() / 16.0F;
                    Vector4f vector4f = new Vector4f(p, q, r, 1.0F);
                    vector4f.mul(matrix4f);
                    vertexConsumer.vertex(vector4f.x(), vector4f.y(), vector4f.z(), f, g, h, k, vertex.u, vertex.v, j, i, l, m, n);
                }
            }
        }

    }

    public void setTexSize(int i, int j) {
        this.xTexSize = (float)i;
        this.yTexSize = (float)j;
    }

    @Environment(EnvType.CLIENT)
    static class Vertex {
        public final Vector3f pos;
        public final float u;
        public final float v;

        public Vertex(float f, float g, float h, float i, float j) {
            this(new Vector3f(f, g, h), i, j);
        }

        public Vertex remap(float f, float g) {
            return new Vertex(this.pos, f, g);
        }

        public Vertex(Vector3f vector3f, float f, float g) {
            this.pos = vector3f;
            this.u = f;
            this.v = g;
        }
    }

    @Environment(EnvType.CLIENT)
    static class Polygon {
        public final Vertex[] vertices;
        public final Vector3f normal;

        public Polygon(Vertex[] vertexes, float f, float g, float h, float i, float j, float k, boolean bl, Direction direction) {
            this.vertices = vertexes;
            float l = 0.0F / j;
            float m = 0.0F / k;
            vertexes[0] = vertexes[0].remap(h / j - l, g / k + m);
            vertexes[1] = vertexes[1].remap(f / j + l, g / k + m);
            vertexes[2] = vertexes[2].remap(f / j + l, i / k - m);
            vertexes[3] = vertexes[3].remap(h / j - l, i / k - m);
            if (bl) {
                int n = vertexes.length;

                for(int o = 0; o < n / 2; ++o) {
                    Vertex vertex = vertexes[o];
                    vertexes[o] = vertexes[n - 1 - o];
                    vertexes[n - 1 - o] = vertex;
                }
            }

            this.normal = direction.step();
            if (bl) {
                this.normal.mul(-1.0F, 1.0F, 1.0F);
            }

        }

        public boolean contains(float x, float y) {
            Vector3f p = new Vector3f(x, y, 0.0F);
            Vector3f n = this.normal;
            float d = n.dot(p);
            float s = 0.0F;
            for (Vertex v : this.vertices) {
                s += n.dot(v.pos);
            }
            return s * d >= 0.0F;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Cube {
        private final Polygon[] polygons;
        public final float minX;
        public final float minY;
        public final float minZ;
        public final float maxX;
        public final float maxY;
        public final float maxZ;

        public Cube(int i, int j, float f, float g, float h, float k, float l, float m, float n, float o, float p, boolean bl, float q, float r) {
            this.minX = f;
            this.minY = g;
            this.minZ = h;
            this.maxX = f + k;
            this.maxY = g + l;
            this.maxZ = h + m;
            this.polygons = new Polygon[6];
            float s = f + k;
            float t = g + l;
            float u = h + m;
            f -= n;
            g -= o;
            h -= p;
            s += n;
            t += o;
            u += p;
            if (bl) {
                float v = s;
                s = f;
                f = v;
            }

           Vertex vertex = new Vertex(f, g, h, 0.0F, 0.0F);
            Vertex vertex2 = new Vertex(s, g, h, 0.0F, 8.0F);
            Vertex vertex3 = new Vertex(s, t, h, 8.0F, 8.0F);
            Vertex vertex4 = new Vertex(f, t, h, 8.0F, 0.0F);
            Vertex vertex5 = new Vertex(f, g, u, 0.0F, 0.0F);
            Vertex vertex6 = new Vertex(s, g, u, 0.0F, 8.0F);
            Vertex vertex7 = new Vertex(s, t, u, 8.0F, 8.0F);
            Vertex vertex8 = new Vertex(f, t, u, 8.0F, 0.0F);
            float w = (float)i;
            float x = (float)i + m;
            float y = (float)i + m + k;
            float z = (float)i + m + k + k;
            float aa = (float)i + m + k + m;
            float ab = (float)i + m + k + m + k;
            float ac = (float)j;
            float ad = (float)j + m;
            float ae = (float)j + m + l;
            this.polygons[2] = new Polygon(new Vertex[]{vertex6, vertex5, vertex, vertex2}, x, ac, y, ad, q, r, bl, Direction.DOWN);
            this.polygons[3] = new Polygon(new Vertex[]{vertex3, vertex4, vertex8, vertex7}, y, ad, z, ac, q, r, bl, Direction.UP);
            this.polygons[1] = new Polygon(new Vertex[]{vertex, vertex5, vertex8, vertex4}, w, ad, x, ae, q, r, bl, Direction.WEST);
            this.polygons[4] = new Polygon(new Vertex[]{vertex2, vertex, vertex4, vertex3}, x, ad, y, ae, q, r, bl, Direction.NORTH);
            this.polygons[0] = new Polygon(new Vertex[]{vertex6, vertex2, vertex3, vertex7}, y, ad, aa, ae, q, r, bl, Direction.EAST);
            this.polygons[5] = new Polygon(new Vertex[]{vertex5, vertex6, vertex7, vertex8}, aa, ad, ab, ae, q, r, bl, Direction.SOUTH);
        }
    }

    public static class CubeMouseDetection {
        private final Cube cube;

        public CubeMouseDetection(int mouseX, int mouseY)  {
            cube = new Cube(450, 450, -100, 15.4F, -100, 200, 0, 200, 0, 0, 0, false, 64.0F, 32.0F); // Create a cube instance
            if (isMouseInsideCube(mouseX, mouseY)) {
                System.out.println("Mouse clicked inside the cube!");
            }
        }

        public boolean isMouseInsideCube(int mouseX, int mouseY) {
            float x = (float) mouseX;
            float y = (float) mouseY;

            return x >= cube.minX && x <= cube.maxX &&
                    y >= cube.minY && y <= cube.maxY;
        }
    }

}

package me.lulu.cavesizemodifier.nms.v1_8_R3;

import me.lulu.cavesizemodifier.utils.Randoms;
import net.minecraft.server.v1_8_R3.ChunkSnapshot;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.WorldGenCaves;

public class NewWorldGenCaves extends WorldGenCaves {
    public NewWorldGenCaves() {
    }

    protected void a(World var1, int var2, int var3, int var4, int var5, ChunkSnapshot var6) {
        int var7 = this.b.nextInt(this.b.nextInt(this.b.nextInt(15) + 1) + 1);

        if (!Randoms.randomCaveRate(b)) {
            var7 = 0;
        }

        for (int var8 = 0; var8 < var7; ++var8) {
            double var9 = ( double ) (var2 * 16 + this.b.nextInt(16));
            double var11 = ( double ) Randoms.getGenerateY(this.b);
            double var13 = ( double ) (var3 * 16 + this.b.nextInt(16));
            int var15 = 1;
            if (Randoms.randomLargeRate(this.b)) {
                this.a(this.b.nextLong(), var4, var5, var6, var9, var11, var13);
                var15 += this.b.nextInt(4);
            }

            for (int var16 = 0; var16 < var15; ++var16) {
                float var17 = this.b.nextFloat() * 3.1415927F * 2.0F;
                float var18 = (this.b.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float var19 = this.b.nextFloat() * 2.0F + this.b.nextFloat();
                if (this.b.nextInt(10) == 0) {
                    var19 *= this.b.nextFloat() * this.b.nextFloat() * 3.0F + 1.0F;
                }

                this.a(this.b.nextLong(), var4, var5, var6, var9, var11, var13, var19, var17, var18, 0, 0, 1.0D);
            }
        }

    }
}

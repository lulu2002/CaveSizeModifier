package me.lulu.cavesizemodifier.nms.v1_12_R1;

import me.lulu.cavesizemodifier.utils.Randoms;
import net.minecraft.server.v1_12_R1.ChunkSnapshot;
import net.minecraft.server.v1_12_R1.World;
import net.minecraft.server.v1_12_R1.WorldGenCaves;

public class NewWorldGenCaves extends WorldGenCaves {

    protected void a(World var1, int var2, int var3, int var4, int var5, ChunkSnapshot var6) {
        int var7 = this.f.nextInt(this.f.nextInt(this.f.nextInt(15) + 1) + 1);
        if (!Randoms.randomCaveRate(this.f)) {
            var7 = 0;
        }

        for (int var8 = 0; var8 < var7; ++var8) {
            double var9 = ( double ) (var2 * 16 + this.f.nextInt(16));
            double var11 = ( double ) Randoms.getGenerateY(this.f);
            double var13 = ( double ) (var3 * 16 + this.f.nextInt(16));
            int var15 = 1;
            if (Randoms.randomLargeRate(this.f)) {
                this.a(this.f.nextLong(), var4, var5, var6, var9, var11, var13);
                var15 += this.f.nextInt(4);
            }

            for (int var16 = 0; var16 < var15; ++var16) {
                float var17 = this.f.nextFloat() * 6.2831855F;
                float var18 = (this.f.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float var19 = this.f.nextFloat() * 2.0F + this.f.nextFloat();
                if (this.f.nextInt(10) == 0) {
                    var19 *= this.f.nextFloat() * this.f.nextFloat() * 3.0F + 1.0F;
                }

                this.a(this.f.nextLong(), var4, var5, var6, var9, var11, var13, var19, var17, var18, 0, 0, 1.0D);
            }
        }

    }
}
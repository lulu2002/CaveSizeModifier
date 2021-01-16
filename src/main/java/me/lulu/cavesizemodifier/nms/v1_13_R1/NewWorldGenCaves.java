package me.lulu.cavesizemodifier.nms.v1_13_R1;

import me.lulu.cavesizemodifier.settings.Settings;
import me.lulu.cavesizemodifier.utils.Randoms;
import net.minecraft.server.v1_13_R1.*;

import java.util.BitSet;
import java.util.Random;

public class NewWorldGenCaves extends WorldGenCaves {
    public NewWorldGenCaves() {

    }

    public boolean a(GeneratorAccess var1, Random var2, int var3, int var4, int var5, int var6, BitSet var7, WorldGenFeatureConfigurationChance var8) {
        int var9 = (this.a() * 2 - 1) * 16;
        int var10 = var2.nextInt(var2.nextInt(var2.nextInt(Settings.Cave.GENERATE_RATE + 1) + 1) + 1);

        for (int var11 = 0; var11 < var10; ++var11) {
            double var12 = ( double ) (var3 * 16 + var2.nextInt(16));
            double var14 = ( double ) b(var2);
            double var16 = ( double ) (var4 * 16 + var2.nextInt(16));
            int var18 = 1;
            float var21;
            if (Randoms.randomLargeRate(var2)) {
                var21 = 1.0F + var2.nextFloat() * 6.0F;
                this.a(var1, var2.nextLong(), var5, var6, var12, var14, var16, var21, 0.5D, var7);
                var18 += var2.nextInt(4);
            }

            for (int var27 = 0; var27 < var18; ++var27) {
                float var20 = var2.nextFloat() * 6.2831855F;
                var21 = (var2.nextFloat() - 0.5F) / 4.0F;
                float var24 = var2.nextFloat() * 2.0F + var2.nextFloat();
                if (var2.nextInt(10) == 0) {
                    var24 *= var2.nextFloat() * var2.nextFloat() * 3.0F + 1.0F;
                }

                int var25 = var9 - var2.nextInt(var9 / 4);
                this.a(var1, var2.nextLong(), var5, var6, var12, var14, var16, var24, var20, var21, 0, var25, 1.0D, var7);
            }
        }

        return true;
    }

    protected int b(Random var0) {
        return Randoms.getGenerateY(var0);
    }
}
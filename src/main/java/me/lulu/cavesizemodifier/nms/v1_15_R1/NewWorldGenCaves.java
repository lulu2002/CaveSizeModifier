package me.lulu.cavesizemodifier.nms.v1_15_R1;

import com.mojang.datafixers.Dynamic;
import me.lulu.cavesizemodifier.settings.Settings;
import me.lulu.cavesizemodifier.utils.Randoms;
import net.minecraft.server.v1_15_R1.*;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class NewWorldGenCaves extends WorldGenCaves {

    public NewWorldGenCaves(Function<Dynamic<?>, ? extends WorldGenFeatureConfigurationChance> var0, int var1) {
        super(var0, var1);
    }


    public boolean a(IChunkAccess var0, Function<BlockPosition, BiomeBase> var1, Random var2, int var3, int var4, int var5, int var6, int var7, BitSet var8, WorldGenFeatureConfigurationChance var9) {
        int var10 = (this.c() * 2 - 1) * 16;
        int var11 = var2.nextInt(var2.nextInt(var2.nextInt(this.a() + 1) + 1) + 1);

        for (int var12 = 0; var12 < var11; ++var12) {
            double var13 = ( double ) (var4 * 16 + var2.nextInt(16));
            double var15 = ( double ) this.b(var2);
            double var17 = ( double ) (var5 * 16 + var2.nextInt(16));
            int var19 = 1;
            float var22;
            if (Randoms.randomLargeRate(var2)) {
                var22 = 1.0F + var2.nextFloat() * 6.0F;
                this.a(var0, var1, var2.nextLong(), var3, var6, var7, var13, var15, var17, var22, 0.5D, var8);
                var19 += var2.nextInt(4);
            }

            for (int var20 = 0; var20 < var19; ++var20) {
                float var21 = var2.nextFloat() * 6.2831855F;
                var22 = (var2.nextFloat() - 0.5F) / 4.0F;
                float var23 = this.a(var2);
                int var24 = var10 - var2.nextInt(var10 / 4);
                this.a(var0, var1, var2.nextLong(), var3, var6, var7, var13, var15, var17, var23, var21, var22, 0, var24, this.b(), var8);
            }
        }

        return true;
    }

    @Override
    protected int a() {
        return Settings.Cave.GENERATE_RATE;
    }

    @Override
    protected int b(Random var0) {
        return Randoms.getGenerateY(var0);
    }
}
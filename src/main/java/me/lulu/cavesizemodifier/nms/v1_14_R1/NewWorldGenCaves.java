package me.lulu.cavesizemodifier.nms.v1_14_R1;

import com.mojang.datafixers.Dynamic;
import me.lulu.cavesizemodifier.settings.Settings;
import me.lulu.cavesizemodifier.utils.Randoms;
import net.minecraft.server.v1_14_R1.*;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class NewWorldGenCaves extends WorldGenCaves {

    public NewWorldGenCaves(Function<Dynamic<?>, ? extends WorldGenFeatureConfigurationChance> var0, int var1) {
        super(var0, var1);
    }

    public boolean a(IChunkAccess var0, Random var1, int var2, int var3, int var4, int var5, int var6, BitSet var7, WorldGenFeatureConfigurationChance var8) {
        int var9 = (this.c() * 2 - 1) * 16;
        int var10 = var1.nextInt(var1.nextInt(var1.nextInt(this.a() + 1) + 1) + 1);

        for (int var11 = 0; var11 < var10; ++var11) {
            double var12 = ( double ) (var3 * 16 + var1.nextInt(16));
            double var14 = ( double ) this.b(var1);
            double var16 = ( double ) (var4 * 16 + var1.nextInt(16));
            int var18 = 1;
            float var21;
            if (Randoms.randomLargeRate(var1)) {
                var21 = 1.0F + var1.nextFloat() * 6.0F;
                this.a(var0, var1.nextLong(), var2, var5, var6, var12, var14, var16, var21, 0.5D, var7);
                var18 += var1.nextInt(4);
            }

            for (int var19 = 0; var19 < var18; ++var19) {
                float var20 = var1.nextFloat() * 6.2831855F;
                var21 = (var1.nextFloat() - 0.5F) / 4.0F;
                float var22 = this.a(var1);
                int var23 = var9 - var1.nextInt(var9 / 4);
                this.a(var0, var1.nextLong(), var2, var5, var6, var12, var14, var16, var22, var20, var21, 0, var23, this.b(), var7);
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
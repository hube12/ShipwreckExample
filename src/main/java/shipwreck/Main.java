package shipwreck;

import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.structure.Shipwreck;
import kaptainwutax.seedcracker.util.loot.LootBuilder;
import kaptainwutax.seedcracker.util.loot.MCLootTables;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;

import static kaptainwutax.seedcracker.util.loot.MCLootTables.SHIPWRECK_TREASURE_CHEST;

public class Main {
    public static Shipwreck SHIPWRECK = new Shipwreck(MCVersion.v1_15);
    public static void main(String[] args) {
        long worldSeed=1L;
        long radius=1000;
        CPos spawnPos=new Spawn().getSpawn(worldSeed); // you dont actually need it
        radius/=32;

        ChunkRand rand = new ChunkRand();
        for (int regX = 0; regX < radius; regX++) {
            for (int regZ = 0; regZ < radius; regZ++) {
                CPos shipwreck = SHIPWRECK.getInRegion(worldSeed, regX, regZ, rand);
                OverworldBiomeSource ow = new OverworldBiomeSource(MCVersion.v1_15, worldSeed).build();
                if(!SHIPWRECK.canSpawn(shipwreck.getX(), shipwreck.getZ(), ow))continue;
                System.out.format("Shipwreck at (%d, %d)\n", shipwreck.getX() * 16, shipwreck.getZ() * 16);
                MCLootTables lootTables=new MCLootTables();
                LootContext lootContext = new LootBuilder().setRandom(rand.toRandom()).put(LootContextParameters.POSITION, new BlockPos(shipwreck.getX(), 0, shipwreck.getZ())).build(SHIPWRECK_TREASURE_CHEST);
                List<ItemStack> itemStacks = lootTable.getDrops(lootContext);

            }
        }

    }
}

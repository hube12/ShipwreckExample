package shipwreck;


import kaptainwutax.biomeutils.Biome;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.seedutils.mc.pos.CPos;

import java.util.*;

public class Spawn {
    private static final List<Biome> PLAYER_SPAWN_BIOMES = Arrays.asList(Biome.FOREST, Biome.PLAINS, Biome.TAIGA, Biome.TAIGA_HILLS, Biome.WOODED_HILLS, Biome.JUNGLE, Biome.JUNGLE_HILLS);
    private static final List<GenerateTerrain.Blocks> VALID_SPAWN=  Arrays.asList(GenerateTerrain.Blocks.GRASS, GenerateTerrain.Blocks.PODZOL);
    public BlockPos findBiomeHorizontal(int x, int y, int z, int length, List<Biome> list, Random random, OverworldBiomeSource biomeSource) {
        int step = 1;
        int X = x >> 2;
        int Z = z >> 2;
        int Lenght = length >> 2;
        int Y = y >> 2;
        BlockPos blockPos = null;
        int counter = 0;
        for (int i = Lenght; i <= Lenght; i += step) {
            for (int offsetZ = -i; offsetZ <= i; offsetZ += step) {
                for (int offsetX = -i; offsetX <= i; offsetX += step) {
                    int localPosX;
                    int localPosZ;
                    if (!list.contains(biomeSource.getBiomeForNoiseGen(localPosX = X + offsetX, Y, localPosZ = Z + offsetZ))) continue;
                    if (blockPos == null || random.nextInt(counter + 1) == 0) {
                        blockPos = new BlockPos(localPosX << 2, y, localPosZ << 2);
                    }
                    ++counter;
                }
            }
        }
        return blockPos;
    }
    public int getSeaLevel(){
        return 64;
    }

    public List<GenerateTerrain.Blocks> getSurfaceBlocks(OverworldBiomeSource biomeSource,BlockPos blockPos) {
        List<GenerateTerrain.Blocks> surfaceBlocks=new ArrayList<>();
        GenerateTerrain chunkGenerator=new GenerateTerrain(biomeSource);
            for (Biome biome : PLAYER_SPAWN_BIOMES) {
                surfaceBlocks.add(chunkGenerator.getTopBlockState(blockPos));
            }

        return surfaceBlocks;
    }

    public BlockPos getSpawnPosInChunk(int x,int z,boolean bl4){
        // implement
        return new BlockPos(x,0,z);
    }
    public CPos getSpawn(long seed){

        CPos chunkPos;

        OverworldBiomeSource biomeSource = new OverworldBiomeSource(MCVersion.v1_15,seed).build();

        Random random = new Random(seed);
        BlockPos blockPos = findBiomeHorizontal(0, this.getSeaLevel(), 0, 256, PLAYER_SPAWN_BIOMES, random,biomeSource);
        chunkPos = blockPos == null ? new CPos(0, 0) : new CPos(blockPos.getX(),blockPos.getZ());
        if (blockPos == null) {
            System.out.println("Unable to find spawn biome");
        }
        boolean bl4 = false;

        for (GenerateTerrain.Blocks block : VALID_SPAWN) {
            if (!getSurfaceBlocks(biomeSource,blockPos).contains(block)) continue;
            bl4 = true;
            break;
        }
        CPos spawnPos= new CPos(chunkPos.getX()+8,chunkPos.getZ()+8);
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = -1;
        int n5 = 32;
        for (int i = 0; i < 1024; ++i) {
            BlockPos blockPos2;
            if (n > -16 && n <= 16 && n2 > -16 && n2 <= 16 && (blockPos2 = getSpawnPosInChunk(chunkPos.getX() + n, chunkPos.getZ() + n2,bl4)) != null) {
                spawnPos=new CPos(blockPos2.getX(),blockPos2.getZ());
                break;
            }
            if (n == n2 || n < 0 && n == -n2 || n > 0 && n == 1 - n2) {
                int n6 = n3;
                n3 = -n4;
                n4 = n6;
            }
            n += n3;
            n2 += n4;
        }
        return spawnPos;
    }


}

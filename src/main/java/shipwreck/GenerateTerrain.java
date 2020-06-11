package shipwreck;

import kaptainwutax.biomeutils.source.OverworldBiomeSource;

public class GenerateTerrain {
    GenerateTerrain(OverworldBiomeSource biomeSource){

    }
    public enum Blocks {
        AIR(0),
        STONE(1),
        GRASS(2),
        DIRT(3),
        BEDROCK(7),
        MOVING_WATER(9),
        SAND(20),
        GRAVEL(13),
        PODZOL(12),
        ICE(79);

        private int value;

        Blocks(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
        public boolean isAir(){
            return this==Blocks.AIR;
        }
    }


    public Blocks getTopBlockState(BlockPos blockPos){
        //implement me
        return Blocks.GRASS;
    }

}

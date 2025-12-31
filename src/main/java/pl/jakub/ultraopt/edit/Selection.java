package pl.jakub.ultraopt.edit;

import net.minecraft.util.math.BlockPos;

public class Selection {

    private BlockPos pos1;
    private BlockPos pos2;

    public void setPos1(BlockPos pos) {
        pos1 = pos;
    }

    public void setPos2(BlockPos pos) {
        pos2 = pos;
    }

    public BlockPos getPos1() {
        return pos1;
    }

    public BlockPos getPos2() {
        return pos2;
    }
}
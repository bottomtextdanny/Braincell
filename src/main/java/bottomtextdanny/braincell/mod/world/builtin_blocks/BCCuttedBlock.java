package bottomtextdanny.braincell.mod.world.builtin_blocks;

import bottomtextdanny.braincell.mod._base.registry.block_extensions.ExtraBlockRegisterer;
import bottomtextdanny.braincell.mod._base.registry.managing.DeferrorType;
import bottomtextdanny.braincell.mod._base.registry.managing.ModDeferringManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;

import javax.annotation.Nullable;

public class BCCuttedBlock extends Block implements ExtraBlockRegisterer<Block> {
    @Nullable
	private String mask;
    private StairBlock stairs;
    private SlabBlock slab;

    public BCCuttedBlock(Properties properties, String mask) {
        super(properties);
        this.mask = mask;
    }
	
	public BCCuttedBlock(Properties properties) {
		super(properties);
		this.mask = null;
	}

    public SlabBlock slab() {
        return this.slab;
    }

    public StairBlock stairs() {
        return this.stairs;
    }

    @Override
    public void registerExtra(Block object, ModDeferringManager solving) {
        solving.getRegistryDeferror(DeferrorType.BLOCK).ifPresent(registry -> {
            if (this.mask == null) this.mask = object.getRegistryName().getPath();
            this.stairs = new StairBlock(object.defaultBlockState(), Properties.copy(object));
            this.stairs.setRegistryName(this.mask + "_stairs");
            solving.doHooksForObject(DeferrorType.BLOCK, this.stairs);
            registry.addDeferredRegistry(() -> this.stairs);
            this.slab = new SlabBlock(Properties.copy(object));
            this.slab.setRegistryName(this.mask + "_slab");
            solving.doHooksForObject(DeferrorType.BLOCK, this.slab);
            registry.addDeferredRegistry(() -> this.slab);
            this.mask = null;
        });
    }
}

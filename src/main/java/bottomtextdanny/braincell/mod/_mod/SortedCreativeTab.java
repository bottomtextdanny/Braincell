package bottomtextdanny.braincell.mod._mod;

import bottomtextdanny.braincell.Braincell;
import bottomtextdanny.braincell.mod._base.registry.managing.BCRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.function.Supplier;

public class SortedCreativeTab extends CreativeModeTab {
    private @Nullable final BCRegistry<Item> specifiedList;

    public SortedCreativeTab(String label) {
        super(label);
        this.specifiedList = null;
    }

    public SortedCreativeTab(String label, BCRegistry<Item> itemRegistry) {
        super(label);
        this.specifiedList = itemRegistry;
    }

    @Override
    public ItemStack makeIcon() {
        return null;
    }

    @Override
    public void fillItemList(@NotNull NonNullList<ItemStack> itemList) {
        if (this.specifiedList != null) {
            for(Supplier<? extends Item> item : this.specifiedList.getRegistryEntries()) {
                item.get().fillItemCategory(this, itemList);
            }
        } else {
            super.fillItemList(itemList);
        }
        itemList.sort(
                Comparator.comparing(
                        stack -> (int) Braincell.common().getItemSortData().getSortValue(stack.getItem().getRegistryName())
                )
        );
    }
}

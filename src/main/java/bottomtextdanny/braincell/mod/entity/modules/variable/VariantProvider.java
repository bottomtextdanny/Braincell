package bottomtextdanny.braincell.mod.entity.modules.variable;

import bottomtextdanny.braincell.Braincell;
import bottomtextdanny.braincell.mod.entity.modules.ModuleProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface VariantProvider extends ModuleProvider {
    Logger LOGGER = LogManager.getLogger(Braincell.ID + "_variant_module");

    VariableModule variableModule();

    default boolean operatingVariableModule() {
        return variableModule() != null;
    }

    default Form<?> chooseVariant() {
        if (operatingVariableModule()) {
            throw new UnsupportedOperationException("Variant not specified.");
        }
        return null;
    }
}

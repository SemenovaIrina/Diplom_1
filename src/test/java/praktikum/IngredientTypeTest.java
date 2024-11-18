package praktikum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class IngredientTypeTest {

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    void IngredientTypeIncludeValue(IngredientType ingredient) {
        Assertions.assertNotNull(ingredient);
    }

}

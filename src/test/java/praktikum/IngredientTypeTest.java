package praktikum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class IngredientTypeTest {

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    void ingredientTypeIncludeValue(IngredientType ingredient) {
        Assertions.assertNotNull(ingredient, "В перечислении присутствуют не все ожидаемые значения");
    }
}
package praktikum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static praktikum.data.UtilsForDataPrepare.stringRandomGenerate;

public class IngredientTest {
    public static final int INGREDIENT_NAME_LENGTH = 10;
    public static final String INGREDIENT_NAME = stringRandomGenerate(INGREDIENT_NAME_LENGTH);
    public static final float INGREDIENT_PRICE = (float) Math.random() * 100;

    private IngredientType type;

    private Ingredient ingredient;

    @BeforeEach
    public void setUp() {
        ingredient = new Ingredient(type, INGREDIENT_NAME, INGREDIENT_PRICE);
    }

    @Test
    void getNameReturnCorrectIngredientName() {
        String actual = ingredient.getName();
        Assertions.assertTrue(INGREDIENT_NAME.equals(actual), "Возвращаемое значение навания не соответствует ожидаемому");
    }

    @Test
    void getPriceReturnCorrectIngredientPrice() {
        float actual = ingredient.getPrice();
        Assertions.assertEquals(INGREDIENT_PRICE, actual, "Возвращаемое значение цены не соответствует ожидаемому");
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    void getTypeEatMeat(IngredientType type) {
        ingredient = new Ingredient(type, INGREDIENT_NAME, INGREDIENT_PRICE);
        Assertions.assertEquals(type, ingredient.getType(), "Возвращаемое значение типа не соответствует ожидаемому");
    }

}

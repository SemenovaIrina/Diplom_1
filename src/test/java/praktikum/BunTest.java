package praktikum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static praktikum.data.UtilsForDataPrepare.stringRandomGenerate;

class BunTest {

    public static final String BUN_NAME = stringRandomGenerate(10);
    public static final float BUN_PRICE = (float)Math.random()*100;

    private Bun bun;

    @BeforeEach
    public void setUp() {
        bun = new Bun(BUN_NAME,BUN_PRICE);
    }

    @Test
    void getNameReturnCorrectBunName() {
        String actual = bun.getName();
        Assertions.assertTrue(BUN_NAME.equals(actual), "Возвращаемое значение навания не соответствует ожидаемому");
    }

    @Test
    void getPriceReturnCorrectBunPrice() {
        float actual = bun.getPrice();
        Assertions.assertEquals(BUN_PRICE, actual,"Возвращаемое значение цены не соответствует ожидаемому");
    }
}
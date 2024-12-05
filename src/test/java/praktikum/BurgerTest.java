package praktikum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.when;
import static praktikum.data.UtilsForDataPrepare.stringRandomGenerate;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {
    public static final int INGREDIENT_COUNT = new Random().nextInt(10);
    public static final String BUN_NAME = "random bun";
    public static final String INGREDIENT_NAME = "random ingredient";
    public static final float INGREDIENT_PRICE = new Random().nextFloat() * 100;
    public static final float BUN_PRICE = new Random().nextFloat() * 100;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient; //мокаем 1 ингредиент

    @InjectMocks
    private Burger burger = new Burger();

    @Test
    void setBunsAddBunToBurger() {
        Random rnd = new Random();
        String bunName = stringRandomGenerate(rnd.nextInt(10));
        float bunPrice = rnd.nextFloat() * 100;
        Bun newBun = new Bun(bunName, bunPrice);
        burger.setBuns(newBun);
        //проверяем, что в бургер была добавлена именно созданная булочка
        Assertions.assertEquals(bunName, burger.bun.getName(), "Значение name у bun при добавлении в burger не соответствует ожидаемому");
        Assertions.assertEquals(bunPrice, burger.bun.getPrice(), "Значение price у bun при добавлении в burger не соответствует ожидаемому");
    }

    @Test
    void getPriceReturnCorrectBurgerPrice() {
        //создаем список из моков для ингредиентов и инициализируем им бургер
        //это необходимо, так как мы замокали сами ингредиенты, но не их список
        burger.ingredients = createIngredientList(INGREDIENT_COUNT);
        //задаем поведение мокированного bun
        when(bun.getPrice()).thenReturn(BUN_PRICE);
        //создаем мок для итератора по списку моков
        Iterator<Ingredient> iteratorMock = Mockito.mock(Iterator.class);
        //задаем результат итерирования по списку моков
        when(iteratorMock.next()).thenAnswer(
                (Answer) invocation -> ingredient
        );
        //задаем поведение метода getPrice() для элемента списка
        when(iteratorMock.next().getPrice()).thenReturn(INGREDIENT_PRICE);
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        String actualPrice = decimalFormat.format(burger.getPrice());
        String expectedPrice = decimalFormat.format(BUN_PRICE * 2 + INGREDIENT_PRICE * INGREDIENT_COUNT);
        Assertions.assertTrue(expectedPrice.equals(actualPrice), "Значение цены не соответствует ожидаемой");

    }

    public List<Ingredient> createIngredientList(int ingredientCount) {
        List<Ingredient> list = new ArrayList<>();
        for (int i = 0; i < ingredientCount; i++) {
            list.add(ingredient);       //список моков
        }
        return list;
    }

    @Test
    void getReceiptReturnCorrectBurgerReceipt() {
        //создаем список из моков для ингредиентов и инициализируем им бургер
        //это необходимо, так как мы замокали сами ингредиенты, но не их список
        burger.ingredients = createIngredientList(INGREDIENT_COUNT);
        //задаем поведение мокированного bun
        when(bun.getName()).thenReturn(BUN_NAME);
        when(bun.getPrice()).thenReturn(BUN_PRICE);
        //создаем мок для итератора по списку моков
        Iterator<Ingredient> iteratorMock = Mockito.mock(Iterator.class);
        //задаем результат итерирования по списку моков
        when(iteratorMock.next()).thenAnswer(
                (Answer) invocation -> ingredient
        );
        //задаем поведение метода getName() для элемента списка
        when(iteratorMock.next().getName()).thenReturn(INGREDIENT_NAME);
        //задаем поведение метода getPrice() для элемента списка
        when(iteratorMock.next().getPrice()).thenReturn(INGREDIENT_PRICE);
        //задаем поведение метода getType() для элемента списка
        when(iteratorMock.next().getType()).thenAnswer(
                new Answer() {
                    private int count = 0;

                    public IngredientType answer(InvocationOnMock invocation) {
                        count++;
                        return IngredientType.values()[count % 2];
                    }
                });
        String actualReceipt = burger.getReceipt();
        String expectedPrice = createReceipt();
        Assertions.assertTrue(expectedPrice.equals(actualReceipt), "Содержание рецепта не соответствует ожидаемому");

    }

    public String createReceipt() {
        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", BUN_NAME));
        int count = 1;
        for (int i = 0; i < INGREDIENT_COUNT; i++) {
            receipt.append(String.format("= %s %s =%n", IngredientType.values()[count % 2].toString().toLowerCase(),
                    INGREDIENT_NAME));
            count++;
        }
        receipt.append(String.format("(==== %s ====)%n", BUN_NAME));
        receipt.append(String.format("%nPrice: %f%n", BUN_PRICE * 2 + INGREDIENT_PRICE * INGREDIENT_COUNT));
        return receipt.toString();
    }
}

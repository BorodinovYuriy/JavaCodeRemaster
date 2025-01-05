import org.example.pages.demoblaze.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class DemoblazeTest extends BaseUiTest {

    @Test
    public void smokeCheckPO() {
        BasePage basePage = new BasePage(webDriver);
        BeforeLogin beforeLogin = new BeforeLogin(webDriver);
        AfterLogin afterLogin = new AfterLogin(webDriver);
        Cart cart = new Cart(webDriver);
        Order order = new Order(webDriver);

        basePage.openPage();

        Assertions.assertTrue(beforeLogin.singUp(),
                "Пользователь с таким логином уже зарегистрирован в системе");
        Assertions.assertTrue(
                beforeLogin.logIn(), "nameofuser (Welcome 'Username') - не отобразилось, ошибка login()");

        afterLogin.getCategoryListXpath().forEach(categoryXpath -> {
            Map<WebElement, WebElement> shoppingElement = afterLogin.randomProduct(categoryXpath);
            shoppingElement.forEach((key1, value1) -> {

        Assertions.assertTrue(afterLogin.byAndCheck(shoppingElement),
                "Цена на карточке товара в общем списке не соответствует цене внутри карточки");
            });
        });
        Assertions.assertTrue(cart.loadCartPage().checkTotalSum(),
                "Общая цена продуктов и цена в Total не соответствуют");
        cart.makeOrderClick();

        Assertions.assertTrue(order.placeOrder().purchaseAndCheckDate(),
                "Local date не совпадает с date в заказе");

    }
}









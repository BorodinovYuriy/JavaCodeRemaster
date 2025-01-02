import org.example.pages.demoblaze.*;
import org.junit.jupiter.api.Test;

public class DemoblazeTest extends BaseUiTest{
    @Test
    public void smokeCheckPO(){
        BasePage basePage = new BasePage(webDriver);
        BeforeLogin beforeLogin = new BeforeLogin(webDriver);
        AfterLogin afterLogin = new AfterLogin(webDriver);
        Product product = new Product(webDriver);
        Cart cart = new Cart(webDriver);
        Order order = new Order(webDriver);

//        1. Зарегистрироваться
        beforeLogin.registerNewUser();
//        2.Залогиниться
        beforeLogin.login();
//        3.Добавить в корзину по одному любому гаджету из каждой категории
//        4.Сравнить цену из общего списка и с карточки товара
        afterLogin.addSomeToCardAndCheckPrice();
//        5.Перейти в корзину и убедиться, что общая цена посчитана верно
        cart.checkSum();
//        6.Оформить заказ
        cart.makeOrder();
//        7.Убедиться что дата в конце заказа совпадает с датой в системе
        order.checkDate();

    }

}








//@Test
//    public void registrationCheckPO() {
//
//        BaseDemoblaze baseDemoblaze = new BaseDemoblaze(chromeDriver);
//        DemoblazeBeforeLogIn beforeLogIn = new DemoblazeBeforeLogIn(chromeDriver);
//        DemoblazeAfterLogIn afterLogIn = new DemoblazeAfterLogIn(chromeDriver);
//        DemoblazeProduct product = new DemoblazeProduct(chromeDriver);
//        DemoblazeCart cart = new DemoblazeCart(chromeDriver);

//        beforeLogIn.performRegistrationAndLogin();
//        afterLogIn.getCategoryListXpath().forEach(categoryXpath ->
//                afterLogIn.addProductAndValidatePrice(product, categoryXpath)
//                        .goHomeAndWait(beforeLogIn)
//        );
//        cart.performOrder();
//    }
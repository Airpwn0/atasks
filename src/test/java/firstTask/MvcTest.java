package firstTask;

import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;

/**
 * Some experiments with CSS selectors;
 */
public class MvcTest {

    @Test
    public void ToggleTest() {
        open("http://todomvc.com/examples/emberjs/");
        $("#new-todo").shouldBe(visible).setValue("a").pressEnter();
        $("#new-todo").setValue("b").pressEnter();
        $("#new-todo").setValue("c").pressEnter();
        $$("#todo-list>li").shouldHave(exactTexts("a","b","c"));

        $("#todo-list>li:nth-child(2)>div>.toggle").click();
        $("#todo-list>li:nth-child(2)").shouldHave(cssClass("completed"));
        $("#todo-list>li:nth-child(1):not(.completed)");
        $("#todo-list>li:nth-child(3):not(.completed)");
    }
}

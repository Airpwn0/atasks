package firstTask.Xpath;

import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;

/**
 * Some experiments with Xpath selectors;
 */
public class TodoMvcTest {

    @Test
    public void completeTasks() {
        
        open("http://todomvc.com/examples/emberjs/");
        $x("//*[@id='new-todo']").setValue("a").pressEnter();
        $x("//*[@id='new-todo']").setValue("b").pressEnter();
        $x("//*[@id='new-todo']").setValue("c").pressEnter();
        $$x("//*[@id='todo-list']//li").shouldHave(exactTexts("a", "b", "c"));

        //$x("//*[@id='todo-list']//li[.//*text()='b']//*[@class='toggle']").click();
        $(byXpath("//*[@id='todo-list']//li[.//text()='b']//*[@class='toggle']")).click();
        $x("//*[@id='todo-list']//li[contains(@class, 'completed')]").shouldHave(exactText("b"));
        $$x("//*[@id='todo-list']//li[contains (@class, 'active')]").shouldHave(exactTexts("a", "c"));
    }
}

package firstTask;

import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;

/**
 * Some experiments with CSS selectors;
 */
public class TodoMvcTest {

    @Test
    public void completeTasks() {
        open("http://todomvc.com/examples/emberjs/");
        $("#new-todo").setValue("a").pressEnter();
        $("#new-todo").setValue("b").pressEnter();
        $("#new-todo").setValue("c").pressEnter();
        $$("#todo-list>li").shouldHave(exactTexts("a","b","c"));

        $("#todo-list>li:nth-child(2) .toggle").click();
        $("#todo-list>li.completed").shouldHave(exactText("b"));
        $$("#todo-list>li:not(.completed)").shouldHave(exactTexts("a", "c"));
    }
}
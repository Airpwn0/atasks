package firstTask.Css;

import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;

/**
 * Some experiments with CSS selectors;
 */
public class TodoMvcTest {

    @Test
    public void completeTasks() {
        open("http://todomvc.com/examples/emberjs/");
        SelenideElement newTodo = $("#new-todo");
        newTodo.setValue("a").pressEnter();
        newTodo.setValue("b").pressEnter();
        newTodo.setValue("c").pressEnter();
        $$("#todo-list>li").shouldHave(exactTexts("a","b","c"));

        $("#todo-list>li:nth-child(2) .toggle").click();
        $("#todo-list>li.completed").shouldHave(exactText("b"));
        $$("#todo-list>li:not(.completed)").shouldHave(exactTexts("a", "c"));
    }
}
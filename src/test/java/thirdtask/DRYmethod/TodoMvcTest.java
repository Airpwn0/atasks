package thirdtask.DRYmethod;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

/**
 * Third task with DRY method.
 */

public class TodoMvcTest {

    @Test
    public void tasksMainFlow() {
        open("https://todomvc4tasj.herokuapp.com/");

        //Create
        add("a", "b", "c");
        assertTodoList("a", "b", "c");

        //Edit
        activateInput("a");
        todoList.findBy(cssClass("editing")).find(".edit")
                .setValue("a edited").pressEnter();

        //Complete and Clear
        todoList.findBy(exactText("a edited")).find(".toggle").click();
        clearButton.click();
        assertTodoList("b", "c");

        //Cancel edit
        activateInput("b");
        todoList.findBy(cssClass("editing")).find(".edit")
                .setValue("will be canceled").pressEscape();

        //Delete by button
        todoList.findBy(exactText("b")).hover().find(".destroy").click();
        assertTodoList("c");

    }
    ElementsCollection todoList = $$("#todo-list>li");
    SelenideElement clearButton = $("#clear-completed");

    private void add(String... s) {
        for (String text: s) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    private void assertTodoList(String... s) {
        for (String text: s) {
            todoList.shouldHave(exactTexts(text));
        }
    }
    private void activateInput(String text) {
        todoList.findBy(exactText(text)).doubleClick();
    }
}

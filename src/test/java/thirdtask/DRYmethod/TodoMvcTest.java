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
        inputArea.setValue("a edited").pressEnter();

        //Complete and Clear
        searchTask("a edited").find(".toggle").click();
        $("#clear-completed").click();
        assertTodoList("b", "c");

        //Cancel edit
        activateInput("b");
        inputArea.setValue("will be canceled").pressEscape();

        //Delete by button
        searchTask("b").hover().find(".destroy").click();
        assertTodoList("c");

    }
    private ElementsCollection todoList = $$("#todo-list>li");

    private SelenideElement inputArea = todoList.findBy(cssClass("editing")).find(".edit");

    private void add(String... taskText) {
        for (String text: taskText) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    private SelenideElement searchTask(String taskText) {
        return todoList.findBy(exactText(taskText));
    }

    private void assertTodoList(String... taskText) {
        todoList.shouldHave(exactTexts(taskText));
        }

    private void activateInput(String taskText) {
        todoList.findBy(exactText(taskText)).doubleClick();
    }
}

package thirdtask.KISSmethod;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

/**
 * Third task with KISS method.
 */

public class TodoMvcTest {

    @Test
    public void tasksMainFlow() {

        openMainPage();

        add("a", "b", "c");
        assertTasks("a", "b", "c");

        edit("b", "b edited");

        toggle("b edited");
        clearCompleted();
        assertTasks("a", "c");

        cancelEdit("a", "text will be canceled");

        delete("a");
        assertTasks("c");
    }

    private ElementsCollection tasks = $$("#todo-tasks>li");

    private void openMainPage() {
        open("https://todomvc4tasj.herokuapp.com/");
    }

    private void add(String... taskText) {
        for (String text : taskText) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    private void assertTasks(String... tasksText) {
        tasks.shouldHave(exactTexts(tasksText));
    }

    private SelenideElement startEdit(String taskText, String arg) {
        tasks.findBy(exactText(taskText)).doubleClick();
        return tasks.findBy(cssClass("editing")).find(".edit").setValue(arg);
    }

    private void edit(String taskText, String newTaskText) {
        startEdit(taskText, newTaskText).pressEnter();
    }

    private void cancelEdit(String taskText, String oldTaskText) {
        startEdit(taskText, oldTaskText).pressEscape();
    }

    private void toggle(String taskText) {
        tasks.findBy(exactText(taskText)).find(".toggle").click();
    }

    private void clearCompleted() {
        $("#clear-completed").click();
    }

    private void delete(String taskText) {
        tasks.findBy(exactText(taskText)).hover().find(".destroy").click();
    }
}


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
        assertTasks("a", "b", "c");

        //Edit
        startEdit("a", "a edited").pressEnter();

        //Complete and Clear
        task("a edited").find(".toggle").click();
        $("#clear-completed").click();
        assertTasks("b", "c");

        //Cancel edit
        startEdit("b", "will be canceled").pressEscape();

        //Delete by button
        task("b").hover().find(".destroy").click();
        assertTasks("c");

    }
    private ElementsCollection list = $$("#todo-list>li");

    private void add(String... taskText) {
        for (String text: taskText) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    private SelenideElement task(String taskText) {
        return list.findBy(exactText(taskText));
    }

    private void assertTasks(String... tasksText) {
        list.shouldHave(exactTexts(tasksText));
        }

    private SelenideElement startEdit(String taskText, String arg) {
        list.findBy(exactText(taskText)).doubleClick();
        return list.findBy(cssClass("editing")).find(".edit").setValue(arg);
    }

}

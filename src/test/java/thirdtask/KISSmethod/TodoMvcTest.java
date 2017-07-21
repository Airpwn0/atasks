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

            completeTask("b edited");
            clearCompleted();
            assertTasks("a","c");

            cancelEdit("a", "text will be canceled");

            delete("a");
            assertTasks("c");
        }

        private ElementsCollection list = $$("#todo-list>li");

        private void openMainPage() {
            open ("https://todomvc4tasj.herokuapp.com/");
        }

        private void add(String... taskText) {
            for (String text: taskText) {
                $("#new-todo").setValue(text).pressEnter();
            }
        }

        private void assertTasks(String... tasksText) {
            list.shouldHave(exactTexts(tasksText));
        }

        private SelenideElement startEdit(String taskText, String arg) {
            list.findBy(exactText(taskText)).doubleClick();
            return list.findBy(cssClass("editing")).find(".edit").setValue(arg);
        }

        private void edit(String taskText, String arg) {
            startEdit(taskText, arg).pressEnter();
        }

        private void cancelEdit(String taskText, String arg) {
            startEdit(taskText, arg).pressEscape();
        }

        private void completeTask(String taskText) {
            list.findBy(exactText(taskText)).find(".toggle").click();
        }

        private void clearCompleted() {
            $("#clear-completed").click();
        }

        private void delete(String taskText) {
            list.findBy(exactText(taskText)).hover().find(".destroy").click();
        }
    }


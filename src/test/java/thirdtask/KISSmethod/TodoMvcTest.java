package thirdtask.KISSmethod;

import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

    /**
     * Third task with KISS method.
     */

    public class TodoMvcTest {

        @Test
        public void tasksMainFlow() {

            openMainPage();

            add("a", "b", "c");
            assertList("a", "b", "c");

            update("b", "b edited");

            complete("b edited");
            clear();
            assertList("a","c");

            cancelEdit("a", "text will be canceled");

            delete("a");
            assertList("c");
        }

        private void openMainPage() {
            open ("https://todomvc4tasj.herokuapp.com/");
        }
        private void add(String... taskText) {
            for (String text: taskText) {
                $("#new-todo").setValue(text).pressEnter();
            }
        }
        private void assertList(String... taskText) {
            $$("#todo-list>li").shouldHave(exactTexts(taskText));
        }
        private void update(String taskText, String arg) {
            $$("#todo-list>li").findBy(exactText(taskText)).doubleClick();
            $$("#todo-list>li").findBy(cssClass("editing")).find(".edit")
                                            .setValue(arg).pressEnter();
        }
        private void complete(String taskText) {
            $$("#todo-list>li").findBy(exactText(taskText)).find(".toggle").click();
        }
        private void clear() {
            $("#clear-completed").click();
        }
        private void cancelEdit(String taskText, String arg) {
            $$("#todo-list>li").findBy(exactText(taskText)).doubleClick();
            $$("#todo-list>li").findBy(cssClass("editing")).find(".edit")
                    .setValue(arg).pressEscape();
        }
        private void delete(String taskText) {
            $$("#todo-list>li").findBy(exactText(taskText)).hover().find(".destroy").click();
        }

    }


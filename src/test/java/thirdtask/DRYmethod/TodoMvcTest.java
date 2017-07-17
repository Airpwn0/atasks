package thirdtask.DRYmethod;

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
        $$("#todo-list>li").shouldHave(exactTexts("a", "b", "c"));

        //Update
        $$("#todo-list>li").findBy(exactText("a")).doubleClick();
        $$("#todo-list>li").findBy(cssClass("editing")).find(".edit")
                .setValue("a edited").pressEnter();

        //Complete and Clear
        $$("#todo-list>li").findBy(exactText("a edited")).find(".toggle").click();
        $("#clear-completed").click();
        $$("#todo-list>li").shouldHave(exactTexts("b", "c"));

        //Cancel edit
        $$("#todo-list>li").findBy(exactText("b")).doubleClick();
        $$("#todo-list>li").findBy(cssClass("editing")).find(".edit")
                .setValue("will be canceled").pressEscape();

        //Delete by button
        $$("#todo-list>li").findBy(exactText("b")).hover().find(".destroy").click();
        $$("#todo-list>li").shouldHave(exactTexts("c"));
    }
    private void add(String... s) {
        for (String text: s) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

}
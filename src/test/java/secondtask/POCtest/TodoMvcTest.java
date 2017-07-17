package secondtask.POCtest;

import com.codeborne.selenide.Configuration;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;

/**
 * Second task with CRUD tests.
 */

public class TodoMvcTest {

    @Test
    public void tasksMainFlow() {
        open("https://todomvc4tasj.herokuapp.com/");

        //Create
        $("#new-todo").setValue("a").pressEnter();
        $("#new-todo").setValue("b").pressEnter();
        $("#new-todo").setValue("c").pressEnter();
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

}

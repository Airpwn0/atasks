package secondtask.POCtest;

import com.codeborne.selenide.Configuration;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;

/**
 * Second task with CRUD tests.
 */
public class MvcTest2 {



    @Test
    public void e2e() {

        open("https://todomvc4tasj.herokuapp.com/");

        // create
        $("#new-todo").setValue("a").pressEnter();
        $("#new-todo").setValue("b").pressEnter();
        $("#new-todo").setValue("c").pressEnter();
        $("#new-todo").setValue("d").pressEnter();
        $$("#todo-list>li").shouldHave(exactTexts("a", "b", "c", "d"));

        //complete
        $$("#todo-list>li").findBy(exactText("b")).find(".toggle").click();
        $$("#todo-list>li").filterBy(cssClass("completed")).shouldHave(exactTexts("b"));
        $$("#todo-list>li").excludeWith(cssClass("completed")).shouldHave(exactTexts("a", "c", "d"));

        //complete > active
        $$("#todo-list>li").findBy(exactText("c")).find(".toggle").click();
        $$("#todo-list>li").filterBy(cssClass("completed")).shouldHave(exactTexts("b", "c"));
        $$("#todo-list>li").excludeWith(cssClass("completed")).shouldHave(exactTexts("a", "d"));
        $$("#todo-list>li.completed").findBy(exactText("c")).find(".toggle").click();
        $$("#todo-list>li").filterBy(cssClass("completed")).shouldHave(exactTexts("b"));
        $$("#todo-list>li").excludeWith(cssClass("completed")).shouldHave(exactTexts("a", "c", "d"));

        //update complete
        $$("#todo-list>li.completed").findBy(exactText("b")).doubleClick();
        $("#todo-list>li.editing").find(".edit").setValue("bb").pressEnter();
        $$("#todo-list>li").shouldHave(exactTexts("a", "bb", "c", "d"));

        //update active
        $$("#todo-list>li").findBy(exactText("a")).doubleClick();
        $("#todo-list>li.editing").find(".edit").setValue("aa").pressEnter();
        $$("#todo-list>li").shouldHave(exactTexts("aa", "bb", "c","d"));

        //delete complete
        $("#todo-list>li.completed").hover().find(".destroy").click();
        $$("#todo-list>li").shouldHave(exactTexts("aa", "c","d"));

        //delete active by text clearing
        $$("#todo-list>li").findBy(exactText("aa")).doubleClick();
        $("#todo-list>li.editing").find(".edit").setValue("").pressEnter();
        $$("#todo-list>li").shouldHave(exactTexts("c", "d"));

        //toggle(complete) all
        $("#toggle-all").click();
        $$("#todo-list>li").filterBy(cssClass("completed")).shouldHave(exactTexts("c", "d"));

        //clear all completed
        $("#clear-completed").click();
        $("#todo-list").shouldNotBe(visible);

        /* filter Active
        $$("#filters>li").findBy(exactText("Active")).click();
        $$("#todo-list>li").findBy(cssClass("completed")).shouldHave(exactText("b")).shouldBe(disappear);
        $("#todo-list>li").shouldHave(exactText("a")).shouldBe(visible);
        $("#todo-list>li").shouldHave(exactText("c")).shouldBe(visible);
        */
    }

}

package com.FirstTask.app;

import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;

/**
 * Some experiments with Xpath selectors;
 */
public class MvcTest1 {

    @Test
    public void ToggleTest() {
        open("http://todomvc.com/examples/emberjs/");
        $x("//*[@id='new-todo']").shouldBe(visible).setValue("a").pressEnter();
        $x("//*[@id='new-todo']").setValue("b").pressEnter();
        $x("//*[@id='new-todo']").setValue("c").pressEnter();
        $x("//*//label[text()='a']");
        $x("//*//label[text()[contains (.,'b')]]");
        $x("//div[@id='ember225']//section[@id='main']/ul/li//label[text()[contains(.,'c')]]");

        $x("//*//label[text()='b']/../input[@class='toggle']").click();
        $x("//*[@id='todo-list']/li[contains (@class, 'completed')]").shouldHave(exactText("b"));
        $x("//*//label[text()[contains (.,'a')]]").shouldNotHave(cssClass("completed"));
        $x("//*//label[text()[contains (.,'c')]]/../input[not(contains (@class, 'completed'))]");



    }
}

package com.FirstTask.app;

import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;

/**
 * Created by Laptop on 27.06.2017.
 */
public class MvcTest1 {

    @Test
    public void ToggleTest() {
        open("http://todomvc.com/examples/emberjs/");
        $x("//*[@id='new-todo']").shouldBe(visible).setValue("a").pressEnter();
        $x("//*[@id='new-todo']").setValue("b").pressEnter();
        $x("//*[@id='new-todo']").setValue("c").pressEnter();

        $x("//*//label[text()='v']");
        $x("//*//label[text()[contains (.,'b')]]");
        $x("//div[@id='ember225']//section[@id='main']/ul/li//label[text()[contains(.,'c')]]");

        $x("//*//label[text()='b']/../input[@class='toggle']").click();
        $x("//*[@id='todo-list']/li[contains (@class, 'completed')]").shouldHave(exactText("b"));
        $x("//*//li[@class=\"ember-view\"]//label[text()[contains (.,\"a\")]]/../input[@class=\"toggle\"]");
        $x("//*//li[@class=\"ember-view\"]//label[text()[contains (.,\"c\")]]/../input[@class=\"toggle\"]");



    }
}

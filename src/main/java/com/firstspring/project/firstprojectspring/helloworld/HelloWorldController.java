package com.firstspring.project.firstprojectspring.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

//REST API
@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    // /hello-world
// We can use this for Request Mapping, here we need to specify the method. e.g. GET
//    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")

    // Instead we can use the GetMapping directly.
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World 111";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    // Path Parameters
    // /user/{id}/todos/{id} => user/2/todos/200
    // /hello-world/path-variable/{name}
    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
//        return new HelloWorldBean("Hello World" + name); // We can also use this
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
//        return "Hello World Int";

        // 'en' - English (Good Morning)
        // 'nl' - Dutch (Goedemorgen)
        // 'fr' - French (Bonjour)

        //Fetching everything from messages_(language).properties
    }
}

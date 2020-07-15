import models.Animal;
import models.EndangeredAnimal;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        Integer port;

        if (processBuilder.environment().get("PORT") != null) {
            port = Integer.parseInt(processBuilder.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animal.ANIMAL_TYPE);
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            Animal animal = new Animal(name);
            animal.save();
            model.put("animals", Animal.all());
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animal.all());
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animals/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Animal animal = Animal.findById(Integer.parseInt(request.params(":id")));
            animal.delete();
            model.put("animals", Animal.all());
            return new ModelAndView(model,"animals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Animal animal = Animal.findById(Integer.parseInt(request.params(":id")));
            model.put("animal", animal);
            model.put("animals", Animal.all());
            return new ModelAndView(model, "edit-animal.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animals/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Animal animal = Animal.findById(Integer.parseInt(request.params(":id")));
            String name = request.queryParams("name");
            animal.update(name);
            model.put("animals", Animal.all());
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        get("endangered/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animal.ANIMAL_TYPE);
            return new ModelAndView(model,"endangered-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            EndangeredAnimal eAnimal = new EndangeredAnimal(name,age,health);
            eAnimal.save();
            model.put("endangered", EndangeredAnimal.allEnda());
            return new ModelAndView(model, "endangered.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("endangered", EndangeredAnimal.allEnda());
            return new ModelAndView(model, "endangered.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endangered/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            EndangeredAnimal animal = (EndangeredAnimal) EndangeredAnimal.findEndangered(Integer.parseInt(request.params(":id")));
            animal.delete();
            model.put("animals", EndangeredAnimal.allEnda());
            return new ModelAndView(model,"animals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endangered/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            EndangeredAnimal animal = (EndangeredAnimal) EndangeredAnimal.findEndangered(Integer.parseInt(request.params(":id")));
            model.put("animal", animal);
            model.put("endangered", EndangeredAnimal.allEnda());
            model.put("template", "templates/edit-endangered.vtl");
            return new ModelAndView(model, "edit-endangered.hbs");
        }, new HandlebarsTemplateEngine());
    }
}

import models.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Timestamp;
import java.time.Instant;
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

        get("rangers/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "ranger-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/rangers", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int badge = Integer.parseInt(request.queryParams("badge"));
            Ranger ranger = new Ranger(name,badge);
            ranger.save();
            model.put("rangers", Ranger.all());
            model.put("template", "templates/rangers.vtl");
            return new ModelAndView(model, "rangers.hbs");
        }, new HandlebarsTemplateEngine());

        get("/rangers", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rangers", Ranger.all());
            return new ModelAndView(model, "rangers.hbs");
        }, new HandlebarsTemplateEngine());

        post("/rangers/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Ranger ranger = Ranger.find(Integer.parseInt(request.params(":id")));
            ranger.delete();
            model.put("rangers", Ranger.all());
            model.put("template", "templates/rangers.vtl");
            return new ModelAndView(model,"rangers.hbs");
        }, new HandlebarsTemplateEngine());

        get("/rangers/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Ranger ranger = Ranger.find(Integer.parseInt(request.params(":id")));
            model.put("ranger", ranger);
            model.put("rangers", Ranger.all());
            return new ModelAndView(model, "edit-ranger.hbs");
        }, new HandlebarsTemplateEngine());

        post("/rangers/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Ranger ranger = Ranger.find(Integer.parseInt(request.params(":id")));
            String name = request.queryParams("name");
            int badge = Integer.parseInt(request.queryParams("badge"));
            ranger.update(name,badge);
            model.put("rangers", Ranger.all());
            model.put("template", "templates/rangers.vtl");
            return new ModelAndView(model, "rangers.hbs");
        }, new HandlebarsTemplateEngine());

        get("locations/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "location-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/locations", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            Location location = new Location(name);
            location.save();
            model.put("locations", Location.all());
            return new ModelAndView(model, "locations.hbs");
        }, new HandlebarsTemplateEngine());

        get("/locations", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("locations", Location.all());
            return new ModelAndView(model, "locations.hbs");
        }, new HandlebarsTemplateEngine());

        post("/locations/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Location location = Location.find(Integer.parseInt(request.params(":id")));
            location.delete();
            model.put("locations", Location.all());
            model.put("template", "templates/locations.vtl");
            return new ModelAndView(model,"locations.hbs");
        }, new HandlebarsTemplateEngine());

        get("/locations/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Location location = Location.find(Integer.parseInt(request.params(":id")));
            model.put("location", location);
            model.put("locations", Location.all());
            model.put("template", "templates/edit-location.vtl");
            return new ModelAndView(model,"edit-location.hbs");
        }, new HandlebarsTemplateEngine());

        post("/locations/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Location location = Location.find(Integer.parseInt(request.params(":id")));
            String name = request.queryParams("name");
            location.update(name);
            model.put("locations", Location.all());
            return new ModelAndView(model,"locations.hbs");
        }, new HandlebarsTemplateEngine());

        get("sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animal.all());
            model.put("locations", Location.all());
            model.put("endangereds", EndangeredAnimal.all());
            model.put("rangers", Ranger.all());
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int animalid = Integer.parseInt(request.queryParams("animalid"));
            int rangerid = Integer.parseInt(request.queryParams("rangerid"));
            int locationid = Integer.parseInt(request.queryParams("locationid"));
            String animaltype = request.queryParams("animaltype");
            Sighting sighting = new Sighting(animalid, rangerid, locationid, animaltype);
            sighting.save();
            model.put("sightings", Sighting.all());
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings", Sighting.all());
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Sighting sighting = Sighting.find(Integer.parseInt(request.params(":id")));
            sighting.delete();
            model.put("sightings", Sighting.all());
            return new ModelAndView(model,"sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Sighting sighting = Sighting.find(Integer.parseInt(request.params(":id")));
            model.put("sighting", sighting);
            model.put("sightings", Sighting.all());
            return new ModelAndView(model, "edit-sighting.hbs");
        }, new HandlebarsTemplateEngine());
    }

}

package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.ebean.SqlUpdate;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.net.http.HttpResponse;

public class UserController extends Controller {

    public Result addUser(Http.Request request) {
        String username;
        String password;
        ObjectNode result = Json.newObject();
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            username = json.findPath("username").textValue();
            password = json.findPath("password").textValue();
            if (username == null || password == null) {
                return badRequest("Missing username or password");
            } else {

                User user = new User(username, password);
                result.put("username", username);
                result.put("password", password);

                user.save();
                return ok(result);
            }
        }
    }

    public Result getUser(String userName) {
        User user = User.find.query().where().eq("username", userName).findOne();
        ObjectNode result = Json.newObject();
        if (user != null) {
            result.put("username", user.getUserName());
            result.put("password", user.getHashPassword());
        }
        return ok(result);

    }


    public Result updateUser(Http.Request request) {
        JsonNode json = request.body().asJson();
        ObjectNode result = Json.newObject();

        int uid = json.findPath("uid").asInt();
        User user = User.find.query().where().eq("uid", uid).findOne();

        // Move this line at 41
        String username = json.findPath("username").textValue();

        if (username == null) {
            return badRequest("Missing username or password");
        } else {
           if(user!=null){
            user.setUserName(username);
            user.save();
            // user.update();
               result.put("username", user.getUserName());
            result.put("password", user.getHashPassword());}
            return ok(result);

        }
    }
}


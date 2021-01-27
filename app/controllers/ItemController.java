package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Item;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemController extends Controller {

    final Logger log = LoggerFactory.getLogger(this.getClass());

    public Result addItem(Http.Request request) {
        JsonNode json = request.body().asJson();
        ObjectNode result = Json.newObject();

        int uid;
        String subject;
        String item_desc;
        Timestamp timestamp;
        String status;

        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            uid = json.findPath("uid").asInt();
            subject = json.findPath("subject").textValue();
            item_desc = json.findPath("item_desc").textValue();
            timestamp = new Timestamp(json.findPath("timestamp").asLong());
            status = json.findPath("status").textValue();
        }
        if (subject == null || item_desc == null || status == null) {
            return badRequest("Missing data");
        } else {

            Item items = new Item(uid, item_desc, subject, timestamp, status);
//            result.put("item_id", items.getItemId());
            result.put("subject", items.getSubject());
            result.put("item_desc", items.getItemDesc());
            //  result.put("timestamp", items.getTimestamp());
            items.save();
            return ok(result);
        }
    }

    public Result listItems(String username) {
        log.debug("Username is " + username);

        List<Item> list = User.find.query()
                .where()
                .eq("username", username)
                .findOne()
                .item;

        ObjectNode result = Json.newObject();
        {
            if (username == null)
                return badRequest("Missing data");
            else {
                result.put("itemsCount", list.toString());
                return ok(result);
            }
        }
    }


    public Result deleteItems(String username) {

        ObjectNode result = Json.newObject();
        {
            if (username == null)
                return badRequest("No user exists");
            else {
                result.put("username", username);
                return ok(result);
            }
        }
    }
}



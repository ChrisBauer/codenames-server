
package com.codenames;

import java.util.HashMap;
import java.lang.StringBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomDispatcher {

  private HashMap<Integer, String> roomList;
  private int next;

  RoomDispatcher () {
    next = 0;
    roomList = new HashMap<Integer, String>();
  }

  @RequestMapping("/")
  public String listRooms () {
    if (roomList.size() == 0) {
      return "{}";
    }

    StringBuilder sb = new StringBuilder("{");
    roomList.forEach((k, v) -> sb.append(k + ": '" + v + "', "));
    sb.replace(sb.length() - 2, sb.length(), "}");
    return sb.toString();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String getRoomInfo(@PathVariable("id") int id) {
    if (roomList.get(id) != null) {
      return roomList.get(id) + " exists";
    }
    return "No room with id " + id + " found";
  }

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public String createRoom() {
    roomList.put(++next, "Room " + next);
    return "Creating a new room with id: " + next;
  }

  @RequestMapping(value = "/join/{id}", method = RequestMethod.GET)
  public String joinRoom (@PathVariable("id") int id) {
    if (roomList.get(id) != null) {
      return "Entering " + roomList.get(id);
    }
    return "No room with id " + id + " found";
  }

  @RequestMapping("leave")
  public String leaveRoom () {
    return "Leaving current room";
  }
}

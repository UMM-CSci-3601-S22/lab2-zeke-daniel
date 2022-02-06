package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.todo.TodoDatabase getTodo functionality
 */
public class GetTodoByIDFromDB {

  @Test
  public void getFirstBlanche() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo todo = db.getTodo("58895985a22c04e761776d54");
    assertEquals("Blanche", todo.owner, "Incorrect owner");
  }

  @Test
  public void getSecondFry() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo todo = db.getTodo("58895985ae3b752b124e7663");
    assertEquals("Fry", todo.owner, "Incorrect owner");
  }
}

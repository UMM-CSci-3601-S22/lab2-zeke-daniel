package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.user.TodoDatabase filterTodosByBlanche and listTodos with _age_ query
 * parameters
 */
// The tests here include a ton of "magic numbers" (numeric constants).
// It wasn't clear to me that giving all of them names would actually
// help things. The fact that it wasn't obvious what to call some
// of them says a lot. Maybe what this ultimately means is that
// these tests can/should be restructured so the constants (there are
// also a lot of "magic strings" that Checkstyle doesn't actually
// flag as a problem) make more sense.
@SuppressWarnings({ "MagicNumber" })
public class FilterTodosByOwnerFromDB {

  @Test
  public void filterTodosByOwner() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] ownerBlancheTodos = db.filterTodosByOwner(allTodos, "Blanche");
    assertEquals(43, ownerBlancheTodos.length, "Incorrect number of todos with owner Blanche");

    Todo[] ownerFryTodos = db.filterTodosByOwner(allTodos, "Fry");
    assertEquals(61, ownerFryTodos.length, "Incorrect number of todos with owner Fry");
  }

  @Test
  public void listTodosWithOwnerFilter() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("owner", Arrays.asList(new String[] {"Blanche"}));
    Todo[] ownerBlancheTodos = db.listTodos(queryParams);
    assertEquals(43, ownerBlancheTodos.length, "Incorrect number of todos with owner Blanche");

    queryParams.put("owner", Arrays.asList(new String[] {"Fry"}));
    Todo[] ownerFryTodos = db.listTodos(queryParams);
    assertEquals(61, ownerFryTodos.length, "Incorrect number of todos with owner Fry");
  }
}

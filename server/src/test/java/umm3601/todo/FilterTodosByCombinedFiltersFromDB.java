package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.user.TodosDatabase listTodos with _age_ and _company_ query
 * parameters
 */
public class FilterTodosByCombinedFiltersFromDB {

  @Test
  public void listTodosWithCombinedFilters() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("owner", Arrays.asList(new String[] {"Blanche"}));
    Todo[] ownerBlancheTodos = db.listTodos(queryParams);
    assertEquals(2, ownerBlancheTodos.length, "Incorrect number of todos with owner Blanche");

    queryParams.clear();
    queryParams.put("status", Arrays.asList(new String[] {"true"}));
    Todo[] trueTodos = db.listTodos(queryParams);
    assertEquals(2, trueTodos.length, "Incorrect number of todos with status true");

    queryParams.clear();
    queryParams.put("owner", Arrays.asList(new String[] {"Blanche"}));
    queryParams.put("status", Arrays.asList(new String[] {"true"}));
    Todo[] trueBlancheTodos = db.listTodos(queryParams);
    assertEquals(1, trueBlancheTodos.length, "Incorrect number of todos with status true and owner Blanche");
  }
}

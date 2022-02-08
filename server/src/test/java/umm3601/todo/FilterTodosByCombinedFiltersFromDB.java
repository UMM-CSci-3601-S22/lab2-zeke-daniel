package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.user.TodosDatabase listTodos with owner and status query
 * parameters
 */
@SuppressWarnings({ "MagicNumber" })
public class FilterTodosByCombinedFiltersFromDB {

  @Test
  public void listTodosWithCombinedFilters() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("owner", Arrays.asList(new String[] {"Blanche"}));
    Todo[] ownerBlancheTodos = db.listTodos(queryParams);
    assertEquals(43, ownerBlancheTodos.length, "Incorrect number of todos with owner Blanche");

    queryParams.clear();
    queryParams.put("status", Arrays.asList(new String[] {"Complete"}));
    Todo[] trueTodos = db.listTodos(queryParams);
    assertEquals(143, trueTodos.length, "Incorrect number of todos with status true");

    queryParams.clear();
    queryParams.put("owner", Arrays.asList(new String[] {"Blanche"}));
    queryParams.put("status", Arrays.asList(new String[] {"Complete"}));
    Todo[] trueBlancheTodos = db.listTodos(queryParams);
    assertEquals(22, trueBlancheTodos.length, "Incorrect number of todos with status true and owner Blanche");
  }

  /**
   * Tests umm3601.user.TodosDatabase listTodos with limit query parameter.
   * @throws NumberFormatException
   */
  @Test
  public void limitTodos() throws NumberFormatException, IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("limit", Arrays.asList(new String[] {"3"}));
    Todo[] limitTodos = db.listTodos(queryParams);
    assertEquals(3, limitTodos.length, "Incorrect number of todos displayed");
  }

  /**
   * Tests listTodos with owner parameter and limit parameter.
   */
  @Test
  public void limitTodosWithOtherFilter() throws NumberFormatException, IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("owner", Arrays.asList(new String[] {"Blanche"}));
    Todo[] limitOwner = db.listTodos(queryParams);
    assertEquals(43, limitOwner.length, "Incorrect number of todos with owner");

    queryParams.put("limit", Arrays.asList(new String[] {"5"}));
    limitOwner = db.listTodos(queryParams);
    assertEquals(5, limitOwner.length, "Incorrect number of todos displayed");
  }

}

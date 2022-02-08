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
    queryParams.put("status", Arrays.asList(new String[] {"complete"}));
    Todo[] trueTodos = db.listTodos(queryParams);
    assertEquals(143, trueTodos.length, "Incorrect number of todos with status true");

    queryParams.clear();
    queryParams.put("owner", Arrays.asList(new String[] {"Blanche"}));
    queryParams.put("status", Arrays.asList(new String[] {"complete"}));
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

  /**
   * Tests listTodos with status parameter
   */
  @Test
  public void listTodosWithIncompleteStatus() throws IOException
  {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("status", Arrays.asList(new String[] {"incomplete"}));
    Todo[] falseTodos = db.listTodos(queryParams);
    assertEquals(157, falseTodos.length, "Incorrect number of todos with false status");
  }

  /**
   * Tests contains functionality.
   */
  @Test
  public void listTodosWithGivenString() throws IOException
  {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("contains", Arrays.asList(new String[] {"Cillum"}));
    Todo[] cillumTodos = db.listTodos(queryParams);
    assertEquals(8, cillumTodos.length, "Incorrect number of todos whose" +
    " body contains 'Cillum'");
  }

  /**
   * Tests sorting functionality
   */
  /*
  @Test
  public void sortAlphabeticallyByOwner() throws IOException
  {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();
    Todo[] tempDB = db.listTodos(queryParams).clone();
    Todo[] sortedDB = tempDB.sort();


    queryParams.put("orderBy", Arrays.asList(new String[] {"owner"}));
    Todo[] ownerOrdered = db.listTodos(queryParams);
    assertEquals(sortedDB, ownerOrdered, "Array is not sorted correctly");

  }
*/

}

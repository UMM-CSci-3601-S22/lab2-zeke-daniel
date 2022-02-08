package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

@SuppressWarnings({ "MagicNumber" })
public class FilterTodosByContainsFromDB {

  @Test
  public void filterTodosByContains() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] containsIpsumTodos = db.filterTodosByContains(allTodos, "Ipsum");
    assertEquals(71, containsIpsumTodos.length, "Incorrect number of todos with text 'Ipsum' in the body");

    Todo[] containsAnimTodos = db.filterTodosByContains(allTodos, "Anim");
    assertEquals(94, containsAnimTodos.length, "Incorrect number of todos with text 'Anim' in the body");
  }

  @Test
  public void listTodosWithContainsFilter() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("contains", Arrays.asList(new String[] {"Ipsum"}));
    Todo[] containsIpsumTodos = db.listTodos(queryParams);
    assertEquals(71, containsIpsumTodos.length, "Incorrect number of todos with text 'Ipsum' in the body");

    queryParams.clear();
    queryParams.put("contains", Arrays.asList(new String[] {"Anim"}));
    Todo[] containsAnimTodos = db.listTodos(queryParams);
    assertEquals(94, containsAnimTodos.length, "Incorrect number of todos with text 'Anim' in the body");
  }



}

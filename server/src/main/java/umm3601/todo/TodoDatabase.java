package umm3601.todo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.BadRequestResponse;

/**
 * A fake "database" of todo info
 * <p>
 * Since we don't want to complicate this lab with a real database, we're going
 * to instead just read a bunch of todo data from a specified JSON file, and
 * then provide various database-like methods that allow the `todoController` to
 * "query" the "database".
 */
public class TodoDatabase {

  private Todo[] allTodos;

  public TodoDatabase(String todoDataFile) throws IOException {
    InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(todoDataFile));
    ObjectMapper objectMapper = new ObjectMapper();
    allTodos = objectMapper.readValue(reader, Todo[].class);
  }

  public int size() {
    return allTodos.length;
  }

  /**
   * Get the single todo specified by the given ID. Return `null` if there is no
   * todo with that ID.
   *
   * @param id the ID of the desired todo
   * @return the todo with the given ID, or null if there is no todo with that ID
   */
  public Todo getTodo(String id) {
    return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  /**
   * Get an array of all the todos satisfying the queries in the params.
   *
   * @param queryParams map of key-value pairs for the query
   * @return an array of all the todos matching the given criteria
   */
  public Todo[] listTodos(Map<String, List<String>> queryParams) {
    Todo[] filteredTodos = allTodos;

    // Filter age if defined
    if (queryParams.containsKey("owner")) {
      String ownerParam = queryParams.get("owner").get(0);
      try {
        String targetOwner = ownerParam;
        filteredTodos = filterTodosByOwner(filteredTodos, targetOwner);
      } catch (NumberFormatException e) {
        throw new BadRequestResponse("Specified owner '" + ownerParam + "' can't be parsed to an integer");
      }
    }
    // Filter status if defined
    if (queryParams.containsKey("status")) {
      String targetStatus = queryParams.get("status").get(0);
      filteredTodos = filterTodosByStatus(filteredTodos, targetStatus);
    }
    // Process other query parameters here...
    if(queryParams.containsKey("category")){
      String targetCategory = queryParams.get("category").get(0);
      filteredTodos = filterTodosByCategory(filteredTodos, targetCategory);
    }

    return filteredTodos;
  }

  /**
   * Get an array of all the todos having the target owner.
   *
   * @param todos     the list of todos to filter by owner
   * @param targetOwner the target owner to look for
   * @return an array of all the todos from the given list that have the target
   *         owner
   */
  public Todo[] filterTodosByOwner(Todo[] todos, String targetOwner) {
    return Arrays.stream(todos).filter(x -> x.owner == targetOwner).toArray(Todo[]::new);
  }

  /**
   * Get an array of all the todos having the target status.
   *
   * @param todos         the list of todos to filter by status
   * @param targetStatus the target status to look for
   * @return an array of all the todos from the given list that have the target
   *         status
   */
  public Todo[] filterTodosByStatus(Todo[] todos, String targetStatus) {
    return Arrays.stream(todos).filter(x -> Boolean.toString(x.status) == targetStatus).toArray(Todo[]::new);  //  This might be a pain-point, doing boolean -> string conversion.
  }

  /**
   * Get an array of all the todos having the target category.
   *
   * @param todos         the list of todos to filter by category
   * @param targetCategory the target category to look for
   * @return an array of all the todos from the given list that have the target
   *         category
   */
  public Todo[] filterTodosByCategory(Todo[] todos, String targetCategory) {
    return Arrays.stream(todos).filter(x -> x.category == targetCategory).toArray(Todo[]::new);
  }
}

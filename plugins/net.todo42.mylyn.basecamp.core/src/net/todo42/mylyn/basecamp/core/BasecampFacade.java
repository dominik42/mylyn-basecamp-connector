package net.todo42.mylyn.basecamp.core;

import java.util.List;

import org.eclipse.mylyn.tasks.core.TaskRepository;

import api.basecamp.BCAuth;
import api.basecamp.Project;
import api.basecamp.Projects;
import api.basecamp.ToDoItem;
import api.basecamp.ToDoItems;
import api.basecamp.ToDoList;
import api.basecamp.ToDoLists;

/**
 * @author Dominik Hirt
 */
public class BasecampFacade
{
    private static BasecampFacade instance = new BasecampFacade();

    private BasecampFacade()
    {

    }

    public static BasecampFacade getInstance()
    {
        return instance;
    }

    public List<Project> getProjects(TaskRepository repository)
    {
        BCAuth auth = createAuth(repository);
        List<Project> projects = new Projects(auth).getProjects();
        return projects;
    }

    private BCAuth createAuth(TaskRepository repository)
    {
        BCAuth auth = new BCAuth();
        auth.username = repository.getUserName();
        auth.password = repository.getPassword();
        auth.company = "efinia";
        auth.useSsl = true;
        return auth;
    }

    public List<ToDoList> getToDoLists(TaskRepository repository, Project project)
    {
        BCAuth auth = createAuth(repository);
        List<ToDoList> todoLists = new ToDoLists(auth, project).getToDoLists();
        return todoLists;
    }

    public List<ToDoItem> getItemForTodoListId(TaskRepository repository, String todoListId)
    {
        BCAuth auth = createAuth(repository);
        List<ToDoItem> todoItems = new ToDoItems(auth, todoListId).getToDoItems();
        return todoItems;
    }

    public ToDoItem getItemForId(TaskRepository repository, String taskId)
    {
        BCAuth auth = createAuth(repository);
        ToDoItem todoItem = new ToDoItem(auth, taskId);
        return todoItem;
    }

}

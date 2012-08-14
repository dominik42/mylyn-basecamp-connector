package net.todo42.mylyn.basecamp.core;

import java.util.List;

import api.basecamp.BCAuth;
import api.basecamp.People;
import api.basecamp.Person;
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

    public List<Person> getPersons(BCAuth auth)
    {
        List<Person> persons = new People(auth).getPeople();
        return persons;
    }

    public List<Project> getProjects(BCAuth auth)
    {
        List<Project> projects = new Projects(auth).getProjects();
        return projects;
    }

    public List<ToDoList> getToDoListsForProject(BCAuth auth, Project project)
    {
        List<ToDoList> todoLists = new ToDoLists(auth, project).getToDoLists();
        return todoLists;
    }

    public List<ToDoList> getToDoListsForCurrentUser(BCAuth auth)
    {
        List<ToDoList> todoLists = new ToDoLists(auth).getToDoLists();
        return todoLists;
    }

    public List<ToDoItem> getToDoItems(BCAuth auth, QueryFilter filter)
    {
        List<ToDoItem> todoItems = new ToDoItems(auth, filter.getTodoListId(), filter.getPersonId(), filter.isLoadCompleted()).getToDoItems();
        return todoItems;
    }

    public ToDoItem getToDoItem(BCAuth auth, String taskId)
    {
        ToDoItem todoItem = new ToDoItem(auth, taskId);
        return todoItem;
    }


}

package net.todo42.mylyn.basecamp.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import api.basecamp.BCAuth;
import api.basecamp.People;
import api.basecamp.Person;
import api.basecamp.Project;
import api.basecamp.Projects;
import api.basecamp.ToDoItem;
import api.basecamp.ToDoItems;
import api.basecamp.ToDoList;
import api.basecamp.ToDoLists;

public class JavaWrapperTest
{
    private BCAuth auth = new BCAuth("dominik42", "start11up!", "efinia", true);

    @Test
    public void testGetProjetcs() throws Exception
    {
        List<Project> all = new Projects(auth).getProjects();
        assertTrue(all.size() > 0);
    }

    @Test
    public void testGetToDoLists() throws Exception
    {
        List<Project> projects = new Projects(auth).getProjects();
        assertTrue(projects.size() > 0);

        List<ToDoList> all = new ToDoLists(auth, projects.get(0)).getToDoLists();
        assertTrue(all.size() > 0);

        List<ToDoItem> allItems = new ToDoItems(auth, "" + all.get(0).getId(), "", true).getToDoItems();
        assertTrue(allItems.size() > 0);
    }

    @Test
    public void testGetPersons() throws Exception
    {
        List<Person> persons = new People(auth).getPeople();
        assertTrue(persons.size() > 0);
    }

}

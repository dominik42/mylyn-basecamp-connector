package net.todo42.mylyn.basecamp.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import net.todo42.mylyn.basecamp.core.BasecampFacade;
import net.todo42.mylyn.basecamp.core.QueryFilter;

import org.junit.Before;
import org.junit.Test;

import api.basecamp.BCAuth;
import api.basecamp.ToDoItem;
import api.basecamp.ToDoList;

public class BascampFascadeTest
{

    private BCAuth auth;

    @Before
    public void setup()
    {

        auth = createAuth();
    }

    private BCAuth createAuth()
    {
        BCAuth auth = new BCAuth();
        auth.username = "dominik42";
        auth.password = "start11up!";
        auth.company = "efinia";
        auth.useSsl = true;
        return auth;
    }

    @Test
    public void testLoadToDoListsForCurrentUser() throws Exception
    {
        List<ToDoList> result = BasecampFacade.getInstance().getToDoListsForCurrentUser(auth);
        assertEquals(3, result.size());
    }

    @Test
    public void testLoadBugsForDominik() throws Exception
    {
        QueryFilter filter = new QueryFilter();
        filter.setTodoListId("20897679");
        filter.setPersonId("6929995");
        filter.setLoadCompleted(false);

        List<ToDoItem> result = BasecampFacade.getInstance().getToDoItems(auth, filter);
        assertEquals(1, result.size());
    }


}

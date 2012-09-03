package net.todo42.mylyn.basecamp.ui;

import java.util.List;

import net.todo42.mylyn.basecamp.core.BasecampFacade;
import net.todo42.mylyn.basecamp.core.QueryFilter;
import net.todo42.mylyn.basecamp.core.Utils;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.mylyn.commons.workbench.forms.SectionComposite;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositoryQueryPage2;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import api.basecamp.BCAuth;
import api.basecamp.Person;
import api.basecamp.Project;
import api.basecamp.ToDoList;

/**
 * @author Dominik Hirt
 */
public class BasecampQueryPage extends AbstractRepositoryQueryPage2
{
    private BCAuth auth = null;

    private Combo comboProjects = null;
    private int lastProjectId = -1;

    private Combo comboToDoLists = null;
    private List<ToDoList> todoLists = null;

    private Combo comboPersons = null;
    private List<Person> persons = null;

    private Button btLoadCompleted = null;


    public BasecampQueryPage(TaskRepository taskRepository, IRepositoryQuery query)
    {
        super("basecamp", taskRepository, query);
        setTitle("Basecamp ToDo List Search");
        setMessage("Choose your todo list");

        this.auth = Utils.createBCAuth(taskRepository);
    }

    @Override
    public void createControl(Composite parent)
    {
        GridLayout layout = new GridLayout(2, false);
        parent.setLayout(layout);

        List<Project> projects = BasecampFacade.getInstance().getProjects(auth);

        createProjectsControls(parent, projects);
        createToDoListWidget(parent, projects.get(0));
        createPersonsCombo(parent);
        createLoadCompleted(parent);
    }

    private void createProjectsControls(Composite parent, final List<Project> projects)
    {
        Label label = new Label(parent, SWT.NONE);
        label.setText("Project: ");

        comboProjects = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
        GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.TOP).grab(false, false).applyTo(comboProjects);
        if (projects != null)
        {
            for (Project project : projects)
            {
                comboProjects.add(project.getName());
            }
            comboProjects.select(0);
            lastProjectId = 0;

            comboProjects.addSelectionListener(new SelectionListener()
            {
                public void widgetSelected(SelectionEvent event)
                {
                    if (selectionHasChanged(event))
                    {
                        lastProjectId = comboProjects.getSelectionIndex();
                        Project selPro = getSelectedProject(comboProjects.getSelectionIndex());
                        todoLists = BasecampFacade.getInstance().getToDoListsForProject(auth, selPro);
                        updateToDoListCombo(todoLists);
                    }
                }

                private Project getSelectedProject(int selectionIndex)
                {
                    return projects.get(selectionIndex);
                }

                public void widgetDefaultSelected(SelectionEvent event)
                {
                }
            });

        }
        setControl(comboProjects);
    }

    private boolean selectionHasChanged(SelectionEvent event)
    {
        return comboProjects.getSelectionIndex() != lastProjectId;
    }

    protected void updateToDoListCombo(List<ToDoList> todoLists)
    {
        comboToDoLists.removeAll();
        if (todoLists != null)
        {
            for (ToDoList todoList : todoLists)
            {
                comboToDoLists.add(todoList.getName());
            }
            comboToDoLists.select(0);
        }
    }

    private void createToDoListWidget(Composite parent, Project project)
    {
        Label todolistLabel = new Label(parent, SWT.NONE);
        todolistLabel.setText("ToDo List: ");

        comboToDoLists = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
        GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.TOP).grab(false, false).applyTo(comboToDoLists);

        todoLists = BasecampFacade.getInstance().getToDoListsForProject(auth, project);
        updateToDoListCombo(todoLists);
    }

    private void createPersonsCombo(Composite parent)
    {
        Label label = new Label(parent, SWT.NONE);
        label.setText("Assigned to: ");

        comboPersons = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
        GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.TOP).grab(false, false).applyTo(comboPersons);

        persons = BasecampFacade.getInstance().getPersons(auth);
        if (persons != null)
        {
            for (Person person : persons)
            {
                comboPersons.add(person.getFirstName() + " " + person.getLastName());
            }
            comboPersons.select(0);
        }
    }

    private void createLoadCompleted(Composite parent)
    {
        Label label = new Label(parent, SWT.NONE);
        label.setText("Load completed tasks: ");

        btLoadCompleted = new Button(parent, SWT.CHECK | SWT.CENTER);
        GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).grab(true, false).applyTo(btLoadCompleted);
    }


    @Override
    protected void createPageContent(SectionComposite parent)
    {
        int i = 9;
    }

    @Override
    protected void doRefreshControls()
    {
        // ignore

    }

    @Override
    protected boolean hasRepositoryConfiguration()
    {
        // ignore
        return false;
    }

    @Override
    protected boolean restoreState(IRepositoryQuery query)
    {
        // ignore
        return false;
    }

    @Override
    public void applyTo(IRepositoryQuery query)
    {
        ToDoList selected = todoLists.get(comboToDoLists.getSelectionIndex());
        Person person = persons.get(comboPersons.getSelectionIndex());

        query.setSummary(selected.getName() + " (" + person.getFirstName() + " " + person.getLastName() + ")");
        // IRepositoryQuery can't handle objects as attribute values
        query.setAttribute(QueryFilter.TODO_LIST_ID, String.valueOf(selected.getId()));
        query.setAttribute(QueryFilter.PERSON_ID, String.valueOf(person.getId()));
        query.setAttribute(QueryFilter.LOAD_COMPLETED, String.valueOf(btLoadCompleted.getSelection()));
    }


    @Override
    public boolean isPageComplete()
    {
        return true;
    }


}

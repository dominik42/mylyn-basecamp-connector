package net.todo42.mylyn.basecamp.ui;

import java.util.List;

import net.todo42.mylyn.basecamp.core.BasecampFacade;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.mylyn.commons.workbench.forms.SectionComposite;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositoryQueryPage2;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import api.basecamp.Project;
import api.basecamp.ToDoList;

/**
 * @author Dominik Hirt
 */
public class BasecampQueryPage extends AbstractRepositoryQueryPage2
{
    private TaskRepository tRepository = null;

    private Combo comboToDoLists = null;
    private List<ToDoList> todoLists = null;


    public BasecampQueryPage(TaskRepository taskRepository, IRepositoryQuery query)
    {
        super("basecamp", taskRepository, query);
        setTitle("Basecamp ToDo List Search");
        setMessage("Choose your todo list");
        this.tRepository = taskRepository;
    }

    @Override
    public void createControl(Composite parent)
    {
        GridLayout layout = new GridLayout(2, false);
        parent.setLayout(layout);

        List<Project> projects = BasecampFacade.getInstance().getProjects(tRepository);

        createProjectsWidget(parent, projects);
        createToDoListWidget(parent, projects.get(0));
    }

    private void createProjectsWidget(Composite parent, List<Project> projects)
    {
        Label label = new Label(parent, SWT.NONE);
        label.setText("Your projects: ");

        final Combo projectList = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
        GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).grab(true, false).applyTo(projectList);
        if (projects != null)
        {
            for (Project project : projects)
            {
                projectList.add(project.getName());
            }
            projectList.select(0);

            if (projects.size() > 1)
            {
                projectList.setEnabled(true);
                projectList.addSelectionListener(new SelectionListener()
                {
                    public void widgetSelected(SelectionEvent event)
                    {
                        int idx = projectList.getSelectionIndex();
                        //selectToDoListFor(idx);
                    }

                    public void widgetDefaultSelected(SelectionEvent event)
                    {
                    }
                });
            }
            else
            {
                projectList.setEnabled(false);
            }
        }
        setControl(projectList);
    }

    private void createToDoListWidget(Composite parent, Project project)
    {
        Label todolistLabel = new Label(parent, SWT.NONE);
        todolistLabel.setText("ToDo List: ");

        comboToDoLists = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
        GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).grab(true, false).applyTo(comboToDoLists);

        todoLists = BasecampFacade.getInstance().getToDoLists(tRepository, project);
        if (todoLists != null)
        {
            for (ToDoList todoList : todoLists)
            {
                comboToDoLists.add(todoList.getName());
            }
            comboToDoLists.select(0);
        }
    }


    @Override
    protected void createPageContent(SectionComposite parent)
    {
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
        query.setSummary(selected.getName());
        query.setAttribute("id", String.valueOf(selected.getId()));
    }


    @Override
    public boolean isPageComplete()
    {
        return true;
    }


}

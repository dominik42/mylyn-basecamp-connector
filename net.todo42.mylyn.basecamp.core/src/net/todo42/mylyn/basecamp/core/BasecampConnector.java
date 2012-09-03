package net.todo42.mylyn.basecamp.core;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.mylyn.tasks.core.AbstractRepositoryConnector;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskDataHandler;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.core.data.TaskDataCollector;
import org.eclipse.mylyn.tasks.core.data.TaskMapper;
import org.eclipse.mylyn.tasks.core.sync.ISynchronizationSession;

import api.basecamp.ToDoItem;

/**
 * @author Dominik Hirt
 */
public class BasecampConnector extends AbstractRepositoryConnector
{
    private final BasecampTaskDataHandler taskDataHandler = new BasecampTaskDataHandler(this);

    @Override
    public boolean canCreateNewTask(TaskRepository repository)
    {
        // ignore
        return false;
    }

    @Override
    public boolean canCreateTaskFromKey(TaskRepository repository)
    {
        // ignore
        return false;
    }

    @Override
    public String getConnectorKind()
    {
        return BasecampCorePlugin.CONNECTOR_KIND;
    }

    @Override
    public String getLabel()
    {
        return "Basecamp Connector";
    }

    @Override
    public String getRepositoryUrlFromTaskUrl(String taskFullUrl)
    {
        // ignore
        return null;
    }


    @Override
    public String getTaskIdFromTaskUrl(String taskFullUrl)
    {
        // ignore
        return null;
    }

    @Override
    public String getTaskUrl(String repositoryUrl, String taskId)
    {
        // ignore
        return null;
    }

    @Override
    public boolean hasTaskChanged(TaskRepository taskRepository, ITask task, TaskData taskData)
    {
        // ignore
        return false;
    }

    @Override
    public IStatus performQuery(TaskRepository taskRepository, IRepositoryQuery query, TaskDataCollector collector, ISynchronizationSession session, IProgressMonitor monitor)
    {
        IStatus result = Status.OK_STATUS;
        monitor.beginTask("Querying Basecamp repository", IProgressMonitor.UNKNOWN);

        // IRepositoryQuery can't handle objects as attribute values so transform all string attributes here
        QueryFilter filter = new QueryFilter();
        filter.setTodoListId(query.getAttribute(QueryFilter.TODO_LIST_ID));
        filter.setPersonId(query.getAttribute(QueryFilter.PERSON_ID));
        filter.setLoadCompleted(Boolean.valueOf(query.getAttribute(QueryFilter.LOAD_COMPLETED)));

        List<ToDoItem> issues = BasecampFacade.getInstance().getToDoItems(Utils.createBCAuth(taskRepository), filter);
        for (ToDoItem issue : issues)
        {
            if (filter.isLoadCompleted() || !issue.isCompleted())
            {
                TaskData data = taskDataHandler.createPartialTaskData(taskRepository, monitor, issue);
                data.setPartial(true);
                collector.accept(data);
            }
        }
        result = Status.OK_STATUS;

        monitor.done();
        return result;
    }

    @Override
    public void updateTaskFromTaskData(TaskRepository taskRepository, ITask task, TaskData taskData)
    {
        TaskMapper taskMapper = new TaskMapper(taskData);
        taskMapper.applyTo(task);
    }

    @Override
    public TaskData getTaskData(TaskRepository taskRepository, String taskId, IProgressMonitor monitor) throws CoreException
    {
        TaskData result = null;
        monitor.beginTask("Querying Basecamp repository for todo item " + taskId, IProgressMonitor.UNKNOWN);

        ToDoItem todoItem = BasecampFacade.getInstance().getToDoItem(Utils.createBCAuth(taskRepository), taskId);
        result = taskDataHandler.createFullTaskData(taskRepository, monitor, todoItem);

        monitor.done();
        return result;
    }

    @Override
    public void updateRepositoryConfiguration(TaskRepository taskRepository, IProgressMonitor monitor) throws CoreException
    {
        // ignore
    }

    @Override
    public AbstractTaskDataHandler getTaskDataHandler()
    {
        return taskDataHandler;
    }
}

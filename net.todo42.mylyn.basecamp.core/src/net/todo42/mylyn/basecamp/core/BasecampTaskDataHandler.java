/*******************************************************************************
 * Copyright (c) 2004, 2008 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/

package net.todo42.mylyn.basecamp.core;

import java.util.Date;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.mylyn.tasks.core.ITaskMapping;
import org.eclipse.mylyn.tasks.core.RepositoryResponse;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskDataHandler;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMapper;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMetaData;
import org.eclipse.mylyn.tasks.core.data.TaskData;

import api.basecamp.ToDoItem;

public class BasecampTaskDataHandler extends AbstractTaskDataHandler
{
    public BasecampTaskDataHandler(BasecampConnector basecampConnector)
    {
        // ignore
    }

    @Override
    public RepositoryResponse postTaskData(TaskRepository repository, TaskData taskData, Set<TaskAttribute> oldAttributes, IProgressMonitor monitor) throws CoreException
    {
        // ignore
        return null;
    }

    @Override
    public boolean initializeTaskData(TaskRepository repository, TaskData data, ITaskMapping initializationData, IProgressMonitor monitor) throws CoreException
    {
        // ignore
        return false;
    }

    @Override
    public TaskAttributeMapper getAttributeMapper(TaskRepository repository)
    {
        return new TaskAttributeMapper(repository);
    }

    /**
     * create the task data needed for the tasklist only all details for a given issue are retrieved later on opening
     * the issue with the rich editor
     *
     * @param repository
     * @param monitor
     * @param issue
     * @return
     */
    public TaskData createPartialTaskData(TaskRepository repository, IProgressMonitor monitor, ToDoItem issue)
    {
        TaskData data = new TaskData(getAttributeMapper(repository), BasecampCorePlugin.CONNECTOR_KIND, repository.getRepositoryUrl(), "" + issue.getId());
        data.setPartial(true);
        data.setVersion("1");

        createAttribute(data, TaskAttribute.TASK_KEY, "" + issue.getId());
        createAttribute(data, TaskAttribute.SUMMARY, issue.getContent());
        createAttribute(data, TaskAttribute.USER_ASSIGNED, issue.getResponsiblePartyName() != null ? issue.getResponsiblePartyName() : "");
        createAttribute(data, TaskAttribute.STATUS, issue.isCompleted());
        createAttribute(data, TaskAttribute.DATE_DUE, issue.getDueAt());
        if (issue.isCompleted())
        {
            createAttribute(data, TaskAttribute.DATE_COMPLETION, new Date());
        }
        // https://efinia.basecamphq.com/projects/6674233-efinia/todo_items/139521407/comments
        createAttribute(data, TaskAttribute.TASK_URL, repository.getUrl() + "/todo_items/" + issue.getId() + ".xml");

        return data;
    }

    public TaskData createFullTaskData(TaskRepository repository, IProgressMonitor monitor, ToDoItem todoItem)
    {
        TaskData data = new TaskData(getAttributeMapper(repository), BasecampCorePlugin.CONNECTOR_KIND, repository.getRepositoryUrl(), "" + todoItem.getId());
        data.setPartial(false);
        data.setVersion("1");

        createAttribute(data, TaskAttribute.TASK_KEY, "" + todoItem.getId());
        createAttribute(data, TaskAttribute.SUMMARY, todoItem.getContent());
        createAttribute(data, TaskAttribute.USER_ASSIGNED, todoItem.getResponsiblePartyName() != null ? todoItem.getResponsiblePartyName() : "");
        createAttribute(data, TaskAttribute.STATUS, todoItem.isCompleted());
        createAttribute(data, TaskAttribute.DATE_DUE, todoItem.getDueAt());
        if (todoItem.isCompleted())
        {
            createAttribute(data, TaskAttribute.DATE_COMPLETION, new Date());
        }

        return data;

    }

    private void createAttribute(TaskData data, String attributeName, String attributeValue)
    {
        TaskAttribute attr = data.getRoot().createAttribute(attributeName);
        TaskAttributeMetaData metaData = attr.getMetaData();
        metaData.defaults();
        metaData.setType(TaskAttribute.TYPE_LONG_TEXT);
        metaData.setKind(TaskAttribute.KIND_DEFAULT);
        metaData.setLabel(attributeName);
        metaData.setReadOnly(false);
        attr.addValue(attributeValue);
    }

    private void createAttribute(TaskData data, String attributeName, Date attributeValue)
    {
        if (attributeValue != null)
        {
            TaskAttribute attr = data.getRoot().createAttribute(attributeName);
            TaskAttributeMetaData metaData = attr.getMetaData();
            metaData.defaults();
            metaData.setType(TaskAttribute.TYPE_DATE);
            metaData.setKind(TaskAttribute.KIND_DEFAULT);
            metaData.setLabel(attributeName);
            metaData.setReadOnly(false);
            attr.setValue(String.valueOf(attributeValue.getTime()));
        }
    }

    private void createAttribute(TaskData data, String attributeName, boolean attributeValue)
    {
        TaskAttribute attr = data.getRoot().createAttribute(attributeName);
        TaskAttributeMetaData metaData = attr.getMetaData();
        metaData.defaults();
        metaData.setType(TaskAttribute.TYPE_BOOLEAN);
        metaData.setKind(TaskAttribute.KIND_DEFAULT);
        metaData.setLabel(attributeName);
        metaData.setReadOnly(false);
        attr.setValue(String.valueOf(attributeValue));
    }


}

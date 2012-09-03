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

/**
 * Container for all objects used as filter for Basecamp todo item queries.
 * 
 * @author Dominik
 */
public class QueryFilter
{
    public static final String TODO_LIST_ID = "todoListId";
    public static final String PERSON_ID = "personId";
    public static final String LOAD_COMPLETED = "loadCompleted";

    private String todoListId;
    private String personId;
    private boolean loadCompleted;

    public String getTodoListId()
    {
        return todoListId;
    }

    public void setTodoListId(String todoListId)
    {
        this.todoListId = todoListId;
    }

    public String getPersonId()
    {
        return personId;
    }

    public void setPersonId(String personId)
    {
        this.personId = personId;
    }

    public boolean isLoadCompleted()
    {
        return loadCompleted;
    }

    public void setLoadCompleted(boolean loadCompleted)
    {
        this.loadCompleted = loadCompleted;
    }
}

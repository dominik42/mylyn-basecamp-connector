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

import org.eclipse.mylyn.tasks.core.TaskRepository;

import api.basecamp.BCAuth;

public class Utils
{
    public static BCAuth createBCAuth(TaskRepository taskRepository)
    {
        BCAuth auth = new BCAuth();
        auth.username = taskRepository.getUserName();
        auth.password = taskRepository.getPassword();
        auth.company = "efinia";
        auth.useSsl = true;

        return auth;
    }

}

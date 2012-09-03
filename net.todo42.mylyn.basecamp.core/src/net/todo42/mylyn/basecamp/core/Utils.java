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

import static org.junit.Assert.assertEquals;

import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.junit.Test;

import api.basecamp.BCAuth;

public class Utils
{
    public static BCAuth createBCAuth(TaskRepository taskRepository)
    {
        BCAuth auth = new BCAuth();
        auth.username = taskRepository.getUserName();
        auth.password = taskRepository.getPassword();
        String strUrl = taskRepository.getUrl();

        auth.company = extractCompany(strUrl);
        auth.useSsl = true;

        return auth;
    }

    // https://company.basecamphq.com -> company
    private static String extractCompany(String strUrl)
    {
        int start = strUrl.indexOf("//") + 2;
        int end = strUrl.indexOf(".", start);

        return strUrl.substring(start, end);
    }

    @Test
    public void testExtractCompanny() throws Exception
    {
        assertEquals("company", extractCompany("https://company.basecamphq.com"));
        assertEquals("efinia", extractCompany("https://efinia.basecamphq.com"));
        assertEquals("canberra", extractCompany("https://canberra.basecamphq.com"));
    }

}

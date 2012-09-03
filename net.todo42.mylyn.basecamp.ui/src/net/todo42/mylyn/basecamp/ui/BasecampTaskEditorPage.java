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

package net.todo42.mylyn.basecamp.ui;

import net.todo42.mylyn.basecamp.core.BasecampCorePlugin;

import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorPage;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditor;

/**
 * @author Steffen Pingel
 */
public class BasecampTaskEditorPage extends AbstractTaskEditorPage {

	public BasecampTaskEditorPage(TaskEditor editor) {
		super(editor, "xmlTaskEditorPage", "XML", BasecampCorePlugin.CONNECTOR_KIND);
	}

}

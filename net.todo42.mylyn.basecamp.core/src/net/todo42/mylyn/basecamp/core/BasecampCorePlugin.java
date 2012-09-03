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

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class BasecampCorePlugin extends Plugin {

	public static final String ID_PLUGIN = "net.todo42.mylyn.basecamp.core"; //$NON-NLS-1$

	public static final String CONNECTOR_KIND = "net.todo42.mylyn.basecamp"; //$NON-NLS-1$

	public static final String REPOSITORY_KEY_SERVER_URL = ID_PLUGIN + ".serverUrl";

	public static final String QUERY_KEY_SUMMARY = ID_PLUGIN + ".summary";

	public static final String QUERY_KEY_PROJECT = ID_PLUGIN + ".project";

	private static BasecampCorePlugin plugin;

	public BasecampCorePlugin() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static BasecampCorePlugin getDefault() {
		return plugin;
	}

}

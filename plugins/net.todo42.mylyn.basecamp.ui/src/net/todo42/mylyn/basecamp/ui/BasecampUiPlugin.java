package net.todo42.mylyn.basecamp.ui;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * @author Dominik Hirt
 */
public class BasecampUiPlugin extends Plugin {

	public static final String ID_PLUGIN = "net.todo42.mylyn.basecamp.ui"; //$NON-NLS-1$

	private static BasecampUiPlugin plugin;

	public BasecampUiPlugin() {
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

	public static BasecampUiPlugin getDefault() {
		return plugin;
	}

}

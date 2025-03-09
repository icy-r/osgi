
package coursecontent_consumer;

import coursecontent_producer.CourseContentService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private ServiceReference<?> serviceReference;
	private CourseContentGUI gui;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		serviceReference = context.getServiceReference(CourseContentService.class.getName());

		if (serviceReference != null) {
			coursecontent_producer.CourseContentService service = (CourseContentService) context.getService(serviceReference);
			gui = new CourseContentGUI(service);
			gui.setVisible(true);
			System.out.println("CourseContent_Consumer started");
		} else {
			System.out.println("CourseContentService not available");
		}
	}

	public void stop(BundleContext bundleContext) throws Exception {
		if (gui != null) {
			gui.dispose();
		}

		if (serviceReference != null) {
			context.ungetService(serviceReference);
		}

		Activator.context = null;
		System.out.println("CourseContent_Consumer stopped");
	}
}

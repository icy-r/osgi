package librarymanagement_usermanagement;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    private static BundleContext context;
    private ServiceRegistration<?> serviceRegistration;

    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext bundleContext) throws Exception {
        Activator.context = bundleContext;
        UserManagementService service = new UserManagementServiceImpl();
        serviceRegistration = context.registerService(UserManagementService.class.getName(), service, null);
        System.out.println("LibraryManagement_UserManagement started");
    }

    public void stop(BundleContext bundleContext) throws Exception {
        serviceRegistration.unregister();
        Activator.context = null;
        System.out.println("LibraryManagement_UserManagement stopped");
    }
}

package librarymanagement_rentalmanagement;

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
        RentalManagementService service = new RentalManagementServiceImpl();
        serviceRegistration = context.registerService(RentalManagementService.class.getName(), service, null);
        System.out.println("LibraryManagement_RentalManagement started");
    }

    public void stop(BundleContext bundleContext) throws Exception {
        serviceRegistration.unregister();
        Activator.context = null;
        System.out.println("LibraryManagement_RentalManagement stopped");
    }
}

package librarymanagement_consumer;

import librarymanagement_bookcatalog.BookCatalogService;
import librarymanagement_rentalmanagement.RentalManagementService;
import librarymanagement_duefeecalculation.DueFeeCalculationService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private ServiceReference<?> bookCatalogServiceReference;
	private ServiceReference<?> rentalManagementServiceReference;
	private ServiceReference<?> dueFeeCalculationServiceReference;
	private LibraryManagementGUI gui;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		bookCatalogServiceReference = context.getServiceReference(BookCatalogService.class.getName());
		rentalManagementServiceReference = context.getServiceReference(RentalManagementService.class.getName());
		dueFeeCalculationServiceReference = context.getServiceReference(DueFeeCalculationService.class.getName());

		if (bookCatalogServiceReference != null && rentalManagementServiceReference != null && dueFeeCalculationServiceReference != null) {
			BookCatalogService bookCatalogService = (BookCatalogService) context.getService(bookCatalogServiceReference);
			RentalManagementService rentalManagementService = (RentalManagementService) context.getService(rentalManagementServiceReference);
			DueFeeCalculationService dueFeeCalculationService = (DueFeeCalculationService) context.getService(dueFeeCalculationServiceReference);

			gui = new LibraryManagementGUI(bookCatalogService, rentalManagementService, dueFeeCalculationService);
			gui.setVisible(true);
			System.out.println("LibraryManagement_Consumer started");
		} else {
			System.out.println("Required services not available");
		}
	}

	public void stop(BundleContext bundleContext) throws Exception {
		if (gui != null) {
			gui.dispose();
		}

		if (bookCatalogServiceReference != null) {
			context.ungetService(bookCatalogServiceReference);
		}

		if (rentalManagementServiceReference != null) {
			context.ungetService(rentalManagementServiceReference);
		}

		if (dueFeeCalculationServiceReference != null) {
			context.ungetService(dueFeeCalculationServiceReference);
		}

		Activator.context = null;
		System.out.println("LibraryManagement_Consumer stopped");
	}
}

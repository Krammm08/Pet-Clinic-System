package com.app.service.impl;

import com.app.dao.OfferedServiceDAO;
import com.app.dao.impl.OfferedServiceDAOImpl;
import com.app.exception.DatabaseException;
import com.app.model.OfferedService;
import com.app.service.OfferedServiceService;

import java.util.List;

public class OfferedServiceServiceImpl implements OfferedServiceService {

    private OfferedServiceDAO serviceDAO = new OfferedServiceDAOImpl();

    @Override
    public boolean addService(OfferedService service) {
        // Validation 1: Service Type cannot be blank
        if (service.getServiceType() == null || service.getServiceType().trim().isEmpty()) {
            System.out.println("Validation Error: Service type cannot be blank.");
            return false;
        }

        // Validation 2: Service Fee cannot be negative (0.0 is fine for free services)
        if (service.getServiceFee() < 0.0) {
            System.out.println("Validation Error: Service fee cannot be negative.");
            return false;
        }

        return serviceDAO.addService(service);
    }

    @Override
    public List<OfferedService> getAllServices() throws DatabaseException {
        return serviceDAO.getAllServices();
    }

    @Override
    public OfferedService getServiceById(int serviceId) {
        if (serviceId <= 0) {
            System.out.println("Validation Error: Invalid Service ID.");
            return null;
        }
        return serviceDAO.getServiceById(serviceId);
    }

    @Override
    public boolean updateService(OfferedService service) {
        if (service.getServiceId() <= 0) {
            System.out.println("Validation Error: Cannot update. Invalid Service ID.");
            return false;
        }

        if (service.getServiceType() == null || service.getServiceType().trim().isEmpty()) {
            System.out.println("Validation Error: Service type cannot be updated to blank.");
            return false;
        }

        if (service.getServiceFee() < 0.0) {
            System.out.println("Validation Error: Service fee cannot be updated to a negative number.");
            return false;
        }

        return serviceDAO.updateService(service);
    }

    @Override
    public boolean deleteService(int serviceId) {
        if (serviceId <= 0) {
            System.out.println("Validation Error: Invalid Service ID provided for deletion.");
            return false;
        }
        return serviceDAO.deleteService(serviceId);
    }
}
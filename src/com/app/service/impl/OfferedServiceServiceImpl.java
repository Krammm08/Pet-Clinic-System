package com.app.service.impl;

import com.app.dao.OfferedServiceDAO;
import com.app.dao.impl.OfferedServiceDAOImpl;
import com.app.model.OfferedService;
import com.app.service.OfferedServiceService;

import java.util.List;

public class OfferedServiceServiceImpl implements OfferedServiceService {

    // The Service owns a copy of the DAO to talk to the database
    private OfferedServiceDAO serviceDAO = new OfferedServiceDAOImpl();

    @Override
    public boolean addService(OfferedService service) {
        if (service.getServiceName() == null || service.getServiceName().trim().isEmpty()) {
            System.out.println("Validation Error: Service name cannot be blank.");
            return false;
        }

        if (service.getServiceFee() < 0) {
            System.out.println("Validation Error: Service fee cannot be negative.");
            return false;
        }

        if (service.getDescription() == null || service.getDescription().trim().isEmpty()) {
            System.out.println("Validation Warning: Description is blank, but we will allow it.");
        }

        return serviceDAO.addService(service);
    }

    @Override
    public List<OfferedService> getAllServices() {
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

        if (service.getServiceName() == null || service.getServiceName().trim().isEmpty()) {
            System.out.println("Validation Error: Service name cannot be updated to blank.");
            return false;
        }

        if (service.getServiceFee() < 0) {
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
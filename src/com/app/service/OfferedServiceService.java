package com.app.service;

import com.app.model.OfferedService;
import java.util.List;

public interface OfferedServiceService {
    boolean addService(OfferedService service);
    List<OfferedService> getAllServices();
    OfferedService getServiceById(int serviceId);
    boolean updateService(OfferedService service);
    boolean deleteService(int serviceId);
}
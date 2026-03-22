package com.app.dao;

import com.app.exception.DatabaseException;
import com.app.model.OfferedService;
import java.util.List;

public interface OfferedServiceDAO {
    boolean addService(OfferedService service);
    boolean insertOption(OfferedService option) throws DatabaseException;
    List<OfferedService> getAllOptions() throws DatabaseException;
    OfferedService getServiceById(int serviceId);
    boolean updateService(OfferedService service);
    boolean deleteService(int serviceId);
}
package com.app.dao.impl;

import com.app.dao.OfferedServiceDAO;
import com.app.model.OfferedService;
import com.app.util.DbConnection; // Using your updated connection utility!

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OfferedServiceDAOImpl implements OfferedServiceDAO {

    @Override
    public boolean addService(OfferedService service) {
        // Skipping service_id so MySQL can auto-increment it
        String sql = "INSERT INTO tblservices (service_type, service_fee) VALUES (?, ?)";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, service.getServiceType());
            ps.setDouble(2, service.getServiceFee()); // Now uses setDouble!

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error adding service: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<OfferedService> getAllServices() {
        List<OfferedService> serviceList = new ArrayList<>();
        String sql = "SELECT * FROM tblservices";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                OfferedService service = new OfferedService();

                service.setServiceId(rs.getInt("service_id"));
                service.setServiceType(rs.getString("service_type"));
                service.setServiceFee(rs.getDouble("service_fee")); // Now reads as a double

                serviceList.add(service);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving services: " + e.getMessage());
        }
        return serviceList;
    }

    @Override
    public OfferedService getServiceById(int serviceId) {
        return null; // TODO: Implement later
    }

    @Override
    public boolean updateService(OfferedService service) {
        return false; // TODO: Implement later
    }

    @Override
    public boolean deleteService(int serviceId) {
        return false; // TODO: Implement later
    }
}
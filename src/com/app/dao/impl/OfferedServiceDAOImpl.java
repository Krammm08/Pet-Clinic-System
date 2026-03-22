package com.app.dao.impl;

import com.app.dao.OfferedServiceDAO;
import com.app.exception.DatabaseException;
import com.app.model.OfferedService;
import com.app.util.DbConnection; // Using your updated connection utility!

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public List<OfferedService> getAllServices() throws DatabaseException {
        List<OfferedService> services = new ArrayList<>();

        // FIX: Changed from tblservices to tblofferedservices
        String sql = "SELECT * FROM tblofferedservices";

        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                OfferedService service = new OfferedService();
                service.setServiceId(rs.getInt("service_id"));

                // Note: If your database columns are named differently (like 'name' or 'price'),
                // just change the text inside these green quotes!
                service.setServiceType(rs.getString("service_type"));
                service.setServiceFee(rs.getDouble("service_fee"));

                services.add(service);
            }
        } catch (SQLException e) {
            // This is the exact line that printed your error!
            throw new DatabaseException("Error retrieving services: " + e.getMessage());
        }
        return services;
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
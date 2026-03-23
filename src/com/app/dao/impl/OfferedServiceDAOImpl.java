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
        OfferedService service = null;
        // Note: Ensure 'service_name' and 'price' match your XAMPP columns for this table
        String sql = "SELECT * FROM tblofferedservices WHERE service_id = ?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, serviceId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                service = new OfferedService();
                service.setServiceId(rs.getInt("service_id"));
                // Mapping DB 'service_name' to Model 'serviceType'
                service.setServiceType(rs.getString("service_name"));
                // Mapping DB 'price' to Model 'serviceFee'
                service.setServiceFee(rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("\tX Error: " + e.getMessage());
        }
        return service;
    }

    @Override
    public boolean updateService(OfferedService service) {
        String sql = "UPDATE tblofferedservices SET service_name = ?, price = ? WHERE service_id = ?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, service.getServiceType());
            stmt.setDouble(2, service.getServiceFee());
            stmt.setInt(3, service.getServiceId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("\tX Update Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteService(int serviceId) {
        String sql = "DELETE FROM tblofferedservices WHERE service_id = ?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, serviceId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("\tX Cannot delete: Service is likely linked to existing appointments.");
            return false;
        }
    }
}
package com.app.dao.impl;

import com.app.dao.OfferedServiceDAO;
import com.app.model.OfferedService;
import com.app.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OfferedServiceDAOImpl implements OfferedServiceDAO {

    @Override
    public boolean addService(OfferedService service) {
        String sql = "INSERT INTO tblservices (service_name, description, service_fee) VALUES (?, ?, ?)";

        try (Connection conn = DbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, service.getServiceName());
            ps.setString(2, service.getDescription());
            ps.setInt(3, service.getServiceFee());

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
                service.setServiceName(rs.getString("service_name"));
                service.setDescription(rs.getString("description"));
                service.setServiceFee(rs.getInt("service_fee"));

                serviceList.add(service);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving services: " + e.getMessage());
        }
        return serviceList;
    }

    @Override
    public OfferedService getServiceById(int serviceId) {
        return null;
    }

    @Override
    public boolean updateService(OfferedService service) {
        return false;
    }

    @Override
    public boolean deleteService(int serviceId) {
        return false;
    }
}
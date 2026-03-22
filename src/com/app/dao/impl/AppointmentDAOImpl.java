package com.app.dao.impl;

import com.app.dao.AppointmentDAO;
import com.app.model.Appointment;
import com.app.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements AppointmentDAO {

    @Override
    public boolean addAppointment(Appointment appointment){
        String sql = "INSERT INTO tblappointments (user_id, pet_id, service_id, appointment_date, appointment_time, is_approve) VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection connection = DbConnection.connect();
            PreparedStatement prep = connection.prepareStatement(sql)){
            prep.setInt(1, appointment.getUserID());
            prep.setInt(2, appointment.getPetID());
            prep.setInt(3, appointment.getServiceID());
            prep.setDate(4, appointment.getAppointmentDate());
            prep.setTime(5, appointment.getAppointmentTime());

            prep.setInt(6, appointment.getIsApprove());

            return prep.executeUpdate() > 0;
        } catch (Exception e){
            System.out.println("Error adding medicine: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Appointment> getAllAppointments(){
        List<Appointment> appointmentList = new ArrayList<>();
        String sql = "SELECT * FROM tblappointments";

        try(Connection connection = DbConnection.connect();
            PreparedStatement prep = connection.prepareStatement(sql);
            ResultSet rs = prep.executeQuery()){

            while(rs.next()){
                Appointment appointment = new Appointment();

                appointment.setAppointmentID(rs.getInt("appointment_id"));
                appointment.setUserID(rs.getInt("user_id"));
                appointment.setPetID(rs.getInt("pet_id"));
                appointment.setServiceId(rs.getInt("service_id"));
                appointment.setAppointmentDate(rs.getDate("appointment_date"));
                appointment.setAppointmentTime(rs.getTime("appointment_time"));
                appointment.setIsApprove(rs.getInt("is_approve"));

                appointmentList.add(appointment);
            }
        } catch (Exception e){
            System.out.println("Error retrieving medicines: " + e.getMessage());
        }
        return appointmentList;
    }

    @Override
    public Appointment getAppointmentById(int appointmentId){
        return null;
    }

    @Override
    public boolean updateAppointment(Appointment appointment){
        return false;
    }

    @Override
    public boolean deleteAppointment(int appointmentId){
        return false;
    }
}

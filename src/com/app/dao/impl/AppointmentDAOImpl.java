
package com.app.dao.impl;

import com.app.dao.AppointmentDAO;

import com.app.dao.AppointmentDAO;
import com.app.model.Appointment;
import com.app.model.Medicine;
import com.app.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements AppointmentDAO {

    @Override
    public boolean addAppointment(Appointment appointment){
        String sql = "INSERT INTO tblappointments (appointment_date, appointment_time, is_approve) VALUES (?, ?, ?)";

        try(Connection connection = DbConnection.connect();
            PreparedStatement prep = connection.prepareStatement(sql)){
            prep.setDate(1, appointment.getAppointmentDate());
            prep.setTime(2, appointment.getAppointmentTime());
            prep.setInt(3, appointment.getIsApprove());

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

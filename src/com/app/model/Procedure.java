
package com.app.model;

public class Procedure {
    
    private int procedureId;
    private int appointmentId;
    private int serviceId;
    private int vetId;
    private int userId;
    private int petId;
    private String diagnosis;
    private int medicineId;
    private String procedureDate;
    
    // CONSTRUCTOR
    public void Procedure(int procedureId, int appointmentId, int serviceId, int vetId, int userId, int petId, String diagnosis, int medicineId, String procedureDate){
        this.procedureId = procedureId;
        this.appointmentId = appointmentId;
        this.serviceId = serviceId;
        this.vetId = vetId;
        this.userId = userId;
        this.petId = petId;
        this.diagnosis = diagnosis;
        this.medicineId = medicineId;
        this.procedureDate = procedureDate;
    } 
    
    // GETTER & SETTER
    
    public int getprocedureId(){
        return procedureId;
    }
    
    public void setProcedureId(int procedureId){
        this.procedureId = procedureId;
    }
    
    public int getAppointmentId(){
        return appointmentId;
    }
    
    public void setAppointmentId(int appointmentId){
        this.appointmentId = appointmentId;
    }
    
    public int getServiceId(){
        return serviceId;
    }
    
    public void setServiceId(int serviceId){
        this.serviceId = serviceId;
    }
    
    public int getVetId(){
        return vetId;
    }
    
    public void setVetId(int vetId){
        this.vetId = vetId;
    }
    
    public int getUserId(){
        return userId;
    }
    
    public void setUserId(int userId){
        this.userId = userId;
    }
    
    public int getPetId(){
        return petId;
    }
    
    public void setPetId(int petId){
        this.petId = petId;
    }
    
    public String getDiagnosis(){
        return diagnosis;
    }
    
    public void setDiagnosis(String diagnosis){
        this.diagnosis = diagnosis;
    }
    
    public int getMedicineId(){
        return medicineId;
    }
    
    public void setMedicineId(int medicineId){
        this.medicineId = medicineId;
    }
    
    public String getProcedureDate(){
        return procedureDate;
    }
    
    public void setProcedureDate(String procedureDate){
        this.procedureDate = procedureDate;
    }
}

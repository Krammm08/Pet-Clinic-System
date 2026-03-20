
package com.app.model;

public class Process {
    
    private int processId;
    private int appointmentId;
    private int serviceId;
    private int vetId;
    private int userId;
    private int petId;
    private String diagnosis;
    private int medicineId;
    private String processDate;
    
    // CONSTRUCTOR
    public void Process(int processId, int appointmentId, int serviceId, int vetId, int userId, int petId, String diagnosis, int medicineId, String processDate){
        this.processId = processId;
        this.appointmentId = appointmentId;
        this.serviceId = serviceId;
        this.vetId = vetId;
        this.userId = userId;
        this.petId = petId;
        this.diagnosis = diagnosis;
        this.medicineId = medicineId;
        this.processDate = processDate;
    } 
    
    // GETTER & SETTER
    
    public int getProcessId(){
        return processId;
    }
    
    public void setProcessId(int processId){
        this.processId = processId;
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
    
    public String getProcessDate(){
        return processDate;
    }
    
    public void setProcessDate(String processDate){
        this.processDate = processDate;
    }
}

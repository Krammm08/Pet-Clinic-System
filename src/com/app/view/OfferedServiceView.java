package com.app.view;

import com.app.exception.DatabaseException;
import com.app.model.OfferedService;
import com.app.model.User;
import com.app.dao.OfferedServiceDAO;
import com.app.dao.impl.OfferedServiceDAOImpl;
import com.app.util.Asciiart;
import com.app.util.InputUtil;
import java.util.List;

public class OfferedServiceView {
    
    private final OfferedServiceDAO OfferedServiceDAO = new OfferedServiceDAOImpl();
    private final Asciiart art = new Asciiart();
    
    public void show(User user) throws DatabaseException {

        while (true) {
            
            System.out.println("\n\t========== ALL OFFERED SERVICES ==========");
            System.out.println("\t|\t1. View All Services");
            System.out.println("\t|\t2. Back");
            System.out.println("\t==========================================");
            int choice = InputUtil.getInt("\tChoose option: ");
            System.out.println("\t==========================================\n");
            switch (choice) {

                case 1:
                    viewAllVets();
                    break;

                case 2:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
    private void viewAllVets() throws DatabaseException {
        List<OfferedService> list = OfferedServiceDAO.getAllServices();

        System.out.println("\n\t========== ALL OFFERED SERVICES ==========");

        if (list.isEmpty()) {
            System.out.println("No care options found.");
            return;
        }

        for (OfferedService option : list) {
            System.out.println("Offered Service ID: " + option.getServiceId());
            System.out.println("Service Type: " + option.getServiceType());
            System.out.println("Service Fee: " + option.getServiceFee());
            System.out.println("--------------------------");
        }

    }
}

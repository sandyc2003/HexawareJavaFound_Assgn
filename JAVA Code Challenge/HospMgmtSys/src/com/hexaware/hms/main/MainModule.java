package com.hexaware.hms.main;

import com.hexaware.hms.service.HospitalServiceImpl;
import com.hexaware.hms.dao.IHospitalService;
import com.hexaware.hms.entity.Appointment;
import com.hexaware.hms.exception.PatientNumberNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) throws PatientNumberNotFoundException {
        Scanner scanner = new Scanner(System.in);
        IHospitalService service = new HospitalServiceImpl();
        int choice;

        do {
            System.out.println("\n--- Hospital Appointment Management ---");
            System.out.println("1. Get Appointment by ID");
            System.out.println("2. Get Appointments for Patient");
            System.out.println("3. Get Appointments for Doctor");
            System.out.println("4. Schedule Appointment");
            System.out.println("5. Update Appointment");
            System.out.println("6. Cancel Appointment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Appointment ID: ");
                    int appId = scanner.nextInt();
                    Appointment app1 = service.getAppointmentById(appId);
                    System.out.println(app1 != null ? app1 : "Appointment not found");
                    break;

                case 2:
                    System.out.print("Enter Patient ID: ");
                    int pid = scanner.nextInt();
				List<Appointment> patientApps = service.getAppointmentsForPatient(pid);
				if (patientApps.isEmpty())
				    System.out.println("No appointments found for this patient.");
				else
				    patientApps.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Enter Doctor ID: ");
                    int did = scanner.nextInt();
                    List<Appointment> doctorApps = service.getAppointmentsForDoctor(did);
                    if (doctorApps.isEmpty())
                        System.out.println("No appointments found for this doctor.");
                    else
                        doctorApps.forEach(System.out::println);
                    break;

                case 4:
                    System.out.println("Enter details to schedule appointment:");
                    System.out.print("Appointment ID: ");
                    int newAppId = scanner.nextInt();
                    System.out.print("Patient ID: ");
                    int newPid = scanner.nextInt();
                    System.out.print("Doctor ID: ");
                    int newDid = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Appointment Date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine()); 

                    System.out.print("Description: ");
                    String desc = scanner.nextLine();

                    Appointment newApp = new Appointment(newAppId, newPid, newDid, date, desc);
                    if (service.scheduleAppointment(newApp))
                        System.out.println("Appointment scheduled successfully.");
                    else
                        System.out.println("Failed to schedule appointment.");
                    break;

                case 5:
                    System.out.println("Enter updated appointment details:");
                    System.out.print("Appointment ID: ");
                    int upAppId = scanner.nextInt();
                    System.out.print("Patient ID: ");
                    int upPid = scanner.nextInt();
                    System.out.print("Doctor ID: ");
                    int upDid = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Appointment Date (YYYY-MM-DD): ");
                    LocalDate date1 = LocalDate.parse(scanner.nextLine());

                    System.out.print("Description: ");
                    String upDesc = scanner.nextLine();

                    Appointment upApp = new Appointment(upAppId, upPid, upDid, date1, upDesc);
                    if (service.updateAppointment(upApp))
                        System.out.println("Appointment updated successfully.");
                    else
                        System.out.println("Failed to update appointment.");
                    break;

                case 6:
                    System.out.print("Enter Appointment ID to cancel: ");
                    int cancelId = scanner.nextInt();
                    if (service.cancelAppointment(cancelId))
                        System.out.println("Appointment canceled successfully.");
                    else
                        System.out.println("Failed to cancel appointment.");
                    break;

                case 0:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter again.");
            }

        } while (choice != 0);

        scanner.close();
    }
}

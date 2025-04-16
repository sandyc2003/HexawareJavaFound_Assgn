package com.hexaware.hms.dao;

import com.hexaware.hms.entity.Appointment;
import java.util.List;

public interface IHospitalService {

    // Get an appointment by its ID
    Appointment getAppointmentById(int appointmentId);

    // Get all appointments for a particular patient
    List<Appointment> getAppointmentsForPatient(int patientId);

    // Get all appointments for a particular doctor
    List<Appointment> getAppointmentsForDoctor(int doctorId);

    // Schedule a new appointment
    boolean scheduleAppointment1(Appointment appointment);

    // Update an existing appointment
    boolean updateAppointment(Appointment appointment);

    // Cancel an appointment
    boolean cancelAppointment(int appointmentId);

	boolean scheduleAppointment(com.hexaware.hms.entity.Appointment appointment);
}

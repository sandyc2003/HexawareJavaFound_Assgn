package com.hexaware.hms.service;


import com.hexaware.hms.entity.Appointment;
import com.hexaware.hms.dao.HospitalService;
import com.hexaware.hms.dao.IHospitalService;

import java.util.List;

public class HospitalServiceImpl implements IHospitalService {

    private final HospitalService hospitalServiceDao;
	private HospitalService HospitalService;

    public HospitalServiceImpl() {
        this.hospitalServiceDao = new HospitalService();
		this.HospitalService = new HospitalService(); 
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        
        return hospitalServiceDao.getAppointmentById(appointmentId);
    }

    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId) {
        
        return hospitalServiceDao.getAppointmentsForPatient(patientId);
    }

    @Override
    public List<Appointment> getAppointmentsForDoctor(int doctorId) {
        
        return hospitalServiceDao.getAppointmentsForDoctor(doctorId);
    }

    @Override
    public boolean scheduleAppointment(Appointment appointment) {
        
        return hospitalServiceDao.scheduleAppointment(appointment);
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        
        return hospitalServiceDao.updateAppointment(appointment);
    }

    @Override
    public boolean cancelAppointment(int appointmentId) {
        
        return hospitalServiceDao.cancelAppointment(appointmentId);
    }

	@Override
	public boolean scheduleAppointment1(Appointment appointment) {
		// TODO Auto-generated method stub
		return false;
	}
}

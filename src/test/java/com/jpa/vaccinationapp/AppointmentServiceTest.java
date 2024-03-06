package com.jpa.vaccinationapp;

import com.jpa.vaccinationapp.admin.Admin;
import com.jpa.vaccinationapp.admin.AdminException;
import com.jpa.vaccinationapp.admin.AdminRepository;
import com.jpa.vaccinationapp.admin.service.AdminService;
import com.jpa.vaccinationapp.appointment.Appointment;
import com.jpa.vaccinationapp.appointment.AppointmentException;
import com.jpa.vaccinationapp.appointment.AppointmentRepository;
import com.jpa.vaccinationapp.appointment.VaccinationStatusDTO;
import com.jpa.vaccinationapp.appointment.service.AppointmentService;
import com.jpa.vaccinationapp.patient.Patient;
import com.jpa.vaccinationapp.patient.PatientException;
import com.jpa.vaccinationapp.patient.PatientRepository;
import com.jpa.vaccinationapp.patient.service.PatientService;
import com.jpa.vaccinationapp.slot.Slot;
import com.jpa.vaccinationapp.slot.SlotException;
import com.jpa.vaccinationapp.slot.SlotRepository;
import com.jpa.vaccinationapp.slot.service.SlotService;
import com.jpa.vaccinationapp.vaccinationCenter.Center;
import com.jpa.vaccinationapp.vaccinationCenter.CenterException;
import com.jpa.vaccinationapp.vaccinationCenter.CenterRepository;
import com.jpa.vaccinationapp.vaccinationCenter.service.CenterService;
import com.jpa.vaccinationapp.vaccine.Vaccine;
import com.jpa.vaccinationapp.vaccine.VaccineException;
import com.jpa.vaccinationapp.vaccine.VaccineRepository;
import com.jpa.vaccinationapp.vaccine.service.VaccineService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@SpringBootTest
@TestInstance(TestInstance. Lifecycle. PER_CLASS)
class AppointmentServiceTest {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private CenterService centerService;
    @Autowired
    private CenterRepository centerRepository;
    @Autowired
    private SlotService slotService;
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    VaccineService vaccineService;
    @Autowired
    VaccineRepository vaccineRepository;

    Patient patient;
    Center center;
    Slot slot;
    Admin admin;
    Vaccine vaccine;
    Appointment appointment;
    Appointment bookedAppointment;

    @BeforeAll
    @Transactional
    void beforeAll(){
        try {
            admin = this.adminService.addAdmin(new Admin("admin@gmail.com", "1234567890",
                    "Smith", "Password","Admin"));
            patient = this.patientService.createPatient(new Patient("user@gmail.com", "1234"
                    , "password", "xyz", "sample"));
            vaccine = this.vaccineService.createVaccine(new Vaccine("covishield",
                    LocalDate.now(), LocalDate.now(), "covid vaccine"));
            Set<Vaccine> vaccineSet = new HashSet<>();
            vaccineSet.add(vaccine);
            center = this.centerService.createCenter(new Center("Alpha Health",
                    "No:485,krishnar street,polur, Arakkonam",
                    "631003","Ranipet",
                    "Tamil Nadu","9593813109",vaccineSet, null, admin));

            LocalDateTime currentTime = LocalDateTime.now();
            LocalTime localTime = currentTime.toLocalTime();
            Date startTime = Date.from(localTime.atDate(LocalDate.of(1970, 1, 1))
                    .atZone(ZoneId.systemDefault())
                    .toInstant());
            slot = this.slotService.createSlot(new Slot(startTime, startTime, 10, center,null
                    ,LocalDate.now()));
        } catch (VaccineException | PatientException | AdminException | SlotException | CenterException e) {
            System.out.println(e.getMessage());
        }
    }
    @AfterAll
    void deleteEveryResource() {
        this.slotRepository.delete(slot);
        this.centerRepository.delete(center);
        this.vaccineRepository.delete(vaccine);
        this.patientRepository.delete(patient);
        this.adminRepository.delete(admin);
    }
    @BeforeEach
    void createAppointment() {
        appointment = new Appointment(false, slot, vaccine,
                null, patient,LocalDate.now());
        try {
            bookedAppointment = this.appointmentService.bookAnAppointment(appointment);
        } catch (AppointmentException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterEach
    void deletAppointment() {
        this.appointmentRepository.delete(appointment);
    }
    @Test
    void bookAnAppointmentTest() {
        Appointment createAppointment = new Appointment(false, slot, vaccine,
                null, patient,LocalDate.of(2024, 10, 1));
        Appointment newAppointment = null;
        try {
            newAppointment = this.appointmentService.bookAnAppointment(createAppointment);
            Assertions.assertNotNull(newAppointment);
        } catch (AppointmentException e) {
            Assertions.fail(e.getMessage());
        }
        this.appointmentRepository.delete(newAppointment);
    }

    @Test
    void bookAnAppointmentExceptionTest() {
        Assertions.assertThrows(AppointmentException.class, ()->this.appointmentService.bookAnAppointment(null));
    }
    @Test
    void getAppointmentByIdTest() {
        Appointment existingAppointment = null;
        try {
            Assertions.assertNotNull(bookedAppointment);
            existingAppointment = this.appointmentService.getAppointmentById(bookedAppointment.getBookingId());
            Assertions.assertNotNull(existingAppointment);
            Assertions.assertEquals(existingAppointment.getBookingId(), bookedAppointment.getBookingId());
        } catch (AppointmentException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    void getAppointmentByIdMessageTest() {
        try {
            Assertions.assertNotNull(this.appointmentService.getAppointmentById(10));
        } catch (AppointmentException e) {
            Assertions.assertEquals("No such appointment found for the given id "+10, e.getMessage());
        }
    }

    @Test
    void getAppointmentsForVaccinationCenterTest() {
        try {
            List<Appointment> appointmentList = this.appointmentService.getAppointmentsForVaccinationCenter(center.getCenterId());
            Assertions.assertNotNull(appointmentList);
            Assertions.assertNotNull(appointmentList.stream().filter(appointment ->
                    Objects.equals(appointment.getSlot().getCenter().getCenterId(), center.getCenterId())));
        } catch (AppointmentException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    void deleteAppointmentByIdTest() {
        try {
            Assertions.assertNotNull(this.appointmentService.deleteAppointmentById(bookedAppointment.getBookingId()));
            Assertions.assertTrue(this.appointmentRepository.findById(bookedAppointment.getBookingId()).isEmpty());
        } catch (AppointmentException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void updateAppointmentTest() {
        try {
            VaccinationStatusDTO vaccinationStatusDTO = new VaccinationStatusDTO();
            vaccinationStatusDTO.setAppointmentId(bookedAppointment.getBookingId());
            vaccinationStatusDTO.setVaccinated(true);
            Appointment updateAppointment = this.appointmentService.updateAppointmentStatus(vaccinationStatusDTO);
            Assertions.assertNotNull(updateAppointment);
        } catch (AppointmentException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    void getAppointmentByPatientTest() {
        try {
            List<Appointment> appointmentList = this.appointmentService.getAppointmentByPatient(patient.getPatientId());
            Assertions.assertNotNull(appointmentList);
            List<Appointment> appointments = appointmentList.stream().filter(appointment -> appointment.getPatient()
                    .getPatientId().equals(patient.getPatientId())).toList();
            Assertions.assertFalse(appointments.isEmpty());
        } catch (AppointmentException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    void getAppointmentByPatientExceptionTest() {
        Patient newPatient = null;
        try {
            newPatient = new Patient("user1@gmail.com", "1234"
                    , "password1", "xyz", "sample");
            try {
                newPatient = this.patientService.createPatient(newPatient);
            } catch (PatientException e) {
                Assertions.fail(e.getMessage());
            }
            List<Appointment> appointmentList = this.appointmentService.getAppointmentByPatient(newPatient.getPatientId());
            Assertions.assertNull(appointmentList);
        } catch (AppointmentException e) {
            Assertions.assertEquals("No appointments found for the given patientId :" + newPatient.getPatientId(), e.getMessage());
        }
        this.patientRepository.delete(newPatient);
    }
    @Test
    void getAllAppointmentsTest() {
        try{
            List<Appointment> appointmentList = this.appointmentService.getAllAppointments();
            Assertions.assertNotNull(appointmentList);
        } catch (AppointmentException e) {
            Assertions.fail(e.getMessage());
        }
    }
}

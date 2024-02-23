package com.jpa.vaccinationapp.appointment;

public class VaccinationStatusDTO {
    private Integer appointmentId;
    private Boolean isVaccinated;

    public VaccinationStatusDTO() {
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Boolean getVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(Boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    public VaccinationStatusDTO(Integer appointmentId, Boolean isVaccinated) {
        this.appointmentId = appointmentId;
        this.isVaccinated = isVaccinated;
    }
}

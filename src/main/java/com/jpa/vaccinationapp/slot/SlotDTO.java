package com.jpa.vaccinationapp.slot;

public class SlotDTO {
    private Integer slotId;
    private Integer centerId;

    public SlotDTO(Integer slotId, Integer centerId) {
        this.slotId = slotId;
        this.centerId = centerId;
    }

    public SlotDTO() {
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }
}

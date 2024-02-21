package com.jpa.vaccinationapp.slot;

public class WindowDto {
    private Integer slotId;
    private Integer windows;

    public WindowDto(Integer slotId, Integer windows) {
        this.slotId = slotId;
        this.windows = windows;
    }

    public WindowDto() {
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public Integer getWindows() {
        return windows;
    }

    public void setWindows(Integer windows) {
        this.windows = windows;
    }
}

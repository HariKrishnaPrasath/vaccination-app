package com.jpa.vaccinationapp.certificate;

import com.jpa.vaccinationapp.appointment.Appointment;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PDFGenerator {
    public void generate(Appointment appointment, HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance (document, response.getOutputStream());
        document.open();
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);
        Paragraph paragraph1 = new Paragraph(appointment.getSlot().getCenter().getCenterName(), fontTiltle);
        document.add(paragraph1);

        Paragraph paragraph2 = new Paragraph("slot time" + appointment.getSlot().getStartTime()+"-"+
                appointment.getSlot().getEndTime(),fontTiltle);
        document.add(paragraph2);

        Paragraph paragraph3 = new Paragraph("Name: "+appointment.getPatient().getPatientName()
                +"Vaccinated Successfully on " + appointment.getBookingDate());
        document.add(paragraph3);

        Paragraph paragraph4 = new Paragraph(" Vaccine Name: "+appointment.getVaccine().getVaccineName());
        document.add(paragraph4);
        document.close();
    }
}

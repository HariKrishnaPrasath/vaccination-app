package com.jpa.vaccinationapp.certificate;

import com.jpa.vaccinationapp.appointment.Appointment;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

public class PDFGenerator {
    public void generate(Appointment appointment, HttpServletResponse response) throws DocumentException, IOException {


        Document document = new Document(PageSize.A5);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        titleFont.setSize(18);
        Font contentFont = FontFactory.getFont(FontFactory.HELVETICA);
        contentFont.setSize(12);

        Font contentFont2 = FontFactory.getFont(FontFactory.HELVETICA);
        contentFont2.setSize(8);
// Adding a title
        Paragraph title = new Paragraph("Vaccination Certificate", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(Chunk.NEWLINE); // Adding space between elements

        Paragraph line =new Paragraph("");
// Center Name
        Paragraph centerName = new Paragraph("Center: " + appointment.getSlot().getCenter().getCenterName(), contentFont);
        document.add(centerName);

        Paragraph centerContact = new Paragraph("Center Contact No: " + appointment.getSlot().getCenter().getContactNumber(), contentFont);
        document.add(centerContact);

        Paragraph centerAddress = new Paragraph("Center Address: " + appointment.getSlot().getCenter().getAddress()+","+appointment.getSlot().getCenter().getDistrict()+","+appointment.getSlot().getCenter().getState()+","+appointment.getSlot().getCenter().getPincode(), contentFont);
        document.add(centerAddress);

// Slot Time
        Paragraph slotTime = new Paragraph("Slot Time: " + appointment.getSlot().getStartTime() + "-" + appointment.getSlot().getEndTime(), contentFont);
        document.add(slotTime);

// Patient Name
        Paragraph patientName = new Paragraph("Name: " + appointment.getPatient().getPatientName(), contentFont);
        document.add(patientName);

// Vaccination Date
        Paragraph bookingDate = new Paragraph("Vaccinated on: " + appointment.getBookingDate(), contentFont);
        document.add(bookingDate);

// Vaccine Name
        Paragraph vaccineName = new Paragraph("Vaccine: " + appointment.getVaccine().getVaccineName(), contentFont);
        document.add(vaccineName);

        Paragraph detail = new Paragraph("Download Date: " + LocalDate.now(), contentFont);
        document.add(detail);

        document.add(Chunk.NEWLINE);

        String imageUrl = "https://th.bing.com/th/id/R.6338328416aec03141842b1058874ce6?rik=T6%2by%2fmqalxONtQ&riu=http%3a%2f%2fwww.pngall.com%2fwp-content%2fuploads%2f2%2fApproved-Stamp.png&ehk=Wu%2bK8DpoafFsOStKDaHFrIJZYyxZYV87FmwEwJ8jnYU%3d&risl=&pid=ImgRaw&r=0"; // Replace with your image URL
        Image image = Image.getInstance(new URL(imageUrl));
        image.scaleToFit(200, 200);
        document.add(image);

        document.add(Chunk.NEWLINE);

        Paragraph details2 =new Paragraph("Approval Status",contentFont);
        document.add(details2);

        document.add(Chunk.NEWLINE);

        Paragraph details3=new Paragraph("This certificate is authorized by "+appointment.getSlot().getCenter().getCenterName()+"admin",contentFont2);
        document.add(details3);
        document.close();


    }
}

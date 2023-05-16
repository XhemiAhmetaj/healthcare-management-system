package com.ikub.healthcare.service;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import com.ikub.healthcare.domain.entity.Appointment;
import com.ikub.healthcare.domain.exception.ResourceNotFoundException;
import com.ikub.healthcare.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentServiceTest;
    @MockBean
    private AppointmentRepository appointmentRepository;


    @Test
    public void findAppointmentById_ok() {
        Mockito.doReturn(Optional.of(new Appointment())).when(appointmentRepository).findById(Mockito.anyInt());
        AppointmentDTO out = appointmentServiceTest.findAppointmentById(1);
        assertNotNull(out);
    }

    @Test
    public void findAppointmentById_ko() {
        Mockito.doThrow(new ResourceNotFoundException("Appointment not found"))
                .when(appointmentRepository).findById(Mockito.anyInt());
        Throwable throwable = assertThrows(Throwable.class, () -> appointmentServiceTest.findAppointmentById(1));
        assertEquals(ResourceNotFoundException.class, throwable.getClass());
    }

    @Test
    public void test_findAllAppointment_ok() {
        List<AppointmentDTO> appointments = new ArrayList<>();
        Mockito.doReturn(appointments).when(appointmentRepository).findAll();
        List<AppointmentDTO> out = appointmentServiceTest.findAllAppointment();
        assertNotNull(out);
    }
}

package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.AppointmentMapper;
import com.java.rollercoaster.dao.EventMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.errorEnum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    public ErrorEnum addAppointment(Appointment appointment) {
        if (null == appointment){
            return ErrorEnum.EMPTY_APPOINTMENT;
        } else if (null != appointmentMapper.selectByPrimaryKey(appointment.getAppointmentid())){
            return ErrorEnum.DUPLICATE_APPOINTMENT;
        } else if (null == eventMapper.selectByPrimaryKey(appointment.getEventName())) {
            return ErrorEnum.NO_SUCH_EVENT;
        } else if(null == userAccountMapper.selectByPrimaryKey(appointment.getUserId())) {
            return ErrorEnum.USER_NOT_EXIST;
        }else if(0 >= eventMapper.selectByPrimaryKey(appointment.getEventName()).getEventRemainPositions()) {
            return ErrorEnum.EVENT_NO_POSITION;
        }
        Event event = eventMapper.selectByPrimaryKey(appointment.getEventName());
        Integer currentPosition = event.getEventRemainPositions() - 1;
        event.setEventRemainPositions(currentPosition);
        eventMapper.updateByPrimaryKeySelective(event);
        appointmentMapper.insert(appointment);
        return ErrorEnum.OK;

    }

    @Override
    public ErrorEnum updateAppointment(Appointment appointment) {
        if (null == appointment){
            return ErrorEnum.EMPTY_APPOINTMENT;
        } else if (null == appointmentMapper.selectByPrimaryKey(appointment.getAppointmentid())){
            return ErrorEnum.NO_SUCH_APPOINTMENT;
        } else if (null == eventMapper.selectByPrimaryKey(appointment.getEventName())){
            return ErrorEnum.NO_SUCH_EVENT;
        } else if (null == userAccountMapper.selectByPrimaryKey(appointment.getUserId())){
            return ErrorEnum.USER_NOT_EXIST;
        }
        Appointment prevAppoint = appointmentMapper.selectByPrimaryKey(appointment.getAppointmentid());
        if(prevAppoint.getEventName() == appointment.getEventName()){
            appointmentMapper.updateByPrimaryKeySelective(appointment);
            return ErrorEnum.OK;
        } else{
            if(0 >= eventMapper.selectByPrimaryKey(appointment.getEventName()).getEventRemainPositions()){
                return ErrorEnum.EVENT_NO_POSITION;
            }else{
                Event newEvent = eventMapper
                        .selectByPrimaryKey(appointment.getEventName());
                Integer currentNewEventPosition = newEvent.getEventRemainPositions() - 1;
                newEvent.setEventRemainPositions(currentNewEventPosition);
                eventMapper.updateByPrimaryKeySelective(newEvent);

                appointmentMapper.updateByPrimaryKeySelective(appointment);

                Event oldEvent = eventMapper
                        .selectByPrimaryKey(prevAppoint.getEventName());
                Integer currentOldEventPosition = oldEvent.getEventRemainPositions() + 1;
                oldEvent.setEventRemainPositions(currentOldEventPosition);
                eventMapper.updateByPrimaryKeySelective(oldEvent);

                return ErrorEnum.OK;
            }
        }
    }

    @Override
    public ErrorEnum deleteAppointment(String appointmentId) {
        if (null == appointmentId){
            return ErrorEnum.EMPTY_APPOINTMENT;
        } else if (null == appointmentMapper.selectByPrimaryKey(appointmentId)){
            return ErrorEnum.NO_SUCH_APPOINTMENT;
        }
        Event unwantedEvent = eventMapper
                .selectByPrimaryKey(
                        appointmentMapper
                                .selectByPrimaryKey(appointmentId)
                                .getEventName());
        appointmentMapper.deleteByPrimaryKey(appointmentId);
        Integer newEventPosition = unwantedEvent.getEventRemainPositions() + 1;
        unwantedEvent.setEventRemainPositions(newEventPosition);
        eventMapper.updateByPrimaryKeySelective(unwantedEvent);

        return ErrorEnum.OK;
    }

}

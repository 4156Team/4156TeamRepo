package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.AppointmentMapper;
import com.java.rollercoaster.dao.EventMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.AppointmentExample;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.service.AppointmentService;
import com.java.rollercoaster.service.model.TimedAppointmentModel;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    public String addAppointment(Appointment appointment) throws BusinessException {
        if (null == appointment) {
            throw new BusinessException(ErrorEnum.EMPTY_APPOINTMENT);
        } else if (null != appointmentMapper.selectByPrimaryKey(appointment
                .getAppointmentId())) {
            throw new BusinessException(ErrorEnum.DUPLICATE_APPOINTMENT);
        } else if (null == eventMapper.selectByPrimaryKey(appointment.getEventName())) {
            throw new BusinessException(ErrorEnum.NO_SUCH_EVENT);
        } else if (null == userAccountMapper.selectByPrimaryKey(appointment.getUserId())) {
            throw new BusinessException(ErrorEnum.USER_NOT_EXIST);
        } else if (0 >= eventMapper.selectByPrimaryKey(appointment.getEventName())
                .getEventRemainPositions()) {
            throw new BusinessException(ErrorEnum.EVENT_NO_POSITION);
        }
        Event event = eventMapper.selectByPrimaryKey(appointment.getEventName());
        Integer currentPosition = event.getEventRemainPositions() - 1;
        event.setEventRemainPositions(currentPosition);
        eventMapper.updateByPrimaryKeySelective(event);
        appointmentMapper.insert(appointment);
        return appointment.getAppointmentId();

    }

    @Override
    public ErrorEnum updateAppointment(Appointment appointment) {
        if (null == appointment) {
            return ErrorEnum.EMPTY_APPOINTMENT;
        } else if (null == appointmentMapper
                .selectByPrimaryKey(appointment
                        .getAppointmentId())) {
            return ErrorEnum.NO_SUCH_APPOINTMENT;
        } else if (null == eventMapper.selectByPrimaryKey(appointment.getEventName())) {
            return ErrorEnum.NO_SUCH_EVENT;
        } else if (null == userAccountMapper.selectByPrimaryKey(appointment.getUserId())) {
            return ErrorEnum.USER_NOT_EXIST;
        }
        Appointment prevAppoint = appointmentMapper
                .selectByPrimaryKey(appointment.getAppointmentId());
        if (prevAppoint.getEventName().equals(appointment.getEventName())) {
            appointmentMapper.updateByPrimaryKeySelective(appointment);
            return ErrorEnum.OK;
        } else {
            if (0 >= eventMapper.selectByPrimaryKey(appointment.getEventName())
                    .getEventRemainPositions()) {
                return ErrorEnum.EVENT_NO_POSITION;
            } else {
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
    public ErrorEnum deleteAppointment(String appointmentId, UserModel userModel) {
        if (null == appointmentId) {
            return ErrorEnum.EMPTY_APPOINTMENT;
        } else if (null == appointmentMapper.selectByPrimaryKey(appointmentId)) {
            return ErrorEnum.NO_SUCH_APPOINTMENT;
        } else if (userModel.getRole() == Role.visitor && !userModel.getUserId()
                .equals(appointmentMapper
                        .selectByPrimaryKey(appointmentId).getUserId()) ) {
            return ErrorEnum.NOT_SAME_VISITOR;
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

    @Override
    public List<TimedAppointmentModel> getAppointmentsByUserId(Integer userId) {
        AppointmentExample appointmentExample = new AppointmentExample();
        AppointmentExample.Criteria criteria = appointmentExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<Appointment> appointmentList = appointmentMapper.selectByExample(appointmentExample);
        List<TimedAppointmentModel> timedAppointmentModelsList
                = addTime2Appointment(appointmentList);
        return timedAppointmentModelsList;
    }

    private List<TimedAppointmentModel> addTime2Appointment(List<Appointment> appointments) {
        List<TimedAppointmentModel> res = new ArrayList<>();

        for (Appointment appointment: appointments) {
            String eventName = appointmentMapper
                    .selectByPrimaryKey(appointment.getAppointmentId())
                    .getEventName();
            Event event = eventMapper.selectByPrimaryKey(eventName);
            TimedAppointmentModel timedAppointmentModel
                    = new TimedAppointmentModel(appointment, event);
            res.add(timedAppointmentModel);
        }

        return res;
    }


}

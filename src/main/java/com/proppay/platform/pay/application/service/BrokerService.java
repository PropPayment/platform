package com.proppay.platform.pay.application.service;

import com.proppay.platform.pay.adapter.in.web.dto.BrokerConditionRequest;
import com.proppay.platform.pay.application.in.broker.*;
import com.proppay.platform.pay.adapter.in.web.dto.BrokerRequest;
import com.proppay.platform.pay.application.out.admin.AdminPort;
import com.proppay.platform.pay.application.out.broker.DeleteBrokerPort;
import com.proppay.platform.pay.application.out.broker.LoadBrokerPort;
import com.proppay.platform.pay.application.out.broker.SaveBrokerPort;
import com.proppay.platform.pay.domain.admin.Admin;
import com.proppay.platform.pay.domain.broker.Broker;
import com.proppay.platform.pay.domain.broker.BrokerAddress;
import com.proppay.platform.pay.domain.property.Property;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BrokerService implements RegisterBrokerUseCase, GetBrokerUseCase, ListBrokersUseCase, ApproveBrokerUseCase, DeleteBrokerUseCase {


    private final LoadBrokerPort loadPort;
    private final SaveBrokerPort savePort;
    private final DeleteBrokerPort deletePort;
    private final AdminPort adminPort;

    @Override
    public Broker registerBroker(BrokerRequest request) {
        BrokerAddress address = BrokerAddress.of(request.getStreetAddress(), request.getDetailAddress(), request.getZipCode());
        Broker broker = Broker.of(request.getName(), request.getLicenseNumber(), request.getPhoneNumber(), address);

        try {
            return savePort.saveBroker(broker);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("이미 등록된 공인중개사 등록번호입니다.");
        }
    }


    @Override
    public void approveBroker(Long AdminId, Long brokerId) {

        Broker broker = loadPort.loadBroker(brokerId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 공인중개사 존재하지 않습니다."));

        Admin admin = adminPort.loadAdmin(AdminId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 관리자가 존재하지 않습니다."));

        broker.approve(admin);

        savePort.saveBroker(broker);

    }


    @Override
    public void deleteBroker(Long brokerId) {
        if (!loadPort.existsBroker(brokerId)) {
            throw new EntityNotFoundException("해당 공인중개사가 존재하지 않습니다.");
        }

        deletePort.deleteBroker(brokerId);
    }

    @Override
    public Optional<Broker> getBroker(Long brokerId) {
        return loadPort.loadBroker(brokerId);
    }

    @Override
    public Page<Broker> getList(Pageable pageable) {
        return loadPort.loadBrokers(pageable);
    }

    @Override
    public Page<Broker> getListNotApproved(Pageable pageable) {
        return loadPort.loadBrokersNotApproved(pageable);
    }

    @Override
    public Page<Broker> getListRejected(Pageable pageable) {
        return loadPort.loadBrokersRejected(pageable);
    }

    @Override
    public Page<Property> getListByRegion(Pageable pageable, BrokerConditionRequest request) {
        return null;
    }

    @Override
    public Page<Property> getListByLikeCount(Pageable pageable) {
        return null;
    }
}

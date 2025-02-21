package com.proppay.platform.pay.application.service;

import com.proppay.platform.pay.application.in.broker.ApproveBrokerUseCase;
import com.proppay.platform.pay.application.in.broker.DeleteBrokerUseCase;
import com.proppay.platform.pay.application.in.broker.RegisterBrokerUseCase;
import com.proppay.platform.pay.application.in.dto.BrokerRequest;
import com.proppay.platform.pay.application.out.admin.AdminPort;
import com.proppay.platform.pay.application.out.broker.DeleteBrokerPort;
import com.proppay.platform.pay.application.out.broker.LoadBrokerPort;
import com.proppay.platform.pay.application.out.broker.SaveBrokerPort;
import com.proppay.platform.pay.domain.admin.Admin;
import com.proppay.platform.pay.domain.broker.Broker;
import com.proppay.platform.pay.domain.broker.BrokerAddress;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BrokerService implements RegisterBrokerUseCase, ApproveBrokerUseCase, DeleteBrokerUseCase {

    private final LoadBrokerPort loadPort;
    private final SaveBrokerPort savePort;
    private final DeleteBrokerPort deletePort;
    private final AdminPort adminPort;


    @Override
    public Broker registerBroker(BrokerRequest request) {

        BrokerAddress address = BrokerAddress.of(request.getStreetAddress(), request.getDetailAddress(), request.getZipCode());
        Broker broker = Broker.of(request.getName(), request.getLicenseNumber(), request.getPhoneNumber(), address);

        return savePort.saveBroker(broker);
    }


    @Override
    public void approveBroker(Long AdminId, Long brokerId) {

        Broker broker = loadPort.loadBroker(brokerId)
                .orElseThrow(() -> new EntityNotFoundException("해당 공인중개사 존재하지 않습니다."));

        Admin admin = adminPort.loadAdmin(AdminId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 관린자가 존재하지 않습니다."));

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

}

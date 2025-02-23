package com.proppay.platform.pay.application.service;

import com.proppay.platform.pay.adapter.in.web.dto.LawyerConditionRequest;
import com.proppay.platform.pay.adapter.in.web.dto.LawyerRequest;
import com.proppay.platform.pay.application.in.lawyer.*;
import com.proppay.platform.pay.application.out.admin.AdminPort;
import com.proppay.platform.pay.application.out.lawyer.DeleteLawyerPort;
import com.proppay.platform.pay.application.out.lawyer.LoadLawyerPort;
import com.proppay.platform.pay.application.out.lawyer.SaveLawyerPort;
import com.proppay.platform.pay.domain.admin.Admin;
import com.proppay.platform.pay.domain.lawyer.Lawyer;
import com.proppay.platform.pay.domain.lawyer.LawyerAddress;
import com.proppay.platform.pay.domain.lawyer.LawyerSnippet;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LawyerService implements RegisterLawyerUseCase, GetLawyerUseCase, ListLawyersUseCase, ApproveLawyerUseCase, DeleteLawyerUseCase {

    private final SaveLawyerPort savePort;
    private final LoadLawyerPort loadPort;
    private final DeleteLawyerPort deletePort;
    private final AdminPort loadAdminPort;

    @Override
    public Lawyer registerLawyer(LawyerRequest request) {

        LawyerAddress address = LawyerAddress.of(request.getStreetAddress(), request.getDetailAddress(), request.getPostalCode());
        LawyerSnippet snippet = LawyerSnippet.of(request.getRegion(), request.getWorkingHours(), request.getAffiliatedAgency(), request.isRemoteSupportAvailable());

        Lawyer lawyer = Lawyer.of(request.getName(), request.getLicenseNumber(), request.getContact(), address, snippet);

        try {
            return savePort.saveLawyer(lawyer);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("이미 등록된 법무사 등록번호입니다.");
        }
    }

    @Override
    public void approveLawyer(Long AdminId, Long lawyerId) {
        Lawyer lawyer = loadPort.loadLawyer(lawyerId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 법무사 입니다."));

        Admin admin = loadAdminPort.loadAdmin(AdminId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 관리자가 존재하지 않습니다."));

        lawyer.approve(admin);

        savePort.saveLawyer(lawyer);
    }


    @Override
    public Optional<Lawyer> getLawyer(Long lawyerId) {
        return loadPort.loadLawyer(lawyerId);
    }

    @Override
    public Page<Lawyer> getList(Pageable pageable) {
        return loadPort.loadLawyers(pageable);
    }

    @Override
    public Page<Lawyer> getListNotApproved(Pageable pageable) {
        return loadPort.loadLawyersNotApproved(pageable);
    }

    @Override
    public Page<Lawyer> getListRejected(Pageable pageable) {
        return loadPort.loadLawyersRejected(pageable);
    }

    @Override
    public Page<Lawyer> getListByRegion(Pageable pageable, LawyerConditionRequest request) {
        return null;
    }

    @Override
    public Page<Lawyer> getListByLikeCount(Pageable pageable) {
        return null;
    }

    @Override
    public void deleteLawyer(Long lawyerId) {
        deletePort.deleteLawyer(lawyerId);
    }
}

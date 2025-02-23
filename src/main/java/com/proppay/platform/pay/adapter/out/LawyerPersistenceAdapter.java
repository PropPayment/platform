package com.proppay.platform.pay.adapter.out;

import com.proppay.platform.pay.adapter.out.jpa.lawyer.LawyerJpaEntity;
import com.proppay.platform.pay.adapter.out.jpa.lawyer.LawyerJpaEntityRepository;
import com.proppay.platform.pay.application.out.lawyer.DeleteLawyerPort;
import com.proppay.platform.pay.application.out.lawyer.LoadLawyerPort;
import com.proppay.platform.pay.application.out.lawyer.SaveLawyerPort;
import com.proppay.platform.pay.domain.lawyer.Lawyer;
import com.proppay.platform.pay.domain.lawyer.LawyerStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LawyerPersistenceAdapter implements SaveLawyerPort, LoadLawyerPort, DeleteLawyerPort {

    private final LawyerJpaEntityRepository repository;


    @Override
    public Lawyer saveLawyer(Lawyer lawyer) {
        var entity = LawyerJpaEntity.from(lawyer);
        return repository.save(entity)
                .toDomain();
    }

    @Override
    public Optional<Lawyer> loadLawyer(Long id) {
        return repository.findById(id)
                .map(LawyerJpaEntity::toDomain);
    }

    @Override
    public boolean existsLawyer(Long id) {
        return repository.existsById(id);
    }

    // 승인된 법무사
    @Override
    public Page<Lawyer> loadLawyers(Pageable pageable) {
        return repository.findAllByOrderByCreatedAtDesc(LawyerStatus.APPROVED, pageable)
                .map(LawyerJpaEntity::toDomain);
    }

    @Override
    public Page<Lawyer> loadLawyersNotApproved(Pageable pageable) {
        return repository.findAllByOrderByCreatedAtDesc(LawyerStatus.PENDING, pageable)
                .map(LawyerJpaEntity::toDomain);
    }

    @Override
    public Page<Lawyer> loadLawyersRejected(Pageable pageable) {
        return repository.findAllByOrderByCreatedAtDesc(LawyerStatus.REJECTED, pageable)
                .map(LawyerJpaEntity::toDomain);
    }

    @Override
    public Page<Lawyer> loadLawyersByRegion(String city, String district, String dong, Pageable pageable) {
        return repository.findByRegion(LawyerStatus.APPROVED, city, district, dong, pageable)
                .map(LawyerJpaEntity::toDomain);
    }

    @Override
    public Page<Lawyer> loadLawyersByRegionAndLike(String city, String district, String dong, Pageable pageable) {
        return repository.findByRegionAndLikeCount(LawyerStatus.APPROVED, city, district, dong, pageable)
                .map(LawyerJpaEntity::toDomain);
    }

    @Override
    public void deleteLawyer(Long id) {
        repository.deleteById(id);
    }


}

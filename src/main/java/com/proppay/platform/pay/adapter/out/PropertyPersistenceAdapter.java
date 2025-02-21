package com.proppay.platform.pay.adapter.out;

import com.proppay.platform.pay.adapter.out.jpa.property.PropertyJpaEntity;
import com.proppay.platform.pay.adapter.out.jpa.property.PropertyJpaEntityRepository;
import com.proppay.platform.pay.adapter.in.web.dto.ConditionRequest;
import com.proppay.platform.pay.application.out.property.DeletePropertyPort;
import com.proppay.platform.pay.application.out.property.LoadPropertyPort;
import com.proppay.platform.pay.application.out.property.SavePropertyPort;
import com.proppay.platform.pay.domain.property.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PropertyPersistenceAdapter implements LoadPropertyPort, SavePropertyPort, DeletePropertyPort {

    private final PropertyJpaEntityRepository repository;

    @Override
    public Property saveProperty(Property property) {

        var entity = PropertyJpaEntity.from(property);
        return repository.save(entity).toDomain();
    }

    @Override
    public Optional<Property> loadProperty(Long id) {
        return repository.findById(id)
                .map(PropertyJpaEntity::toDomain);
    }

    @Override
    public Page<Property> loadList(Pageable pageable) {
        Page<PropertyJpaEntity> result = repository.findAllByOrderByCreatedAtDesc(pageable);
        return convertToPage(result, pageable);
    }

    @Override
    public Page<Property> loadListByLikeCount(Pageable pageable) {
        Page<PropertyJpaEntity> result = repository.findAllOrderByLikeCount(pageable);
        return convertToPage(result, pageable);
    }

    @Override
    public Page<Property> loadListByViewCount(Pageable pageable) {
        Page<PropertyJpaEntity> result = repository.findAllOrderByViewCount(pageable);
        return convertToPage(result, pageable);
    }

    @Override
    public Page<Property> loadListByCondition(Pageable pageable, ConditionRequest request) {
        return null;
    }

    @Override
    public void deleteProperty(Long id) {
        repository.deleteById(id);
    }

    /**
     * Page<PropertyJpaEntity>를 Page<Property>로 변환하는 공통 메서드
     */

    private Page<Property> convertToPage(Page<PropertyJpaEntity> result, Pageable pageable) {
        List<Property> dtoList = result.map(PropertyJpaEntity::toDomain).getContent();
        return new PageImpl<>(dtoList, pageable, result.getTotalElements());
    }
}

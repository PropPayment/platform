package com.proppay.platform.pay.adapter.out;

import com.proppay.platform.pay.adapter.out.jpa.property.PropertyJpaEntity;
import com.proppay.platform.pay.adapter.out.jpa.property.PropertyJpaEntityRepository;
import com.proppay.platform.pay.application.in.dto.ConditionRequest;
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
        Page<Property> result = repository.findAllByOrderByCreatedAtDesc(pageable).map(PropertyJpaEntity::toDomain);
        List<Property> dtoList = result.getContent();
        return new PageImpl<>(dtoList, pageable, result.getTotalElements());
    }

    @Override
    public Page<Property> loadListByLikeCount(Pageable pageable) {
        Page<Property> result = repository.findAllOrderByLikeCount(pageable).map(PropertyJpaEntity::toDomain);
        List<Property> dtoList = result.getContent();
        return new PageImpl<>(dtoList, pageable, result.getTotalElements());
    }

    @Override
    public Page<Property> loadListByViewCount(Pageable pageable) {
        Page<Property> result = repository.findAllOrderByViewCount(pageable).map(PropertyJpaEntity::toDomain);
        List<Property> dtoList = result.getContent();
        return new PageImpl<>(dtoList, pageable, result.getTotalElements());
    }

    @Override
    public Page<Property> loadListByCondition(Pageable pageable, ConditionRequest request) {
        return null;
    }

    @Override
    public void deleteProperty(Long id) {
        repository.deleteById(id);
    }
}

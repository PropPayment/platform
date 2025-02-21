package com.proppay.platform.pay.application.service.property;


import com.proppay.platform.pay.application.in.dto.ConditionRequest;
import com.proppay.platform.pay.application.in.dto.PropertyRequest;
import com.proppay.platform.pay.application.in.property.*;
import com.proppay.platform.pay.application.out.property.DeletePropertyPort;
import com.proppay.platform.pay.application.out.property.LoadPropertyPort;
import com.proppay.platform.pay.application.out.user.LoadUserPort;
import com.proppay.platform.pay.application.out.property.SavePropertyPort;
import com.proppay.platform.pay.domain.property.Property;
import com.proppay.platform.pay.domain.property.PropertyAddress;
import com.proppay.platform.pay.domain.property.PropertySnippet;
import com.proppay.platform.pay.domain.property.PropertyStatistic;
import com.proppay.platform.pay.domain.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyService implements CreatePropertyUseCase, GetPropertyDetailsUseCase ,DeletePropertyUseCase, UpdatePropertyUseCase, ListPropertiesUseCase {

    private final SavePropertyPort savePort;
    private final LoadPropertyPort loadPort;
    private final DeletePropertyPort deletePort;
    private final LoadUserPort loadUserPort;

    @Override
    public Property create(PropertyRequest request) {

        User user = loadUserPort.loadUser(request.getUserId())
                .orElseThrow(() -> new NoSuchElementException("해당 유저가 존재하지 않습니다"));

        PropertyAddress address = PropertyAddress.of(request.getStreetAddress(), request.getDetailAddress(), request.getPostalCode());
        PropertySnippet snippet = PropertySnippet.of(request.getTitle(), request.getDescription(), request.getQuantity(), request.getBathrooms(), request.getBuiltYear());
        PropertyStatistic statistic = PropertyStatistic.of(0, 0);

        Property property = Property.of(user, request.getType(), address, snippet, statistic, request.getPrice());

        return savePort.saveProperty(property);
    }

    @Override
    public void deleteProperty(Long id) {
        deletePort.deleteProperty(id);
    }

    @Override
    public Optional<Property> getPropertyDetails(Long propertyId) {
        return loadPort.loadProperty(propertyId);
    }

    @Override
    public Page<Property> getList(Pageable pageable) {
        return loadPort.loadList(pageable);
    }

    @Override
    public Page<Property> getListByCondition(ConditionRequest request, Pageable pageable) {
        return loadPort.loadListByCondition(pageable, request);
    }

    @Override
    public Page<Property> getListByLikeCount(Pageable pageable) {
        return loadPort.loadListByLikeCount(pageable);
    }

    @Override
    public Page<Property> getListByViewCount(Pageable pageable) {
        return loadPort.loadListByViewCount(pageable);
    }

}

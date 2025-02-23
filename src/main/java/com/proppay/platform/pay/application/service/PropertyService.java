package com.proppay.platform.pay.application.service;


import com.proppay.platform.pay.adapter.in.web.dto.PropertyConditionRequest;
import com.proppay.platform.pay.adapter.in.web.dto.PropertyDetailResponse;
import com.proppay.platform.pay.adapter.in.web.dto.PropertyRequest;
import com.proppay.platform.pay.application.in.property.*;
import com.proppay.platform.pay.application.out.estate.LoadEstatePort;
import com.proppay.platform.pay.application.out.property.DeletePropertyPort;
import com.proppay.platform.pay.application.out.property.LoadPropertyPort;
import com.proppay.platform.pay.application.out.user.LoadUserPort;
import com.proppay.platform.pay.application.out.property.SavePropertyPort;
import com.proppay.platform.pay.domain.estate.Estate;
import com.proppay.platform.pay.domain.property.Property;
import com.proppay.platform.pay.domain.property.PropertyAddress;
import com.proppay.platform.pay.domain.property.PropertySnippet;
import com.proppay.platform.pay.domain.property.PropertyStatistic;
import com.proppay.platform.pay.domain.user.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional

public class PropertyService implements CreatePropertyUseCase, GetPropertyDetailsUseCase ,DeletePropertyUseCase, UpdatePropertyUseCase, ListPropertiesUseCase {

    private final SavePropertyPort savePort;
    private final LoadPropertyPort loadPort;
    private final DeletePropertyPort deletePort;

    private final LoadUserPort loadUserPort;
    private final LoadEstatePort loadEstatePort;

    @Override
    public PropertyDetailResponse create(PropertyRequest request) {

        User user = getUser(request.getUserId());

        Estate estate = loadEstatePort.loadEstateByCode(request.getAptCode())
                .orElseThrow(() -> new EntityNotFoundException("해당하는 아파트가 존재하지 않습니다."));

        PropertyAddress address = PropertyAddress.of(estate.getKaptAddr(), request.getDetailAddress(), request.getPostalCode());
        PropertySnippet snippet = PropertySnippet.of(estate.getKaptName(), request.getDescription(), request.getQuantity(), request.getBathrooms(), request.getBuiltYear());

        PropertyStatistic statistic = PropertyStatistic.of(0, 0);

        Property property = savePort.saveProperty(Property.of(user.getId(), request.getType(), address, snippet, statistic, request.getPrice(), request.getAptCode()));

        return PropertyDetailResponse.from(property, user);
    }

    @Override
    public void deleteProperty(Long id) {
        deletePort.deleteProperty(id);
    }

    @Override
    public PropertyDetailResponse getPropertyDetails(Long propertyId) {

        Property property = loadPort.loadProperty(propertyId)
                .orElseThrow(() -> new EntityNotFoundException("매물이 존재하지 않습니다."));

        User user = getUser(property.getOwnerId());

        return PropertyDetailResponse.from(property, user);

    }

    @Override
    public Page<Property> getList(Pageable pageable) {
        return loadPort.loadList(pageable);
    }

    @Override
    public Page<Property> getListByCondition(PropertyConditionRequest request, Pageable pageable) {
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


    private User getUser(Long userId) {
        return loadUserPort.loadUser(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다"));
    }

}

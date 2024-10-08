package com.pinpoint.PinPoint.services.Impl;

import com.pinpoint.PinPoint.entity.StateInfoEntity;
import com.pinpoint.PinPoint.repository.StateInfoRepository;
import com.pinpoint.PinPoint.services.StateService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {
    Logger logger = LoggerFactory.getLogger(StateServiceImpl.class);
    private final StateInfoRepository stateInfoRepository;

    @Override
    public StateInfoEntity getStateInfoByPincode(Integer pincode) {
        return stateInfoRepository.findByPincode(pincode).orElse(null);
    }

    @Override
    public List<String> getAllDistrictByStateName(String stateName) {
        if (stateName == null) {
//            throw ExceptionDataNotFound("message", "state name "+stateName+" does not exists, check name again");
            return null;
        }
        stateName = stateName.trim();
        return stateInfoRepository.findDistinctDistrictByState(stateName).orElse(null);
    }

    @Override
    public String getDistrictOfPincode(Integer pincode) {
        StateInfoEntity result = stateInfoRepository.findByPincode(pincode).orElse(null);
        if (result == null) {
//             throw DataNotFoundError
            return null;
        }
        return result.getDistrict();
    }

    @Override
    public String getStateOfPincode(Integer pincode) {
        StateInfoEntity result = stateInfoRepository.findByPincode(pincode).orElse(null);
        if (result == null) {
            // throw DataNotFundError
            return null;
        }
        return result.getState();
    }

    @Override
    public List<String> getAllStatesOfIndia() {
        return stateInfoRepository.findDistinctState();
    }
}

package myy803.springboot.traineeship;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import myy803.springboot.traineeship.dao.CompanyMapper;
import myy803.springboot.traineeship.dao.TraineeshipMapper;
import myy803.springboot.traineeship.model.Company;
import myy803.springboot.traineeship.model.TraineeshipPosition;
import myy803.springboot.traineeship.service.CompanyServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CompanyServiceTest {

    @Mock
    private TraineeshipMapper traineeshipMapper;
    @Mock
    private CompanyMapper companyMapper;

    @InjectMocks
    private CompanyServiceImpl companyService;

    public CompanyServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAvailablePositions() {
    	Company company = new Company();
    	company.setUsername("test");
    	Optional<Company> optionalCompany = Optional.of(company);
    	
    	TraineeshipPosition mockPosition = new TraineeshipPosition();
    	mockPosition.setCompany(optionalCompany.get());
        List<TraineeshipPosition> mockPositions = new ArrayList<>();
        mockPositions.add(mockPosition);
        
        when(companyMapper.findByUsername("test")).thenReturn(optionalCompany);
        when(traineeshipMapper.findAllByCompany(optionalCompany)).thenReturn(mockPositions);

        List<TraineeshipPosition> result = companyService.retrieveAvailablePositions("test");

        assertEquals(1, result.size());
    }

    @Test
    void testEmptyPositionsList() {
        when(traineeshipMapper.findAll()).thenReturn(new ArrayList<>());

        List<TraineeshipPosition> result = companyService.retrieveAssignedPositions(null);

        assertTrue(result.isEmpty());
    }
}


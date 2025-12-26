    package com.example.demo.service.impl;

    import com.example.demo.model.ShiftTemplate;
    import com.example.demo.repository.ShiftTemplateRepository;
    import com.example.demo.service.ShiftTemplateService;
    import org.springframework.stereotype.Service;
    import java.util.List;

    @Service
    public class ShiftTemplateServiceImpl implements ShiftTemplateService {

        private final ShiftTemplateRepository shiftTemplateRepository;

        public ShiftTemplateServiceImpl(ShiftTemplateRepository shiftTemplateRepository) {
            this.shiftTemplateRepository = shiftTemplateRepository;
        }

        @Override
        public ShiftTemplate saveTemplate(ShiftTemplate template) {
            return shiftTemplateRepository.save(template);
        }

        @Override
        public ShiftTemplate getTemplateById(Long id) {
            return shiftTemplateRepository.findById(id).orElse(null);
        }

        @Override
        public List<ShiftTemplate> getTemplatesByDepartment(Long departmentId) {
            return shiftTemplateRepository.findByDepartmentId(departmentId);
        }

        @Override
        public void deleteTemplate(Long id) {
            shiftTemplateRepository.deleteById(id);
        }

        @Override
        public List<ShiftTemplate> getAll() {
            return shiftTemplateRepository.findAll();
        }
    }
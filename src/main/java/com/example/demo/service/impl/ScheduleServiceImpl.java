@Override
public void deleteSchedule(Long id) {
    scheduleRepository.deleteById(id);
}
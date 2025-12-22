@Entity
@Table(name = "employeeavailability")
public class EmployeeAvailability {
    // ... other fields ...
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;  // JPA handles relationship
    
    // NO setEmployeeId() method needed!
    
    // Normal getters/setters only
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
}

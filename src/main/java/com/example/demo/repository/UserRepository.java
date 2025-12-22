package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserDetailsService {

    // Basic validations
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);

    // Single lookups
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailIgnoreCase(String email);

    // Active users
    List<User> findByActiveTrueOrderByName();
    Page<User> findByActiveTrue(Pageable pageable);

    // Role-based queries
    List<User> findByRoleOrderByName(UserRole role);
    Page<User> findByRole(UserRole role, Pageable pageable);
    List<User> findByRoleAndActiveTrueOrderByName(UserRole role);

    // Search functionality
    Page<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name, String email, Pageable pageable);
    List<User> findByNameContainingIgnoreCaseOrderByName(String name);

    // Security & Audit queries
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.active = true")
    Optional<User> findActiveByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.lastLoginAt < :cutoffDate")
    List<User> findInactiveUsers(@Param("cutoffDate") LocalDateTime cutoffDate);

    // Admin operations
    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role")
    long countUsersByRole(@Param("role") UserRole role);

    @Query("SELECT u.role, COUNT(u) FROM User u GROUP BY u.role")
    List<Object[]> getUserRoleDistribution();

    // Bulk operations
    void deleteByRole(UserRole role);
    List<User> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Native query for advanced reporting
    @Query(value = "SELECT u.*, COUNT(l.id) as login_count " +
                   "FROM users u " +
                   "LEFT JOIN user_logins l ON u.id = l.user_id " +
                   "WHERE u.active = 1 " +
                   "GROUP BY u.id " +
                   "ORDER BY login_count DESC", nativeQuery = true)
    List<Object[]> findActiveUsersWithLoginStats();

    // Spring Security UserDetailsService
    @Override
    default User loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }
}

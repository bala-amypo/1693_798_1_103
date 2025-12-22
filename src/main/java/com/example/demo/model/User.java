// Inside User.java
public String getEmail() { return this.email; }
public String getPassword() { return this.password; }
public UserRole getRole() { return this.role; }

// Required by UserDetails interface
@Override
public String getUsername() { return this.email; } 

@Override
public boolean isAccountNonExpired() { return true; }

@Override
public boolean isAccountNonLocked() { return true; }

@Override
public boolean isCredentialsNonExpired() { return true; }

@Override
public boolean isEnabled() { return true; }
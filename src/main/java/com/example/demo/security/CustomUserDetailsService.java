// Inside the loadUserByUsername method, change the return to this:
return org.springframework.security.core.userdetails.User.builder()
    .username(user.getEmail())
    .password(user.getPassword())
    .authorities(user.getRole().name()) // Use .name() to pass a String
    .build();
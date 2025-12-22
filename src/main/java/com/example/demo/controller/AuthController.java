@PostMapping("/login")
public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
    // SIMPLIFIED - no Authentication casting needed
    User user = userService.findByEmail(request.getEmail());
    String token = jwtTokenProvider.generateToken(user.getEmail());  // Pass email directly
    
    AuthResponse response = new AuthResponse(token, user.getId(), user.getEmail(), user.getRole());
    return ResponseEntity.ok(response);
}

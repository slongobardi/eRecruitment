package it.trefin.erecruitment.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtRequestFilter jwtRequestFilter;
  private final CustomUserDetailsService customUserDetailsService;

  public SecurityConfig(JwtRequestFilter jwtRequestFilter,
                        CustomUserDetailsService customUserDetailsService) {
    this.jwtRequestFilter = jwtRequestFilter;
    this.customUserDetailsService = customUserDetailsService;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
      .cors(withDefaults())
      .csrf(csrf -> csrf.disable())

      // ✅ evita popup browser/basic e form login
      .httpBasic(b -> b.disable())
      .formLogin(f -> f.disable())

      .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

      .authorizeHttpRequests(auth -> auth

        // (consigliato per Angular) preflight
        // .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()

        .requestMatchers(
          "/auth/login",
          "/auth/register",
          "/auth/registerEvent",
          "/auth/registerEventUniversita",
          "/auth/cambiaPassword",
          "/auth/resetPassword/**",
          "/auth/resetPasswordAdmin/**",

          "/api/sendEmail/inviaEmail",
          "/api/sendEmail/inviaEmailAdmin",
          "/api/sendEmail/inviaEmail/**",
          "/api/sendEmail/confirmEmail/**",

          "/api/candidatura/all",
          "/api/candidatura/visualizza/**",
          "/api/candidatura/skillDellaCandidatura/**",

          "/api/azienda/visualizza/**",

          "/api/skill/visualizza/**",
          "/api/skill/all"
        ).permitAll()

        .requestMatchers("/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated()
      );

    // ✅ collega il tuo userDetailsService + encoder (equivalente del configure(AuthenticationManagerBuilder...))
    http.authenticationProvider(authenticationProvider());

    // ✅ filtro JWT
    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(customUserDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // ✅ equivalente del tuo authenticationManagerBean()
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }
}

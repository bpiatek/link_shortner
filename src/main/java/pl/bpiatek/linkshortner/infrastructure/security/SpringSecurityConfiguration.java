package pl.bpiatek.linkshortner.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.bpiatek.linkshortner.user.domain.UserPrincipalDetailsService;

/**
 * Created by Bartosz Piatek on 04/11/2019
 */
@Configuration
@EnableWebSecurity
class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private UserPrincipalDetailsService userPrincipalDetailsService;

  public SpringSecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService) {
    this.userPrincipalDetailsService = userPrincipalDetailsService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().headers().frameOptions().disable()
        .and()
        .authorizeRequests()
        .antMatchers("/api/test/2").hasAuthority("ACCESS_TEST2")
        .antMatchers("/api/test/1").hasAuthority("ACCESS_TEST1")
        .antMatchers("/api/user/all").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .httpBasic();
//        .antMatchers("/api/authentication/login").permitAll()
//        .antMatchers("/api/**").authenticated();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider());
  }

  @Bean
  DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    daoAuthenticationProvider.setUserDetailsService(userPrincipalDetailsService);

    return daoAuthenticationProvider;
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

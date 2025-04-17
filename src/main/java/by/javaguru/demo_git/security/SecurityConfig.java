package by.javaguru.demo_git.security;// Импортируем нужные классы из Spring Security
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// Эта аннотация говорит Spring: "это класс конфигурации"
// Здесь мы настраиваем безопасность нашего веб-приложения
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Метод, помеченный @Bean, говорит Spring: "создай и управляй этим объектом"
    // В данном случае — это цепочка фильтров безопасности (SecurityFilterChain)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 🔒 CSRF — это защита от подделки межсайтовых запросов.
                // Мы её отключаем, потому что чаще всего она мешает при разработке REST API
                .csrf(csrf -> csrf.disable())

                // 👮 Здесь мы говорим, какие запросы на сервер можно делать, а какие — нет
                .authorizeHttpRequests(auth -> auth
                        // ⛔️ Все запросы (на любой URL) требуют авторизации (логина)
                        .anyRequest().authenticated()
                )

                // 🧾 Включаем базовую HTTP авторизацию
                // Это такой простой способ входа: браузер покажет всплывающее окно "введите логин и пароль"
                .httpBasic(Customizer.withDefaults());

        // ✅ Возвращаем объект конфигурации — так Spring Security поймёт, как фильтровать запросы
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("Admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.builder()
                .username("User")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);

    }
}

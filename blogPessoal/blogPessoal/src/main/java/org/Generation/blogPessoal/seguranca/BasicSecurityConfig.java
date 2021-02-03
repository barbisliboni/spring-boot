package org.Generation.blogPessoal.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity // habilitando a configuraçao de web security
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired /*
				 * injetando dependencias para que um objeto passe a funcionar, tendo seu ciclo
				 * de vida controlado pelo spring
				 */

	private UserDetailsService userDetailsService;
	// UserDetailsService é uma classe ja existente no WebSecurity

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);

		/*
		 * o AuthenticationManagerBuilder vai ser usado apenas para criar um
		 * AuthenticationManager local, que vai ser "filho" de um global
		 */

		/*
		 * um AuthenticationManager é um container provedor de autenticações, dando uma
		 * interface consistente para as mesmas
		 */
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		/*
		 * esse método irá fazer com que a senha recebida se transforme de um formato de
		 * texto literal para uma sequencia ilegivel de caracteres
		 */
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/usuarios/logar").permitAll().antMatchers("/usuarios/cadastrar")
				.permitAll().anyRequest().authenticated().and().httpBasic().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors().and().csrf().disable();

		/*
		 * .antMatchers("/usuarios/logar").permitAll() liberar/permitir end
		 * points/caminhos dentro do controller para que o client tenha acesso ao mesmo
		 * sem precisar passar uma chave token
		 */

		/*
		 * .anyRequest().authenticated() todas as requisições deverão ser autenticadas,
		 * ou seja, será preciso passar uma chave no HEADER
		 */

		/*
		 * .httpBasic() vai habilitar o Http Basic Authentication para a aplicação com
		 * alguns padrões "razoáveis"
		 */

		/*
		 * .sessionManagement() indicar qual o tipo de sessao que sera utilizada
		 * STATELESS, pois a aplicação nao ira guardar nenhuma informaçao sobre o
		 * logged-in do usuario na memória
		 */

		/*
		 * .cors() permite a restrição de recursos implementados nos web browsers, ou
		 * seja, previne o código de JavaScript de produzir ou consumir as requisições
		 * de uma origem diferente
		 */

		/*
		 * .csrf().disable() é um "ataque" que força um usuario a executar ações
		 * indesejadas em uma aplicaçao web a qual o mesmo já está atualmente
		 * autenticado, portanto, deixamos a opçao disable
		 */

		// throws Exception é uma tratação de erro
	}

}

package org.Generation.blogPessoal.seguranca;

import java.util.Optional;

import org.Generation.blogPessoal.model.Usuario;
import org.Generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // dizendo que está classe trata-se de um serviço
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired // injetando o item abaixo
	private UsuarioRepository userRepository; // repositório para manipulação de dados de persistência do usuário

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Usuario> user = userRepository.findByUsuario(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

		/*
		 * orElseThrow() é usado para pegar o valor de uma Optional instance se a mesma
		 * está presente, e se não há nenhum valor presente nessa Optional instance,
		 * então esse método vai "jogar"(throw) a exception gerada
		 */

		return user.map(UserDetailsImpl::new).get();
		/*
		 * os :: servem para dizer que o UserDetailsImpl está se referindo ao método com
		 * a ajuda direta da classe UserDetailsImpl
		 */
	}
}

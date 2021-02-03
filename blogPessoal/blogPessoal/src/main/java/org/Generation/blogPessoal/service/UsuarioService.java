package org.Generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.Generation.blogPessoal.model.UserLogin;
import org.Generation.blogPessoal.model.Usuario;
import org.Generation.blogPessoal.repository.UsuarioRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired // injetando o repositório de usuário
	private UsuarioRepository repository;

	public Optional<Usuario> CadastrarUsuario(Usuario usuario) {

		if (repository.findByUsuario(usuario.getUsuario()).isPresent())
			return null;

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // BCrypt é um método de criptografia do tipo hash
																		// para senhas

		String senhaEncoder = encoder.encode(usuario.getSenha()); // recebendo e codificando a senha do usuario
		usuario.setSenha(senhaEncoder); // redefinindo a senha do usuario, sendo substituida pela senha criptografada

		return Optional.of(repository.save(usuario)); // retornando e armazenando a senha criptografada
	}

	// Regra de negócios que irá ditar tudo que se refere a logar
	public Optional<UserLogin> Logar(Optional<UserLogin> user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // instanciando o objeto encoder
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) { // conferindo se as senhas são
																					// iguais

				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				user.get().setSenha(usuario.get().getSenha());

				return user;

			}
		}
		return null;
	}

}

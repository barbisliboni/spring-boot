package org.Generation.blogPessoal.seguranca;

import java.util.Collection;

import org.Generation.blogPessoal.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*aplicando a regra de negócios da interface UserDetails,
 * a qual simplesmente armazenará informação do usuário que mais tarde é encapsulada
 * em objetos de autenticação
 */
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	/*
	 * O serialVersionUID serve para rastrear a compatibilidade de versões
	 * serializadas das classes, por exemplo, se eu tenho uma classe X, salvo e
	 * depois de um tempo altero essa classe, talvez os dados desserializados nao
	 * sejam compativeis com a nova versao da classe, uma vez que ela sofreu uma
	 * alteração
	 */

	private String userName;
	private String password;

	public UserDetailsImpl(Usuario user) { // construtor de classe
		this.userName = user.getUsuario();
		this.password = user.getSenha();
	}

	public UserDetailsImpl() {
	} // construtor de classe vazio

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// indica se a conta do usuário está expirada ou não
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		//// indica se a conta do usuário está bloqueada ou não
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// indica se as credenciais do usuário (senha) estão expiradas ou não
		return true;
	}

	@Override
	public boolean isEnabled() {
		// indica se o usuário está habilitado ou não
		return true;
	}

}
